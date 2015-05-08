package example;

public class Obstacle{
	
	// Declaring a few variables
	private float x, y;
	private int width, height;

	// Constructer
	public Obstacle(int x, int y, int width, int height) {
		this.setX(x);
		this.setY(y);
		this.width = width;
		this.height = height;
	}
	
	// Getters and setters methods for the obstacles.
		public float getX() {
			return x;
		}

		public void setX(float x) {
			this.x = x;
		}

		public float getY() {
			return y;
		}

		public void setY(float y) {
			this.y = y;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}
}
