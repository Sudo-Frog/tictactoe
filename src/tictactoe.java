import java.awt.*;
import java.awt.event.*;

class Fdemo extends Frame implements ActionListener {

    Button[] b = new Button[9];
    Button newGame;
    Label statusLabel;

    int turn = 0; // even = O's turn odd = X's turn
    boolean gameOver = false; //to track if the game has ended
    int[] winCombo = null; //the indices of the winning combo; used in winning line draw. Storing it this way allows for scalability

    Fdemo() {
        setLayout(null);
        setSize(800, 600);
        setLocation(400, 100);
        setBackground(Color.black);
        setForeground(Color.white);

        int k = 0, x = 8, y = 28;

        //Create the 9 buttons
        for (int i = 0; i < 9; i++) {
            b[i] = new Button("");
            b[i].setSize(100, 100);
            b[i].setLocation(x, y);
            b[i].setFont(new Font("", Font.BOLD, 40));
            b[i].setBackground(Color.orange);
            b[i].setForeground(Color.black);
            b[i].addActionListener(this);
            add(b[i]);

            x += 100;
            if ((i + 1) % 3 == 0) { x = 8; y += 100; }
        }

        //Create the New Game button
        newGame = new Button("New Game");
        newGame.setSize(150, 40);
        newGame.setLocation(500, 300);
        newGame.setFont(new Font("", Font.BOLD, 20));
        newGame.setBackground(Color.orange);
        newGame.setForeground(Color.black);
        newGame.addActionListener(this);
        add(newGame);

        // Create the status label for victories and such
        statusLabel = new Label("");
        statusLabel.setSize(300, 40);
        statusLabel.setLocation(500, 250);
        statusLabel.setFont(new Font("", Font.BOLD, 18));
        statusLabel.setForeground(Color.white);
        add(statusLabel);

        setVisible(true);
        validate();
        repaint();
    }

    // Handle all button clicks
    public void actionPerformed(ActionEvent e) {

        // Handle "New Game" button event
        if (e.getSource() == newGame) {
            resetGame(); //call newGame() function
            return;
        }

        //if the game is over gameOver=true, ignore all clicks on the X/O buttons
        if (gameOver) return;

        // Handle game buttons
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == b[i] && b[i].getLabel().equals("")) {
                b[i].setLabel(turn % 2 == 0 ? "O" : "X");
                turn++;
                checkWinner();
                break;
            }
        }
    }

    /**
     * Method to reset the game of TicTacToe for another round
     */
    private void resetGame() {
        for (Button btn : b) {
            btn.setLabel("");
            btn.setEnabled(true); //re-enable the buttons for a new game
            btn.setBackground(Color.orange);
        }
        statusLabel.setText("");
        turn = 0;
        gameOver = false; //set to false to allow another game
        winCombo = null;  //Reset the winning combo
        repaint();
    }


    /**
     * Called to check for a winner, when a button is clicked
     */
    private void checkWinner() {
        int[][] wins = {
            {0,1,2}, {3,4,5}, {6,7,8}, // rows
            {0,3,6}, {1,4,7}, {2,5,8}, // cols
            {0,4,8}, {2,4,6}           // diagonals
        };

        //Now check each of the indices listed in the possible wins[][] array and check if any
         //of the 3 combinations of indices have the same label that isn't "" (empty)
        for (int[] combo : wins) {
            String s1 = b[combo[0]].getLabel();
            String s2 = b[combo[1]].getLabel();
            String s3 = b[combo[2]].getLabel();

            if (!s1.equals("") && s1.equals(s2) && s2.equals(s3)) {
                String winner = s1.equals("O") ? "Player 1" : "Player 2";
                statusLabel.setText(winner + " wins!");
                gameOver = true;
                disableButtons();
                winCombo = combo;
                repaint();  //trigger a redraw to show the victory line
                return;
            }
        }

        // Draw condition
        boolean allFilled = true;
        for (Button btn : b) {
            if (btn.getLabel().equals("")){
                allFilled = false;
                break;
            }
        }
        if (allFilled) {
            statusLabel.setText("It's a draw!");
        gameOver = true;
        }
    }

    /**
     * Method used to create the victory line across a combo to show the winning 3-square combo
     *  Override paint() from Frame class
     */
    public void paint(Graphics g){
        super.paint(g);

        if(winCombo != null){
            g.setColor(Color.red);
            g.setFont(new Font("", Font.BOLD, 18));
            g.drawString("Winning line!", 500, 200);

            //Get the coordinates of first and last buttons in the winning combo
            Rectangle r1 = b[winCombo[0]].getBounds();
            Rectangle r3 = b[winCombo[2]].getBounds();
                int x1 = r1.x + r1.width / 2;
                int y1 = r1.y + r1.height / 2;
                int x2 = r3.x + r3.width / 2;
                int y2 = r3.y + r3.height / 2;

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(5));
            g2.drawLine(x1, y1, x2, y2);
        }
    }


    /**
     * Method to disable the game buttons (X/O) when game is over.
     */
    private void disableButtons() {
        //for (Button btn : b) btn.removeActionListener(this);
        for (Button btn : b) btn.setEnabled(false);
    }


    public static void main(String[] args) {
        new Fdemo();
    }
}