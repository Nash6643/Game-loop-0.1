package engine.config;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class EngineConfig {
    private static final String CONFIG_FILE = "engine.properties";
    private final Properties properties = new Properties();

    public EngineConfig() {
        loadDefaults();
        loadFromFile();
    }

    private void loadDefaults() {
        properties.setProperty("targetFps", "60");
        properties.setProperty("screenWidth", "800");
        properties.setProperty("screenHeight", "600");
        properties.setProperty("debugMode", "true");
        properties.setProperty("enableParticles", "true");
    }

    public void loadFromFile() {
        if (!Files.exists(Paths.get(CONFIG_FILE))) return;
        try (InputStream in = Files.newInputStream(Paths.get(CONFIG_FILE))) {
            properties.load(in);
        } catch (Exception e) {
            System.err.println("Could not load config file, using defaults.");
        }
    }

    public void saveToFile() {
        try (OutputStream out = Files.newOutputStream(Paths.get(CONFIG_FILE))) {
            properties.store(out, "Java 2D Game Engine Configuration");
        } catch (Exception e) {
            System.err.println("Could not save config file.");
        }
    }

    public int getInt(String key, int defaultValue) {
        return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(properties.getProperty(key, String.valueOf(defaultValue)));
    }
}