package game.teleport;

import engine.actions.ActionList;
import engine.actions.MoveActorAction;
import engine.actors.Actor;
import engine.positions.Ground;
import engine.positions.Location;
import game.utility.Status;

/**
 * Door that teleports Player to a new location.
 * <p>
 * Created by:
 *
 * @author Mior Shazryl Afiq
 * @version 3.0
 * @since 15/10/2022
 */
public class Door extends Ground {

  // private attributes
  private Location myLocation;

  private String myDestinationDescription;

  private Door destinationDoor;

  /**
   * Constructor for Door with no parameters. This constructor is used to create a Door that is not
   * a destination Door.
   */
  public Door() {
    super('=');
    this.addCapability(Status.INDESTRUCTIBLE);
  }

  /**
   * Constructor for Door with parameters. This constructor is used to create a Door that is a
   * destination Door.
   *
   * @param myLocation               Destination of the Door
   * @param myDestinationDescription Description of the destination of the Door
   */
  public Door(Location myLocation, String myDestinationDescription) {
    super('=');
    this.addCapability(Status.INDESTRUCTIBLE);
    this.myLocation = myLocation;
    this.myDestinationDescription = myDestinationDescription;
  }

  /**
   * Returns true if an Actor can enter this location. Actors can enter a location if the terrain is
   * passable.
   *
   * @param actor the Actor who might be moving
   * @return true if the Actor can enter this location
   */
  public boolean canActorEnter(Actor actor) {
    return true;
  }

  /**
   * Returns a collection of Actions that the Actor can perform when the Actor is on this location.
   * If the Actor is a Player and is standing on Door, the Player can teleport to the destination of
   * Door.
   *
   * @param actor     the Actor acting
   * @param location  the current Location
   * @param direction string representing the direction of the Actor
   * @return a collection of Actions
   */
  @Override
  public ActionList allowableActions(Actor actor, Location location, String direction) {
    ActionList action = new ActionList();
    if (myLocation.containsAnActor() && myLocation.getActor().hasCapability(Status.IMMUNE)) {
      action.add(new MoveActorAction(destinationDoor.myLocation,
          destinationDoor.myDestinationDescription));
    }
    return action;
  }

  /**
   * Sets the destination of Door.
   */
  public void setDestinationDoor(Door destinationDoor) {
    this.destinationDoor = destinationDoor;
  }
}
