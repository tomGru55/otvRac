package com.example.demo.Controllers;

import com.example.demo.repo.oscars;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DatatableController {
    @GetMapping("/datatable")
    public String datatable(){
        return "datatable";
    }

}
