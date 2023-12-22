package br.com.rafay.restAPI.Services;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafay.restAPI.DTOs.PersonDTO;
import br.com.rafay.restAPI.Exceptions.ResourceNotFoundException;
import br.com.rafay.restAPI.Mapper.DozerMapper;
import br.com.rafay.restAPI.Model.Person;
import br.com.rafay.restAPI.Repositories.PersonRepository;


@Service 
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName()); 

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDTO> findAll(){
        logger.info("finding one person!");
       return DozerMapper.parseListObject(personRepository.findAll(), PersonDTO.class);   
    }


    // private Person mockPerson(int i) {
    //      Person person = new Person(counter.incrementAndGet(), "Rafael" + i, "Martines" + i , "Avenida" + i, "Male" + i);
    //      return person; 
    // }


    public PersonDTO findById(Long id){ 
        logger.info("Finding one person!");
        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerMapper.parseObject(entity, PersonDTO.class);
    }


    public PersonDTO create (PersonDTO person){
        logger.info("Create one person"); 
        var entity = DozerMapper.parseObject(person, Person.class); 
        return DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class); 
    }

    public PersonDTO update (PersonDTO person){
        logger.info("Update one person!"); 



        Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); 



        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());



        return DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class);  
    }

    public void delete (Long id){
        logger.info("delete one person"); 

        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(entity);
    }
    
}
