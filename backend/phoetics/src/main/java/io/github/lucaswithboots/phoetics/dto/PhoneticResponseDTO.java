package io.github.lucaswithboots.phoetics.dto;

import io.github.lucaswithboots.phoetics.model.Phonetic;
import lombok.Data;

@Data
public class PhoneticResponseDTO {
    private Long id;
    private String name;
    private String code;

    public PhoneticResponseDTO(Phonetic phonetic) {
        this.id = phonetic.getId();
        this.name = phonetic.getName();
        this.code = phonetic.getCode();
    }
}
