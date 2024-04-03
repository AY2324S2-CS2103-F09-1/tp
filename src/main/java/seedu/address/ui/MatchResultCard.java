package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.address.model.house.House;
import seedu.address.model.person.Seller;

/**
 * An UI component that displays information of a match result.
 */
public class MatchResultCard extends UiPart<Region> {
    private static final String FXML = "MatchResultListCard.fxml";

    private Seller seller;
    private ObservableList<House> filteredHouses;

    private PersonCard sellerPanel;
    private HouseListPanel houseListPanel;
    @FXML
    private VBox sellerInfo;
    @FXML
    private StackPane sellerInfoPanelPlaceholder;
    @FXML
    private VBox houseList;
    @FXML
    private StackPane houseListPanelPlaceholder;

    /**
     * Creates a {@code MatchResultCard} with the given match result to display.
     */
    public MatchResultCard(Seller seller, int displayedIndex) {
        super(FXML);
        this.seller = seller;
        this.filteredHouses = seller.getHouses();

        sellerPanel = new PersonCard(this.seller, displayedIndex);

        houseListPanel = new HouseListPanel(this.filteredHouses);
        houseListPanelPlaceholder.getChildren().add(houseListPanel.getRoot());
    }
}
