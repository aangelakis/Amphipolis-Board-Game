package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Amphora extends FindingTile{
	private AmphoraColors col;
	private int area = 3;
	private String category = "Amphora";
	
	/**
	 * <b>Constructor:</b> Creates a new Amphora tile with the given color. <br>
	 * <b>Postcondition:</b> A new amphora tile with the given color has been created.
	 * 
	 * @param col
	 */
	public Amphora(AmphoraColors col){
		this.col = col;
	}
	
	/**
	 * <b>Accessor:</b> Returns the color of an amphora tile. <br>
	 * <b>Postcondition:</b> The color of an amphora tile has been returned.
	 * 
	 * @return the color of an amphora tile.
	 */
	public AmphoraColors getCol() {
		return this.col;
	}
	
	/**
	 * Returns the string representation of an amphora tile. <br>
	 * <b>Postcondition:</b> The string representation of an amphora tile is returned.
	 * 
	 * @return the string representation of an amphora tile.
	 */
	public String toString() {
		return col + " Amphora";
	}
	
	/**
	 * <b>Accessor:</b> Returns the category of the amphora tile. <br>
	 * <b>Postcondition:</b> The category of the amphora tile has been returned.
	 * 
	 * @return the category of a finding tile.
	 */
	@Override
	public String getCategory() {
		return this.category;
	}
	
	/**
	 * <b>Accessor:</b> Returns the area of the amphora tile. <br>
	 * <b>Postcondition:</b> The area of the amphora tile is returned.
	 * 
	 * @return the area of the amphora tile.
	 */
	public int getArea() {
		return this.area;
	}
}
