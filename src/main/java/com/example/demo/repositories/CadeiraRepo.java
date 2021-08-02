package com.example.demo.repositories;

import com.example.demo.models.Cadeira;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CadeiraRepo extends CrudRepository<Cadeira,Long> {
    Optional<Cadeira> findByNome(String name);
}
