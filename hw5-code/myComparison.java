//Noshin Anjum Nisa
//nnisa
//Hours Spent: 15

import java.util.*;

public class myComparison implements Comparator<Student>
{
  // here's where you define the ordering for this Comparator
  // this one negates the normal compareTo result, thus causing
  // Strings to be sorted in descending order
  public int compare(Student s1, Student s2)
  {
    return s1.getID().compareTo(s2.getID());
  }
  
  // not necessary to override Comparator's equals(Object) method since
    // you're generally not going to compare two Comparator's for equality!
  
}