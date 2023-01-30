package views;
import Models.Card;
import controllers.cardController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class matchingGame implements ActionListener {
    private JFrame mainFrame;
    private Container mainPane;
    private ImageIcon cardImage[];
    private cardController cardControl;

    public matchingGame(){
        //The main windows
        this.mainFrame=new JFrame("Memory Card Matching Game");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(950,950);
        this.mainPane=this.mainFrame.getContentPane();
        this.mainPane.setLayout(new BoxLayout(this.mainPane, BoxLayout.PAGE_AXIS));
        //This is the menu bar
        JMenuBar menuBar = new JMenuBar();
        this.mainFrame.setJMenuBar(menuBar);
        //Game menu 
        JMenu gameMenu = new JMenu("CLICK HERE FOR GAME OPTIONS");
        menuBar.add(gameMenu);
        //Start Menu
        newStartMenu("New Game", gameMenu, this);
        newStartMenu("Exit", gameMenu, this);

        //Loading Card to Screen
        this.cardImage = loadCardImage();

    }
    //gives the cards an image value 
    private ImageIcon[] loadCardImage(){
        ImageIcon image[] = new ImageIcon[10];
        for(int i = 0; i < 9; i++){
            String fileImages = "images/default/card"+i+".jpg";
            image[i] = new ImageIcon(fileImages);
        }
        return image;
    }
    
    //Makes the 4x4 grid the cards are going to be placed on
    public JPanel makingCards(){
        JPanel panel = new JPanel(new GridLayout(4,4));
        //Sets the cards to have a backside
        ImageIcon backImage = this.cardImage[0];
        cardController controller = new cardController();

        int addingCards[] = new int[16];
        try {
        	//gives values to each card in a pair so they the game can control whether they match or not
        	for (int i = 0; i<8; i++){
        	   addingCards[2 * i] = i + 1;
        	   addingCards[2 * i + 1]= i + 1;
        	}

        }
        catch (ArrayIndexOutOfBoundsException e){
        	System.out.print("e");
        }
        //randomizing
        //Randomzies the card values so they can be placed in a random order on the grid 
        randomizeCardArray(addingCards);
        //making the card objects

        //Intializes/Instantiates the card objects
        for(int i=0; i <addingCards.length;i++){
            int num = addingCards[i];
            Card newCard = new Card(controller, this.cardImage[num], backImage, num);
            panel.add(newCard);
        }
        return panel;
    }

    //Randomzies the card values so they can be placed in a random order on the grid 

    private void randomizeCardArray(int[] t) {
		Random shuffler = new Random();
		for (int i = 0;i<t.length;i++) {
			int x = shuffler.nextInt(t.length);
			//swapping mechanism 
			int s = t[x];
			t[x] = t[i];
			t[i] = s;
			
		}
		
	}
    
    //Adds new game menu to the top left of panel to start a new game
	private void newStartMenu(String new_game, JMenu Menu, ActionListener listen) {
        JMenuItem newItem = new JMenuItem(new_game);
        newItem.setActionCommand(new_game);
        newItem.addActionListener(listen);
        Menu.add(newItem);
    }
   // Removes cards from the main panel and allows them to be visible to the user
	public void newGame(){
	    this.mainPane.removeAll(); //remove any existing cards
	    this.mainPane.add(makingCards()); //create new set of cards
	    this.mainFrame.setVisible(true);
	}

//Checks mouse inputs for 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("New Game")){
            newGame();
        }
        if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        }
    }
    
}
