import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TopPanel {
    private final JPanel topPanel;
    public TopPanel(){
        topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
                exit.setOpaque(true);
                exit.setBackground(Color.RED);
                exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exit.setBackground(Color.LIGHT_GRAY);
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
                } catch (URISyntaxException | IOException ex) {
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
    }

    public TopPanel( final JFrame frame){
        topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(Color.LIGHT_GRAY);
        JPanel topPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel topPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));

        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.add(topPanelLeft);
        topPanel.add(topPanelRight);

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
                exit.setOpaque(true);
                exit.setBackground(Color.RED);
                exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exit.setBackground(Color.LIGHT_GRAY);
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
                } catch (URISyntaxException | IOException ex) {
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
    }

    public JPanel getTopPanel() {
        return topPanel;
    }
}
