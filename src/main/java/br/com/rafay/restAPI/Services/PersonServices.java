package br.com.rafay.restAPI.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafay.restAPI.Exceptions.ResourceNotFoundException;
import br.com.rafay.restAPI.Model.Person;
import br.com.rafay.restAPI.Repositories.PersonRepository;


@Service 
public class PersonServices {
    private final AtomicLong counter =  new AtomicLong(); 
    private Logger logger = Logger.getLogger(PersonServices.class.getName()); 

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll(){
        logger.info("finding one person!");
        return personRepository.findAll();  

    }


    // private Person mockPerson(int i) {
    //      Person person = new Person(counter.incrementAndGet(), "Rafael" + i, "Martines" + i , "Avenida" + i, "Male" + i);
    //      return person; 
    // }


    public Person findById(Long id){ 
        logger.info("Finding one person!");
        
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }


    public Person create (Person person){
        logger.info("Create one person"); 

        return personRepository.save(person); 
    }

    public Person update (Person person){
        logger.info("Update one person!"); 

        Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); 

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());

        return personRepository.save(entity); 
    }

    public void delete (Long id){
        logger.info("delete one person"); 

        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(entity);
    }
    
}
