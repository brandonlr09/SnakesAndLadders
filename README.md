# SnakesAndLadders
An automated game of Snakes and Ladders for up to 4 players.


- The number of players (2 to 4) is entered by the user.
- The players names are entered by the user.
- The game begins.
- The order of play is determined by a dice flip. The player with the highest rolls goes first. In the event of a tie, players re-roll.
- If the reached square has a bottom of a ladder (1-4-9-21-28-36-51-71-80), then the player moves up to the square that has the top of the ladder (14-31-38-42-44-67-84-91-100).
- If the reached square has a head of a snake (16-48-64-79-93-95-97-98), then the player moves down the square that has the tail of the snake (6-19-24-30-60-68-76-78).
- The game is concluded once any of the players reaches precisely square 100. If a player is close to 100, and the dice value exceeds the maximum possible moves, the player moves
backward with the excessive amount.


Technologies used
---------------------
- Java
