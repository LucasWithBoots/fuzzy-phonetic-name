package io.github.lucaswithboots.phoetics.repository;

import io.github.lucaswithboots.phoetics.model.Phonetic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneticRepository extends JpaRepository<Phonetic, Long> {
}
