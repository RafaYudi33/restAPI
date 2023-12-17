package br.com.rafay.restAPI.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafay.restAPI.Exceptions.ResourceNotFoundException;
import br.com.rafay.restAPI.Model.PersonDTO;
import br.com.rafay.restAPI.Repositories.PersonRepository;


@Service 
public class PersonServices {
    private final AtomicLong counter =  new AtomicLong(); 
    private Logger logger = Logger.getLogger(PersonServices.class.getName()); 

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDTO> findAll(){
        logger.info("finding one person!");
        return personRepository.findAll();  

    }


    // private Person mockPerson(int i) {
    //      Person person = new Person(counter.incrementAndGet(), "Rafael" + i, "Martines" + i , "Avenida" + i, "Male" + i);
    //      return person; 
    // }


    public PersonDTO findById(Long id){ 
        logger.info("Finding one person!");
        
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }


    public PersonDTO create (PersonDTO person){
        logger.info("Create one person"); 

        return personRepository.save(person); 
    }

    public PersonDTO update (PersonDTO person){
        logger.info("Update one person!"); 

        PersonDTO entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); 

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());

        return personRepository.save(entity); 
    }

    public void delete (Long id){
        logger.info("delete one person"); 

        PersonDTO entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(entity);
    }
    
}
