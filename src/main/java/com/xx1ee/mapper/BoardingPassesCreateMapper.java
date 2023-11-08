package com.xx1ee.mapper;

import com.xx1ee.dto.BoardingPassesCreateDto;
import com.xx1ee.entity.boarding_passes;

public class BoardingPassesCreateMapper implements Mapper<BoardingPassesCreateDto, boarding_passes>{
    @Override
    public boarding_passes mapFrom(BoardingPassesCreateDto object) {
        return boarding_passes.builder().boardingPassesPK(object.boardingPassesPK())
                .boarding_no(object.boarding_no())
                .seat_no(object.seat_no())
                .build();
    }
}
