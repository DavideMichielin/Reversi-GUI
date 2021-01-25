import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Start extends JFrame implements ActionListener{

    public Start() {
        setLocationRelativeTo(null); //center of the screen
        setSize(300,100);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Othello-Reversi Menu");
        setLayout(new BorderLayout());
        JPanel container = new JPanel(new GridBagLayout());
        add(container, BorderLayout.CENTER);
        JPanel buttonMenu = new JPanel(new GridLayout(1,3,5,10));
        JButton play = new JButton("Play");
        JButton help = new JButton("Help");
        JButton exit = new JButton("Exit");
        buttonMenu.add(play);
        buttonMenu.add(help);
        buttonMenu.add(exit);
        container.add(buttonMenu);
        play.addActionListener(this);
        help.addActionListener(this);
        exit.addActionListener(this);
        setVisible(true);
        /*SpinnerModel value =
                new SpinnerNumberModel(8,
                        4,
                        26,
                        2);

        String[] typeOfGame = {"Othello", "Reversi"};

        JFrame frame = new JFrame("Othello/Reversi");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel contenitore = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        ++c.gridy;
        ++c.gridy;
        ++c.gridy;

        JPanel menu = new JPanel(new GridLayout(1,1 ));
        menu.setPreferredSize(new Dimension(500,35));
        menu.add(new JLabel("Menu Game",SwingConstants.CENTER));
        contenitore.add(menu,c);

        JPanel playerSelection = new JPanel(new GridLayout(2, 3));
        playerSelection.setPreferredSize(new Dimension(400, 50));
        playerSelection.add(new JLabel("Player-1:", SwingConstants.CENTER));
        playerSelection.add(getEmptyLabel(new Dimension(50, 25)));
        playerSelection.add(new JLabel("Player-2:", SwingConstants.CENTER));
        playerSelection.add(new JTextField());
        playerSelection.add(getEmptyLabel(new Dimension(50, 25)));
        playerSelection.add(new JTextField());
        ++c.gridy;
        contenitore.add(playerSelection,c);

        JPanel rowsAndColumnSelection = new JPanel(new GridLayout(1, 2, 10, 3  ));
        rowsAndColumnSelection.setPreferredSize(new Dimension(400, 30));
        rowsAndColumnSelection.add(new JLabel("Rows and Column", SwingConstants.CENTER));

        rowsAndColumnSelection.add(new JSpinner(value));
        ++c.gridy;
        contenitore.add(rowsAndColumnSelection,c);

        JPanel selectTypeOfGame = new JPanel(new GridLayout(2,1));
        selectTypeOfGame.setPreferredSize(new Dimension(200,35));
        selectTypeOfGame.add(new JLabel("Type Of Game", SwingConstants.CENTER));
        selectTypeOfGame.add(new JComboBox(typeOfGame));
        ++c.gridy;
        contenitore.add(selectTypeOfGame,c);

        JPanel submissionPanel = new JPanel(new GridLayout(1, 1));
        JButton startGame = new JButton("Start Game");
        submissionPanel.add(startGame);
        ++c.gridy;
        contenitore.add(submissionPanel,c);

        frame.setContentPane(contenitore);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        frame.setVisible(true);*/
    }

    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
            case "Play":
                Play p = new Play();
                setVisible(false);
                break;
            case "Help":
                JOptionPane.showMessageDialog(this, "Descrizione del gioco");
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
        }
    }

    public static void main(String[] args) {
        new Start();
    }
}
