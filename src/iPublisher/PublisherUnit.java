package iPublisher;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Abdelkader
 */

public class PublisherUnit {

    private final StringProperty publisherName;
    private final StringProperty address;
    private final StringProperty region;

    //didn't implement
    private Authors[] authors;

    public PublisherUnit(String name, String street , String area) {
        this.publisherName = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(street);
        this.region = new SimpleStringProperty(area);
    }


    public void setPublisherName(String name) {
        publisherName.set(name);
    }
    public StringProperty publisherNameProperty() {
        return publisherName;
    }
    public String getName() {
        return publisherName.get();
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

    public void setRegion(String area) {
        region.set(area);
    }
    public StringProperty regionProperty() {
        return region;
    }
    public String getRegion() {
        return region.get();
    }

}
