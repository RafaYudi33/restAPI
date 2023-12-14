package br.com.rafay.restAPI;

import java.lang.Math;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafay.restAPI.Exceptions.UnsuportedMathOperationException;
import br.com.rafay.restAPI.Utils.MathOperations;
import br.com.rafay.restAPI.Utils.Utils;


@RestController
public class MathController {

    @Autowired
    MathOperations mathOperations; 

    @Autowired
    Utils utils;


    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum (
            @PathVariable(value = "numberOne") String numberOne, 
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
        
            if(!utils.isNumeric(numberOne)||(!utils.isNumeric(numberTwo))){
               throw new UnsuportedMathOperationException("Insira apenas valores numéricos");
            }
            

            return mathOperations.sum(utils.convertToDouble(numberOne), utils.convertToDouble(numberTwo)); 
    }


    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sub (
            @PathVariable(value = "numberOne") String numberOne, 
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
            
            
            if(!utils.isNumeric(numberOne)||(!utils.isNumeric(numberTwo))){
               throw new UnsuportedMathOperationException("Insira apenas valores numéricos");
            }
            

            return mathOperations.sub(utils.convertToDouble(numberOne), utils.convertToDouble(numberTwo)); 
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}")
    public Double div(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
            
            if(!utils.isNumeric(numberOne)||(!utils.isNumeric(numberTwo))){
               throw new UnsuportedMathOperationException("Insira apenas valores numéricos");
            }
            

            return mathOperations.div(utils.convertToDouble(numberOne), utils.convertToDouble(numberTwo)); 
    }


    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo 
    ){


            
            if(!utils.isNumeric(numberOne)||(!utils.isNumeric(numberTwo))){
               throw new UnsuportedMathOperationException("Insira apenas valores numéricos");
            }
            

            return mathOperations.mult(utils.convertToDouble(numberOne), utils.convertToDouble(numberTwo)); 
    }



    
    @RequestMapping(value = "/sqrt/{number}")  
    public Double squareRoot(
            @PathVariable(value = "number") String number
    ){

            
            if(!utils.isNumeric(number)){
               throw new UnsuportedMathOperationException("Insira apenas valores numéricos");
            }
            

            return mathOperations.squareRoot(utils.convertToDouble(number)); 
            
    }

       
    @RequestMapping(value = "/media/{numberOne}/{numberTwo}")  
    public Double media(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){

            
            if(!utils.isNumeric(numberOne)||(!utils.isNumeric(numberTwo))){
               throw new UnsuportedMathOperationException("Insira apenas valores numéricos");
            }
            

            return mathOperations.media(utils.convertToDouble(numberOne), utils.convertToDouble(numberTwo)); 
            
    }

    

}
