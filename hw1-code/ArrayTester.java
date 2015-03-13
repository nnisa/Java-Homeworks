
// Full Name: 
// User ID: 
// Approximate number of hours spent on this assignment: 

public class ArrayTester
{
    private static final int MAX_VALUES = 10;
    private int[] intArr;
    private int numValues;  // the number of valid array elements
    
    
    // default constructor
    public ArrayTester()
    {
        intArr = new int[MAX_VALUES];
        numValues = 0;
    }
    
    // construct an ArrayTester object from an array of ints, a///////////
    // if there are more elements in a than MAX_VALUES, store////////////
    // the first MAX_VALUES elements from a in the ArrayTester///////////
    // HINT: use a.length to determine how many elements are in array a//
    public ArrayTester(int[] a)
    {
      int i = 0;
      intArr = new int[MAX_VALUES];
        if (a.length < MAX_VALUES)
        {
          for (i =0; i< a.length; i++)
          {
            intArr[i] = a[i];
            numValues++;
          }
        }
        else 
        {
          for (i =0; i< MAX_VALUES; i++)
          {
            intArr [i] = a[i];
            numValues++;
          }
        }
    }
    
    // Adds a new integer to the end of the current elements////////
    // in the array and adjusts numValues accordingly./////////////
    // I.e., if the array contained 12, 4, 5 and this method///////
    // was called to insert 13, the array would contain////////
    // 12, 4, 5, 13 and numValues would now be 4////////////////
    // If there are already MAX_VALUES in the array, it////////
    // should NOT add the new integer, but print an error////////
    // message.  It should NOT cause a run-time exception!////////
    public void insert(int i)
    {
      if (numValues < MAX_VALUES)
      {
          intArr[numValues] = i;
          numValues++;
      }
    else
      {
        System.out.println("The Array does not have space left for another ineteger");
      }
      }
    
    // Checks if the array is sorted in increasing order/////////
    // i.e., a[i] <= a[i+1] for all valid array elements////////
    // Returns true if it is, false otherwise//////////////////
    public boolean isSorted()
    {
      int i;
      for (i = 0; i< (numValues-1); i++)
        if(intArr[i] <= intArr[i+1])
      {
         
      }
      else
      {
        return false;
    }
      return true;
    }
    
   
    // Returns the minimum value in the array///////////////////
    // Returns Integer.MIN_VALUE if the array is empty//////////
    public int minimum()
    {
       int min = intArr[0];
       int len = intArr.length;
       if (len > 0)
      {
        for (int i=0; i<numValues; i++) 
        {
        if (intArr[i] < min )
        min = intArr[i];
          
        }
        return min;
      }
      else
      {
        return Integer.MIN_VALUE;
      }}
    
 
    // Searches the array to see if a given value (the key)
    // is present.  Returns the index of the first occurrence
    // of the value in the array or -1 if the value is not present
    public int indexOf(int key, int []array)
    
       
    {
      //if (intArr.contains(key))
      for (int i =0; i<array.length; i++)
      {
        if (array[i] == key){
      
          return i;
        }
        
      }
     
       return -1;
      }
     
 
    // Searches the array to see if a given value (the key)
    // is present.  Returns the index of the last occurrence
    // of the value in the array or -1 if the value is not present
    public int lastIndex(int key, int []array)
    {
      int index = -1;
      for (int i =0; i<array.length; i++)
      {
        if (array[i] == key){
          index = i;
        }
      }
      return index;
    }
    
    
    // Counts the number of vowels in the String parameter, storing
    // the number of lowercase vowels in the first 5 positions of the
    // array and the uppercase in the last 5 positions
    public void countVowels(String s)
    {
      for (int i=0; i<intArr.length; i++){
      intArr[i]=0;
      numValues = 0;
      }
      String newArr= "aeiouAEIOU";
      int [] numb = new int [10]; 
      for (int i =0; i< newArr.length(); i++){
        for (int j=0; j<s.length(); j++){
          if (s.charAt(j) == newArr.charAt(i)){
            numb[i] = numb[i]+1;
          }
        }
      }
      for (int i=0; i< numb.length; i++){
        {intArr[i] = numb[i];
        numValues ++;
        }
      }
    }

    
    
     
    // Rotates all the elements one position to the right,
    // placing the last element in position 0
    // Example:  if the array before the call to rotateRight is 4, 5, 6, 1
    // it is 1, 4, 5, 6 after the call
    public void rotateRight()
    {
      
      int last = intArr[numValues-1];
      for (int i= numValues-1; i >= 1; i--){
        intArr[i] = intArr[i-1];
      }
      intArr[0] = last;
    }
 
    
  
    // Reverses the elements stored in the array
    // Example: if the array before the call to reverse is 1, 4, 5, 6
    // it is 6, 5, 4, 1 after the call
    public void reverse()
    {
     int [] newArr= new int [numValues];
     for (int i=0; i<numValues; i++){
     newArr[i] = intArr[numValues-1-i];
     }
     for (int i =0; i<numValues; i++){
     intArr[i] = newArr[i];
     }
    }
     
    
    

    // Returns a String representing all the attributes of this ArrayTester///////////////
    public String toString()
    {
        String stri;
        String s = "";
        for ( int i = 0; i<numValues; i++)
        {
         stri = String.valueOf(intArr[i]);
         s = s + stri;
    }
        return s;
    }
    
    
    public static void main(String[] args)
    {
        int[] arr = {1,2,3,4,6,7,8,9,9,0};
        ArrayTester a = new ArrayTester(arr);
        
        System.out.println(arr.length);
        ArrayTester anotherArray = new ArrayTester(arr);
        System.out.println(a.toString());
        //a.insert(5);
        //System.out.println(a);
        System.out.println(a.isSorted());
        System.out.println(a.minimum());

        System.out.println(a.indexOf(3, arr));
        System.out.println(a.lastIndex(3, arr));
        //a.rotateRight();
        //System.out.println(a.toString());
        a.reverse();
        System.out.println(a.toString());
        anotherArray.countVowels("aUaaaI");
        System.out.println(anotherArray.toString());
      
        
    }
}
