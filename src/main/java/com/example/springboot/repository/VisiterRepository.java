package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.Visiter;

@Repository
public interface VisiterRepository extends JpaRepository <Visiter, Long>{

}
