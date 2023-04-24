package Main.Objects;

import java.awt.geom.Rectangle2D;

public class FinishLine  extends Object {

	public static final int DEFAULT_WIDTH = 25;
	public static final int DEFAULT_HEIGHT = 25;
	
	private int value;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public FinishLine(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
	}

	
	@Override
	public boolean checkIfHit(double x, double y, double width, double height) {
		Rectangle2D.Double finish = new Rectangle2D.Double(this.x, this.y, this.width, this.height);
		Rectangle2D.Double obj = new Rectangle2D.Double(x, y, width, height);
		if(finish.intersects(obj)) {
			return true;
		}
		return false;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
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

	public void setHeight(int height) {
		this.height = height;
	}


	
}
