import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Online extends JFrame implements ActionListener {

    private String optionOnline[] = {"Host", "Client"};

    public Online() {
        TopPanel topPanel = new TopPanel(this);
        add(topPanel.getTopPanel(), BorderLayout.NORTH);

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        JRadioButton chooseHost = new JRadioButton("Host");
        chooseHost.setSelected(true);
        JRadioButton chooseClient = new JRadioButton("Client");

        ButtonGroup group = new ButtonGroup();
        group.add(chooseHost);
        group.add(chooseClient);

        container.add(chooseHost,c);
        c.gridx = 2;
        container.add(chooseClient,c);

        c.gridx = 0;
        ++c.gridy;
        String hostIP = "";
        try{
            hostIP = InetAddress.getLocalHost().getHostAddress();
        }catch(UnknownHostException exception){
            System.out.println("Cannot take own IP address");
        }
        container.add(new JLabel("ciao"),c);
        c.gridx = 2;
        container.add(new JTextField("Insert IP Address"),c);

        ++c.gridy;
        c.gridx = 1;
        JPanel playButtonContainer = new JPanel();
        container.add(playButtonContainer, c);
        JButton playButton = new JButton("PLAY!");
        playButtonContainer.add(playButton);
        playButton.addActionListener(this);

        add(container, BorderLayout.CENTER);

        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //center of the screen
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    //Radius Host - Client

    //Se Host mostra label con il proprio IP

    //Se client mostra JTextField dove inserire l'ip
}

