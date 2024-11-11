package com.apiparkeo.parkeo.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiparkeo.parkeo.Entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

    Optional<Client> findByCedula(Long cedula);

}
