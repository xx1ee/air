package com.xx1ee.dto;

import com.xx1ee.classes.BoardingPassesPK;

public record BoardingPassesReadDto(BoardingPassesPK boardingPassesPK, Integer boarding_no, String seat_no) {
}
