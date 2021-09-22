package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

//import java.util.List;
//import javax.imageio.ImageIO;
//import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
//import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

import Controller.Controller;
import Model.*;
import Model.Character;

/**
 * 
 * @author Alexandros Angelakis csd4334
 *
 */
@SuppressWarnings("serial")
public class GraphicalUI extends JFrame {
	private Controller game;
	private JPanel player1_field;
	private JPanel player2_field;
	private JPanel player3_field;
	private JPanel player4_field;
	private JLabel players_turn;
	private JLabel useACharacter;
	private JLabel players_inventory;
	private JLabel toDo;
	private JButton drawTiles, endTurn;
	private JLabel backgroundImage;
	private JLabel currentTilesInBag;
	private JButton digger1, archaeologist1, assistant1, professor1;
	private JButton digger2, archaeologist2, assistant2, professor2;
	private JButton digger3, archaeologist3, assistant3, professor3;
	private JButton digger4, archaeologist4, assistant4, professor4;
	private Icon diggerIcon, assistantIcon, professorIcon, archaeologistIcon;
	private Icon greenMosaicIcon, redMosaicIcon, yellowMosaicIcon;
	private Icon caryatidIcon, sphinxIcon;
	private Icon landslideIcon;
	private Icon upperBodyOldSkeletonIcon, upperBodyYoungSkeletonIcon, lowerBodyOldSkeletonIcon,
			lowerBodyYoungSkeletonIcon;
	private Icon blueAmphoraIcon, brownAmphoraIcon, redAmphoraIcon, greenAmphoraIcon, yellowAmphoraIcon,
			purpleAmphoraIcon;
	private JPanel player1Characters;
	private JPanel player2Characters;
	private JPanel player3Characters;
	private JPanel player4Characters;
	private JPanel generalPanel;
	private JPanel panel1, panel2,	panel4, panel5, panel7, panel8;
	private ArrayList<JButton> mosaicButtons = new ArrayList<JButton>();
	private ArrayList<JButton> skeletonButtons = new ArrayList<JButton>();
	private ArrayList<JButton> amphoraButtons = new ArrayList<JButton>();
	private ArrayList<JButton> statueButtons = new ArrayList<JButton>();
	private ArrayList<JButton> landslideButtons = new ArrayList<JButton>();
	private JPanel mosaicPanel, skeletonPanel, amphoraPanel, statuePanel, landslidePanel;
	JMenuItem filePauseMusic, fileResumeMusic, fileStopMusic, fileStartMusic, exitGame, howToPlayGR, howToPlayENG;
	AudioInputStream audioInput;
	Clip clip;
	File musicPath = new File("music1.wav"); // copyright free music from -> https://www.youtube.com/watch?v=rPK_QQuwKDI
	
