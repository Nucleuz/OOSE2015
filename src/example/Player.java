package example;

public class Player{

	public float x1, y1, x2, y2;
	private float width, height;

	
	public Player(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		width = x2-x1;
		height = y2-y1;
	}
	
	public void position(float x, float y){
		x1 = x;
		y1 = y;
		x2 = x + width;
		y2 = y+height;
	}

}
