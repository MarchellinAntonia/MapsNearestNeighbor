package chellin.com.mapstutorial;

/**
 * Created by chellin on 21/11/16.
 */

public class Distances {

    String name;
    double distances;

    public Distances(String name, double distances) {
        this.name = name;
        this.distances = distances;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistances() {
        return distances;
    }

    public void setDistances(double distances) {
        this.distances = distances;
    }
}
