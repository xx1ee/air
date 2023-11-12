package com.xx1ee;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.xx1ee.classes.AirportName;
import com.xx1ee.classes.City;
import com.xx1ee.classes.Model;
import com.xx1ee.entity.airports_data;
import com.xx1ee.mapper.AirportsDataReadMapper;
import com.xx1ee.repos.AirportsDataRepository;
import com.xx1ee.service.AirportsDataService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        String data = "{\n" +
                "        \"en\":\"Test170\",\n" +
                "        \"ru\":\"Тест170\"\n" +
                "}";
        Gson gson = new Gson();
        //JsonElement jsonModel = gson.fromJson(data, JsonElement.class);
        Model model = gson.fromJson(data, Model.class);
        System.out.println(model.getEn());
    }
}