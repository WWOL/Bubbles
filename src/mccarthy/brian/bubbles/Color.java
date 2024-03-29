package mccarthy.brian.bubbles;

public enum Color {
	
	RED(1, 0, 0),
	GREEN(0, 1, 0),
	BLUE(0, 0, 1),
	WHITE(1, 1, 1);
	
	double red = 0;
	double green = 0;
	double blue = 0;
	double alpha = 0;
	
	Color(double red, double green, double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	Color(double red, double green, double blue, double alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

	public double getRed() {
		return red;
	}
	
	public void setRed(double red) {
		this.red = red;
	}
	
	public double getGreen() {
		return green;
	}
	
	public void setGreen(double green) {
		this.green = green;
	}
	
	public double getBlue() {
		return blue;
	}
	
	public void setBlue(double blue) {
		this.blue = blue;
	}
	
	public double getAlpha() {
		return alpha;
	}
	
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	
}
