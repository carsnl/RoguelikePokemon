package game.actors;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actors.Actor;
import engine.displays.Display;
import engine.positions.GameMap;
import engine.displays.Menu;

import game.affection.AffectionManager;
import game.utility.Status;
import game.time.TimePerceptionManager;

/**
 * Class representing the Player.
 * <p>
 * Created by:
 *
 * @author Riordan D. Alfredo Modified by:
 */
public class Player extends Actor {

  private final Menu menu = new Menu();

  /**
   * Constructor.
   *
   * @param name        Name to call the player in the UI
   * @param displayChar Character to represent the player in the UI
   * @param hitPoints   Player's starting number of hitpoints
   */
  public Player(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    this.addCapability(Status.IMMUNE);
    this.addCapability(Status.HOSTILE);
    this.addCapability(Status.TRAINER);
    AffectionManager.getInstance().registerTrainer(this);

  }

  @Override
  /**
   * Process turn for a Player
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return available actions for this Pokemon this turn.
   */
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    // Handle multi-turn Actions
		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		}

    // process turn and TimePeriod here
    TimePerceptionManager t = TimePerceptionManager.getInstance();
    t.run();

    // return/print the console menu
    return menu.showMenu(this, actions, display);
  }

  @Override
  /**
   * Display the character used to represent the Player on the game map
   * @return character representing Player
   */
  public char getDisplayChar() {
    return super.getDisplayChar();
  }


}
