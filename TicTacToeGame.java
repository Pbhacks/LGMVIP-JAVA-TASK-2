import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToeGame extends JFrame implements ActionListener {

    private JPanel gameBoard;
    private JButton[] buttons;
    private JLabel currentPlayerLabel;
    private JLabel winnerLabel;

    private char currentPlayer = 'X';

    public TicTacToeGame() {
        super("Tic Tac Toe");

        gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(3, 3));

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(this::actionPerformed);
            gameBoard.add(buttons[i]);
        }

        currentPlayerLabel = new JLabel("Player X's turn");
        winnerLabel = new JLabel();

        add(gameBoard, "Center");
        add(currentPlayerLabel, "North");
        add(winnerLabel, "South");

        setSize(300, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        // Check if the button is already clicked.
        if (button.getText().equals("")) {
            // Set the button text to the current player's symbol.
            button.setText(String.valueOf(currentPlayer));

            // Switch to the next player.
            if (currentPlayer == 'X') {
                currentPlayer = 'O';
            } else {
                currentPlayer = 'X';
            }

            // Update the current player label.
            currentPlayerLabel.setText("Player " + currentPlayer + "'s turn");

            // Check for a winner.
            if (checkForWinner()) {
                gameOver();
            }
        }
    }

    private boolean checkForWinner() {
        // Check for a winner in the rows.
        for (int i = 0; i < 3; i++) {
            if (buttons[i * 3].getText().equals(buttons[i * 3 + 1].getText()) && buttons[i * 3].getText().equals(buttons[i * 3 + 2].getText()) && !buttons[i * 3].getText().equals("")) {
                return true;
            }
        }

        // Check for a winner in the columns.
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(buttons[i + 3].getText()) && buttons[i].getText().equals(buttons[i + 6].getText()) && !buttons[i].getText().equals("")) {
                return true;
            }
        }

        // Check for a winner in the diagonals.
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && !buttons[0].getText().equals("")) {
            return true;
        }
        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && !buttons[2].getText().equals("")) {
            return true;
        }

        return false;
    }

    private void gameOver() {
        // Display the winner of the game.
        winnerLabel.setText("Player " + currentPlayer + " wins!");

        // Disable all the buttons.
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
