package io.github.lucaswithboots.phoetics.controller;

import io.github.lucaswithboots.phoetics.dto.PhoneticDTO;
import io.github.lucaswithboots.phoetics.dto.PhoneticResponseDTO;
import io.github.lucaswithboots.phoetics.model.Phonetic;
import io.github.lucaswithboots.phoetics.service.PhoneticService;
import mtfn.MetaphonePtBr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/phonetics")
public class PhoneticController {

    @Autowired
    private PhoneticService phoneticService;

    @GetMapping
    public ResponseEntity<List<PhoneticResponseDTO>> searchByName(@RequestParam String name) {
        List<Phonetic> phonetics = phoneticService.findByName(name);
        List<PhoneticResponseDTO> response = phonetics.stream()
                .map(PhoneticResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Phonetic> create(@RequestBody PhoneticDTO phoneticDTO){
        Phonetic phonetic = new Phonetic();

        String metaphoneCode = new MetaphonePtBr(phoneticDTO.getName()).toString();
        phonetic.setName(phoneticDTO.getName());
        phonetic.setCode(metaphoneCode);

        return ResponseEntity.status(HttpStatus.CREATED).body(phoneticService.save(phonetic));
    }
}
