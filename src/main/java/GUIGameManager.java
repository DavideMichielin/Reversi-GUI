import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GUIGameManager extends JFrame {
    private JLabel showPlayer1Name, showPlayer2Name, showPlayer1Disks, showPlayer2Disks;
    public GUIGameManager(String namePlayer1, String namePlayer2, int dimensionBoard, String gameType) {
        showPlayer1Name = new JLabel(namePlayer1);
        showPlayer2Name = new JLabel(namePlayer2);
        showPlayer1Disks = new JLabel("2"); // conversione da int con un metodo che vede il numero di dischi
        showPlayer2Disks = new JLabel("2");
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
        statisticsPanel.setPreferredSize(new Dimension(300, 700));
        statisticsPanel.setBackground(Color.decode("#d2691e"));
        JPanel boardPanel = new JPanel(new GridLayout(dimensionBoard,dimensionBoard)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int sizeCell = 700/dimensionBoard;
                for(int i = 0; i < dimensionBoard; i++){
                    g2d.setStroke(new BasicStroke(3));
                    g2d.drawLine(0, i*sizeCell, 700, i*sizeCell);
                    g2d.drawLine(i*sizeCell, 0, i*sizeCell, 700);
                }

            }
        };
        int a = 0;
        boardPanel.setPreferredSize(new Dimension(700, 700));
        boardPanel.setBackground(Color.decode("#0E6B0E"));
        statisticsPanel.setBorder(BorderFactory.createMatteBorder(0, 30, 0, 30, Color.blue));

        showPlayer1Name.setForeground(Color.BLACK);
        showPlayer1Disks.setForeground(Color.BLACK);
        showPlayer2Name.setForeground(Color.WHITE);
        showPlayer2Disks.setForeground(Color.WHITE);

        statisticsPanel.add(showPlayer1Name);
        statisticsPanel.add(showPlayer1Disks);
        statisticsPanel.add(showPlayer2Name);
        statisticsPanel.add(showPlayer2Disks);

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
