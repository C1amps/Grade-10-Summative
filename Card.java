package Models;

import javax.swing.*;

import controllers.cardController;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Class for making the card models
public class Card extends JLabel implements MouseListener {
    Icon faceImage;
    Icon backImage;
    boolean isFaceUp = false;
    int num;
    int iconWidthHalf;
    int iconHeightHalf;
    boolean mousePressed = false;
	private cardController controller;
//Constructor for card object
    public Card(cardController controller,Icon face, Icon back, int number){
        super(back);
        this.faceImage = face;
        this.backImage = back;
        this.num = number;
        this.addMouseListener(this);
        //gets the x and y values of the card 
        this.iconHeightHalf = back.getIconHeight()/2;
        this.iconWidthHalf=face.getIconWidth()/2; 
        this.controller = controller; 
    }


    public int getNum(){
        return num;
    } 
//Checks if cursor is over the card hitbox
//KEY PART: Works by checking the distance from the middle of the card and if either the X and Y values are over the card it returns false, else its true
    private boolean overIcon(int x, int y){
        int distX = Math.abs(x-(this.getWidth()/2));
        int distY = Math.abs(y-this.getHeight()/2);

        if((distX > this.iconWidthHalf * 2) || distY > this.iconHeightHalf * 2){ 
            return false;
        }
        return true;
    }

//handles clicking to flip the cards up if cursor is on hit box
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.overIcon(e.getX(), e.getY())) {
                this.turnUp();
            
        }
        System.out.println("y");
    }
//Gives the card on what display should be seen if its FaceUp
    private void turnUp() {
        if (!this.isFaceUp) {
            this.setIcon(this.faceImage);
            this.isFaceUp = true;
            this.controller.turnUp(this);
        }
    }
  //Gives the card on what display should be seen if its FaceDown
    public void turnDown() {
        if(this.isFaceUp){
            this.setIcon(this.backImage);
            this.isFaceUp = false;
            return;

        }
    }


   //checks if the mouse cursor is over a hitbox/card
    @Override
    public void mousePressed(MouseEvent e) {
        if (overIcon(e.getX(), e.getY())){
            this.mousePressed = true;
        }
    }
    

    //It checks if the mousePressed variable is true and if it is, calls the mouseClicked method
    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.mousePressed){
            this.mousePressed = false;
            this.mouseClicked(e);
            System.out.println("x");
        }
        this.mousePressed = false;
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.mousePressed = false;
    }
}
