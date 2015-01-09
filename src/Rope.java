import java.util.Timer;


/**
 * 
 * @author Max Highsmith
 * @version 11/18/2014
 *  
 *  Rope which Baboon objects travel across
 * 
 *
 */
public class Rope implements Runnable
{
	/**Time which thread will be delayed for */
	public final static int DELAY = 100;
	
	/** Queue of baboons waiting to cross the rope */
	BoundedQueue<Baboon> baboonsWaiting;
	
	/** Queue of baboons currently on the rope*/
	BoundedQueue<Baboon> baboonsOnRope = new BoundedQueue<Baboon>(Driver.NUMBEROFBABOONS);
	
	/** indicates which direction baboons currently on the rope are moving, true = left, false=right*/
	public boolean ropeDirection;
	
	/** tells if rope is empty */
	public boolean ropeIsEmpty = true;
	
	
	/** 
	 * 
	 * @param aQueue Queue of baboons waiting to cross bridge
	 */
	public Rope(BoundedQueue<Baboon> aQueue)
	{
		baboonsWaiting = aQueue;
		
	}
	
	
	/**
	 * checks if baboons are waiting to cross bridge.
	 * if baboons are waiting to cross bridge, pulls them across 
	 */
	public void run()
	{
		try
		{
			while(true)  // continually checks status of baboonsWaiting
			{
				if(!baboonsWaiting.isEmpty())
				{	   
					if (ropeIsEmpty || (baboonsWaiting.peek().getDirection() == ropeDirection))
					{
						Baboon temp = baboonsWaiting.remove();
						baboonsOnRope.add(temp);
						System.out.println("Baboon "+ temp.getName()+" has climbed on the rope");
						temp.crossBridge();
					if(ropeIsEmpty)
					{
						ropeIsEmpty = false;
						ropeDirection = temp.getDirection();
					}	
					}
					/*f baboon furthest along rope has been on rope for enough time, 
					rope removes the baboon  from queue baboonsOnRope*/
					if(baboonsOnRope.peek().getHasCrossed())   
					{
						baboonsOnRope.remove();	
						if(baboonsOnRope.isEmpty())
						{
							ropeIsEmpty = true;
						}
					}

					
				
				}
				Thread.sleep(DELAY);
				
			}
			
		}
		catch (InterruptedException exception)
		{
			System.out.println("error in rope");
		}
		
		
	}
	
}
