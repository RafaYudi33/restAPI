package br.com.rafay.restAPI.Services;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.rafay.restAPI.Controllers.PersonController;
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
       var vo = DozerMapper.parseListObject(personRepository.findAll(), PersonDTO.class);   
       return vo; 
    }


    // private Person mockPerson(int i) {
    //      Person person = new Person(counter.incrementAndGet(), "Rafael" + i, "Martines" + i , "Avenida" + i, "Male" + i);
    //      return person; 
    // }


    public PersonDTO findById(Long id){ 
        logger.info("Finding one person!");
        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        var vo = DozerMapper.parseObject(entity, PersonDTO.class);
        try {
            vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return vo; 
    }


    public PersonDTO create (PersonDTO person){
        logger.info("Create one person"); 
        var entity = DozerMapper.parseObject(person, Person.class); 
        return DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class); 
    }

    public PersonDTO update (PersonDTO person){
        logger.info("Update one person!"); 



        Person entity = personRepository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); 



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
