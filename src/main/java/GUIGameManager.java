import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUIGameManager extends JFrame {
    private JLabel showPlayer1Name, showPlayer2Name, showPlayer1Disks, showPlayer2Disks;
    private final JPanel[][] graphicBoard;
    private final int FRAME_SIZE = 700;
    private final int DISK_RADIUS = 50;
    private boolean[][] diskIsPresent;
    private int numberOfMoves = 0;
    private ArrayList<Point> points;


    public GUIGameManager(String namePlayer1, String namePlayer2, int dimensionBoard, String gameType) {
        graphicBoard = new JPanel[dimensionBoard][dimensionBoard];
        diskIsPresent = new boolean[dimensionBoard][dimensionBoard];
        points = new ArrayList<Point>();

        showPlayer1Name = new JLabel(namePlayer1);
        showPlayer2Name = new JLabel(namePlayer2);
        showPlayer1Disks = new JLabel("2"); // conversione da int con un metodo che vede il numero di dischi
        showPlayer2Disks = new JLabel("2");

        TopPanel topPanel = new TopPanel();
        add(topPanel.getTopPanel(), BorderLayout.NORTH);

        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel statisticsPanel = new JPanel(new GridLayout(3,2));
        statisticsPanel.setPreferredSize(new Dimension(300, FRAME_SIZE));
        statisticsPanel.setBackground(Color.decode("#d2691e"));
        statisticsPanel.setBorder(BorderFactory.createMatteBorder(0, 30, 0, 30, Color.blue));

        JPanel boardPanel = createGridPanel(dimensionBoard, this);

        showPlayer1Name.setForeground(Color.BLACK);
        showPlayer1Disks.setForeground(Color.BLACK);
        showPlayer2Name.setForeground(Color.WHITE);
        showPlayer2Disks.setForeground(Color.WHITE);

        statisticsPanel.add(showPlayer1Name);
        statisticsPanel.add(showPlayer1Disks);
        statisticsPanel.add(showPlayer2Name);
        statisticsPanel.add(showPlayer2Disks);

        c.gridy = 0;
        c.gridx = 0;
        container.add(statisticsPanel,c);
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
        JPanel boardPanel = new JPanel(new GridLayout(dimensionBoard,dimensionBoard));
        for(int indexRow = 0; indexRow < dimensionBoard; indexRow++ ){
            for(int indexColumn = 0; indexColumn < dimensionBoard; indexColumn++){
                graphicBoard[indexRow][indexColumn] = new JPanel(){
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        for (Point point : points) {
                            g2.setColor(Color.BLACK); //print fill oval
                            g2.fillOval(point.x, point.y, DISK_RADIUS, DISK_RADIUS);
                            g2.setColor(Color.WHITE); //print border
                            g2.drawOval(point.x, point.y, DISK_RADIUS, DISK_RADIUS);
                        }
                    }
                };
                graphicBoard[indexRow][indexColumn].setBorder(new LineBorder(Color.BLACK,3));
                graphicBoard[indexRow][indexColumn].setBackground(Color.decode("#0E6B0E"));
                final int indexR = indexRow, indexC = indexColumn;
                graphicBoard[indexRow][indexColumn].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(numberOfMoves < 4){
                            if((indexR < dimensionBoard / 2 - 1 ||  indexR > dimensionBoard / 2) ||
                                    (indexC < dimensionBoard / 2 - 1 ||  indexC > dimensionBoard / 2)){
                                JOptionPane.showMessageDialog(frame, "Invalid First Position");
                            }else{
                                if(isSetDisk(indexR, indexC)){
                                    JOptionPane.showMessageDialog(frame, "Invalid Position");
                                }else{
                                    setCell(indexR, indexC);
                                    int cellCenter = FRAME_SIZE/dimensionBoard;
                                    int center = cellCenter/2 - DISK_RADIUS/2;
                                    points.add(new Point(center, center));
                                    graphicBoard[indexR][indexC].repaint();
                                    numberOfMoves++;
                                }
                            }
                        }else{
                            if(isSetDisk(indexR, indexC)){
                                JOptionPane.showMessageDialog(frame, "Invalid Position");
                            }else{
                                setCell(indexR, indexC);
                                int cellCenter = FRAME_SIZE/dimensionBoard;
                                int center = cellCenter/2 - DISK_RADIUS/2;
                                points.add(new Point(center, center));
                                graphicBoard[indexR][indexC].repaint();
                                numberOfMoves++;
                            }
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


    private boolean isSetDisk(final int x, final int y) {
        return diskIsPresent[x][y];
    }

    private void setCell(int x, int y) {
        diskIsPresent[x][y] = true;
    }

}
