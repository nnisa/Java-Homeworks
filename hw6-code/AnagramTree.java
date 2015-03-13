/**Noshin Anjum Nisa
  * nnisa
  * hours worked : 10 hours
  * 
  * */
import java.util.*;

public class AnagramTree
{
  private BST<String> tree;
  
  public AnagramTree(String fileName,int maxLength)
  {
    int read = 0;
    int inserted = 0;
    
    
    tree= new BST<String>();
    Scanner scan = getFileScanner(fileName);
    while (scan.hasNext())
    {
      read++;
      String word = scan.next();
      if (word.length() <= maxLength)
      {
        inserted++;
        String sorted = sort(word);
        tree.add(sorted,word);
      }
    }
    
    int nodes=tree.count();
    System.out.println("Total number of words read:" + read);
    System.out.println("Number of words inserted (length <="+ maxLength +"):" + inserted);
    System.out.println("Number of nodes in the tree:" + nodes);
  }
  
  public static Scanner getFileScanner(String filename)
  {
    Scanner scanner = null;
    try { scanner = new Scanner(new java.io.File(filename)); }
    catch (Exception e)
    {
      System.out.println("File not found: " + filename);
      return null;
    }
    return scanner;
  }
  
  
  
  
  public String sort(String word)
  {
    Character[] arr = new Character[word.length()];
    
    for (int i=0; i< word.length(); i++)
    {
      arr[i] = word.charAt(i);
    }
    Arrays.sort(arr);
    String result = "";
    for (int i=0; i<arr.length; i++) 
    {
      result = result + arr[i];
    }
    return result;
  }
  
  public boolean isEmpty()
  {
    if (tree.count()==0)
      return true;
    return false;
  } 
  
  public ArrayList<String> findMatches(String searchKey)
  {
    String word = sort(searchKey);
    return tree.find(word);
  }
  
  
  
  
  
  /* public String sort(String word)
   {
   String[] arr = word.split("");
   
   for (int i=0; i< word.length(); i++)
   {
   arr[i] = word.getChar(i);
   }
   arr = arr.sort();
   } */
  
  
  /* public String sort(String value)//////////////////////////
   { 
   String temp=" ";                                    //////////////////////////
   
   for(int i = 0; i < value.length(); i++)     //////////////////////////   
   {                                               
   for(int j = i+1; j < value.length(); j++) //////////////////////////
   {                                           
   if(value.CharAt[i].compareTo(value.CharAt[j+1]) < 0)       //////////////////////////         
   {                                       
   temp = temp + i;                     //////////////////////////
   
   }                               //////////////////////////
   }                          //////////////////////////
   }
   }
   */ 
  
}