//Noshin Nisa
//nnisa
//Hours Spent: 10

/**
 * A generic linked list class that you will
 * write so that it implements the SimpleList<AnyType> 
 * and the SimpleQueue<AnyType> interfaces.
 */
import java.util.*;
public class MyList<AnyType> implements SimpleList<AnyType>, SimpleQueue<AnyType>
{
  private Node start;
  private Node last;
  private int length;
  
  /**
   * Determines if the queue has no elements
   * 
   * @return <code>true</code> if the queue is empty; <code>false</code> otherwise
   */
  public boolean isEmpty()
  {
    if (length == 0)
      return true;
    return false; 
  }
  
  /**
   * Inserts an element at the end of the queue
   * 
   * @param value the element to be inserted into the queue
   */
  public void enqueue(AnyType value)
  {
    if (length == 0)
    {
      start = new Node (value, null, null);
      last = start;
      length++;
    }
    else if (length >0)
    {
      last.next = new Node (value, null, last);
      last = last.next;
      length++;
    }
  }
  
  public int getSize()
  {
    return length;
  }
  
  /**
   * Retrieves and removes the first element of the queue
   * @return the element at the front of the original queue
   * @throws unchecked NoSuchElementException if the queue is empty
   */
  
  public AnyType dequeue()
  {
    if (length == 0)
    {
      throw new NoSuchElementException();
    }
    else if(length ==1)
    {
      AnyType first = start.data; 
      start = null;
      last = null;
      length--;
      return first;
    }
    else
    {
      AnyType first = start.data; 
      start = start.next;
      start.prev = null;
      length--;
      return first;
    }
  }
  
  /**
   * Retrieves, but does not remove, the first element of the queue
   * 
   * @return the element at the front of the original queue, or <code>null</code> if the queue is empty
   */
  
  public AnyType peek()
  {
    if(length == 0)
    {
      throw new NoSuchElementException();
    }
    Node first = start; 
    return first.data;
  }
  
  
  public Iterator<AnyType> iterator()
  {
    return new MyIterator();
  }
  
  
  private class MyIterator implements Iterator<AnyType>
  {
    Node current = start;
    public boolean hasNext()
    {
      if (current == null)
        return false;
      return true;
    }
    
    public AnyType next()
    {
      AnyType x = current.data;
      if (hasNext() == true)
      {
        x = current.data;
        current = current.next;
      }
      return x;
    }
    
    // we MUST implement this method since it is in the Iterator interface
    // but if we don't, really, we should tell the user that is the case
    // by throwing the appropriate exception rather than just having an empty { }
    public void remove()
    {
      throw new UnsupportedOperationException("it's hard; I'm tired");
    }
  }
  
  
  
  /*
   * [
   * Returns a <code>String</code> representation of this linked list
   * 
   * @return a string representation of this list
   */
  public String toString()
  {
    // use StringBuilder for these "result building" strings
    // to avoid creating O(n) Strings in creating the result
    StringBuilder s = new StringBuilder("[");  //String s = "[";
    for(Node curr = start; curr != null; curr = curr.next)
    {
      s.append(curr.data);  //s += curr.data;
      if (curr.next != null)
        s.append(" --> ");
    }
    s.append("]");  //s += "]";
    return s.toString();  //return s;
  }
  ////////////////////////////////////////////////////////
  
  /**
   * Removes the first element of the list that equals value.
   * 
   * @param value the element to remove
   * @return  <code>true</code> if the element was removed; <code>false</code> otherwise
   */
  public boolean remove(AnyType value)
  {
    Node curr1 = start;
    if (curr1.data.equals(value))
    {
      start = curr1.next;
      length--;
      return true;
    }
    else
    {
      for (Node curr = start; curr.next!=null; curr=curr.next)
      { 
        if (curr.data.equals(value))
        {
          curr.prev.next = curr.next;
          curr.next.prev = curr.prev;
          length--;
          return true;
        }
      }
      if (last.data.equals(value))
      {
        last.prev.next = null;
        last = last.prev;
        length--;
        
      }
    }
    return false;
  }
  
  
  public boolean add(AnyType value)
  {
    if (length == 0)
    {
      start = new Node (value, null, null);
      last = start;
      length++;
      return true;
    }
    else if (length >0)
    {
      last.next = new Node (value, null, last);
      last = last.next;
      length++;
    }
    return false; 
  }
  
  
  /**
   * a private inner class used by this List class
   */
  private class Node
  {
    private AnyType data;
    private Node next;
    private Node prev;
    
    private Node(AnyType data, Node next, Node prev)
    {
      this.data = data;
      this.next = next;
      this.prev = prev;
    }
  }
  
  
  //a main for testing/debugging
  public static void main(String[] args)
   {
   MyList<String> emptyList = new MyList<String>();
   MyList<String> list2 = new MyList<String>();
   list2.add("node1");list2.add("node2");
   System.out.println("initial list: " + list2);
   list2.remove("node2");
   System.out.println("removed node2: " +list2);
   list2.add("node2");
   System.out.println("added node2 [error if last not maintained]: " +list2);
   list2.remove("node1");
   System.out.println("removed node1: " +list2);
   list2.remove("node2");
   System.out.println("removed node2: " +list2);
   list2.add("test1");
   System.out.println("added test1: " +list2);
   list2.add("test2");
   System.out.println("added test2: " + list2);
   list2.add("test3");
   System.out.println("added test3: "+ list2);
   }
}
