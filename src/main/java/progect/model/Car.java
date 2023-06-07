package progect.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "engine")
    private String engine;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "availability")
    private boolean availability;

    @Column(name = "carNumber")
    private String carNumber;

    @Column(name = "comment")
    private String comment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_user_id")
    private User OneToOneUser;

    public Car(){

    }

    public Car(String model, String engine, String transmission, boolean availability, String carNumber, String comment){
        this.model = model;
        this.engine = engine;
        this.transmission = transmission;
        this.availability = availability;
        this.carNumber = carNumber;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getOneToOneUser() {
        return OneToOneUser;
    }

    public void setOneToOneUser(User oneToOneUser) {
        OneToOneUser = oneToOneUser;
    }
}
