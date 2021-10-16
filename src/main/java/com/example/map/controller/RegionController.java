package com.example.map.controller;

import com.example.map.dtocarriers.RegionCarrier;
import com.example.map.model.Country;
import com.example.map.model.Region;
import com.example.map.repository.CountryRepository;
import com.example.map.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    CountryRepository countryRepository;
    @PostMapping("/post")
    public String insert(@RequestBody RegionCarrier regionCarrier) {
        Optional<Country> optional = countryRepository.findById(regionCarrier.getCountry_id());
        if (optional.isPresent()) {
            Region region = new Region(regionCarrier.getRegionname(), optional.get());
            regionRepository.save(region);
            return "Ma'lumot qo'shildi";
        } else {
            return "Bunday Country topilmadi !!!";
        }
    }
    @GetMapping("/get")
    public ArrayList<Region> getAll(){
        return (ArrayList<Region>) regionRepository.findAll();
    }
    @GetMapping("/get/{id}")
    public Region getId(@PathVariable Integer id){
        Optional<Region> optional=regionRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        else {
            return new Region();
        }
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Integer id,@RequestBody RegionCarrier regionCarrier) {
        Optional<Region> optional = regionRepository.findById(id);
        if (optional.isPresent()) {
            Optional<Country> optional1 = countryRepository.findById(regionCarrier.getCountry_id());
            if (optional1.isPresent()) {
                Region region1 = optional.get();
                region1.setRegionname(regionCarrier.getRegionname());
                region1.setCountry(optional1.get());
                regionRepository.save(region1);
                return "Yangilandi";
            } else {
                return "Bunday Country topilmadi !!!";
            }
        } else {
            return "Bunday Region topilmadi !!!";
        }
    }
     @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
         Optional<Region> optional=regionRepository.findById(id);
         if (optional.isPresent()){
             regionRepository.deleteById(id);
             return "O'chirildi !!!";
         }
         else {
             return "Bunday Region topilmadi !!!";
         }
     }
}
