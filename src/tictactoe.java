import java.awt.*;
import java.awt.event.*;

/**
 * Class to create and manage the GUI window on run
 */
class Fdemo extends Frame implements ActionListener{

    //Create array for the buttons
    Button b[] = new Button[9];
    int k=0, x=8, y=28;

    //creating the GUI to play our game of TicTacToe
    Fdemo(){
        setLayout(null);
        setVisible(true);
        setSize(800,600);
        setLocation(400,100);
        setBackground(Color.black);
        setForeground(Color.white);

        //for loop to iterate and create the buttons on run
        for(int i=1; i<=3; i++){
            for(int j=1; j<=3;j++){
                b[k] = new Button();
                b[k].setSize(100,100);
                b[k].setLocation(x,y);
                b[k].setFont(new Font("", Font.BOLD, 40));

                //add the button
                add(b[k]);

                b[k].addActionListener(this);
                b[k].setBackground(Color.orange);
                k++;
                //Move the location for the next button by the same size as the buttons
                x=x+100;
            }

            //reset the coordinates
            x=8;
            y=y+100;
        }

    }

    public void actionPerformed(ActionEvent e){

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
