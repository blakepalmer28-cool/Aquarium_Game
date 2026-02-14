import java.awt.*;

public class Fish {


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


    public Fish(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =4;
        dy =0;
        width = 50;
        height = 50;
        isAlive = true;
        hitbox = new Rectangle(xpos,ypos,width,height);
        lives = 3;


    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {

        if (xpos < 0) {
            xpos =1400;
            ypos = (int)(Math.random()*851);


        }
        if (xpos > 1400) {
            xpos =0;
            ypos = (int)(Math.random()*851);

        }
        if (ypos < 0) {
            ypos =850;
            xpos = (int)(Math.random()*1400); //random movement

        }
        if (ypos > 850-height) {
            ypos = 0;
            xpos = (int)(Math.random()*1400); //random movement
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos,ypos,width,height);

    }
}






