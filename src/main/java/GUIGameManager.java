import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUIGameManager extends JFrame {
    private final JLabel showPlayer1Name, showPlayer2Name, showPlayer1Disks, showPlayer2Disks;
    private final JPanel[][] graphicBoard;
    private final int FRAME_SIZE = 700;
    private final int diskRadius;
    private final boolean[][] diskIsPresent;
    private int numberOfMoves = 0;
    private final ArrayList<Point> points;
    private boolean currentColor; //check color of player


    public GUIGameManager(String namePlayer1, String namePlayer2, int dimensionBoard, String gameType) {
        graphicBoard = new JPanel[dimensionBoard][dimensionBoard];
        diskIsPresent = new boolean[dimensionBoard][dimensionBoard];
        points = new ArrayList<>();
        diskRadius = (FRAME_SIZE/dimensionBoard)/2;

        showPlayer1Name = new JLabel(namePlayer1);
        showPlayer2Name = new JLabel(namePlayer2);
        showPlayer1Disks = new JLabel("2"); // conversione da int con un metodo che vede il numero di dischi
        showPlayer2Disks = new JLabel("2");

        TopPanel topPanel = new TopPanel(this);
        add(topPanel.getTopPanel(), BorderLayout.NORTH);

        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel statisticsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        statisticsPanel.setPreferredSize(new Dimension(300, FRAME_SIZE));
        statisticsPanel.setBackground(Color.decode("#d2691e"));
        statisticsPanel.setBorder(new EmptyBorder(0,25,0,25));
        showPlayer1Name.setForeground(Color.BLACK);
        showPlayer1Disks.setForeground(Color.BLACK);
        showPlayer2Name.setForeground(Color.WHITE);
        showPlayer2Disks.setForeground(Color.WHITE);

        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setMaximumSize(new Dimension(30,10));

        c1.gridx = c1.gridy = 0;
        statisticsPanel.add(showPlayer1Name,c1);
        c1.gridx = 2;
        statisticsPanel.add(showPlayer1Disks,c1);
        c1.gridy = 5;
        c1.gridx = 0;
        statisticsPanel.add(showPlayer2Name, c1);
        c1.gridx = 2;
        statisticsPanel.add(showPlayer2Disks, c1);
        c1.gridy = 10;
        c1.gridx = 1;
        c1.weightx = 2;
        statisticsPanel.add(mainMenuButton, c1);
        c.gridy = 0;
        c.gridx = 0;
        container.add(statisticsPanel, c);

        JPanel boardPanel = createGridPanel(dimensionBoard, this);
        ++c.gridx;
        container.add(boardPanel, c);
        add(container);
        setResizable(false);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
        boardPanel.setPreferredSize(new Dimension(FRAME_SIZE, FRAME_SIZE));
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

}
