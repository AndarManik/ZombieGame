import GameEngine.AbstractGame;
import GameEngine.Engine;

public class GameBoard implements AbstractGame {
    @Override
    public void update(Engine engine) {

    }

    @Override
    public void render(int[] imageData) {
        for (int i = 0; i < imageData.length; i++) {
            if(i % 7 == 1)
                imageData[i] -= 3 * (i + 1);
            else {
                if (i % 3 == 2)
                    imageData[i] += 2 * (i + 1);
                else
                    imageData[i] -= (i + 1);
            }
        }
    }
}
