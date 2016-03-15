***************************************************************
**************** SYSC 3110 Project - 2nd Milestone ************
********************* Team - GangOfFour ***********************
***************************************************************

Team members:
Jeton Sinoimeri 100875046
Joel Prakash 100910302
Himanish Kaushal 100846382
Varun Sriram 100869591

***NOTE***
We assumed that all the work was to be done on Othello. So we have created 
a GUI version of Othello and we tried to implment mini-max only for Othello.
There is no way of running even the text version of tic tac toe because the 
main method directly starts up the GUI for Othello. 

AUTHORS and DELIVERABLES

AI.java - Varun
Inherits from Player class. Mainly used to make a move for the AI player
that the human is playing against.

AITest.java - Jeton
JUnit testing of the AI class.

Board.java - Himanish
Specifics boards for TicTacToe and Othello inherit from this class. Contains 
information about the general behaviour of a board. Declares a 2-D array of sqaures
that represents the board, contains a method that prints a String representation of
the board. Other methods included.

BoardTest.java - Jeton
JUnit testing of the Board class.

Controller.java - Varun, Himanish
This is the controller class that is part of the MVC game set-up. It controls
the actions that are performed when a square on the board (represented by
black/white/green panels) is clicked. It also helps update the score board.

DocumentationHELP.java - Jeton
Javadoc document.

Game.java - Jeton
Othello and TicTacToe extend from this abstract class. Contains the board and a list
of players that will play the game. Contains all necessary methods that any game might
need.

GameMain.java - Varun
This class is now useless because the main is now run in the Main class.

GameTest.java - Jeton
JUnit testing of the Game class.

Main.java - Varun
This is the main class.

MiniMaxAI.java - Joel, Varun
This class contains all the code that is needed to choose the AI player's next
move using mini-max.

NewGameListener.java - Varun
Implements ActionListener. This is a listener class that listens to the 'new game' button in the Othello
GUI frame. 

Node.java - Joel, Varun
A brand new node class created to support the tree in mini-ma used to decide the 
AI player's next move.

Human.java - Jeton
Implements the Player interface. Represents the human player.

HumanTest.java - Jeton
JUnit testing of the Human class.

Othello.java - Himanish, Joel
This class extends from Game. contains information about the behaviour of Othello.
It still needs refactoring due to heavy code duplication. The HELP string will return 
rules of a TicTacToe game. 

OthelloBoard.java - Himanish, Joel
Inherits from Board class. Contains Othello specific tasks to be carried out on the
board during game play. 

OthelloBoardTest.java - 
JUnit testing of the OthelloBoard class.

OthelloGrid.java - 
JUnit testing of the OthelloGrid class.

OthelloMVCSetup.java - Varun
This is the main class that links all the GUI components together and sets up the game
using the MVC pattern.

OthelloPanel.java - Varun
The panel that contains the squares of the board.

OthelloTest.java - Himanish
JUnit testing of the Othello class.

PassListener.java - Varun
Implements ActionListener. This is a listener class that listens to the 'pass' button in the Othello
GUI frame.  

Player.java - Varun
Interface contains methods needed by any player.

ScoreLabel.java - Varun
Extends JLabel. 

Square.java - Joel
Contains information about the behaviour of each square on a board.

SquareTest.java - Jeton
JUnit testing of the Square class.

TicTacToe.java - Jeton, Varun
Inherits from Game and contains information about the behaviour of the tic tac toe.

TicTacToeTest.java - Jeton
JUnit testing of the TicTacToe class.

TicTacToeBoard.java - Jeton, Varun
Inherits from Board class. Contains TicTacToe specific tasks to be carried out on the
board during game play.

TicTacToeBoardTest.java - Jeton
JUnit testing of the TicTacToeBoard class.


KNOWN ISSUES

- The MiniMax class has a lot of bugs and runs into errors. Isn't fully implemented.
- Minor issues may include printing of wrong HELP string in Othello.  

ROADMAP AHEAD

We are going to get the mini-max implementation of the AI working perfectly. We are going to
further seperate the model, view and controller by putting them in differnt folders. We are 
going to decide on the new game that we are going to do and design the GUI component and implementation
of the rules of that game. 