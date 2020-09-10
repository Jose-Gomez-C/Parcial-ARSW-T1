package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeException;
import edu.eci.arsw.api.primesrepo.service.PrimeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
public class PrimesController
{
	
	@Autowired
    PrimeService primeService;


    @RequestMapping( value = "/primes", method = GET )
    public ResponseEntity<?> getPrimes(){
    	
        return new ResponseEntity<>(primeService.getFoundPrimes(), HttpStatus.OK);
    }
    
    @RequestMapping( value = "/primes/add", method = POST )
    public  ResponseEntity<?> addPrime(@RequestBody FoundPrime foundPrime){
        try {
			primeService.addFoundPrime(foundPrime);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (PrimeException e) {
			return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    
    @RequestMapping( value = "/primes/{primeNumber}", method = GET)
    public ResponseEntity<?> getPrime(@PathVariable String primeNumber){
		try {
			return new ResponseEntity<>(primeService.getPrime(primeNumber),HttpStatus.CREATED);
		} catch (PrimeException e) {
			return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
    //TODO implement additional methods provided by PrimeService



}
