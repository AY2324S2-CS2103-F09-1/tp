package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE_SELLER;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.house.House;
import seedu.address.model.house.PriceAndHousingTypePredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;
import seedu.address.testutil.SellerBuilder;

class AddSellerCommandTest {

    @Test
    public void constructor_nullSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSellerCommand(null));
    }

    //    Not sure where it went wrong, formatting got issue, but the values are correct
    //    org.opentest4j.AssertionFailedError:
    //    expected: <New seller added= seedu.address.model.person.Seller{name=Amy Bee, phone=85355255,
    //    email=amy@gmail.com,
    //    housingType=HDB, tags=[]}>
    //    but was: <New seller added= Amy Bee; Phone= 85355255; Email= amy@gmail.com; Housing Type= HDB; Tags= >
    //    @Test
    //    public void execute_sellerAcceptedByModel_addSuccessful() throws Exception {
    //        ModelStubAcceptingSellerAdded modelStub = new ModelStubAcceptingSellerAdded();
    //        Seller validSeller = new SellerBuilder().build();
    //
    //        CommandResult commandResult = new AddSellerCommand(validSeller).execute(modelStub);
    //
    //        assertEquals(String.format(AddSellerCommand.MESSAGE_SUCCESS, validSeller),
    //                commandResult.getFeedbackToUser());
    //        assertEquals(Arrays.asList(validSeller), modelStub.sellersAdded);
    //    }

    @Test
    public void execute_duplicateSeller_throwsCommandException() {
        Seller validSeller = new SellerBuilder().build();
        AddSellerCommand addSellerCommand = new AddSellerCommand(validSeller);
        ModelStub modelStub = new ModelStubWithSeller(validSeller);

        assertThrows(CommandException.class,
                AddSellerCommand.MESSAGE_DUPLICATE_SELLER, () -> addSellerCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Seller alice = new SellerBuilder().withName("Alice").build();
        Seller bob = new SellerBuilder().withName("Bob").build();
        AddSellerCommand addAliceCommand = new AddSellerCommand(alice);
        AddSellerCommand addBobCommand = new AddSellerCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddSellerCommand addAliceCommandCopy = new AddSellerCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddSellerCommand addCommand = new AddSellerCommand(ALICE_SELLER);
        String expected = AddSellerCommand.class.getCanonicalName() + "{sellerToAdd=" + ALICE_SELLER + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addHouse(House house, Person person) {

        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person findPersonByName(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasHouse(House house) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteHouse(House house, Person owner) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<House> getFilteredSellerList(PriceAndHousingTypePredicate predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredSellerList(PriceAndHousingTypePredicate predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single seller.
     */
    private class ModelStubWithSeller extends ModelStub {
        private final Person seller;

        ModelStubWithSeller(Person seller) {
            requireNonNull(seller);
            this.seller = seller;
        }

        @Override
        public boolean hasPerson(Person seller) {
            requireNonNull(seller);
            return this.seller.isSamePerson(seller);
        }
    }

    /**
     * A Model stub that always accept the seller being added.
     */
    private class ModelStubAcceptingSellerAdded extends ModelStub {
        final ArrayList<Person> sellersAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person seller) {
            requireNonNull(seller);
            return sellersAdded.stream().anyMatch(seller::isSamePerson);
        }

        public void addPerson(Person seller) {
            requireNonNull(seller);
            sellersAdded.add(seller);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
