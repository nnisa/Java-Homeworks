//Noshin Anjum Nisa
//nnisa
//Hours Spent: 15

/**
 * Implements a student (name, andrewid)
 */
public class Student implements Comparable<Student>
{
  private String firstName;
  private String lastName;
  private String andrewID;
  
  public Student(String first, String last, String id)
  {
    firstName = first;
    lastName = last;
    andrewID = id;
  }
  
  public String getFirstName()
  {
    return firstName;
  }
  
  public String getLastName()
  {
    return lastName;
  }
  
  public String getID()
  {
    return andrewID;
  }
  
  public String getFullName()
  {
    return firstName + " " + lastName;
  }
  
  public String toString()
  {
    return firstName + " " + lastName + " " + andrewID;
  }
  
  
  public int compareTo(Student s)
  {
    if (this.getLastName().compareTo(s.getLastName()) == 0)
    {
      return (this.getFirstName().compareTo(s.getFirstName()));
    } 
    return (this.getLastName().compareTo(s.getLastName()));
  }
  
  
  public boolean equals(Student s)
  {
    if (this.getLastName().compareTo(s.getLastName()) == 0)
    {
      return true; 
    }
    return false; 
  }
  
  
  public static void main(String[] args)
  {
    
  }
}
