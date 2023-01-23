package GameEngine.EngineComponents;

public class Engine implements Runnable {

    public final long UPDATE_INTERVAL = (long) (Math.pow(10, 9) / 60);
    public final int WIDTH = 1600;
    public final int HEIGHT = 900;
    public final double SCALE = 1;

    public double tickCounter = 0;

    public Thread thread;
    public Window window;
    public Renderer renderer;
    public Input input;
    public AbstractGame game;
    public boolean isRunning = false;

    public Engine(AbstractGame game) {
        thread = new Thread(this);
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);

        this.game = game;
        thread.start();
    }

    @Override
    public void run() {
        game.initialize(this);
        isRunning = true;

        long startTime = System.nanoTime();
        long stopTime;

        boolean needsRendering = false;


        while(isRunning) {

            stopTime = System.nanoTime() - startTime;

            while(stopTime > UPDATE_INTERVAL) {
                needsRendering = true;
                game.update();
                input.update();
                stopTime -= UPDATE_INTERVAL;
                startTime = System.nanoTime();
                tickCounter++;
            }

            if(needsRendering) {
                game.render();
                window.render();
                needsRendering = false;
            }
        }
    }
}
