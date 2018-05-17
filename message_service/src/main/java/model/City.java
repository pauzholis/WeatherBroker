package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс с названием города преобразуемый в xml
 */
@XmlRootElement
public class City implements XmlModel {

    private String name;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
