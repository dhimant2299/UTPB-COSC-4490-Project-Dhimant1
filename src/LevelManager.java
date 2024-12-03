import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    public int currentLevel = 1;  
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


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



    private LevelData levelOne() {
        List<Platform> platforms = new ArrayList<>();
        List<Spike> spikes = new ArrayList<>();

        platforms.add(new Platform(100, screenSize.height - 200, 500, 20));
        platforms.add(new Platform(700, screenSize.height - 400, 400, 20));

        spikes.add(new Spike(300, screenSize.height - 2, 20));
        spikes.add(new Spike(500, screenSize.height - 380, 20));

        Goal goal = new Goal(1000, screenSize.height - 500, 30, 90);

        return new LevelData(platforms, spikes, goal);
    }

    public void advanceLevel() {
        currentLevel++;
    }
    

    private LevelData levelTwo() {
        List<Platform> platforms = new ArrayList<>();
        List<Spike> spikes = new ArrayList<>();

        platforms.add(new Platform(200, screenSize.height - 200, 600, 20));
        platforms.add(new Platform(500, screenSize.height - 350, 300, 20));
        platforms.add(new Platform(800, screenSize.height - 500, 150, 20));

        spikes.add(new Spike(150, screenSize.height - 2, 25));
        spikes.add(new Spike(600, screenSize.height - 340, 25));
        //spikes.add(new Spike(850, screenSize.height - 490, 25));

        Goal goal = new Goal(910, screenSize.height - 590, 30, 90);

        return new LevelData(platforms, spikes, goal);
    }


    private LevelData levelThree() {
        List<Platform> platforms = new ArrayList<>();
        List<Spike> spikes = new ArrayList<>();

        platforms.add(new Platform(200, screenSize.height - 200, 600, 20));
        platforms.add(new Platform(400, screenSize.height - 350, 300, 20));
        platforms.add(new Platform(650, screenSize.height - 500, 350, 20));
        platforms.add(new Platform(900, screenSize.height - 650, 200, 20));

        spikes.add(new Spike(100, screenSize.height - 2, 25));
        spikes.add(new Spike(450, screenSize.height - 340, 25));
        spikes.add(new Spike(750, screenSize.height - 490, 25));

        Goal goal = new Goal(1000, screenSize.height - 740, 30, 90);

        return new LevelData(platforms, spikes, goal);
    }

    private LevelData levelFour() {
        List<Platform> platforms = new ArrayList<>();
        List<Spike> spikes = new ArrayList<>();

        spikes.add(new Spike(250, screenSize.height - 2, 20));
        spikes.add(new Spike(300, screenSize.height - 2, 20));
        spikes.add(new Spike(350, screenSize.height - 2, 20));
        spikes.add(new Spike(400, screenSize.height - 2, 20));
        spikes.add(new Spike(800, screenSize.height - 2, 20));
        spikes.add(new Spike(850, screenSize.height - 2, 20));
        spikes.add(new Spike(900, screenSize.height - 2, 20));
        spikes.add(new Spike(950, screenSize.height - 2, 20));
        spikes.add(new Spike(1000, screenSize.height - 2, 20));
        spikes.add(new Spike(1050, screenSize.height - 2, 20));
        spikes.add(new Spike(1100, screenSize.height - 2, 20));
        spikes.add(new Spike(1150, screenSize.height - 2, 20));
        spikes.add(new Spike(1200, screenSize.height - 2, 20));
        spikes.add(new Spike(1250, screenSize.height - 2, 20));
        spikes.add(new Spike(1300, screenSize.height - 2, 20));
        spikes.add(new Spike(1350, screenSize.height - 2, 20));
        spikes.add(new Spike(1400, screenSize.height - 2, 20));
        spikes.add(new Spike(1450, screenSize.height - 2, 20));
        spikes.add(new Spike(1500, screenSize.height - 2, 20));

        platforms.add(new Platform(300, screenSize.height - 200, 40, 20));
        platforms.add(new Platform(500, screenSize.height - 300, 40, 20));
        platforms.add(new Platform(700, screenSize.height - 400, 40, 20));
        platforms.add(new Platform(900, screenSize.height - 500, 40, 20));
        platforms.add(new Platform(1100, screenSize.height - 600, 40, 20));
        platforms.add(new Platform(1350, screenSize.height - 700, 40, 20));

        Goal goal = new Goal(1350, screenSize.height - 200, 60, 20);

        return new LevelData(platforms, spikes, goal);
    }
 
    private LevelData levelFive() {
        List<Platform> platforms = new ArrayList<>();
        List<Spike> spikes = new ArrayList<>();
    
        // Spikes scattered with larger gaps to encourage careful movement
        spikes.add(new Spike(100, screenSize.height - 2, 20));
        spikes.add(new Spike(300, screenSize.height - 2, 20));
        spikes.add(new Spike(600, screenSize.height - 2, 20));
        spikes.add(new Spike(650, screenSize.height - 2, 20));
        spikes.add(new Spike(1200, screenSize.height - 2, 20));
        spikes.add(new Spike(1450, screenSize.height - 2, 20));
        spikes.add(new Spike(1500, screenSize.height - 2, 20));
        spikes.add(new Spike(1800, screenSize.height - 2, 20));
    
        // Platforms with varied lengths and positioning to encourage precision
        platforms.add(new Platform(150, screenSize.height - 200, 150, 20));
        platforms.add(new Platform(500, screenSize.height - 350, 100, 20));  
        platforms.add(new Platform(850, screenSize.height - 200, 70, 20));  
        platforms.add(new Platform(1000, screenSize.height - 500, 50, 20)); 
        platforms.add(new Platform(1150, screenSize.height - 650, 100, 20)); 
        platforms.add(new Platform(1300, screenSize.height - 300, 80, 20)); 
        platforms.add(new Platform(1600, screenSize.height - 400, 120, 20));
    
        // A narrow, difficult platform to require careful movement
        platforms.add(new Platform(1750, screenSize.height - 200, 40, 20));
    
        // A low platform with spikes above it, making players cautious of jumping
        platforms.add(new Platform(700, screenSize.height - 150, 100, 20));
        spikes.add(new Spike(750, screenSize.height - 200, 20)); 
        spikes.add(new Spike(850, screenSize.height - 200, 20)); 
    
        // Multiple small platforms in a staggered upward path near the end
        platforms.add(new Platform(1600, screenSize.height - 650, 80, 20));
        platforms.add(new Platform(1700, screenSize.height - 750, 80, 20));
        platforms.add(new Platform(1800, screenSize.height - 850, 80, 20));
    
        // Placing the goal at the peak, so players need to navigate through the obstacles
        Goal goal = new Goal(1400, screenSize.height - 500, 60, 20);
    
        return new LevelData(platforms, spikes, goal);
    }
    private LevelData levelSix() {
        List<Platform> platforms = new ArrayList<>();
        List<Spike> spikes = new ArrayList<>();
    
        // Spike maze at the bottom to discourage falling
        for (int i = 200; i < screenSize.width - 200; i += 100) {
            spikes.add(new Spike(i, screenSize.height - 2, 20));
        }
    
        // Starting platform, large and safe
        platforms.add(new Platform(100, screenSize.height - 200, 200, 20));
    
        // First section: a series of narrow platforms with spikes between
        platforms.add(new Platform(400, screenSize.height - 400, 80, 20));
        //spikes.add(new Spike(480, screenSize.height - 290, 20));
        platforms.add(new Platform(600, screenSize.height - 400, 80, 20));
        spikes.add(new Spike(680, screenSize.height - 390, 20));
        platforms.add(new Platform(400, screenSize.height - 100, 300, 20));
    
        // Second section: an upward path with alternating platforms
        platforms.add(new Platform(800, screenSize.height - 550, 100, 20));
        platforms.add(new Platform(950, screenSize.height - 650, 60, 20));
        platforms.add(new Platform(1100, screenSize.height - 250, 60, 20));
    
        // Third section: long jump challenges with gaps and spikes below
        platforms.add(new Platform(1250, screenSize.height - 350, 120, 20));
        spikes.add(new Spike(1300, screenSize.height - 520, 20));
        platforms.add(new Platform(1450, screenSize.height - 400, 120, 20));
        spikes.add(new Spike(1500, screenSize.height - 620, 20));
    
        // Final section: small platforms in a stairway to the goal
        platforms.add(new Platform(1500, screenSize.height - 350, 60, 20));
        platforms.add(new Platform(1450, screenSize.height - 550, 60, 20));
        platforms.add(new Platform(1350, screenSize.height - 250, 60, 20));
        spikes.add(new Spike(1300, screenSize.height - 2, 20));
        spikes.add(new Spike(1450, screenSize.height - 2, 20));
        spikes.add(new Spike(1400, screenSize.height - 2, 20));
        spikes.add(new Spike(1500, screenSize.height - 2, 20));
    
        // Goal placed at the peak
        Goal goal = new Goal(1400, screenSize.height - 70, 100, 10);
    
        return new LevelData(platforms, spikes, goal);
    }
    
    
   public LevelData loadLevel() {
    switch (currentLevel) {
        case 1:
            return levelOne();  
        case 2:
            return levelTwo();  
        case 3:
            return levelThree(); 
        case 4:
            return levelFour();
        case 5:
            return levelFive();
        case 6:
            return levelSix();
        default:
            currentLevel = 1;  
            return levelOne();
    }
}


   
    public void nextLevel() {
        currentLevel++;
    }
}
