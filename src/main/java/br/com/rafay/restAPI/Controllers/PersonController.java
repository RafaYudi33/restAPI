package br.com.rafay.restAPI.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafay.restAPI.Model.PersonDTO;
import br.com.rafay.restAPI.Services.PersonServices;



@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices personServices;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE  )    
    public List<PersonDTO> findByAll(){

        return personServices.findAll(); 
    }



    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE  )
    public PersonDTO findById(@PathVariable("id") Long id) throws Exception{

        return personServices.findById(id); 

    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public PersonDTO create(@RequestBody PersonDTO person){

        return personServices.create(person); 

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE  )
    public PersonDTO update(@RequestBody PersonDTO person){
        return personServices.update(person); 

    }

    @DeleteMapping(value = "/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        personServices.delete(id); 
        return ResponseEntity.noContent().build();
    }



}
