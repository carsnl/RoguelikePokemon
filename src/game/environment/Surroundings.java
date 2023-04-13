package game.environment;

import engine.positions.GameMap;
import engine.positions.Ground;
import engine.positions.Location;
import game.utility.Element;
import game.utility.Status;
import java.util.ArrayList;

/**
 * Stores a Location and accesses its surroundings; modifies the Location and its surroundings
 * Locations stored as a 9x9 grid in an ArrayList
 * <p>
 *
 * @author Lai Carson
 * @version 1.0
 * @since 21/09/2022
 */
public class Surroundings {

  private ArrayList<Location> surroundings = new ArrayList<Location>(); // 8 surrounding Locations
  private Location centre; // centre of 9x9 grid
  private static Surroundings instance = null; // instance null first time

  /**
   * Constructor.
   */
  private Surroundings() {
  }

  /**
   * A singleton pattern
   *
   * @return A new (if not previously instantiated) or exisiting Surrounding instance
   */
  public static Surroundings getInstance() {
    if (instance == null) {
      instance = new Surroundings();
    }
    return instance;
  }

  /**
   * Configures the 9x9 grid and stores it as an attribute; checks the number of surrounding
   * Locations that have the identical element when compared to the centre
   *
   * @param location the centre of the 9x9 grid
   * @return how many of the 8 surrounding Locations have identical elements
   */
  public int checkSurroundings(Location location) {
    surroundings.clear(); // clear data before use
    GameMap map = location.map(); // map required to access surrounding Locations
    // assuming 1 ground can only have 1 element, get its (only) element
    Element centreElement = (location.getGround().findCapabilitiesByType(Element.class)).get(0);
    this.centre = location;

    // 9x9 grid layout
    // [x-1, y+1] [x+0, y+1] [x+1, y+1]
    // [x-1, y+0] [location] [x+1, y+0]
    // [x-1, y-1] [x+0, y-1] [x+1, y-1]

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0) {
          ; // do not add centre to list
        } else {
          try {
            surroundings.add(map.at((location.x() + i), (location.y() + j)));
          } catch (ArrayIndexOutOfBoundsException e) {
            ; // if coordinates out of map bounds, ignore it
          }
        }
      }
    }

    int sameAdjacentElement = 0; // reset

    // check how many of surrounding Locations have same element as centre
    for (Location spot : surroundings) {
      if (spot.getGround().hasCapability(centreElement)) {
        sameAdjacentElement += 1;
      }
    }

    return sameAdjacentElement;
  }

  /**
   * Set surrounding Ground to a different Ground if it is a different element
   *
   * @param ground   change current Ground to this one
   * @param location centre of the grid
   */
  public void setSurroundings(Ground ground, Location location) {
    // assuming 1 ground can only have 1 element, get its only element
    Element element = (ground.findCapabilitiesByType(Element.class)).get(0);

    // get surrounding Locations
    checkSurroundings(location);

    // for each surrounding Location
    for (Location spot : surroundings) {
      // only replace if different element or not indestructible
      if (!(spot.getGround().hasCapability(element)) && !(spot.getGround().hasCapability(
          Status.INDESTRUCTIBLE))) {
        spot.setGround(ground);
      }
    }
  }
}
