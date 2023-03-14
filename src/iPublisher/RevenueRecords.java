package iPublisher;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class RevenueRecords {

    private final IntegerProperty sales;
    private final DoubleProperty margin;
    private final StringProperty date;
    private final StringProperty givenTitle;


    public String getGivenTitle() {
        return givenTitle.get();
    }

    public StringProperty givenTitleProperty() {
        return givenTitle;
    }

    public void setGivenTitle(String givenTitle) {
        this.givenTitle.set(givenTitle);
    }

    public RevenueRecords(IntegerProperty sales, DoubleProperty margin, StringProperty date, StringProperty givenTitle) {
        this.sales = sales;
        this.margin = margin;
        this.date = date;
        this.givenTitle = givenTitle;
    }

    public int getSales() {
        return sales.get();
    }

    public IntegerProperty salesProperty() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales.set(sales);
    }

    public double getMargin() {
        return margin.get();
    }

    public DoubleProperty marginProperty() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin.set(margin);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
