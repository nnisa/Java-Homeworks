import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;  // added so user can set initial # of pellets

/**
 * The user interface and main program for the munch game.
 *
 * This is a two-player game.  Each player controls a caterpillar and
 * attempts to perform manuevers so that it eats as many pellets as
 * possible.  Whoever eats more pellets is the winner.
 *
 * The caterpillars are controlled via keyboard commands.  Caterpillars
 * initially move eastward, but at any moment a user can command the
 * caterpillar to stop moving or else change directions (north, south, 
 * east, or west).  The keyboard commands for each player are as follows:
 *
 * Player one (blue): w=north, s=south, d=east, a=west, q=pause
 *
 * Player two (red):  i=north, k=south, l=east, j=west, p=pause
 *
 * @see Caterpillar
 * @see Pellets
 */
public class MunchGame extends Frame
{
    // the internal state of a munch game
    final static int LENGTH = 10;        // initial length of each caterpillar
    final static int BOARD_WIDTH = 60;   // board width ...
    final static int BOARD_HEIGHT = 40;  // ... and height
    final static int SEGMENT_SIZE = 10;  // size of each caterpillar segment
    // player one's caterpillar
    private Caterpillar playerOne = new Caterpillar (Color.BLUE, new Point(20,10), LENGTH, SEGMENT_SIZE*4, BOARD_HEIGHT, BOARD_WIDTH);
    // player two's caterpillar
    private Caterpillar playerTwo = new Caterpillar (Color.RED, new Point(20,30), LENGTH, SEGMENT_SIZE*(BOARD_WIDTH-4), BOARD_HEIGHT, BOARD_WIDTH);
    // the pellets, to be initialized in the MunchGame constructor
    Pellets pellets;  // package visibility so the caterpillars can access and "eat" the pellets
    
    
    /**
     * Construct a new instance of the caterpillar game.
     */
    public MunchGame()
    {
        // I/O to acquire and set the number of pellets
        String input = JOptionPane.showInputDialog("Number of pellets? ");
        int numPellets = Integer.parseInt(input);
        pellets = new Pellets(Color.GREEN, numPellets);
        
        // create the board and necessary event listeners
        setSize((BOARD_WIDTH+1)*SEGMENT_SIZE, BOARD_HEIGHT*SEGMENT_SIZE+32);
        setTitle("Munch Game");
        addKeyListener(new KeyReader());
        addWindowListener(new CloseAndQuit());
    }
    
    /**
     * Run the game and animate the results.
     */
    public void run()
    {
        while (!pellets.allGone())
        {
            movePlayers();
            repaint();
            try
            {
                // sleep 100 milliseconds to give illusion of animation
                Thread.sleep(100);
            }
            catch (Exception e) { }
        }
    }
    
    /**
     * Draw the current game state, or display splash screen with winner if done.
     *
     * @param g the graphics pane on which to redraw
     */
    public void paint(Graphics g)
    {
        if (!pellets.allGone())
        {
            pellets.paint(g);
            playerOne.paint(g);
            playerTwo.paint(g);
        }
        else
        {
            int playerOneScore = playerOne.getScore();
            int playerTwoScore = playerTwo.getScore();
            g.setFont(g.getFont().deriveFont(Font.BOLD, (float)20.0));
            
            if (playerOneScore > playerTwoScore)
            {
                g.setColor(Color.blue);
                g.drawString("Blue is the winner!", 220,200);
            }
            else if (playerTwoScore > playerOneScore)
            {
                g.setColor(Color.red);
                g.drawString("Red is the winner!", 220,200);
            }
            else
            {
                g.setColor(Color.black);
                g.drawString("A tie game!", 250,200);
            }
        }
    }
    
    /**
     * For each player, consume the next command and move accordingly.
     */
    public void movePlayers() 
    {
        playerOne.move(this);
        playerTwo.move(this);
    }
    
    /**
     * Determine whether the given point is free (so that a caterpillar
     * can move onto it).
     *
     * @param newPos the point to test
     * @return true if newPos is free
     */
    public boolean canMove(Point newPos)
    {
        // get x, y coordinate
        int x = newPos.x;
        int y = newPos.y;
        
        // test if on the board
        if ((x <= 0) || (x >= BOARD_WIDTH) || (y <= 0) || (y >= BOARD_HEIGHT))
            return false;

        // test if newPos puts one caterpillar on another
        if (playerOne.onPosition(newPos) || playerTwo.onPosition(newPos))
            return false;
        // safe square to move onto
        return true;
    }
    
    /**
     * Convert keyboard inputs for the two players into
     * corresponding caterpillar commands.
     */
    private class KeyReader extends KeyAdapter
    {
        public void keyPressed (KeyEvent e)
        {
            char c = e.getKeyChar();
            switch (c)
            {
                case 'q': playerOne.setDirection('Z'); break;  // 'Z' pauses that caterpillar
                case 'p': playerTwo.setDirection('Z'); break;  // 'Z' pauses that caterpillar
                case 'a': playerOne.setDirection('W'); break;
                case 'd': playerOne.setDirection('E'); break;
                case 'w': playerOne.setDirection('N'); break;
                case 's': playerOne.setDirection('S'); break;
                case 'j': playerTwo.setDirection('W'); break;
                case 'k': playerTwo.setDirection('S'); break;
                case 'l': playerTwo.setDirection('E'); break;
                case 'i': playerTwo.setDirection('N'); break;
            }
        }
    }
    
    /**
     * Cleanly quit if game window is closed.
     */
    private class CloseAndQuit extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
            System.exit(0);
        }
    }
    
    /**
     * The main method. This creates a new world, displays it, and then
     * starts it running.
     *
     * @param args the command line arguments (all of which are ignored)
     */
    public static void main(String[] args)
    {
        MunchGame world = new MunchGame();
        world.setVisible(true);
        world.run();
    }
}
