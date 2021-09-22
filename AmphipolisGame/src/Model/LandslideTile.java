package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class LandslideTile implements Tile{
	private String category = "Landslide";
	private int area = 5;
	/**
	 * <b>Accessor:</b> Returns the cateogry of the landslide tile. <br>
	 * <b>Postcondition:</n> The landslide category has been returned.
	 */
	@Override
	public String getCategory() {
		return this.category;
	}
	
	/**
	 * Returns the string representation of a landslide tile. <br>
	 * <b>Postcondition:</b> The string representation of a landslide tile is returned.
	 * 
	 * @return the string representation of a landslide tile.
	 */
	public String toString() {
		return "LandslideTile";
	}

	/**
	 * <b>Accessor:</b> Returns the area of the landslide tile.
	 * <b>Postcondition:</b> The area of the landslide tile has been returned.
	 */
	public int getArea() {
		return this.area;
	}
}
