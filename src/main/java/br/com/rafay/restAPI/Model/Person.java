package br.com.rafay.restAPI.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private long id; 
    private String firstName; 
    private String lastName; 
    private String address; 
    private String gender; 

}
