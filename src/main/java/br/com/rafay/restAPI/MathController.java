package br.com.rafay.restAPI;

import java.lang.Math;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafay.restAPI.Exceptions.UnsuportedMathOperationException;


@RestController
public class MathController {

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum (
            @PathVariable(value = "numberOne") String numberOne, 
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
        
            if(!isNumeric(numberOne)|| !isNumeric(numberTwo)){
               throw new UnsuportedMathOperationException("Insira apenas valores numéricos");
            }
            

            return convertToDouble(numberOne) + convertToDouble(numberTwo); 
    }


    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sub (
            @PathVariable(value = "numberOne") String numberOne, 
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception{
            
            if(!isNumeric(numberOne)||!isNumeric(numberTwo)){
                throw new UnsuportedMathOperationException("Insira apenas valores numéricos"); 
            }

            return convertToDouble(numberOne) - convertToDouble(numberTwo); 
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}")
    public Double div(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
            if(!isNumeric(numberOne)||!isNumeric(numberTwo)){
                throw new UnsuportedMathOperationException("Insira apenas valores numéricos"); 
            }


            return convertToDouble(numberOne)/convertToDouble(numberTwo);
    }


    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo 
    ){


            if(!isNumeric(numberOne)||!isNumeric(numberTwo)){
                throw new UnsuportedMathOperationException("Insira apenas valores numéricos"); 
            }


            return convertToDouble(numberOne)*convertToDouble(numberTwo);
    }



    
    @RequestMapping(value = "/raizQuadrada/{number}")  
    public Double squareRoot(
            @PathVariable(value = "number") String number
    ){

            if(!isNumeric(number)){
                throw new UnsuportedMathOperationException("Insira apenas valores numéricos"); 
            }


            return Math.sqrt(convertToDouble(number));
            
    }

       
    @RequestMapping(value = "/media/{numberOne}/{numberTwo}")  
    public Double media(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){

            if(!isNumeric(numberOne)||!isNumeric(numberTwo)){
                throw new UnsuportedMathOperationException("Insira apenas valores numéricos"); 
            }


            return (convertToDouble(numberOne)+convertToDouble(numberTwo))/2;
            
    }

     
    private Double convertToDouble(String strNumber) {
        if(strNumber == null) return 0D; 


        String number = strNumber.replaceAll(",", "."); 
        if(isNumeric(number)){
            return Double.parseDouble(number); 
        }

        return 0D; 
    }

    private boolean isNumeric(String strNumber) {
       if(strNumber == null) return false;  
       String number = strNumber.replaceAll(",",".");  
       return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    } 

}
