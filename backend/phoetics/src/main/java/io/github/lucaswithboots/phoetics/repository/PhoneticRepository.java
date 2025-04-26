package io.github.lucaswithboots.phoetics.repository;

import io.github.lucaswithboots.phoetics.model.Phonetic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneticRepository extends JpaRepository<Phonetic, Long> {
    List<Phonetic> findByCode(String code);
    List<Phonetic> findAll();
}
