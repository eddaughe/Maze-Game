package Main.Objects;

import java.awt.geom.Rectangle2D;

public class Wall extends Object {

	public static final double DEFAULT_WIDTH = 25;
	public static final double DEFAULT_HEIGHT = 25;
	
	private double x;
	private double y;
	private double height;
	private double width;
	
	public Wall(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public boolean checkIfHit(double x, double y, double width, double height) {
		Rectangle2D.Double wall = new Rectangle2D.Double(this.x, this.y, this.width, this.height);
		Rectangle2D.Double obj = new Rectangle2D.Double(x, y, width, height);
		if(wall.intersects(obj)) {
			return true;
		}
		return false;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	

}
