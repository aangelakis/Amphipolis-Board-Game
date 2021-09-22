package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public abstract class Character {
	private boolean isUsed = false; //checks if the character card is used
	//private String character;
	/**
	 * <b>Transformer:</b> It sets the character as used. <br>
	 * <b>Postcondition:</b> The character is used.
	 */
	public void useCharacter() {
		this.isUsed = true;
	}
	
	/**
	 * <b>Accessor:</b> Returns true if the character is used, false otherwise. <br>
	 * <b>Postcondition:</b> True is returned if the character is used, false otherwise.
	 * 
	 * @return true if the character is used, false otherwise.
	 */
	public boolean getIsUsed() {
		return this.isUsed;
	}
	 
	/**
	 * <b>Accessor:</b> Returns the string character of a character ( for example 
	 * 					"Assistant" for an assistant character ). <br>
	 * <b>Postcondition:</b> The string character of a character has been returned.
	 * 
	 * @return the string character of a character.
	 */
	abstract public String getCharacter();
	
	/**
	 * <b>Transformer:</b> It uses the power of a character. <br>
	 * <b>Postcondition:</b> The power of a character has been used.
	 */
	public abstract void power(Player player, int area);
	

	/**
	 * Returns a string representation of a character. <br>
	 * <b>Postcondition:</b> A string representation of a character has been returned.
	 * 
	 * @return a string representation of a character.
	 */
	public abstract String toString();
	
}
