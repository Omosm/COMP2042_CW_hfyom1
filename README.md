# Brick_Destroy
This is a simple arcace video game.
Player's goal is to destroy a wall with a small controller.
The game has  very simple commmand:
SPACE start/pause the game
A move left the player
D move rigth the player
ESC enter/exit pause menu
ALT+SHITF+F1 open console
the game automatically pause if the frame loses focus

Enjoy ;-)

## Refactory

 #### Organising Files 
  Separating resources from the source codes.
 #### Appropriate packages- MVC
  Created packages: controller, view, model, main. Moved classes to appropriate packages that fit.
  
#### Breaking and Cleaning up Classes 
  To follow the Single Responsibility Principle some classes have been broken down.
  Level related methods in class Wall were moved to an newly made class called level.
  Crack class was separated from Brick class.
 
 
## Additions 
  #### Additional screen Info
   In the home menu when starting the game, user can click info button to learn instructions.
  #### Simple feature
   Created a sound feature. It's a game theme and can be turned on or off in the pause menu.
  #### High Score
   Player can enter their name at the home menu and if the player loses or wins a high score pop up will be displayed showing the player's name and score.
  #### Maven
   Used Maven to create build files, indicating the dependencies of the codes.
  #### JUnit
   Added meaningful JUnit tests.
  #### Javadocs 
   Added Javadocs for all necessary classes to ensure easier understanding of the code.
      
     


