import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OneVsOne {
    private final JFrame frame;
    private int mouseX;
    private int mouseY;
    public OneVsOne() {
        frame = new JFrame("Reversi/Othello Game - Local 2 Player");
        JPanel containerTopPanel = new JPanel();
        JPanel topPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel topPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));

        containerTopPanel.setLayout(new GridLayout(1,2));
        containerTopPanel.add(topPanelLeft);
        containerTopPanel.add(topPanelRight);

        frame.add(containerTopPanel, BorderLayout.NORTH);
        topPanelRight.setBackground(Color.LIGHT_GRAY);
        topPanelLeft.setBackground(Color.LIGHT_GRAY);
        topPanelRight.setSize(frame.getWidth()/2, 10);
        topPanelLeft.setSize(frame.getWidth()/2, 10);

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
        // nomi dei due player
        // dimensioni
        // tipo di game

        JPanel container = new JPanel(new GridBagLayout());
        frame.add(container, BorderLayout.CENTER);
        JPanel namePlayersContainer = new JPanel(new GridLayout(2, 2, 5, 10));
        container.add(namePlayersContainer);
        JLabel player1 = new JLabel("Name Player 1:");
        JTextField namePlayer1 = new JTextField();
        JLabel player2 = new JLabel("Name Player 2:");
        JTextField namePlayer2 = new JTextField();
        namePlayersContainer.add(player1);
        namePlayersContainer.add(namePlayer1);
        namePlayersContainer.add(player2);
        namePlayersContainer.add(namePlayer2);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //center of the screen
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
