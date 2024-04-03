package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import seedu.address.model.State;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;

/**
 * API of UI component
 */
public interface Ui {

    /** Starts the UI (and the App).  */
    void start(Stage primaryStage);

    /** Displays Person details */
    void showPersonDetails(Person person);

    /** Displays list of filtered sellers */
    void showMatchResults(ObservableList<Seller> sellers);

    /** Updates the ui layout */
    void updateUiLayout(State newState);

}
