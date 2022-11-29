package GameEngine;

public class Engine implements Runnable {

    public final long UPDATE_INTERVAL = (long) (Math.pow(10, 9) / 60);
    public final int WIDTH = 1600;
    public final int HEIGHT = 900;
    public final double SCALE = 1;

    public Thread thread;
    public Window window;

    public Renderer renderer;
    public AbstractGame game;
    public boolean isRunning = false;

    public Engine(AbstractGame game) {
        thread = new Thread(this);
        window = new Window(this);
        renderer = new Renderer(this);
        this.game = game;
        thread.start();
    }




    @Override
    public void run() {
        isRunning = true;

        long startTime = System.nanoTime();
        long stopTime;

        boolean needsRendering = false;


        while(isRunning) {

            stopTime = System.nanoTime() - startTime;

            while(stopTime > UPDATE_INTERVAL) {
                needsRendering = true;
                game.update(this);
                stopTime -= UPDATE_INTERVAL;
                startTime = System.nanoTime();
            }

            if(needsRendering) {
                game.render(renderer.renderData);
                window.show();
                needsRendering = false;
            }
        }
    }
}
