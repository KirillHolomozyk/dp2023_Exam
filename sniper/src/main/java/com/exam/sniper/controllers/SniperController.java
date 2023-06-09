package com.exam.sniper.controllers;

import com.exam.sniper.entities.Sniper;
import com.exam.sniper.repositories.SniperRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
@RequestMapping("/api/snipers")
@Slf4j
public class SniperController {

    @Autowired
    SniperRepository sniperRepository;

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Sniper postSnipers(@RequestBody Sniper sniper){
        log.info("POST METHOD");
        sniperRepository.save(sniper);
        log.info("After POST METHOD - added {} sniper", sniper.getName());
        return sniper;
    }

    @GetMapping("/get")
    public List<Sniper> getSnipers(){
        List<Sniper> list;
        log.info("GET ALL METHOD");
        list = sniperRepository.findAll();
        log.info("After GET ALL METHOD - found {} snipers", list.size());
        return list;
    }

    @GetMapping("/get/{id}")
    public Sniper getSniperById(@PathVariable("id") long id){
        Sniper sniper = sniperRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found sniper with id " + id));
        log.info("GET BY ID METHOD");
        log.info("After GET BY ID METHOD - sniper with {} id was found", id);
        return sniper;
    }

    @PutMapping ("/update/{id}")
    public Sniper putSniper(@PathVariable(name = "id") long id, @RequestBody Sniper newSniper){
        log.info("PUT METHOD");
        Sniper updatedSniper = sniperRepository.findById(id)
                .orElseThrow(()->{
                    log.error("PUT METHOD was failed - not found sniper with id - {}", id);
                    return new ResourceAccessException("Not found sniper with id: " + id);
                });
        updatedSniper.setName(newSniper.getName());
        updatedSniper.setUnit(newSniper.getUnit());
        updatedSniper.setAccuracy(newSniper.getAccuracy());
        updatedSniper.setSalary(newSniper.getSalary());
        sniperRepository.save(updatedSniper);
        log.info("After PUT METHOD - sniper with {} id was changed", id);
        return updatedSniper;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSniper(@PathVariable(name = "id") long id){
        log.info("DELETE BY ID METHOD");
        Sniper deletedsniper = sniperRepository.findById(id)
                .orElseThrow(()->{
                    log.error("DELETE BY ID METHOD was failed - not found sniper with id - {}", id);
                    return new ResourceAccessException("Not found sniper with id: " + id);
                });
        sniperRepository.deleteById(id);
        log.info("After DELETE BY ID METHOD - {} sniper with {} id was deleted", deletedsniper.getName(), id);
    }

    @DeleteMapping("/delete")
    public void deleteSnipers(){
        List<Sniper> list;
        list = sniperRepository.findAll();
        log.info("DELETE ALL METHOD");
        sniperRepository.deleteAll();
        log.info("After DELETE ALL METHOD: {} sniper(s) was(were) deleted", list.size());
    }
}