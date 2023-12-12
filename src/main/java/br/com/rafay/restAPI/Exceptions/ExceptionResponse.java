package br.com.rafay.restAPI.Exceptions;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
public class ExceptionResponse implements Serializable {
    
    private static final long serialVersionUID = 1L; 

    private Date timestamp;
    private String message; 
    private String details; 
}
