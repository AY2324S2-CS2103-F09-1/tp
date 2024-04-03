package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Seller;


/**
 * Panel containing the list of match results.
 */
public class MatchResultListPanel extends UiPart<Region> {
    private static final String FXML = "MatchResultListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MatchResultListPanel.class);

    @FXML
    private ListView<Seller> matchResultListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public MatchResultListPanel() {
        super(FXML);
    }

    public void setMatchResults(ObservableList<Seller> filteredSellerList) {
        matchResultListView.setItems(filteredSellerList);
        matchResultListView.setCellFactory(listView -> new MatchResultListPanel.MatchResultListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Seller} using a {@code MatchResultCard}.
     */
    class MatchResultListViewCell extends ListCell<Seller> {
        @Override
        protected void updateItem(Seller seller, boolean empty) {
            super.updateItem(seller, empty);

            if (empty || seller == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MatchResultCard(seller, getIndex() + 1).getRoot());
            }
        }
    }

}
