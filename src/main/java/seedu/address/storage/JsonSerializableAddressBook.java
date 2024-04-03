package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.house.House;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {
    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    private final List<JsonAdaptedSeller> sellers = new ArrayList<>();
    private final List<JsonAdaptedBuyer> buyers = new ArrayList<>();
    private final List<JsonAdaptedHouse> houses = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("buyers") List<JsonAdaptedBuyer> buyers,
                @JsonProperty("sellers") List<JsonAdaptedSeller> sellers) {
        if (buyers != null) {
            this.buyers.addAll(buyers);
        }
        if (sellers != null) {
            this.sellers.addAll(sellers);
        }
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        source.getPersonList().stream()
                .filter(Seller.class::isInstance)
                .map(Seller.class::cast)
                .map(JsonAdaptedSeller::new)
                .forEach(sellers::add);

        source.getPersonList().stream()
                .filter(Buyer.class::isInstance)
                .map(Buyer.class::cast)
                .map(JsonAdaptedBuyer::new)
                .forEach(buyers::add);
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        List<Person> combinedList = new ArrayList<>();

        for (JsonAdaptedSeller jsonAdaptedSeller : sellers) {
            Seller seller = jsonAdaptedSeller.toModelType();
            combinedList.add(seller);
            for (House h: seller.getHouses()) {
                addressBook.addHouseToHouses(h);
            }
        }

        for (JsonAdaptedBuyer jsonAdaptedBuyer : buyers) {
            Buyer buyer = jsonAdaptedBuyer.toModelType();
            combinedList.add(buyer);
        }

        for (Person person : combinedList) {
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }

        return addressBook;
    }
}
