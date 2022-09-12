package com.example.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Patient;
import com.example.springboot.repository.PatientRepository;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;
	
	//get all patient
	 @GetMapping("/get_all_patient")
		public ResponseEntity<List<Patient>> getAllPatient(@RequestParam(required = false) String query) {
			try {
				List<Patient> pat = new ArrayList<Patient>();
				if (query == null)
					patientRepository.findAll().forEach(pat::add);
				else
					patientRepository.findByNomPatContaining(query).forEach(pat::add);
				if (pat.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(pat, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	//get patient by id
		 @GetMapping("/get_patient/{id}")
		 public ResponseEntity<Patient> getPatientById(@PathVariable(value = "id") Long codePat)
		    throws ResourceNotFoundException {
		    Patient pat = patientRepository.findById(codePat)
		    .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + codePat));
		        return ResponseEntity.ok().body(pat);
		    }
		 
	//create patient
		 @PostMapping("/create_patient")
		 public Patient createPatient(@RequestBody Patient pat) {
		        return this.patientRepository.save(pat);
		    }
		 
	//update patient
		 @PutMapping("/update_patient/{id}")
		 public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id") Long codePat,
		         @RequestBody Patient patientDetails) throws ResourceNotFoundException {
			 	Patient pat = patientRepository.findById(codePat)
		        .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + codePat));

		        pat.setNomPat(patientDetails.getNomPat());
		        pat.setPrenomPat(patientDetails.getPrenomPat());
		        pat.setSexePat(patientDetails.getSexePat());
		        pat.setAdressePat(patientDetails.getAdressePat());
		        final Patient updatedPat = this.patientRepository.save(pat);
		        return ResponseEntity.ok(updatedPat);
		    }
		 
	//delete patient
		 @DeleteMapping("/delete_patient/{id}")
		 public Map<String, Boolean> deletePatient(@PathVariable(value = "id") Long codePat)
		         throws ResourceNotFoundException {
		        Patient pat = patientRepository.findById(codePat)
		       .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + codePat));

		        this.patientRepository.delete(pat);
		        Map<String, Boolean> response = new HashMap<>();
		        response.put("deleted", Boolean.TRUE);
		        return response;
		    }
}
