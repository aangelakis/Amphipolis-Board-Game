package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Statue extends FindingTile{
	private String category = "Statue";
	private int area = 2;
	/**
	 * <b>Accessor:</b> Returns the category of the Statue tile. <br>
	 * <b>Postcondition:</b> The category of the Statue tile is returned.
	 * 
	 * @return the category of the Statue tile.
	 */
	@Override
	public String getCategory() {
		return this.category;
	}

	/**
	 * Returns the string representation of a Statue tile. <br>
	 * <b>Postcondition:</b> The string representation of a Statue tile is returned.
	 * 
	 * @return the string representation of a Statue tile.
	 */
	@Override
	public String toString() {
		return null;
	}

	/**
	 * <b>Accessor:</b> Returns the area of the statue tile ( area 2 ).
	 * <b>Postcondition:</b> The area of the statue tile has been returned.
	 * @return the area of the statue tile.
	 */
	@Override
	public int getArea() {
		return this.area;
	}
}
