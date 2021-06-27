package ru.one.hhadvisor.program;

import net.minidev.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class JsonAreas {
    private JSONObject areas;
    private String stringareas;

    public String giveAreas() throws IOException {
        try (FileReader reader = new FileReader("/other/areas.json"))
        //Scanner scanner = new Scanner(reader))
        {

        stringareas = reader.toString();

        }
    catch (Exception e){
        System.out.println(e);
    }

        return stringareas;
    }

        //FileReader reader = new FileReader("/other/areas.json"))



    public JsonAreas() throws FileNotFoundException {
    }
}
