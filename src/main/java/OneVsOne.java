import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OneVsOne extends JFrame {
    private final String[] dimensionOfBoard = {"4x4", "6x6", "8x8", "10x10", "12x12", "14x14",
            "16x16", "18x18", "20x20", "22x22", "24x24", "26x26"};

    private final String[] gameType = {"Othello", "Reversi"};

    private final JTextField namePlayer1, namePlayer2;
    private final JComboBox<String> availableDimension, availableGameType;


    public OneVsOne() {
        TopPanel topPanel = new TopPanel(this);
        add(topPanel.getTopPanel(), BorderLayout.NORTH);
        new DraggableFrame(this);
        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        add(container, BorderLayout.CENTER);

        JPanel namePlayersContainer = new JPanel(new GridLayout(2, 2, 70, 15));
        namePlayersContainer.setBorder(new EmptyBorder(0, 0, 17, 0));
        container.add(namePlayersContainer, c);
        JLabel player1 = new JLabel("Name Player 1:");
        namePlayer1 = new JTextField();
        JLabel player2 = new JLabel("Name Player 2:");
        namePlayer2 = new JTextField();
        namePlayersContainer.add(player1);
        namePlayersContainer.add(player2);
        namePlayersContainer.add(namePlayer1);
        namePlayersContainer.add(namePlayer2);
        ++c.gridy;

        JPanel boardConfigurationContainer = new JPanel(new GridLayout(2, 2, 70, 7));
        boardConfigurationContainer.setBorder(new EmptyBorder(0, 0, 25, 0));
        container.add(boardConfigurationContainer, c);
        JLabel dimension = new JLabel("Board Size: ");

        availableDimension = new JComboBox<>(dimensionOfBoard);
        JLabel typeOfGame = new JLabel("Select Game: ");
        availableGameType = new JComboBox<>(gameType);
        availableDimension.setSelectedIndex(2);
        boardConfigurationContainer.add(dimension);
        boardConfigurationContainer.add(typeOfGame);
        boardConfigurationContainer.add(availableDimension);
        boardConfigurationContainer.add(availableGameType);

        ++c.gridy;
        JPanel playButtonContainer = new JPanel();
        container.add(playButtonContainer, c);
        JButton playButton = new JButton("PLAY!");
        playButtonContainer.add(playButton);
        playButton.addActionListener(e -> {
            //invece di fare questo controllo ci sta chiamare il costruttore del "back-end" e vedere se lancia l'eccezione
            if (namePlayer1.getText().isEmpty() || namePlayer2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Insert both player name");
            } else if (namePlayer1.getText().equals(namePlayer2.getText())) {
                JOptionPane.showMessageDialog(this, "Name must be different");
            } else if (namePlayer1.getText().length() > 8 || namePlayer2.getText().length() > 8) {
                JOptionPane.showMessageDialog(this, "One or more player name is too long");
            } else {
                int dimensionBoard = Integer.parseInt(availableDimension.getSelectedItem().toString().split("x")[0]);
                GameBuilder.create2PlayerGameWithGUI(dimensionBoard, namePlayer1.getText(), namePlayer2.getText(), availableGameType.getSelectedItem().toString());
                setVisible(false);
            }
        });

        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //center of the screen
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
    }
}
