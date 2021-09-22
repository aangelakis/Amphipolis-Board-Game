package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Archaeologist extends Character{
	//private boolean isUsed = false; //checks if the character card is used
	private String character = "Archaeologist";
	
	/**
	 * <b>Transformer:</b> It uses the power of the character Archaeologist. <br>
	 * <b>Postcondition:</b> The power of the character Archaeologist has been used.
	 */
	@Override
	public void power(Player player, int area) {
		player.setHasDrewTilesFromBoard(false);
		player.setChoice(area - 5);
		
	}

	/**
	 * Returns a string representation of the Archaeologist. <br>
	 * <b>Postcondition:</b> A string representation of the Archaeologist has been returned.
	 * 
	 * @return a string representation of the Archaeologist.
	 */
	@Override
	public String toString() {
		return "I am an " + character;
	}

	/**
	 * <b>Accessor:</b> Returns the string character of the Archaeologist. <br>
	 * <b>Postcondition:</b> The string character of the Archaeologist has been returned.
	 * 
	 * @return the string character of the Archaeologist.
	 */
	@Override
	public String getCharacter() {
		return this.character;
	}

}
