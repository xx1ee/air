package com.xx1ee.service;

import com.xx1ee.classes.BoardingPassesPK;
import com.xx1ee.dto.AirportsDataCreateDto;
import com.xx1ee.dto.AirportsDataReadDto;
import com.xx1ee.dto.BoardingPassesCreateDto;
import com.xx1ee.dto.BoardingPassesReadDto;
import com.xx1ee.entity.airports_data;
import com.xx1ee.entity.boarding_passes;
import com.xx1ee.mapper.BoardingPassesCreateMapper;
import com.xx1ee.mapper.BoardingPassesReadMapper;
import com.xx1ee.repos.BoardingPassesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BoardingPassesService {
    private final BoardingPassesRepository boardingPassesRepository;
    private final BoardingPassesCreateMapper boardingPassesCreateMapper;
    private final BoardingPassesReadMapper boardingPassesReadMapper;
    @Transactional
    public boolean delete(BoardingPassesPK id) {
        var maybeAircraft = boardingPassesRepository.findById(id);
        maybeAircraft.ifPresent(aircraft -> boardingPassesRepository.delete(maybeAircraft.get()));
        return maybeAircraft.isPresent();
    }

    public Optional<BoardingPassesReadDto> findById(BoardingPassesPK id) {
        return boardingPassesRepository.findById(id).map(boardingPassesReadMapper::mapFrom);
    }

    public void update(boarding_passes boardingPasses) {
        boardingPassesRepository.update(boardingPasses);
    }
    public void save(BoardingPassesCreateDto boardingPasses) {
        boardingPassesRepository.save(boardingPassesCreateMapper.mapFrom(boardingPasses));
    }
    public List<boarding_passes> findAll() {
        return boardingPassesRepository.findAll();
    }

}
