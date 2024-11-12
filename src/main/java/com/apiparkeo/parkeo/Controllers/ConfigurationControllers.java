package com.apiparkeo.parkeo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiparkeo.parkeo.Entities.Configuration;
import com.apiparkeo.parkeo.Repositories.ConfigRepository;

@RestController
@RequestMapping("/apiparkeo")
@CrossOrigin(origins = "http://localhost:5173/")
public class ConfigurationControllers {
    @Autowired
    private ConfigRepository configRepository;

    @GetMapping("/config/{id}")
    public Configuration getConfigById(@PathVariable Long id){
        return configRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No Config found with ID: " + id));
    }

    @PostMapping("/config")
    public Configuration createConfig(@RequestBody Configuration configuration){
        return configRepository.save(configuration);
    }
    
    @PutMapping("/config/{id}")
    public Configuration updateUser(@PathVariable Long id, @RequestBody Configuration detailConfig){
        Configuration configuration = configRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No Config found with ID: " + id));

        configuration.setSlot(detailConfig.getSlot());
        configuration.setPrecio(detailConfig.getPrecio());

        return configRepository.save(configuration);
    }

    @DeleteMapping("/config/{id}")
    public String deleteUser(@PathVariable Long id){
        Configuration configuration = configRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No Config found with ID: " + id));

        configRepository.delete(configuration);
        return "The Config with ID " + id + " was removed.";
    }
}
