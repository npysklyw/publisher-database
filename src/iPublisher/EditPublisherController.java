
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


public class EditPublisherController  implements Initializable {

    //Refs the exit button
    @FXML Button cancelBtn;

    //Refs the publisher combobox
    @FXML ComboBox pubBox;

    //Refs to the UI textfields
    @FXML TextField name;
    @FXML TextField address;
    @FXML TextField region;

    //This list will be used to populate the combo boxes
    final ObservableList<String> data = FXCollections.observableArrayList();

    //Variables to point to the publisher adapter we use
    private PublisherUnitAdapter publisherAdapter;

    //This will populate the combobox and point the adapter to the adapter we create in the main controller
    //This method is called in the main controller
    public void setModel(PublisherUnitAdapter publisher) {

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

        String pubName = (String)pubBox.getValue();

        publisherAdapter.setPubDetail(name.getText(),address.getText(),region.getText(),pubName);

        pubBox.setItems(publisherAdapter.getPublishingNamesList());
        clear();

    }

    //This method will add all the necessary data to each arraylist we defined earlier
    public void buildComboBoxData() {
        try {

            data.addAll(publisherAdapter.getPublishingNamesList());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    private void displayAlert(String s) {
    }

    //This method defines behavior when the combobox option is changed
    //The textfield data will be switched to the current database stored value of the author combobox instance
    public void change() throws SQLException {

        ObservableList<PublisherUnit> pubList = publisherAdapter.getPublisherUnitsList();
        String pubName = (String)pubBox.getValue();
        if (pubName == null) {

        }
        else {
            PublisherUnit p = null;
            for (PublisherUnit pub: pubList
            ) {
                System.out.println(pub.getName());
                if (pub.getName().equals(pubName)) {
                    p = pub;
                }
            }
            name.setText(p.getName());
            address.setText(p.getAddress());
            region.setText(p.getRegion());
        }

    }

    //Init method, this will set the combobox data to the arraylist
    public void initialize(URL url, ResourceBundle rb) {

        pubBox.setItems(data);
        System.out.println("Dasdas");

    }

    //This method defines behavior for the delete button
    //The current publisher combobox item will be sent to the adapter which will delete this row
    public void delete() throws SQLException {
        String pubName = (String)pubBox.getValue();
        publisherAdapter.removePublishingUnit(pubName);
        pubBox.setItems(publisherAdapter.getPublishingNamesList());
        clear();

    }

    //Simply will set all combobox and textfields to empty values
    public void clear() throws SQLException {
        pubBox.setValue(null);
        name.setText("");
        address.setText("");
        region.setText("");


    }
}
