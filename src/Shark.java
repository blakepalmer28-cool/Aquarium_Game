import java.awt.*;

public class Shark {

    public String name;                //holds the name of the enemy
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the enemy in the x direction
    public int dy;                    //the speed of the enemy in the y direction
    public int width;
    public int height;
    public boolean isAlive; //a boolean to denote if the enemy is alive or dead.
    public Rectangle hitbox; // hitbox of the character



    public Shark(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =2;
        dy =0;
        width = 300;
        height = 200;
        isAlive = true;
        hitbox = new Rectangle(xpos,ypos,width,height);


    } // constructor

    //The move method.  Everytime this is run (or "called") the enemy's x position and y position change by dx and dy
    public void move() {

        if (xpos < 0) {
            dx = -dx;
            dy = (int)(Math.random()*10); //random movement

        }
        if (xpos > 1400) {
            dx = -dx;
            dy = (int)(Math.random()*10); //random movement

        }
        if (ypos < 0) {
            dy = -dy;
            dx = (int)(Math.random()*10); //random movement

        }
        if (ypos > 850-height) {
            dy = -dy;
            dx = (int)(Math.random()*10); //random movement
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos,ypos,width,height);
    }

}
