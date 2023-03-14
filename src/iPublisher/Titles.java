package iPublisher;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Abdelkader
 */

public class Titles {

    private final StringProperty titleID;
    private final StringProperty name;
    private final StringProperty isbn;
    private final StringProperty editionNo;
    private final StringProperty pubDate;
    private final StringProperty pubQTY;
    private final StringProperty author;

    //didn't implement these field
    private RevenueRecords[] records;
    private Promotion[] promos;

    public Titles(String id, String name , String isbn, String edNum, String publicationDate,String pubQuantity,String aut) {
        this.titleID = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.isbn = new SimpleStringProperty(isbn);
        this.editionNo = new SimpleStringProperty(edNum);
        this.pubDate = new SimpleStringProperty(publicationDate);
        this.pubQTY = new SimpleStringProperty(pubQuantity);
        this.author = new SimpleStringProperty(aut);
    }


    public void setTitleID(String name) {
        titleID.set(name);
    }
    public StringProperty titleIDProperty() {
        return titleID;
    }
    public String getTitleID() {
        return titleID.get();
    }

    public void setName(String street) {
        name.set(street);
    }
    public StringProperty nameProperty() {
        return name;
    }
    public String getName() {
        return name.get();
    }

    public void setIsbn(String area) {
        isbn.set(area);
    }
    public StringProperty isbnProperty() {
        return isbn;
    }
    public String getIsbn() {
        return isbn.get();
    }

    public String getEditionNo() {
        return editionNo.get();
    }

    public StringProperty editionNoProperty() {
        return editionNo;
    }

    public void setEditionNo(String editionNo) {
        this.editionNo.set(editionNo);
    }

    public String getPubDate() {
        return pubDate.get();
    }

    public StringProperty pubDateProperty() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate.set(pubDate);
    }

    public String getPubQTY() {
        return pubQTY.get();
    }

    public StringProperty pubQTYProperty() {
        return pubQTY;
    }

    public void setPubQTY(String pubQTY) {
        this.pubQTY.set(pubQTY);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

}
