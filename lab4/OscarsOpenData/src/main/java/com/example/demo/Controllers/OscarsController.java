package com.example.demo.Controllers;
import com.example.demo.repo.oscars;
import com.example.demo.service.OscarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/datatable")
public class OscarsController {
    @Autowired
    OscarsService oscarsService;

    FileGeneratorClass fileGeneratorClass = new FileGeneratorClass();

    @GetMapping("filtered/")
    public ResponseEntity<CustomResponse<?>> getFilteredData(@RequestParam String keyword, @RequestParam String selectOption)  {
        List<oscars> currentFilteredData=oscarsService.getFilteredData(keyword,selectOption);
        if(!currentFilteredData.isEmpty()) {
            fileGeneratorClass.generateJsonFile(currentFilteredData,"D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/generatedFiles/oscarsFilteredJSON.json");
            fileGeneratorClass.generateCsvFile(currentFilteredData,"D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/generatedFiles/oscarsFilteredCSV.csv");
            CustomResponse<List<oscars>> customResponse = new CustomResponse<>("OK",
                    "Fetched filtered data (select option : " + selectOption + ", keyword: " + keyword + ")"
                    , currentFilteredData);
            return ResponseEntity.ok(customResponse);
        } else {
            fileGeneratorClass.generateJsonFile(currentFilteredData,"D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/generatedFiles/oscarsFilteredJSON.json");
            fileGeneratorClass.generateCsvFile(currentFilteredData,"D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/generatedFiles/oscarsFilteredCSV.csv");
            CustomResponse<List<oscars>> customResponse = new CustomResponse<>("OK",
                    "Fetched an empty list because no records meet the conditions (select option : " + selectOption + ", keyword: " + keyword + ")"
                    , currentFilteredData);
            return ResponseEntity.ok(customResponse);
        }
    }

    @GetMapping("/all")
    private ResponseEntity<CustomResponse<?>> getAll(){
        List<oscars> result = oscarsService.getAll();
        CustomResponse<List<oscars>> customResponse = new CustomResponse<>("OK", "Fetched all data", result);
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/{id}")
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
    @GetMapping("/name/{name}")
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
    @GetMapping("/lastName/{lastName}")
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


    @PostMapping("/")
    public ResponseEntity<CustomResponse<oscars>> createRecord(@RequestBody oscars oscar) {
        oscars data = oscarsService.createRecord(oscar);
        CustomResponse<oscars> customResponse = new CustomResponse<>("OK", "Successfully created a new record with id: " + data.getId(), data);
        return ResponseEntity.ok(customResponse);
    }

    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id){
        oscarsService.deleteRecord(id);
    }



}
