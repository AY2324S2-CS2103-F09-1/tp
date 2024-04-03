package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.house.House;
import seedu.address.model.house.PriceAndHousingTypePredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;
import seedu.address.ui.Ui;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private ObservableList<Seller> filteredSellers;


    private Ui ui = null;
    private State state = State.PERSON_LIST;
    private Person currentDisplayedPerson = null;
    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredSellers = new FilteredList<>(FXCollections.observableArrayList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public Person findPersonByName(Name name) {
        requireNonNull(name);
        return addressBook.findPersonByName(name);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public boolean hasPerson(Name name) {
        requireNonNull(name);
        return addressBook.hasPerson(name);
    }

    @Override
    public boolean hasHouse(House house) {
        requireNonNull(house);
        return addressBook.hasHouse(house);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void deletePersonFromPersons(Person target) {
        addressBook.removePersonFromPersons(target);
    }

    @Override
    public void deleteHouse(House house, Person owner) {
        addressBook.removeHouse(house, owner);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addHouse(House house, Person owner) {
        addressBook.addHouse(house, owner);
    }

    @Override
    public void addHouseToHouses(House house) {
        addressBook.addHouseToHouses(house);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }


    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Update Ui state ============================================================================

    public State getState() {
        return state;
    }

    public void setState(State newState) {
        boolean isStateChanged = isSameState(newState);
        if (!isStateChanged) {
            state = newState;
            if (ui != null) {
                ui.updateUiLayout(newState);
            }
        }
    }

    public boolean isSameState(State newState) {
        return state.equals(newState);
    }

    //=========== Get Data for displaying ====================================================================

    @Override
    public void setUi(Ui ui) {
        this.ui = ui;
    }
    @Override
    public void showPerson(Person target) {
        requireNonNull(target);
        this.currentDisplayedPerson = target;
        if (ui != null) {
            ui.showPersonDetails(currentDisplayedPerson);
        }
    }

    @Override
    public void showMatchResults(ObservableList<Seller> seller) {
        requireNonNull(seller);
        if (ui != null) {
            ui.showMatchResults(filteredSellers);
        }
    }

    @Override
    public Person getPerson() {
        return this.currentDisplayedPerson;
    }

    //=================================================================================================
    @Override
    public void updateFilteredSellerList(PriceAndHousingTypePredicate predicate) {
        requireNonNull(predicate);
        filteredSellers.clear();
        ArrayList<Seller> temp = new ArrayList<>();
        filteredPersons.setPredicate(person -> {
            if (person instanceof Seller) {
                Seller seller = ((Seller) person).copy();
                ObservableList<House> houses = getFilteredHousesForSeller((Seller) person, predicate);

                if (houses.isEmpty()) {
                    return false;
                }
                seller.getHouses().clear();

                for (House house : houses) {
                    seller.addHouse(house);
                }
                temp.add(seller);
                filteredSellers = FXCollections.observableArrayList(temp);
                return true;
            }

            return false;
        });
    }

    @Override
    public ObservableList<Seller> getFilteredSellerList() {
        return filteredSellers;
    }


    private ObservableList<House> getFilteredHousesForSeller(Seller seller, PriceAndHousingTypePredicate predicate) {
        ObservableList<House> originalHouseList = seller.getHouses();
        FilteredList<House> filteredHouseList = new FilteredList<>(originalHouseList, predicate);

        ObservableList<House> convertedList = FXCollections.observableArrayList();
        convertedList.addAll(filteredHouseList);


        return convertedList;
    }

    @Override
    public ObservableList<House> getAllFilteredHouseList(PriceAndHousingTypePredicate predicate) {
        FilteredList<Person> filteredSellers = filteredPersons.filtered(person -> person instanceof Seller);
        ObservableList<House> allHouses = filteredSellers.stream()
                .map(seller -> ((Seller) seller).getHouses())
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        return allHouses.filtered(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
