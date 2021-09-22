package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Professor extends Character{
	//private boolean isUsed = false; //checks if the character card is used
	private String character = "Professor";
	
	/**
	 * <b>Transformer:</b> It uses the power of the character Professor. <br>
	 * <b>Postcondition:</b> The power of the character Professor has been used.
	 */
	@Override
	public void power(Player player, int area) {
		player.setHasDrewTilesFromBoard(false);
		player.setChoice(area + 5);
		player.setNumOfTilesHeDrew(-1);
	}

	/**
	 * Returns a string representation of the Professor. <br>
	 * <b>Postcondition:</b> A string representation of the Professor has been returned.
	 * 
	 * @return a string representation of the Professor.
	 */
	@Override
	public String toString() {
		return "I am a " + character;
	}

	/**
	 * <b>Accessor:</b> Returns the string character of the Professor. <br>
	 * <b>Postcondition:</b> The string character of the Professor has been returned.
	 * 
	 * @return the string character of the Professor.
	 */
	@Override
	public String getCharacter() {
		return this.character;
	}

}
