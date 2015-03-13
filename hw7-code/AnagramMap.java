// Noshin Anjum Nisa
// nnisa
// hours works: 8 hours

import java.util.*;

public class AnagramMap
{
  private TreeMap<String, ArrayList<String>> noshinMap;
  private int max;
  /*
   * constructor for the class
   * @param fileName a String with the name of the file to build the dictionary with
   * @param maxLength the length of the longest word(s) to be loaded into the dictionary
   */
  public AnagramMap(String fileName, int maxLength)
  {
    noshinMap = new TreeMap<String, ArrayList<String>>();
    Scanner file = getFileScanner(fileName);
    int count = 0;
    max=maxLength;
    while (file.hasNext())
    {
      String word = file.next();
      if(word.length() <= maxLength)
      {
        String sortedWord = sort(word);
        
        if (!noshinMap.containsKey(sortedWord))
        {
          noshinMap.put(sortedWord, new ArrayList<String>());
          count++;
        }
        noshinMap.get(sortedWord).add(word);
      }
    }
    System.out.println("Number of entries = "+count);
  }
  
  /*
   * handy, dandy helper function to open a file given a string with its name
   * @param filename the name of the file
   * @return a Scanner for that file, null if it could not be opened
   */
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
  
  public void printAllAnagrams()
  {
    Scanner input = new Scanner(System.in);
    System.out.print("\nstring to search [#] to stop: ");
    String searchKey = input.next();
    while (!searchKey.equals("#"))
    {
      if (searchKey.length() <= max)
        helper(searchKey);
      else
        System.out.println("  That word is too long; max length = " + max);
      
      System.out.print("\nstring to search [#] to stop: ");
      searchKey = input.next();
    }
  }
  
  public void helper(String word)
  {
    TreeMap<Integer, ArrayList<String>> newMap = new TreeMap<Integer, ArrayList<String>>();
    PowerSet ps = new PowerSet(word.length());
    while (ps.hasNext())
    {
      int[] a = ps.next();
      String string = "";
      for (int i= 0; i < a.length; i++)
      { 
        string = string + word.charAt(a[i]);
      }
      String sorted = sort(string);
      if (noshinMap.containsKey(sorted))
      {
        int length = sorted.length();
        
        if (!newMap.containsKey(length))
          newMap.put(length, new ArrayList<String>());  
        
        ArrayList<String> list = noshinMap.get(sorted);
        
        for (int l = 0; l<list.size() && list!=null; l++)
        {
          if(!newMap.get(length).contains(list.get(l)))
            newMap.get(length).add(list.get(l));
        }
      }
    }
    for (int i=2; i< word.length()+1; i++)
    {
      if (newMap.get(i) != null)
      {
        Collections.sort(newMap.get(i));
        System.out.println( i+":"+ newMap.get(i));
      }
    }
  }
  
  public String sort(String word)
  {
    Character[] arr = new Character[word.length()];
    for (int i=0; i< word.length(); i++)
      arr[i] = word.charAt(i);
    Arrays.sort(arr);
    String result = "";
    for (int i=0; i<arr.length; i++) 
      result = result + arr[i];
    return result;
  }
  
  
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    System.out.print("Maximum word size to consider? ");
    int max = input.nextInt();
    //AnagramMap a = new AnagramMap("small-words-qatar.txt", max);
    AnagramMap a = new AnagramMap("words.txt", max);  // use this file once the small one works
    a.printAllAnagrams();
  }
}
