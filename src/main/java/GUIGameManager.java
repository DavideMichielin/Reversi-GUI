import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUIGameManager extends JFrame implements ActionListener {
    private final JLabel showPlayer1Name, showPlayer2Name, showPlayer1Disks, showPlayer2Disks;
    private final JPanel[][] graphicBoard;
    private final int FRAME_SIZE = 700;
    private final int diskRadius;
    private final boolean[][] diskIsPresent;
    private int numberOfMoves = 0;
    private final ArrayList<Point> points;
    private boolean currentColor; //check color of player
    private JLabel namePlayerTurn;


    public GUIGameManager(String namePlayer1, String namePlayer2, int dimensionBoard, String gameType) {
        graphicBoard = new JPanel[dimensionBoard][dimensionBoard];
        diskIsPresent = new boolean[dimensionBoard][dimensionBoard];
        points = new ArrayList<>();
        diskRadius = (FRAME_SIZE/dimensionBoard)/2;

        showPlayer1Name = new JLabel(namePlayer1, JLabel.CENTER);
        showPlayer2Name = new JLabel(namePlayer2, JLabel.CENTER);
        showPlayer1Disks = new JLabel("0", JLabel.CENTER); // conversione da int con un metodo che vede il numero di dischi
        showPlayer2Disks = new JLabel("0", JLabel.CENTER);

        showPlayer1Name.setFont(new Font("Tahoma", Font.BOLD, 30));
        showPlayer1Disks.setFont(new Font("Tahoma", Font.BOLD, 60));
        showPlayer2Name.setFont(new Font("Tahoma", Font.BOLD, 30));
        showPlayer2Disks.setFont(new Font("Tahoma", Font.BOLD, 60));

        TopPanel topPanel = new TopPanel();
        add(topPanel.getTopPanel(), BorderLayout.NORTH);

        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel statisticsPanel = new JPanel(new BorderLayout());
        statisticsPanel.setPreferredSize(new Dimension(300, FRAME_SIZE));
        statisticsPanel.setBackground(Color.decode("#b0b0b0"));
        statisticsPanel.setBorder(new EmptyBorder(0,25,0,25));
        showPlayer1Name.setForeground(Color.BLACK);
        showPlayer1Disks.setForeground(Color.BLACK);
        showPlayer2Name.setForeground(Color.WHITE);
        showPlayer2Disks.setForeground(Color.WHITE);

        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setMaximumSize(new Dimension(30,10));
        mainMenuButton.addActionListener(this);

        namePlayerTurn = new JLabel(namePlayer1+"'s Turn", JLabel.CENTER);
        namePlayerTurn.setBorder(new EmptyBorder(50, 0, 50, 0));
        namePlayerTurn.setFont(new Font("Tahoma", Font.BOLD, 30));

        statisticsPanel.add(namePlayerTurn, BorderLayout.NORTH);

        JPanel playerStatisticsPanel = new JPanel(new GridLayout(4,1));
        playerStatisticsPanel.setBackground(Color.decode("#b0b0b0"));
        playerStatisticsPanel.add(showPlayer1Name);
        playerStatisticsPanel.add(showPlayer1Disks);
        playerStatisticsPanel.add(showPlayer2Name);
        playerStatisticsPanel.add(showPlayer2Disks);
        statisticsPanel.add(playerStatisticsPanel, BorderLayout.CENTER);
        statisticsPanel.add(mainMenuButton, BorderLayout.SOUTH);
        c.gridy = 0;
        c.gridx = 0;
        container.add(statisticsPanel, c);
        container.setBackground(Color.decode("#b0b0b0"));

        JPanel boardPanel = new JPanel(new BorderLayout());
        JPanel board = createGridPanel(dimensionBoard, this);

        JPanel rowPanel = new JPanel(new GridLayout(dimensionBoard,1));
        rowPanel.setBorder(new EmptyBorder(0,10,0,10));
        rowPanel.setPreferredSize(new Dimension(30 ,FRAME_SIZE));
        rowPanel.setBackground(Color.decode("#b0b0b0"));

        boardPanel.add(board, BorderLayout.CENTER);
        JPanel columPanel = new JPanel(new GridLayout(1, dimensionBoard+1));

        columPanel.setBorder(new EmptyBorder(0, 30, 0,0));
        columPanel.setBackground(Color.decode("#b0b0b0"));
        for(int i = 0; i < dimensionBoard; i++){
            char coloumValue = (char) (i+65);
            columPanel.add(new JLabel(Character.toString(coloumValue), JLabel.CENTER));
            rowPanel.add(new JLabel((i+1)+""));
        }
        boardPanel.add(columPanel, BorderLayout.NORTH);
        boardPanel.add(rowPanel, BorderLayout.WEST);

        ++c.gridx;
        container.add(boardPanel, c);

        add(container);
        setResizable(false);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new Start();
        setVisible(false);

    }

    private JPanel createGridPanel(int dimensionBoard, final JFrame frame) {
        JPanel boardPanel = new JPanel(new GridLayout(dimensionBoard, dimensionBoard));
        for (int indexRow = 0; indexRow < dimensionBoard; indexRow++) {
            for (int indexColumn = 0; indexColumn < dimensionBoard; indexColumn++) {
                graphicBoard[indexRow][indexColumn] = new JPanel() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        for (Point point : points) {
                            if (currentColor) {
                                g2.setColor(Color.BLACK); //print fill oval
                            } else {
                                g2.setColor(Color.WHITE); //print fill oval
                            }
                            g2.fillOval(point.x, point.y, diskRadius, diskRadius);
                        }
                    }
                };
                graphicBoard[indexRow][indexColumn].setBorder(new LineBorder(Color.BLACK, 3));
                graphicBoard[indexRow][indexColumn].setBackground(Color.decode("#0E6B0E"));
                final int indexR = indexRow, indexC = indexColumn;
                graphicBoard[indexRow][indexColumn].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (numberOfMoves < 4) {
                            if ((indexR < dimensionBoard / 2 - 1 || indexR > dimensionBoard / 2) ||
                                    (indexC < dimensionBoard / 2 - 1 || indexC > dimensionBoard / 2)) {
                                JOptionPane.showMessageDialog(frame, "Invalid First Position");
                            } else {
                                doMove(indexR, indexC, dimensionBoard, frame);
                            }
                        } else {
                            doMove(indexR, indexC, dimensionBoard, frame);
                        }
                    }
                });
                boardPanel.add(graphicBoard[indexRow][indexColumn]);
            }
        }
        boardPanel.setPreferredSize(new Dimension(700, 700));
        boardPanel.setBackground(Color.BLACK);

        return boardPanel;
    }


    private void doMove(int indexR, int indexC, int dimensionBoard, final JFrame frame) {
        if (isSetDisk(indexR, indexC)) {
            JOptionPane.showMessageDialog(frame, "Invalid Position");
        } else {
            setCell(indexR, indexC);
            int cellCenter = FRAME_SIZE / dimensionBoard;
            int center = cellCenter / 2 - diskRadius / 2;
            points.add(new Point(center, center));
            graphicBoard[indexR][indexC].repaint();
            if (currentColor) {
                namePlayerTurn.setText(showPlayer1Name.getText()+"'s turn");
                namePlayerTurn.setForeground(Color.BLACK);
            } else {
                namePlayerTurn.setText(showPlayer2Name.getText()+"'s turn");
                namePlayerTurn.setForeground(Color.WHITE);
            }
            numberOfMoves++;
            currentColor = !currentColor;
        }
    }

    public boolean isSetDisk(final int x, final int y) {
        return diskIsPresent[x][y];
    }

    private void setCell(int x, int y) {
        diskIsPresent[x][y] = true;
    }

    public static void main(String[] args) {
        new GUIGameManager("a", "b", 26, "affio");
    }
}
