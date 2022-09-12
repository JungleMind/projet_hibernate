package com.example.springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "visiter")
public class Visiter {

	private long id;
	private long codeMed;
	private long codePat;
	private Date dateVis;
	
	
	public Visiter() {
		
	}
	public Visiter(long codeMed, long codePat, Date dateVis) {
		this.codeMed = codeMed;
		this.codePat = codePat;
		this.dateVis = dateVis;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name= "codeMed")
	public long getCodeMed() {
		return codeMed;
	}
	public void setCodeMed(long codeMed) {
		this.codeMed = codeMed;
	}
	
	@Column(name= "codePat")
	public long getCodePat() {
		return codePat;
	}
	public void setCodePat(long codePat) {
		this.codePat = codePat;
	}
	
	@Column(name= "dateVis")
	public Date getDateVis() {
		return dateVis;
	}
	public void setDateVis(Date dateVis) {
		this.dateVis = dateVis;
	}
	
	@Override
    public String toString() {
        return "Visiter [id=" + id + ", codeMed=" + codeMed + ", codePat=" + codePat + ", date=" + dateVis
       + "]";
    }

	
}
