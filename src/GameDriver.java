import GameEngine.EngineComponents.Engine;
import ZombieGameComponents.ZombieGame;

public class GameDriver {
    public static void main(String[] args) {
        Engine engine = new Engine(new ZombieGame());
    }
}
