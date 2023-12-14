package br.com.rafay.restAPI.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.rafay.restAPI.Model.Person;


@Service 
public class PersonServices {
    private final AtomicLong counter =  new AtomicLong(); 
    private Logger logger = Logger.getLogger(PersonServices.class.getName()); 


    public List<Person> findAll(){

        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            Person person = mockPerson(i); 
            persons.add(person); 
        }


        return persons; 

    }


    private Person mockPerson(int i) {
         Person person = new Person(counter.incrementAndGet(), "Rafael" + i, "Martines" + i , "Avenida" + i, "Male" + i);
         return person; 
    }


    public Person findById(String id){
        logger.info("Finding one person!");
        
        Person person = new Person(counter.incrementAndGet(), "Rafael", "Martines", "Avenida", "Male");

        return person; 
    }


    public Person create (Person person){
        logger.info("Create one person"); 

        return person; 
    }

    public Person update (Person person){
        logger.info("Create one person"); 

        return person; 
    }

}
