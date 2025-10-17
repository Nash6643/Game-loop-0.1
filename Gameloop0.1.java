public class SimpleGameLoop {
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            // Game logic goes here
            System.out.println("Game running...");

            // Sleep for a short time to avoid flooding the console
            try {
                Thread.sleep(1000); // 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Example condition to stop the loop (optional)
            // running = false;
        }
    }
}
