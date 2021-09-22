package Controller;

import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.TreeSet;
import java.util.Random;

//import javax.swing.JOptionPane;
//import javax.swing.text.View;

import Model.*;
import Model.Character;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
public class Controller {

	private Board board = new Board();
	private Player P1, P2, P3, P4;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Bag bag = new Bag();

	/**
	 * <b>Constructor:</b> Constructs a new Controller and sets the game as eligible
	 * to start. <br>
	 * <b>Postcondition:</b> Constructs a new Controller, with new 4 players and the
	 * bag.
	 */
	public Controller() {

		P1 = new Player("Player1", 1);
		P2 = new Player("Player2", 2);
		P3 = new Player("Player3", 3);
		P4 = new Player("Player4", 4);
		P1.initCharacters();
		P2.initCharacters();
		P3.initCharacters();
		P4.initCharacters();
		players.add(P1);
		players.add(P2);
		players.add(P3);
		players.add(P4);
		bag.initBag();
		initializeBoard();
	}

	/**
	 * <b>Transformer:</b> Initializes the board with the 4 tiles when the game starts, 1 in each area.
	 * <b>Postcondition:</b> The board has been initialized with the 4 tiles when the game started.
	 */
	public void initializeBoard() {
		ArrayList<Tile> tiles = bag.getTiles();
		Random randomTile = new Random();
		while(true) {
			int i = randomTile.nextInt(bag.getActiveNumberOfTiles());
			if(tiles.get(i) instanceof Mosaic) {
				if(board.getMosaicArea().size() == 0) {
					board.addTileToArea(tiles.get(i));
					bag.removeTile(tiles.get(i));
					break;
				}
			}
		}
		while(true) {
			int i = randomTile.nextInt(bag.getActiveNumberOfTiles());
			if(tiles.get(i) instanceof Statue) {
				if(board.getStatueArea().size() == 0) {
					board.addTileToArea(tiles.get(i));
					bag.removeTile(tiles.get(i));
					break;
				}
			}
		}
		while(true) {
			int i = randomTile.nextInt(bag.getActiveNumberOfTiles());
			if(tiles.get(i) instanceof Skeleton) {
				if(board.getSkeletonArea().size() == 0) {
					board.addTileToArea(tiles.get(i));
					bag.removeTile(tiles.get(i));
					break;
				}
			}
		}
		while(true) {
			int i = randomTile.nextInt(bag.getActiveNumberOfTiles());
			if(tiles.get(i) instanceof Amphora) {
				if(board.getAmphoraArea().size() == 0) {
					board.addTileToArea(tiles.get(i));
					bag.removeTile(tiles.get(i));
					break;
				}
			}
		}
	}
	
	/**
	 * <b>Observer</b>:Return true if the game (the bag is empty or the landslide
	 * area is full) has finished, false otherwise. <br>
	 * <p>
	 * <b>Postcondition</b> return true if the game (the bag is empty or the
	 * landslide area is full) has finished, false otherwise.
	 * </p>
	 * 
	 * @return true if the game (the bag is empty or the landslide area is full) has
	 *         finished, false otherwise.
	 */
	public boolean game_has_finished() {
		return board.hasFinished();
	}

	/**
	 * <b>Accessor:</b> Returns which player has the turn. <br>
	 * <b>Postcondition:</b> Returns which player has the turn.
	 *
	 * @return which player has the turn (for example 1 if P1 has the turn).
	 */
	public int seeTurn() {
		return board.getCurrentPlayer();
	}

	/**
	 * <b>Accessor:</b> Returns the player who has the turn. <br>
	 * <Postcondition:</b> The player who has the turn has been returned.
	 * 
	 * @return the player who has the turn.
	 */
	public Player getCurrentPlayer() {
		int id = seeTurn();
		return players.get(id - 1);
	}

