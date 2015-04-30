package example;

public class Obstacle {
	public float x, y;
	private float width, height;

	public Obstacle(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public void position(float x1, float y1){
		x = x1;
		y = y1;
	}
}
