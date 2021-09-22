package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public abstract class FindingTile implements Tile{
	//private String category;
	
	/**
	 * <b>Accessor:</b> Returns the category of a finding tile. <br>
	 * <b>Postcondition:</b> A finding tile's category has been returned.
	 */
	@Override
	abstract public String getCategory();

	/**
	 * Returns the string representation of a finding tile. <br>
	 * <b>Postcondition:</b> The string representation of a finding tile is returned.
	 * 
	 * @return the string representation of a finding tile.
	 */
	abstract public String toString();
}
