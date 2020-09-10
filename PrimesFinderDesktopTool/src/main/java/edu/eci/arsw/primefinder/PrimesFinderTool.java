package edu.eci.arsw.primefinder;

import java.math.BigInteger;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;

public class PrimesFinderTool {

	public static void main(String[] args) {

		int maxPrim=1000;
		boolean apagados = true;
		PrimesResultSet prs=new PrimesResultSet("john");
		PrimesControler controlador = new PrimesControler(new BigInteger("1"), new BigInteger("10000"), prs, 4);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (apagados) {
			if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement()>10000){
				//System.out.println("gg");
				apagados = false; 
				controlador.start();
			}
		}

		//PrimeFinder.findPrimes(new BigInteger("1"), new BigInteger("10000"), prs);

		//System.out.println("Prime numbers found:");
		//System.out.println(prs.getPrimes());


		/*while(task_not_finished){
                try {
                    //check every 10ms if the idle status (10 seconds without mouse
                    //activity) was reached. 
                    Thread.sleep(10);
                    if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement()>10000){
                        System.out.println("Idle CPU ");
                    }
                    else{
                        System.out.println("User working again!");
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/





	}

}


