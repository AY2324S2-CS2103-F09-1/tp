package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Buyer;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;


/**
 * A Utility class to help with building Buyer objects.
 */
public class BuyerBuilder {
    public static final String DEFAULT_NAME = "James Lim";
    public static final String DEFAULT_PHONE = "93840172";
    public static final String DEFAULT_EMAIL = "james@gmail.com";
    public static final String DEFAULT_HOUSING_TYPE = "HDB";

    private Name name;
    private Phone phone;
    private Email email;
    private String housingType;
    private Set<Tag> tags;

    /**
     * Creates a {@code BuyerBuilder} with the default information.
     */
    public BuyerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        housingType = DEFAULT_HOUSING_TYPE;
        tags = new HashSet<>();
    }

    /**
     * Initializes the BuyerBuilder with the data of {@code personToCopy}
     */
    public BuyerBuilder(Person personToCopy) { //TODO: need to figure this out if need Person or Buyer only)
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        housingType = personToCopy.getHousingType();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public BuyerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public BuyerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public BuyerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public BuyerBuilder withHousingType(String housingType) {
        this.housingType = housingType;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public BuyerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Person build() {
        return new Buyer(name, phone, email, housingType, tags);
    }
}
