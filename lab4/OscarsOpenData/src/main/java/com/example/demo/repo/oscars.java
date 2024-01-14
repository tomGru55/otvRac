package com.example.demo.repo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class oscars {
    @Id @GeneratedValue
    private Long id;
    private String nazivCeremonije;
    private String datumCeremonije;
    private String lokacijaCeremonije;
    private String kategorijaNagrade;
    private String film;
    private String dobitnikIme;
    private String dobitnikPrezime;
    private String dobitnikDatumrod;
    @Column(name="ime_lika_u_filmu")
    private String imeLikaUFilmu;
    private String redatelj;
}

