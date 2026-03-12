import java.awt.*;

public class Turtle {


    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive; //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox; //hitbox of the hero/character
    public int lives; //an integer for number of lives
    public boolean isCrashing; //a boolean to denote if the character is crashing or not


    public Turtle(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =1;
        dy =0;
        width = 290;
        height = 230;
        isAlive = true;
        hitbox = new Rectangle(xpos,ypos,width,height);
        lives =5;


    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        if (xpos < 0) {
            dx=-dx;


        }
        if (xpos > 1400-width) {
            dx=-dx;

        }
        if (ypos < 0) {
            dy=-dy;

        }
        if (ypos > 850-height) {
            dy=-dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos,ypos,width,height);
    }
}
