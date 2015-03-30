package example;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ball{

	private Image BallImg;
	//private float yspeed;
	private float x1,y1,x2,y2;
	private float width,height;
	
	
	public Ball(float x1,float y1,float x2,float y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		//this.yspeed = yspeed;
		width = x2-x1;
		height = y2-y1;
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException{
		BallImg = new Image("images/Ball.png");
		BallImg.draw(x1,y1);
	}
	
	public float getX1() {	return x1;}

	public float getY1() {	return y1;}

	public float getX2() {	return x2;}

	public float getY2() {	return y2;}
	
	
	public void position(float x, float y){
		x1 = x;
		y1 = y;
		x2 = x + width;
		y2 = y+height;
	}
	
	

	
}
