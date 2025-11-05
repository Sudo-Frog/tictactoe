import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class FdemoSwing extends JFrame implements ActionListener {

    private BoardPanel boardPanel; //JPanel to hold our 9 buttons
    private OverlayPanel overlay; //An overlay to hold our red victory line
    private JButton newGame;
    private JLabel statusLabel;

    private int turn = 0;
    private boolean gameOver = false; //if the game is over or not. Set to false because the game isn't over before it starts. 

    public FdemoSwing() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocation(400, 100);
        getContentPane().setBackground(Color.black);
        setLayout(null);

        //A layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(8, 28, 300, 300);
        layeredPane.setLayout(null);
        add(layeredPane);

        //Create the game board (bottom layer)
        boardPanel = new BoardPanel();
        boardPanel.setBounds(0, 0, 300, 300);
        layeredPane.add(boardPanel, Integer.valueOf(0));

        //Create the overlay panel (the top layer and transparent)
        overlay = new OverlayPanel(boardPanel);
        overlay.setBounds(0, 0, 300, 300);
        overlay.setOpaque(false);
        layeredPane.add(overlay, Integer.valueOf(1)); //the layer is higher

        //Create the JButton for our new game button.
        newGame = new JButton("New Game!");
        newGame.setBounds(500, 300, 150, 40);
        newGame.setFont(new Font("", Font.BOLD, 20));
        newGame.setBackground(Color.orange);
        newGame.setForeground(Color.black);
        newGame.addActionListener(this);
        add(newGame);

        //Create the status label to communicate the game's status to players (game over, victories, etc)
        statusLabel = new JLabel("", SwingConstants.LEFT);
        statusLabel.setBounds(500, 250, 300, 40);
        statusLabel.setFont(new Font("", Font.BOLD, 18));
        statusLabel.setForeground(Color.white);
        add(statusLabel);

        setVisible(true);
    }

    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FdemoSwing::new);
    }

    /**
     * Event Handling
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            resetGame();
            return;
        }

        //if the game is over
        if (gameOver) return;

        //Checking for winner and alternating turns
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == boardPanel.buttons[i] &&
                boardPanel.buttons[i].getText().equals("")) {

                //alternate turns by incrementing
                boardPanel.buttons[i].setText(turn % 2 == 0 ? "O" : "X");
                turn++;
                checkWinner();
                break;
            }
        }
    }



    // v =========== GAME LOGIC ============ v

    /**
     * Function to reset the game.
     * Clears the buttons, winning combo, status label, and resets turn order. 
     */
    private void resetGame() {
        for (JButton btn : boardPanel.buttons) {
            btn.setText("");
            btn.setEnabled(true);
            btn.setBackground(Color.orange);
        }
        statusLabel.setText("");
        turn = 0;
        gameOver = false;
        overlay.setWinningCombo(null);
        overlay.repaint();
    }

    /**
     * Function to check the current game state and determine if there is a winner and which player is the winner. 
     */
    private void checkWinner() {
        int[][] wins = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
        };   //The winning combos^, these are the indices of the buttons that would result in a classic TicTacToe victory

        for (int[] combo : wins) {
            String s1 = boardPanel.buttons[combo[0]].getText();
            String s2 = boardPanel.buttons[combo[1]].getText();
            String s3 = boardPanel.buttons[combo[2]].getText();

            if (!s1.equals("") && s1.equals(s2) && s2.equals(s3)) {
                String winner = s1.equals("O") ? "Player 1" : "Player 2";
                statusLabel.setText(winner + " wins!");
                gameOver = true;
                disableButtons();
                overlay.setWinningCombo(combo);
                overlay.repaint();
                return;
            }
        }

        boolean allFilled = true;
        for (JButton btn : boardPanel.buttons) {
            if (btn.getText().equals("")) {
                allFilled = false;
                break;
            }
        }

        //Logic for a draw: all buttons filled without a victory
        if (allFilled) {
            statusLabel.setText("It's a draw!");
            gameOver = true;
        }
    }

    /**
     * Function to disable buttons between/after games
     * Can't play if the current game is over
     */
    private void disableButtons() {
        for (JButton btn : boardPanel.buttons) btn.setEnabled(false);
    }


    //v ======== PANELS AND OVERLAYS ========= v

    //This is the board game that houses the buttons (the 3x3 grid the game is played within)
    private class BoardPanel extends JPanel {
        JButton[] buttons = new JButton[9]; //the buttons

        BoardPanel() {
            setLayout(null);
            setBackground(Color.gray); //background for the game
            int x = 0, y = 0;

            //create and add each of the 9 buttons
            for (int i = 0; i < 9; i++) {
                buttons[i] = new JButton("");
                buttons[i].setBounds(x, y, 100, 100);
                buttons[i].setFont(new Font("", Font.BOLD, 40));
                buttons[i].setBackground(Color.orange);
                buttons[i].setForeground(Color.black);
                buttons[i].addActionListener(FdemoSwing.this);
                add(buttons[i]);
                x += 100;
                if ((i + 1) % 3 == 0) { x = 0; y += 100; }
            }
        }
    }

    //Transparent overlay panel that houses the red victory line if there is a vixtory
    private class OverlayPanel extends JPanel {
        private int[] winningCombo = null; //The winning combo is stored here because it is this panel's responsiblity
        private final BoardPanel board;

        //constructor for the OverlayPanel
        OverlayPanel(BoardPanel board) {
            this.board = board;
        }

        //logic for setting the winning combo
        void setWinningCombo(int[] combo) {
            this.winningCombo = combo;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (winningCombo != null) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(5));
                g2.setColor(Color.red);

                Rectangle r1 = board.buttons[winningCombo[0]].getBounds();
                Rectangle r3 = board.buttons[winningCombo[2]].getBounds();

                int x1 = r1.x + r1.width / 2;
                int y1 = r1.y + r1.height / 2;
                int x2 = r3.x + r3.width / 2;
                int y2 = r3.y + r3.height / 2;

                g2.drawLine(x1, y1, x2, y2);
            }
        }
    }
}