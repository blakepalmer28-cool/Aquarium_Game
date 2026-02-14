import java.awt.*;

public class Fish2 {


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





    public Fish2(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =3;
        dy =0;
        width = 90;
        height = 80;
        isAlive = true;
        hitbox = new Rectangle(xpos,ypos,width,height);
        lives = 3;


    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {

        if (xpos < 0) {
            dx = -dx;
            dy = (int)(Math.random()*10); //random movement

        }
        if (xpos > 1400-width) {
            dx=-dx;
            dy = (int)(Math.random()*10); //random movement

        }
        if (ypos < 0) {
            dy = -dy;
            dx = (int)(Math.random()*10); //random movement

        }
        if (ypos > 850 - height) {
            dy = -dy;
            dx = (int)(Math.random()*10); //random movement
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos,ypos,width,height);
    }
}
