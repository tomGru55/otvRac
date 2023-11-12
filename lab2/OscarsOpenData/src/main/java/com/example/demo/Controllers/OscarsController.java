package com.example.demo.Controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.repo.oscars;
import com.example.demo.service.OscarsService;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("dataquery")
public class OscarsController {
    @Autowired
    OscarsService oscarsService;


    @GetMapping
    public List<oscars> getFilteredData(@RequestParam String keyword, @RequestParam String selectOption)  {
        System.out.println("keyword: "+keyword);
        System.out.println("selectOption: "+selectOption);
        List<oscars> currentFilteredData=oscarsService.getFilteredData(keyword,selectOption);
        generateJsonFile(currentFilteredData);
        generateCsvFile(currentFilteredData);
        return currentFilteredData;
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
        sb.append("naziv_ceremonije,datum_ceremonije,lokacija_ceremonije,kategorija_nagrade,film,dobitnik_ime,dobitnik_prezime,dobitnik_datumrod,ime_lika_u_filmu,redatelj");
        sb.append("\n");
        for(oscars i: currentFilteredData){
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
