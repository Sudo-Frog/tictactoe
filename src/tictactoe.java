import java.awt.*;
import java.awt.event.*;

/**
 * Class to create and manage the GUI window on run
 */
class Fdemo extends Frame implements ActionListener{

    //Create array for the buttons
    Button b[] = new Button[9]; //Gameplay Buttons
    Button b1; //New Game Button

    int k=0, x=8, y=28;
    int a = 0; //O and X
    int p1,p2,p3,p4,p5,p6,p7,p8,p9 = 0;
    int l = 70;

    //creating the GUI to play our game of TicTacToe
Fdemo() {
        setLayout(null);
        setSize(800, 600);
        setLocation(400, 100);
        setBackground(Color.black);
        setForeground(Color.white);

        int k = 0, x = 8, y = 28;

        // Create 9 TicTacToe buttons
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                b[k] = new Button();
                b[k].setSize(100, 100);
                b[k].setLocation(x, y);
                b[k].setFont(new Font("", Font.BOLD, 40));
                b[k].setBackground(Color.orange);
                b[k].setForeground(Color.black);
                b[k].addActionListener(this);
                add(b[k]);
                k++;
                x += 100;
            }
            x = 8;
            y += 100;
        }

        // New Game button
        b1 = new Button("New Game");
        b1.setSize(150, 40);
        b1.setLocation(500, 300);
        b1.setFont(new Font("", Font.BOLD, 20));
        b1.setBackground(Color.orange);
        b1.setForeground(Color.black);
        b1.addActionListener(this);
        add(b1);

        // Show frame *after* adding components
        setVisible(true);

        // Force repaint after display to ensure AWT colors apply
        validate();
        repaint();
    }

    

    /**
     * Method to perform actions on buttons
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1){
            for(int i=0; i<=8; i++){
                b[i].setLabel("");
            }
            //restart the game
            p1=0; p2=0; p3=0;p4=0;p5=0;p6=0;p7=0;p8=0;p9=0;
        }

        		// For the 9 buttons
		
		if(e.getSource()==b[0] &&  p1==0){
			
			if(a%2==0){
				b[0].setLabel("O"); p1++; a++;
			}
			else{
				b[0].setLabel("X"); p1++; a++;
			}
				
		}
		if(e.getSource()==b[1] &&  p2==0){
			
			if(a%2==0){
				b[1].setLabel("O"); p2++; a++;
			}
			else{
				b[1].setLabel("X"); p2++; a++;
			}
				
		}
		if(e.getSource()==b[2] &&  p3==0){
			
			if(a%2==0){
				b[2].setLabel("O"); p3++; a++;
			}
			else{
				b[2].setLabel("X"); p3++; a++;
			}
				
		}
		if(e.getSource()==b[3] &&  p4==0){
			
			if(a%2==0){
				b[3].setLabel("O"); p4++; a++;
			}
			else{
				b[3].setLabel("X"); p4++; a++;
			}
				
		}
		if(e.getSource()==b[4] &&  p5==0){
			
			if(a%2==0){
				b[4].setLabel("O"); p5++; a++;
			}
			else{
				b[4].setLabel("X"); p5++; a++;
			}
				
		}
		if(e.getSource()==b[5] &&  p6==0){
			
			if(a%2==0){
				b[5].setLabel("O"); p6++; a++;
			}
			else{
				b[5].setLabel("X"); p6++; a++;
			}
				
		}
		if(e.getSource()==b[6] &&  p7==0){
			
			if(a%2==0){
				b[6].setLabel("O"); p7++; a++;
			}
			else{
				b[6].setLabel("X"); p7++; a++;
			}
				
		}
		if(e.getSource()==b[7] &&  p8==0){
			
			if(a%2==0){
				b[7].setLabel("O"); p8++; a++;
			}
			else{
				b[7].setLabel("X"); p8++; a++;
			}
				
		}
		if(e.getSource()==b[8] &&  p9==0){
			
			if(a%2==0){
				b[8].setLabel("O"); p9++; a++;
			}
			else{
				b[8].setLabel("X"); p9++; a++;
			}
				
		}

        		// Winning conditions
		
		Font f = new Font("",Font.BOLD,20);
		
		if(b[0].getLabel()==b[1].getLabel() && b[0].getLabel()==b[2].getLabel()){
			
			if(b[0].getLabel()=="O"){
				
				Label p1 = new Label("Player 1 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}
			
			if(b[0].getLabel()=="X"){
				
				Label p1 = new Label("Player 2 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}			
		}
		else if(b[3].getLabel()==b[4].getLabel() && b[4].getLabel()==b[5].getLabel()){
			
			if(b[3].getLabel()=="O"){
				
				Label p1 = new Label("Player 1 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}
			
			if(b[3].getLabel()=="X"){
				
				Label p1 = new Label("Player 2 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}			
		}
		else if(b[6].getLabel()==b[7].getLabel() && b[6].getLabel()==b[8].getLabel()){
			
			if(b[6].getLabel()=="O"){
				
				Label p1 = new Label("Player 1 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}
			
			if(b[6].getLabel()=="X"){
				
				Label p1 = new Label("Player 2 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}			
		}
		else if(b[0].getLabel()==b[3].getLabel() && b[0].getLabel()==b[6].getLabel()){
			
			if(b[0].getLabel()=="O"){
				
				Label p1 = new Label("Player 1 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}
			
			if(b[0].getLabel()=="X"){
				
				Label p1 = new Label("Player 2 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}			
		}
		else if(b[1].getLabel()==b[4].getLabel() && b[1].getLabel()==b[7].getLabel()){
			
			if(b[1].getLabel()=="O"){
				
				Label p1 = new Label("Player 1 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}
			
			if(b[1].getLabel()=="X"){
				
				Label p1 = new Label("Player 2 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}			
		}
		else if(b[2].getLabel()==b[5].getLabel() && b[5].getLabel()==b[8].getLabel()){
			
			if(b[2].getLabel()=="O"){
				
				Label p1 = new Label("Player 1 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}
			
			if(b[2].getLabel()=="X"){
				
				Label p1 = new Label("Player 2 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}			
		}
		else if(b[0].getLabel()==b[4].getLabel() && b[0].getLabel()==b[8].getLabel()){
			
			if(b[0].getLabel()=="O"){
				
				Label p1 = new Label("Player 1 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}
			
			if(b[0].getLabel()=="X"){
				
				Label p1 = new Label("Player 2 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}			
		}
		else if(b[2].getLabel()==b[4].getLabel() && b[2].getLabel()==b[6].getLabel()){
			
			if(b[2].getLabel()=="O"){
				
				Label p1 = new Label("Player 1 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}
			
			if(b[2].getLabel()=="X"){
				
				Label p1 = new Label("Player 2 wins");
				p1.setSize(150,50);
				p1.setLocation(320,l);
				p1.setFont(f);
				add(p1);
				l+=50;
			}			
		}

}
}

/**
 * 
 * @param args
 */
public class tictactoe {
    public static void main(String[] args)
    {
        Fdemo f = new Fdemo();
    }
}
