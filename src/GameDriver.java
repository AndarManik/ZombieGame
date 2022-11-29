import GameEngine.Engine;

public class GameDriver {
    public static void main(String[] args) {
        GameBoard game = new GameBoard();
        Engine engine = new Engine(game);
    }
}
