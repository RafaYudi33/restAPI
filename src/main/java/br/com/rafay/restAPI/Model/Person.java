package br.com.rafay.restAPI.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "first_name",nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 80) 
    private String lastName; 

    @Column(nullable = false, length = 100)
    private String address; 

    @Column(nullable = false, length = 6)
    private String gender; 


}
