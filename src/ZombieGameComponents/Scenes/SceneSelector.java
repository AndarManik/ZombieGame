package ZombieGameComponents.Scenes;

import GameEngine.EngineComponents.Engine;
import ZombieGameComponents.ZombieGame;

public class SceneSelector {
    public Scenes currentScene;
    MainGame game;
    Menu menu;
    LevelEditor editor;
    Settings settings;
    public SceneSelector(Engine engine, ZombieGame zombieGame) {
        currentScene = Scenes.MAINMENU;
        game = new MainGame(this,engine, zombieGame);
        menu = new Menu(this,engine, zombieGame);
        editor = new LevelEditor(this,engine, zombieGame);
        settings = new Settings(this, engine, zombieGame);
    }
    public void update(){
        switch (currentScene) {
            case MAINGAME -> game.update();
            case MAINMENU -> menu.update();
            case LEVELEDITOR -> editor.update();
            case SETTINGS -> settings.update();
        }
    }
    public void render() {
        switch(currentScene) {
            case MAINGAME -> game.render();
            case MAINMENU -> menu.render();
            case LEVELEDITOR -> editor.render();
            case SETTINGS -> settings.render();
        }
    }
}