	/**
	 * <b>Accessor:</b> Returns the players of the game. <br>
	 * <Postcondition:</b> The players of the game has been returned.
	 * @return the players of the game.
	 */
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	/**
	 * <b>Transformer:</b> Sets the turn to the next player. <br>
	 * <b>Postcondition:</b> The turn has been set to the next player.
	 */
	public void setTurn() {
		System.out.println("Player with id " + (seeTurn()) + " had the previous turn");
		if (board.getCurrentPlayer() == 4) {
			board.setCurrentPlayer(1);
			players.get(0).setHasDrewTilesFromBag(false);
			players.get(0).setHasChoseCharacter(false);
			players.get(0).setHasDrewTilesFromBoard(false);
			players.get(0).setChoice(0);
			players.get(0).setCurrentCharacterUsed(null);
			players.get(0).setChoices(0, 0, 0, 0);
		} else {
			board.setCurrentPlayer(board.getCurrentPlayer() + 1);
			players.get(seeTurn() - 1).setHasDrewTilesFromBag(false);
			players.get(seeTurn() - 1).setHasChoseCharacter(false);
			players.get(seeTurn() - 1).setHasDrewTilesFromBoard(false);
			players.get(seeTurn() - 1).setChoice(0);
			players.get(seeTurn() - 1).setCurrentCharacterUsed(null);
			players.get(seeTurn() -1).setChoices(0,0,0,0);
		}
	}

