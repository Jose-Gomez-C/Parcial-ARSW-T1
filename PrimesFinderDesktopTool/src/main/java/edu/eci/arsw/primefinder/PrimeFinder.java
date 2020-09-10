package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimeFinder extends Thread{
    private BigInteger a;
    private BigInteger b;
    private PrimesResultSet prs;
    private AtomicInteger itCount;
    private boolean estado;
    
	public PrimeFinder(BigInteger _a, BigInteger _b, PrimesResultSet prs) {
		a = _a;
		b = _b;
		this.prs = prs;
		itCount = new AtomicInteger(0);
	}
	@Override
	public void run() {
		estado = true; 
        MathUtilities mt=new MathUtilities();
        BigInteger i = a;
        while (i.compareTo(b)<=0){
        	
            itCount.getAndIncrement();
            if (mt.isPrime(i)){
                prs.addPrime(i);
            }
            i=i.add(BigInteger.ONE);
        }
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
