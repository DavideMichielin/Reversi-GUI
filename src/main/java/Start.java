import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Start extends JFrame {
    
    public Start() {
        
        new DraggableFrame(this);
        TopPanel topPanel = new TopPanel();
        add(topPanel.getTopPanel(), BorderLayout.NORTH);

        JPanel container = new JPanel(new GridBagLayout());
        add(container, BorderLayout.CENTER);
        JPanel buttonMenu = new JPanel(new GridLayout(1, 3, 5, 10));
        JButton oneVsOne = new JButton("1vs1");
        JButton oneVsCPU = new JButton("vsComputer");
        JButton online = new JButton("Online");
        buttonMenu.add(oneVsOne);
        buttonMenu.add(oneVsCPU);
        buttonMenu.add(online);
        container.add(buttonMenu);
        oneVsOne.addActionListener(e -> {
            new OneVsOne();
            setVisible(false);
        });
        oneVsCPU.addActionListener(e -> {
            new OneVsCPU();
            setVisible(false);
        });
        online.addActionListener(e -> {
            new Online();
            setVisible(false);
        });
        MouseAdapter ma = new MouseAdapter() {

        };

        setSize(500, 300);
        setLocationRelativeTo(null); //center of the screen
        setUndecorated(true);
        setResizable(false);
        setTitle("Othello-Reversi Menu");
        setVisible(true);
    }

    /*public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "1vs1":
                new OneVsOne();
                setVisible(false);
                break;
            case "vsComputer":
                new OneVsCPU();
                setVisible(false);
                break;
            case "Online":
                new Online();
                setVisible(false);
                break;
            default:
        }
    }*/

    public static void main(String[] args) {
        new Start();
    }
}
