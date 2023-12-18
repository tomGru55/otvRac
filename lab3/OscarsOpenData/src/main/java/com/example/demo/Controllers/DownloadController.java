package com.example.demo.Controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.PathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

@RestController
public class DownloadController {

    @GetMapping("download/oscarsJSON")
    public ResponseEntity<Resource> downloadJSON(){
            Resource resource = new PathResource(Paths.get("D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/oscarsJSON.json"));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            headers.setContentDispositionFormData("attachment", "oscarsJSON.json");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);


    }
    @GetMapping("download/oscarsFilteredJSON")
    public ResponseEntity<Resource> downloadFilteredJSON(){
        Resource resource = new PathResource(Paths.get("D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/generatedFiles/oscarsFilteredJSON.json"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setContentDispositionFormData("attachment", "oscarsFilteredJSON.json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);


    }
     @GetMapping("download/oscarsCSV")
    public ResponseEntity<Resource> downloadCSV() {
             Resource resource = new PathResource(Paths.get("D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/oscarsCSV.csv"));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("text/csv"));

            headers.setContentDispositionFormData("attachment", "oscarsCSV.csv");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);

    }
    @GetMapping("download/oscarsFilteredCSV")
    public ResponseEntity<Resource> downloadFilteredCSV() {
        Resource resource = new PathResource(Paths.get("D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/generatedFiles/oscarsFilteredCSV.csv"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));

        headers.setContentDispositionFormData("attachment", "oscarsFilteredCSV.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);

    }
    @GetMapping("download/openapi")
    public ResponseEntity<Resource> downloadOpenapi(){
        Resource resource = new PathResource(Paths.get("D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/openapi.json"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setContentDispositionFormData("attachment", "openapi.json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);


    }
}
