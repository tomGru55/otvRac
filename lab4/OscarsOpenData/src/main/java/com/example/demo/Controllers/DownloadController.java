package com.example.demo.Controllers;

import com.example.demo.repo.oscars;
import com.example.demo.service.OscarsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.PathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class DownloadController {
    @Autowired
    OscarsService oscarsService;
    FileGeneratorClass fileGeneratorClass = new FileGeneratorClass();



    @GetMapping("download/downloadData")
    public ResponseEntity<Resource> downloadData() throws IOException {
        fileGeneratorClass.generateFiles(oscarsService.getAllRows());

        byte[] zipBytes;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
             Stream<Path> paths = Files.walk(Paths.get("D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/generatedFiles/Download"))) {

            paths.filter(Files::isRegularFile).forEach(path -> {
                try {
                    ZipEntry zipEntry = new ZipEntry(Paths.get("D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/generatedFiles/Download").relativize(path).toString());
                    zipOutputStream.putNextEntry(zipEntry);
                    Files.copy(path, zipOutputStream);
                    zipOutputStream.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            zipOutputStream.finish();

            zipBytes = byteArrayOutputStream.toByteArray();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("Download.zip").build());

        Resource resource = new ByteArrayResource(zipBytes);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }


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
