package com.example.demo.dao;

import com.example.demo.repo.oscars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;
@Repository
public interface OscarsDao extends JpaRepository<oscars, String> {
    String filterDataQuery = "SELECT o FROM oscars o "+
        "where ( "+
        "("+
        ":selectOption = 'wildcard' and ("+
        "o.nazivCeremonije like %:keyword% or TO_CHAR(o.datumCeremonije, 'DD-MM-YYYY') like %:keyword% or o.lokacijaCeremonije like %:keyword% or o.kategorijaNagrade like %:keyword% "+
        "or o.film  like %:keyword% or o.dobitnikIme like %:keyword%  or o.dobitnikPrezime like %:keyword%  or TO_CHAR(o.dobitnikDatumrod, 'DD-MM-YYYY') like %:keyword%  "+
        "or o.imeLikaUFilmu like %:keyword%  or o.redatelj like %:keyword% )"+
        ")"+
        "or "+
        "(:selectOption = 'nazivCeremonije' and o.nazivCeremonije like %:keyword%)"+
        "or"+
        "(:selectOption = 'datumCeremonije' and TO_CHAR(o.datumCeremonije, 'DD-MM-YYYY') like %:keyword%)"+
        "or"+
        "(:selectOption = 'lokacijaCeremonije' and o.lokacijaCeremonije like %:keyword%)"+
        "or"+
        "(:selectOption = 'kategorijaNagrade' and o.kategorijaNagrade like %:keyword%)"+
        "or"+
        "(:selectOption = 'film' and o.film like %:keyword%)"+
        "or"+
        "(:selectOption = 'dobitnikIme' and o.dobitnikIme like %:keyword%)"+
        "or"+
        "(:selectOption = 'dobitnikPrezime' and o.dobitnikPrezime like %:keyword%)"+
        "or"+
        "(:selectOption = 'dobitnikDatumrod' and TO_CHAR(o.dobitnikDatumrod, 'DD-MM-YYYY') like %:keyword%)"+
        "or"+
        "(:selectOption = 'imeLikaUFilmu' and o.imeLikaUFilmu like %:keyword%)"+
        "or"+
        "(:selectOption = 'redatelj' and o.redatelj like %:keyword%)"+
        ")";

   @Query(filterDataQuery)
    List<oscars> getFilteredData(@Param("keyword") String keyword, @Param("selectOption") String selectOption);

}
