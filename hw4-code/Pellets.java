import java.awt.*;
import java.util.Random;

/**
 * Pellets, to be eaten by Caterpillars in the Munch Game.
 *
 * @see MunchGame
 */
public class Pellets
{
    // private variables giving the characteristics of the pellets
    private Color color;                     // pellet color
    private SimpleList<Point> pellets = new MyList<Point>(); // the pellets, stored as a List of Points
    private Random gen = new Random();       // random number generator for initial Pellet locations
    
    /**
     * Create the new pellets, with the given color at randomly chosen positions.
     *
     * @param c the pellet color
     * @param num the number of pellets
     */
    public Pellets(Color c, int num)
    {
        color = c;
        
        for (int i=0; i<num; i++)
        {
            Point p = new Point(gen.nextInt(MunchGame.BOARD_WIDTH-1)+1,
                                gen.nextInt(MunchGame.BOARD_HEIGHT-1)+1);
            pellets.add(p);
        }
    }
    
    /**
     * Determine whether a pellet lies on the given position.
     *
     * @param p the position to test
     * @return <code>true</code> if a pellet lies on p; <code>false</code> otherwise
     */
    public boolean onPosition(Point p)
    {
        for(Point pellet : pellets)  // iterate over all the pellets
        {
            if (pellet.equals(p))
                return true;
        }
        return false;
    }
    
    /**
     * Remove the pellet at Point p from the collection of pellets.
     *
     * @param p the Point that has the pellet to remove
     */
    public void remove(Point p)
    {
        pellets.remove(p);
    }
    
    /**
     * Determine whether all of the pellets have been consumed.
     *
     * @return <code>true</code> if there are no more pellets; <code>false</code> otherwise
     */
    public boolean allGone()
    {
        return pellets.isEmpty();
    }
    
    /**
     * Draw the pellets on the given pane
     *
     * @param g the Graphics pane on which to draw the pellet
     */
    public void paint (Graphics g)
    {
        g.setColor(color);
        for(Point pellet : pellets)
        {
            g.fillOval(5+MunchGame.SEGMENT_SIZE*pellet.x+2,
                       15+MunchGame.SEGMENT_SIZE*pellet.y+2, 
                       MunchGame.SEGMENT_SIZE-4,
                       MunchGame.SEGMENT_SIZE-4);
        }
    }
}
