package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUSING_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalHouses.HOUSE2;
import static seedu.address.testutil.TypicalHouses.HOUSE3;
import static seedu.address.testutil.TypicalPersons.ALICE_SELLER;
import static seedu.address.testutil.TypicalPersons.BOB_SELLER;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.house.House;
import seedu.address.testutil.SellerBuilder;

public class SellerTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Seller seller = new SellerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> seller.getTags().remove(0));
    }

    @Test
    public void isSameSeller() {
        // same object -> returns true
        assertTrue(ALICE_SELLER.isSamePerson(ALICE_SELLER));

        // null -> returns false
        assertFalse(ALICE_SELLER.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new SellerBuilder(ALICE_SELLER).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withHousingType(VALID_HOUSING_TYPE_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE_SELLER.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new SellerBuilder(ALICE_SELLER).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE_SELLER.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new SellerBuilder(BOB_SELLER).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB_SELLER.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new SellerBuilder(BOB_SELLER).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB_SELLER.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // Initialize with houses for ALICE and BOB
        ArrayList<House> aliceHouses = new ArrayList<>(Arrays.asList(HOUSE3));
        ArrayList<House> bobHouses = new ArrayList<>(Arrays.asList(HOUSE2));

        // Create new Seller instances with houses
        Seller sellerAlice = new Seller(ALICE_SELLER.getName(), ALICE_SELLER.getPhone(), ALICE_SELLER.getEmail(),
                ALICE_SELLER.getHousingType(), aliceHouses, ALICE_SELLER.getTags());
        Seller sellerBob = new Seller(BOB_SELLER.getName(), BOB_SELLER.getPhone(), BOB_SELLER.getEmail(),
                BOB_SELLER.getHousingType(), bobHouses, BOB_SELLER.getTags());

        // same object -> returns true
        assertTrue(sellerAlice.equals(sellerAlice));

        // null -> returns false
        assertFalse(sellerAlice.equals(null));

        // different type -> returns false
        assertFalse(sellerAlice.equals(5));

        // different seller -> returns false
        assertFalse(sellerAlice.equals(sellerBob));

        // Same details, different houses -> returns false
        Seller sellerAliceCloneWithDifferentHouses = new Seller(ALICE_SELLER.getName(), ALICE_SELLER.getPhone(),
                ALICE_SELLER.getEmail(), ALICE_SELLER.getHousingType(), bobHouses, ALICE_SELLER.getTags());
        assertFalse(sellerAlice.getHouses().get(0).toString()
                .equals(sellerAliceCloneWithDifferentHouses.getHouses().get(0).toString()));

        // Same details, same houses -> returns true
        Seller sellerAliceClone = new Seller(ALICE_SELLER.getName(), ALICE_SELLER.getPhone(), ALICE_SELLER.getEmail(),
                ALICE_SELLER.getHousingType(), aliceHouses, ALICE_SELLER.getTags());
        assertTrue(sellerAlice.getHouses().get(0).toString().equals(sellerAliceClone.getHouses().get(0).toString()));
    }

    @Test
    public void toStringMethod() {
        String expected = Seller.class.getCanonicalName()
                + "{name=" + ALICE_SELLER.getName() + ", phone=" + ALICE_SELLER.getPhone()
                + ", email=" + ALICE_SELLER.getEmail() + ", housingType=" + ALICE_SELLER.getHousingType()
                + ", tags=" + ALICE_SELLER.getTags() + "}";
        assertEquals(expected, ALICE_SELLER.toString());
    }
}
