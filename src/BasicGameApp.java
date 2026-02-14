//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Fish moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program

    //Sets the width and height of the program window
    final int WIDTH = 1400;
    final int HEIGHT = 850;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    public Image nemoPic;
    public Image doryPic;
    public Image brucePic;
    public Image crushPic;

    //Declare the objects used in the program
    public Fish nemo;
    public Turtle crush;
    public Fish2 dory;
    public Shark bruce;
    //Declare the images used in the program
    public Image backgroundPic;
    public Image gameover;
    public Image endScene;


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    public BasicGameApp() {

        setUpGraphics();
        //create (construct) the objects needed for the game and load up
        //names
        nemo = new Fish(700, 100);
        dory = new Fish2(500, 100);
        bruce = new Shark(50, 100);
        crush = new Turtle(0, 0);
        //images
        nemoPic = Toolkit.getDefaultToolkit().getImage("Nemo.png"); //load the picture
        doryPic = Toolkit.getDefaultToolkit().getImage("Dory.png"); //load the picture
        brucePic = Toolkit.getDefaultToolkit().getImage("Bruce.png"); //load the picture
        crushPic = Toolkit.getDefaultToolkit().getImage("Crush.png"); //load the picture
        backgroundPic = Toolkit.getDefaultToolkit().getImage("Water.png"); //load the picture
        gameover = Toolkit.getDefaultToolkit().getImage("gameOver.png"); //load the picture
        endScene = Toolkit.getDefaultToolkit().getImage("endScene.jpg"); //load the picture


    }


//*******************************************************************************
//User Method Section
//
//code to do things here.

    // main thread
    // this is the code that plays the game after things are set up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(2); // sleep for 10 ms
        }
    }


    public void moveThings() {
        //calls the move( ) code in the objects
        nemo.move();
        //nemo move
        dory.move();
        //dory move
        bruce.move();
        //bruce move
        crush.move();
        //if dory,nemo, and crush are not alive bruce stops moving
        if (dory.isAlive == false && nemo.isAlive == false && crush.isAlive ==false) {
            bruce.dx = 0;
            bruce.dy = 0;
        }

        crashing();
    }


    public void crashing() {
        //if characters crash into each otherr

        if (!bruce.hitbox.intersects(nemo.hitbox)) {
            nemo.isCrashing = false;
        }
        if (!bruce.hitbox.intersects(nemo.hitbox)) {
            dory.isCrashing = false;
        }
        if (!bruce.hitbox.intersects(crush.hitbox)){
            crush.isCrashing = false;
        }
        //if statement that minus a life off of nemo if bruce intersects nemo
        if (bruce.hitbox.intersects(nemo.hitbox) && nemo.isAlive == true && nemo.isCrashing == false) {
            System.out.println("Nemo - 1 life");
            bruce.dx = -bruce.dx;
            nemo.dx = 2;
            bruce.dy = -bruce.dy;
            nemo.dy = 0;
            nemo.lives--;
            nemo.isCrashing = true;
            nemo.xpos = 700;
            nemo.xpos = 100;


        }
        //Delares what happens if nemo has no more lives
        if (nemo.lives == 0) {
            nemo.isAlive = false;
            nemo.xpos = -222;
            nemo.ypos = -222;
        }
        //if statement that minus a life off of dory if bruce intersects dory
        if (bruce.hitbox.intersects(dory.hitbox) && dory.isAlive == true && dory.isCrashing == false) {
            System.out.println("Dory -1 life");
            bruce.dx = -bruce.dx;
            dory.dx = -dory.dx;
            bruce.dy = -bruce.dy;
            dory.dy = -dory.dy;
            dory.lives--;
            dory.isCrashing = true;


        }
        //Delares what happens if dory has no more lives
        if (dory.lives == 0) {
            dory.isAlive = false;
            dory.xpos = -222;
            dory.ypos = -222;


        }
        //if statement that minus a life off of crush if bruce intersects crush
        if (bruce.hitbox.intersects(crush.hitbox) && crush.isAlive == true && crush.isCrashing == false) {
            System.out.println("Crush -1 life");
            bruce.dx = -bruce.dx;
            crush.dx = -crush.dx;
            bruce.dy = -bruce.dy;
            crush.dy = -crush.dy;
            crush.lives--;
            crush.isCrashing = true;


        }
        //Delares what happens if crush has no more lives
        if (crush.lives == 0) {
            crush.isAlive = false;
            crush.xpos = -222;
            crush.ypos = -222;


        }

    }
        //Pauses or sleeps the computer for the amount specified in milliseconds
        public void pause ( int time ){
            //sleep
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {

            }
        }

        //Graphics setup method
        public void setUpGraphics () {
            frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

            panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
            panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
            panel.setLayout(null);   //set the layout

            // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
            // and trap input events (Mouse and Keyboard events)
            canvas = new Canvas();
            canvas.setBounds(0, 0, WIDTH, HEIGHT);
            canvas.setIgnoreRepaint(true);

            panel.add(canvas);  // adds the canvas to the panel.

            // frame operations
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
            frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
            frame.setResizable(false);   //makes it so the frame cannot be resized
            frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

            // sets up things so the screen displays images nicely.
            canvas.createBufferStrategy(2);
            bufferStrategy = canvas.getBufferStrategy();
            canvas.requestFocus();
            System.out.println("DONE graphic setup");

        }


        //paints things on the screen using bufferStrategy
        private void render () {
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, WIDTH, HEIGHT);
            //background pick being drawn
            g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);
            //bruce image drawn
            g.drawImage(brucePic, bruce.xpos, bruce.ypos, bruce.width, bruce.height, null);
            //draw characters when alive
            if (nemo.isAlive) {
                g.drawImage(nemoPic, nemo.xpos, nemo.ypos, nemo.width, nemo.height, null);
            }
            if (dory.isAlive) {
                g.drawImage(doryPic, dory.xpos, dory.ypos, dory.width, dory.height, null);
            }
            if (crush.isAlive) {
                g.drawImage(crushPic, crush.xpos, crush.ypos, crush.width, crush.height, null);
            }
            //Game over screen taht shows if nemo,dory, and bruce are not alive
            if (nemo.isAlive == false && dory.isAlive == false && crush.isAlive == false) {
                g.drawImage(endScene, 0, 0, WIDTH, HEIGHT, null);
                g.drawImage(gameover, 10, 50, WIDTH, HEIGHT, null);


            }


            //draw the image of the Fish


            g.dispose();

            bufferStrategy.show();
            //hit box drawing that is not shown on screen
            g.drawRect(nemo.hitbox.x, nemo.hitbox.y, nemo.hitbox.width, nemo.hitbox.height);
            g.drawRect(dory.hitbox.x, dory.hitbox.y, dory.hitbox.width, dory.hitbox.height);
            g.drawRect(crush.hitbox.x, crush.hitbox.y, crush.hitbox.width, crush.hitbox.height);


        }
    }

