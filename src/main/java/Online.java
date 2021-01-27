import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Online extends JFrame implements ActionListener {

    private String optionOnline[] = {"Host", "Client"};
    private final JLabel IPAddressThisPC = new JLabel();
    private final JTextField IPAddressHostPC = new JTextField("Insert IP Address");

    public Online() {
        TopPanel topPanel = new TopPanel(this);
        add(topPanel.getTopPanel(), BorderLayout.NORTH);

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        JRadioButton chooseHost = new JRadioButton("Host");
        JRadioButton chooseClient = new JRadioButton("Client");

        ButtonGroup group = new ButtonGroup();
        group.add(chooseHost);
        group.add(chooseClient);

        container.add(chooseHost,c);
        c.gridx = 2;
        container.add(chooseClient,c);

        c.gridx = 0;
        ++c.gridy;
        try{
            String hostIP = InetAddress.getLocalHost().getHostAddress();
            IPAddressThisPC.setText(hostIP);
            container.add(IPAddressThisPC,c);

        }catch(UnknownHostException exception){
            System.out.println("Cannot take own IP address");
        }
        c.gridx = 2;
        container.add(IPAddressHostPC,c);
        ++c.gridy;
        c.gridx = 1;
        JPanel playButtonContainer = new JPanel();
        container.add(playButtonContainer, c);
        JButton playButton = new JButton("PLAY!");
        playButtonContainer.add(playButton);
        playButton.addActionListener(this);

        add(container, BorderLayout.CENTER);

        chooseHost.addActionListener(this);
        chooseClient.addActionListener(this);

        IPAddressHostPC.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                IPAddressHostPC.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                IPAddressHostPC.setText("Insert IP Address");
            }
        });

        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //center of the screen
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton pressedRadioButton = (JRadioButton) e.getSource();
        if(pressedRadioButton.getText().equals("Host")){
            IPAddressThisPC.setEnabled(true);
            IPAddressHostPC.setEnabled(false);
        } else {
            IPAddressHostPC.setEnabled(true);
            IPAddressThisPC.setEnabled(false);
        }
    }
}

