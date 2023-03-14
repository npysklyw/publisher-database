
package iPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class AddAuthorController implements Initializable {


    @FXML Button cancelBtn;
    final ObservableList<String> data = FXCollections.observableArrayList();


    //Defining textfields for the UI
    @FXML TextField id;
    @FXML TextField name;
    @FXML TextField  address;
    @FXML ComboBox unit;

    //Making variables to store the adapters
    private AuthorsAdapter authorAdapter;
    private PublisherUnitAdapter publisherAdapter;

    //This will set up the combobox and define the adapters
    //This is called in the maincontroller upon creation of the add author window
    public void setReset(AuthorsAdapter author,PublisherUnitAdapter publisher) {

        authorAdapter = author;
        publisherAdapter = publisher;

        buildComboBoxData();

    }

    //Defines exit button behavior
    public void exit() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    //This defines behavior when update is pressed
    //Adds a new author to the database with the information in the fields
    public void addAuthor() throws SQLException {
        String pUnit = (String) unit.getValue();
        authorAdapter.insertAuthor(id.getText(),name.getText(),address.getText(),pUnit);
    }

    //This gets all the current publishingunit names and will add them to an arraylist
    public void buildComboBoxData() {
        try {

            data.addAll(publisherAdapter.getPublishingNamesList());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    private void displayAlert(String s) {

    }

    //Init method to set combobox items to the arraylist
    public void initialize(URL url, ResourceBundle rb) {

        unit.setItems(data);


    }



}
