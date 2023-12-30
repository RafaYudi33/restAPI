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
       var dtos = DozerMapper.parseListObject(personRepository.findAll(), PersonDTO.class);   

        
        dtos.stream().forEach(p -> {
            try {
                p.add(
                    linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
       

       return dtos; 
    }
    

    public PersonDTO findById(Long id) throws Exception{ 
        logger.info("Finding one person!");
        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        var dto = DozerMapper.parseObject(entity, PersonDTO.class);
        try {
            dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return dto; 
    }


    public PersonDTO create (PersonDTO person) throws Exception{
        logger.info("Create one person"); 
        var entity = DozerMapper.parseObject(person, Person.class); 
        var dto = DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class); 

        try {
            dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return dto; 
    }

    public PersonDTO update (PersonDTO person) throws Exception{
        logger.info("Update one person!"); 

        Person entity = personRepository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); 

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());

        var dto = DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class);  

        try {
            dto.add(linkTo(methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return dto; 
    }

    public void delete (Long id) throws Exception{
        logger.info("delete one person"); 

        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        personRepository.delete(entity);
    }
    
}
