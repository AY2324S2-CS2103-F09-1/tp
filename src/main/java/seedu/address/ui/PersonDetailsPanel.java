package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;


/**
 * Panel containing the details of the selected person.
 */
public class PersonDetailsPanel extends UiPart<Region> {
    private static final String FXML = "PersonDetailsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonDetailsPanel.class);

    private Person displayedPerson;

    private HouseListPanel houseListPanel;

    @FXML
    private VBox personDetailsPane;
    @FXML
    private Label name;
    //    @FXML
    // private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label housingType;
    @FXML
    private Label budget;
    @FXML
    private FlowPane tags;

    @FXML
    private VBox houseList;
    @FXML
    private StackPane houseListPanelPlaceholder;
    /**
     * Creates a {@code PersonDetailsPanel} with the given {@code ObservableList}.
     */
    public PersonDetailsPanel() {
        super(FXML);
    }

    public void setPersonDetails(Person person) {
        this.displayedPerson = person;
        name.setText(displayedPerson.getName().fullName);
        phone.setText("Phone: " + displayedPerson.getPhone().value);
        email.setText("Email: " + displayedPerson.getEmail().value);

        if (displayedPerson instanceof Buyer) {
            budget.setVisible(true);
            housingType.setVisible(true);
            houseList.setVisible(false);
            Buyer buyer = (Buyer) displayedPerson;
            housingType.setText("Housing Type Requirement: " + buyer.getPreferredHousingType().value);
            budget.setText("Budget: $" + buyer.getBudget().toString());
        } else if (displayedPerson instanceof Seller) {
            housingType.setVisible(false);
            budget.setVisible(false);
            houseList.setVisible(true);
            Seller seller = (Seller) displayedPerson;
            houseListPanel = new HouseListPanel(seller.getHouses());
            houseListPanelPlaceholder.getChildren().add(houseListPanel.getRoot());
        }
    }

}




