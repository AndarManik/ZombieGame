package GameEngine.EngineComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private JFrame frame;
    public BufferedImage image;
    public Canvas canvas;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    public Window(Engine engine) {
            image = new BufferedImage(engine.WIDTH, engine.HEIGHT, BufferedImage.TYPE_INT_RGB);

            canvas = new Canvas();
            Dimension dim = new Dimension((int) (engine.WIDTH * engine.SCALE), (int) (engine.HEIGHT * engine.SCALE));
            canvas.setPreferredSize(dim);
            canvas.setMinimumSize(dim);
            canvas.setMaximumSize(dim);

            frame = new JFrame("ZombieGameComponents.ZombieGame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(canvas, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);

            canvas.createBufferStrategy(2);
            bufferStrategy = canvas.getBufferStrategy();
            graphics = bufferStrategy.getDrawGraphics();
    }

    public void render(){
        graphics.drawImage(image, 0,0, canvas.getWidth(), canvas.getHeight(), null);
        bufferStrategy.show();
    }
}
