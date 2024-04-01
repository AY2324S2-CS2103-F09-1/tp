package seedu.address.model.house;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.house.exceptions.DuplicateHouseException;
import seedu.address.model.house.exceptions.HouseNotFoundException;


/**
 * A list of houses that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code House#isSamePerson(House)}. As such, adding and updating of
 * persons uses House#isSamePerson(House) for equality so as to ensure that the house being added or updated is
 * unique in terms of identity in the UniqueHouseList. However, the removal of a house uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueHouseList implements Iterable<House> {

    private final ObservableList<House> internalList = FXCollections.observableArrayList();
    private final ObservableList<House> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(House toCheck) {
        requireNonNull(toCheck);
        if (toCheck instanceof Landed) {
            return internalList.stream().anyMatch(toCheck::equals);
        } else {
            for (House h: internalList) {
                int index1 = toCheck.toString().indexOf("Street");
                int index2 = h.toString().indexOf("Street");
                int lastIndex1 = toCheck.toString().indexOf("price", index1);
                int lastIndex2 = h.toString().indexOf("price", index2);

                if (index1 == -1 || index2 == -1 || lastIndex1 == -1 || lastIndex2 == -1) {
                    throw new AssertionError("Invalid house");
                }

                String substr1 = toCheck.toString().substring(index1 + "Street".length(), lastIndex1);
                String substr2 = h.toString().substring(index2 + "Street".length(), lastIndex2);
                if (substr1.equals(substr2)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Adds a house to the list.
     * The house must not already exist in the list.
     */
    public void add(House toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateHouseException();
        }
        internalList.add(toAdd);
    }

    /* WE KEEP THIS HERE IN CASE GOT EDITHOUSE YA
    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.

    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }
    */



    /**
     * Removes the equivalent house from the list.
     * The house must exist in the list.
     */
    public void remove(House toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new HouseNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code houses}.
     * {@code houses} must not contain duplicate houses.
     */
    public void setHouses(List<House> houses) {
        requireAllNonNull(houses);
        if (!housesAreUnique(houses)) {
            throw new DuplicateHouseException();
        }

        internalList.setAll(houses);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<House> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<House> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueHouseList)) {
            return false;
        }

        UniqueHouseList otherUniqueHouseList = (UniqueHouseList) other;
        return internalList.equals(otherUniqueHouseList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code houses} contains only unique houses.
     */
    private boolean housesAreUnique(List<House> houses) {
        for (int i = 0; i < houses.size() - 1; i++) {
            for (int j = i + 1; j < houses.size(); j++) {
                if (houses.get(i).equals(houses.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
