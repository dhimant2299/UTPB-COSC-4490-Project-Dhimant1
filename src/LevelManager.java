import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    public int currentLevel = 1;  // Track the current level
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Inner class to hold level data
    public static class LevelData {
        public List<Platform> platforms;
        public List<Spike> spikes;
        public Goal goal;

        public LevelData(List<Platform> platforms, List<Spike> spikes, Goal goal) {
            this.platforms = platforms;
            this.spikes = spikes;
            this.goal = goal;
        }
    }

    // Level 1 definition
    private LevelData levelOne() {
        List<Platform> platforms = new ArrayList<>();
        List<Spike> spikes = new ArrayList<>();

        platforms.add(new Platform(100, screenSize.height - 200, 500, 20));
        platforms.add(new Platform(700, screenSize.height - 400, 400, 20));

        spikes.add(new Spike(300, screenSize.height - 2, 20));
        spikes.add(new Spike(500, screenSize.height - 380, 20));

        Goal goal = new Goal(1000, screenSize.height - 500, 30, 70);

        return new LevelData(platforms, spikes, goal);
    }

    // Level 2 definition
    private LevelData levelTwo() {
        List<Platform> platforms = new ArrayList<>();
        List<Spike> spikes = new ArrayList<>();

        platforms.add(new Platform(200, screenSize.height - 200, 600, 20));
        platforms.add(new Platform(500, screenSize.height - 350, 300, 20));
        platforms.add(new Platform(800, screenSize.height - 500, 150, 20));

        spikes.add(new Spike(150, screenSize.height - 2, 20));
        spikes.add(new Spike(600, screenSize.height - 320, 20));
        spikes.add(new Spike(850, screenSize.height - 470, 20));

        Goal goal = new Goal(900, screenSize.height - 550, 30, 70);

        return new LevelData(platforms, spikes, goal);
    }

    // Level 3 definition
    private LevelData levelThree() {
        List<Platform> platforms = new ArrayList<>();
        List<Spike> spikes = new ArrayList<>();

        platforms.add(new Platform(200, screenSize.height - 200, 600, 20));
        platforms.add(new Platform(400, screenSize.height - 350, 300, 20));
        platforms.add(new Platform(650, screenSize.height - 500, 150, 20));
        platforms.add(new Platform(900, screenSize.height - 650, 200, 20));

        spikes.add(new Spike(100, screenSize.height - 2, 20));
        spikes.add(new Spike(450, screenSize.height - 320, 20));
        spikes.add(new Spike(750, screenSize.height - 470, 20));

        Goal goal = new Goal(1000, screenSize.height - 700, 30, 70);

        return new LevelData(platforms, spikes, goal);
    }

    // Load level based on the current level index
    public LevelData loadLevel() {
        switch (currentLevel) {
            case 1:
                return levelOne();
            case 2:
                return levelTwo();
            case 3:
                return levelThree();
            default:
                currentLevel = 1;  // Loop back to level 1 after the last level
                return levelOne();
        }
    }

    // Advance to the next level
    public void nextLevel() {
        currentLevel++;
    }
}
