import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestGUI {
    @ParameterizedTest
    @CsvSource({"8,0,0", "26,1,1", "4, 0, 0"})
    public void cellIsEmptyAtBoardOfDimension(int dimensionBoard, int indexR, int indexC){
        GUIGameManager game = new GUIGameManager("Bob", "Alice", dimensionBoard, "Othello");
        assertFalse(game.isSetDisk(indexC,indexR));
    }
    @ParameterizedTest
    @CsvSource({"8,3,3", "8,4,3", "8,3,4", "8,3,4"})
    public void firstValidPosition(int dimensionBoard, int indexR, int indexC){
        GUIGameManager game = new GUIGameManager("Bob", "Alice", dimensionBoard, "Othello");
        game.isValidMove(indexR, indexC, dimensionBoard, game);
        assertTrue(game.isSetDisk(indexR, indexC));
    }
    @ParameterizedTest
    @CsvSource({"8,5,3", "8,1,3", "8,3,6", "8,3,2"})
    public void firstInvalidPosition(int dimensionBoard, int indexR, int indexC){
        GUIGameManager game = new GUIGameManager("Bob", "Alice", dimensionBoard, "Othello");
        game.isValidMove(indexR, indexC, dimensionBoard, game);
        assertFalse(game.isSetDisk(indexR, indexC));
    }
}
