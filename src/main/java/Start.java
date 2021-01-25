import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Start implements ActionListener {
    private int mouseX, mouseY;
    private final JFrame frame;

    public Start() {
        frame = new JFrame();

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        topPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(frame.getX() + e.getX() - mouseX, frame.getY() + e.getY() - mouseY);
            }
        });
        topPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        frame.add(topPanel, BorderLayout.NORTH);
        topPanel.setBackground(Color.LIGHT_GRAY);
        final JLabel help = new JLabel("?");
        final JLabel exit = new JLabel("X");
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
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
        });
        exit.setBorder(new EmptyBorder(0, 10, 0, 10));
        exit.setSize(10, 5);
        exit.setFont(new Font("Tahoma", Font.BOLD, 15));
        help.setBorder(new EmptyBorder(0, 10, 0, 10));
        help.setSize(10, 5);
        help.setFont(new Font("Tahoma", Font.BOLD, 15));
        topPanel.add(help);
        topPanel.add(exit);


        JPanel container = new JPanel(new GridBagLayout());
        frame.add(container, BorderLayout.CENTER);
        JPanel buttonMenu = new JPanel(new GridLayout(1, 3, 5, 10));
        JButton oneVsOne = new JButton("2 Player Local");
        JButton oneVsCPU = new JButton("vs Computer");
        JButton online = new JButton("Online");
        buttonMenu.add(oneVsOne);
        buttonMenu.add(oneVsCPU);
        buttonMenu.add(online);
        container.add(buttonMenu);
        online.addActionListener(this);
        frame.setLocationRelativeTo(null); //center of the screen
        frame.setSize(500, 300);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setTitle("Othello-Reversi Menu");
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "2 Player Local":
                new OneVsOne();
                frame.setVisible(false);
                break;
            case "vs Computer":
                new OneVsCPU();
                frame.setVisible(false);
                break;
            case "Onlinr":
                new Online();
                frame.setVisible(false);
                break;
            case "?":
                JOptionPane.showMessageDialog(frame, "Descrizione del gioco");
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
