package example;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class Ball {
	
	public float x,y;
	
	public float dx = 0.1f;
	public float dy = 0.1f;
	
	public int width = 20;
	public int height = 20;
	public int radius = (width + height) / 2;
	
	public Ball(float x, float y){
		this.x = x;
		this.y = y;
	}
}
