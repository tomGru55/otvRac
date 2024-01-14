package com.example.demo.Controllers;

import com.example.demo.repo.oscars;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@NoArgsConstructor
public class FileGeneratorClass {
    public void generateFiles(List<oscars> oscars){
        generateCsvFile(oscars,"D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/generatedFiles/Download/oscarsCSV.csv");
        generateJsonFile(oscars,"D:/IntelijJ_workspace/OscarsOpenData/src/main/resources/static/generatedFiles/Download/oscarsJSON.json");
    }

    public void generateCsvFile(List<oscars> currentFilteredData, String pathStr) {
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

    public void generateJsonFile(List<oscars> currentFilteredData, String pathStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode jsonArray = objectMapper.createArrayNode();

        for(oscars i: currentFilteredData){
            ObjectNode jsonObject = objectMapper.createObjectNode();
            ObjectNode awardsChildObject=objectMapper.createObjectNode();
            ObjectNode winnerChildObject = objectMapper.createObjectNode();
            ObjectNode contextObject = objectMapper.createObjectNode();

            contextObject.put("@vocab", "http://schema.org/");
            contextObject.put("dobitnik_ime","givenName" );
            contextObject.put("dobitnik_prezime", "familyName");
            contextObject.put("dobitnik_datumrod", "birthDate");
            contextObject.put("dobitnik", "Person");

            jsonObject.put("@context", contextObject);

            jsonObject.put("id", i.getId());
            jsonObject.put("naziv_ceremonije", i.getNazivCeremonije());
            jsonObject.put("datum_ceremonije", String.valueOf(i.getDatumCeremonije()));
            jsonObject.put("lokacija_ceremonije", i.getLokacijaCeremonije());
            awardsChildObject.put("kategorija_nagrade",i.getKategorijaNagrade());
            awardsChildObject.put("film",i.getFilm());

            winnerChildObject.put("@type", "Person");
            winnerChildObject.put("dobitnik_ime",i.getDobitnikIme());
            winnerChildObject.put("dobitnik_prezime",i.getDobitnikPrezime());
            winnerChildObject.put("dobitnik_datumrod", String.valueOf(i.getDobitnikDatumrod()));

            awardsChildObject.put("ime_lika_u_filmu",i.getImeLikaUFilmu());
            awardsChildObject.put("redatelj",i.getRedatelj());
            awardsChildObject.put("dobitnik", winnerChildObject);

            jsonObject.put("nagrade", awardsChildObject);
            jsonArray.add(jsonObject);
        }
        String JSONData = jsonArray.toPrettyString();
        try {
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
