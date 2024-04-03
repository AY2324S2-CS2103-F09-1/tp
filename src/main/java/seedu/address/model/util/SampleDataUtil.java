package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.house.Block;
import seedu.address.model.house.Condominium;
import seedu.address.model.house.Hdb;
import seedu.address.model.house.House;
import seedu.address.model.house.HousingType;
import seedu.address.model.house.Level;
import seedu.address.model.house.PostalCode;
import seedu.address.model.house.Price;
import seedu.address.model.house.Street;
import seedu.address.model.house.UnitNumber;
import seedu.address.model.person.Budget;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Seller;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Buyer[] getSampleBuyers() {
        return new Buyer[]{
            new Buyer(new Name("Alex Yeoh"), new Phone("87438807"),
                        new Email("alexyeoh@example.com"), new Budget("100000"),
                        new HousingType("Hdb")),
            new Buyer(new Name("Cernice Yu"), new Phone("99272758"),
                        new Email("cerniceyu@example.com"), new Budget("200000"),
                        new HousingType("Condominium")),
            new Buyer(new Name("Eharlotte Oliveiro"), new Phone("93210283"),
                        new Email("eharlotte@example.com"), new Budget("300000"),
                        new HousingType("Hdb")),
        };
    }

    public static Seller[] getSampleSellers() {
        ObservableList<House> bavidLiHouses = FXCollections.observableArrayList();
        bavidLiHouses.add(new Hdb(new Level("3"), new PostalCode("098703"),
                new Street("Ang Mo Kio Avenue 1"), new UnitNumber("02"), new Block("51"), new Price("1111111")));
        ObservableList<House> drfanHouses = FXCollections.observableArrayList();
        drfanHouses.add(new Condominium(new Level("4"), new PostalCode("098713"),
                new Street("Ang Mo Kio Avenue 2"), new UnitNumber("03"), new Block("52"), new Price("2222222")));
        ObservableList<House> foyHouses = FXCollections.observableArrayList();
        foyHouses.add(new Hdb(new Level("5"), new PostalCode("098723"),
                new Street("Ang Mo Kio Avenue 3"), new UnitNumber("04"), new Block("53"), new Price("3333333")));
        foyHouses.add(new Hdb(new Level("6"), new PostalCode("098724"),
                new Street("Toa Payoh Avenue 4"), new UnitNumber("05"), new Block("54"), new Price("4444444")));

        return new Seller[]{
            new Seller(new Name("Bavid Li"), new Phone("91031282"),
                        new Email("libavid@example.com"), bavidLiHouses),
            new Seller(new Name("Drfan Ibrahim"), new Phone("92492021"),
                        new Email("Drfan@example.com"), drfanHouses),
            new Seller(new Name("Foy Balakrishnan"), new Phone("92624417"),
                        new Email("foyb@example.com"), foyHouses)
        };
    }

    public static List<Person> getSamplePersons() {
        List<Person> persons = new ArrayList<>();
        persons.addAll(Arrays.asList(getSampleBuyers()));
        persons.addAll(Arrays.asList(getSampleSellers()));
        return persons;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person person: getSamplePersons()) {
            sampleAb.addPerson(person);
            if (person instanceof Seller) {
                for (House h: ((Seller) person).getHouses()) {
                    sampleAb.addHouseToHouses(h);
                }
            }
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
