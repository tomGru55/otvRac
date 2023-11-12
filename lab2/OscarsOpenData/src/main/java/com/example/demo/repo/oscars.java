package com.example.demo.repo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class oscars {
    private String nazivCeremonije;
    private java.time.LocalDate datumCeremonije;
    private String lokacijaCeremonije;
    private String kategorijaNagrade;
    private String film;
    private String dobitnikIme;
    private String dobitnikPrezime;
    private java.time.LocalDate dobitnikDatumrod;
    @Id @Column(name="ime_lika_u_filmu")
    private String imeLikaUFilmu;
    private String redatelj;
}
