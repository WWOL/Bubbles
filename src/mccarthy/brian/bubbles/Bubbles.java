package mccarthy.brian.bubbles;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;

public class Bubbles {

	private static Bubbles instance;
	private boolean running;
	private List<Square> squareList;
	private Gamemode gamemode; 

	public static void main(String[] args) {
		instance = new Bubbles();
		instance.start();
	}

	public void start() {
		instance.squareList = new ArrayList<Square>();
		instance.running = false;
		GLRenderer.setup();
		instance.setupSquares();
		//instance.gamemode = Gamemode.SINGULAR;
		instance.gamemode = Gamemode.TIMED;
		try {
			GLRenderer.run();
		} catch (Exception e) {
			throw new RuntimeException("There was an error while rendering!", e);
		}
	}

	public void setupSquares() {
		int x = 10;
		Square s1;
		System.out.println("Display size: " + Display.getWidth());
		while (x < Display.getWidth()) {
			System.out.println("Square");
			s1 = new Square(x, 10, 20, 20);
			s1.setColor(Square.randomColor());
			squareList.add(s1);
			x+= 30;
		}
		s1 = new Square(400, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
	}

	/*public void setupSquares() {
		Square s1;
		s1 = new Square(10, 10, 20, 20);
		s1.setColor(Color.BLUE);
		squareList.add(s1);
		s1 = new Square(40, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		s1 = new Square(70, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		s1 = new Square(100, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		s1 = new Square(130, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		s1 = new Square(160, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		s1 = new Square(190, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		s1 = new Square(220, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		s1 = new Square(250, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		s1 = new Square(280, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		s1 = new Square(310, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		s1 = new Square(340, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		s1 = new Square(370, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		s1 = new Square(400, 10, 20, 20);
		s1.setColor(Square.randomColor());
		squareList.add(s1);
		//squareList.add(Square.randomSquare());
	}//*/

	public static Bubbles getInstance() {
		return instance;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public synchronized List<Square> getSquares() {
		return squareList;
	}

	public void removeSquare(Square square) {
		squareList.remove(square);
	}

	public void shutdown() {
		running = false;
	}

	public void gameover() {
		JOptionPane.showMessageDialog(null, "Game Over!\nYou scored: " + Scoring.getScore() + " (" + Scoring.getTotalSquaresScored() + " squares)");
		Bubbles.getInstance().shutdown();
	}

	public Gamemode getGamemode() {
		return gamemode;
	}

	public void setGamemode(Gamemode gamemode) {
		this.gamemode = gamemode;
	}
}
