import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GUIGameManager extends JFrame {

    public GUIGameManager(String namePlayer1, String namePlayer2, int dimensionBoard, String gameType) {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        add(topPanel, BorderLayout.NORTH);
        topPanel.setBackground(Color.LIGHT_GRAY);
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
        exit.setBorder(new EmptyBorder(0, 10, 0, 10));
        exit.setSize(10, 5);
        exit.setFont(new Font("Tahoma", Font.BOLD, 15));
        help.setBorder(new EmptyBorder(0, 10, 0, 10));
        help.setSize(10, 5);
        help.setFont(new Font("Tahoma", Font.BOLD, 15));
        topPanel.add(help);
        topPanel.add(exit);

        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel statisticsPanel = new JPanel(new GridLayout(3,2));
        statisticsPanel.setMinimumSize(new Dimension(300, 700));
        statisticsPanel.setMaximumSize(new Dimension(300, 700));
        statisticsPanel.setPreferredSize(new Dimension(300, 700));
        JPanel boardPanel = new JPanel(new GridLayout(dimensionBoard,dimensionBoard));
        boardPanel.setMinimumSize(new Dimension(700, 700));
        boardPanel.setMaximumSize(new Dimension(700, 700));
        boardPanel.setPreferredSize(new Dimension(700, 700));
        statisticsPanel.setBackground(Color.RED);
        boardPanel.setBackground(Color.decode("#0E6B0E"));

        c.gridy = 0;
        c.gridx = 0;
        container.add(statisticsPanel,c);
        ++c.gridx;
        container.add(boardPanel, c);
        add(container);
        setResizable(false);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
