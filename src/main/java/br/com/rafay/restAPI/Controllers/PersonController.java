package br.com.rafay.restAPI.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafay.restAPI.Model.Person;
import br.com.rafay.restAPI.Services.PersonServices;



@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices personServices;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE  )    
    public List<Person> findByAll(){

        return personServices.findAll(); 
    }



    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE  )
    public Person findById(@PathVariable("id") String id) throws Exception{

        return personServices.findById(id); 

    }
    
    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE  )
    public Person create(@RequestBody Person person){

        return personServices.create(person); 

    }

    @RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE  )
    public Person update(@RequestBody Person person){

        return personServices.create(person); 

    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable(value = "id") String id){

        personServices.delete(id); 

    }



}
