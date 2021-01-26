import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OneVsOne {
    private final JFrame frame;
    private int mouseX;
    private int mouseY;

    private final String[] dimensionOfBoard = {"4x4", "6x6", "8x8", "10x10", "12x12", "14x14",
            "16x16", "18x18", "20x20", "22x22", "24x24", "26x26"};

    private final String[] gameType = {"Othello", "Reversi"};

    private final JTextField namePlayer1, namePlayer2;
    private final JComboBox<String> availableDimension, availableGameType;


    public OneVsOne() {
        frame = new JFrame("Reversi/Othello Game - Local 2 Player");
        JPanel containerTopPanel = new JPanel();
        JPanel topPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel topPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));

        containerTopPanel.setLayout(new GridLayout(1, 2));
        containerTopPanel.add(topPanelLeft);
        containerTopPanel.add(topPanelRight);

        frame.add(containerTopPanel, BorderLayout.NORTH);
        topPanelRight.setBackground(Color.LIGHT_GRAY);
        topPanelLeft.setBackground(Color.LIGHT_GRAY);
        topPanelRight.setSize(frame.getWidth() / 2, 10);
        topPanelLeft.setSize(frame.getWidth() / 2, 10);

        final JLabel back = new JLabel("\u2190 Back");
        final JLabel help = new JLabel("?");
        final JLabel exit = new JLabel("X");
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        help.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String url = "https://en.wikipedia.org/wiki/Reversi#Rules";

                    Desktop dt = Desktop.getDesktop();
                    URI uri = new URI(url);
                    dt.browse(uri.resolve(uri));
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                help.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Start();
                frame.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                back.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
        exit.setBorder(new EmptyBorder(0, 10, 0, 10));
        exit.setSize(10, 5);
        exit.setFont(new Font("Tahoma", Font.BOLD, 15));
        help.setBorder(new EmptyBorder(0, 10, 0, 10));
        help.setSize(10, 5);
        help.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.setSize(10, 5);
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        topPanelRight.add(help);
        topPanelRight.add(exit);
        topPanelLeft.add(back);

        // nomi dei due player -> v
        // dimensioni -> v
        // tipo di game -> v
        //play

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        frame.add(container, BorderLayout.CENTER);
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
        playButton.addActionListener(play);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //center of the screen
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private final ActionListener play = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //invece di fare questo controllo ci sta chiamare il costruttore del "back-end" e vedere se lancia l'eccezione
            if (namePlayer1.getText().isEmpty() || namePlayer2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Insert both player name");
            } else if (namePlayer1.getText().equals(namePlayer2.getText())) {
                JOptionPane.showMessageDialog(frame, "Change one Player name");
            } else {
                int dimension = Integer.parseInt(availableDimension.getSelectedItem().toString().split("x")[0]);
                new GUIGameManager(namePlayer1.getText(),namePlayer2.getText(), dimension,availableGameType.getSelectedItem().toString());
                frame.setVisible(false);
            }
        }
    };
}
