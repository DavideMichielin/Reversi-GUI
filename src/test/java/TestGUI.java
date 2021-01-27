import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class TestGUI {

    @Test
    // sto test non mi convince troppo, dovremo vedere se riusciamo a introdurre le cose fatte da Giorgia e Dodo
    public void cell1AIsEmptyAtBoardOfDimension8x8Initialization() {
        GUIGameManager game = new GUIGameManager("Bob", "Alice", 8, "Othello");
        assertFalse(game.isSetDisk(0,0));
    }
}
