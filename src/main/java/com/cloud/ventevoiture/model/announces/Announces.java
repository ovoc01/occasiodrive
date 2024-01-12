package com.cloud.ventevoiture.model.announces;

import java.sql.Date;

import com.cloud.ventevoiture.model.car.Car;
import com.cloud.ventevoiture.model.user.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "announces")
@Entity
public class Announces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id_announces;
    String description;
    Date date_announces;
    int status;
    
    @ManyToOne
    @JoinColumn(name = "id_car")
    Car car;
    double selling_price;
    Date validation_date;

    @ManyToOne
    @JoinColumn(name = "id_person")
    Person person;

    public int getId_announces() {
        return id_announces;
    }

    public void setId_announces(int id_announces) {
        this.id_announces = id_announces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_announces() {
        return date_announces;
    }

    public void setDate_announces(Date date_announces) {
        this.date_announces = date_announces;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }

    public Date getValidation_date() {
        return validation_date;
    }

    public void setValidation_date(Date validation_date) {
        this.validation_date = validation_date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    
}
