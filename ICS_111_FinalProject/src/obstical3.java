
//Made by Marcos Buccat
// December 2, 2017
import java.util.Random;

public class obstical3 {
	int x;
	int y;
	int posX; // the X postion of the asteroid
	int posY;// The Y postion of the asteroid
	int directionX;// the x direction asteroid
	int directionY;// the y direction of asteroid
	private int max_ScreenX; // Max screen resolution horizontally
	private int max_ScreenY;// Max screen resoluion vertically
	private static EZImage asteroid;// Add image of the asteroid
	private int Speed;// The speed of the asteroid
	private static int rotatationSpeed;// The speed of the asteroids rotation
	Random random = new Random();

	obstical3(String filename, int maxx, int maxy) {
		asteroid = EZ.addImage(filename, 0, 0);
		max_ScreenX = maxy;
		max_ScreenY = maxy;
		initialize_pict();// create and postion the asteroids
	}

	void initialize_pict() {

		// figures out how many asteroids can fit on screen vertiaclly
		int multi = (int) max_ScreenY / asteroid.getHeight() * 2;
		// Generate a random number using that multiple to calculate the position on the
		// screen.
		int randomY = random.nextInt(multi) * asteroid.getHeight() / 2;
		setPosition(max_ScreenX, randomY);

		// The asteroid will have random speed
		int spd = random.nextInt(8) + 5;
		Speed = spd;
	}

	public void setPosition(int posX, int posY) {
		x = posX;
		y = posY;
		setObstacleImagePosition(x, y);

	}

	public void setObstacleImagePosition(int posX, int posY) {
		asteroid.translateTo(posX, posY);
	}

	public boolean isInside(int posx, int posy) {
		return asteroid.isPointInElement(posx, posy);
	}

	public void scaleTo(double d) {
		// TODO Auto-generated method stub
		asteroid.rotateBy(d);
	}

	void move(int c) {
		x = x - c - Speed;// moves to the left

		setPosition(x, y);
		if (x < 0) {
			initialize_pict();
		}
	}

	public void rotateBy(int i) {
		asteroid.rotateBy(i);
	}
}
