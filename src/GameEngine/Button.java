package GameEngine;

import GameEngine.EngineComponents.Engine;
import GameEngine.EngineComponents.Sprite;

public class Button {
    Point position;
    double height, width;
    Runnable onClickAction;
    Sprite sprite;

    public Button(double positionX, double positionY, String spriteFile, Runnable onClickAction) {
        position = new Point(positionX, positionY);

        this.onClickAction = onClickAction;
        sprite = new Sprite(("src//Sprites//" + spriteFile));
        this.height = sprite.height;
        this.width = sprite.width;    }

    public void isClicked(double x, double y) {
        if(     x >= position.x - width / 2 &&
                x <= position.x + width / 2 &&
                y >= position.y - height / 2 &&
                y <= position.y + height / 2)
            onClickAction.run();
    }

    public void render(Engine engine) {
        engine.renderer.drawImage(sprite, (int) (position.x - width / 2), (int) (position.y - height / 2));

    }

}
