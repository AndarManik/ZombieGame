package ZombieGameComponents.Scenes;

import GameEngine.Button;
import GameEngine.EngineComponents.Engine;
import ZombieGameComponents.ZombieGame;

import java.util.ArrayList;

public class Menu extends Scene{
    public ArrayList<Button> buttons = new ArrayList<>();
    public Menu(SceneSelector sceneSelector, Engine engine, ZombieGame zombieGame) {
        super(sceneSelector,engine,zombieGame,"src//Sprites//menuBackGround.png");

        buttons.add(startButton());

        buttons.add(levelEditorButton());

    }

    private Button levelEditorButton() {
        return new Button(800, 550,"editor.png", () -> {
            sceneSelector.game.addPlayer();
            sceneSelector.currentScene = Scenes.LEVELEDITOR;
        });
}


    private Button startButton() {
        return new Button(800, 250,"startButton.png", () -> {
            sceneSelector.game.addPlayer();
            sceneSelector.currentScene = Scenes.MAINGAME;
        });
    }


    public void update() {
        if(engine.input.currentMouseInputs[1])
            buttons.forEach((button) ->
                    button.isClicked(engine.input.mouseX, engine.input.mouseY));
    }

    public void render() {
        engine.renderer.drawImage(backGround,0,0);
        buttons.forEach((button) ->
                button.render(engine));
    }
}
