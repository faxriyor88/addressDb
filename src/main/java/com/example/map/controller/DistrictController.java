package com.example.map.controller;

import com.example.map.dtocarriers.DistrictCarrier;
import com.example.map.model.District;
import com.example.map.model.Region;
import com.example.map.repository.DistrictRepository;
import com.example.map.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
@RequestMapping("/district")
@RestController
public class DistrictController {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    RegionRepository regionRepository;

    @PostMapping("/post")
    public String insert(@RequestBody DistrictCarrier districtCarrier) {
        Optional<Region> optional = regionRepository.findById(districtCarrier.getRegion_id());
        if (optional.isPresent()) {
            District district = new District(districtCarrier.getDistrictname(), optional.get());
            districtRepository.save(district);
            return "Ma'lumot qo'shildi";
        } else {
            return "Bunday Region topilmadi !!!";
        }
    }

    @GetMapping("/get")
    public ArrayList<District> getAll() {
        return (ArrayList<District>) districtRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public District getId(@PathVariable Integer id) {
        Optional<District> optional = districtRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return new District();
        }
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Integer id, @RequestBody DistrictCarrier districtCarrier) {
        Optional<District> optional = districtRepository.findById(id);
        if (optional.isPresent()) {
            Optional<Region> optional1 = regionRepository.findById(districtCarrier.getRegion_id());
            if (optional1.isPresent()) {
                District district1 = optional.get();
                district1.setDistrictName(districtCarrier.getDistrictname());
                district1.setRegion(optional1.get());
                districtRepository.save(district1);
                return "Yangilandi !!!";
            } else {
                return "Bunday Region topilmadi !!!";
            }
        } else {
            return "Bunday District topilmadi  !!!";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<District> optional = districtRepository.findById(id);
        if (optional.isPresent()) {
            districtRepository.deleteById(id);
            return "O'chirildi !!!";
        } else {
            return "Bunday District topilmadi !!!";
        }
    }
}
