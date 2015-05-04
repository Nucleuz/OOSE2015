package example;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Ball {
	
//	public Shape rect;
	
	public float x,y;
	
	public int width, height;
	
	public float dx = 0.2f;
	public float dy = 0.2f;
	
	public int widthSize = 10;
	public int heightSize = 10;
	public int radius = (widthSize + heightSize) / 2;
	
	public Ball(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
//		this.x2 = width;
//		this.y2 = height;
		
//		width = width-x;
//		height = height-y;
		
//		rect = new Rectangle(x,y,width,height);
	}
}
