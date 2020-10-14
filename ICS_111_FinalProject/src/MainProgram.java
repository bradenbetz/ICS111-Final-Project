
//INSTRUCTIONS
//USE THE "W" and "S" keys to navigate the spaceship through the field of obstacles
//Avoid the obstacles for as long as possible
// collect the stars to gain points
//if ship collides with obstacle you lose
//The obstacle moves faster over time

//Main class made by Caelan Barut
import java.awt.Color;
import java.util.Random;

public class MainProgram {
	static final int Max_Asteroids = 1;
	static final int MAX_ROCK = 1;
	final static int RUN_STATE = 0;
	static final int Max_ScreenX = 1200;// This is the screen size x
	static final int Max_ScreenY = 1360;// This is the screen size y
	static final int Collides = 1;
	final static int LOSE_STATE = 2;
	static final int MAX_FUEL = 10;

	public static void main(String[] args) {

		// Setup EZ graphics system.
		EZ.initialize(Max_ScreenX, Max_ScreenY); // PIXEL picture element
		int asterod = RUN_STATE;
		Random rand = new Random();
		// create time variable with start time
		long startTime;
		startTime = System.currentTimeMillis();
		// Create sky images
		EZImage sky1 = EZ.addImage("OuterSpace.png", 500, 255);
		EZImage sky2 = EZ.addImage("OuterSpace.png", 1500, 255);
		// scale the sky
		sky1.scaleBy(2.0);
		sky2.scaleBy(2.0);
		// add spaceship
		Spaceship ship = new Spaceship("ship.png", 100, 270); // Braden

		// CREATE ALL OBSTACLES BELOW
		// create new obstacles based on "MAX_ASTERIODS"
		obstacles[] asteriod = new obstacles[Max_Asteroids];
		for (int i = 0; i < Max_Asteroids; i++) {
			asteriod[i] = new obstacles("obstacle_asteroid.png", Max_ScreenX, Max_ScreenY);// position the asteroid and

		} // Create the second obstacles based on MAX_ROCK
		Obstacle2[] rocks = new Obstacle2[MAX_ROCK];
		for (int g = 0; g < MAX_ROCK; g++) {
			rocks[g] = new Obstacle2("rock.png", Max_ScreenX, Max_ScreenY);
		} // create the third obstacle
		obstical3[] moreRocks = new obstical3[MAX_ROCK];
		for (int y = 0; y < MAX_ROCK; y++) {
			moreRocks[y] = new obstical3("obstacle_asteroid.png", Max_ScreenX, Max_ScreenY);
		}
		obstalce4[] evenMoreRocks = new obstalce4[MAX_ROCK];
		for (int t = 0; t < MAX_ROCK; t++) {
			evenMoreRocks[t] = new obstalce4("obstacle_asteroid.png", Max_ScreenX, Max_ScreenY);
		}
		// Create the amount of fuel
		Fuel[] gas = new Fuel[MAX_FUEL];
		for (int i = 0; i < MAX_FUEL; i++) {
			gas[i] = new Fuel("gold.png", Max_ScreenX, Max_ScreenY);
		}
		EZSound boom = EZ.addSound("explode.wav");
		EZSound music = EZ.addSound("music.wav");
		music.play();
		Random randomGenerator = new Random();
		float a = 1f;
		int b = 0;// counter for the time.
		int points = 0;// Starting amount for the points... collect object to get points

		// While game is in Run_STATE, loop until game is in LOSE_STATE
		while (asterod == RUN_STATE) {
			ship.play();// Braden
			long currentTime = System.currentTimeMillis();
			if (currentTime - startTime >= 1000) {
				startTime = System.currentTimeMillis();
				a += 0.1f;// Every second increase size by this amount
				b++;// Increase by one very second, This will be the timer counter in Int
			}
			int c = b/2;
			// Marcos
			if (c > 14) {// This code is for scaling the speed with time. Each second make object move
							// faster
				c = 12;// Set a limit so the object don't move too fast
			}

			if (a > 4) {// Limit the size the obstacle can grow
				a = 4;
			}

			// This is the codes to make asteroid move, scale asteroid with time and
			// collision
			for (int j = 0; j < Max_Asteroids; j++) {
				asteriod[j].scaleTo(0.2 * a);// Make the size of asteroid smaller

				asteriod[j].move(c-1);// Get asteroids to move with a certain speed
				asteriod[j].rotateBy(-1);// Rotate the asteroid
				int s = 0;// counter for the future step, for s
				// Checks if the ship collides with asteroid
				if ((asteriod[j].isInside(ship.getX() - s, ship.getY() - s))) {
					asterod = LOSE_STATE;// Change it to lose state, ends game and will do
					// something
				}
			}

			// This is the code to make the second obstacle move, scaled the size, and
			// collision
			for (int j = 0; j < MAX_ROCK; j++) {
				evenMoreRocks[j].scaleTo(0.1 * a);
				evenMoreRocks[j].move(c - 4);
				evenMoreRocks[j].rotateBy(-1);
				rocks[j].scaleTo(1.3);// Make the size of asteroid smaller
				rocks[j].move(1 + c);// Get asteroids to move
				moreRocks[j].move( c /2 -3);
				moreRocks[j].rotateBy(-1);
				int s = 0;// counter for the future step, for s
				// Checks if the ship collides with asteroid
				if ((evenMoreRocks[j].isInside(ship.getX() - s, ship.getY() - s))) {
					asterod = LOSE_STATE;// Change it to lose state, ends game and will do
					// something
				}
				if ((rocks[j].isInside(ship.getX() - s, ship.getY() - s))) {
					asterod = LOSE_STATE;// Change it to lose state, ends game and will do
					// something
				}
				int y = 0;
				if ((moreRocks[j].isInside(ship.getX() - y, ship.getY() - y))) {
					asterod = LOSE_STATE;// Change it to lose state, ends game and will do
					// something
				}
			}

			// The is the code to make the asteroid move , scale image to a smaller size and
			// collision code
			for (int g = 0; g < MAX_FUEL; g++) {
				gas[g].move(1 + c);// Make the fuel image move to the left
				gas[g].scaleTo(0.2);// Scale image to 0.2
				int s = 0;// Counter for the future steps
				if ((gas[g].isInside(ship.getX() - s, ship.getY() - s))
						|| (gas[g].isInside(ship.getX() + 20, ship.getY() - s))
						|| (gas[g].isInside(ship.getX() - s, ship.getY() + 20))
						|| (gas[g].isInside(ship.getX() + 20, ship.getY() + 20))) {
					points += 1000;// Increase the point by this amount
					gas[g].setPosition(0, 0);// Moves the fuel image of screen if collides with ship

				}

			}

			// If the player loses do the follwoing......
			if (asterod == LOSE_STATE) {

				music.stop();// The background music will pause
				boom.play();// play this sound
				EZ.addImage("boom.png", ship.getX(), ship.getY());// place the explosion image when crashed
				Color u = new Color(250, 10, 10);// The color of the font
				EZText text = EZ.addText(595, 200, "You Lose", u, 100);// Will display a lose message
				EZText text2 = EZ.addText(595, 300, "Score:" + " " + points, u, 100);// will display the points
				EZText text3 = EZ.addText(595, 400, "Time:"+ " " + b + " " + "sec", u, 100);// will display the Time survived

				text.setFont("American Captain.TTF");// This is the font style used
				text2.setFont("American Captain.TTF");// This is the font style used
				text3.setFont("American Captain.TTF");// This is the font style used
				// player loses
			}

			// animate the sky
			sky1.moveForward(-6.5);
			sky2.moveForward(-6.5);

			if (sky1.getXCenter() < -500) {
				sky1.moveForward(2000);
			}
			if (sky2.getXCenter() < -500) {
				sky2.moveForward(2000);
			}

			EZ.refreshScreen();
		}
	}
}
