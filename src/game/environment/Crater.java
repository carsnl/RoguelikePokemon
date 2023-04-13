package game.environment;

import game.actors.Charmander;
import game.utility.Element;
import game.actors.Pokemon;

/**
 * Crater is one of the available types of Ground in game
 * <p>
 *
 * @author Lai Carson
 * @version 2.0
 * @since 21/09/2022
 */

public class Crater extends SpawningGround {

  private final int CHANCE_SPAWN_CHARMANDER = 10; // (10/100 = 1/10; see ChanceGenerator)
  private final int CHANCE_SPAWN_POKEFRUIT = 4; // (25/100 = 1/4; see ChanceGenerator)
  private final int CHANCE_SPAWN_IF_ADJACENT = 0; // adjacent locations with similar ground element

  /**
   * Crater constructor
   */
  public Crater() {
    super('O');
    this.addCapability(Element.FIRE);
  }

  /**
   * Spawns a pokemon based on Crater
   *
   * @return a Charmander
   */
  @Override
  public Pokemon spawnPokemon() {
    return new Charmander();
  }

  /**
   * @return Charmander spawn chance
   */
  @Override
  public int getSpawnChancePokemon() {
    return CHANCE_SPAWN_CHARMANDER;
  }

  /**
   *
   * @return Pokefruit spawn chance
   */
  @Override
  public int getSpawnChancePokefruit() {
    return CHANCE_SPAWN_POKEFRUIT;
  }

  /**
   *
   * @return Number of adjacent Locations that need the same Ground element to spawn Pokemon
   */
  @Override
  public int getSpawnIfAdjacent() {
    return CHANCE_SPAWN_IF_ADJACENT;
  }
}