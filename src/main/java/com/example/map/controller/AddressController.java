package com.example.map.controller;


import com.example.map.dtocarriers.AddressCarrier;
import com.example.map.model.*;
import com.example.map.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RequestMapping("/map")
@RestController
public class AddressController {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ContinentRepository continentRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    DistrictRepository districtRepository;

    @PostMapping("/post")
    public String insert(@RequestBody AddressCarrier addressCarrier) {
        Optional<District> district = districtRepository.findById(addressCarrier.getDistrict_id());
        if (district.isPresent()) {
            Address address = new Address(addressCarrier.getHomeNumber(), addressCarrier.getStreet(), district.get());
            addressRepository.save(address);
            return "Ma'lumot qo'shildi";
        } else {
            return "Bunday District topilmadi !!!";
        }
    }

    @GetMapping("/get")
    public ArrayList<Address> getAll() {
        return (ArrayList<Address>) addressRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Address getAll(@PathVariable Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            return optionalAddress.get();
        } else {
            return new Address();
        }
    }

    @PutMapping("/put/{id}")
    public String update(@PathVariable Integer id, @RequestBody AddressCarrier addressCarrier) {
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isPresent()) {
            Optional<District> district = districtRepository.findById(addressCarrier.getDistrict_id());
            if (district.isPresent()) {
                Address address = optional.get();
                address.setStreet(addressCarrier.getStreet());
                address.setHomeNumber(addressCarrier.getHomeNumber());
                address.setDistrict(district.get());
                addressRepository.save(address); // address table
                return "Yangilandi";
            } else {
                return "Bunday District topilmadi !!!";
            }
        } else {
            return "Bunday Address topilmadi !!!";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isPresent()) {
            addressRepository.deleteById(id);
            return "O'chirildi";
        } else {
            return "Bunday Adress topilmadi !!!";
        }
    }
}
