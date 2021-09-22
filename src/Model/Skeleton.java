package Model;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Skeleton extends FindingTile{
	private final boolean isUp;
	private final boolean isOld;
	private String category = "Skeleton";
	private int area = 4;

	/**
	 * <b>Constructor:</b> Creates a new upper/lower and young/old skeleton tile body. <br>
	 * <b>Postcondition:</b> The skeleton tile has been created.
	 *  
	 * @param isUp
	 * @param isOld
	 */
	public Skeleton(boolean isUp, boolean isOld){
		this.isUp = isUp;
		this.isOld = isOld;
	}
	
	/**
	 * <b>Accessor:</b> Returns true if the skeleton tile is an upper body one, false otherwise. <br>
	 * <b>Postcondition:</b> Returns true if the skeleton tile is an upper body one, false otherwise.
	 * 
	 * @return true if the skeleton tile is an upper body one, false otherwise.
	 */
	public boolean getIsUp() {
		return this.isUp;
	}
	
	/**
	 * <b>Accessor:</b> Returns true if the skeleton tile is an old one, false otherwise. <br>
	 * <b>Postcondition:</b> Returns true if the skeleton tile is an old one, false otherwise.
	 * 
	 * @return true if the skeleton tile is an old one, false otherwise
	 */
	public boolean getIsOld() {
		return this.isOld;
	}
	
	/**
	 * Returns the string representation of a skeleton tile. <br>
	 * <b>Postcondition:</b> The string representation of a skeleton tile is returned.
	 * 
	 * @return the string representation of a skeleton tile.
	 */
	public String toString() {
		if(isUp && isOld)
			return "Upper body of an old skeleton";
		else if(isUp && !isOld)
			return "Upper body of a young skeleton";
		else if(!isUp && isOld)
			return "Lower body of an old skeleton";
		else
			return "Lower body of a young skeleton";
	}

	/**
	 * <b>Accessor:</b> Returns the category of the skeleton tile. <br>
	 * <b>Postcondition:</b> The category of the skeleton tile is returned.
	 * 
	 * @return the category of the skeleton tile.
	 */
	@Override
	public String getCategory() {
		return this.category;
	}

	/**
	 * <b>Accessor:</b> Returns the area of the skeleton tile ( area 4 ).
	 * <b>Postcondition:</b> The area of the skeleton tile has been returned.
	 * @return the area of the skeleton tile.
	 */
	@Override
	public int getArea() {
		return this.area;
	}
}
