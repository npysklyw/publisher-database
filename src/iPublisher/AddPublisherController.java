
package iPublisher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddPublisherController  {

    @FXML Button cancelBtn;

    //Defining textfields for the UI
    @FXML TextField name;
    @FXML TextField address;
    @FXML TextField region;

    //Making variables to store the publisher adapter
    private PublisherUnitAdapter publisherAdapter;

    //This will define the adapters
    //This is called in the maincontroller upon creation of the add publisher window
    public void setModel(PublisherUnitAdapter publisher) {

        publisherAdapter = publisher;

    }

    //Defines exit button behavior
    public void exit() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    //This defines behavior when update is pressed
    //Adds a new publisher to the database with the information in the fields
    public void addPublisher() throws SQLException {

        publisherAdapter.insertPublishingUnit(name.getText(),address.getText(),region.getText());
    }

}
