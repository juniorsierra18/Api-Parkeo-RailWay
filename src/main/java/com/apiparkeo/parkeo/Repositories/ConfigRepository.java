package com.apiparkeo.parkeo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiparkeo.parkeo.Entities.Configuration;

public interface ConfigRepository extends JpaRepository<Configuration, Long>{

}
