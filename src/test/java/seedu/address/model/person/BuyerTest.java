package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUSING_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalPersons.ALI_BUYER;
import static seedu.address.testutil.TypicalPersons.BEN_BUYER;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BuyerBuilder;

public class BuyerTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Buyer buyer = new BuyerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> buyer.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALI_BUYER.isSamePerson(ALI_BUYER));

        // null -> returns false
        assertFalse(ALI_BUYER.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAli = new BuyerBuilder(ALI_BUYER).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withHousingType(VALID_HOUSING_TYPE_BOB).build();
        assertTrue(ALI_BUYER.isSamePerson(editedAli));

        // different name, all other attributes same -> returns false
        editedAli = new BuyerBuilder(ALI_BUYER).withName(VALID_NAME_BEN).build();
        assertFalse(ALI_BUYER.isSamePerson(editedAli));

        // name differs in case, all other attributes same -> returns true
        Person editedBen = new BuyerBuilder(BEN_BUYER).withName(VALID_NAME_BEN.toLowerCase()).build();
        assertTrue(BEN_BUYER.isSamePerson(editedBen));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BEN + " ";
        editedBen = new BuyerBuilder(BEN_BUYER).withName(nameWithTrailingSpaces).build();
        assertFalse(BEN_BUYER.isSamePerson(editedBen));
    }

    @Test
    public void equals() {

        Person buyerAliCopy = new BuyerBuilder(ALI_BUYER).build();

        // same object -> returns true
        assertTrue(ALI_BUYER.equals(buyerAliCopy));

        // same object -> returns true
        assertTrue(ALI_BUYER.equals(ALI_BUYER));

        // null -> returns false
        assertFalse(ALI_BUYER.equals(null));

        // different type -> returns false
        assertFalse(ALI_BUYER.equals(5));

        // different person -> returns false
        assertFalse(ALI_BUYER.equals(BOB));

        // different name -> returns false
        Person editedAli = new BuyerBuilder(ALI_BUYER).withName(VALID_NAME_BOB).build();
        assertFalse(ALI_BUYER.equals(editedAli));

        // different phone -> returns false
        editedAli = new BuyerBuilder(ALI_BUYER).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALI_BUYER.equals(editedAli));

        // different email -> returns false
        editedAli = new BuyerBuilder(ALI_BUYER).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALI_BUYER.equals(editedAli));

        // different housingtype -> returns false
        editedAli = new BuyerBuilder(ALI_BUYER).withHousingType(VALID_HOUSING_TYPE_BOB).build();
        assertFalse(ALI_BUYER.equals(editedAli));
    }

    @Test
    public void toStringMethod() {
        String expected = Buyer.class.getCanonicalName() + "{name="
                + ALI_BUYER.getName() + ", phone=" + ALI_BUYER.getPhone()
                + ", email=" + ALI_BUYER.getEmail() + ", housingType=" + ALI_BUYER.getPreferredHousingType()
                + ", budget=" + ALI_BUYER.getBudget()
                + ", tags=" + ALI_BUYER.getTags() + "}";
        assertEquals(expected, ALI_BUYER.toString());
    }
}
