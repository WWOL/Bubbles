package mccarthy.brian.bubbles;

import java.util.Random;

public class Square {

	private int height;
	private int width;
	private int x;
	private int y;
	private boolean visable;
	private Color color;

	public Square(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.visable = true;
		this.color = Color.values()[new Random().nextInt(Color.values().length)];
	}

	public static Square randomSquare() {
		Random rand = new Random();
		return new Square(rand.nextInt(100), rand.nextInt(100), rand.nextInt(100), rand.nextInt(100));
	}
	
	public void execute() {
		Scoring.addScore(this);
	}

	public boolean isIntersecting(int x, int y) {
		boolean xIntercept = x < (this.x + this.width) && x > this.x;
		boolean yIntercept = y < (this.y + this.height) && y > this.y;
		return xIntercept && yIntercept;
	}

	public static Color randomColor() {
		return Color.values()[new Random().nextInt(Color.values().length)];
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
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

	public boolean isVisable() {
		return visable;
	}

	public void setVisable(boolean visable) {
		this.visable = visable;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
