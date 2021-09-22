package Model;

import java.util.ArrayList;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Board {
	private	ArrayList<Tile> skeletonArea;		//an array list that stores the Skeleton tiles in  the board area
	private	ArrayList<Tile> amphoraArea;			//an array list that stores the Amphora tiles in  the board area
	private	ArrayList<Tile> mosaicArea;			//an array list that stores the Mosaic tiles in  the board area
	private	ArrayList<Tile> statueArea;			//an array list that stores the Statue tiles in  the board area
	private	ArrayList<Tile> landslideArea;	//an array list that stores the Landslide tiles in  the board area
	private int currentPlayerID;
	private boolean isFinished;
	
	/**
	 * <b>Constructor:</b> Initializes the ArrayLists of the 5 areas and some variables (for example
	 * 					   who is the current player playing, if the game is finished and if the game has started). <br>
	 * <b>Postcondition:</b> A board has been created and its variables have been initialized.
	 */
	public Board(){
		skeletonArea = new ArrayList<>();
		amphoraArea = new ArrayList<>();
		mosaicArea = new ArrayList<>();
		statueArea = new ArrayList<>();
		landslideArea = new ArrayList<>();
		currentPlayerID = 1;
		isFinished = false;	
	}
	
	/**
	 * <b>Accessor:</b> Returns the tiles in the skeleton area. <br>
	 * <b>Postcondition:</b> The tiles in the skeleton area have been returned.
	 * 
	 * @return the tiles in the skeleton area.
	 */
	public ArrayList<Tile> getSkeletonArea(){
		return this.skeletonArea;
	}
	
	/**
	 * <b>Accessor:</b> Returns the tiles in the amphora area. <br>
	 * <b>Postcondition:</b> The tiles in the amphora area have been returned.
	 * 
	 * @return the tiles in the amphora area.
	 */
	public ArrayList<Tile> getAmphoraArea(){
		return this.amphoraArea;
	}
	/**
	 * <b>Accessor:</b> Returns the tiles in the mosaic area. <br>
	 * <b>Postcondition:</b> The tiles in the mosaic area have been returned.
	 * 
	 * @return the tiles in the mosaic area.
	 */
	public ArrayList<Tile> getMosaicArea(){
		return this.mosaicArea;
	}
	
	/**
	 * <b>Accessor:</b> Returns the tiles in the landslide area. <br>
	 * <b>Postcondition:</b> The tiles in the landslide area have been returned.
	 * 
	 * @return the tiles in the landslide area.
	 */
	public ArrayList<Tile> getLandslideArea(){
		return this.landslideArea;
	}
	
	/**
	 * <b>Accessor:</b> Returns the tiles in the statue area. <br>
	 * <b>Postcondition:</b> The tiles in the statue area have been returned.
	 * 
	 * @return the tiles in the statue area.
	 */
	public ArrayList<Tile> getStatueArea(){
		return this.statueArea;
	}
	
	/**
	 * <b>Accessor:</b> Returns the current player playing. <br>
	 * <b>Postcondition:</b> The current player playing has been returned.
	 * 
	 * @return the current player playing.
	 */
	public int getCurrentPlayer() {
		return this.currentPlayerID;
	}
	
	/**
	 * <b>Transformer:</b> Sets the player with the ID as the current player playing. <br>
	 * <b>Postcondition:</b> The player with ID has been set as the current player playing.
	 * 
	 * @param ID
	 */
	public void setCurrentPlayer(int ID) {
		this.currentPlayerID = ID;
	}
	
	/**
	 * <b>Observer:</b> Returns true if the game has finished, false otherwise. <br>
	 * <b>Postcondition:</b> True is returned if the game has finished, false otherwise.
	 * 
	 * @return true if the game has finished, false otherwise
	 */
	public boolean hasFinished() {
		return this.isFinished;
	}

	/**
	 * <b>Transformer:</b> It finishes the game by changing the variable isFinished to true. <br>
	 * <b>Precondition:</b> The bag should be empty or the landslide area should be full with tiles. <br>
	 * <b>Postcondition:</b> The game is finished.
	 * 
	 * @param bag
	 */
	public void finishGame(Bag bag) {
		if(bag.isEmptyBag() || landslideArea.size() >= 16)
			this.isFinished = true;
	}
	
	/**
	 * <b>Transformer:</b> It adds a tile to an area of the board, depending on its category. <br>
	 * <b>Postcondition:</b> A ti le is added to an area of the board, depending on its category.
	 * 
	 * @param tile
	 */
	public void addTileToArea(Tile tile) {
		if(tile.getCategory() == "Skeleton")
			skeletonArea.add(tile);
		else if(tile.getCategory() == "Amphora")
			amphoraArea.add(tile);
		else if(tile.getCategory() == "Mosaic")
			mosaicArea.add(tile);
		else if(tile.getCategory() == "Landslide")
			landslideArea.add(tile);
		else
			statueArea.add(tile);
		System.out.println("SkeletonArea Size: " + skeletonArea.size());
		System.out.println("AmphoraArea Size: " + amphoraArea.size());
		System.out.println("MosaicArea Size: " + mosaicArea.size());
		System.out.println("LandslideArea Size: " + landslideArea.size());
		System.out.println("StatueArea Size: " + statueArea.size());
		System.out.println("===========================");
	}
	
	/**
	 * <b>Transformer:</b> It removes a tile from an area of the board, depending on its category. <br>
	 * <b>Postcondition:</b> A tile from an area of the board has been removed.
	 * 
	 * @param tile
	 */
	public void removeTileFromArea(Tile tile) {
		if(tile.getCategory() == "Skeleton") {
			this.skeletonArea.remove(tile);
		}
		else if(tile.getCategory() == "Amphora") {
			amphoraArea.remove(tile);
		}
		else if(tile.getCategory() == "Mosaic") {
			mosaicArea.remove(tile);
		}
		else {
			statueArea.remove(tile);
		}
	}
}
