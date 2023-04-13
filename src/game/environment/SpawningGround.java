package game.environment;

import engine.positions.Ground;
import engine.positions.Location;
import game.utility.ChanceGenerator;
import game.utility.Element;
import game.actors.Pokemon;
import game.items.Pokefruit;

/**
 * Parent class for Ground that can spawn objects
 * <p>
 *
 * @author Lai Carson
 * @version 1.0
 * @since 21/09/2022
 */

public abstract class SpawningGround extends Ground {

  /**
   * constructor.
   *
   * @param displayChar display character of ground
   */
  public SpawningGround(char displayChar) {
    super(displayChar);
  }

  /**
   * @return spawn chance of Pokemon
   */
  public abstract int getSpawnChancePokemon();

  /**
   * @return spawn chance of pokefruit
   */
  public abstract int getSpawnChancePokefruit();

  /**
   * @return number of surrounding locations that need to have the same element to spawn pokemon
   */
  public abstract int getSpawnIfAdjacent();

  /**
   * @return pokemon based on Ground type
   */
  public abstract Pokemon spawnPokemon();

  /**
   * @return A pokefruit
   */
  public Pokefruit spawnPokefruit() {
    return new Pokefruit(getElement());
  }

  /**
   * Returns element of a SpawningGround class (assuming a SpwaningGround can only have 1 element)
   * @return element of a SpawningGround class
   */
  public Element getElement(){
    return this.findCapabilitiesByType(Element.class).get(0);
  }

  /**
   * Checks if conditions met, then spawns pokemon and pokefruit
   *
   * @param location The location of the Ground
   */
  @Override
  public void tick(Location location) {
    // decide if a pokemon or item should be spawned this turn
    spawn(location);
  }

  public void spawn(Location location) {
    // assuming 1 ground can only have 1 element, get its only element
    Element element = location.getGround().findCapabilitiesByType(Element.class).get(0);
    Surroundings surroundings = Surroundings.getInstance();
    ChanceGenerator chanceGenerator = ChanceGenerator.getInstance();

    // story location and surroundings
    int sameAdjacentType = surroundings.checkSurroundings(location);

    // spawn pokemon and fruit
    // if adjacency requirement met and no actor at location
    if (sameAdjacentType >= getSpawnIfAdjacent() && !(location.containsAnActor())) {
      // if chance outcome is true
      if (chanceGenerator.chanceOutcome(getSpawnChancePokemon())) {
        location.addActor(spawnPokemon());
      }
    }
    // if chance outcome is true
    if (chanceGenerator.chanceOutcome(getSpawnChancePokefruit())) {
      location.addItem(spawnPokefruit());
    }
  }
}
