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


public class EditTitleController implements Initializable {

    //Refs the exit button
    @FXML Button cancelBtn;

    //Refs the title combobox
    @FXML ComboBox titleBox;

    //Refs to the UI textfields, adn the author combobox
    @FXML TextField id;
    @FXML TextField name;
    @FXML TextField  isbn;
    @FXML TextField  edition;
    @FXML TextField  pubDate;
    @FXML TextField  pubQuant;
    @FXML ComboBox author;

    //These lists populate the comboboxes
    final ObservableList<String> data = FXCollections.observableArrayList();
    final ObservableList<String> dataTwo = FXCollections.observableArrayList();

    //Variables to point to the various adapters we use
    private AuthorsAdapter authorAdapter;
    private TitlesAdapter titleAdapter;

    //This will populate the comboboxes and point the adapters to adapters we create in the main controller
    //This method is called in the main controller
    public void setReset(AuthorsAdapter author,TitlesAdapter title) {

        authorAdapter = author;
        titleAdapter = title;

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

        String tileName = (String) titleBox.getValue();
        String auth = (String) author.getValue();

        titleAdapter.setTitleDetail(id.getText(),name.getText(),isbn.getText(),edition.getText(),pubDate.getText(),pubQuant.getText(),auth,tileName);

        titleBox.setItems(titleAdapter.getTitleIDsList());
        clear();



    }

    //This method will add all the necessary data to each arraylist we defined earlier
    public void buildComboBoxData() {
        try {

            data.addAll(titleAdapter.getTitleIDsList());
            dataTwo.addAll(authorAdapter.getAuthorIDsList());

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    private void displayAlert(String s) {
    }

    //This method defines behavior when the combobox option is changed
    //The textfield data will be switched to the current database stored value of the title combobox instance
    public void change() throws SQLException {


        ObservableList<Titles> titleList = titleAdapter.getTitlesList();
        String titleID = (String) titleBox.getValue();
        if (titleID == null) {

        }
        else {
            Titles t = null;
            for (Titles titles: titleList
            ) {

                if (titles.getTitleID().equals(titleID)) {
                    t = titles;
                }
            }
            id.setText(t.getTitleID());
            name.setText(t.getName());
            isbn.setText(t.getIsbn());
            edition.setText(t.getEditionNo());
            pubDate.setText(t.getPubDate());
            pubQuant.setText(t.getPubQTY());
            author.setValue(t.getAuthor());
        }

    }

    //Init method, this will set the combobox data to the arraylist
    public void initialize(URL url, ResourceBundle rb) {

        titleBox.setItems(data);
        author.setItems(dataTwo);

    }

    //This method defines behavior for the delete button
    //The current title combobox item will be sent to the adapter which will delete this row
    public void delete() throws SQLException {
        String titleID = (String) titleBox.getValue();
        titleAdapter.removeTitle(titleID);
        titleBox.setItems(titleAdapter.getTitleIDsList());
        clear();

    }

    //Simply will set all combobox and textfields to empty values
    public void clear() throws SQLException {
        titleBox.setValue(null);
        id.setText("");
        name.setText("");
        isbn.setText("");
        edition.setText("");
        pubQuant.setText("");
        pubDate.setText("");

        author.setValue(null);


    }
}