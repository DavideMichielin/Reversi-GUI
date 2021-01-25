import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Play extends JFrame implements ActionListener {
    public Play() {
        setLocationRelativeTo(null);
        setSize(300,100);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Play!");
        JPanel container = new JPanel(new GridBagLayout());
        add(container, BorderLayout.CENTER);
        JPanel containerTitle = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Select game mode:");
        containerTitle.setBorder(new EmptyBorder(10,100,10,10));
        containerTitle.add(title);
        add(containerTitle, BorderLayout.NORTH);
        JPanel buttonMenu = new JPanel(new GridLayout(2,3,5,10));
        JButton oneVsOne = new JButton("1v1");
        JButton oneVsComputer = new JButton("CPU");
        JButton online = new JButton("Online");
        buttonMenu.add(oneVsOne);
        buttonMenu.add(oneVsComputer);
        buttonMenu.add(online);
        container.add(buttonMenu);
        oneVsOne.addActionListener(this);
        oneVsComputer.addActionListener(this);
        online.addActionListener(this);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
            case "1v1":
                OneVsOne ovo = new OneVsOne();
                setVisible(false);
                break;
            case "CPU":
                OneVsCPU ovc = new OneVsCPU();
                setVisible(false);
                break;
            case "Online":
                Online onl = new Online();
                break;
            default:
        }
    }
}