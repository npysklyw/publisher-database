package iPublisher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Match;

public class TitlesAdapter {

    Connection connection;

    public TitlesAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;

        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                stmt.execute("DROP TABLE Titles");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {

                // Create the table of Titles
                stmt.execute("CREATE TABLE Titles ("
                        + "TitleID CHAR(15) NOT NULL PRIMARY KEY, "
                        + "Name CHAR(40), "
                        + "ISBN CHAR(30), "
                        + "EditionNumber CHAR(15), "
                        + "PublicationDate CHAR(30), "
                        + "PublicationQuantity CHAR(15), "
                        + "Author CHAR(15) "
                        + ")");

                populateSamples();


            }
        }
    }

    //Will initialize the Title table by adding in some default rows
    private void populateSamples() throws SQLException{

        this.insertTitle("43567","1984","313131313","2","January 1967","10000000","3134");


    }

    //Method that will insert a row into our table with all the parameters passed
    //We use the SQL INSERT for this
    public void insertTitle(String id, String name, String isbn, String edNum, String pubDate, String pubQuant, String aut ) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("INSERT INTO Titles (TitleID, Name, ISBN, EditionNumber, PublicationDate,PublicationQuantity,Author) "
                + "VALUES ( '" + id + "', '" + name + "', '" + isbn + "', '" + edNum + "', '" + pubDate + "', '" + pubQuant + "', '" + aut + "')");

    }

    //This method will remove the row that contains the id that is passed to the method
    //We use the SQL DELETE for this
    public void removeTitle(String id) throws SQLException {

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("DELETE FROM Titles WHERE TitleID= '" + id + "'");
    }

    //This method will return an arraylist of title objects
    //We first get the entire Titles table then iterate through the resultset adding each row to an arraylist
    //Finally we return this arraylist
    public ObservableList<Titles> getTitlesList() throws SQLException {
        ObservableList<Titles> titleList = FXCollections.observableArrayList();

        String selectCommand = "SELECT * FROM Titles";

        //Creating a statement object
        Statement stmt = connection.createStatement();

        //Then we store the result of the query from the command variable, as a resultset object
        ResultSet result = stmt.executeQuery(selectCommand);

        //We iterate through the results we obtained
        while (result.next()) {

            titleList.add(new Titles(result.getString("TitleID"), result.getString("Name"), result.getString("ISBN"), result.getString("EditionNumber"), result.getString("PublicationDate"), result.getString("PublicationQuantity"),result.getString("Author")));
        }

        return titleList;
    }

    //This method will return an arraylist of title ids
    //We first get the entire Titles table then iterate through the resultset adding each titleID to an arraylist
    //Finally we return this arraylist
    public ObservableList<String> getTitleIDsList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String command = "SELECT * FROM Titles";

        // Execute the statement and return the result, store it as a resultset
        rs= stmt.executeQuery(command);


        //We loop through the entire resultset
        while (rs.next()) {

            list.add(rs.getString("TitleID"));
        }

        return list;
    }

    //This method is used to update the Title table with new values passed to it
    //We use the SQL UPDATE command for this
    public void setTitleDetail(String id,String name, String add, String EditionNumber, String pubDate, String pubQuant, String aut, String oldID) throws SQLException
    {
        //This is the update SQL command as a string
        String update = "Update Titles SET TitleID = '" + id + "',  Name = '" + name + "',  ISBN = '" + add + "', EditionNumber= '" + EditionNumber + "', PublicationDate= '" + pubDate + "', PublicationQuantity= '" + pubQuant + "', Author= '" + aut + "' Where TitleID = '" + oldID + "'";

        //We create a statement object
        Statement stmt = connection.createStatement();

        //Then we perform the update
        stmt.executeUpdate(update);

    }
}
