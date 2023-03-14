
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

public class AddTitleController implements Initializable {

    @FXML
    Button cancelBtn;
    final ObservableList<String> data = FXCollections.observableArrayList();

    //Defining textfields for the UI
    @FXML TextField id;
    @FXML TextField name;
    @FXML TextField  isbn;
    @FXML TextField  edition;
    @FXML TextField  pubDate;
    @FXML TextField  pubQuant;

    //Combobox ref
    @FXML ComboBox author;

    //Making variables to store the adapters
    private AuthorsAdapter authorAdapter;
    private TitlesAdapter titleAdapter;

    //This will set up the combobox and define the adapters
    //This is called in the maincontroller upon creation of the add title window
    public void setReset(TitlesAdapter title,AuthorsAdapter author) {

        authorAdapter = author;
        titleAdapter = title;

        buildComboBoxData();

    }

    //Defines exit button behavior
    public void exit() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    //This defines behavior when update is pressed
    //Adds a new title to the database with the information in the fields and combobox
    public void addTitle() throws SQLException {
        String autString = (String) author.getValue();
        titleAdapter.insertTitle(id.getText(),name.getText(),isbn.getText(),edition.getText(),pubDate.getText(),pubQuant.getText(),autString);
    }

    //This gets all the current author names and will add them to an arraylist
    public void buildComboBoxData() {
        try {

            data.addAll(authorAdapter.getAuthorIDsList());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    private void displayAlert(String s) {
    }

    //Init method to set combobox items to the arraylist
    public void initialize(URL url, ResourceBundle rb) {

        author.setItems(data);


    }



}
