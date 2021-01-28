import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Online {

    private String optionOnline[] = {"Host", "Client"};
    private final JLabel IPAddressThisPC = new JLabel();
    private final JTextField IPAddressHostPC = new JTextField("Insert IP Address");
    private final ButtonGroup group;
    private final JFrame frame = new JFrame();

    public Online() {
        TopPanel topPanel = new TopPanel(frame);
        frame.add(topPanel.getTopPanel(), BorderLayout.NORTH);

        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        JRadioButton chooseHost = new JRadioButton("Host");
        chooseHost.setActionCommand("host");
        JRadioButton chooseClient = new JRadioButton("Client");
        chooseClient.setActionCommand("client");
        group = new ButtonGroup();
        group.add(chooseHost);
        group.add(chooseClient);

        container.add(chooseHost, c);
        c.gridx = 2;
        container.add(chooseClient, c);

        c.gridx = 0;
        ++c.gridy;
        try {
            String hostIP = InetAddress.getLocalHost().getHostAddress();
            IPAddressThisPC.setText(hostIP);
            container.add(IPAddressThisPC, c);

        } catch (UnknownHostException exception) {
            System.out.println("Cannot take own IP address");
        }
        c.gridx = 2;
        container.add(IPAddressHostPC, c);
        ++c.gridy;
        c.gridx = 1;
        JPanel playButtonContainer = new JPanel();
        container.add(playButtonContainer, c);
        JButton playButton = new JButton("PLAY!");
        playButtonContainer.add(playButton);
        frame.add(container, BorderLayout.CENTER);

        chooseHost.addActionListener(manageWhichElementMustBeEnabled);
        chooseClient.addActionListener(manageWhichElementMustBeEnabled);
        playButton.addActionListener(controlRegularityOfSelectionAndPlay);


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

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //center of the screen
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private final ActionListener manageWhichElementMustBeEnabled = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton pressedRadioButton = (JRadioButton) e.getSource();
            if (pressedRadioButton.getText().equals("Host")) {
                IPAddressThisPC.setEnabled(true);
                IPAddressHostPC.setEnabled(false);
            } else {
                IPAddressHostPC.setEnabled(true);
                IPAddressThisPC.setEnabled(false);
            }
        }
    };

    private final ActionListener controlRegularityOfSelectionAndPlay = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (group.getSelection() == null) {
                JOptionPane.showMessageDialog(frame, "Please, make a selection",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else if (group.getSelection().getActionCommand().equals("client")
                    && !isIp(IPAddressHostPC.getText())) {
                JOptionPane.showMessageDialog(frame, "Please, write a valid IP number",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
            }
        }
    };

    public static boolean isIp(String string) {
        String[] parts = string.split("\\.", -1);
        return parts.length == 4 // 4 parts
                && Arrays.stream(parts)
                .map(Integer::parseInt)
                .filter(i -> i <= 255 && i >= 0) // Must be inside [0, 255]
                .count() == 4; // 4 numerical parts inside [0, 255]
    }
}

