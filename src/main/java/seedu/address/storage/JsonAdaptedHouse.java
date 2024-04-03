package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.house.Block;
import seedu.address.model.house.Condominium;
import seedu.address.model.house.Hdb;
import seedu.address.model.house.House;
import seedu.address.model.house.Landed;
import seedu.address.model.house.Level;
import seedu.address.model.house.PostalCode;
import seedu.address.model.house.Price;
import seedu.address.model.house.Street;
import seedu.address.model.house.UnitNumber;

/**
 * Jackson-friendly version of {@link House}.
 */
public class JsonAdaptedHouse {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "House's %s field is missing!";
    private String housingType;
    private String block;
    private String level;
    private final String postalCode;
    private final String street;
    private final String unitNumber;
    private final String price;

    /**
     * Constructs a {@code JsonAdaptedHouse} with the given house details.
     */
    @JsonCreator
    public JsonAdaptedHouse(@JsonProperty("housingType") String housingType,
                            @JsonProperty("block") String block,
                            @JsonProperty("level") String level,
                            @JsonProperty("postalCode") String postalCode,
                            @JsonProperty("street") String street,
                            @JsonProperty("unitNumber") String unitNumber,
                            @JsonProperty("price") String price) {
        this.housingType = housingType;
        this.block = block;
        this.level = level;
        this.postalCode = postalCode;
        this.street = street;
        this.unitNumber = unitNumber;
        this.price = price;
    }

    /**
     * Converts a given {@code House} into this class for Jackson use.
     */
    public JsonAdaptedHouse(House source) {
        extractHouseDetails(source);
        this.postalCode = source.getPostalCode().value;
        this.street = source.getStreet().value;
        this.unitNumber = source.getUnitNumber().value;
        this.price = source.getPrice().value;
    }

    private void extractHouseDetails(House source) {
        if (source instanceof Condominium) {
            extractCondominiumDetails((Condominium) source);
        } else if (source instanceof Hdb) {
            extractHdbDetails((Hdb) source);
        } else {
            extractDefaultHouseDetails();
        }
    }

    private void extractCondominiumDetails(Condominium condominium) {
        if (condominium.getBlock() != null) {
            this.block = condominium.getBlock().value;
        } else {
            this.block = null;
        }
        if (condominium.getLevel() != null) {
            this.level = condominium.getLevel().value;
        } else {
            this.level = null;
        }
        this.housingType = "Condominium";
    }

    private void extractHdbDetails(Hdb hdb) {
        if (hdb.getBlock() != null) {
            this.block = hdb.getBlock().value;
        } else {
            this.block = null;
        }
        if (hdb.getLevel() != null) {
            this.level = hdb.getLevel().value;
        } else {
            this.level = null;
        }
        this.housingType = "Hdb";
    }

    private void extractDefaultHouseDetails() {
        this.block = null;
        this.level = null;
        this.housingType = "Landed";
    }

    /**
     * Converts this Jackson-friendly adapted house object into the model's {@code House} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted house.
     */
    public House toModelType() throws IllegalValueException {
        if (postalCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PostalCode.class.getSimpleName()));
        }
        if (!PostalCode.isValidPostalCode(postalCode)) {
            throw new IllegalValueException(PostalCode.MESSAGE_CONSTRAINTS);
        }
        final PostalCode modelPostalCode = new PostalCode(postalCode);

        if (street == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Street.class.getSimpleName()));
        }
        if (!Street.isValidStreet(street)) {
            throw new IllegalValueException(Street.MESSAGE_CONSTRAINTS);
        }
        final Street modelStreet = new Street(street);

        if (unitNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UnitNumber.class.getSimpleName()));
        }
        if (!UnitNumber.isValidUnitNumber(unitNumber)) {
            throw new IllegalValueException(UnitNumber.MESSAGE_CONSTRAINTS);
        }
        final UnitNumber modelUnitNumber = new UnitNumber(unitNumber);

        if (price == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(price);

        Block modelBlock = null;
        Level modelLevel = null;

        if (block != null) {
            modelBlock = new Block(block);
        }
        if (level != null) {
            modelLevel = new Level(level);
        }

        switch (housingType.toLowerCase()) {
        case "condominium":
            if (modelBlock != null) {
                return new Condominium(modelLevel, modelPostalCode, modelStreet, modelUnitNumber,
                        modelBlock, modelPrice);
            } else {
                return new Condominium(modelLevel, modelPostalCode, modelStreet, modelUnitNumber, modelPrice);
            }
        case "hdb":
            return new Hdb(modelLevel, modelPostalCode, modelStreet, modelUnitNumber, modelBlock, modelPrice);
        case "landed":
            return new Landed(modelUnitNumber, modelPostalCode, modelStreet, modelPrice);
        default:
            throw new IllegalValueException("Unknown House Type");
        }
    }
}
