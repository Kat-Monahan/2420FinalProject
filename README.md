# Lost Hiker

A search-and-rescue game written in Java and Swing. A hiker is lost on a 10×10 mountain
map. Move your rescue team across the terrain and find them before time runs out.

Final project for CS 2420 (Algorithms & Data Structures), Salt Lake Community College.

## How to play
- Each move costs time, based on the terrain you enter: meadow and bridge 5, forest 10,
  mountain 15. Rivers can't be crossed.
- The hiker wanders to a neighboring tile after some of your moves, so you only ever know their
  last known position.
- **Scan** checks the tile you're standing on. If the hiker is there, you win. If not, you learn
  where they were last seen.
- **Cheat Code** highlights the fastest route to the hiker's actual position.
- **Move History** lists the tiles you've visited.
- You lose if the timer reaches zero.

## How it works
- The map is loaded from `src/Resources/map.txt` (starting positions, blocked cells) and stored
  as an undirected graph with one vertex per tile.
- The Cheat Code builds an edge-weighted digraph from the terrain costs and uses
  **Dijkstra's algorithm** to find the lowest-time path to the hiker.
- Uses `Graph`, `EdgeWeightedDigraph`, `DijkstraSP`, and `Queue` from Princeton's
  [algs4](https://algs4.cs.princeton.edu) library.

## How to run
1. Download `algs4.jar` and add it to the project's build path.
2. Import the project into Eclipse (or another Java IDE) and run `GameWindow.java`
   from the project folder, so the `src/Resources` paths resolve.

## Team
- **Kathleen Monahan** – game window, opening, win and lose screens, hiker status, and shared work
  on the game logic, control panel, map display, and terrain
- **Bowen Berthelson** – map loading and graph construction, game configuration, hiker and team
  models, and shared work on the game logic and terrain
