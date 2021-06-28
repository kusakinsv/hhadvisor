package ru.one.hhadvisor.program;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Scanner;

public class JsonAreas {

    public void test() {
        //getJson("classpath:sample.json");
    } }

//    public JsonAreas getJson(String path) {
//        File file = null;
//        try {
//            file = ResourceUtils.getFile(path);
//            //Read File Content
//            String content = new String(Files.readAllBytes(file.toPath()));
//            //Get a Json String
//            System.out.println(content);
//            JsonAreas json = JSON.parseObject(content);
//            return json;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


//                try (FileReader reader = new FileReader("/other/areas.json"))
//        //Scanner scanner = new Scanner(reader))
//        {
//
//        stringareas = reader.toString();
//
//        }
//    catch (Exception e){
//        System.out.println(e);
//    }
//
//        return stringareas;
//    }
//
//        //FileReader reader = new FileReader("/other/areas.json"))
//
//
//
//    public JsonAreas() throws FileNotFoundException {
//    }

