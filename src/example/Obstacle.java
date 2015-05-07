package example;

public class Obstacle{
	private float x, y;
	private int width, height;

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

		public void setX(int x) {
			this.x = x;
		}

		public float getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setX(float x) {
			this.x = x;
		}

		public void setY(float y) {
			this.y = y;
		}
}
