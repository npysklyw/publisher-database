package iPublisher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Match;

public class PublisherUnitAdapter {

    Connection connection;

    public PublisherUnitAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;

        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                stmt.execute("DROP TABLE PublisherUnits");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {

                // Create the table of PublisherUnits
                stmt.execute("CREATE TABLE PublisherUnits ("
                        + "PublisherName CHAR(15) NOT NULL PRIMARY KEY, "
                        + "Address CHAR(30), "
                        + "Region CHAR(15) "
                        + ")");


                populateSamples();


            }
        }
    }

    //Will initialize the PublishingUnit table by adding in some default rows
    private void populateSamples() throws SQLException{

        this.insertPublishingUnit("Penguin", "67 Yonge Street", "Ontario");
        this.insertPublishingUnit("Random House", "78 Russell Road", "Alberta");
        this.insertPublishingUnit("Harper Collins", "53 Dundas Street", "Quebec");



    }

    //Method that will insert a row into our table with all the parameters passed
    //We use the SQL INSERT for this
    public void insertPublishingUnit(String name, String add, String reg) throws SQLException {
        Statement stmt = connection.createStatement();

        stmt.executeUpdate("INSERT INTO PublisherUnits (PublisherName, Address, Region) "
                + "VALUES ( '" + name + "', '" + add + "', '" + reg + "')");

    }

    //This method will remove the row that contains the name that is passed to the method
    //We use the SQL DELETE for this
    public void removePublishingUnit(String name) throws SQLException {

        Statement stmt = connection.createStatement();

        stmt.executeUpdate("DELETE FROM PublisherUnits WHERE PublisherName= '" + name + "'");
    }

    //This method will return an arraylist of publisherunit objects
    //We first get the entire PublisherUnits table then iterate through the resultset adding each row to an arraylist
    //Finally we return this arraylist
    public ObservableList<PublisherUnit> getPublisherUnitsList() throws SQLException {
        ObservableList<PublisherUnit> pubList = FXCollections.observableArrayList();

        String selectCommand = "SELECT * FROM PublisherUnits";

        //Creating a statement object
        Statement stmt = connection.createStatement();

        //Then we store the result of the query from the command variable, as a resultset object
        ResultSet result = stmt.executeQuery(selectCommand);

        //We iterate through the results we obtained
        while (result.next()) {

            pubList.add(new PublisherUnit(result.getString("PublisherName"), result.getString("Address"), result.getString("Region")));
        }

        return pubList;
    }

    //This method will return an arraylist of publisher names
    //We first get the entire PublisherUnits table then iterate through the resultset adding each publisherName to an arraylist
    //Finally we return this arraylist
    public ObservableList<String> getPublishingNamesList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String command = "SELECT * FROM PublisherUnits";

        // Execute the statement and return the result, store it as a resultset
        rs= stmt.executeQuery(command);


        //We loop through the entire resultset
        while (rs.next()) {

            list.add(rs.getString("PublisherName"));
        }

        return list;
    }

    //This method is used to update the PublisherUnits table with new values passed to it
    //We use the SQL UPDATE command for this
    public void setPubDetail(String name, String add, String region, String oldName) throws SQLException
    {
        //This is the update SQL command as a string
        String update = "Update PublisherUnits SET PublisherName = '" + name + "', Address = '" + add + "', Region= '" + region + "' Where PublisherName = '" + oldName + "'";

        //We create a statement object
        Statement stmt = connection.createStatement();

        //Then we perform the update
        stmt.executeUpdate(update);

    }
}
