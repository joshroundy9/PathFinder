## Intro
This project is a maze builder that if finds any possible solution with the fastest time using dijkstra's algorithm.
<img width="300" alt="Screen Shot 2022-10-12 at 12 48 44 PM" src="https://user-images.githubusercontent.com/104024433/195402254-74b3fb64-5163-4a98-a921-2f6c0ca8f87b.png">
<img width="300" alt="Screen Shot 2022-10-12 at 12 52 44 PM" src="https://user-images.githubusercontent.com/104024433/195402650-5c811b20-5276-4749-aefc-b26d297eba6a.png">






## Instructions 
To edit a tile simply click it to cycle through nonsolid (white), solid (black), and finish (flag) tiles.
To start the solving animation press start and then the algorithm begins searching at the start tile, spreading red search tiles in all directions until it finds a finish tile or has searched all paths open to it.

## Technicalities
The project is built using Java swing, with the grid system utilizing a hashmap linking a coordinate to a tile object.
The grid system allows the very simple nested for loop pathfinding algorithm to spread throughout the maze until it either finds the end or runs out of space to expand.
