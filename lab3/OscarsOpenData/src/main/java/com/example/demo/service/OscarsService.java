package com.example.demo.service;

import com.example.demo.dao.OscarsDao;
import com.example.demo.repo.oscars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class OscarsService {
    @Autowired
    OscarsDao oscarsDao;
    public List<oscars> getAllRows(){
        return oscarsDao.findAll();
    }
    public List<oscars> getByLastName(String lastName){
        return oscarsDao.findByDobitnikPrezime(lastName);
    }

    public List<oscars> getByName(String name) {
        return oscarsDao.findByDobitnikIme(name);
    }
   public List<oscars> getFilteredData(@RequestParam String keyword, @RequestParam String selectOption){
       return oscarsDao.getFilteredData(keyword,selectOption);
   }
   public Optional<oscars> getById(Long id){
        return oscarsDao.findById(id);
    }
   public oscars createRecord(oscars oscar){
        return oscarsDao.save(oscar);
   }

   public void deleteRecord(Long id){
         oscarsDao.deleteById(id);
   }
    public oscars updateRecord(Long id, oscars newEntity){
        oscars existingEntity = oscarsDao.findById(id).orElse(null);
        if(existingEntity!=null){
            existingEntity.setNazivCeremonije(newEntity.getNazivCeremonije());
            existingEntity.setDatumCeremonije(newEntity.getDatumCeremonije());
            existingEntity.setLokacijaCeremonije(newEntity.getLokacijaCeremonije());
            existingEntity.setKategorijaNagrade(newEntity.getKategorijaNagrade());

            existingEntity.setDobitnikIme(newEntity.getDobitnikIme());
            existingEntity.setDobitnikPrezime(newEntity.getDobitnikPrezime());
            existingEntity.setDobitnikDatumrod(newEntity.getDobitnikDatumrod());

            existingEntity.setRedatelj(newEntity.getRedatelj());
            existingEntity.setFilm(newEntity.getFilm());
            existingEntity.setImeLikaUFilmu(newEntity.getImeLikaUFilmu());

           return oscarsDao.save(existingEntity);
        } else {
            return null; //handlaj pogresku
        }
    }
    public List<oscars> getAll(){
        return oscarsDao.findAll();
    }

}