	/**
	 * <b>Transformer:</b> It calculates the score of each player after the game is
	 * finished. <br>
	 * <b>Postcondition:</b> The score of each player has been calculated.
	 */
	public void calculateScore() {
		players.get(0).calculateScore(); 	//Calculate score for player 1 except statue score
		players.get(1).calculateScore();	//Calculate score for player 2 except statue score
		players.get(2).calculateScore();	//Calculate score for player 3 except statue score
		players.get(3).calculateScore();	//Calculate score for player 4 except statue score
		Player temp;
		Player[] playersCaryatidScore = new Player[] {players.get(0), players.get(1), players.get(2), players.get(3)};
		Player[] playersSphinxScore = new Player[] {players.get(0), players.get(1), players.get(2), players.get(3)};
		
		// Calculating the Statue scores
		for(int i=0; i < playersCaryatidScore.length; i++) {
			for(int j = 1; j < playersCaryatidScore.length; j++) {
				if(playersCaryatidScore[j-1].getNumOfCaryatid() < playersCaryatidScore[j].getNumOfCaryatid()) {
					temp = playersCaryatidScore[j-1];
					playersCaryatidScore[j-1] = playersCaryatidScore[j];
					playersCaryatidScore[j] = temp;
				}
			}
		}
		
		for(int i=0; i < playersSphinxScore.length; i++) {
			for(int j = 1; j < playersSphinxScore.length; j++) {
				if(playersSphinxScore[j-1].getNumOfSphinx() < playersSphinxScore[j].getNumOfSphinx()) {
					temp = playersSphinxScore[j-1];
					playersSphinxScore[j-1] = playersSphinxScore[j];
					playersSphinxScore[j] = temp;
				}
			}
		}
		
		if(playersCaryatidScore[0].getNumOfCaryatid() != playersCaryatidScore[1].getNumOfCaryatid() && playersCaryatidScore[3].getNumOfCaryatid() != playersCaryatidScore[2].getNumOfCaryatid()) {
			playersCaryatidScore[0].addScore(6);
			playersCaryatidScore[1].addScore(3);
			playersCaryatidScore[2].addScore(3);
			playersCaryatidScore[0].setCaryatidScore(6);
			playersCaryatidScore[1].setCaryatidScore(3);
			playersCaryatidScore[2].setCaryatidScore(3);
		}
		else {
			if(playersCaryatidScore[0].getNumOfCaryatid() == playersCaryatidScore[1].getNumOfCaryatid() && playersCaryatidScore[1].getNumOfCaryatid() == playersCaryatidScore[2].getNumOfCaryatid() && playersCaryatidScore[2].getNumOfCaryatid() == playersCaryatidScore[3].getNumOfCaryatid()) {
				playersCaryatidScore[0].addScore(6);
				playersCaryatidScore[1].addScore(6);
				playersCaryatidScore[2].addScore(6);
				playersCaryatidScore[3].addScore(6);
				playersCaryatidScore[0].setCaryatidScore(6);
				playersCaryatidScore[1].setCaryatidScore(6);
				playersCaryatidScore[2].setCaryatidScore(6);
				playersCaryatidScore[3].setCaryatidScore(6);
			}
			else if(playersCaryatidScore[0].getNumOfCaryatid() == playersCaryatidScore[1].getNumOfCaryatid() && playersCaryatidScore[1].getNumOfCaryatid() != playersCaryatidScore[2].getNumOfCaryatid() && playersCaryatidScore[2].getNumOfCaryatid() != playersCaryatidScore[3].getNumOfCaryatid()) {
				playersCaryatidScore[0].addScore(6);
				playersCaryatidScore[1].addScore(6);
				playersCaryatidScore[2].addScore(3);
				playersCaryatidScore[0].setCaryatidScore(6);
				playersCaryatidScore[1].setCaryatidScore(6);
				playersCaryatidScore[2].setCaryatidScore(3);
			}
			else if(playersCaryatidScore[0].getNumOfCaryatid() == playersCaryatidScore[1].getNumOfCaryatid() && playersCaryatidScore[1].getNumOfCaryatid() == playersCaryatidScore[2].getNumOfCaryatid() && playersCaryatidScore[2].getNumOfCaryatid() != playersCaryatidScore[3].getNumOfCaryatid()) {
				playersCaryatidScore[0].addScore(6);
				playersCaryatidScore[1].addScore(6);
				playersCaryatidScore[2].addScore(6);
				playersCaryatidScore[0].setCaryatidScore(6);
				playersCaryatidScore[1].setCaryatidScore(6);
				playersCaryatidScore[2].setCaryatidScore(6);
			}
			else if(playersCaryatidScore[0].getNumOfCaryatid() != playersCaryatidScore[1].getNumOfCaryatid() && playersCaryatidScore[1].getNumOfCaryatid() != playersCaryatidScore[2].getNumOfCaryatid() && playersCaryatidScore[2].getNumOfCaryatid() == playersCaryatidScore[3].getNumOfCaryatid()) {
				playersCaryatidScore[0].addScore(6);
				playersCaryatidScore[1].addScore(3);
				playersCaryatidScore[0].setCaryatidScore(6);
				playersCaryatidScore[1].setCaryatidScore(3);
			}
			else if(playersCaryatidScore[0].getNumOfCaryatid() != playersCaryatidScore[1].getNumOfCaryatid() && playersCaryatidScore[1].getNumOfCaryatid() == playersCaryatidScore[2].getNumOfCaryatid() && playersCaryatidScore[2].getNumOfCaryatid() == playersCaryatidScore[3].getNumOfCaryatid()) {
				playersCaryatidScore[0].addScore(6);
				playersCaryatidScore[0].setCaryatidScore(6);
			}
			else if(playersCaryatidScore[0].getNumOfCaryatid() == playersCaryatidScore[1].getNumOfCaryatid() && playersCaryatidScore[1].getNumOfCaryatid() != playersCaryatidScore[2].getNumOfCaryatid() && playersCaryatidScore[2].getNumOfCaryatid() == playersCaryatidScore[3].getNumOfCaryatid()) {
				playersCaryatidScore[0].addScore(6);
				playersCaryatidScore[1].addScore(6);
				playersCaryatidScore[0].setCaryatidScore(6);
				playersCaryatidScore[1].setCaryatidScore(6);
			}
		}
		
		if(playersSphinxScore[0].getNumOfSphinx() != playersSphinxScore[1].getNumOfSphinx() && playersSphinxScore[3].getNumOfSphinx() != playersSphinxScore[2].getNumOfSphinx()) {
			playersSphinxScore[0].addScore(6);
			playersSphinxScore[1].addScore(3);
			playersSphinxScore[2].addScore(3);
			playersSphinxScore[0].setSphinxScore(6);
			playersSphinxScore[1].setSphinxScore(3);
			playersSphinxScore[2].setSphinxScore(3);
		}
		else {
			if(playersSphinxScore[0].getNumOfSphinx() == playersSphinxScore[1].getNumOfSphinx() && playersSphinxScore[1].getNumOfSphinx() == playersSphinxScore[2].getNumOfSphinx() && playersSphinxScore[2].getNumOfSphinx() == playersSphinxScore[3].getNumOfSphinx()) {
				playersSphinxScore[0].addScore(6);
				playersSphinxScore[1].addScore(6);
				playersSphinxScore[2].addScore(6);
				playersSphinxScore[3].addScore(6);
				playersSphinxScore[0].setSphinxScore(6);
				playersSphinxScore[1].setSphinxScore(6);
				playersSphinxScore[2].setSphinxScore(6);
				playersSphinxScore[3].setSphinxScore(6);
			}
			else if(playersSphinxScore[0].getNumOfSphinx() == playersSphinxScore[1].getNumOfSphinx() && playersSphinxScore[1].getNumOfSphinx() != playersSphinxScore[2].getNumOfSphinx() && playersSphinxScore[2].getNumOfSphinx() != playersSphinxScore[3].getNumOfSphinx()) {
				playersSphinxScore[0].addScore(6);
				playersSphinxScore[1].addScore(6);
				playersSphinxScore[2].addScore(3);
				playersSphinxScore[0].setSphinxScore(6);
				playersSphinxScore[1].setSphinxScore(6);
				playersSphinxScore[2].setSphinxScore(3);
			}
			else if(playersSphinxScore[0].getNumOfSphinx() == playersSphinxScore[1].getNumOfSphinx() && playersSphinxScore[1].getNumOfSphinx() == playersSphinxScore[2].getNumOfSphinx() && playersSphinxScore[2].getNumOfSphinx() != playersSphinxScore[3].getNumOfSphinx()) {
				playersSphinxScore[0].addScore(6);
				playersSphinxScore[1].addScore(6);
				playersSphinxScore[2].addScore(6);
				playersSphinxScore[0].setSphinxScore(6);
				playersSphinxScore[1].setSphinxScore(6);
				playersSphinxScore[2].setSphinxScore(6);
			}
			else if(playersSphinxScore[0].getNumOfSphinx() != playersSphinxScore[1].getNumOfSphinx() && playersSphinxScore[1].getNumOfSphinx() != playersSphinxScore[2].getNumOfSphinx() && playersSphinxScore[2].getNumOfSphinx() == playersSphinxScore[3].getNumOfSphinx()) {
				playersSphinxScore[0].addScore(6);
				playersSphinxScore[1].addScore(3);
				playersSphinxScore[0].setSphinxScore(6);
				playersSphinxScore[1].setSphinxScore(3);
			}
			else if(playersSphinxScore[0].getNumOfSphinx() != playersSphinxScore[1].getNumOfSphinx() && playersSphinxScore[1].getNumOfSphinx() == playersSphinxScore[2].getNumOfSphinx() && playersSphinxScore[2].getNumOfSphinx() == playersSphinxScore[3].getNumOfSphinx()) {
				playersSphinxScore[0].addScore(6);
				playersSphinxScore[0].setSphinxScore(6);
			}
			else if(playersSphinxScore[0].getNumOfSphinx() == playersSphinxScore[1].getNumOfSphinx() && playersSphinxScore[1].getNumOfSphinx() != playersSphinxScore[2].getNumOfSphinx() && playersSphinxScore[2].getNumOfSphinx() == playersSphinxScore[3].getNumOfSphinx()) {
				playersSphinxScore[0].addScore(6);
				playersSphinxScore[1].addScore(6);
				playersSphinxScore[0].setSphinxScore(6);
				playersSphinxScore[1].setSphinxScore(6);
			}
		}
	}

