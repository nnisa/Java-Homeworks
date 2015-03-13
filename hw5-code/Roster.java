//Noshin Anjum Nisa
//nnisa
//Hours Spent: 15

/**
 * Implements a class roster
 */
import java.util.*;

public class Roster implements Iterable<Student>
{
  private String courseNumber;
  private ArrayList<Student> students;
  
  public Roster(String number)
  {
    courseNumber = number;
    students = new ArrayList<Student>();
  }
  
  
  
  
  public int size()
  {
    return students.size();
  }
  
  public void addStudent(Student newStudent)
  {
    students.add(newStudent);
  }
  
  public String toString()
  {
    StringBuilder result = new StringBuilder("Class roster - " + courseNumber + "\n");
    for (int i = 0; i < students.size(); i++)
      result.append("  " + students.get(i).toString() + "\n");
    return result.toString();
  }
  
  public Iterator<Student> iterator()
  {
    return new MyIterator();
  }
  
  // private inner class to implement iterator over the list
  private class MyIterator implements Iterator<Student>
  {
    private int index = 0;
    
    // this should run in O(1) time
    public boolean hasNext()
    {
      return index < students.size();
    }
    
    // this, also, should run in O(1) time
    public Student next()
    {
      if (hasNext())
      {
        Student result = students.get(index);
        index++;
        return result;
      }
      else
        throw new NoSuchElementException();   
    }
    
    public void remove()
    {
      // we've got to have some implementation, but we'll
      // defer for now and just throw this exception;
      // I'd rather use iterators just to iterate anyway!
      throw new UnsupportedOperationException();
    }
  }
  
  public void sortStudents()
  {
    Collections.sort(students);
  }
  
  
  public void sortByIDComparator()
  {
    Collections.sort(students, new myComparison());
  }
  
  public Roster(String courseSection, String fileName)
  {
    Scanner l = getFileScanner(fileName);
    students = new ArrayList<Student>();
    courseNumber = courseSection;
    while (l.hasNext())
    {
      String firstName = l.next();
      String lastName = l.next();
      String andrewID = l.next();
      students.add( new Student (firstName, lastName, andrewID));
    }
  }
  
  
  
  
  
  public int queueIndexOfChar(int w, int a, int length, Student s)
  {
    int index;
    if (length-1 < a) // if it gets a blank space or null it puts the index to 0 
    {
      index = 0;
    }
    else
    {
      index = s.getID().charAt(a)-'a'+1;
    }
    return index; 
  }  
  
  
  public void sortByID()
  {
    int index;
    ArrayList<MyList<Student>> myQueues = new ArrayList<MyList<Student>>();
    for (int i =0; i<=26; i++)
    {
      myQueues.add(new MyList());
    }
    
    for (int a = 7; a>=0; a-- )// to go thru letters 
    {
      for ( int w = 0; w<students.size(); w++) // this gets the index of the student 
      {
        int length = students.get(w).getID().length();
        index = queueIndexOfChar(w, a, length, students.get(w));
        myQueues.get(index).enqueue(students.get(w));
      }
      
      students = new ArrayList<Student>();
      for (int i = 0; i<27; i++)
      {
        while (myQueues.get(i).isEmpty()==false)
        {
          Student s = myQueues.get(i).dequeue();
          students.add(s);
        }
      }
    }
  }
  
  public static Scanner getFileScanner(String filename) { 
    Scanner scanner = null; 
    try { scanner = new Scanner(new java.io.File(filename)); } 
    catch (Exception e)  
    { 
      System.out.println("File not found:" + filename); 
      return null; 
    } 
    return scanner; 
  } 
  
  
  
  
  public static void main(String[] args)
  {
    Roster course = new Roster("15-121w");
    course.addStudent(new Student("Tarek","Al-Hariri","thariri"));
    course.addStudent(new Student("Ali","Naqi","anaqi"));
    course.addStudent(new Student("Noshin","Nisa","nnisa"));         
    course.addStudent(new Student("Abdelrahman","Haroun","aharoun"));
    System.out.println(course);
    /*
     Iterator<Student> foo = course.iterator();
     while (foo.hasNext())
     {
     System.out.println(foo.next().getID());
     }
     */
    // the above while loop can also be written as
    for (Student s : course)
      System.out.println(s.getID());
    //and, just as easily...
    for (Student s : course)
      System.out.println(s.getLastName());
  }    
}
