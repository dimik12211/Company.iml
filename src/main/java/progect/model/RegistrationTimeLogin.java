package progect.model;

import javax.persistence.*;

@Entity
@Table(name = "t_registrationtimelogin")
public class RegistrationTimeLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "timeAndDate")
    private String timeAndDate;

    @Column(name = "username")
    private String username;

    public RegistrationTimeLogin(){

    }

    public RegistrationTimeLogin(String name, String surname, String patronymic, String timeAndDate, String username){
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.timeAndDate = timeAndDate;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
