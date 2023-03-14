package iPublisher;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Abdelkader
 */

public class Authors {

    private final StringProperty authorID;
    private final StringProperty authorName;
    private final StringProperty address;
    private final StringProperty publishingUnit;

    //didn't implement
    private Titles[] titles;

    public Authors(String id,String name, String street , String unit) {
        this.authorName = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(street);
        this.publishingUnit = new SimpleStringProperty(unit);
        this.authorID = new SimpleStringProperty(id);
    }


    public void setAuthorName(String name) {
        authorName.set(name);
    }
    public StringProperty authorNameProperty() {
        return authorName;
    }

    public String getAuthorName() {
        return authorName.get();
    }

    public void setAddress(String street) {
        address.set(street);
    }
    public StringProperty addressProperty() {
        return address;
    }
    public String getAddress() {
        return address.get();
    }

    public void setPublishingUnit(String area) {
        publishingUnit.set(area);
    }
    public StringProperty publishingUnitProperty() {
        return publishingUnit;
    }
    public String getPublishingUnit() {
        return publishingUnit.get();
    }

    public void setAuthorID(String id) {
        authorID.set(id);
    }
    public StringProperty authorIDProperty() {
        return authorID;
    }

    public String getAuthorID() {
        return authorID.get();
    }

}
