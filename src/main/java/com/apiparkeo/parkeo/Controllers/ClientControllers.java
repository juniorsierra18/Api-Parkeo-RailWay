package com.apiparkeo.parkeo.Controllers;

import java.util.List;

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

import com.apiparkeo.parkeo.Entities.Client;
import com.apiparkeo.parkeo.Repositories.ClientRepository;

@RestController
@RequestMapping("/apiparkeo")
@CrossOrigin(origins = "http://localhost:5173/")
public class ClientControllers {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @GetMapping("/clients/{id}")
    public Client getClientById(@PathVariable Long id){
        return clientRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No Client found with ID: " + id));
    }

    @GetMapping("/clients/cedula/{cedula}")
    public Client getClientByCedula(@PathVariable Long cedula) {
        return clientRepository.findByCedula(cedula)
            .orElseThrow(() -> new RuntimeException("No Client found with cedula: " + cedula));
    }

    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client){
        return clientRepository.save(client);
    }

    @PutMapping("/clients/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client detailClient){
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No Client found with ID: " + id));

        // Verifica si los valores son diferentes de null antes de asignarlos
        if (detailClient.getCedula() != null) {
            client.setCedula(detailClient.getCedula());
        }
        if (detailClient.getMatricula() != null) {
            client.setMatricula(detailClient.getMatricula());
        }

        client.setPrecio(detailClient.getPrecio());

        client.setEstado(detailClient.getEstado());

        return clientRepository.save(client);
    }

    @DeleteMapping("/clients/{id}")
    public String deleteClient(@PathVariable Long id){
        Client client = clientRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No Client found with ID: " + id));

        clientRepository.delete(client);
        return "The Client with ID " + id + " was removed.";
    }

    @DeleteMapping("/clients/cedula/{cedula}")
    public String deleteClientByCedula(@PathVariable Long cedula) {
        Client client = clientRepository.findByCedula(cedula)
            .orElseThrow(() -> new RuntimeException("No Client found with cedula: " + cedula));

        clientRepository.delete(client);
        return "The Client with cedula " + cedula + " was removed.";
    }
}
