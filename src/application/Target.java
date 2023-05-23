package application;

public class Target {
	int width;
	int height;
	int x;
	int y;
	
	
	public Target(int w, int h, int x, int y) {
		width = w;
		height = h;
		this.x = x;
		this.y = y;
	}
	public void setWidth(int w) {
		width = w;
	}
	public void setHeight(int h) {
		height = h;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
		
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
