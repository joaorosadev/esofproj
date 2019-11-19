package com.example.demo.repositories;

import com.example.demo.models.Estudante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudanteRepo extends CrudRepository<Estudante,Long> {
    Optional<Estudante> findByName(String name);
}
