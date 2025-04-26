package io.github.lucaswithboots.phoetics.service;

import io.github.lucaswithboots.phoetics.model.Phonetic;
import io.github.lucaswithboots.phoetics.repository.PhoneticRepository;
import io.github.lucaswithboots.phoetics.utils.PhoneticUtils;
import mtfn.MetaphonePtBr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneticServiceImpl implements PhoneticService {
    @Autowired
    private PhoneticRepository phoneticRepository;

    @Override
    public Phonetic save(Phonetic phonetic) {
        phonetic.setCode(phonetic.getCode().replaceAll("\\s+", ""));
        return phoneticRepository.save(phonetic);
    }

    @Override
    public List<Phonetic> findByName(String name) {
        String inputCode = new MetaphonePtBr(name).toString().replaceAll("\\s+", "");

        // Busca exata primeiro
        List<Phonetic> exactMatches = phoneticRepository.findByCode(inputCode);
        if (!exactMatches.isEmpty()) {
            return exactMatches;
        }

        // Se não encontrar, busca por similaridade
        return phoneticRepository.findAll().stream()
                .filter(p -> PhoneticUtils.isSimilar(inputCode, p.getCode()))
                .collect(Collectors.toList());
    }
}