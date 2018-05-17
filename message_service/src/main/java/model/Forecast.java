package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Forecast implements XmlModel {
    private String date;
    private String city;
    private String temp;
    private String text;

    public Forecast() {
    }

    public Forecast(String date, String city, String temp, String text) {
        this.date = date;
        this.city = city;
        this.temp = temp;
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