	/**
	 * <b>Accessor:</b> It returns the winner of the game. <br>
	 * <b>Precondition:</b> The game has to be finished when we call this method.
	 * <br/>
	 * <b>Postcondition:</b> The winner of the game is returned. <br/>
	 * 
	 * @return the winner of the game (for example 1 if P1 is the winner of the
	 *         game)
	 */
	public int seeWinner() {
		Player[] winner = new Player[] {players.get(0), players.get(1), players.get(2), players.get(3)};
		Player temp;
		for(int i=0; i < winner.length; i++) {
			for(int j = 1; j < winner.length; j++) {
				if(winner[j-1].getScore() < winner[j].getScore()) {
					temp = winner[j-1];
					winner[j-1] = winner[j];
					winner[j] = temp;
				}
			}
		}
		return winner[0].getID();
	}

	/**
	 * <b>Accessor:</b> It returns the number of tiles in the bag. <br>
	 * <b>PostconditionL</b> The number of tiles in the bag has been returned.
	 * 
	 * @return the number of tiles in the bag.
	 */
	public int numberOfTilesInBag() {
		return bag.getActiveNumberOfTiles();
	}

	/**
	 * <b>Accessor:</b> Returns the bag of the game. <br>
	 * <b>Postcondition:</b> The bag of the game has been returned.
	 * 
	 * @return the bag of the game.
	 */
	public Bag getBag() {
		return this.bag;
	}

