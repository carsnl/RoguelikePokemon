package game.environment;

import game.utility.Element;
import game.actors.Pokemon;
import game.actors.Squirtle;

/**
 * Waterfall is one of the available types of Ground in game
 * <p>
 *
 * @author Lai Carson
 * @version 1.0
 * @since 21/09/2022
 */
public class Waterfall extends SpawningGround {

  private final int CHANCE_SPAWN_SQUIRTLE = 5; //(20/100 = 1/5, see ChanceGenerator)
  private final int CHANCE_SPAWN_POKEFRUIT = 5; //(20/100 = 1/5, see ChanceGenerator)
  private final int CHANCE_SPAWN_IF_ADJACENT = 2; // adjacent locations with similar ground element

  /**
   * Constructor.
   */
  public Waterfall() {
    super('W');
    this.addCapability(Element.WATER);
  }

  /**
   * Spawns a pokemon based on Waterfall
   *
   * @return a Squirtle
   */
  @Override
  public Pokemon spawnPokemon() {
    return new Squirtle();
  }

  /**
   * @return Squirtle spawn chance
   */
  @Override
  public int getSpawnChancePokemon() {
    return CHANCE_SPAWN_SQUIRTLE;
  }

  /**
   * @return Pokefruit spawn chance
   */
  @Override
  public int getSpawnChancePokefruit() {
    return CHANCE_SPAWN_POKEFRUIT;
  }

  /**
   * @return Number of adjacent Locations that need the same Ground element to spawn Pokemon
   */
  @Override
  public int getSpawnIfAdjacent() {
    return CHANCE_SPAWN_IF_ADJACENT;
  }
}
