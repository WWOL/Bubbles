package mccarthy.brian.bubbles;

public class Scoring {

	private static int score = 0;
	private static int squares = 0;
	private static int amp = 1;
	private static int misses = 0;
	
	public static void addScore(Square square) {
		System.out.println("Scoring: " + square.getColor());
		switch(square.getColor()) {
		case RED:
			score += 1 * amp;
			System.out.println("Added: " + 1*amp);
			break;
		case GREEN:
			score += 2 * amp;
			System.out.println("Added: " + 2*amp);
			break;
		case BLUE:
			score += 3 * amp;
			System.out.println("Added: " + 3*amp);
			break;
		case WHITE:
			score += 4 * amp;
			System.out.println("Added: " + 5*amp);
			break;
		default:
			throw new RuntimeException("A color has not been scored properly!"); 
		}
		squares++;
		if (squares % 20 == 0) {
			amp++;
		}
	}
	
	public static void addMiss() {
		misses++;
	}
	
	public static int getScore() {
		return score;
	}
	
	public static int getAmp() {
		return amp;
	}
	
	public static void setAmp(int amp) {
		Scoring.amp = amp;
	}
	
	public static int getTotalSquaresScored() {
		return squares;
	}

	public static int getMisses() {
		return misses;
	}

	public static void setMisses(int misses) {
		Scoring.misses = misses;
	}
	
}
