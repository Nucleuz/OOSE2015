package example;
import org.newdawn.slick.*;

public class Obstacle {
	private float x, y;
	private float width, height;
	//private Image obstacleImg;
	

	public Obstacle(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException{
		g.drawRect(x,y,width,height);
	}
	
	public float getX() {	return x;}

	public float getY() {	return y;}

	public float getWidth() {	return width;}

	public float getHeight() {	return height;}
	
	public void position(float x1, float y1){
		x = x1;
		y = y1;
	}
}
