package com.xx1ee.dto;

import com.xx1ee.classes.Model;

public record PopularPlaneReadDto(String aircraft_code, Model model, Integer count){
}
