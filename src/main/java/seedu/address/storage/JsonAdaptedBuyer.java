package seedu.address.storage;

import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Person;



/**
 * Jackson-friendly version of {@link Buyer}.
 */
public class JsonAdaptedBuyer extends JsonAdaptedPerson {


    /**
     * Constructs a {@code JsonAdaptedSeller}, extends from JsonAdaptedHouse.
     */
    @JsonCreator
    public JsonAdaptedBuyer(@JsonProperty("name") String name,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("housingType") String housingType,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        super(name, phone, email, housingType, tags);
    }

    /**
     * Converts a given {@code JsonAdaptedBuyer} into this class for Jackson use.
     */
    public JsonAdaptedBuyer(Buyer source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted seller object into the model's {@code Buyer} object.
     */
    @Override
    public Buyer toModelType() throws IllegalValueException {
        Person person = super.toModelType();
        return new Buyer(person.getName(), person.getPhone(), person.getEmail(),
                person.getHousingType(), new HashSet<>(person.getTags()));
    }
}
