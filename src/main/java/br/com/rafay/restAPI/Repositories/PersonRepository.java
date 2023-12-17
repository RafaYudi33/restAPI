package br.com.rafay.restAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafay.restAPI.Model.PersonDTO;

@Repository
public interface PersonRepository extends JpaRepository<PersonDTO, Long>{
    
}
