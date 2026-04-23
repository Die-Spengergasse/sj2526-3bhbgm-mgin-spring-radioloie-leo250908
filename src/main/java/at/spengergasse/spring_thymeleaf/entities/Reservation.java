package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Reservation {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private int id;

    @Column
    private LocalDate date;

    @Column
    private String time;

    private String bodyRegion;

    private String comment;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="device_id")
    private Device device;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Reservation(int id, LocalDate date, String time, String bodyRegion, String comment, Patient patient, Device device) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.bodyRegion = bodyRegion;
        this.comment = comment;
        this.patient = patient;
        this.device = device;
    }

    public Reservation() {
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBodyRegion() {
        return bodyRegion;
    }

    public void setBodyRegion(String bodyRegion) {
        this.bodyRegion = bodyRegion;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

