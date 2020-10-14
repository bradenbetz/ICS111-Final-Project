// Fuel class made by Marcos Buccat
//November 30, 2017
import java.util.Random;

public class Fuel {
int x, y;
private EZImage fuel;
private int max_ScreenX;
private int max_ScreenY;
private int Speed;
//Constructor
 Fuel(String filename, int x, int y) {
	fuel= EZ.addImage(filename, 0, 0);
	max_ScreenX = x;
	max_ScreenY = x;
	initialize_pict();
}


private void initialize_pict() {
	// figures out how many asteroids can fit on screen vertically
	Random random = new Random();

			int multi = (int) max_ScreenY / fuel.getHeight()*2;
			// Generate a random number using that multiple to calculate the position on the
			// screen.
			int randomY = random.nextInt(multi) * fuel.getHeight()/2;
			setPosition(max_ScreenX, randomY);

			
			// The asteroid will have random speed
			int spd = random.nextInt(4) + 5;
			Speed = spd;	
}
public void setPosition(int posX, int posY) {
	x = posX;
	y = posY;
	setObstacleImagePosition(x, y);

}
void move(int c) {
	x = x-c - Speed;// moves to the left

	setPosition(x, y);
	if (x < 0) {
		initialize_pict();
	}
}

private void setObstacleImagePosition(int x, int y) {

	fuel.translateTo(x, y);
	
}


public void scaleTo(double d) {// This code scales the size of fuel image
fuel.scaleTo(d);	
}
public boolean isInside(int posx, int posy) {
	return fuel.isPointInElement(posx, posy);
}
}
