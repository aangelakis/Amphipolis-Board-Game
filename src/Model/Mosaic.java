package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Mosaic extends FindingTile{
	private MosaicColors col;
	private String category = "Mosaic";
	private int area = 1;
	
	/**
	 * <b>Constructor:</b> Creates a mosaic tile with the given color. <br>
	 * <b>Postcondition:</b> A new mosaic tile with the given color has been created.
	 * 
	 * @param col
	 */
	public Mosaic(MosaicColors col){
		this.col = col;
	}
	
	/**
	 * <b>Accessor:</b> Returns the color of a mosaic tile. <br>
	 * <b>Postcondition:</b> The color of the mosaic tile has been returned.
	 *
	 * @return the color of the mosaic tile
	 */
	public MosaicColors getCol() {
		return this.col;
	}
	
	/**
	 * Returns the string representation of a mosaic tile. <br>
	 * <b>Postcondition:</b> The string representation of a mosaic tile is returned.
	 * 
	 * @return the string representation of a mosaic tile.
	 */
	public String toString() {
		return col + " Mosaic";
	}
	
	/**
	 * <b>Accessor:</b> Returns the category of the mosaic tile. <br>
	 * <b>Postcondition:</b> The category of the mosaic tile is returned.
	 * 
	 * @return the category of the mosaic tile.
	 */
	public String getCategory() {
		return this.category;
	}

	/**
	 * <b>Accessor:</b> Returns the area of the mosaic tile ( area 1 ).
	 * <b>Postcondition:</b> The area of the mosaic tile has been returned.
	 * @return the area of the mosaic tile.
	 */
	@Override
	public int getArea() {
		return this.area;
	}
}
