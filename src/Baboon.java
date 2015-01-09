import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 
 * @author Max Highsmith
 * @version 11/18/2014
 * 
 * Baboon object which crosses bridge 
 * 
 *
 *
 */


public class Baboon
{
	/** DELAY signifes the time it take the baboon to cross rope*/
	final static int CROSSINGDELAY = 4000;
	/** direction baboon is trying to cross  true = left, false = right*/
	boolean direction; // true = left, false= right
	/** indicates if baboon has crossed bridge*/
	boolean hasCrossed = false;
	/** name of baboon*/
	int name;	
	
	/** Action changes hasCrossed to true one Baboon has been on rope for CROSSINGDELAY seconds*/
	 ActionListener taskPerformer = new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	         hasCrossed = true;
	         System.out.println("Baboon "+name+ " has crossed");
	        
	      }
	  };
	  
	  /** Timer starts when baboon is on bridge*/
	  Timer timer = new Timer(CROSSINGDELAY, taskPerformer); 
	 
	
	/** Baboon constructor
	 * @param adirection  direction baboon aims to move
	 * @param aname  name of baboon
	 * */
	public Baboon(boolean adirection, int aname)
	{
		direction= adirection;
		name = aname;
	}
	
	/** @return  hasCrossed */
	public boolean getHasCrossed()
	{
		return hasCrossed;
	}
  
	/** @return  direction*/
	public boolean getDirection()
	{
		return direction;
	}
	
	/**
	 * 
	 * @return name
	 */
	public int getName()
	{
		return name;
	}
	
	
	/**causes baboon to begin countdown until it has sucesfully crossed bridge */
	public void crossBridge()
	{
		timer.setRepeats(false);
		timer.start();
	}
	
}
