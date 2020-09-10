package edu.eci.arsw.primefinder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import edu.eci.arsw.mouseutils.MouseMovementMonitorExample;

public class PrimesControler extends Thread{
	private BigInteger a;
	private BigInteger b;
	private PrimesResultSet prs;
	private AtomicInteger itCount;
	private int numeroHilos;
	private boolean estado;

	private List<PrimeFinder> hilos;
	public PrimesControler(BigInteger _a, BigInteger _b, PrimesResultSet prs , int numeroHilos) {
		a = _a;
		b = _b;
		this.prs = prs;
		this.numeroHilos = numeroHilos;

		BigInteger numeroHilosBig = new BigInteger(Integer.toString(numeroHilos)); 
		hilos = new ArrayList<PrimeFinder>();
		BigInteger numeroProceso = b.divide(new BigInteger(Integer.toString(numeroHilos)));
		BigInteger numeroInicial = a;
		BigInteger numeroFinal = numeroProceso;
		PrimeFinder temporal;
		estado = false;
		for (int i =0 ; i < numeroHilos ; i++) {
			if (i+1 == numeroHilos) {
				temporal = new PrimeFinder(numeroInicial, numeroFinal.add((numeroFinal.mod(numeroHilosBig))), prs);

			}else {
				temporal = new PrimeFinder(numeroInicial, numeroFinal, prs);
			}
			hilos.add(temporal);
			System.out.println("Numero inicial " + numeroInicial + " Numero final "+ numeroFinal);
			numeroInicial = numeroInicial.add(numeroProceso);
			numeroFinal = numeroFinal.add(numeroProceso);

		}
	}
	@Override
	public void run() {
		
	}
}
