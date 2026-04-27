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
    public boolean isUp;
    public boolean isDown;
    public boolean isRight;
    public boolean isLeft;
    public int score;


    public Fish(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =10;
        dy =10;
        width = 50;
        height = 50;
        isAlive = true;
        hitbox = new Rectangle(xpos,ypos,width,height);
        lives = 3;
        isUp = false;
        isDown = false;
        isRight = false;
        isLeft = false;


    } // constructor
//nemo
    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {

        if (isUp ==true){
            dy =-2;
        }

        if (isUp ==false && isDown ==false){
            dy=0;
        }
        if (isDown ==true){
            dy =2;
        }
        if(isRight ==true){
            dx = 2;
        }
        if (isRight ==false && isLeft ==false){
            dx=0;
        }
        if(isLeft ==true){
            dx =-2;
        }


        if (xpos < 0) {//change this to bounce so that it wont glitch out
            dx=-dx;
            xpos =0;

//changes direction if it huts an area

        }
        if (xpos > 1400-width) {
            xpos =0;
            ypos = (int)(Math.random()*851);
            score = score+1;

        }
        if (ypos < 0) {
            dy =-dy;//random movement
            ypos = 0;

        }
        if (ypos > 850-height) {
            ypos = 850-height;
            dy =-dy; //random movement
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos,ypos,width,height);

    }
}






