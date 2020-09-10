package edu.eci.arsw.primefinder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;

public class PrimesControler extends Thread{
	private BigInteger a;
	private BigInteger b;
	private PrimesResultSet prs;
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
		for (int i =0 ; i < this.numeroHilos ; i++) {
			if (i+1 == this.numeroHilos) {
				temporal = new PrimeFinder(numeroInicial, numeroFinal.add((numeroFinal.mod(numeroHilosBig))), prs);

			}else {
				temporal = new PrimeFinder(numeroInicial, numeroFinal, prs);
			}
			hilos.add(temporal);
			//System.out.println("Numero inicial " + numeroInicial + " Numero final "+ numeroFinal);
			numeroInicial = numeroInicial.add(numeroProceso);
			numeroFinal = numeroFinal.add(numeroProceso);

		}
	}
	@Override
	public void run() {
		//System.out.println("arranque");
		for(PrimeFinder hilo : hilos) {
			hilo.start();
			estado = true;
		}
		boolean corriendo = true; 
		while(corriendo) {
			//System.out.println(prs.getPrimes());
			if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() == 10000) {
				//System.out.println(MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement());
				for(PrimeFinder hilo : hilos) {
					if(estado) {
						hilo.setEstado(false);
						//System.out.println("Pare");
					}else {
						hilo.aCorrer();
						//System.out.println("Corriendo");
					}
				}
				if(estado) {
					estado = false;
				}else {
					estado = true;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int numerHilosFinalizados = 0;
			for (PrimeFinder hilo : hilos) {
				if (hilo.setFin()){
					numerHilosFinalizados++;
				}
			}
			if (numerHilosFinalizados == hilos.size()) {
				corriendo = false; 
			}
		}
		System.out.println("Prime numbers found:");
		System.out.println(prs.getPrimes());
	}
}
