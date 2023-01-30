package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import Models.Card;
import views.matchingGame;

//This class is controlling the behaviours of the card
public class cardController implements ActionListener {
    private Vector turnedCards; //Dynamic Array to auto add values
    private Timer turnDownTimer; //Timer to flip down cards
    private Timer turnDownDelay; //Amount of time till flip down
    
    //Variable to know when the game has ended
    private int matchedCards;
    private boolean endGame;
    
    public cardController() {
         this.matchedCards = 0;
         this.endGame = false;
        
        this.turnedCards = new Vector(2);
        this.turnDownTimer = new Timer(2000, this);
        this.turnDownTimer.setRepeats(false);
        this.turnDownDelay = new Timer(2000, this);
        this.turnDownDelay.setRepeats(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.turnDownTimer) {
            //go through all the cards in the turnedCards Vector
            for(int i = 0; i<this.turnedCards.size(); i++) {
                //get a card at the current index
                Card card = (Card)this.turnedCards.get(i);
                //turn the card down
                card.turnDown();
            }
            //clear the turnedCards Vector
            this.turnedCards.clear();
        }
    }

    //Checks the value of the vector and if its less than 2 passes the card in as an argument to doAddCard
    public boolean turnUp(Card card) {
        if (this.turnedCards.size()<2) {
            return doAddCard(card);
        }
        return false;
    }
    
    //Adds the argument from turnUp method to the vector 
    private boolean doAddCard(Card card) {
        //Adds the card to vector 
        this.turnedCards.add(card);
        if(this.turnedCards.size()==2) {
            //gets the other card that got turned up
            Card otherCard = (Card)this.turnedCards.get(0);
            //checks if the cards have matching values
            if(otherCard.getNum() == card.getNum()) {
                //clears truned card vector
                this.turnedCards.clear();
                //adds to an array
                this.matchedCards++;
                //checks to see if game is over
                if (matchedCards == 8) {
                   showEndGameMenu();
                }
            }
            //if cards dont match runs the turndown timer again
            else {
                this.turnDownTimer.start();
            }
        }
        return true;
    }


  //End game menu for whether or not you want to 
  	public void showEndGameMenu() {
  		int userChoice = JOptionPane.showOptionDialog(null, "Congratulations, you have won the game!", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Play Again", "Exit"}, null);
  		if (userChoice == 0) {
  			matchingGame mg = new matchingGame();
  			mg.newGame();
  		} 
  		else if (userChoice == 1) {
  			System.exit(0);
  		}
  	}

  }
