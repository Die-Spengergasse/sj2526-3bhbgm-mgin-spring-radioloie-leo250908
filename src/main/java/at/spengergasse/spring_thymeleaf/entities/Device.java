package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;

@Entity
@Table
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    private String geraetename;
    private int raum_nr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGeraetename() {
        return geraetename;
    }

    public void setGeraetename(String geraetename) {
        this.geraetename = geraetename;
    }

    public int getRaum_nr() {
        return raum_nr;
    }

    public void setRaum_nr(int raumNr) {
        this.raum_nr = raumNr;
    }
}
