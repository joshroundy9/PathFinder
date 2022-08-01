## Intro
This project is essentially a maze builder that if possible finds the solution with the fastest time.

## Instructions 
To edit a tile simply click it to cycle through nonsolid, solid, and finish flag tiles.
To start the solving animation press start.
The rest should be self explanatory.

## Technicalities
The project is built using Java swing, with the grid system utilizing a hashmap linking a coordinate to a tile object.
The grid system allows the very simple nested for loop pathfinding algorithm to spread throughout the maze until it either finds the end or runs out of space to expand.
