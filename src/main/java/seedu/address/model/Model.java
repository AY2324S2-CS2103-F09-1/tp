package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.house.House;
import seedu.address.model.house.PriceAndHousingTypePredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;
import seedu.address.ui.Ui;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the person with the unique name {@code name} .
     */
    Person findPersonByName(Name name);

    /**
     * Returns true if a person who is the same object as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person with the same name as {@code person} exists in the address book.
     */
    boolean hasPerson(Name name);


    /**
     * Returns true if a house with the same address details as {@code house} exists in the address book.
     */
    boolean hasHouse(House house);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePersonFromPersons(Person target);

    /**
     * Deletes the given house.
     * The house must belong to a seller in the address book.
     */
    void deleteHouse(House house, Person owner);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds the given house.
     * {@code house} must not already exist in the address book.
     */
    void addHouse(House house, Person person);

    /**
     * Adds the given house.
     * {@code house} must not already exist in the address book.
     */
    void addHouseToHouses(House house);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns the current ui state of the program.
     */
    State getState();

    /**
     * Update the ui state of the program.
     */
    void setState(State newState);

    /**
     * Check if the new ui state is same as the current ui state.
     */
    boolean isSameState(State newState);

    /**
     * Initialise the ui.
     */
    void setUi(Ui ui);

    /**
     * Displays the given person.
     * {@code target} must exist in the address book.
     */
    void showPerson(Person target);

    /**
     * Displays the match results of the buyer.
     */
    void showMatchResults(ObservableList<Seller> seller);
    /**
     * Returns the person for viewing details.
     */
    Person getPerson();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filtered list of sellers based on the given price and housing type predicate.
     *
     * @param predicate The predicate to filter the sellers.
     */
    void updateFilteredSellerList(PriceAndHousingTypePredicate predicate);


    /**
     * Retrieves the filtered list of sellers.
     * @return The filtered list of sellers.
     */
    public ObservableList<House> getAllFilteredHouseList(PriceAndHousingTypePredicate predicate);

    /** Returns an unmodifiable view of seller list*/
    public ObservableList<Seller> getFilteredSellerList();
}
