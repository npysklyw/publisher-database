package iPublisher;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Abdelkader
 */
public class MainController implements Initializable {

    @FXML
    private MenuBar mainMenu;
    private Connection conn;

    //Variables we will use to point to adapters
    private PublisherUnitAdapter publishers;
    private AuthorsAdapter author;
    private TitlesAdapter titles;

    public void showAbout() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Parent About = (Parent) fxmlLoader.load();

        Scene scene = new Scene(About);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showAddNewPublisher() throws Exception {

        //Set the adapter variables to the relevant adapter
        publishers = new PublisherUnitAdapter(conn, false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPublisher.fxml"));
        Parent AddPub = (Parent) fxmlLoader.load();

        //Get the controller from the fxml file used for this window
        //We use this to call the setReset method to point the adapter variables in the controller to the ones we have created here
        AddPublisherController addPubControl = (AddPublisherController) fxmlLoader.getController();
        addPubControl.setModel(publishers);

        Scene scene = new Scene(AddPub);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Add Publisher");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showAddNewAuthor() throws Exception {

        //Set the adapter variables to the relevant adapters
        publishers = new PublisherUnitAdapter(conn, false);
        author = new AuthorsAdapter(conn,false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddAuthor.fxml"));
        Parent AddAut = (Parent) fxmlLoader.load();

        //Get the controller from the fxml file used for this window
        //We use this to call the setReset method to point the adapter variables in the controller to the ones we have created here
        AddAuthorController authorControl = (AddAuthorController) fxmlLoader.getController();
        authorControl.setReset(author,publishers);

        Scene scene = new Scene(AddAut);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Add Author");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showAddNewTitle() throws Exception {

        //Set the adapter variables to the relevant adapters
        titles = new TitlesAdapter(conn, false);
        author = new AuthorsAdapter(conn,false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddTitle.fxml"));
        Parent AddTitle = (Parent) fxmlLoader.load();

        //Get the controller from the fxml file used for this window
        //We use this to call the setReset method to point the adapter variables in the controller to the ones we have created here
        AddTitleController addTitleControl = (AddTitleController) fxmlLoader.getController();
        addTitleControl.setReset(titles,author);

        Scene scene = new Scene(AddTitle);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Add Title");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showEditPublisher() throws Exception {

        //Set the adapter variables to the relevant adapter
        publishers = new PublisherUnitAdapter(conn, false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditPublisher.fxml"));
        Parent EditPub = (Parent) fxmlLoader.load();

        //Get the controller from the fxml file used for this window
        //We use this to call the setReset method to point the adapter variables in the controller to the ones we have created here
        EditPublisherController editPublisherControl = (EditPublisherController) fxmlLoader.getController();
        editPublisherControl.setModel(publishers);

        Scene scene = new Scene(EditPub);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Edit/Remove Publisher");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showEditAuthor() throws Exception {

        //Set the adapter variables to the relevant adapters
        publishers = new PublisherUnitAdapter(conn, false);
        author = new AuthorsAdapter(conn,false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditAuthor.fxml"));
        Parent EditAut = (Parent) fxmlLoader.load();

        //Get the controller from the fxml file used for this window
        //We use this to call the setReset method to point the adapter variables in the controller to the ones we have created here
        EditAuthorController editAuthorControl = (EditAuthorController) fxmlLoader.getController();
        editAuthorControl.setReset(author,publishers);

        Scene scene = new Scene(EditAut);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Edit/Remove Author");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showEditTitle() throws Exception {

        //Set the adapter variables to the relevant adapters
        titles = new TitlesAdapter(conn, false);
        author = new AuthorsAdapter(conn,false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditTitle.fxml"));
        Parent EditTitle = (Parent) fxmlLoader.load();

        //Get the controller from the fxml file used for this window
        //We use this to call the setReset method to point the adapter variables in the controller to the ones we have created here
        EditTitleController titleControl = (EditTitleController) fxmlLoader.getController();
        titleControl.setReset(author,titles);

        Scene scene = new Scene(EditTitle);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/iPublisher/WesternLogo.png"));
        stage.setTitle("Edit/Remove Title");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    //Init method
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:iPublisherDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);

            //I create an instance of an adapter for publisher, authors, and titles so we can communicate with the DB
            publishers = new PublisherUnitAdapter(conn, true);
            author = new AuthorsAdapter(conn, true);
            titles = new TitlesAdapter(conn, true);

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }

    }


    private void displayAlert(String message) {
    }

    public void exit() {

        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }


}

