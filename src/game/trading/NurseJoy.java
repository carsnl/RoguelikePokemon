package game.trading;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actions.DoNothingAction;
import engine.actors.Actor;
import engine.displays.Display;
import engine.positions.GameMap;
import game.utility.Element;
import game.items.Pokeball;
import game.items.Pokefruit;

/**
 * Nurse Joy trades items with Candies from the Player
 * <p>
 * Created by:
 *
 * @author Mior Shazryl Afiq
 * @version 2.0
 * @since 26/09/2022
 */
public class NurseJoy extends Actor {

  /**
   * Constructor.
   */
  public NurseJoy() {
    super("Nurse Joy", '%', 0);
  }

  /**
   * Checks if Player is located next to Nurse Joy
   *
   * @param actor the Actor to check
   * @return true if Player is located next to Nurse Joy
   */
  public boolean isNextToPlayer(Actor actor, GameMap map) {
    return map.locationOf(actor).getExits().contains(map.locationOf(this));
  }

  /**
   * Returns a collection of Actions that the Player can perform when Player approaches Nurse Joy
   *
   * @param otherActor the Actor that is approaching
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return a collection of Actions
   */
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    actions.add(new TradeAction(new Pokefruit(Element.FIRE), 1));
    actions.add(new TradeAction(new Pokefruit(Element.GRASS), 1));
    actions.add(new TradeAction(new Pokefruit(Element.WATER), 1));
    actions.add(new TradeAction(new Pokeball(), 5));
    return actions;
  }

  /**
   * Nurse Joy is an actor to only trade items. Nurse Joy will not do anything throughout the whole
   * game.
   *
   * @param actions    collection of Actions for this Actor
   * @param lastAction the Action this Actor took last turn
   * @param map        the map containing the Actor
   * @param display    the I/O object where written messages will be displayed
   * @return an action
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }
}
