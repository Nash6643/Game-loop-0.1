package engine.audio;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;

public class SoundManager {
    private final Map<String, Clip> soundClips = new HashMap<>();
    private boolean soundEnabled = true;

    public void loadSound(String name, String filePath) {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) return;
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            soundClips.put(name, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Failed to load audio clip: " + filePath);
        }
    }

    public void playSound(String name) {
        if (!soundEnabled) return;
        Clip clip = soundClips.get(name);
        if (clip != null) {
            if (clip.isRunning()) clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void setSoundEnabled(boolean enabled) { this.soundEnabled = enabled; }
    public boolean isSoundEnabled() { return soundEnabled; }
}