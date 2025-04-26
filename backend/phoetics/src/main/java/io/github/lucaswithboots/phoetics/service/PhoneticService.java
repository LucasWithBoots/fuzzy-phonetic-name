package io.github.lucaswithboots.phoetics.service;

import io.github.lucaswithboots.phoetics.model.Phonetic;

import java.util.List;

public interface PhoneticService {

    long count();

    Phonetic save(Phonetic phonetic);
    List<Phonetic> findByName(String name);
}
