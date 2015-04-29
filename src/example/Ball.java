package example;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Ball {
	
	private Vector2f pos;
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
		public void render(GameContainer gc, Graphics g) throws SlickException
		{
			g.setColor(Color.red);
			g.fillOval(pos.getX()-10,pos.getY()-20,20,20);
		}
		
		public boolean isActive(){
			return active;
		}
	
}
