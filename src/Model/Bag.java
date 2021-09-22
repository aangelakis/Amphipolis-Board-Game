package Model;

import java.util.ArrayList;
import java.util.Collections;

//import Model.Tile;


/**
 * This class creates the Bag of the game.
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Bag {
	private static ArrayList<Tile> tiles; //The tiles in the bag
	private static int activeNumberOfTiles; //The number of tiles in the bag
	
	
	/**
	 * <b>Constructor:</b> Constructs a new bag and initializes its fields. <br>
	 * <b>Postcondition:</b> Creates a new bag with an ArrayList of tiles.
	 */
	public Bag(){
		Bag.activeNumberOfTiles = 0;
		tiles = new ArrayList<Tile>();
	}

	/**
	 * <b>Transformer:</b> Initializes and shuffles all the tiles in the bag <br>
	 * <b>Postcondition:</b> All of the tiles have been initialized and shuffled in the bag
	 */
	public void initBag() {
		for(MosaicColors mcol : MosaicColors.values()) { //putting the mosaic tiles in the bag
			for(int i=0; i<9; i++) {
				tiles.add(new Mosaic(mcol));
				Bag.activeNumberOfTiles++;
			}
		}
		
		for(int i=0; i<24; i++) { //putting the landslide and statue tiles in the bag
			tiles.add(new LandslideTile());
			Bag.activeNumberOfTiles++;
			if(i<12) {
				tiles.add(new Caryatid());
				Bag.activeNumberOfTiles++;
			}
			else {
				tiles.add(new Sphinx());
				Bag.activeNumberOfTiles++;
			}
		}
		
		for(AmphoraColors acol : AmphoraColors.values()) { //putting the amphora tiles in the bag
			for(int i=0; i<5; i++) {
				tiles.add(new Amphora(acol));
				Bag.activeNumberOfTiles++;
			}
		}
		
		for(int i=0; i<30; i++) {
			if(i<10) {
				tiles.add(new Skeleton(true, true));
			}else if(i<20) {
				tiles.add(new Skeleton(false, true));
			}else if(i<25) {
				tiles.add(new Skeleton(true,false));
			}else {
				tiles.add(new Skeleton(false, false));
			}
			Bag.activeNumberOfTiles++;
		}
		Collections.shuffle(tiles);
	}
	
	/**
	 * <b>Observer:</b> Returns true if the bag is empty, false otherwise. <br>
	 * <b>Postcondition:</b> Returns true if the bag is empty, false otherwise.
	 * 
	 * @return true if the bag is empty, false otherwise.
	 */
	public boolean isEmptyBag() {
		if(Bag.activeNumberOfTiles == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * <b>Transformer:</b> Removes a tile from the bag. <br>
	 * <b>Postcondition:</b> A tiles has been removed from the bag.
	 * 
	 * @param tile
	 */
	public void removeTile(Tile tile) {
		tiles.remove(tile);
		Bag.activeNumberOfTiles--;
		
	}
	
	/**
	 * <b>Accessor:</b> Returns the number of tiles in the bag. <br>
	 * <b>Postcondition:</b> The number of tiles in the bag has been returned.
	 * 
	 * @return the number of tile in the bag 
	 */
	public int getActiveNumberOfTiles() {
		return Bag.activeNumberOfTiles;
	}
	
	/**
	 * <b>Accessor:</b> Returns the tiles in the bag. <br>
	 * <b>Postcondition:</b> The tiles in the bag have been returned.
	 * 
	 * @return the tiles in the bag
	 */
	public ArrayList<Tile> getTiles(){
		return tiles;
	}
	
}