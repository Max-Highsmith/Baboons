import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

/** 
 * 
 * @author Max Highsmith
 * @version 11/18/2014
 * Creates Baboons on both sides of the canyon being crossed
 * 
 * */
public class BaboonFactory implements Runnable
{
	/** Time which thread will be delayed*/
	private final static int DELAY = 1000;
	
	/** Time to wait until next baboon is created*/
	private final static int TIMETOGENERATE = 1000;
	
	/** Array of baboons to be created*/
	private Baboon[] baboons;
	
	/**Array of baboons who are waiting to get on bridge */
	private BoundedQueue<Baboon> baboonsWaiting;
	
	/**Random number generator used for baboon arrival intervals */
	private Random random = new Random();
	
	/**Iterator used to indicate which baoon will be put in line to get on bridge*/
	private int i = 0;
	
	/** used for loop, indicates if a baboon of certain name has been created already */
	private int hasBeenCreated = 0;
	
	
	/**Timer used to keep baboon*/
	private Timer timer;
	
	/**Increments iterator*/
	 ActionListener factoryTaskPerformer = new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	         i++;
	         
	         
	      }
	 };
	
	
	 /**
	  * 
	  * @param aQueue queue of baboons waiting to get on rope
	  */
	public BaboonFactory(BoundedQueue<Baboon> aQueue)
	{
		baboonsWaiting = aQueue;
	}

	
	  /**
	   * 
	   * @param iterator for baboon creation
	   * @return returns true if baboon of name 'i' has already been created 
	   */
	public boolean doneAlready(int i)
	{
		if(i < hasBeenCreated)
			return true;
		else 
		{
			hasBeenCreated ++;
			return false;
		}
	}
	   
	
	/**
	 * Creates baboons after random intervals of time have passed
	 */
	public void run()
	{
		try
		{
			
			baboons = new Baboon[Driver.NUMBEROFBABOONS];
			while(i<Driver.NUMBEROFBABOONS-1)
			{
				if(!doneAlready(i))
				{
					baboons[i] = new Baboon(random.nextBoolean(),i);
					if(baboons[i].getDirection())
					{
						System.out.println("Baboon "+i+" is trying to travel to the left");
					}
					else
					{
						System.out.println("Baboon "+ i  +" is trying to travel to the right");
					}
					baboonsWaiting.add(baboons[i]);
					//Causes timer to take 1-8 seconds to trigger activity
					timer = new Timer(TIMETOGENERATE*(1+ (int) (random.nextFloat() * 7)), factoryTaskPerformer);
					//
					timer.setRepeats(false);
					timer.start();
					Thread.sleep(DELAY);
				}
			}
			
		}
		catch(InterruptedException exception)
		{
			System.out.println("error in factory");
		}

	}
}
