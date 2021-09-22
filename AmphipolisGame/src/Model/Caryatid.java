package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Caryatid extends Statue{
	private String category = "Caryatid";
	
	/**
	 * Returns the string representation of a caryatid tile. <br>
	 * <b>Postcondition:</b> The string representation of a caryatid tile is returned.
	 * 
	 * @return the string representation of a caryatid tile.
	 */
	public String toString() {
		return "Caryatid";
	}
	
	/**
	 * <b>Accessor:</b> Returns the category of the Caryatid Tile. <br>
	 * <b>Postcondition:</b> The category of the Caryatid Tile has been returned.
	 * 
	 * @return the category of the Caryatid Tile.
	 */
	public String getCategory() {
		return this.category;
	}
}
