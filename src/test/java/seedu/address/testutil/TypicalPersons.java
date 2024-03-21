package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUSING_TYPE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUSING_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalHouses.HOUSE2;
import static seedu.address.testutil.TypicalHouses.HOUSE3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.house.Block;
import seedu.address.model.house.Level;
import seedu.address.model.house.NonLanded;
import seedu.address.model.house.PostalCode;
import seedu.address.model.house.Street;
import seedu.address.model.house.UnitNumber;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 * Currently, it has been replaced by sellers, need to add buyers too
 */
public class TypicalPersons {

    public static final Seller ALICE_SELLER = new SellerBuilder().withName("Alice Pauline")
            .withHousingType("HDB").withEmail("alice@example.com")
            .withPhone("94351253").withHouses(new NonLanded(new Block("10A"), new Level("15"),
                    new PostalCode("654321"), new Street("Orchard Street"), new UnitNumber("150")))
            .withTags("friends").build();
    public static final Seller BENSON_SELLER = new SellerBuilder().withName("Benson Meier")
            .withHousingType("HDB").withEmail("johnd@example.com")
            .withPhone("94351253").withHouses(new NonLanded(new Level("16"), new PostalCode("654321"),
                    new Street("Orchard Street"), new UnitNumber("150")))
            .withTags("friends").build();
    public static final Seller CARL_SELLER = new SellerBuilder().withName("Carl Kurz").withPhone("95352563")
            .withHousingType("HDB").withEmail("heinz@example.com").withHouses(new NonLanded(new Block("10B"),
                    new Level("17"), new PostalCode("654322"), new Street("Orchard Street 1"), new UnitNumber("150")))
            .build();
    public static final Seller DANIEL_SELLER = new SellerBuilder().withName("Daniel Meier").withPhone("87652533")
            .withHousingType("HDB")
            .withEmail("cornelia@example.com").withHouses(new NonLanded(new Block("10C"), new Level("18"),
                    new PostalCode("654323"), new Street("Orchard Street 2"),
                    new UnitNumber("150"))).withTags("friends").build();
    public static final Seller ELLE_SELLER = new SellerBuilder().withName("Elle Meyer").withPhone("9482224")
            .withHousingType("HDB")
            .withEmail("heinz@example.com").withHouses(new NonLanded(new Block("10D"), new Level("19"),
                    new PostalCode("654324"), new Street("Orchard Street 3"), new UnitNumber("150"))).build();
    public static final Seller FIONA_SELLER = new SellerBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withHousingType("HDB")
            .withEmail("heinz@example.com").withHouses(new NonLanded(new Block("10E"), new Level("20"),
                    new PostalCode("654325"), new Street("Orchard Street 4"), new UnitNumber("150"))).build();
    public static final Seller GEORGE_SELLER = new SellerBuilder().withName("George Best").withPhone("9482442")
            .withHousingType("HDB")
            .withEmail("heinz@example.com").withHouses(new NonLanded(new Block("10F"), new Level("21"),
                    new PostalCode("654326"), new Street("Orchard Street 5"), new UnitNumber("150"))).build();

    // Manually added
    public static final Seller HOON_SELLER = new SellerBuilder().withName("Hoon Meier").withPhone("8482424")
            .withHousingType("HDB")
            .withEmail("stefan@example.com").withEmail("heinz@example.com").withHouses(new NonLanded(new Level("22"),
                    new PostalCode("654327"), new Street("Orchard Street 6"), new UnitNumber("150"))).build();
    public static final Seller IDA_SELLER = new SellerBuilder().withName("Ida Mueller").withPhone("8482131")
            .withHousingType("HDB")
            .withEmail("hans@example.com").withEmail("heinz@example.com").withHouses(new NonLanded(new Level("23"),
                    new PostalCode("654328"), new Street("Orchard Street 7"), new UnitNumber("150"))).build();

    // Manually added - Seller's details found in {@code CommandTestUtil}
    public static final Seller AMY_SELLER = new SellerBuilder().withName(VALID_NAME_AMY)
            .withHousingType(VALID_HOUSING_TYPE_AMY).withEmail(VALID_EMAIL_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withTags(VALID_TAG_FRIEND).withHouses(HOUSE2).build();

    public static final Seller BOB_SELLER = new SellerBuilder().withName(VALID_NAME_BOB)
            .withHousingType(VALID_HOUSING_TYPE_BOB).withEmail(VALID_EMAIL_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withHouses(HOUSE3).build();

    public static final Person ALI = new BuyerBuilder().withName("Ali York")
            .withPhone("82937163").withEmail("ali@gmail.com").withHousingType("HDB").withTags("friends").build();
    public static final Person BEN = new BuyerBuilder().withName(VALID_NAME_BEN).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withHousingType(VALID_HOUSING_TYPE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();
    // Manually added - Person's details found in {@code CommandTestUtil}

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253").withHousingType(VALID_HOUSING_TYPE_AMY)
            .withTags("friends").build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withHousingType(VALID_HOUSING_TYPE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();




    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE_SELLER, BENSON_SELLER,
                CARL_SELLER, DANIEL_SELLER, ELLE_SELLER, FIONA_SELLER, GEORGE_SELLER));
    }
}
