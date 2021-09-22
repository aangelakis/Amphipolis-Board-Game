package Model;

import java.util.ArrayList;
import java.util.Random;

//import Controller.Controller;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Player {

	private  String name; //the name of the player
	private ArrayList<Tile> hisTiles; //His tiles' inventory
	private ArrayList<Character> hisCharacters; //His characters' inventory
	private int score; //His score
	private int choice; //Choose tiles from what area
	private final int ID; //His ID
	private boolean hasChoseCharacter; //A boolean variable to see if the player has chose a character this round
	private boolean hasPlayed; //A boolean variable to see if the player has played this round (for example he drew tiles from the bag and draw tiles from the board)
	private boolean hasDrewTilesFromBag; //A boolean variable to see if the player has drew tiles from the bag this round 
	private boolean hasDrewTilesFromBoard; //A boolean variable to see if the player has drew tiles from the board this round
	private int numOfMosaic, numOfSkeleton, numOfStatue, numOfAmphora; //Four variables to know how many of each tiles the player has.
	private int numOfTilesHeDrew; //A variable to watch the number of tiles the player has drawn each round.
	private int numOfCaryatid, numOfSphinx; //Two variables to know how many Caryatid and Sphinx tiles the player has.
	private int skeletonScore, amphoraScore, caryatidScore, sphinxScore, mosaicScore; //All the scores of the player.
	private Character currentCharacterUsed;
	private int firstChoice, secondChoice, thirdChoice, fourthChoice;
	
	/**
	 * <b>Constructor:</b> Constructs a new player with the given name and ID <br> 
	 * <b>Postcondition:</b> Creates a new player with the given name and ID and 
	 * 						 it also initializes some variables (for example initialize
	 * 						 variables that give us information about his score and the area
	 * 						 to choose tiles from).
	 * @param name
	 * @param ID
	 */
	public Player(String name, int ID){
		this.name = name;
		this.ID = ID;
		this.choice = 0;
		this.score = 0;
		hisTiles = new ArrayList<Tile>();
		hisCharacters = new ArrayList<Character>();
		hasChoseCharacter = false;
		hasPlayed = false;
		hasDrewTilesFromBag = false;
		numOfAmphora = 0;
		numOfMosaic = 0;
		numOfSkeleton = 0;
		numOfStatue = 0;
		numOfTilesHeDrew = 0;
		numOfCaryatid = 0;
		numOfSphinx = 0;
		skeletonScore = 0;
		amphoraScore = 0;
		caryatidScore = 0;
		sphinxScore = 0;
		mosaicScore = 0;
		currentCharacterUsed = null;
		firstChoice = 0;
		secondChoice = 0;
		thirdChoice = 0;
		fourthChoice = 0;
	}
	
	/**
	 * <b>Accessor:</b> Returns the name of a player. <br>
	 * <b>Postcondition:</b> The name of the player has been returned.
	 *
	 * @return the name of a player
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * <b>Transformer:</b> It sets the name of a player. <br>
	 * <b>Postcondition:</b> The name of the player is changed to name. 
	 * 
	 * @param name the new name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * <b>Accessor:</b> Returns the ID of a player. <br>
	 * <b>Postcondition:</b> The ID of the player has been returned.
	 *
	 * @return the ID of the player
	 */
	public int getID() {
		return this.ID;
	}
	
	/**
	 * <b>Transformer:</b> It initializes the 4 characters into his ArrayList of characters. <br>
	 * <b>Postcondition:</b> The 4 characters have been succesfully intialized into his ArrayList of characters.
	 */
	public void initCharacters() {
		Character assistant = new Assistant();
		Character archaeologist = new Archaeologist();
		Character digger = new Digger();
		Character professor = new Professor();
		hisCharacters.add(assistant);
		hisCharacters.add(archaeologist);
		hisCharacters.add(digger);
		hisCharacters.add(professor);
	}
	
	/**
	 * <b>Accessor:</b> Returns the current characters in his ArrayList of characters. <br>
	 * <b>Postcondition:</b> The current characters in his ArrayList of characters have been returned.
	 * 
	 * @return the current characters in his ArrayList of characters
	 */
	public ArrayList<Character> getCharacters(){
		return this.hisCharacters;
	}
	
	/**
	 * <b>Accessor:</b> Returns the tiles in his ArrayList of tiles. <br>
	 * <b>Postcondition:</b> The tiles in his ArrayList of tiles have been returned.
	 * 
	 * @return the tiles from his ArrayList of tiles
	 */
	public ArrayList<Tile> getTiles(){
		return this.hisTiles;
	}
	
	/**
	 * <b>Transformer:</b> Sets the hasDrewTiles variable of the player. <br>
	 * <b>Postcondition:</b> The hasDrewTiles variable of the player has been set. 
	 */
	public void setHasDrewTilesFromBag(boolean b) {
		this.hasDrewTilesFromBag = b;
	}
	
	/**
	 * <b>Accessor:</b> Returns true if the player has drawn tiles from the bag, false instead. <br>
	 * <b>Postcondition:</b> True is returned if the player has drawn tiles from the bag, false instead.
	 * 
	 * @return true if the player has drawn tiles from the bag, false instead.
	 */
	public boolean getHasDrewTiles() {
		return this.hasDrewTilesFromBag;
	}
	
	/**
	 * <b>Transformer:</b> Sets the given number to the numOfTilesHeDrew variable.
	 * <b>Postcondition:</b> The given number has been set to the numOfTilesHeDrew variable.
	 * @param num
	 */
	public void setNumOfTilesHeDrew(int num) {
		this.numOfTilesHeDrew = num;
	}
	
	/**
	 * <b>Transformer:</b> Removes 4 tiles from the bag. <br>
	 * <b>Precondition:</b> The bag should not be empty. <br>
	 * <b>Postcondition:</b> Four tiles were removed from the bag.
	 *
	 * @param bag
	 */
	public ArrayList<Tile> drawTilesFromBag(Bag bag) {
		Random randomTile = new Random();
		ArrayList<Tile> bagTiles = new ArrayList<>();
		ArrayList<Tile> tilesToBePut = new ArrayList<>();
		bagTiles = bag.getTiles();
		for(int j=0; j<4; j++) {
			if(bag.getActiveNumberOfTiles() == 0)
				break;
			int i = randomTile.nextInt(bag.getActiveNumberOfTiles());
			tilesToBePut.add(bagTiles.get(i));
			System.out.println(bagTiles.get(i));
			bag.removeTile(bagTiles.get(i));
		}
			this.hasDrewTilesFromBag = true;		
			return tilesToBePut;
	}
	
	/**
	 * <b>Transformer:</b> Draws up to two tiles from an area and put them into his ArrayList of tiles and sets the choice of the player. <br>
	 * <b>Postcondition:</b> Two tiles were removed from the given area and placed in his ArrayList of tiles.
	 * 
	 * @param area
	 * @return 1 in success, 0 otherwise.
	 */
	public int drawTilesFromBoard(Tile tile) {
		numOfAmphora = 0;
		numOfMosaic = 0;
		numOfSkeleton = 0;
		numOfStatue = 0;
		if(numOfTilesHeDrew == -1) {
			hisTiles.add(tile);
			secondChoice = tile.getArea()+ 5;
			numOfTilesHeDrew++;
		}
		else if(numOfTilesHeDrew == 0) {
			if(!(currentCharacterUsed instanceof Professor)) {
				if(tile instanceof Mosaic)
					this.choice = 1;
				else if(tile instanceof Statue)
					this.choice = 2;
				else if(tile instanceof Amphora)
					this.choice = 3;
				else
					this.choice = 4;
				firstChoice = this.choice;
			}
			else
				thirdChoice = tile.getArea() + 5;
			if((thirdChoice == secondChoice || thirdChoice == firstChoice) && secondChoice != 0) {
				return 0;
			}
			else {
				hisTiles.add(tile);
				numOfTilesHeDrew++;
				this.hasDrewTilesFromBoard = true;
			}
		}
		else if(numOfTilesHeDrew == 1){
			if(tile.getArea() == this.choice || this.choice == 0 || currentCharacterUsed instanceof Professor) {
				if(currentCharacterUsed instanceof Professor)
					fourthChoice = tile.getArea() + 5;
				if((fourthChoice == thirdChoice || fourthChoice == secondChoice || fourthChoice == firstChoice) && thirdChoice != 0 && secondChoice != 0) {
					return 0;
				}
				else {
					hisTiles.add(tile);
					numOfTilesHeDrew++;
					this.hasDrewTilesFromBoard = true;
				}
			}
			else {
				System.out.println("you can't chooce tile from this area of the board.");
			}
		}
		System.out.println("First Choice: " +firstChoice);
		System.out.println("Second Choice: " +secondChoice);
		System.out.println("Third Choice: " +thirdChoice);
		System.out.println("Fourth Choice: " +fourthChoice);
		for(int i=0; i< hisTiles.size(); i++) {
			if(hisTiles.get(i) instanceof Mosaic)
				numOfMosaic++;
			else if(hisTiles.get(i) instanceof Statue)
				numOfStatue++;
			else if(hisTiles.get(i) instanceof Amphora)
				numOfAmphora++;
			else
				numOfSkeleton++;
		}
		System.out.println("numoftileshedrew = " + numOfTilesHeDrew);
		System.out.println("Player " + this.ID + " has " + numOfAmphora + " amphora tiles");
		System.out.println("Player " + this.ID + " has " + numOfMosaic + " mosaic tiles");
		System.out.println("Player " + this.ID + " has " + numOfSkeleton + " skeleton tiles");
		System.out.println("Player " + this.ID + " has " + numOfStatue + " statue tiles");
		return 1;
	}
	
	/**
	 * <b>Transformer:</b> Removes the given character from his ArrayList of characters. <br>
	 * <b>Precondition:</b> The character should not be used and the given character should be equal to our 4 characters. <br>
	 * <b>Postcondition:</b> The given character has been used from his ArrayList of characters.
	 * 
	 * @param character
	 */
	public void useCharacter(Character character) {
		for(int i=0; i<hisCharacters.size(); i++) {
			if(hisCharacters.get(i).getCharacter().equals(character.getCharacter()) && hisCharacters.get(i).getIsUsed() == false) {
				hisCharacters.get(i).useCharacter();
				this.hasChoseCharacter = true;
			}
		}
	}
	
	/**
	 * <b>Transformer:</b> It calculates the mosaic score. <br>
	 * <b>Postcondition:</b> The mosaic score is calculated and added to the overall score.
	 */
	public void calculateMosaicScore() {
		int mosaics;
		int red = 0, green = 0, yellow = 0;
		int redMosaic = 0, greenMosaic = 0, yellowMosaic = 0;
		int remainingRed = 0, remainingGreen = 0, remainingYellow = 0;
		int mosaicScore = 0;
		ArrayList<Mosaic> calculateMosaic = new ArrayList<>();
		for(int i=0; i < hisTiles.size(); i++) {
			if(hisTiles.get(i) instanceof Mosaic)
				calculateMosaic.add((Mosaic)hisTiles.get(i));
		}
		mosaics  = calculateMosaic.size() / 4;
		if(mosaics > 0) {
			for(int i=0; i < calculateMosaic.size(); i++) {
				if(calculateMosaic.get(i).getCol() == MosaicColors.GREEN)
					green++;
				else if(calculateMosaic.get(i).getCol() == MosaicColors.RED)
					red++;
				else
					yellow++;
			}
			redMosaic = red / 4;
			greenMosaic = green / 4;
			yellowMosaic = yellow / 4;
			remainingRed = red;
			remainingGreen = green;
			remainingYellow = yellow;
			
			if(redMosaic > 0) {
				mosaicScore += 4 * redMosaic;
				remainingRed = red % 4;
			}
			
			if(greenMosaic > 0) {
				mosaicScore += 4 * greenMosaic;
				
				remainingGreen = green % 4;
			}
			if(yellowMosaic > 0) {
				mosaicScore += 4 * yellowMosaic;
				remainingYellow = yellow % 4;
			}
			
			int remainingMosaics = (remainingGreen + remainingRed + remainingYellow)/4;
			if(remainingMosaics > 0)
				mosaicScore += 2 * remainingMosaics;
		}
		else {
			System.out.println("Not enough mosaic tiles to create a mosaic for Player: " + this.ID);
		}
		System.out.println("The score of Player: " + this.ID + " has mosaic score of: " + mosaicScore);
		this.score += mosaicScore;
		this.mosaicScore = mosaicScore;
	}
	
	/**
	 * <b>Transformer:</b> Calculates the skeleton score. <br>
	 * <b>Postcondition:</b> The skeleton score has been calculated and added to the overall score.
	 */
	public void calculateSkeletonScore() {
		int oldSkeletons = 0, youngSkeletons = 0;
		int family = 0;
		int lowerOldSkeleton = 0, lowerYoungSkeleton = 0, upperOldSkeleton = 0, upperYoungSkeleton = 0;
		int skeletonScore = 0;
		ArrayList<Skeleton> calculateSkeleton = new ArrayList<>();
		for(int i=0; i < hisTiles.size(); i++) {
			if(hisTiles.get(i) instanceof Skeleton)
				calculateSkeleton.add((Skeleton)hisTiles.get(i));
		}
		for(int i=0; i < calculateSkeleton.size(); i++) {
			if(calculateSkeleton.get(i).getIsUp() && calculateSkeleton.get(i).getIsOld())
				upperOldSkeleton++;
			else if(calculateSkeleton.get(i).getIsUp() && !calculateSkeleton.get(i).getIsOld())
				upperYoungSkeleton++;
			else if(!calculateSkeleton.get(i).getIsUp() && calculateSkeleton.get(i).getIsOld())
				lowerOldSkeleton++;
			else 
				lowerYoungSkeleton++;
		}
		
		int min = 0;
		if(upperOldSkeleton > 0 && lowerOldSkeleton > 0) { // Finding how many old skeletons we have.
			if(upperOldSkeleton < lowerOldSkeleton)
				min = upperOldSkeleton;
			else
				min = lowerOldSkeleton;
			
			for(int i=0; i < min; i++) {
				oldSkeletons++;
			}
		}
		else {
			System.out.println("Not enough old parts of skeletons to create an old skeleton for Player: " + this.ID);	
		}
		
		if(upperYoungSkeleton > 0 && lowerYoungSkeleton > 0) {
			if(upperYoungSkeleton < lowerYoungSkeleton)
				min = upperYoungSkeleton;
			else
				min = lowerYoungSkeleton;
			
			for(int i=0; i < min; i++) 
				youngSkeletons++;
		}
		else {
			System.out.println("Not enough young parts of skeletons to create a young skeleton for Player: " + this.ID);
		}
		
		int oldSkeletonsFamily = oldSkeletons / 2; 
		if(oldSkeletonsFamily > 0 && youngSkeletons > 0) {
			if(youngSkeletons >= oldSkeletonsFamily) {
				family += oldSkeletonsFamily;
				oldSkeletons -= 2 * oldSkeletonsFamily;
				youngSkeletons -= oldSkeletonsFamily;
			}
			else {
				family += youngSkeletons;
				oldSkeletons -= 2 * youngSkeletons;
				youngSkeletons = 0;
			}
		}
		
		skeletonScore += 6 * family;
		skeletonScore += oldSkeletons;
		skeletonScore += youngSkeletons;
		System.out.println("The score of Player: " + this.ID + " has skeleton score of: " + skeletonScore);
		this.score += skeletonScore;
		this.skeletonScore = skeletonScore;
	}
	
	/**
	 * <b>Transformer:</b> Calculates the amphora score. <br>
	 * <b>Postcondition:</b> The amphora score has been calculated and added to the overall score.
	 */
	public void calculateAmphoraScore() {
		int amphoras, remainingAmphoras, amphoraScore = 0;
		int blue = 0, brown = 0,  red = 0, green = 0, yellow = 0, purple = 0;
		int remainingBlue = 0, remainingBrown = 0, remainingRed = 0, remainingGreen = 0, remainingYellow = 0, remainingPurple = 0;
		ArrayList<Amphora> calculateAmphora = new ArrayList<>();
		for(int i=0; i < hisTiles.size(); i++) {
			if(hisTiles.get(i) instanceof Amphora)
				calculateAmphora.add((Amphora) hisTiles.get(i)); 
		}
		amphoras = calculateAmphora.size();
		for(int i=0; i < calculateAmphora.size(); i++) {
			if(calculateAmphora.get(i).getCol() == AmphoraColors.BLUE)
				blue++;
			else if(calculateAmphora.get(i).getCol() == AmphoraColors.BROWN)
				brown++;
			else if(calculateAmphora.get(i).getCol() == AmphoraColors.RED)
				red++;
			else if(calculateAmphora.get(i).getCol() == AmphoraColors.GREEN)
				green++;
			else if(calculateAmphora.get(i).getCol() == AmphoraColors.YELLOW)
				yellow++;
			else
				purple++;
		}
		System.out.println("Player's: " + this.ID + " score without the amphoras = " + this.score);
		while(amphoras != 0) {
			if(amphoras < 3) {
				System.out.println("Not enough amphoras to calculate for player: " + this.ID + " !");
				amphoras = 0;
			}
			else{
				if(amphoras == 3) {
					if(blue > 1 || brown > 1 || red > 1 || green > 1 || yellow > 1 || purple > 1) {
						System.out.println("I have 3 amphoras but 2 or 3 with the same colours");
						amphoras = 0;
					}
					else {
						this.score += 1;
						amphoraScore += 1;
						amphoras = 0;
					}
				}else {
					if(blue > 1 || brown > 1 || red > 1 || green > 1 || yellow > 1 || purple > 1) {
						if(blue > 1) {
							amphoras -= ((blue + 1) / 2);
							remainingBlue = ((blue + 1) / 2);
							blue -= ((blue + 1) / 2);
						}
						if(brown > 1) {
							amphoras -= ((brown + 1) / 2);
							remainingBrown = ((brown + 1) / 2);
							brown -= ((brown + 1) / 2);
						}
						if(red > 1) {
							amphoras -= ((red + 1) / 2);
							remainingRed = ((red + 1) / 2);
							red -= ((red + 1) / 2);
						}
						if(green > 1) {
							amphoras -= ((green + 1) / 2);
							remainingGreen = ((green + 1) / 2);
							green -= ((green + 1) / 2);
						}
						if(yellow > 1) {
							amphoras -= ((yellow + 1) / 2);
							remainingYellow = ((yellow + 1) / 2);
							yellow -= ((yellow + 1) / 2);
						}
						if(purple > 1) {
							amphoras -= ((purple + 1) / 2);
							remainingPurple = ((purple + 1) / 2);
							purple -= ((purple + 1) / 2);
						}
					}
					else {
						if(amphoras == 4) {
							this.score += 2;
							amphoraScore += 2;
						}
						else if(amphoras == 5) {
							this.score += 4;
							amphoraScore += 4;
						}
						else {
							this.score += 6;
							amphoraScore += 6;
						}
						amphoras = 0;
					}	
				}
			}
		}
		
		remainingAmphoras = remainingBlue + remainingBrown + remainingGreen + remainingPurple + remainingRed + remainingYellow;
		//calculating the remaining amphoras score!
		while(remainingAmphoras != 0) {
			if(remainingAmphoras < 3) {
				System.out.println("Not enough amphoras to calculate for player: " + this.ID + " !");
				remainingAmphoras = 0;
			}
			else{
				if(remainingAmphoras == 3) {
					if(remainingBlue > 1 || remainingBrown > 1 || remainingRed > 1 || remainingGreen > 1 || remainingYellow > 1 || remainingPurple > 1) {
						System.out.println("I have 3 amphoras but 2 or 3 with the same colours");
						remainingAmphoras = 0;
					}
					else {
						this.score += 1;
						amphoraScore += 1;
						remainingAmphoras = 0;
					}
				}else {
					if(remainingBlue > 1 || remainingBrown > 1 || remainingRed > 1 || remainingGreen > 1 || remainingYellow > 1 || remainingPurple > 1) {
						if(remainingBlue > 1) {
							remainingAmphoras -= ((remainingBlue + 1) / 2);
							remainingBlue = ((remainingBlue + 1) / 2);
							remainingBlue -= ((remainingBlue + 1) / 2);
						}
						if(remainingBrown > 1) {
							remainingAmphoras -= ((remainingBrown + 1) / 2);
							remainingBrown = ((remainingBrown + 1) / 2);
							remainingBrown -= ((remainingBrown + 1) / 2);
						}
						if(remainingRed > 1) {
							remainingAmphoras -= ((remainingRed + 1) / 2);
							remainingRed = ((remainingRed + 1) / 2);
							remainingRed -= ((remainingRed + 1) / 2);
						}
						if(remainingGreen > 1) {
							remainingAmphoras -= ((remainingGreen + 1) / 2);
							remainingGreen = ((remainingGreen + 1) / 2);
							remainingGreen -= ((remainingGreen + 1) / 2);
						}
						if(remainingYellow > 1) {
							remainingAmphoras -= ((remainingYellow + 1) / 2);
							remainingYellow = ((remainingYellow + 1) / 2);
							remainingYellow -= ((remainingYellow + 1) / 2);
						}
						if(remainingPurple > 1) {
							remainingAmphoras -= ((remainingPurple + 1) / 2);
							remainingPurple = ((remainingPurple + 1) / 2);
							remainingPurple -= ((remainingPurple + 1) / 2);
						}
					}
					else {
						if(remainingAmphoras == 4) {
							this.score += 2;
							amphoraScore += 2;
						}
						else if(remainingAmphoras == 5) {
							this.score += 4;
							amphoraScore += 4;
						}
						else {
							this.score += 6;
							amphoraScore += 6;
						}
						remainingAmphoras = 0;
					}	
				}
			}
		}
		this.amphoraScore = amphoraScore;
		System.out.println("Player's: " + this.ID + " score with the amphoras = " + this.score);
	}
	
	/**
	 * <b>Accessor:</b> Returns the number of statue tiles in his inventory to see who has the most statue tiles.
	 * <b>Postcondition:</b> The number of statue tiles has been returned.
	 * 
	 * @return the number of statue tiles.
	 */
	public void numberOfStatues() {
		ArrayList<Statue> statues = new ArrayList<>();
		for(int i=0; i < hisTiles.size(); i++){
			if(hisTiles.get(i) instanceof Statue)
				statues.add((Statue)hisTiles.get(i));
		}
		for(int i=0; i < statues.size(); i++) {
			if(statues.get(i) instanceof Sphinx)
				numOfSphinx++;
			else
				numOfCaryatid++;
		}
	}
	
	/**
	 * <b>Accessor:</b> Returns the number of Caryatid tiles in his inventory. <br>
	 * <b>Postcondition:</b> The number of Caryatid tiles in his inventory has been returned.
	 * @return the number of Caryatid tiles in his inventory.
	 */
	public int getNumOfCaryatid() {
		return this.numOfCaryatid;
	}
	
	/**
	 * <b>Accessor:</b> Returns the number of sphinx tiles in his inventory. <br>
	 * <b>Postcondition:</b> The number of sphinx tiles in his inventory has been returned. <br>
	 * @return the number of sphinx tiles in his inventory.
	 */
	public int getNumOfSphinx() {
		return this.numOfSphinx;
	}
	
	/**
	 * <b>Transformer:</b> It calculates and sets the score of the player when the game is finished. <br>
	 * <b>Postcondition:</b> The score has been calculated and set.
	 */
	public void calculateScore() {
		calculateMosaicScore();
		calculateSkeletonScore();
		calculateAmphoraScore();
		numberOfStatues();
	}
	
	/**
	 * <b>Transformer:</b> It sets the caryatid score of the player. <br>
	 * <b>Postcondition:</b> The caryatid score of the player has been set.
	 * @param score
	 */
	public void setCaryatidScore(int score) {
		this.caryatidScore = score;
	}
	
	/**
	 * <b>Transformer:</b> It sets the sphinx score of the player. <br>
	 * <b>Postcondition:</b> The sphinx score of the player has been set.
	 * @param score
	 */
	public void setSphinxScore(int score) {
		this.sphinxScore = score;
	}
	
	/**
	 * <b>Accessor:</b> Returns the sphinx score of the player. <br>
	 * <b>Postcondition:</b> The sphinx score of the player has been returned.
	 * @return the sphinx score of the player.
	 */
	public int getSphinxScore() {
		return this.sphinxScore;
	}
	
	/**
	 * <b>Accessor:</b> Returns the caryatid score of the player. <br>
	 * <b>Postcondition:</b> The caryatid score of the player has been returned.
	 * @return the caryatid score of the player.
	 */
	public int getCaryatidScore() {
		return this.caryatidScore;
	}
	
	/**
	 * <b>Accessor:</b> Returns the amphora score of the player. <br>
	 * <b>Postcondition:</b> The amphora score of the player has been returned.
	 * @return the amphora score of the player.
	 */
	public int getAmphoraScore() {
		return this.amphoraScore;
	}
	
	/**
	 * <b>Accessor:</b> Returns the mosaic score of the player. <br>
	 * <b>Postcondition:</b> The mosaic score of the player has been returned.
	 * @return the mosaic score of the player.
	 */
	public int getMosaicScore() {
		return this.mosaicScore;
	}
	
	/**
	 * <b>Accessor:</b> Returns the skeleton score of the player. <br>
	 * <b>Postcondition:</b> The skeleton score of the player has been returned.
	 * @return the skeleton score of the player.
	 */
	public int getSkeletonScore() {
		return this.skeletonScore;
	}
	
	/**
	 * <b>Transformer:</b> It sets the first, second, third and fourth choices of the player
	 *					   when the professor card is used. <br>
	 * <b>Postcondition:</b> The first, second, third and fourth choices of the player has been set.
	 * @param first
	 * @param second
	 * @param third
	 * @param fourth
	 */
	public void setChoices(int first, int second, int third, int fourth) {
		firstChoice = first;
		secondChoice = second;
		thirdChoice = third;
		fourthChoice = fourth;
	}
	
	/**
	 * <b>Accessor:</b> Returns the first choice of the player. <br>
	 * <b>Postcondition:</b> The first choice of the player has been returned.
	 * @return the first choice of the player.
	 */
	public int getFirstChoice() {
		return this.firstChoice;
	}
	
	/**
	 * <b>Accessor:</b> Returns the second choice of the player. <br>
	 * <b>Postcondition:</b> The second choice of the player has been returned.
	 * @return the second choice of the player.
	 */
	public int getSecondChoice() {
		return this.secondChoice;
	}
	
	/**
	 * <b>Accessor:</b> Returns the third choice of the player. <br>
	 * <b>Postcondition:</b> The third choice of the player has been returned.
	 * @return the third choice of the player.
	 */
	public int getThirdChoice() {
		return this.thirdChoice;
	}
	
	/**
	 * <b>Accessor:</b> Returns the fourth choice of the player. <br>
	 * <b>Postcondition:</b> The fourth choice of the player has been returned.
	 * @return the fourth choice of the player.
	 */
	public int getFourthChoice() {
		return this.fourthChoice;
	}
	
	/**
	 * <b>Accessor:</b> Returns the score of the player. <br>
	 * <b>Postcondition:</b> The score has been returned.
	 * 
	 * @return the score of the player
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * <b>Transformer:</b> Sets the score. <br>
	 * <b>Postcondition:</b> The score has been set.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * <b>Transformer:</b> Adds the given number to the overall score of the player. <br>
	 * <b>Postcondition:</b> The given number has been added to the overall score of the player.
	 * @param add
	 */
	public void addScore(int add) {
		this.score += add;
	}
	
	/**
	 * <b>Accessor:</b> Returns the choice of the player. <br>
	 * <b>Postcondition:</b> The choice of the player has been returned.
	 * 
	 * @return the choice of the player
	 */
	public int getChoice() {
		return this.choice;
	}
	
	/**
	 * <b>Transformer:</b> It sets the given choice to the player. <br>
	 * <b>Postcondition:</b> The given choice has been set to the player.
	 * @param choice
	 */
	public void  setChoice(int choice) {
		this.choice = choice;
	}
	
	/**
	 * <b>Transformer:</b> It sets the character the player used this round. <br>
	 * <b>Postcondition:</b> The current character the player used this round has been set.
	 * @param ch
	 */
	public void setCurrentCharacterUsed(Character ch) {
		this.currentCharacterUsed = ch;
	}
	
	/**
	 * <b>Transformer:</b> It sets true if the player has chose a character this round, false otherwise. <br>
	 * <b>Postcondition:</b> True is set if the player has chose a character this round, false otherwise.
	 * 
	 * @param b
	 */
	public void setHasChoseCharacter(boolean b) {
		this.hasChoseCharacter = b;
	}
	
	/**
	 * <b>Accessor:</b> Returns true if the player has chose a character this round, false otherwise. <br>
	 * <b>Postcondition:</b> True is returned if the player has chose a character this round, false otherwise.
	 * 
	 * @return true if the player has chose a character this round, false otherwise
	 */
	public boolean getHasChoseCharacter() {
		return this.hasChoseCharacter;
	}
	
	/**
	 * <b>Transformer:</b> It sets true if the player has played this round, false otherwise. <br>
	 * <b>Postcondition:</b> True is set if the player has played this round, false otherwise.
	 * 
	 * @param b
	 */
	public void setHasPlayed(boolean b) {
		this.hasPlayed = b;
	}
	
	/**
	 * <b>Accessor:</b> Returns true if the player has played this round, false otherwise. <br>
	 * <b>Postcondition:</b> True is returned if the player has played this round, false otherwise.
	 * 
	 * @return true if the player has played this round, false otherwise.
	 */
	public boolean getHasPlayed() {
		return this.hasPlayed;
	}
	
	/**
	 * <b>Accessor:</b> Returns true if the player has drawn tiles from the board, false otherwise. <br>
	 * <b>Postcondition:</b> True is returned if the player has drawn tiles from board, false otherwise.
	 * @return true if the player has drawn tiles from the board, false otherwise.
	 */
	public boolean getHasDrewTilesFromBoard() {
		return this.hasDrewTilesFromBoard;
	}
	
	/**
	 * <b>Accessor:</b> Returns the number of tiles the player has drawn from the board. <br>
	 * <b>Postcondition:</b> The number of tiles the player has drawn from the board has been returned.
	 * @return the number of tiles the player has drawn from the board.
	 */
	public int getNumOfTilesHeDrew() {
		return this.numOfTilesHeDrew;
	}
	
	/**
	 * <b>Transformer:</b> It sets true to the hasDrewTilesFromBoard variable if the player has drawn tiles from the board, false otherwise.
	 * <b>Postcondition:</b> True has been set to the hasDrewTilesFromBoard variable if the player has drawn tiles from the board, false otherwise.
	 * @param b
	 */
	public void setHasDrewTilesFromBoard(boolean b) {
		this.hasDrewTilesFromBoard = b;
		if(!hasChoseCharacter)
			this.numOfTilesHeDrew = 0;
	}
}