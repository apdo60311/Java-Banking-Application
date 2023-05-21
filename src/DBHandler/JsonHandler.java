package DBHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * JsonHandler 
 * Contains all methods related to json file which used as database function like
 * Insert user , update user , delete user
 */

public class JsonHandler {

    // read users  from json file and store them in array of map [to]
    public static void readFromJson(List<Map<String, Object>> to , String path) {
        JSONParser parser = new JSONParser();
        if (to == null) {
            to = new ArrayList<>();
        }
        try {
            // read JSON file into JSON array
            Object obj = parser.parse(new FileReader(path));
            JSONArray jsonArray = (JSONArray) obj;

            // parse each JSON object into map and add to list
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                Map<String, Object> map = (Map<String, Object>) jsonObject;
                to.add(map);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    // find user by a key like (id , username)
    public static Map<String,Object> findByKey(String path , String key , Object value) {
        List<Map<String, Object>> to = new ArrayList<>();
        readFromJson(to, path);

        for (Map<String,Object> map : to) {
            if (map.get(key).toString().equals(value.toString())) {
                return map;
            }
        }
        return null;
    }
    // insert new data to json file
    public static void writeToJson(Map<String, Object> from , String path) {
        JSONParser parser = new JSONParser();
        try {

            File jsonFile = new File(path);
            // read JSON file into JSON array
            Object obj = parser.parse(new FileReader(jsonFile));
            JSONArray jsonArray = (JSONArray) obj;
            jsonArray.add(from);

            FileWriter fileWriter = new FileWriter(jsonFile);
            BufferedWriter out = new BufferedWriter(fileWriter);

            String jsonContent = jsonArray.toJSONString();

            out.write(jsonContent);
            out.flush();
            out.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    // delete everything in file except [] 
    public static void emptyJson(String path) {
        try {
            File jsonFile = new File(path);

            FileWriter fileWriter = new FileWriter(jsonFile);
            BufferedWriter out = new BufferedWriter(fileWriter);

            String jsonContent = "[]";

            out.write(jsonContent);
            out.flush();
            out.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    //  update data of a user in json file
    public static void writeToJsonWithReplacement(Map<String, Object> from , String path) {
        JSONParser parser = new JSONParser();
        try {
            
            File jsonFile = new File(path);
            // read JSON file into JSON array
            Object obj = parser.parse(new FileReader(jsonFile));
            JSONArray jsonArray = (JSONArray) obj;
            
            for (Object data : jsonArray) {
                // System.out.println(((Map<String, Object>) data).get("id"));
                // System.out.println(from.get("id"));
                if (((Map<String, Object>) data).get("id").toString().equals(from.get("id").toString())) {
                    jsonArray.remove(data);
                    break;
                }
            }
            
            jsonArray.add(from);

            FileWriter fileWriter = new FileWriter(jsonFile);
            BufferedWriter out = new BufferedWriter(fileWriter);

            String jsonContent = jsonArray.toJSONString();

            out.write(jsonContent);
            out.flush();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}