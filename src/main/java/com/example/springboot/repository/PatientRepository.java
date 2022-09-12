package com.example.springboot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.model.Patient;

public interface PatientRepository extends JpaRepository <Patient, Long> {
	List<Patient> findByNomPatContaining(String query);
}
