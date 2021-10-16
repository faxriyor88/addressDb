package com.example.map.controller;

import com.example.map.model.Continent;

import com.example.map.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/continent")
public class ContinentController {
    @Autowired
    ContinentRepository continentRepository;

    @PostMapping("/post")
    public String insert(@RequestBody Continent continent) {
        continentRepository.save(continent);
        return "Ma'lumot qo'shildi !!!";
    }

    @GetMapping("/get")
    public List<Continent> getAll() {
        return continentRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Continent getId(@PathVariable Integer id) {
        Optional<Continent> continent = continentRepository.findById(id);
        if (continent.isPresent()) {
            return continent.get();
        } else {
            return new Continent();
        }
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Integer id, @RequestBody Continent continent) {
        Optional<Continent> optional = continentRepository.findById(id);
        if (optional.isPresent()) {
            Continent continent1 = optional.get();
            continent1.setContinentname(continent.getContinentname());
            continentRepository.save(continent1);
            return "Yangilandi !!!";
        } else {
            return "Yangilanmadi !!!";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Continent> continent = continentRepository.findById(id);
        if (continent.isPresent()) {
            continentRepository.deleteById(id);
            return "O'chirildi !!!";
        } else {
            return "Bunday foydalanuvchi yo'q";
        }
    }

}
