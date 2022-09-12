package com.example.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "medecin")
public class Medecin {

	
	private long codeMed;
	private String nomMed;
	private String prenomMed;
	private String gradeMed;
	
	public Medecin() {
		
	}

	public Medecin(String nomMed, String prenomMed, String gradeMed) {
		this.nomMed = nomMed;
		this.prenomMed = prenomMed;
		this.gradeMed = gradeMed;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getCodeMed() {
		return codeMed;
	}
	public void setCodeMed(long codeMed) {
		this.codeMed = codeMed;
	}

	@Column(name= "nomMed")
	public String getNomMed() {
		return nomMed;
	}
	public void setNomMed(String nomMed) {
		this.nomMed = nomMed;
	}

	@Column(name="prenomMed")
	public String getPrenomMed() {
		return prenomMed;
	}
	public void setPrenomMed(String prenomMed) {
		this.prenomMed = prenomMed;
	}

	@Column(name="gradeMed")
	public String getGradeMed() {
		return gradeMed;
	}
	public void setGradeMed(String gradeMed) {
		this.gradeMed = gradeMed;
	}
	 
	@Override
	    public String toString() {
	        return "Medecin [code=" + codeMed + ", nom=" + nomMed + ", prenom=" + prenomMed + ", grade=" + gradeMed
	       + "]";
	    }
	
	
	
}
