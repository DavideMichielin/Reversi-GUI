import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Start implements ActionListener {
    private final JFrame frame;

    public Start() {
        frame = new JFrame();

        TopPanel topPanel = new TopPanel();
        frame.add(topPanel.getTopPanel(), BorderLayout.NORTH);

        JPanel container = new JPanel(new GridBagLayout());
        frame.add(container, BorderLayout.CENTER);
        JPanel buttonMenu = new JPanel(new GridLayout(1, 3, 5, 10));
        JButton oneVsOne = new JButton("1vs1");
        JButton oneVsCPU = new JButton("vsComputer");
        JButton online = new JButton("Online");
        buttonMenu.add(oneVsOne);
        buttonMenu.add(oneVsCPU);
        buttonMenu.add(online);
        container.add(buttonMenu);
        oneVsOne.addActionListener(this);
        oneVsCPU.addActionListener(this);
        online.addActionListener(this);

        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null); //center of the screen
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setTitle("Othello-Reversi Menu");
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "1vs1":
                new OneVsOne();
                frame.setVisible(false);
                break;
            case "vsComputer":
                new OneVsCPU();
                frame.setVisible(false);
                break;
            case "Online":
                new Online();
                frame.setVisible(false);
                break;
            default:
        }
    }

    public static void main(String[] args) {
        new Start();
    }
}