	/**
	 * <b>Accessor:</b> Returns the board of the game. <br>
	 * <b>Postcondition:</b> The board of the game has been returned.
	 * 
	 * @return the board of the game.
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * <b>Transformer:</b> Sets tiles to the board. <br>
	 * <b>Postcondition:</b>
	 * 
	 * @param tiles
	 */
	public void setTilesToBoard(ArrayList<Tile> tiles) {
		for (int i = 0; i < tiles.size(); i++) {
			board.addTileToArea(tiles.get(i));
		}
	}

	/**
	 * <b>Transformer:</b> Removes a tile from the board. <br>
	 * <b>Postcondition:</b> A tile has been removed from the board.
	 * 
	 * @param tile
	 */
	public void removeTileFromBoard(Tile tile) {
		this.board.removeTileFromArea(tile);
	}

	/**
	 * <b>Transformer:</b> It adds the given tile to the current player's inventory and it removes it from the board.
	 * <b>Postcondition:</b> The given tile has been given to the current Player and it is removed from the board.
	 * @param tile
	 * @return 1 in success, 0 otherwise.
	 */
	public int  pickTilesFromBoard(Tile tile) {
		int success;
		Player current = getCurrentPlayer();
		success = current.drawTilesFromBoard(tile);
		if(success == 1) {
			board.removeTileFromArea(tile);
			if ((tile instanceof Mosaic) && board.getMosaicArea().size() == 0) {
				current.setHasDrewTilesFromBoard(true);
			} else if (tile instanceof Statue && board.getStatueArea().size() == 0) {
				current.setHasDrewTilesFromBoard(true);
			} else if (tile instanceof Amphora && board.getAmphoraArea().size() == 0) {
				current.setHasDrewTilesFromBoard(true);
			} else if ((tile instanceof Skeleton && board.getSkeletonArea().size() == 0))
				current.setHasDrewTilesFromBoard(true);
		}
		return success;
	}
	
