# MapsNearestNeighbor

Build Android Apps with google maps that calculate nearest coordinate (Android)

## Getting Started

These app can calculate the nearest neighbor coordinate using euclidian distance equation, given some coordinate in ArrayList then system will calculate distance each coordinate with current location.
in this project there is 4 main class:
- Coordinates (Object Class for coordinates with its attributes: place, latitude, longitude)
- Distances (Object Class for distances with its attributes: name, distances)
- DummyActivity (tha main activity)
- DummyCoordinates (contain some main method to calculate distance, calculate nearest distance, add market, etc)

### Code

make sure you connect your project to google Maps in console.developers.google.com
add your google maps key on res/values/google_maps_api.xml

method getDistances to calculate the distance between current location coordinate and coordinate A using Euclidian Distance equation

```
private static ArrayList<Distances> getDistance(double cuLat, double curLong, ArrayList<Coordinates> coord) {
    ArrayList<Distances> distances = new ArrayList<>();
    for (int i=0;i<coord.size();i++){
        distances.add(new Distances(coord.get(i).getPlace(),(Math.sqrt((coord.get(i).getLatitude() - cuLat) * (coord.get(i).getLatitude() - cuLat) + (coord.get(i).getLongitude() - curLong) * (coord.get(i).getLongitude() - curLong)))));
    }
    return distances;
}
```

method getNearestDistance to calculate the nearest distance between current location coordinate and other coordinate B by comparing distance and pick the smallest one. 

```
private static Distances getNearestDistance(double curLat, double curLong, ArrayList<Coordinates> coord) {
    ArrayList<Distances>distances = getDistance(curLat, curLong, coord);
    Distances nearest = new Distances("",Double.MAX_VALUE);
    for(int i=0;i<distances.size();i++) {
        if (nearest.getDistances() > distances.get(i).getDistances()) {
            nearest.setDistances(distances.get(i).getDistances());
            nearest.setName(distances.get(i).getName());
        }
    }
    return nearest;
}
```

## Built With

* IDE - Android Studio 2.1.3
* Operating System - Ubuntu

## Authors

* **Marchellin Antonia**

## Screenshot
* first action is locate current location <br />
<img src="https://cloud.githubusercontent.com/assets/12492522/20509404/28e53c0e-b09b-11e6-89b7-c3b89b3c23ac.png" width="300">
<br />
* when nearby coordinate button is clicked, it show all coordinates in ArrayList and mark the nearest one with blue marker <br />
<img src="https://cloud.githubusercontent.com/assets/12492522/20509405/2916c77e-b09b-11e6-92f9-54c90deedbc2.png" width="300">

