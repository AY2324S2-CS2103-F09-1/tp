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
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALI;
import static seedu.address.testutil.TypicalPersons.BEN;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BuyerBuilder;

public class BuyerTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person buyer = new BuyerBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> buyer.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALI.isSamePerson(ALI));

        // null -> returns false
        assertFalse(ALI.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAli = new BuyerBuilder(ALI).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withHousingType(VALID_HOUSING_TYPE_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALI.isSamePerson(editedAli));

        // different name, all other attributes same -> returns false
        editedAli = new BuyerBuilder(ALI).withName(VALID_NAME_BEN).build();
        assertFalse(ALI.isSamePerson(editedAli));

        // name differs in case, all other attributes same -> returns false
        Person editedBen = new BuyerBuilder(BEN).withName(VALID_NAME_BEN.toLowerCase()).build();
        assertFalse(BEN.isSamePerson(editedBen));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BEN + " ";
        editedBen = new BuyerBuilder(BEN).withName(nameWithTrailingSpaces).build();
        assertFalse(BEN.isSamePerson(editedBen));
    }

    @Test
    public void equals() {

        Person buyerAliCopy = new BuyerBuilder(ALI).build();

        // same object -> returns true
        assertTrue(ALI.equals(buyerAliCopy));

        // same object -> returns true
        assertTrue(ALI.equals(ALI));

        // null -> returns false
        assertFalse(ALI.equals(null));

        // different type -> returns false
        assertFalse(ALI.equals(5));

        // different person -> returns false
        assertFalse(ALI.equals(BOB));

        // different name -> returns false
        Person editedAli = new BuyerBuilder(ALI).withName(VALID_NAME_BOB).build();
        assertFalse(ALI.equals(editedAli));

        // different phone -> returns false
        editedAli = new BuyerBuilder(ALI).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALI.equals(editedAli));

        // different email -> returns false
        editedAli = new BuyerBuilder(ALI).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALI.equals(editedAli));

        // different housingtype -> returns false
        editedAli = new BuyerBuilder(ALI).withHousingType(VALID_HOUSING_TYPE_BOB).build();
        assertFalse(ALI.equals(editedAli));

        // different tags -> returns false
        editedAli = new BuyerBuilder(ALI).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALI.equals(editedAli));
    }

    @Test
    public void toStringMethod() {
        String expected = Buyer.class.getCanonicalName() + "{name=" + ALI.getName() + ", phone=" + ALI.getPhone()
                + ", email=" + ALI.getEmail() + ", housing type=" + ALI.getHousingType()
                + ", tags=" + ALI.getTags() + "}";
        assertEquals(expected, ALI.toString());
    }
}
