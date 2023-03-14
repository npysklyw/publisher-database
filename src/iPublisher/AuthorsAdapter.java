package iPublisher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Match;

public class AuthorsAdapter {

    Connection connection;

    public AuthorsAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;

        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                stmt.execute("DROP TABLE Authors");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {

                // Create the table of Authors
                stmt.execute("CREATE TABLE Authors ("
                        + "AuthorID CHAR(15) NOT NULL PRIMARY KEY, "
                        + "AuthorName CHAR(40), "
                        + "Address CHAR(30), "
                        + "Unit CHAR(15) "
                        + ")");


                populateSamples();


            }
        }
    }

    //Will initialize the Authors table by adding in some default rows
    private void populateSamples() throws SQLException{
        // Create a listing of the matches to be played

        this.insertAuthor("3134","George Orwell", "50 Queen Street", "Penguin");

    }

    //Method that will insert a row into our table with all the parameters passed
    //We use the SQL INSERT for this
    public void insertAuthor(String id, String name, String add, String unit) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("INSERT INTO Authors (AuthorID, AuthorName, Address, Unit) "
                + "VALUES ( '" + id + "', '" + name + "', '" + add + "', '" + unit + "')");

    }

    //This method will remove the row that contains the id that is passed to the method
    //We use the SQL DELETE for this
    public void removeAuthor(String id) throws SQLException {

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("DELETE FROM Authors WHERE AuthorID= '" + id + "'");
    }

    //This method will return an arraylist of author objects
    //We first get the entire Authors table then iterate through the resultset adding each row to an arraylist
    //Finally we return this arraylist
    public ObservableList<Authors> getAuthorsList() throws SQLException {
        ObservableList<Authors> authorList = FXCollections.observableArrayList();

        String selectCommand = "SELECT * FROM Authors";

        //Creating a statement object
        Statement stmt = connection.createStatement();

        //Then we store the result of the query from the command variable, as a resultset object
        ResultSet result = stmt.executeQuery(selectCommand);

        //We iterate through the results we obtained
        while (result.next()) {

            authorList.add(new Authors(result.getString("AuthorID"), result.getString("AuthorName"), result.getString("Address"), result.getString("Unit")));
        }

        return authorList;
    }

    //This method will return an arraylist of author ids
    //We first get the entire Authors table then iterate through the resultset adding each authorID to an arraylist
    //Finally we return this arraylist
    public ObservableList<String> getAuthorIDsList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String command = "SELECT * FROM Authors";

        // Execute the statement and return the result, store it as a resultset
        rs= stmt.executeQuery(command);


        //We loop through the entire resultset
        while (rs.next()) {

            list.add(rs.getString("AuthorID"));
        }

        return list;
    }

    //This method is used to update the Author table with new values passed to it
    //We use the SQL UPDATE command for this
    public void setAutDetail(String id,String name, String add, String unit, String oldID) throws SQLException
    {
        //This is the update SQL command as a string
        String update = "Update Authors SET AuthorID = '" + id + "',  AuthorName = '" + name + "',  Address = '" + add + "', Unit= '" + unit + "' Where AuthorID = '" + oldID + "'";

        //We create a statement object
        Statement stmt = connection.createStatement();

        //Then we perform the update
        stmt.executeUpdate(update);

    }
}
