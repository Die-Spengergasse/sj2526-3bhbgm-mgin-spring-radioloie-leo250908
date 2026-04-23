package at.spengergasse.spring_thymeleaf.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String svnr;
    private String firstname;
    private String lastname;
    private char gender;
    private LocalDate birth;

    public Patient(int id, String svnr, String firstname, String lastname, char gender, LocalDate birth) {
        this.id = id;
        this.svnr = svnr;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birth = birth;
    }

    public Patient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSvnr() {
        return svnr;
    }

    public void setSvnr(String svnr) {
        this.svnr = svnr;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String fristname) {
        this.firstname = fristname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
}
