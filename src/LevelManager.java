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



    private LevelData levelFour() {
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
        spikes.add(new Spike(850, screenSize.height - 490, 25));

        Goal goal = new Goal(910, screenSize.height - 590, 30, 90);

        return new LevelData(platforms, spikes, goal);
    }


    private LevelData levelThree() {
        List<Platform> platforms = new ArrayList<>();
        List<Spike> spikes = new ArrayList<>();

        platforms.add(new Platform(200, screenSize.height - 200, 600, 20));
        platforms.add(new Platform(400, screenSize.height - 350, 300, 20));
        platforms.add(new Platform(650, screenSize.height - 500, 150, 20));
        platforms.add(new Platform(900, screenSize.height - 650, 200, 20));

        spikes.add(new Spike(100, screenSize.height - 2, 25));
        spikes.add(new Spike(450, screenSize.height - 340, 25));
        spikes.add(new Spike(750, screenSize.height - 490, 25));

        Goal goal = new Goal(1000, screenSize.height - 740, 30, 90);

        return new LevelData(platforms, spikes, goal);
    }

    private LevelData levelOne() {
        List<Platform> platforms = new ArrayList<>();
        List<Spike> spikes = new ArrayList<>();

    spikes.add(new Spike(60, screenSize.height - 2, 20));
        spikes.add(new Spike(200, screenSize.height - 2, 20));
        spikes.add(new Spike(350, screenSize.height - 200, 20));
        spikes.add(new Spike(500, screenSize.height - 300, 20));
        spikes.add(new Spike(700, screenSize.height - 400, 20));
        spikes.add(new Spike(950, screenSize.height - 500, 20));
        spikes.add(new Spike(1000, screenSize.height - 2, 20));
        spikes.add(new Spike(1400, screenSize.height - 2, 20));
        //spikes.add(new Spike(1400, screenSize.height - 700, 15));
        spikes.add(new Spike(1350, screenSize.height - 2, 20));
        spikes.add(new Spike(1400, screenSize.height - 2, 20));
        spikes.add(new Spike(1450, screenSize.height - 2, 20));
        spikes.add(new Spike(1500, screenSize.height - 2, 20));

        platforms.add(new Platform(300, screenSize.height - 200, 700, 20));
        platforms.add(new Platform(500, screenSize.height - 300, 450, 20));
        platforms.add(new Platform(700, screenSize.height - 400, 400, 20));
        platforms.add(new Platform(900, screenSize.height - 500, 500, 20));
        platforms.add(new Platform(1250, screenSize.height - 600, 50, 20));
        platforms.add(new Platform(1350, screenSize.height - 700, 800, 20));





        Goal goal = new Goal(1000, screenSize.height - 740, 30, 90);

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
        default:
            currentLevel = 1;  
            return levelOne();
    }
}


   
    public void nextLevel() {
        currentLevel++;
    }
}
