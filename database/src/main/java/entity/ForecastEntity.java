package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность для базы данных
 */
@Entity
@Table(name = "forecast")
public class ForecastEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();
    @Column(name = "city")
    private String city;
    @Column(name = "temp")
    private String temp;
    @Column(name = "text")
    private String text;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    @Override
    public String toString() {
        return "ForecastEntity{" +
                "id=" + id +
                ", date=" + date +
                ", city='" + city + '\'' +
                ", temp='" + temp + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
