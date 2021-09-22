package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Digger extends Character{
	//private boolean isUsed = false; //checks if the character card is used
	private String character = "Digger";
	
	/**
	 * <b>Transformer:</b> It uses the power of the character Digger. <br>
	 * <b>Postcondition:</b> The power of the character Digger has been used.
	 */
	@Override
	public void power(Player player, int area) {
		System.out.println(player.getID());
		player.setHasDrewTilesFromBoard(false);	
	}

	/**
	 * Returns a string representation of the Digger. <br>
	 * <b>Postcondition:</b> A string representation of the Digger has been returned.
	 * 
	 * @return a string representation of the Digger.
	 */
	@Override
	public String toString() {
		return "I am a " + character;
	}

	/**
	 * <b>Accessor:</b> Returns the string character of the Digger. <br>
	 * <b>Postcondition:</b> The string character of the Digger has been returned.
	 * 
	 * @return the string character of the Digger.
	 */
	@Override
	public String getCharacter() {
		return this.character;
	}
	
}
