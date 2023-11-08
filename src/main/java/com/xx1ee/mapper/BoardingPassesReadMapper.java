package com.xx1ee.mapper;

import com.xx1ee.dto.BoardingPassesReadDto;
import com.xx1ee.entity.boarding_passes;

public class BoardingPassesReadMapper implements Mapper<boarding_passes, BoardingPassesReadDto>{
    @Override
    public BoardingPassesReadDto mapFrom(boarding_passes object) {
        return new BoardingPassesReadDto(object.getBoardingPassesPK(), object.getBoarding_no(), object.getSeat_no());}
}
