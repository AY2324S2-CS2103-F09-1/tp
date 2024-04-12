package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.house.House;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of EstateEase
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();
    ObservableList<House> getHouseList();

}
