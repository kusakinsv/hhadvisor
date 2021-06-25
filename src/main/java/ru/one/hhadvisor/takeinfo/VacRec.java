package ru.one.hhadvisor.takeinfo;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class VacRec {
//   private int page=1;
//    private String pagestring = "&page="+page;
//    private final String url = "https://api.hh.ru/vacancies?per_page=1" + pagestring + "&text=Java";
//
//// public VacancyModel testVacancyResponse() throws IOException {
//    public VacancyModel testVacancyResponse() throws IOException {
//        String sURL = url; //just a string
//        URL adr = new URL(sURL);
//        HttpURLConnection request = (HttpURLConnection) adr.openConnection();
//        request.connect();
//        JsonParser jp = new JsonParser(); //from gson
//        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
//        JsonObject rootobj = root.getAsJsonObject();
//
//        int itemid = 0;
//        Integer found = JsonPath.read(rootobj.toString(), "$.found"); //pages - 0-19
//        Integer mainpage = JsonPath.read(rootobj.toString(), "$.page"); //pages - 0-19
//        Integer per_page = JsonPath.read(rootobj.toString(), "$.per_page"); //20
//        String name = JsonPath.read(rootobj.toString(), "$.items[0].name");
//        String company = JsonPath.read(rootobj.toString(), "$.items[0].employer.name");
//        String area = JsonPath.read(rootobj.toString(), "$.items[0].area.name");
//        String areaId = JsonPath.read(rootobj.toString(), "$.items[0].area.id");
//        Configuration conf = Configuration.defaultConfiguration();
//        Configuration conf2 = conf.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
//        Integer salaryFrom = JsonPath.using(conf2).parse(rootobj.toString()).read("$.items[0].salary.from");
//        Integer salaryTo = JsonPath.read(rootobj.toString(), "$.items[0].salary.to");
//        String salaryCurrency = JsonPath.read(rootobj.toString(), "$.items[0].salary.currency");
//        //String experience = JsonPath.read(rootobj.toString(), "");
//
//
//        // System.out.println(name + " " + company + " " + area + " " + areaId + " " + salaryFrom + " " + salaryTo + " " + salaryCurrency);
////        System.out.println("Page:" + mainpage);
////        System.out.println("Found:" + found);
////        System.out.println("Per Page:" + per_page);
//
//
//
//
//
////        VacancyModel testmodel = new VacancyModel(name, company){//, area, areaId, salaryFrom, salaryTo, salaryCurrency);
////        System.out.println(name + ";" + company + ";" + area + ";" + areaId + ";" + salaryFrom + ";" + salaryTo + ";" + salaryCurrency);
////        return testmodel;
//    }

    }





















//    RestTemplate restTempl = new RestTemplate();
//    VacModelGSON model1 = restTempl.getForObject(url, VacModelGSON.class);
//    //ObjectMapper mapper = new ObjectMapper();
//    //model.setName(mapper.readValue();
////        Gson gson = new Gson();
////        VacancyModel model = gson.fromJson(url, VacancyModel.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        VacModelJackson model = objectMapper.readValue(url, VacModelJackson.class);
        //Sdystem.out.println(model.getFound());

        //Object o = JsonPath.parse(url);
         //documentContext = JsonPath.parse(url);
//        name = JsonPath.read(documentContext, "$.items.name",);





////        name = objectMapper.readValue(url,String name);
//        Object o = JsonPath.parse(url);
//        name =
//        System.out.println(o.toString());


       // Page page = objectMapper.readValue(new File("sampleJSONFile.json"), Page.class);
        //



//    public Vacancy getVacancyById(String id) {//работает запрос по id
//        System.out.println(url);
//        return restTempl.getForEntity(url + id, Vacancy.class).getBody();
//
//    }

