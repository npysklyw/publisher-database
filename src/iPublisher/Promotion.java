package iPublisher;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

public class Promotion {

    private final DoubleProperty percent;
    private final StringProperty time;
    private final StringProperty name;
    private Titles title;

    public Titles getTitle() {
        return title;
    }

    public void setTitle(Titles title) {
        this.title = title;
    }

    public Promotion(DoubleProperty percent, StringProperty time, StringProperty name, Titles title) {
        this.percent = percent;
        this.time = time;
        this.name = name;
        this.title = title;
    }

    public double getPercent() {
        return percent.get();
    }

    public DoubleProperty percentProperty() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent.set(percent);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