	/**
	 * <b>Transformer:</b> It uses the power of the given character for the current player.
	 * <b>Postcondition:</b> The power of the given character has been used for the current player.
	 * 
	 * @param character
	 * @return 1 if success, 0 if failure.
	 */
	public int usePowerOfCharacter(Character character) {
		if(character instanceof Digger) {
			if(players.get(seeTurn() - 1).getChoice() == 1) {
				if(board.getMosaicArea().size() < 1) 
					return 0;
				else {
					character.power(players.get(seeTurn() - 1), 1);
					players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
				}
			}
			else if(players.get(seeTurn() - 1).getChoice() == 2) {
				if(board.getStatueArea().size() < 1)
					return 0;
				else {
					character.power(players.get(seeTurn() - 1), 2);
					players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
				}
			}
			else if(players.get(seeTurn() - 1).getChoice() == 3) {
				if(board.getAmphoraArea().size() < 1)
					return 0;
				else {
					character.power(players.get(seeTurn() - 1), 3);
					players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
				}
			}
			else {
				if(board.getSkeletonArea().size() < 1)
					return 0;
				else {
					character.power(players.get(seeTurn() -1), 4);
					players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
				}
			}
		}
		else if(character instanceof Archaeologist) {
			if(players.get(seeTurn() - 1).getChoice() == 1) {
				if(board.getStatueArea().size() >= 1 || board.getAmphoraArea().size() >= 1 || board.getSkeletonArea().size() >= 1) {
					character.power(players.get(seeTurn() - 1), players.get(seeTurn() - 1).getChoice());
					players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
				}
				else
					return 0;
			}
			else if(players.get(seeTurn() - 1).getChoice() == 2) {
				if(board.getMosaicArea().size() >= 1 || board.getAmphoraArea().size() >= 1 || board.getSkeletonArea().size() >= 1) {
					character.power(players.get(seeTurn() - 1), players.get(seeTurn() - 1).getChoice());
					players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
				}
				else
					return 0;
			}
			else if(players.get(seeTurn() - 1).getChoice() == 3) {
				if(board.getMosaicArea().size() >= 1 || board.getStatueArea().size() >= 1 || board.getSkeletonArea().size() >= 1) {
					character.power(players.get(seeTurn() - 1), players.get(seeTurn() - 1).getChoice());
					players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
				}
				else
					return 0;
			}
			else {
				if(board.getMosaicArea().size() >= 1 || board.getAmphoraArea().size() >= 1 || board.getStatueArea().size() >= 1) {
					character.power(players.get(seeTurn() - 1), players.get(seeTurn() - 1).getChoice());
					players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
				}
				else
					return 0;
			}			
		}
		else if(character instanceof Professor) {
			if(players.get(seeTurn() - 1).getChoice() == 1) {
				if(board.getStatueArea().size() >= 1 && board.getAmphoraArea().size() >= 1 && board.getSkeletonArea().size() >= 1) {
					character.power(players.get(seeTurn() - 1), players.get(seeTurn() - 1).getChoice());
					players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
				}
				else
					return 0;
			}
			else if(players.get(seeTurn() - 1).getChoice() == 2) {
				if(board.getMosaicArea().size() >= 1 && board.getAmphoraArea().size() >= 1 && board.getSkeletonArea().size() >= 1) {
					character.power(players.get(seeTurn() - 1), players.get(seeTurn() - 1).getChoice());
					players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
				}
				else
					return 0;
			}
			else if(players.get(seeTurn() - 1).getChoice() == 3) {
				if(board.getMosaicArea().size() >= 1 && board.getStatueArea().size() >= 1 && board.getSkeletonArea().size() >= 1) {
					character.power(players.get(seeTurn() - 1), players.get(seeTurn() - 1).getChoice());
					players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
				}
				else
					return 0;
			}
			else {
				if(board.getMosaicArea().size() >= 1 && board.getAmphoraArea().size() >= 1 && board.getStatueArea().size() >= 1) {
					character.power(players.get(seeTurn() - 1), players.get(seeTurn() - 1).getChoice());
					players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
				}
				else
					return 0;
			}			
			
		}
		else {
			if(board.getAmphoraArea().size() == 0 && board.getMosaicArea().size() == 0 && board.getSkeletonArea().size() == 0 && board.getStatueArea().size() == 0)
				return 0;
			else {
				character.power(players.get(seeTurn() - 1), players.get(seeTurn() - 1).getChoice());
				players.get(seeTurn() - 1).setCurrentCharacterUsed(character);
			}
		}
		return 1;
	}
}