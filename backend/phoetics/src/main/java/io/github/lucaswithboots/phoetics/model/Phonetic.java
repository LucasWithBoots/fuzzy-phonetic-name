package io.github.lucaswithboots.phoetics.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="phonetics")
public class Phonetic {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;

        private String name;

        private String code;


}
