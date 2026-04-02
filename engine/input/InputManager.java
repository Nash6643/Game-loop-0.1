package engine.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputManager extends KeyAdapter {
    private final boolean[] keys = new boolean[256];

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < keys.length) keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < keys.length) keys[e.getKeyCode()] = false;
    }

    public boolean isKeyPressed(int keyCode) {
        return keyCode < keys.length && keys[keyCode];
    }
}