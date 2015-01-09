 
/**

 * @author Max Highsmith
 * @version 11/18/2014
 *  
 *  Main class which runs the simulation
*/
public class Driver 
{
	/** Number of baboons to cross canyon */
	public static final int NUMBEROFBABOONS = 30;
	
	public static void main(String[] args) throws InterruptedException 
	{
		
		BoundedQueue<Baboon> baboonsWaiting = new BoundedQueue<Baboon>(NUMBEROFBABOONS);			
		Runnable baboonFactory = new BaboonFactory(baboonsWaiting);
		Runnable rope = new Rope(baboonsWaiting);
		Thread thread1 = new Thread(baboonFactory);
		Thread thread2 = new Thread(rope);
		thread1.start();
		thread2.start();
		
	}

}
