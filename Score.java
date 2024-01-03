package taquin;

import java.io.Serializable;
import java.time.Duration;

public class Score implements Serializable {

    private int nbCoups;
    private Duration duree;

    public Score(int nbCoups, Duration duree) {
        this.nbCoups = nbCoups;
        this.duree = duree;
    }
}
