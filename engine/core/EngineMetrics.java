package engine.core;

public class EngineMetrics {
    private long frameCount = 0;
    private double totalRenderTimeMs = 0;
    private double averageFrameTimeMs = 0;

    public void recordFrame(double frameTimeSeconds) {
        frameCount++;
        double frameTimeMs = frameTimeSeconds * 1000.0;
        totalRenderTimeMs += frameTimeMs;
        averageFrameTimeMs = totalRenderTimeMs / frameCount;
    }

    public long getFrameCount() { return frameCount; }
    public double getAverageFrameTimeMs() { return averageFrameTimeMs; }
}