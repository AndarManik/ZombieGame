package GameEngine.EngineComponents;

import GameEngine.EngineComponents.Engine;

public interface AbstractGame {
    void initialize(Engine engine);

    void update();
    void render();


}
