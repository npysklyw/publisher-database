package iPublisher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class EditAuthorController implements Initializable {

    //Refs the exit button
    @FXML Button cancelBtn;

    //Refs the author combobox
    @FXML ComboBox autBox;

    //Refs to the UI textfields, adn the publishing unit combobox
    @FXML TextField id;
    @FXML TextField name;
    @FXML TextField address;
    @FXML ComboBox unit;

    //These lists populate the comboboxes
    final ObservableList<String> data = FXCollections.observableArrayList();
    final ObservableList<String> dataTwo = FXCollections.observableArrayList();

    //Variables to point to the various adapters we use
    private AuthorsAdapter authorAdapter;
    private PublisherUnitAdapter publisherAdapter;

    //This will populate the comboboxes and point the adapters to adapters we create in the main controller
    //This method is called in the main controller
    public void setReset(AuthorsAdapter author,PublisherUnitAdapter publisher) {

        authorAdapter = author;
        publisherAdapter = publisher;

        buildComboBoxData();

    }

    //Defines the exit behavior
    public void exit() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    //Method is used for defining update button behavior
    //The method will send the current textfield and combobox data to the adapter which will update the database with these values
    //Then the textfields and comboboxes will all be reset
    public void update() throws SQLException {

        String pubName = (String) autBox.getValue();

        authorAdapter.setAutDetail(id.getText(),name.getText(),address.getText(),(String) unit.getValue(),pubName);

        autBox.setItems(authorAdapter.getAuthorIDsList());
        clear();



    }

    //This method will add all the necessary data to each arraylist we defined earlier
    public void buildComboBoxData() {
        try {

            data.addAll(authorAdapter.getAuthorIDsList());
            dataTwo.addAll(publisherAdapter.getPublishingNamesList());

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    private void displayAlert(String s) {
    }

    //This method defines behavior when the combobox option is changed
    //The textfield data will be switched to the current database stored value of the author combobox instance
    public void change() throws SQLException {


        ObservableList<Authors> autList = authorAdapter.getAuthorsList();
        String autID = (String) autBox.getValue();
        if (autID == null) {

        }
        else {
            Authors a = null;
            for (Authors aut: autList
            ) {

                if (aut.getAuthorID().equals(autID)) {
                    a = aut;
                }
            }
            id.setText(a.getAuthorID());
            name.setText(a.getAuthorName());
            address.setText(a.getAddress());
            unit.setValue(a.getPublishingUnit());
        }

    }

    //Init method, this will set the combobox data to the arraylist
    public void initialize(URL url, ResourceBundle rb) {

        autBox.setItems(data);
        unit.setItems(dataTwo);

    }

    //This method defines behavior for the delete button
    //The current author combobox item will be sent to the adapter which will delete this row
    public void delete() throws SQLException {
        String autID = (String)autBox.getValue();
        authorAdapter.removeAuthor(autID);
        autBox.setItems(authorAdapter.getAuthorIDsList());
        clear();

    }

    //Simply will set all combobox and textfields to empty values
    public void clear() throws SQLException {
        autBox.setValue(null);
        id.setText("");
        name.setText("");
        address.setText("");
        unit.setValue(null);


    }
}