import java.awt.*;

/**
 * Caterpillars, for use in the Munch Game
 *
 * @see MunchGame
 */
public class Caterpillar
{    
    // private fields giving this caterpillar's characteristics
    private Color color;      // its color
    private Point position;      // the position of its head
    private char direction = 'E';    // the direction of its head
    private SimpleQueue<Point> body = new MyList<Point>();   // its entire body
    private int score = 0;      // its current score
    private int scoreLoc;      // x-location to print score on the board
    private SimpleQueue<Character> commands = new MyList<Character>(); // the pending commands to be applied
    private int BOARD_WIDTH;
    private int BOARD_HEIGHT;
    
    /**
     * Construct a new caterpillar with the given color and starting position.
     *
     * @param c this caterpillar's color
     * @param sp this caterpillar's starting position (a point on the grid)
     * @param len the caterpillar's length (number of body segments)
     * @param loc the x coordinate of this caterpillar's score
     */
    public Caterpillar(Color c, Point sp, int len, int loc,  int height, int width)
    {
        color = c;
        scoreLoc = loc;
        for (int i = 0; i < len; i++)
        {
            position = new Point(sp.x + i, sp.y);
            body.enqueue(position);
        }
        BOARD_WIDTH = width;
        BOARD_HEIGHT = height;

    }
    
    /**
     * Set the direction of the head of the caterpillar.
     *
     * @param d the direction (N, S, E, or W) or pause command (Z)
     */
    public void setDirection(char d)
    {
        commands.enqueue(new Character(d));
    }
    
    /**
     * Get the caterpillar's score.
     *
     * @return the current score
     */
    public int getScore()
    {
        return score;
    }
    
    /**
     * Move the caterpillar one step.
     *
     * @param game the current state of the caterpillar game
     */
    public void move (MunchGame game) 
    {
        // first see if our direction is changing
        if (!commands.isEmpty())
        {
            Character c = commands.dequeue();
            direction = c.charValue();
            if (direction == 'E') return;  // we're not moving
        }
        // then find new position along that direction
        Point newPos = newPosition();
        if (game.canMove(newPos))
        {
            // check if we will eat a pellet
            if (game.pellets.onPosition(newPos))
            {
                game.pellets.remove(newPos);
                score++;
                body.enqueue(newPos);
                
            }
            // erase one segment, add another
            body.dequeue();
            body.enqueue(newPos);
            position = newPos;
        }
    }
    
    /**
     * Calculate the next position for the caterpillar,
     * based on its current direction of movement.
     * 
     * @return a Point representing the next position in the chosen direction
     */
    private Point newPosition()
    {
        int x = position.x;
        int y = position.y;
        
        
        switch (direction)
        {
            case 'E': x++; break;
            case 'W': x--; break;
            case 'N': y--; break;
            case 'S': y++; break;
        }
        if (x == BOARD_WIDTH)
        {
          x = 1;
        }
        else if (x == 0)
        {
          x = BOARD_WIDTH -1;   
        }
        
        if (y == BOARD_HEIGHT)
        {
          y = 1;

        }
         else if (y == 0)
        {
          y = BOARD_HEIGHT-2;

        }
        return new Point(x, y);
    }
    
    /**
     * Determine whether any part of the caterpillar's body lies on the
     * given position.
     *
     * @param newPos the position to test
     * @return true if any part of the caterpillar's body lies on newPos
     */
    public boolean onPosition(Point newPos)
    {
        for (Point segment : body)
            if (segment.equals(newPos))
                return true;
        return false;
    }
    
    /**
     * Draw this caterpillar on the given graphics pane and
     * draw its score in the appropriate location.
     *
     * @param g the graphics pane on which to draw the caterpillar
     */
    public void paint(Graphics g)
    {
        g.setColor(color);
        for (Point segment : body)
        {
            g.fillOval(5+MunchGame.SEGMENT_SIZE*segment.x,
                       15+MunchGame.SEGMENT_SIZE*segment.y,
                       MunchGame.SEGMENT_SIZE, MunchGame.SEGMENT_SIZE);
        }
        g.drawString(Integer.toString(score), scoreLoc,
                     MunchGame.SEGMENT_SIZE*(MunchGame.BOARD_HEIGHT+2));
    }
}
