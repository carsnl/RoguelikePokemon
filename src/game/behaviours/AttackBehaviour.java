package game.behaviours;

import engine.actions.Action;
import engine.actions.DoNothingAction;
import engine.actors.Actor;
import engine.positions.Exit;
import engine.positions.GameMap;
import game.actions.AttackAction;
import game.utility.Element;
import game.utility.ElementsHelper;
import game.utility.Status;

/**
 * Determines if a Pokemon should attack based on its surrounding actor.
 * <p>
 * Created by:
 *
 * @author Riordan D. Alfredo Modified by Anson Wong Sie Yuan
 * @version 1.0
 * @since 16/10/2022
 */


public class AttackBehaviour implements Behaviour {

  /**
   * Checks for surrounding for an actor, if conditions are met, then returns a new Attack Action
   *
   * @param actor the Actor acting
   * @param map   the GameMap containing the Actor
   * @return new AttackAction or null if condition not met
   */
  @Override
  public Action getAction(Actor actor, GameMap map) {
      for (Exit exit : map.locationOf(actor).getExits()) {
          if (exit.getDestination().containsAnActor()) {
              Actor otherActor = exit.getDestination().getActor();
              if (!(ElementsHelper.hasAnySimilarElements(actor,
                  otherActor.findCapabilitiesByType(Element.class)))) {
                  System.out.println("Test:   " + otherActor);
                  if (actor.toString().equals("Pikachu")){
                      if(otherActor.hasCapability(Status.TRAINER)) {
                          return new DoNothingAction();
                      }
                  }
                  else{
                      return new AttackAction(otherActor, "here");
                  }
              }
          }
      }
    return null; // go to next behaviour
  }
}
