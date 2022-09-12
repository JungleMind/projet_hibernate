package com.example.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "patient")
public class Patient {

	private long codePat;
	private String nomPat;
	private String prenomPat;
	private String sexePat;
	private String adressePat;
	
	
	public Patient() {
		
	}
	public Patient(String nomPat, String prenomPat, String sexePat, String adressePat) {
		this.nomPat = nomPat;
		this.prenomPat = prenomPat;
		this.sexePat = sexePat;
		this.adressePat = adressePat;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getCodePat() {
		return codePat;
	}
	public void setCodePat(long codePat) {
		this.codePat = codePat;
	}
	@Column(name= "nomPat")
	public String getNomPat() {
		return nomPat;
	}
	public void setNomPat(String nomPat) {
		this.nomPat = nomPat;
	}
	
	@Column(name= "prenomPat")
	public String getPrenomPat() {
		return prenomPat;
	}
	public void setPrenomPat(String prenomPat) {
		this.prenomPat = prenomPat;
	}
	
	@Column(name= "sexePat")
	public String getSexePat() {
		return sexePat;
	}
	public void setSexePat(String sexePat) {
		this.sexePat = sexePat;
	}
	
	@Column(name= "adressePat")
	public String getAdressePat() {
		return adressePat;
	}
	public void setAdressePat(String adressePat) {
		this.adressePat = adressePat;
	}
	
	@Override
    public String toString() {
        return "Patient [code=" + codePat + ", nom=" + nomPat + ", prenom=" + prenomPat + ", sexe=" + sexePat +", adresse="+adressePat
       + "]";
    }
	
}
