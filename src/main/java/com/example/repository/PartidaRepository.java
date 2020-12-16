package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Partida;

//Repositorio JPA para comunicarse con la base de datos
@Repository
public interface PartidaRepository extends JpaRepository<Partida, Integer> {
	

}
