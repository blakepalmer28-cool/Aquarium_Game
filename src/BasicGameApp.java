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
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image nemoPic;
    public Image doryPic;
    public Image brucePic;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	public Fish nemo;
    public Fish2 dory;
    public Shark bruce;
    public Image backgroundPic;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up
		nemo = new Fish(700,100);
        dory = new Fish2(500,100);
        bruce = new Shark(50,100);
        //images
        nemoPic = Toolkit.getDefaultToolkit().getImage("Nemo.png"); //load the picture
        doryPic = Toolkit.getDefaultToolkit().getImage("Dory.png"); //load the picture
        brucePic = Toolkit.getDefaultToolkit().getImage("Bruce.png"); //load the picture
        backgroundPic = Toolkit.getDefaultToolkit().getImage("Water.png"); //load the picture


	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(2); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
		nemo.move();
        nemo.dx = 2;
        dory.move();
        dory.dx=2;
        bruce.move();
        bruce.dx=1;
        crashing();

	}

    public void crashing() {
        //if astros crash into each other
        if (bruce.hitbox.intersects(nemo.hitbox) && nemo.isAlive == true) {
            System.out.println("Nemo Eliminated");
            bruce.dx = -bruce.dx;
            nemo.dx = -nemo.dx;
            bruce.dy = -bruce.dy;
            nemo.dy = -nemo.dy;
            nemo.isAlive = false;
            nemo.xpos = -222;
            nemo.ypos = -222;
            render();

        }
        if (bruce.hitbox.intersects(dory.hitbox) && dory.isCrashing == false) {
                System.out.println("Dory Eliminated");
            bruce.dx = -bruce.dx;
            dory.dx = -dory.dx;
            bruce.dy = -bruce.dy;
            dory.dy = -dory.dy;
            dory.isAlive = false;
            dory.xpos = -222;
            dory.ypos = -222;
            dory.isCrashing = true;


        }

        if (!nemo.hitbox.intersects(dory.hitbox)) {
            dory.isCrashing = false;
        }
    }
	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   public void setUpGraphics() {
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
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);

      //draw the image of the Fish
		g.drawImage(nemoPic, nemo.xpos, nemo.ypos, nemo.width, nemo.height, null);
        g.drawImage(doryPic, dory.xpos, dory.ypos, dory.width, dory.height, null);
        g.drawImage(brucePic, bruce.xpos, bruce.ypos, bruce.width, bruce.height, null);
        g.drawRect(nemo.hitbox.x, nemo.hitbox.y, nemo.hitbox.width, nemo.hitbox.height);
        g.drawRect(dory.hitbox.x, dory.hitbox.y, dory.hitbox.width, dory.hitbox.height);
        g.dispose();

		bufferStrategy.show();
	}
}