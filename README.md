'''bash
Require Java FX and Java
 TO run
./gradlew.bat build     
./gradlew.bat run


'''

The package game Handlers:
gameHandler holds the underlying logic of the game 
Board.java - This is where my representation of Chess board logic lies mostly, I overridden the to string function to allow for chess board representation in the logs and hold important calculation methods for legal moves and active pieces on the board. 
BoardUtil.Java- Holds important constants and validity checks for tiles 
Tile.java- Handles Tile information as I want to originally store the chess board as Linear representation that later made things complicated for the GUI. Handles whether a Tile is occupied or not as both have different behaviors and implications for the game.
Player.Java- where I learned enums can hold functions which is pretty cool
ChessPiece.java- 
The basic and abstract implementation of a chess piece I use this class to extend to all other Chess Pieces, Holds enum for piece Type as well.
Move.java- Move is a complicated one, it handles execution of a move on board. Handle whether a move is attacking or developing. Handles Special move types: king castle(both queen and king side), Pawn attacking move, Pawn Jump and I feel a clever use of extends. One downside of this class this that its bloated with subclasses  
RookHandler.java I implemented this second bishop for vertical and horizontal moving pieces containing logic for developing or attacking moves. Contains edge cases for where the linear representation breaks down ei 0 to 63 
PawnHandler.java: supports forward movement and sideways takes 
KingHandler.java: also a combination of rook and bishop pieces to a smaller degrees, King has special moves like castle that I tried to account for 
QueenHandler.java: a combination of rook and bishop pieces 
BishopHandler.Java: first I implemented Bishop to later help me with diagonal moving pieces, handles edge cases as well for where the linear representation breaks down. Contains definitions for attacking moves and developing moves and  extends from Chess Pieces 

Package player Controller: 
Whiteplayer- extends playerHandler and implements it for White
BlackPlayer - extends playerHandler and implements it for Black
Player Handler- managing the state and behavior of a player in a chess game so it basically serves as a template for the other player objects such that I can extend to them. Where I check for checkmates, Stalemates and other weird states of chess
MoveDriver- responsible for encapsulating the result of a move in a chess game and transition a board to another board state after a move. Alteast tries too
MoveStatus- an enum that tells us whether a move is done.

Package Template 
Contains App.java: displays board where I'm running into issues where the board does not redraw with each move.
