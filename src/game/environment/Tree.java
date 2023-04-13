package game.environment;

import engine.positions.Location;

import game.actors.Bulbasaur;
import game.time.TimePeriodEffect;
import game.utility.ChanceGenerator;
import game.utility.Element;
import game.actors.Pokemon;
import game.items.Candy;
import game.time.TimePerception;

/**
 * Tree is one of the available types of Ground in game
 * <p>
 *
 * @author Lai Carson
 * @version 2.0
 * @since 21/09/2022
 */

public class Tree extends SpawningGround implements TimePerception, ExpandCapable, DropCapable {

  private final int CHANCE_SPAWN_BULBAUSAUR = 7; //(15/100 = 1/6.67, see ChanceGenerator)
  private final int CHANCE_SPAWN_POKEFRUIT = 7; //(15/100 = 1/6.67, see ChanceGenerator)
  private final int CHANCE_SPAWN_IF_ADJACENT = 1; // adjacent locations with similar ground element
  private final int CHANCE_DROP = 20; //(5/100 == 1/20, see ChanceGenerator)
  private final int CHANCE_EXPAND = 10; //(10/100 = 1/10, see ChanceGenerator)

  /**
   * Constructor.
   */
  public Tree() {
    super('+');
    this.addCapability(Element.GRASS);
    this.registerInstance();
    this.addCapability(TimePeriodEffect.DROPABLE);
    this.addCapability(TimePeriodEffect.EXPANDABLE);
  }

  /**
   * Spawns a pokemon based on Tree
   *
   * @return a Bulbausaur
   */
  @Override
  public Pokemon spawnPokemon() {
    return new Bulbasaur();
  }

  /**
   * @return Bulbausaur spawn chance
   */
  @Override
  public int getSpawnChancePokemon() {
    return CHANCE_SPAWN_BULBAUSAUR;
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

  /**
   * Checks time shift, then performs an action based on current capabilities Checks if conditions
   * met, then spawns pokemon and pokefruit
   *
   * @param location The location of the Ground
   */
  @Override
  public void tick(Location location) {
    // decide if a pokemon or item should be spawned this turn
    spawn(location);

    // perform action based on shift
    if (location.getGround().hasCapability(TimePeriodEffect.EXPANDABLE)) {
      ChanceGenerator gen = ChanceGenerator.getInstance();
      // either convert surroundings to tree or hay
      if (gen.chanceOutcome(2)){
        expand(this, CHANCE_EXPAND, location);
      } else {
        //System.out.println("Converted to Hay");
        expand(new Hay(), CHANCE_EXPAND, location);
      }
    } else if (location.getGround().hasCapability(TimePeriodEffect.DROPABLE)) {
      drop(new Candy(), CHANCE_DROP, location);
    }
  }

  /**
   * Adjust capabilities during day
   */
  @Override
  public void dayEffect() {
    this.addCapability(TimePeriodEffect.DROPABLE);
    this.removeCapability(TimePeriodEffect.EXPANDABLE);
  }

  /**
   * Adjust capabilities during night
   */
  @Override
  public void nightEffect() {
    this.addCapability(TimePeriodEffect.EXPANDABLE);
    this.removeCapability(TimePeriodEffect.DROPABLE);
  }

  /**
   * Adjust capabilities during cursed night
   */
  @Override
  public void cursedNightEffect() {
    this.removeCapability(TimePeriodEffect.EXPANDABLE);
  }
}

