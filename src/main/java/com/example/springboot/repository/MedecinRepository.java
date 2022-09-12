package com.example.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.Medecin;


@Repository
public interface MedecinRepository extends JpaRepository <Medecin, Long>{
	List<Medecin> findByNomMedContaining(String query);
}
