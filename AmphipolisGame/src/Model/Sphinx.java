package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Sphinx extends Statue{
	private String category = "Sphinx";
	
	/**
	 * Returns the string representation of a sphinx tile. <br>
	 * <b>Postcondition:</b> The string representation of a sphinx tile is returned.
	 * 
	 * @return the string representation of a sphinx tile.
	 */
	public String toString() {
		return "Sphinx";
	}
	
	/**
	 * <b>Accessor:</b> Returns the category of the Sphinx tile. <br>
	 * <b>Postcondition:</b> The category of the Sphinx tile is returned.
	 * 
	 * @return the category of the Sphinx tile.
	 */
	public String getCategory() {
		return this.category;
	}
}
