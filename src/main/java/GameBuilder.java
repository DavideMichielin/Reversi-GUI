public class GameBuilder {

    public GameBuilder() {
    }

    public static GUIGameManager create2PlayerGameWithGUI(int dimension, String namePlayer1, String namePlayer2, String gameType) {
        return new GUIGameManager(namePlayer1, namePlayer2, dimension, gameType);
    }
}
