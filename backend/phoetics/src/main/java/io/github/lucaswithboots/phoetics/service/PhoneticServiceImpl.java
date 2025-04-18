package io.github.lucaswithboots.phoetics.service;

import io.github.lucaswithboots.phoetics.model.Phonetic;
import io.github.lucaswithboots.phoetics.repository.PhoneticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneticServiceImpl implements PhoneticService {
    @Autowired
    private PhoneticRepository phoneticRepository;

    @Override
    public Phonetic save(Phonetic phonetic) {
        return phoneticRepository.save(phonetic);
    }
}
