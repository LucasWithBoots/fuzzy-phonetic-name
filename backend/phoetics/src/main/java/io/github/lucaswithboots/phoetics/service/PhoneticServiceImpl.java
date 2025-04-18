package io.github.lucaswithboots.phoetics.service;

import io.github.lucaswithboots.phoetics.model.Phonetic;
import io.github.lucaswithboots.phoetics.repository.PhoneticRepository;
import mtfn.MetaphonePtBr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneticServiceImpl implements PhoneticService {
    @Autowired
    private PhoneticRepository phoneticRepository;

    @Override
    public Phonetic save(Phonetic phonetic) {
        return phoneticRepository.save(phonetic);
    }

    @Override
    public List<Phonetic> findByName(String name) {
        String code = new MetaphonePtBr(name).toString();
        return phoneticRepository.findByCode(code);
    }
}
