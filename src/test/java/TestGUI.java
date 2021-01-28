import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestGUI {

    @Test
    public void cell1AIsEmptyAtBoardOfDimension8x8Initialization() {
        GUIGameManager game = new GUIGameManager("Bob", "Alice", 8, "Othello");
        assertFalse(game.isSetDisk(0,0));
    }
    @ParameterizedTest
    @CsvSource({"8,0,0", "26,1,1", "4, 0, 0"})
    public void cellIsEmptyAtBoardOfDimension(int DimensionBoard, int indexR, int indexC){
        GUIGameManager game = new GUIGameManager("Bob", "Alice", DimensionBoard, "Othello");
        assertFalse(game.isSetDisk(indexC,indexR));
    }

}
