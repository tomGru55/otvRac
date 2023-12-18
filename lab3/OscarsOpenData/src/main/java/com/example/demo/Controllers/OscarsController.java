package com.example.demo.Controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.repo.oscars;
import com.example.demo.service.OscarsService;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("dataquery")
public class OscarsController {
    @Autowired
    OscarsService oscarsService;


    @GetMapping
    public ResponseEntity<CustomResponse<?>> getFilteredData(@RequestParam String keyword, @RequestParam String selectOption)  {
        List<oscars> currentFilteredData=oscarsService.getFilteredData(keyword,selectOption);
        if(!currentFilteredData.isEmpty()) {
            generateJsonFile(currentFilteredData);
            generateCsvFile(currentFilteredData);
            CustomResponse<List<oscars>> customResponse = new CustomResponse<>("OK",
                    "Fetched filtered data (select option : " + selectOption + ", keyword: " + keyword + ")"
                    , currentFilteredData);
            return ResponseEntity.ok(customResponse);
        } else {
            generateJsonFile(currentFilteredData);
            generateCsvFile(currentFilteredData);
            CustomResponse<List<oscars>> customResponse = new CustomResponse<>("OK",
                    "Fetched an empty list because no records meet the conditions (select option : " + selectOption + ", keyword: " + keyword + ")"
                    , currentFilteredData);
            return ResponseEntity.ok(customResponse);
        }
    }

