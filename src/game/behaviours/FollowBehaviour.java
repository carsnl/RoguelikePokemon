package game.behaviours;

import engine.actors.Actor;
import engine.actions.Action;
import engine.positions.Exit;
import engine.positions.GameMap;
import engine.positions.Location;
import engine.actions.MoveActorAction;

/**
 * A class that figures out a MoveAction that will move the actor one step closer to a target
 * Actor.
 *
 * @author Riordan D. Alfredo Modified by  Anson Wong Sie Yuan
 * @version 2.0
 * @see edu.monash.fit2099.demo.mars.Application
 * <p>
 * Created by:
 * @since 16/10/2022
 */
public class FollowBehaviour implements Behaviour {

  private final Actor target;

  /**
   * Constructor.
   *
   * @param subject the Actor to follow
   */
  public FollowBehaviour(Actor subject) {
    this.target = subject;
  }

  @Override
  /**
   * To allow the targeted actor to follow the another actor
   *
   * @param Actor Actor to be followed
   * @param map the game map
   */
  public Action getAction(Actor actor, GameMap map) {
		if (!map.contains(target) || !map.contains(actor)) {
			return null;
		}

    Location here = map.locationOf(actor);
    Location there = map.locationOf(target);

    int currentDistance = distance(here, there);
    for (Exit exit : here.getExits()) {
      Location destination = exit.getDestination();
      if (destination.canActorEnter(actor)) {
        int newDistance = distance(destination, there);
        if (newDistance < currentDistance) {
          return new MoveActorAction(destination, exit.getName());
        }
      }
    }

    return null;
  }

  /**
   * Compute the Manhattan distance between two locations.
   *
   * @param a the first location
   * @param b the first location
   * @return the number of steps between a and b if you only move in the four cardinal directions.
   */
  private int distance(Location a, Location b) {
    return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
  }
}