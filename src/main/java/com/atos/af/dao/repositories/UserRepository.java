package com.atos.af.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atos.af.dao.entities.Utilisateur;

public interface UserRepository extends JpaRepository<Utilisateur, Long>{

}