    @GetMapping("/getAll")
    private ResponseEntity<CustomResponse<?>> getAll(){
        List<oscars> result = oscarsService.getAll();
        CustomResponse<List<oscars>> customResponse = new CustomResponse<>("OK", "Fetched all data", result);
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CustomResponse<?>> getById(@PathVariable Long id) {
        Optional<oscars> result = oscarsService.getById(id);

        if (result.isPresent()) {
            oscars data = result.get();
            CustomResponse<oscars> customResponse = new CustomResponse<>("OK", "Fetched resource with id: " + id, data);
            return ResponseEntity.ok(customResponse);
        } else {
            CustomResponse<Void> customResponse = new CustomResponse<>("Not Found", "Resource with id: " + id + " does not exist", null);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);
        }
    }
    @GetMapping("/getByName/{name}")
    public ResponseEntity<CustomResponse<?>> getByName(@PathVariable String name){
        List<oscars> data=oscarsService.getByName(name) ;
        if(!data.isEmpty()){
            CustomResponse<List<oscars>> customResponse = new CustomResponse<>("OK", "Fetched resource with name: " + name, data);
            return ResponseEntity.ok(customResponse);
        } else {
            CustomResponse<Void> customResponse = new CustomResponse<>("Not Found", "Resource with name: " + name + " does not exist", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);
        }
    }
    @GetMapping("/getByLastName/{lastName}")
    public ResponseEntity<CustomResponse<?>> getByLastName(@PathVariable String lastName){
        List<oscars> data=oscarsService.getByLastName(lastName) ;
        if(!data.isEmpty()){
            CustomResponse<List<oscars>> customResponse = new CustomResponse<>("OK", "Fetched resource with last name: " + lastName, data);
            return ResponseEntity.ok(customResponse);
        } else {
            CustomResponse<Void> customResponse = new CustomResponse<>("Not Found", "Resource with last name: " + lastName + " does not exist", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<CustomResponse<oscars>> createRecord(@RequestBody oscars oscar) {
        oscars data = oscarsService.createRecord(oscar);
        CustomResponse<oscars> customResponse = new CustomResponse<>("OK", "Successfully created a new record with id: " + data.getId(), data);
        return ResponseEntity.ok(customResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse<oscars>> updateRecord(@PathVariable Long id, @RequestBody oscars newRecord) {
        oscars data = oscarsService.updateRecord(id,newRecord);
        if(data!=null){
            CustomResponse<oscars> customResponse = new CustomResponse<>("OK", "Successfully updated a record with id: " + data.getId(), data);
            return ResponseEntity.ok(customResponse);
        } else{
            CustomResponse<oscars> customResponse = new CustomResponse<>("OK", "Could not perform update because record with id: " + id +"does not exist", data);
            return ResponseEntity.ok(customResponse);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteRecord(@PathVariable Long id){
        oscarsService.deleteRecord(id);
    }
    private void generateCsvFile(List<oscars> currentFilteredData) {
        String pathStr= "D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/generatedFiles/oscarsFilteredCSV.csv";
        Path path = Paths.get(pathStr);
        if(Files.exists(path)) {
            try {
                Files.delete(path);
                System.out.println("File " + pathStr + " deleted successfully.");
            } catch (Exception e) {
                System.err.println("Error deleting file: " + e.getMessage());
            }
        }


        StringBuilder sb = new StringBuilder();
        sb.append("id,naziv_ceremonije,datum_ceremonije,lokacija_ceremonije,kategorija_nagrade,film,dobitnik_ime,dobitnik_prezime,dobitnik_datumrod,ime_lika_u_filmu,redatelj");
        sb.append("\n");
        for(oscars i: currentFilteredData){
            sb.append(i.getId());
            sb.append(",");
            sb.append(i.getNazivCeremonije().replaceAll(",", "\\\\,"));
            sb.append(",");
            sb.append(i.getDatumCeremonije());
            sb.append(",");
            sb.append(i.getLokacijaCeremonije().replaceAll(",", "\\\\,"));
            sb.append(",");
            sb.append(i.getKategorijaNagrade().replaceAll(",", "\\\\,"));
            sb.append(",");
            sb.append(i.getFilm());
            sb.append(",");
            sb.append(i.getDobitnikIme().replaceAll(",", "\\\\,"));
            sb.append(",");
            sb.append(i.getDobitnikPrezime().replaceAll(",", "\\\\,"));
            sb.append(",");
            sb.append(i.getDobitnikDatumrod());
            sb.append(",");
            sb.append(i.getImeLikaUFilmu().replaceAll(",", "\\\\,"));
            sb.append(",");
            sb.append(i.getRedatelj().replaceAll(",", "\\\\,"));
            sb.append("\n");
        }
        try (FileWriter writer = new FileWriter(pathStr)) {
            writer.write(sb.toString());
            System.out.println("CSV written to file successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateJsonFile(List<oscars> currentFilteredData) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode jsonArray = objectMapper.createArrayNode();

        for(oscars i: currentFilteredData){
            ObjectNode jsonObject = objectMapper.createObjectNode();
            ObjectNode jsonChildObject=objectMapper.createObjectNode();
            jsonObject.put("id", i.getId());
            jsonObject.put("naziv_ceremonije", i.getNazivCeremonije());
            jsonObject.put("datum_ceremonije", String.valueOf(i.getDatumCeremonije()));
            jsonObject.put("lokacija_ceremonije", i.getLokacijaCeremonije());
            jsonChildObject.put("kategorija_nagrade",i.getKategorijaNagrade());
            jsonChildObject.put("film",i.getFilm());
            jsonChildObject.put("dobitnik_ime",i.getDobitnikIme());
            jsonChildObject.put("dobitnik_prezime",i.getDobitnikPrezime());
            jsonChildObject.put("dobitnik_datumrod", String.valueOf(i.getDobitnikDatumrod()));
            jsonChildObject.put("ime_lika_u_filmu",i.getImeLikaUFilmu());
            jsonChildObject.put("redatelj",i.getRedatelj());
            jsonObject.put("nagrade", jsonChildObject);
            jsonArray.add(jsonObject);
        }
        String JSONData = jsonArray.toPrettyString();
        System.out.println(JSONData);
        try {
            String pathStr="D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/generatedFiles/oscarsFilteredJSON.json";
            Path path = Paths.get(pathStr);
            if(Files.exists(path)) {
                try {
                    Files.delete(path);
                    System.out.println("File " + pathStr + " deleted successfully.");
                } catch (Exception e) {
                    System.err.println("Error deleting file: " + e.getMessage());
                }
            }
            Object jsonMap = objectMapper.readValue(JSONData, Object.class);

            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(pathStr), jsonMap);

            System.out.println("JSON written to file successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
