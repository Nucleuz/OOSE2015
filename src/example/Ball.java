package example;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class Ball {
//	
//	protected float ballSpeed;
//	protected Vector2f direction;
//	
//	
//	public Ball(Vector2f pos, float ballSpeed){
//		this.ballSpeed = ballSpeed;
//		this.direction = direction.copy();
//		
//	}
//	
//	public void setDirection(Vector2f direction){
//		this.direction = direction.copy();
//	}
//	
//	public Vector2f getDirection(){
//		return direction;
//	}
//	
//	public void setSpeed(float speed){
//		ballSpeed = speed;
//	}
//	
//	public float getSpeed(){
//		return ballSpeed;
//	}
	public Vector2f pos;
	private Vector2f speed;
	private boolean active = true;
	
	
	public Ball(Vector2f pos, Vector2f speed){
		this.pos = pos;
		this.speed = speed;
	}

	public void update(int t){
		if(active){
			Vector2f realSpeed = speed.copy();
			realSpeed.scale(t/1000.0f);
			pos.add(realSpeed);
		}
	}
		
	public boolean isActive(){
			
		return active;
	}
	
}
