// Noshin Anjum Nisa
// worked for 6 and half hours




import java.util.Arrays;
import java.util. *;
public class TicTacToe
{
  private char[][] board;
  private char mark;
  
  public TicTacToe()
  {
    board = new char[3][3];
    for (int row = 0; row < board.length; row++)
    {
      for (int col = 0; col < board[row].length; col++)
        board[row][col] = ' ';
    }
    // System.out.println(Arrays.deepToString(board));
  }
  
  
  
  
  public void addMark(char mark, int row, int col)
  {
    board[row][col] = mark;
  }
  
  
  
  
  private void humanMove(boolean player1)
  {
    Scanner s = new Scanner(System.in);
    System.out.println("Enter your row number: ");
    int r = s.nextInt();
    
    System.out.println("Enter your column number: ");
    int c = s.nextInt();
    
    while (validMove(r, c) == false)
    {
      System.out.println(" (>_<)  You entered a wrong number, please enter again!");
      
      System.out.println("Enter your row number: ");
      r = s.nextInt();
      
      System.out.println("Enter your column number: ");
      c = s.nextInt();
    }
    if (player1== true)
    {
      addMark('X', r, c);
    }
    else if( player1 == false) 
    {
      addMark('O', r, c);
    }  
  }
  
  
  
  
  private boolean validMove(int row, int col)
  {
    if (  (row<3 && col<3) && (row >-1 && col>-1) && (board[row][col] == ' ') )
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  
  
  
  private boolean noMoves()
  {
    int filledPlaces =0;
    for(int i=0; i<3; i++)
    {
      for (int j=0; j<3; j++)
      {
        if (validMove(i,j) == false)
        {
          filledPlaces++;
        }
      }
    }
    if (filledPlaces == 9)
    {
      return true;
    }
    return false;
  }
  
  
  
  private boolean winningBoard(boolean player1)
  {
    if (player1 == true)
    {
      mark = 'X';
    }
    else
    {
      mark = 'O';
    }
    
    {
      if ( ( (board[0][0]==board[0][1]) && (board[0][1] == mark) && (board[0][1]==board[0][2]) && (board[0][2]== mark) ) ||
          ( (board[1][0]==board[1][1]) &&  (board[1][1] == mark) && (board[1][1]==board[1][2]) && (board[1][2]== mark) ) ||
          ( (board[2][0]==board[2][1]) &&  (board[2][1] == mark) && (board[2][1]==board[2][2]) && (board[2][2]== mark ) ) ||
          
          ( (board[0][0]==board[1][0]) &&  (board[1][0] == mark) && (board[1][0]==board[2][0]) && (board[2][0]== mark) ) ||
          ( (board[0][1]==board[1][1]) &&  (board[1][1] == mark) && (board[1][1]==board[2][1]) && (board[2][1]== mark) ) ||
          ( (board[0][2]==board[1][2]) &&  (board[1][2] == mark) && (board[1][2]==board[2][2]) && (board[2][2]== mark) ) ||
          
          ( (board[0][0]==board[1][1]) &&  (board[1][1] == mark) && (board[1][1]==board[2][2]) && (board[2][2]== mark) ) ||
          ( (board[2][0]==board[1][1]) &&  (board[1][1] == mark) && (board[1][1]==board[0][2]) && (board[0][2]== mark) ) )
      {
        return true;
      }
      else
      {
        return false;
      }
    }
  }
  /*
   for (int i=0; i<3; i++)
   {
   if (board[0][i] == mark){
   return true; 
   }
   else if (board[1][i] == mark)
   {
   return true;
   }
   else if(board[2][i]== mark)
   {
   return true;
   }
   else if (board[i][0]== mark)
   {
   return true;
   }
   else if (board[i][1]== mark)
   {
   return true;
   }
   else if (board[i][2]== mark)
   {
   return true;
   }
   else if (board[i][i]== mark)
   {
   return true;
   }
   else if (board[i][2-i]== mark)
   {
   return true;
   }
   }
   return false;
   }
   */
  private void humanVScomp(boolean player1)
  {
    int row = (int)(Math.random() * 3);
    int column = (int)(Math.random() * 3);
    
    while ( validMove(row, column) == false)
    {
      row = (int)(Math.random() * 3);
      column = (int)(Math.random() * 3);
    }
    
    if (player1== true)
    {
      addMark('X', row, column);
    }
    else
    {
      addMark('O', row, column);
    }
  }
  public void playGame()
  {
    int numbMoves = 1;
    boolean isGameOver = false;
    Scanner sc = new Scanner(System.in);
    System.out.println("Choose your game mode and dare yourself ;)  \n 1. Human vs Human \n 2. Computer vs Human ");
    int mode = sc.nextInt();
    
    if (mode == 1)
    { 
      Scanner scc = new Scanner(System.in);
      
      System.out.println("Player one, enter your name: ");
      String player1 = scc.nextLine();
      
      System.out.println("Player two, enter your name: ");
      String player2 = scc.nextLine();
      
      
      while (isGameOver == false)
      {
        if (numbMoves % 2 == 0)
        {
          System.out.println("Its turn for "+ player1);
          humanMove(true);
          numbMoves++;
        } 
        else 
        {
          System.out.println("Its turn for "+ player2);
          humanMove(false);
          numbMoves++;
        }
        System.out.println(this.toString());
        if (winningBoard(true) == true)
        {
          System.out.println(player1 + " "+ "Wins!!!");
          isGameOver = true;
        }
        else if (winningBoard(false) == true)
        {
          System.out.println(player2 +" "+ "Wins!!!");
          isGameOver = true;
        }
        else if (noMoves() == true)
        {
          System.out.println(" There is a draw :( ");
          isGameOver = true;
        }
      }
      
    }
    else if (mode == 2)
    { 
      Scanner scc = new Scanner(System.in);
      
      System.out.println("Player, enter your name: ");
      String player1 = scc.nextLine();
      
      String player2 = "Computer";
      
      while (isGameOver == false)
      {
        if (numbMoves % 2 == 0)
        {
          System.out.println("Its turn for "+ player1);
          humanMove(true);
          numbMoves++;
        } 
        else 
        {
          System.out.println("Its turn for "+ player2);
          humanVScomp(false);
          numbMoves++;
        }
        System.out.println(this.toString());
        if (winningBoard(true) == true)
        {
          System.out.println(player1 + " "+ "Wins!!!");
          isGameOver = true;
        }
        else if (winningBoard(false) == true)
        {
          System.out.println(player2 +" "+ "Wins!!!");
          isGameOver = true;
        }
        else if (noMoves() == true)
        {
          System.out.println(" There is a draw :( ");
          isGameOver = true;
        }
      }
      
    }
  }
  
  
  
  
  
  
  
  public String toString()
  {
    String result = "";
    for (int row = 0; row < board.length; row++)
    {
      result += "|";
      for (int col = 0; col < board[row].length; col++)
      {
        result += board[row][col] + "|";
      }
      result += "\n";
    }
    
    return result;
  }
  
  public static void main(String[] args)
  {
    TicTacToe game = new TicTacToe();
    game.playGame();
  }
}