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

import com.example.springboot.repository.MedecinRepository;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Medecin;

@RestController
@RequestMapping("/api/medecin")
public class MedecinController {

	@Autowired
	private MedecinRepository medecinRepository;
	
	//get all medecin
		 @GetMapping("/get_all_medecin")
			public ResponseEntity<List<Medecin>> getAllMedecin(@RequestParam(required = false) String nom) {
				try {
					List<Medecin> med = new ArrayList<Medecin>();
					if (nom == null)
						medecinRepository.findAll().forEach(med::add);
					else
						medecinRepository.findByNomMedContaining(nom).forEach(med::add);
					if (med.isEmpty()) {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
					return new ResponseEntity<>(med, HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
	
	//get medecin by id
	 @GetMapping("/get_medecin/{id}")
	    public ResponseEntity<Medecin> getMedecinById(@PathVariable(value = "id") Long codeMed)
	        throws ResourceNotFoundException {
	        Medecin med = medecinRepository.findById(codeMed)
	          .orElseThrow(() -> new ResourceNotFoundException("Medecin not found for this id :: " + codeMed));
	        return ResponseEntity.ok().body(med);
	    }
	
	 //create medecin
	 @PostMapping("/create_medecin")
	    public Medecin createMedecin(@RequestBody Medecin med) {
	        return this.medecinRepository.save(med);
	    }
	
	 //update medecin
	 @PutMapping("/update_medecin/{id}")
	    public ResponseEntity<Medecin> updateMedecin(@PathVariable(value = "id") Long codeMed,
	         @RequestBody Medecin medecinDetails) throws ResourceNotFoundException {
		 	Medecin med = medecinRepository.findById(codeMed)
	        .orElseThrow(() -> new ResourceNotFoundException("Medecin not found for this id :: " + codeMed));

	        med.setNomMed(medecinDetails.getNomMed());
	        med.setPrenomMed(medecinDetails.getPrenomMed());
	        med.setGradeMed(medecinDetails.getGradeMed());
	        final Medecin updatedMed = this.medecinRepository.save(med);
	        return ResponseEntity.ok(updatedMed);
	    }
	 
	 //delete medecin
	 @DeleteMapping("/delete_medecin/{id}")
	    public Map<String, Boolean> deleteMedecin(@PathVariable(value = "id") Long codeMed)
	         throws ResourceNotFoundException {
	        Medecin med = medecinRepository.findById(codeMed)
	       .orElseThrow(() -> new ResourceNotFoundException("Medecin not found for this id :: " + codeMed));

	        this.medecinRepository.delete(med);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
}
