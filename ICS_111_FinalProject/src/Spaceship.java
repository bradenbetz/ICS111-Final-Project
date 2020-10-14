//Spaceship class made by Braden Betz
// November 12, 2017
public class Spaceship<aste> {

	static EZImage Ship;
	static int x, y;
	static float health = 10;
	int aste;
	private static EZImage[] ast = new EZImage[120];

	static int asteroid_counter=0;

	// create spaceship object
	Spaceship(String filename, int posX, int posY) {
		x = posX;
		y = posY;
		Ship = EZ.addImage(filename, posX, posY);

	}
	public static void addWalls(EZImage rocks) {// Add the image of the wall
		ast[asteroid_counter] = rocks;
		asteroid_counter++;
	}
	// set the x and y position of the ship
	void setShipPosition(int posX, int posY) {
		x = posX;
		y = posY;
		Ship.translateTo(posX, posY);
	}

	// get the xcenter of the ship
	public int getX() {
		return x;
	}

	// get the ycenter of the ship
	public int getY() {
		return y;
	}

	// the rate at which the ship moves up
	void moveUp(int boost) {
		y = y + boost;
		setShipPosition(x, y);

	}

	// rate at which the ship moves down
	void moveDown(int boost) {
		y = y - boost;
		setShipPosition(x, y);

	}

	

	void play() {
		// if the "W" key is pressed, move the ship up 5
		if (EZInteraction.isKeyDown('s')) {
			moveUp(10);

			// if the "S" key is pressed, move the ship down 5
		}  if (EZInteraction.isKeyDown('w')) {
			moveDown(10);

		}
	}
	
	


	}

