import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/** 
 * 
 * @author Originally from book
 * @author Modified by Max Highsmith
 * @version 11/18/2014
 *  
 *  A first-in, first-out bounded collection of objects. 
*/ 
public class BoundedQueue<E>
{ 
   /** 
       Constructs an empty queue. 
       @param capacity the maximum capacity of the queue 
   */ 
   public BoundedQueue(int capacity) 
   { 
      elements = new Object[capacity]; 
      head = 0; 
      tail = 0; 
      size = 0;
   } 

   /** 
   Removes the object at the head. 
   @return the object that has been removed from the queue
*/ 
public synchronized E peek() 
     throws InterruptedException
{ 
  while (size == 0) wait();
  E r = (E) elements[head]; 
  if (head == elements.length) 
     head = 0; 
  notifyAll();
  return r; 
} 
   
   /** 
       Removes the object at the head. 
       @return the object that has been removed from the queue
   */ 
   public synchronized E remove() 
         throws InterruptedException
   { 
      while (size == 0) wait();
      E r = (E) elements[head]; 
      head++;
      size--;
      if (head == elements.length) 
         head = 0; 
      notifyAll();
      return r; 
   } 

   /** 
       Appends an object at the tail. 
       @param newValue the object to be appended 
   */ 
   public synchronized void add(E newValue) 
         throws InterruptedException
   { 
	   while (size == elements.length) wait();
		   	elements[tail] = newValue; 
		   	tail++;
		   	size++;
      if (tail == elements.length) 
         tail = 0; 
      notifyAll();
   } 
   
   
   
   /** 
    * @return if the queue is full
    */
   public boolean isFull()
   {
	   return size == elements.length;
   }
   
   /**
    * @return  if the queue is empty
    */
   public boolean isEmpty()
   {
	   return size == 0;
   }

   
   /**
    * @return number of objects currently in queue
    */
   public int howManyInThere()
   {
	   return size;
   }
   
   /** array of objects contained in Queue*/
   private Object[] elements; 
   
   /** position of first element in queue */
   private int head;
   
   
   
   /** positions of last element in queue */
   private int tail; 
   
   /** pointer to element locations*/
   private int size;
   
   
   
}