	/**
	 * <b>Constructor:</b> Creates a new Window and initialises some buttons and
	 * panels. <br>
	 * <b>Postcondition:</b> Creates a new windows and initialises some buttons and
	 * panels starting the game.
	 * @throws IOException 
	 * @throws Exception 
	 */
	public GraphicalUI() throws Exception {
		game = new Controller();
		filePauseMusic = new JMenuItem("Pause");
		fileResumeMusic = new JMenuItem("Resume");
		fileStopMusic = new JMenuItem("Stop");
		fileStartMusic = new JMenuItem("Start");
		exitGame = new JMenuItem("Exit Game");
		howToPlayGR = new JMenuItem("How To Play [GR]");
		howToPlayENG = new JMenuItem("How To Play [ENG]");
		filePauseMusic.addActionListener(new MusicListener());
		fileResumeMusic.addActionListener(new MusicListener());
		fileStopMusic.addActionListener(new MusicListener());
		fileStartMusic.addActionListener(new MusicListener());
		exitGame.addActionListener(new SettingsListener());
		howToPlayGR.addActionListener(new SettingsListener());
		howToPlayENG.addActionListener(new SettingsListener());
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.lightGray);
		this.setJMenuBar(menuBar);
		JMenu musicMenu = new JMenu("Music");
		JMenu settingsMenu = new JMenu("Settings");
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(settingsMenu);
		menuBar.add(musicMenu);
		menuBar.add(helpMenu);
		settingsMenu.add(exitGame);
		helpMenu.add(howToPlayGR);
		helpMenu.add(howToPlayENG);
		musicMenu.add(fileStartMusic);
		musicMenu.add(filePauseMusic);
		musicMenu.add(fileResumeMusic);
		musicMenu.add(fileStopMusic);
		audioInput = AudioSystem.getAudioInputStream(musicPath);		// I saw how to put music from -> https://www.youtube.com/watch?v=TErboGLHZGA&t=364s
		clip = AudioSystem.getClip();
		clip.open(audioInput);
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-20.0f);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
		UIManager.put("OptionPane.background", new ColorUIResource(Color.CYAN));
		diggerIcon = new ImageIcon(
				new ImageIcon("images/digger.png").getImage().getScaledInstance(180, 255, Image.SCALE_AREA_AVERAGING));
		assistantIcon = new ImageIcon(new ImageIcon("images/assistant.png").getImage().getScaledInstance(180, 255,
				Image.SCALE_AREA_AVERAGING));
		professorIcon = new ImageIcon(new ImageIcon("images/professor.png").getImage().getScaledInstance(180, 255,
				Image.SCALE_AREA_AVERAGING));
		archaeologistIcon = new ImageIcon(new ImageIcon("images/archaeologist.png").getImage().getScaledInstance(180,
				255, Image.SCALE_AREA_AVERAGING));
		greenMosaicIcon = new ImageIcon(new ImageIcon("images/mosaic_green.png").getImage().getScaledInstance(65, 65,
				Image.SCALE_AREA_AVERAGING));
		redMosaicIcon = new ImageIcon(new ImageIcon("images/mosaic_red.png").getImage().getScaledInstance(65, 65,
				Image.SCALE_AREA_AVERAGING));
		yellowMosaicIcon = new ImageIcon(new ImageIcon("images/mosaic_yellow.png").getImage().getScaledInstance(65, 65,
				Image.SCALE_AREA_AVERAGING));
		caryatidIcon = new ImageIcon(
				new ImageIcon("images/caryatid.png").getImage().getScaledInstance(65, 65, Image.SCALE_AREA_AVERAGING));
		sphinxIcon = new ImageIcon(
				new ImageIcon("images/sphinx.png").getImage().getScaledInstance(65, 65, Image.SCALE_AREA_AVERAGING));
		upperBodyOldSkeletonIcon = new ImageIcon(new ImageIcon("images/skeleton_big_top.png").getImage()
				.getScaledInstance(65, 65, Image.SCALE_AREA_AVERAGING));
		lowerBodyOldSkeletonIcon = new ImageIcon(new ImageIcon("images/skeleton_big_bottom.png").getImage()
				.getScaledInstance(65, 65, Image.SCALE_AREA_AVERAGING));
		upperBodyYoungSkeletonIcon = new ImageIcon(new ImageIcon("images/skeleton_small_top.png").getImage()
				.getScaledInstance(65, 65, Image.SCALE_AREA_AVERAGING));
		lowerBodyYoungSkeletonIcon = new ImageIcon(new ImageIcon("images/skeleton_small_bottom.png").getImage()
				.getScaledInstance(65, 65, Image.SCALE_AREA_AVERAGING));
		blueAmphoraIcon = new ImageIcon(new ImageIcon("images/amphora_blue.png").getImage().getScaledInstance(65, 65,
				Image.SCALE_AREA_AVERAGING));
		brownAmphoraIcon = new ImageIcon(new ImageIcon("images/amphora_brown.png").getImage().getScaledInstance(65, 65,
				Image.SCALE_AREA_AVERAGING));
		redAmphoraIcon = new ImageIcon(new ImageIcon("images/amphora_red.png").getImage().getScaledInstance(65, 65,
				Image.SCALE_AREA_AVERAGING));
		greenAmphoraIcon = new ImageIcon(new ImageIcon("images/amphora_green.png").getImage().getScaledInstance(65, 65,
				Image.SCALE_AREA_AVERAGING));
		yellowAmphoraIcon = new ImageIcon(new ImageIcon("images/amphora_yellow.png").getImage().getScaledInstance(65,
				65, Image.SCALE_AREA_AVERAGING));
		purpleAmphoraIcon = new ImageIcon(new ImageIcon("images/amphora_purple.png").getImage().getScaledInstance(65,
				65, Image.SCALE_AREA_AVERAGING));
		landslideIcon = new ImageIcon(
				new ImageIcon("images/landslide.png").getImage().getScaledInstance(80, 80, Image.SCALE_AREA_AVERAGING));
		mosaicPanel = new JPanel();
		skeletonPanel = new JPanel();
		amphoraPanel = new JPanel();
		statuePanel = new JPanel();
		landslidePanel = new JPanel();
		player1_field = new JPanel();
		player2_field = new JPanel();
		player3_field = new JPanel();
		player4_field = new JPanel();
		players_turn = new JLabel();
		useACharacter = new JLabel();
		players_turn.setFont(new Font("Consolas", Font.PLAIN, 20));
		players_turn.setText("Player's: 1 turn");
		useACharacter.setFont(new Font("Consolas", Font.PLAIN, 20));
		useACharacter.setText("Use a character");
		drawTiles = new JButton("Draw tiles");
		drawTiles.setFont(new Font("Consolas", Font.PLAIN, 30));
		drawTiles.addActionListener(new drawTileListener());
		endTurn = new JButton("End turn");
		endTurn.setFont(new Font("Consolas", Font.PLAIN, 30));
		endTurn.addActionListener(new endTurnListener());
		drawTiles.setBackground(Color.RED);
		endTurn.setBackground(Color.RED);
		digger1 = new JButton(diggerIcon);
		archaeologist1 = new JButton(archaeologistIcon);
		assistant1 = new JButton(assistantIcon);
		professor1 = new JButton(professorIcon);
		digger1.addActionListener(new characterListener());
		archaeologist1.addActionListener(new characterListener());
		assistant1.addActionListener(new characterListener());
		professor1.addActionListener(new characterListener());
		digger2 = new JButton(diggerIcon);
		archaeologist2 = new JButton(archaeologistIcon);
		assistant2 = new JButton(assistantIcon);
		professor2 = new JButton(professorIcon);
		digger2.addActionListener(new characterListener());
		archaeologist2.addActionListener(new characterListener());
		assistant2.addActionListener(new characterListener());
		professor2.addActionListener(new characterListener());
		digger3 = new JButton(diggerIcon);
		archaeologist3 = new JButton(archaeologistIcon);
		assistant3 = new JButton(assistantIcon);
		professor3 = new JButton(professorIcon);
		digger3.addActionListener(new characterListener());
		archaeologist3.addActionListener(new characterListener());
		assistant3.addActionListener(new characterListener());
		professor3.addActionListener(new characterListener());
		digger4 = new JButton(diggerIcon);
		archaeologist4 = new JButton(archaeologistIcon);
		assistant4 = new JButton(assistantIcon);
		professor4 = new JButton(professorIcon);
		digger4.addActionListener(new characterListener());
		archaeologist4.addActionListener(new characterListener());
		assistant4.addActionListener(new characterListener());
		professor4.addActionListener(new characterListener());
		backgroundImage = new JLabel();
		currentTilesInBag = new JLabel();
		currentTilesInBag.setFont(new Font("Consolas", Font.PLAIN, 20));
		currentTilesInBag.setText("Current tiles in bag: " + game.numberOfTilesInBag());
		players_inventory = new JLabel();
		players_inventory.setFont(new Font("Consolas", Font.PLAIN, 30));
		players_inventory.setText("Player's: 1 inventory");
		toDo = new JLabel();
		toDo.setFont(new Font("Consolas", Font.PLAIN, 25));
		toDo.setText("<html><U>Player: 1</U><br> " + "-Draw tiles from bag. &#9711 <br>"
				+ "-Pick tiles from board. &#9711 <br>" + "-(Optional) Choose a character. &#9711 <br>" + "-End your turn.</html>");
		generalPanel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel7 = new JPanel();
		panel8 = new JPanel();
		player1Characters = new JPanel();
		player2Characters = new JPanel();
		player3Characters = new JPanel();
		player4Characters = new JPanel();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Amphipolis Game");
		this.setSize(1750, 1250);
		ImageIcon img = new ImageIcon("images/landslide.png");
		this.setIconImage(img.getImage());
		this.setVisible(true);
		initComponents();
	}

	/**
	 * <b>Transformer:</b>:initializes some buttons and labels <br>
	 * <b>Postcondition:</b> initializes some buttons and labels @throws
	 */
	private void initComponents() {
		this.setLayout(null);
		backgroundImage.setIcon(new ImageIcon(
				new ImageIcon("images/background.png").getImage().getScaledInstance(1200, 1050, Image.SCALE_DEFAULT)));
		digger1.setPreferredSize(new Dimension(180, 255));
		assistant1.setPreferredSize(new Dimension(180, 255));
		professor1.setPreferredSize(new Dimension(180, 255));
		archaeologist1.setPreferredSize(new Dimension(180, 255));
		digger2.setPreferredSize(new Dimension(180, 255));
		assistant2.setPreferredSize(new Dimension(180, 255));
		professor2.setPreferredSize(new Dimension(180, 255));
		archaeologist2.setPreferredSize(new Dimension(180, 255));
		digger3.setPreferredSize(new Dimension(180, 255));
		assistant3.setPreferredSize(new Dimension(180, 255));
		professor3.setPreferredSize(new Dimension(180, 255));
		archaeologist3.setPreferredSize(new Dimension(180, 255));
		digger4.setPreferredSize(new Dimension(180, 255));
		assistant4.setPreferredSize(new Dimension(180, 255));
		professor4.setPreferredSize(new Dimension(180, 255));
		archaeologist4.setPreferredSize(new Dimension(180, 255));
		generalPanel.add(backgroundImage);
		generalPanel.setBounds(0, 0, 1200, 1050);
		panel1.add(players_turn);
		panel1.setBackground(Color.GREEN);
		panel2.add(useACharacter);
		panel2.setBackground(Color.CYAN);
		panel5.setBackground(Color.MAGENTA);
		panel7.setBackground(Color.GRAY);
		panel8.setBackground(Color.CYAN);
		player1Characters.add(digger1);
		player1Characters.add(archaeologist1);
		player1Characters.add(assistant1);
		player1Characters.add(professor1);
		player2Characters.add(digger2);
		player2Characters.add(archaeologist2);
		player2Characters.add(assistant2);
		player2Characters.add(professor2);
		player3Characters.add(digger3);
		player3Characters.add(archaeologist3);
		player3Characters.add(assistant3);
		player3Characters.add(professor3);
		player4Characters.add(digger4);
		player4Characters.add(archaeologist4);
		player4Characters.add(assistant4);
		player4Characters.add(professor4);
		panel4.add(drawTiles);
		panel4.add(endTurn);
		panel5.add(currentTilesInBag);
		panel7.add(players_inventory);
		panel8.add(toDo);
		this.add(mosaicPanel);
		this.add(amphoraPanel);
		this.add(statuePanel);
		this.add(skeletonPanel);
		this.add(landslidePanel);
		this.add(generalPanel);
		this.add(player1Characters);
		this.add(player2Characters);
		this.add(player3Characters);
		this.add(player4Characters);
		this.add(panel1);
		this.add(panel2);
		this.add(panel4);
		this.add(panel5);
		this.add(panel7);
		this.add(panel8);
		this.add(player1_field);
		this.add(player2_field);
		this.add(player3_field);
		this.add(player4_field);
		panel1.setBounds(1375, 50, 200, 27);
		panel2.setBounds(1375, 100, 200, 27);
		player1Characters.setBounds(1245, 150, 450, 550);
		player2Characters.setBounds(1245, 150, 450, 550);
		player3Characters.setBounds(1245, 150, 450, 550);
		player4Characters.setBounds(1245, 150, 450, 550);
		panel4.setBounds(1245, 750, 450, 50);
		panel5.setBounds(1305, 700, 325, 27);
		panel7.setBounds(1200, 1013, 550, 37);
		panel8.setBounds(1200, 850, 550, 173);
		mosaicPanel.setBounds(35, 32, 350, 280);
		amphoraPanel.setBounds(35, 750, 350, 280);
		statuePanel.setBounds(817, 32, 350, 280);
		skeletonPanel.setBounds(817, 750, 350, 280);
		landslidePanel.setBounds(427, 462, 350, 345);
		player1_field.setBounds(0, 1050, 1750, 200);
		player2_field.setBounds(0, 1050, 1750, 200);
		player3_field.setBounds(0, 1050, 1750, 200);
		player4_field.setBounds(0, 1050, 1750, 200);
		landslidePanel.setBackground(new Color(236, 208, 159));
		statuePanel.setBackground(new Color(236, 208, 159));
		mosaicPanel.setBackground(new Color(236, 208, 159));
		amphoraPanel.setBackground(new Color(236, 208, 159));
		skeletonPanel.setBackground(new Color(236, 208, 159));
		player1Characters.setVisible(true);
		player2Characters.setVisible(false);
		player3Characters.setVisible(false);
		player4Characters.setVisible(false);
		player1_field.setVisible(true);
		player2_field.setVisible(false);
		player3_field.setVisible(false);
		player4_field.setVisible(false);
		player1_field.setBackground(new Color(236, 208, 159));
		player2_field.setBackground(new Color(236, 208, 159));
		player3_field.setBackground(new Color(236, 208, 159));
		player4_field.setBackground(new Color(236, 208, 159));
		this.validate();

		ArrayList<Tile> mosaicDrawnTiles = game.getBoard().getMosaicArea();
		ArrayList<Tile> amphoraDrawnTiles = game.getBoard().getAmphoraArea();
		ArrayList<Tile> statueDrawnTiles =  game.getBoard().getStatueArea();
		ArrayList<Tile> skeletonDrawnTiles = game.getBoard().getSkeletonArea();
		JButton newButton = new JButton();
		newButton.addActionListener(new tileButtonsListener());
		newButton.setPreferredSize(new Dimension(65, 65));
		if (((Mosaic) mosaicDrawnTiles.get(0)).getCol() == MosaicColors.GREEN) {
			newButton.setIcon(greenMosaicIcon);
		} else if (((Mosaic) mosaicDrawnTiles.get(0)).getCol() == MosaicColors.RED) {
			newButton.setIcon(redMosaicIcon);
		} else {
			newButton.setIcon(yellowMosaicIcon);
		}
		mosaicButtons.add(newButton);
		mosaicPanel.add(newButton);
		
		JButton newButton1 = new JButton();
		newButton1.addActionListener(new tileButtonsListener());
		newButton1.setPreferredSize(new Dimension(65, 65));
		if (((Amphora) amphoraDrawnTiles.get(0)).getCol() == AmphoraColors.BLUE) {
			newButton1.setIcon(blueAmphoraIcon);
		} else if (((Amphora) amphoraDrawnTiles.get(0)).getCol() == AmphoraColors.BROWN) {
			newButton1.setIcon(brownAmphoraIcon);
		} else if (((Amphora) amphoraDrawnTiles.get(0)).getCol() == AmphoraColors.GREEN) {
			newButton1.setIcon(greenAmphoraIcon);
		} else if (((Amphora) amphoraDrawnTiles.get(0)).getCol() == AmphoraColors.PURPLE) {
			newButton1.setIcon(purpleAmphoraIcon);
		} else if (((Amphora) amphoraDrawnTiles.get(0)).getCol() == AmphoraColors.RED) {
			newButton1.setIcon(redAmphoraIcon);
		} else {
			newButton1.setIcon(yellowAmphoraIcon);
		}
		amphoraButtons.add(newButton1);
		amphoraPanel.add(newButton1);
		
		JButton newButton2 = new JButton();
		newButton2.addActionListener(new tileButtonsListener());
		newButton2.setPreferredSize(new Dimension(65, 65));
		if (statueDrawnTiles.get(0) instanceof Sphinx) {
			newButton2.setIcon(sphinxIcon);
		} else {
			newButton2.setIcon(caryatidIcon);
		}
		statueButtons.add(newButton2);
		statuePanel.add(newButton2);
		
		JButton newButton3 = new JButton();
		newButton3.addActionListener(new tileButtonsListener());
		newButton3.setPreferredSize(new Dimension(65, 65));
		if (((Skeleton) skeletonDrawnTiles.get(0)).getIsUp() && ((Skeleton) skeletonDrawnTiles.get(0)).getIsOld()) {
			newButton3.setIcon(upperBodyOldSkeletonIcon);
		} else if (((Skeleton) skeletonDrawnTiles.get(0)).getIsUp() && !((Skeleton) skeletonDrawnTiles.get(0)).getIsOld()) {
			newButton3.setIcon(upperBodyYoungSkeletonIcon);
		} else if (!((Skeleton) skeletonDrawnTiles.get(0)).getIsUp() && ((Skeleton) skeletonDrawnTiles.get(0)).getIsOld()) {
			newButton3.setIcon(lowerBodyOldSkeletonIcon);
		} else {
			newButton3.setIcon(lowerBodyYoungSkeletonIcon);
		}
		skeletonButtons.add(newButton3);
		skeletonPanel.add(newButton3);

		this.revalidate();
	}

	/**
	 * <b>Transformer:</b>:initializes some buttons and labels after tiles have been
	 * put on the board. <br>
	 * <b>Postcondition:</b> initializes some buttons and labels after tiles have
	 * been put on the board.
	 */
	public void initButtonTiles() {

	}

	/*
	 * a class which is used for doing some action after a character button has been
	 * pushed
	 */
	private class characterListener implements ActionListener {

		/**
		 * <b>Transformer:</b> Doing some action after a character button has been
		 * pushed. <br>
		 * <b>Postcondition:</b> Doing some action after a character button has been
		 * pushed.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			ImageIcon icn = new ImageIcon(new ImageIcon("images/landslide.png").getImage().getScaledInstance(50, 50,
					Image.SCALE_AREA_AVERAGING));
			Object buttonPressed = e.getSource();
			Player currentPlayer = game.getCurrentPlayer();
			int flag;
			ArrayList<Character> currentPlayerCharacters = currentPlayer.getCharacters(); // index 0: assistant, index
																							// 1: archaeologist, index
																							// 2: digger, index 3:
																							// professor
			if (currentPlayer.getHasDrewTiles() && !currentPlayer.getHasChoseCharacter()
					&& currentPlayer.getHasDrewTilesFromBoard()) {
				if (currentPlayer.getID() == 1) {
					if (buttonPressed == assistant1) {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(0));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 1 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							currentPlayer.useCharacter(currentPlayerCharacters.get(0));
							assistant1.setEnabled(false);
						}
						else {
							JOptionPane.showMessageDialog(null,
									"You cant use the card Assistant because all of the areas have no tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
						}
					} else if (buttonPressed == digger1) {	
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(2));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 1 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							digger1.setEnabled(false);
							currentPlayer.useCharacter(currentPlayerCharacters.get(2));
						}
						else
							JOptionPane.showMessageDialog(null,
									"You can't use the card Digger because the area you selected tiles from has no tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
					} else if (buttonPressed == professor1) {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(3));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 1 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							professor1.setEnabled(false);
							currentPlayer.useCharacter(currentPlayerCharacters.get(3));
						}
						else {
							JOptionPane.showMessageDialog(null,
									"You can't use the card Professor because there is at least one area without any tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
						}
					} else {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(1));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 1 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							archaeologist1.setEnabled(false);
							currentPlayer.useCharacter(currentPlayerCharacters.get(1));
						}
						else
							JOptionPane.showMessageDialog(null,
									"You can't use the card Archaeologist because there are no other areas that have any tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
					}
				} else if (currentPlayer.getID() == 2) {
					if (buttonPressed == assistant2) {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(0));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 2 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							currentPlayer.useCharacter(currentPlayerCharacters.get(0));
							assistant2.setEnabled(false);
						}
						else {
							JOptionPane.showMessageDialog(null,
									"You cant use the card Assistant because all of the areas have no tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
						}
					} else if (buttonPressed == digger2) {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(2));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 2 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							digger2.setEnabled(false);
							currentPlayer.useCharacter(currentPlayerCharacters.get(2));
						}
						else
							JOptionPane.showMessageDialog(null,
									"You can't use the card Digger because the area you selected tiles from has no tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
					} else if (buttonPressed == professor2) {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(3));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 2 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							professor2.setEnabled(false);
							currentPlayer.useCharacter(currentPlayerCharacters.get(3));
						}
						else {
							JOptionPane.showMessageDialog(null,
									"You can't use the card Professor because there is at least one area without any tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
						}
					} else {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(1));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 2 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							archaeologist2.setEnabled(false);
							currentPlayer.useCharacter(currentPlayerCharacters.get(1));
						}
						else
							JOptionPane.showMessageDialog(null,
									"You can't use the card Archaeologist because there are no other areas that have any tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
					}
				} else if (currentPlayer.getID() == 3) {
					if (buttonPressed == assistant3) {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(0));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 3 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							currentPlayer.useCharacter(currentPlayerCharacters.get(0));
							assistant3.setEnabled(false);
						}
						else {
							JOptionPane.showMessageDialog(null,
									"You can't use the card Assistant because all of the areas have no tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
						}
					} else if (buttonPressed == digger3) {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(2));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 3 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							digger3.setEnabled(false);
							currentPlayer.useCharacter(currentPlayerCharacters.get(2));
						}
						else
							JOptionPane.showMessageDialog(null,
									"You can't use the card Digger because the area you selected tiles from has no tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
					} else if (buttonPressed == professor3) {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(3));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 3 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							professor3.setEnabled(false);
							currentPlayer.useCharacter(currentPlayerCharacters.get(3));
						}
						else {
							JOptionPane.showMessageDialog(null,
									"You can't use the card Professor because there is at least one area without any tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
						}
					} else {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(1));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 3 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							archaeologist3.setEnabled(false);
							currentPlayer.useCharacter(currentPlayerCharacters.get(1));
						}
						else
							JOptionPane.showMessageDialog(null,
									"You can't use the card Archaeologist because there are no other areas that have any tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
					}
				} else {
					if (buttonPressed == assistant4) {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(0));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 4 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							currentPlayer.useCharacter(currentPlayerCharacters.get(0));
							assistant4.setEnabled(false);
						}
						else {
							JOptionPane.showMessageDialog(null,
									"You can't use the card Assistant because all of the areas have no tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
						}
					} else if (buttonPressed == digger4) {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(2));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 4 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							digger4.setEnabled(false);
							currentPlayer.useCharacter(currentPlayerCharacters.get(2));
						}
						else
							JOptionPane.showMessageDialog(null,
									"You can't use the card Digger because the area you selected tiles from has no tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
					} else if (buttonPressed == professor4) {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(3));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 4 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							professor4.setEnabled(false);
							currentPlayer.useCharacter(currentPlayerCharacters.get(3));
						}
						else {
							JOptionPane.showMessageDialog(null,
									"You can't use the card Professor because there is at least one area without any tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
						}
					} else {
						flag = game.usePowerOfCharacter(currentPlayerCharacters.get(1));
						if(flag == 1) {
							toDo.setText("<html><U>Player: 4 </U><br> "
									+ "-Draw tiles from bag. &#9989 <br>" + "-Pick tiles from board. &#9711 <br>"
									+ "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
							archaeologist4.setEnabled(false);
							currentPlayer.useCharacter(currentPlayerCharacters.get(1));
						}
						else
							JOptionPane.showMessageDialog(null,
									"You can't use the card Archaeologist because there are no other areas that have any tiles.",
									"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
					}
				}
			} else {
				if (currentPlayer.getHasChoseCharacter())
					JOptionPane.showMessageDialog(null,
							"Player: " + currentPlayer.getID() + " has already used a character this round.",
							"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
				else if (!currentPlayer.getHasDrewTiles())
					JOptionPane.showMessageDialog(null,
							"Player: " + currentPlayer.getID() + " You need to draw tiles from the bag first.",
							"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
				else
					JOptionPane.showMessageDialog(null,
							"Player: " + currentPlayer.getID()
									+ " You need to pick tiles from the board to use a character.",
							"Character use error!", JOptionPane.INFORMATION_MESSAGE, icn);
			}

		}
	}

	/*
	 * a class which is used for doing some action after the end Turn button has
	 * been pushed
	 */
	private class endTurnListener implements ActionListener {

		/**
		 * <b>Transformer:</b> It ends the turn of the player and gives the turn to the
		 * next player. <br>
		 * <b>Postcondition:</b> The turn is passed to the next player.
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			//Object buttonPressed = e.getSource();
			Player currentPlayer = game.getCurrentPlayer();
			if (currentPlayer.getHasDrewTiles() && currentPlayer.getHasDrewTilesFromBoard()) {
				ImageIcon icn = new ImageIcon(new ImageIcon("images/landslide.png").getImage().getScaledInstance(50, 50,
						Image.SCALE_AREA_AVERAGING));
				int input = JOptionPane.showConfirmDialog(null, "End turn for Player: " + currentPlayer.getID() + " ?",
						"End turn confirmation.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icn);
				if (input == 0) {
					if (currentPlayer.getID() == 4) {
						players_turn.setText("Player's: 1 turn");
						players_inventory.setText("Player's: 1 inventory");
						toDo.setText("<html><U>Player: 1</U><br> " + "-Draw tiles from bag. &#9711 <br>"
								+ "-Pick tiles from board. &#9711 <br>" + "-(Optional) Choose a character. &#9711 <br>" + "-End your turn.</html>");
						player1Characters.setVisible(true);
						player2Characters.setVisible(false);
						player3Characters.setVisible(false);
						player4Characters.setVisible(false);
						player1_field.setVisible(true);
						player2_field.setVisible(false);
						player3_field.setVisible(false);
						player4_field.setVisible(false);

					} else if (currentPlayer.getID() == 1) {
						players_turn.setText("Player's: " + (game.seeTurn() + 1) + " turn");
						players_inventory.setText("Player's: " + (game.seeTurn() + 1) + " inventory");
						toDo.setText("<html><U>Player: " + (game.seeTurn() + 1) + "</U><br> "
								+ "-Draw tiles from bag. &#9711 <br>" + "-Pick tiles from board. &#9711 <br>"
								+ "-(Optional) Choose a character. &#9711 <br>" + "-End your turn.</html>");
						player1Characters.setVisible(false);
						player2Characters.setVisible(true);
						player3Characters.setVisible(false);
						player4Characters.setVisible(false);
						player1_field.setVisible(false);
						player2_field.setVisible(true);
						player3_field.setVisible(false);
						player4_field.setVisible(false);
					} else if (currentPlayer.getID() == 2) {
						players_turn.setText("Player's: " + (game.seeTurn() + 1) + " turn");
						players_inventory.setText("Player's: " + (game.seeTurn() + 1) + " inventory");
						toDo.setText("<html><U>Player: " + (game.seeTurn() + 1) + "</U><br> "
								+ "-Draw tiles from bag. &#9711 <br>" + "-Pick tiles from board. &#9711 <br>"
								+ "-(Optional) Choose a character. &#9711 <br>" + "-End your turn.</html>");
						player1Characters.setVisible(false);
						player2Characters.setVisible(false);
						player3Characters.setVisible(true);
						player4Characters.setVisible(false);
						player1_field.setVisible(false);
						player2_field.setVisible(false);
						player3_field.setVisible(true);
						player4_field.setVisible(false);
					} else {
						players_turn.setText("Player's: " + (game.seeTurn() + 1) + " turn");
						players_inventory.setText("Player's: " + (game.seeTurn() + 1) + " inventory");
						toDo.setText("<html><U>Player: " + (game.seeTurn() + 1) + "</U><br> "
								+ "-Draw tiles from bag. &#9711 <br>" + "-Pick tiles from board. &#9711 <br>"
								+ "-(Optional) Choose a character. &#9711 <br>" + "-End your turn.</html>");
						player1Characters.setVisible(false);
						player2Characters.setVisible(false);
						player3Characters.setVisible(false);
						player4Characters.setVisible(true);
						player1_field.setVisible(false);
						player2_field.setVisible(false);
						player3_field.setVisible(false);
						player4_field.setVisible(true);
					}
					game.setTurn();				
				}
			} else {
				if (!currentPlayer.getHasDrewTiles())
					JOptionPane.showMessageDialog(null,
							"Player: " + currentPlayer.getID()
									+ " has not drawn any tiles from the bag yet to end his turn.",
							"End turn error!", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null,
							"Player: " + currentPlayer.getID()
									+ " has not drawn any tiles from the board yet to end his turn .",
							"End turn error!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/*
	 * a class which is used for doing some action after the draw Tiles button has
	 * been pushed
	 */
	private class drawTileListener implements ActionListener {

		/**
		 * <b>Transformer:</b> It draws tiles for the player if he hasn't drawn already.
		 * <br>
		 * <b>Postcondition:</b> Tile have been drawn from the player and put to the
		 * board areas, otherwise a pop up window is shown.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			ImageIcon icn = new ImageIcon(new ImageIcon("images/landslide.png").getImage().getScaledInstance(50, 50,
					Image.SCALE_AREA_AVERAGING));
			ImageIcon icn1 = new ImageIcon(new ImageIcon("images/game_over.png").getImage().getScaledInstance(100, 100,
					Image.SCALE_AREA_AVERAGING));
			//Object buttonPressed = e.getSource();
			Player currentPlayer = game.getCurrentPlayer();
			ArrayList<Tile> drawnTiles = new ArrayList<>();
			ArrayList<Tile> mosaicDrawnTiles = new ArrayList<Tile>();
			ArrayList<Tile> amphoraDrawnTiles = new ArrayList<Tile>();
			ArrayList<Tile> statueDrawnTiles = new ArrayList<Tile>();
			ArrayList<Tile> skeletonDrawnTiles = new ArrayList<Tile>();
			ArrayList<Tile> landslideDrawnTiles = new ArrayList<Tile>();
			if (!currentPlayer.getHasDrewTiles()) {
				System.out.println(currentPlayer.getID());
				drawnTiles = currentPlayer.drawTilesFromBag(game.getBag());
				game.setTilesToBoard(drawnTiles);
				mosaicDrawnTiles = game.getBoard().getMosaicArea();
				amphoraDrawnTiles = game.getBoard().getAmphoraArea();
				statueDrawnTiles = game.getBoard().getStatueArea();
				skeletonDrawnTiles = game.getBoard().getSkeletonArea();
				landslideDrawnTiles = game.getBoard().getLandslideArea();
				for (JButton i : mosaicButtons) {
					mosaicPanel.remove(i);
				}

				for (JButton i : amphoraButtons) {
					amphoraPanel.remove(i);
				}

				for (JButton i : statueButtons) {
					statuePanel.remove(i);
				}

				for (JButton i : skeletonButtons) {
					skeletonPanel.remove(i);
				}

				for (JButton i : landslideButtons) {
					landslidePanel.remove(i);
				}

				mosaicButtons.removeAll(mosaicButtons);
				amphoraButtons.removeAll(amphoraButtons);
				statueButtons.removeAll(statueButtons);
				skeletonButtons.removeAll(skeletonButtons);
				landslideButtons.removeAll(landslideButtons);
				for (int i = 0; i < landslideDrawnTiles.size(); i++) {
					JButton newButton = new JButton();
					newButton.setPreferredSize(new Dimension(80, 80));
					// newButton.setEnabled(false);
					newButton.setIcon(landslideIcon);
					landslideButtons.add(newButton);
					landslidePanel.add(newButton);
				}

				for (Tile i : mosaicDrawnTiles) {
					JButton newButton = new JButton();
					newButton.addActionListener(new tileButtonsListener());
					newButton.setPreferredSize(new Dimension(65, 65));
					if (((Mosaic) i).getCol() == MosaicColors.GREEN) {
						newButton.setIcon(greenMosaicIcon);
					} else if (((Mosaic) i).getCol() == MosaicColors.RED) {
						newButton.setIcon(redMosaicIcon);
					} else {
						newButton.setIcon(yellowMosaicIcon);
					}
					mosaicButtons.add(newButton);
					mosaicPanel.add(newButton);
				}

				for (Tile i : amphoraDrawnTiles) {
					JButton newButton = new JButton();
					newButton.addActionListener(new tileButtonsListener());
					newButton.setPreferredSize(new Dimension(65, 65));
					if (((Amphora) i).getCol() == AmphoraColors.BLUE) {
						newButton.setIcon(blueAmphoraIcon);
					} else if (((Amphora) i).getCol() == AmphoraColors.BROWN) {
						newButton.setIcon(brownAmphoraIcon);
					} else if (((Amphora) i).getCol() == AmphoraColors.GREEN) {
						newButton.setIcon(greenAmphoraIcon);
					} else if (((Amphora) i).getCol() == AmphoraColors.PURPLE) {
						newButton.setIcon(purpleAmphoraIcon);
					} else if (((Amphora) i).getCol() == AmphoraColors.RED) {
						newButton.setIcon(redAmphoraIcon);
					} else {
						newButton.setIcon(yellowAmphoraIcon);
					}
					amphoraButtons.add(newButton);
					amphoraPanel.add(newButton);
				}

				for (Tile i : statueDrawnTiles) {
					JButton newButton = new JButton();
					newButton.addActionListener(new tileButtonsListener());
					newButton.setPreferredSize(new Dimension(65, 65));
					if (i instanceof Sphinx) {
						newButton.setIcon(sphinxIcon);
					} else {
						newButton.setIcon(caryatidIcon);
					}
					statueButtons.add(newButton);
					statuePanel.add(newButton);
				}

				for (Tile i : skeletonDrawnTiles) {
					JButton newButton = new JButton();
					newButton.addActionListener(new tileButtonsListener());
					newButton.setPreferredSize(new Dimension(65, 65));
					if (((Skeleton) i).getIsUp() && ((Skeleton) i).getIsOld()) {
						newButton.setIcon(upperBodyOldSkeletonIcon);
					} else if (((Skeleton) i).getIsUp() && !((Skeleton) i).getIsOld()) {
						newButton.setIcon(upperBodyYoungSkeletonIcon);
					} else if (!((Skeleton) i).getIsUp() && ((Skeleton) i).getIsOld()) {
						newButton.setIcon(lowerBodyOldSkeletonIcon);
					} else {
						newButton.setIcon(lowerBodyYoungSkeletonIcon);
					}
					skeletonButtons.add(newButton);
					skeletonPanel.add(newButton);
				}

				System.out.println(mosaicButtons.size() + " " + mosaicDrawnTiles.size());
				currentTilesInBag.setText("Current tiles in bag: " + game.numberOfTilesInBag());
				toDo.setText(
						"<html><U>Player: " + (currentPlayer.getID()) + "</U><br> " + "-Draw tiles from bag. &#9989 <br>"
								+ "-Pick tiles from board. &#9711 <br>" + "-(Optional) Choose a character. &#9711 <br>" + "-End your turn.</html>");
				game.getBoard().finishGame(game.getBag());
				if (game.getBoard().hasFinished()) {
					game.calculateScore();
					ArrayList<Player> temp = game.getPlayers();
					String str = "=========================================== ScoreBoard ==========================================\n"
							+	"++-------------++--- Mosaic ---++--- Caryatid ---++--- Sphinx ---++--- Amphora ---++--- Skeleton ---++--- Total ---++-------------++\n"
							+	"+ Player 1   |             " + temp.get(0).getMosaicScore() + "             |            " + temp.get(0).getCaryatidScore() + "              |             " + temp.get(0).getSphinxScore() + "            |             " + temp.get(0).getAmphoraScore() + "                 |             " + temp.get(0).getSkeletonScore() + "                |           " + temp.get(0).getScore() + "       |                      +\n" +
								"+--------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n" + 
								"+ Player 2   |             " + temp.get(1).getMosaicScore() + "             |            " + temp.get(1).getCaryatidScore() + "              |             " + temp.get(1).getSphinxScore() + "            |             " + temp.get(1).getAmphoraScore() + "                 |             " + temp.get(1).getSkeletonScore() + "                |           " + temp.get(1).getScore() + "       |                      +\n" +
								"+--------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n" + 
								"+ Player 3   |             " + temp.get(2).getMosaicScore() + "             |            " + temp.get(2).getCaryatidScore() + "              |             " + temp.get(2).getSphinxScore() + "            |             " + temp.get(2).getAmphoraScore() + "                 |             " + temp.get(2).getSkeletonScore() + "                |           " + temp.get(2).getScore() + "       |                      +\n" +
								"+--------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n" + 
								"+ Player 4   |             " + temp.get(3).getMosaicScore() + "             |            " + temp.get(3).getCaryatidScore() + "              |             " + temp.get(3).getSphinxScore() + "            |             " + temp.get(3).getAmphoraScore() + "                 |             " + temp.get(3).getSkeletonScore() + "                |           " + temp.get(3).getScore() + "       |                      +\n" +
								//"++----------------------------------------------------------------------------------------------------------------------------------------------------------------++";
								"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
								
					// calculate the scores of each player and declare the winner!
					JOptionPane.showMessageDialog(null, "The winner is: Player " + game.seeWinner() + " !\n" + str, "Game Over!",
							JOptionPane.INFORMATION_MESSAGE, icn1);
					System.exit(0);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Player: " + currentPlayer.getID() + " has already drawn tiles this round.",
						"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE, icn);
			}
		}

	}

	/*
	 * a class which is used for doing some action after a tile button has been
	 * pushed from the board.
	 */
	private class tileButtonsListener implements ActionListener {

		/**
		 * <b>Transformer:</b> Doing some action after a tile button has been pushed
		 * from the board. <br>
		 * <b>Postcondition:</b> Doing some action after a tile button has been pushed
		 * from the board.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Object buttonPressed = e.getSource();
			Player currentPlayer = game.getCurrentPlayer();
			ArrayList<Tile> mosaicDrawnTiles = new ArrayList<Tile>();
			ArrayList<Tile> amphoraDrawnTiles = new ArrayList<Tile>();
			ArrayList<Tile> statueDrawnTiles = new ArrayList<Tile>();
			ArrayList<Tile> skeletonDrawnTiles = new ArrayList<Tile>();
			//ArrayList<Tile> landslideDrawnTiles = new ArrayList<Tile>();
			mosaicDrawnTiles = game.getBoard().getMosaicArea();
			amphoraDrawnTiles = game.getBoard().getAmphoraArea();
			statueDrawnTiles = game.getBoard().getStatueArea();
			skeletonDrawnTiles = game.getBoard().getSkeletonArea();
			//landslideDrawnTiles = game.getBoard().getLandslideArea();
			int success;
			
			if (currentPlayer.getID() == 1) {
				if (currentPlayer.getHasDrewTiles() && currentPlayer.getNumOfTilesHeDrew() < 2) {
					for (int i = 0; i < mosaicButtons.size(); i++) {
						if (buttonPressed == mosaicButtons.get(i)) {
							if(currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == 6) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 1 || currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 7 || currentPlayer.getChoice() == 8 || currentPlayer.getChoice() == 9) {
									success = game.pickTilesFromBoard(mosaicDrawnTiles.get(i));
									if(success == 1) {
										player1_field.add(mosaicButtons.get(i));
										// game.getBoard().removeTileFromArea(mosaicDrawnTiles.get(i));
										mosaicPanel.remove(mosaicButtons.get(i));
										mosaicButtons.get(i).setEnabled(false);
										mosaicButtons.get(i).setDisabledIcon(mosaicButtons.get(i).getIcon());
										mosaicButtons.remove(mosaicButtons.get(i));
										mosaicPanel.revalidate();
										mosaicPanel.repaint();
									}
									else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} 
								else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
													"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
					
					

					for (int i = 0; i < skeletonButtons.size(); i++) {
						if (buttonPressed == skeletonButtons.get(i)) {
							if(currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == 9) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 4 || currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 7 || currentPlayer.getChoice() == 8 || currentPlayer.getChoice() == 6) {
									success = game.pickTilesFromBoard(skeletonDrawnTiles.get(i));
									if(success == 1) {
										player1_field.add(skeletonButtons.get(i));
										// game.getBoard().removeTileFromArea(skeletonDrawnTiles.get(i));
										skeletonPanel.remove(skeletonButtons.get(i));
										skeletonButtons.get(i).setEnabled(false);
										skeletonButtons.get(i).setDisabledIcon(skeletonButtons.get(i).getIcon());
										skeletonButtons.remove(skeletonButtons.get(i));
										skeletonPanel.revalidate();
										skeletonPanel.repaint();
									}
									else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}

					for (int i = 0; i < statueButtons.size(); i++) {
						if (buttonPressed == statueButtons.get(i)) {
							if(currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == 7) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 2 || currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 6 || currentPlayer.getChoice() == 8 || currentPlayer.getChoice() == 9) {
									success = game.pickTilesFromBoard(statueDrawnTiles.get(i));
									if(success == 1) {
										player1_field.add(statueButtons.get(i));
										// game.getBoard().removeTileFromArea(statueDrawnTiles.get(i));
										statuePanel.remove(statueButtons.get(i));
										statueButtons.get(i).setEnabled(false);
										statueButtons.get(i).setDisabledIcon(statueButtons.get(i).getIcon());
										statueButtons.remove(statueButtons.get(i));
										statuePanel.revalidate();
										statuePanel.repaint();
									}else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}

					for (int i = 0; i < amphoraButtons.size(); i++) {
						if (buttonPressed == amphoraButtons.get(i)) {
							if(currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 8) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 3 || currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == 7 || currentPlayer.getChoice() == 6 || currentPlayer.getChoice() == 9) {
									success = game.pickTilesFromBoard(amphoraDrawnTiles.get(i));
									if(success == 1) {
										player1_field.add(amphoraButtons.get(i));
										// game.getBoard().removeTileFromArea(amphoraDrawnTiles.get(i));
										amphoraPanel.remove(amphoraButtons.get(i));
										amphoraButtons.get(i).setEnabled(false);
										amphoraButtons.get(i).setDisabledIcon(amphoraButtons.get(i).getIcon());
										amphoraButtons.remove(amphoraButtons.get(i));
										amphoraPanel.revalidate();
										amphoraPanel.repaint();
									}
									else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				} else {
					if (!currentPlayer.getHasDrewTiles())
						JOptionPane.showMessageDialog(null, "Player: " + currentPlayer.getID()
								+ " has not drawn tiles from the bag. You cannot pick tiles from the board if you don't draw tiles from the bag first.",
								"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,
								"Player: " + currentPlayer.getID() + " can not draw more tiles this round, except if you use a character.",
								"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);

				}
			} else if (currentPlayer.getID() == 2) {
				if (currentPlayer.getHasDrewTiles() && currentPlayer.getNumOfTilesHeDrew() < 2) {
					for (int i = 0; i < mosaicButtons.size(); i++) {
						if (buttonPressed == mosaicButtons.get(i)) {
							if(currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == 6) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 1 || currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 7 || currentPlayer.getChoice() == 8 || currentPlayer.getChoice() == 9) {
									success = game.pickTilesFromBoard(mosaicDrawnTiles.get(i));
									if(success == 1) {
										player2_field.add(mosaicButtons.get(i));
										// game.getBoard().removeTileFromArea(mosaicDrawnTiles.get(i));
										mosaicPanel.remove(mosaicButtons.get(i));
										mosaicButtons.get(i).setEnabled(false);
										mosaicButtons.get(i).setDisabledIcon(mosaicButtons.get(i).getIcon());
										mosaicButtons.remove(mosaicButtons.get(i));
										mosaicPanel.revalidate();
										mosaicPanel.repaint();
									}
									else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} 
								else {
									JOptionPane.showMessageDialog(null,
													"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
													"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
					
					

					for (int i = 0; i < skeletonButtons.size(); i++) {
						if (buttonPressed == skeletonButtons.get(i)) {
							if(currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == 9) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 4 || currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 7 || currentPlayer.getChoice() == 8 || currentPlayer.getChoice() == 6) {
									success = game.pickTilesFromBoard(skeletonDrawnTiles.get(i));
									if(success == 1) {
										player2_field.add(skeletonButtons.get(i));
										// game.getBoard().removeTileFromArea(skeletonDrawnTiles.get(i));
										skeletonPanel.remove(skeletonButtons.get(i));
										skeletonButtons.get(i).setEnabled(false);
										skeletonButtons.get(i).setDisabledIcon(skeletonButtons.get(i).getIcon());
										skeletonButtons.remove(skeletonButtons.get(i));
										skeletonPanel.revalidate();
										skeletonPanel.repaint();
									}
									else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}

					for (int i = 0; i < statueButtons.size(); i++) {
						if (buttonPressed == statueButtons.get(i)) {
							if(currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == 7) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 2 || currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 6 || currentPlayer.getChoice() == 8 || currentPlayer.getChoice() == 9) {
									success = game.pickTilesFromBoard(statueDrawnTiles.get(i));
									if(success == 1) {
										player2_field.add(statueButtons.get(i));
										// game.getBoard().removeTileFromArea(statueDrawnTiles.get(i));
										statuePanel.remove(statueButtons.get(i));
										statueButtons.get(i).setEnabled(false);
										statueButtons.get(i).setDisabledIcon(statueButtons.get(i).getIcon());
										statueButtons.remove(statueButtons.get(i));
										statuePanel.revalidate();
										statuePanel.repaint();
									}else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}

					for (int i = 0; i < amphoraButtons.size(); i++) {
						if (buttonPressed == amphoraButtons.get(i)) {
							if(currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 8) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 3 || currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == 7 || currentPlayer.getChoice() == 6 || currentPlayer.getChoice() == 9) {
									success = game.pickTilesFromBoard(amphoraDrawnTiles.get(i));
									if(success == 1) {
										player2_field.add(amphoraButtons.get(i));
										// game.getBoard().removeTileFromArea(amphoraDrawnTiles.get(i));
										amphoraPanel.remove(amphoraButtons.get(i));
										amphoraButtons.get(i).setEnabled(false);
										amphoraButtons.get(i).setDisabledIcon(amphoraButtons.get(i).getIcon());
										amphoraButtons.remove(amphoraButtons.get(i));
										amphoraPanel.revalidate();
										amphoraPanel.repaint();
									}
									else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				} else {
					if (!currentPlayer.getHasDrewTiles())
						JOptionPane.showMessageDialog(null, "Player: " + currentPlayer.getID()
								+ " has not drawn tiles from the bag. You cannot pick tiles from the board if you don't draw tiles from the bag first.",
								"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,
								"Player: " + currentPlayer.getID() + " can not draw more tiles this round, except if you use a character.",
								"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);

				}
			} else if (currentPlayer.getID() == 3) {
				if (currentPlayer.getHasDrewTiles() && currentPlayer.getNumOfTilesHeDrew() < 2) {
					for (int i = 0; i < mosaicButtons.size(); i++) {
						if (buttonPressed == mosaicButtons.get(i)) {
							if(currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == 6) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 1 || currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 7 || currentPlayer.getChoice() == 8 || currentPlayer.getChoice() == 9) {
									success = game.pickTilesFromBoard(mosaicDrawnTiles.get(i));
									if(success == 1) {
										player3_field.add(mosaicButtons.get(i));
										// game.getBoard().removeTileFromArea(mosaicDrawnTiles.get(i));
										mosaicPanel.remove(mosaicButtons.get(i));
										mosaicButtons.get(i).setEnabled(false);
										mosaicButtons.get(i).setDisabledIcon(mosaicButtons.get(i).getIcon());
										mosaicButtons.remove(mosaicButtons.get(i));
										mosaicPanel.revalidate();
										mosaicPanel.repaint();
									}
									else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} 
								else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
					
					

					for (int i = 0; i < skeletonButtons.size(); i++) {
						if (buttonPressed == skeletonButtons.get(i)) {
							if(currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == 9) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 4 || currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 7 || currentPlayer.getChoice() == 8 || currentPlayer.getChoice() == 6) {
									success = game.pickTilesFromBoard(skeletonDrawnTiles.get(i));
									if(success == 1) {
										player3_field.add(skeletonButtons.get(i));
										// game.getBoard().removeTileFromArea(skeletonDrawnTiles.get(i));
										skeletonPanel.remove(skeletonButtons.get(i));
										skeletonButtons.get(i).setEnabled(false);
										skeletonButtons.get(i).setDisabledIcon(skeletonButtons.get(i).getIcon());
										skeletonButtons.remove(skeletonButtons.get(i));
										skeletonPanel.revalidate();
										skeletonPanel.repaint();
									}
									else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}

					for (int i = 0; i < statueButtons.size(); i++) {
						if (buttonPressed == statueButtons.get(i)) {
							if(currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == 7) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 2 || currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 6 || currentPlayer.getChoice() == 8 || currentPlayer.getChoice() == 9) {
									success = game.pickTilesFromBoard(statueDrawnTiles.get(i));
									if(success == 1) {
										player3_field.add(statueButtons.get(i));
										// game.getBoard().removeTileFromArea(statueDrawnTiles.get(i));
										statuePanel.remove(statueButtons.get(i));
										statueButtons.get(i).setEnabled(false);
										statueButtons.get(i).setDisabledIcon(statueButtons.get(i).getIcon());
										statueButtons.remove(statueButtons.get(i));
										statuePanel.revalidate();
										statuePanel.repaint();
									}else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}

					for (int i = 0; i < amphoraButtons.size(); i++) {
						if (buttonPressed == amphoraButtons.get(i)) {
							if(currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 8) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 3 || currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == 7 || currentPlayer.getChoice() == 6 || currentPlayer.getChoice() == 9) {
									success = game.pickTilesFromBoard(amphoraDrawnTiles.get(i));
									if(success == 1) {
										player3_field.add(amphoraButtons.get(i));
										// game.getBoard().removeTileFromArea(amphoraDrawnTiles.get(i));
										amphoraPanel.remove(amphoraButtons.get(i));
										amphoraButtons.get(i).setEnabled(false);
										amphoraButtons.get(i).setDisabledIcon(amphoraButtons.get(i).getIcon());
										amphoraButtons.remove(amphoraButtons.get(i));
										amphoraPanel.revalidate();
										amphoraPanel.repaint();
									}
									else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				} else {
					if (!currentPlayer.getHasDrewTiles())
						JOptionPane.showMessageDialog(null, "Player: " + currentPlayer.getID()
								+ " has not drawn tiles from the bag. You cannot pick tiles from the board if you don't draw tiles from the bag first.",
								"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,
								"Player: " + currentPlayer.getID() + " can not draw more tiles this round, except if you use a character.",
								"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);

				}
			} else {
				if (currentPlayer.getHasDrewTiles() && currentPlayer.getNumOfTilesHeDrew() < 2) {
					for (int i = 0; i < mosaicButtons.size(); i++) {
						if (buttonPressed == mosaicButtons.get(i)) {
							if(currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == 6) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 1 || currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 7 || currentPlayer.getChoice() == 8 || currentPlayer.getChoice() == 9) {
									success = game.pickTilesFromBoard(mosaicDrawnTiles.get(i));
									if(success == 1) {
										player4_field.add(mosaicButtons.get(i));
										// game.getBoard().removeTileFromArea(mosaicDrawnTiles.get(i));
										mosaicPanel.remove(mosaicButtons.get(i));
										mosaicButtons.get(i).setEnabled(false);
										mosaicButtons.get(i).setDisabledIcon(mosaicButtons.get(i).getIcon());
										mosaicButtons.remove(mosaicButtons.get(i));
										mosaicPanel.revalidate();
										mosaicPanel.repaint();
									}
									else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} 
								else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
					
					

					for (int i = 0; i < skeletonButtons.size(); i++) {
						if (buttonPressed == skeletonButtons.get(i)) {
							if(currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == 9) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 4 || currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 7 || currentPlayer.getChoice() == 8 || currentPlayer.getChoice() == 6) {
									success = game.pickTilesFromBoard(skeletonDrawnTiles.get(i));
									if(success == 1) {
										player4_field.add(skeletonButtons.get(i));
										// game.getBoard().removeTileFromArea(skeletonDrawnTiles.get(i));
										skeletonPanel.remove(skeletonButtons.get(i));
										skeletonButtons.get(i).setEnabled(false);
										skeletonButtons.get(i).setDisabledIcon(skeletonButtons.get(i).getIcon());
										skeletonButtons.remove(skeletonButtons.get(i));
										skeletonPanel.revalidate();
										skeletonPanel.repaint();
									}
									else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}

					for (int i = 0; i < statueButtons.size(); i++) {
						if (buttonPressed == statueButtons.get(i)) {
							if(currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == 7) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 2 || currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 6 || currentPlayer.getChoice() == 8 || currentPlayer.getChoice() == 9) {
									success = game.pickTilesFromBoard(statueDrawnTiles.get(i));
									if(success == 1) {
										player4_field.add(statueButtons.get(i));
										// game.getBoard().removeTileFromArea(statueDrawnTiles.get(i));
										statuePanel.remove(statueButtons.get(i));
										statueButtons.get(i).setEnabled(false);
										statueButtons.get(i).setDisabledIcon(statueButtons.get(i).getIcon());
										statueButtons.remove(statueButtons.get(i));
										statuePanel.revalidate();
										statuePanel.repaint();
									}else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}

					for (int i = 0; i < amphoraButtons.size(); i++) {
						if (buttonPressed == amphoraButtons.get(i)) {
							if(currentPlayer.getChoice() == -2 || currentPlayer.getChoice() == 8) {
								JOptionPane.showMessageDialog(null,
										"Player: " + currentPlayer.getID() + " cannot draw tiles from the same area! ",
												"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (currentPlayer.getChoice() == 0 || currentPlayer.getChoice() == 3 || currentPlayer.getChoice() == -4 || currentPlayer.getChoice() == -3 || currentPlayer.getChoice() == -1 || currentPlayer.getChoice() == 7 || currentPlayer.getChoice() == 6 || currentPlayer.getChoice() == 9) {
									success = game.pickTilesFromBoard(amphoraDrawnTiles.get(i));
									if(success == 1) {
										player4_field.add(amphoraButtons.get(i));
										// game.getBoard().removeTileFromArea(amphoraDrawnTiles.get(i));
										amphoraPanel.remove(amphoraButtons.get(i));
										amphoraButtons.get(i).setEnabled(false);
										amphoraButtons.get(i).setDisabledIcon(amphoraButtons.get(i).getIcon());
										amphoraButtons.remove(amphoraButtons.get(i));
										amphoraPanel.revalidate();
										amphoraPanel.repaint();
									}
									else {
										JOptionPane.showMessageDialog(null,
												"Player: " + currentPlayer.getID() + " can not draw tiles from an area that he already drew! ",
														"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Player: " + currentPlayer.getID() + " can not draw tiles from an other area! ",
											"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
				} else {
					if (!currentPlayer.getHasDrewTiles())
						JOptionPane.showMessageDialog(null, "Player: " + currentPlayer.getID()
								+ " has not drawn tiles from the bag. You cannot pick tiles from the board if you don't draw tiles from the bag first.",
								"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,
								"Player: " + currentPlayer.getID() + " can not draw more tiles this round, except if you use a character.",
								"Draw Tiles Error.", JOptionPane.INFORMATION_MESSAGE);

				}
			}
			if (currentPlayer.getHasDrewTilesFromBoard())
				toDo.setText(
						"<html><U>Player: " + (currentPlayer.getID()) + "</U><br> " + "-Draw tiles from bag. &#9989 <br>"
								+ "-Pick tiles from board. &#9989 <br>" + "-(Optional) Choose a character. &#9711 <br>" + "-End your turn.</html>");
			if(currentPlayer.getHasChoseCharacter())
				toDo.setText(
						"<html><U>Player: " + (currentPlayer.getID()) + "</U><br> " + "-Draw tiles from bag. &#9989 <br>"
								+ "-Pick tiles from board. &#9989 <br>" + "-(Optional) Choose a character. &#9989 <br>" + "-End your turn.</html>");
		}

	}

	/*
	 * a class which is used for doing some action after a Settings button has been
	 * pushed
	 */
	private class SettingsListener implements ActionListener {

		/**
		 * <b>Transformer:</b> doing some action after an  Exit button has been
		 * pushed <br>
		 * <b>Postcondition:</b> doing some action after an Exit button has
		 * been pushed.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Object buttonPressed = e.getSource();
			if(buttonPressed == exitGame) {
				int input = JOptionPane.showConfirmDialog(null, "Exit Game?",
						"Exit Game.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(input == 0)
					System.exit(0);
			}
			else if(buttonPressed == howToPlayGR) {
				Desktop desktop =  Desktop.getDesktop();
				URI uri;
				try {
					uri = new URI("https://www.youtube.com/watch?v=4dqPWdZO4vk");
					desktop.browse(uri);
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else if(buttonPressed == howToPlayENG) {
				Desktop desktop =  Desktop.getDesktop();
				URI uri;
				try {
					uri = new URI("https://www.youtube.com/watch?v=u1ZBodsDlJU");
					desktop.browse(uri);
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * a class which is used for pausing, stoping, resuming and playing the music in the background
	 * @author alex
	 */
	private class MusicListener implements ActionListener{

		/**
		 * <b>Transformer:</b> Doing some action after a Music JMenuItem has been pushed.
		 * <b>Postcondition:</b> Doing some action after a Music JMenuItem has been pushed. 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Object buttonPressed = e.getSource();
			long clipTimePosition = clip.getMicrosecondPosition();
			if(buttonPressed == filePauseMusic) {
				clip.stop();
			}
			else if(buttonPressed == fileResumeMusic) {
				clip.setMicrosecondPosition(clipTimePosition);
				clip.start();
			}
			else if(buttonPressed == fileStopMusic) {
				clip.setMicrosecondPosition(0);
				clip.stop();
			}
			else {
				clip.setMicrosecondPosition(0);
				clip.start();
			}
		}	
	}
}