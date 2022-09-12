package com.example.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Visiter;
import com.example.springboot.repository.VisiterRepository;

@RestController
@RequestMapping("/api/visiter")
public class VisiterController {

	@Autowired
	private VisiterRepository visiterRepository;
	
	//get all visite
			@GetMapping("/get_all_visite")
			public List<Visiter> getAllVisite(){
				return this.visiterRepository.findAll();
			}
	
	//get visite by id
			 @GetMapping("/get_visite/{id}")
			 public ResponseEntity<Visiter> getVisiteById(@PathVariable(value = "id") Long id)
			    throws ResourceNotFoundException {
			    Visiter vis = visiterRepository.findById(id)
			    .orElseThrow(() -> new ResourceNotFoundException("Visite not found for this id :: " + id));
			        return ResponseEntity.ok().body(vis);
			    }
	//create visite
			 @PostMapping("/create_visite")
			 public Visiter createVisite(@RequestBody Visiter vis) {
			        return this.visiterRepository.save(vis);
			    }
			 
	//update visite
			 @PutMapping("/update_visite/{id}")
			 public ResponseEntity<Visiter> updateVisite(@PathVariable(value = "id") Long id,
			         @RequestBody Visiter visiteDetails) throws ResourceNotFoundException {
				 	Visiter vis = visiterRepository.findById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Visite not found for this id :: " + id));

			        vis.setDateVis(visiteDetails.getDateVis());
			        final Visiter updatedVis = this.visiterRepository.save(vis);
			        return ResponseEntity.ok(updatedVis);
			    }
			 
	//delete visite
			 @DeleteMapping("/delete_visite/{id}")
			 public Map<String, Boolean> deleteVisite(@PathVariable(value = "id") Long id)
			         throws ResourceNotFoundException {
			        Visiter vis = visiterRepository.findById(id)
			       .orElseThrow(() -> new ResourceNotFoundException("Visite not found for this id :: " + id));

			        this.visiterRepository.delete(vis);
			        Map<String, Boolean> response = new HashMap<>();
			        response.put("deleted", Boolean.TRUE);
			        return response;
			    }
}
