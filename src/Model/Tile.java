package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public interface Tile {
	
	/**
	 * <b>Accessor:</b> Returns the category of a tile. <br>
	 * <b>Postcondition:</b> The category of the tile has been returned.
	 * 
	 * @return the category of a tile
	 */
	public String getCategory();
	
	/**
	 * Returns the string representation of a tile. <br>
	 * <b>Postcondition:</b> The string representation of a tile is returned.
	 * 
	 * @return the string representation of a tile.
	 */
	public String toString();

	/**
	 * <b>Accessor:</b> Returns the area of a tile (1 for mosaic, 2 for statue, 3 for amphora, 4 for skeleton,
	 * 5 for landslide).
	 * <b>Postcondition:</b> The area of a tile has been returned.
	 * @return the area of a tile.
	 */
	public int getArea();
}
