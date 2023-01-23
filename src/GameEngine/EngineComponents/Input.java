package GameEngine.EngineComponents;

import GameEngine.Point;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
    private final int NUMBER_OF_KEYBOARD_INPUTS = 256;
    public boolean[] currentKeyboardInputs = new boolean[NUMBER_OF_KEYBOARD_INPUTS];
    public boolean[] previousKeyboardInputs = new boolean[NUMBER_OF_KEYBOARD_INPUTS];

    private final int NUMBER_OF_MOUSE_INPUTS = 5;
    public boolean[] currentMouseInputs = new boolean[NUMBER_OF_MOUSE_INPUTS];
    public boolean[] previousMouseInputs = new boolean[NUMBER_OF_MOUSE_INPUTS];

    public int mouseX, mouseY;

    public Engine engine;

    public Input(Engine engine){
        this.engine = engine;
        mouseX = 0;
        mouseY = 0;

        engine.window.canvas.addKeyListener(this);
        engine.window.canvas.addMouseListener(this);
        engine.window.canvas.addMouseMotionListener(this);
    }

    public void update(){
        System.arraycopy(currentKeyboardInputs, 0, previousKeyboardInputs, 0, currentKeyboardInputs.length);
        System.arraycopy(currentMouseInputs, 0, previousMouseInputs, 0, currentMouseInputs.length);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentKeyboardInputs[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentKeyboardInputs[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentMouseInputs[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentMouseInputs[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = (int) (e.getX() / engine.SCALE);
        mouseY = (int) (e.getY() / engine.SCALE);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int) (e.getX() / engine.SCALE);
        mouseY = (int) (e.getY() / engine.SCALE);
    }

    public double[] getPlayerWASD() {
        double amtX = 0;
        double amtY = 0;

        if(currentKeyboardInputs[KeyEvent.VK_W])
            amtY -= 1;
        if(currentKeyboardInputs[KeyEvent.VK_A])
            amtX -= 1;
        if(currentKeyboardInputs[KeyEvent.VK_S])
            amtY += 1;
        if(currentKeyboardInputs[KeyEvent.VK_D])
            amtX += 1;

        return new double[] {amtX, amtY};
    }

    public double[] getPlayerArrow() {
        double amtX = 0;
        double amtY = 0;

        if(currentKeyboardInputs[38])
            amtY -= 1;
        if(currentKeyboardInputs[37])
            amtX -= 1;
        if(currentKeyboardInputs[40])
            amtY += 1;
        if(currentKeyboardInputs[39])
            amtX += 1;

        return new double[] {amtX, amtY};
    }

    public Point getMousePoint() {
        return new Point(mouseX, mouseY);
    }
}
