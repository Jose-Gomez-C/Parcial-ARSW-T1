package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@Service
public class PrimeServiceStub implements PrimeService
{
	private List<FoundPrime> numerosPrimos;
	public PrimeServiceStub() {
		this.numerosPrimos = new CopyOnWriteArrayList<>();
		numerosPrimos.add(new FoundPrime("prueba", "2"));
		numerosPrimos.add(new FoundPrime("prueba1", "3"));
		numerosPrimos.add(new FoundPrime("prueba2", "5"));
		numerosPrimos.add(new FoundPrime("prueba3", "7"));
		numerosPrimos.add(new FoundPrime("prueba4", "11"));
		numerosPrimos.add(new FoundPrime("prueba5", "13"));
		numerosPrimos.add(new FoundPrime("prueba6", "17"));
	}
	@Override
	public void addFoundPrime( FoundPrime foundPrime ) throws PrimeException{
		for(FoundPrime i :numerosPrimos) {
			if(i.getPrime().equals(foundPrime.getPrime())) {
				throw new PrimeException("El prime ya esta registrado");
			}
		}
		numerosPrimos.add(foundPrime);
	}

	@Override
	public List<FoundPrime> getFoundPrimes(){ 
		return numerosPrimos;
	}

	@Override
	public FoundPrime getPrime(String prime) throws PrimeException{
		FoundPrime respuesta = null;
		try {
			int priem = Integer.parseInt(prime);
			boolean encontrado = true;
			for(int i=0; i < numerosPrimos.size() && encontrado; i++) {
				if(numerosPrimos.get(i).getPrime().equals(prime)){
					respuesta=numerosPrimos.get(i);
				}
			}
		} catch (Exception e) {
			throw new PrimeException("No es un numero");
		}
		if (respuesta == null) {
			throw new PrimeException("Numero primo no encontrado");
		}
		return respuesta;
	}
}

