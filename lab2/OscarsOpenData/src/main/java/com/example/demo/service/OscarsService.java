package com.example.demo.service;

import com.example.demo.dao.OscarsDao;
import com.example.demo.repo.oscars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class OscarsService {
    @Autowired
    OscarsDao oscarsDao;
    public List<oscars> getAllRows(){
        return oscarsDao.findAll();
    }

   public List<oscars> getFilteredData(@RequestParam String keyword, @RequestParam String selectOption){
       return oscarsDao.getFilteredData(keyword,selectOption);
   }

}
