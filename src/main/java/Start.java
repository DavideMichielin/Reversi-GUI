import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Start extends JFrame implements ActionListener{

    public Start() {


        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton help = new JButton("?");
        final JButton exit = new JButton("X");
        topPanel.add(help);
        topPanel.add(exit);
        add(topPanel,BorderLayout.NORTH);

        help.addActionListener(this);
        exit.addActionListener(this);

        JPanel container = new JPanel(new GridBagLayout());
        add(container, BorderLayout.CENTER);
        JPanel buttonMenu = new JPanel(new GridLayout(1,3,5,10));
        JButton oneVsOne = new JButton("vs 2Player");
        JButton oneVsCPU = new JButton("vs Computer");
        JButton online = new JButton("Play");
        buttonMenu.add(oneVsOne);
        buttonMenu.add(oneVsCPU);
        buttonMenu.add(online);
        container.add(buttonMenu);
        online.addActionListener(this);
        setLocationRelativeTo(null); //center of the screen
        setSize(500,300);
        setUndecorated(true);
        setResizable(false);
        setTitle("Othello-Reversi Menu");
        setVisible(true);

        //JPanel menuPannel = new JPanel(new GridLayout(1, 2));

        //add(menuPannel, BorderLayout.EAST);
        //JMenuBar menu = new JMenuBar();
        //menuPannel.add(menu);
        //JMenu help = new JMenu("Help");
        //JMenuItem rules = new JMenuItem("Rule");
        //JMenuItem x = new JMenuItem("X");
        //menu.add(help);
        //help.add(rules);
        //menu.add(rules);
        //menu.add(x);





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
                new Play();
                setVisible(false);
                break;
            case "?":
                JOptionPane.showMessageDialog(this, "Descrizione del gioco");
                break;
            case "X":
                System.exit(0);
                break;
            default:
        }
    }

    public static void main(String[] args) {
        new Start();
    }
}
