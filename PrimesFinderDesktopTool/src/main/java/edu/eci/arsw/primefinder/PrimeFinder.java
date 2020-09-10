package edu.eci.arsw.primefinder;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

import edu.eci.arsw.math.MathUtilities;

public class PrimeFinder extends Thread{
    private BigInteger a;
    private BigInteger b;
    private PrimesResultSet prs;
    private AtomicInteger itCount;
    private boolean estado;
    private boolean fin;
    
	public PrimeFinder(BigInteger _a, BigInteger _b, PrimesResultSet prs) {
		a = _a;
		b = _b;
		this.prs = prs;
		itCount = new AtomicInteger(0);
		fin = false;
	}
	@Override
	public void run() {
		estado = true; 
        MathUtilities mt=new MathUtilities();
        BigInteger i = a;
        while (i.compareTo(b)<=0){
        	para();
            itCount.getAndIncrement();
            if (mt.isPrime(i)){
                prs.addPrime(i);
            }
            i=i.add(BigInteger.ONE);
        }
        fin = true;
        
	}
	public void para() {

		synchronized (this) {
			while(!estado) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}

	}
	public synchronized void aCorrer() {
		estado = true;
		notify();
	}
	public void setEstado(boolean status) {
		estado = status;
	}
	public boolean setFin() {
		return fin; 
	}
    /*    
	public static void findPrimes(BigInteger _a, BigInteger _b, PrimesResultSet prs){
            
                BigInteger a=_a;
                BigInteger b=_b;
                
                MathUtilities mt=new MathUtilities();
                
                int itCount=0;
            
                BigInteger i=a;
                while (i.compareTo(b)<=0){
                    itCount++;
                    if (mt.isPrime(i)){
                        prs.addPrime(i);
                    }
                    i=i.add(BigInteger.ONE);
                }
                
	}
	*/
}
