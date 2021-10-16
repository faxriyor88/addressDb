package com.example.map.controller;

import com.example.map.dtocarriers.CountryCarrier;
import com.example.map.model.Continent;
import com.example.map.model.Country;
import com.example.map.repository.ContinentRepository;
import com.example.map.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
@RequestMapping("/country")
@RestController
public class CountryController {
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    ContinentRepository continentRepository;
    @PostMapping("/post")
    public String insert(@RequestBody CountryCarrier countryCarrier) {
        Optional<Continent> optional = continentRepository.findById(countryCarrier.getContinent_id());
        if (optional.isPresent()) {
            Country country = new Country(countryCarrier.getCountryname(), optional.get());
            countryRepository.save(country);
            return "Ma'lumot qo'shildi";
        } else {
            return "Bunday Continent topilmadi !!!";
        }
    }
    @GetMapping("/get")
    public ArrayList<Country> getAll(){
        return (ArrayList<Country>) countryRepository.findAll();
    }
    @GetMapping("/get/{id}")
    public Country getId(@PathVariable Integer id){
        Optional<Country> optional=countryRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        else {
            return new Country();
        }
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Integer id,@RequestBody CountryCarrier countryCarrier){
        Optional<Country> optional=countryRepository.findById(id);
        if (optional.isPresent()){
            Optional<Continent> optional1=continentRepository.findById(countryCarrier.getContinent_id());
            if(optional1.isPresent()){
            Country country1=optional.get();
            country1.setCountryname(countryCarrier.getCountryname());
            country1.setContinent(optional1.get());
            countryRepository.save(country1);
            return "Yangilandi !!!";
        }else {
            return "Bunday Continent topilmadi !!!";}
        }
        else {
            return "Bunday Country topilmadi !!!";
        }
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Optional<Country> optional=countryRepository.findById(id);
        if (optional.isPresent()){
            countryRepository.deleteById(id);
            return "O'chirildi !!!";
        }
        else {
            return "Bunday Country topilmadi !!!";
        }
    }
}
