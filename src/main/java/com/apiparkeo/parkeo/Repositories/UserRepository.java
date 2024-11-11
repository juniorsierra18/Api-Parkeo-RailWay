package com.apiparkeo.parkeo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiparkeo.parkeo.Entities.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

}
