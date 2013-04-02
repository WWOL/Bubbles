package mccarthy.brian.bubbles;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class GLRenderer {

	private static List<Square> toRemove = new ArrayList<Square>();

	public static void setup() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		Display.setTitle("Bubbles by WWOL!");

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		Bubbles.getInstance().setRunning(true);
	}

	public static void run() throws Exception {
		System.out.println("Bubbles count: " + Bubbles.getInstance().getSquares().size());
		while (!Display.isCloseRequested() && Bubbles.getInstance().isRunning()) {
			Display.sync(60);

			pollKeyboard();
			pollMouse();

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
			List<Square> squares = Bubbles.getInstance().getSquares();
			if (squares.size() == 0) {
				Bubbles.getInstance().gameover();
			}
			for (Square square : squares) {
				if (!square.isVisable()) {
					continue;
				}
				// GL render start
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glColor3d(square.getColor().getRed(), square.getColor().getGreen(), square.getColor().getBlue());
				GL11.glVertex2f(square.getX(), square.getY());
				GL11.glVertex2f(square.getX() + square.getWidth(), square.getY());
				GL11.glVertex2f(square.getX() + square.getWidth(), square.getY() + square.getHeight());
				GL11.glVertex2f(square.getX(), square.getY() + square.getHeight());
				GL11.glEnd();
				// GL render end
				square.setY(square.getY() + 1);
				if (square.getY() > Display.getHeight()) {
					switch(Bubbles.getInstance().getGamemode()) {
					case SINGULAR:
						square.setVisable(false);
						toRemove.add(square);
						break;
					case LIVES:
					case TIMED:
						square.setY(0);
						break;
					default:
						throw new RuntimeException("A gamemode has not been setup properly!");
					}
				}
			}
			for (Square square : toRemove) {
				Bubbles.getInstance().removeSquare(square);
			}
			toRemove.clear();
			Display.update();
		}
		System.out.println("Score: " + Scoring.getScore());
		Display.destroy();
	}

	public static void pollKeyboard() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_A) {
				if (Keyboard.getEventKeyState()) {
					System.out.println("A pressed.");
					Scoring.setAmp(Scoring.getAmp() + 1);
				} else {
					System.out.println("A released.");
				}
			} else if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
				if (Keyboard.getEventKeyState()) {
					Bubbles.getInstance().shutdown();
				}
			}
		}
	}

	public static void pollMouse() {
		while (Mouse.next()) {
			if (Mouse.getEventButton() == 0) {
				if (Mouse.getEventButtonState()) {
					System.out.println("Mouse down.");
					boolean hit = false;
					for (Square square : Bubbles.getInstance().getSquares()) {
						if (square.isIntersecting(Mouse.getEventX(), Mouse.getEventY())) {
							square.execute();
							hit = true;
							square.setVisable(false);
							toRemove.add(square);
						}
					}
					if (!hit) {
						Scoring.addMiss();
						System.out.println("MISS!!!!!!!!!!!!!!");
					}
					if (Scoring.getMisses() == 3) {
						Bubbles.getInstance().gameover();
					}
				} else {
					System.out.println("Mouse up.");
				}
			}
		}
	}

}