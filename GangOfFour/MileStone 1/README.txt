***************************************************************
**************** SYSC 3110 Project - 1st Milestone ************
********************* Team - GangOfFour ***********************
***************************************************************

Team members:
Jeton Sinoimeri
Joel Prakash 100910302
Himanish Kaushal 100846382
Varun Sriram 100869591

AUTHORS and DELIVERABLES

AI.java - Varun
Inherits from Player class. Mainly used to make a move for the AI player
that the human is playing against.

Board.java - Himanish
Specifics boards for TicTacToe and Othello inherit from this class. Contains 
information about the general behaviour of a board. Declares a 2-D array of sqaures
that represents the board, contains a method that prints a String representation of
the board. Other methods included.

Game.java - Jeton
Othello and TicTacToe extend from this abstract class. Contains the board and a list
of players that will play the game. Contains all necessary methods that any game might
need.

GameMain.java - Varun
This class contains the main method. It asks the user which game to play and calls 
related methods to set up the game.

Human.java - Jeton
Implements the Player interface. Used to take input from the Human player via 
the console window.

Othello.java - Himanish, Joel
This class extends from Game. contains information about the behaviour of Othello.
It still needs refactoring due to heavy code duplication. The HELP string will return 
rules of a TicTacToe game. 

OthelloBoard.java - Himanish, Joel
Inherits from Board class. Contains Othello specific tasks to be carried out on the
board during game play. 

Player.java - Varun
Interface contains methods needed by any player.

Square.java - Joel
Contains information about the behaviour of each square on a board.

TicTacToe.java - Jeton, Varun
Inherits from Game and contains information about the behaviour of the tic tac toe.

TicTacToeBoard.java - Jeton, Varun
Inherits from Board class. Contains TicTacToe specific tasks to be carried out on the
board during game play.

KNOWN ISSUES

No major issues were detected.
Minor issues may include printing of wrong HELP string in Othello.  

ROADMAP AHEAD

Since the console input design for both the games is complete, we can now concentrate on making
the code more effective and concise. Strong code refactoring will be done for the next delvierable.
The implementation of minimax to detect intelligent moves for the AI will be completed by the 
next deliverable. 