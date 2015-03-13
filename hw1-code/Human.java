public class Human
{
  private String firstName;
  private String lastName;
  private int myAge;
  private String nationality;
  private String university;
  private String familyMembers;
  public int yearBorn;
  public double height;
  public double weight;
  public Human()
    
  {
    firstName = "";
    lastName = "";
    myAge = 0;
    nationality="";
    university="";
    familyMembers ="";
    yearBorn = 0;
    height = 0;
    weight = 0;
  }
  
  public Human(String first, String last, int age, String nat, String uni, String fam, int yB, double height, double weight)
  {
    firstName = first;
    lastName = last;
    myAge = age;
    nationality = nat;
    university = uni;
    familyMembers =fam;
    yearBorn = yB;
    height = height;
    weight = weight;
  }
  
  public int yearBorn(){
    int value1 = (yearBorn-myAge);
    return value1;
    
    
  }
  public String familyMembers(){
    String value = "In my Family I have" + familyMembers;
    return value;
    
    
  }
  
  public String printNationality(){
    String val= "I'm from "+ nationality;
    return val;
    
    
  }
  public String universityname(){
    String val1= "I study in "+ university;
    return val1;
    
    
  }
  public String fullName(){
    String printArray = "";
    printArray = "My name is"+ " "+ firstName+" "+ lastName+" ";
    return printArray;
  }
  public String toString()
  {
    return firstName + " " + lastName + " " + myAge+ " "+ printNationality()+" "+university+" "+ familyMembers;
  }
  public double bmiTest(){
  double bmi = weight/(height*height);
  return bmi;
  }
    
    
    
    
  static public void main(String[] args)
  {
    Human person = new Human("Nisa", "Bhuiyan", 18, "Bangladesh","Carnegie Mellon", " father mother and sisters",2012, 15, 45);
    //System.out.println(person);
    
    System.out.println(person.printNationality());
    System.out.println(person.universityname());
    System.out.println(person.familyMembers());
    System.out.println(person.fullName());
    System.out.println(person.bmiTest());
  }
}

