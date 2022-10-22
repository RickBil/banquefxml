package ism.banque.core;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Config {

    private static final String  file="src/main/resources/ism/banque/parametre.json";
    public static JsonNode loadJsonFile() throws StreamReadException, DatabindException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File(file);
        JsonNode jsonNode = mapper.readValue(jsonFile, JsonNode.class);

        return jsonNode;
    }

    public static int getSeqAgence() throws StreamReadException, DatabindException, IOException{
       JsonNode root= loadJsonFile();
       JsonNode sequenceNode = root.get("sequence");
        //Recuperation du Noeud Agence 
        JsonNode agenceNode = sequenceNode.get("agence");
        return agenceNode.asInt();
    }

    public static int getSeqCompte() throws StreamReadException, DatabindException, IOException{
        JsonNode root= loadJsonFile();
        JsonNode sequenceNode = root.get("sequence");
         //Recuperation du Noeud Agence 
         JsonNode compteNode = sequenceNode.get("compte");
         return compteNode.asInt();
     }

     public static String getPrefixCompte() throws StreamReadException, DatabindException, IOException{
        JsonNode root= loadJsonFile();
        JsonNode sequenceNode = root.get("prefix");
         //Recuperation du Noeud Agence 
         JsonNode compteNode = sequenceNode.get("compte");
         return compteNode.asText();
     }

     public static String getPrefixAgence() throws StreamReadException, DatabindException, IOException{
        JsonNode root= loadJsonFile();
        JsonNode sequenceNode = root.get("prefix");
         //Recuperation du Noeud Agence 
         JsonNode compteNode = sequenceNode.get("agence");
         return compteNode.asText();
     }



}
