package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    private Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label preferredHousingType;
    @FXML
    private Label budget;
    @FXML
    private Label email;
    @FXML
    private Label postalCode;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox housesContainer;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label tagLabel = new Label(tag.tagName);
                    tagLabel.getStyleClass().add("label");
                    if ("Seller".equals(tag.tagName)) {
                        tagLabel.getStyleClass().add("tag-seller");
                        // TODO(UI): Change background colour value for buyer and seller
                        cardPane.setStyle("-fx-background-color: #511bb5;");
                    } else if ("Buyer".equals(tag.tagName)) {
                        tagLabel.getStyleClass().add("tag-buyer");
                        // TODO(UI): Change background colour value for buyer and seller
                        cardPane.setStyle("-fx-background-color: #2b5d79;");
                    }
                    tags.getChildren().add(tagLabel);
                });
    }
}
