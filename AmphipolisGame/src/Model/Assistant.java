package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Assistant extends Character {
	//private boolean isUsed = false; //checks if the character card is used
	private String character = "Assistant";
	
	/**
	 * <b>Transformer:</b> It uses the power of the character Assistant. <br>
	 * <b>Postcondition:</b> The power of the character Assistant has been used.
	 */
	@Override
	public void power(Player player, int area) {
		player.setHasChoseCharacter(true);
		player.setHasDrewTilesFromBoard(false);
		player.setNumOfTilesHeDrew(1);
		player.setChoice(0);
	}
	
	/**
	 * Returns a string representation of the Assistant. <br>
	 * <b>Postcondition:</b> A string representation of the Assistant has been returned.
	 * 
	 * @return a string representation of the Assistant.
	 */
	@Override
	public String toString() {
		return "I am an " + character;
	}

	/**
	 * <b>Accessor:</b> Returns the string character of the Assistant. <br>
	 * <b>Postcondition:</b> The string character of the Assistant has been returned.
	 * 
	 * @return the string character of the Assistant.
	 */
	@Override
	public String getCharacter() {
		return this.character;
	}

}
