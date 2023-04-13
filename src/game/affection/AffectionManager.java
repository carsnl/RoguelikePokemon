package game.affection;

import engine.actors.Actor;

import game.behaviours.FollowBehaviour;

import game.actors.Pokemon;

import java.util.HashMap;
import java.util.Map;

/**
 * Affection Manager
 * <p>
 * Created by:
 *
 * @author Riordan D. Alfredo Modified by  Anson Wong Sie Yuan
 * @version 2.0
 * @since 15/10/2022
 */
public class AffectionManager {

  /**
   * Singleton instance (the one and only for a whole game).
   */
  private static AffectionManager instance;
  /**
   * HINT: is it just for a Charmander?
   */
  private final Map<Pokemon, Integer> affectionPoints;

  /**
   * We assume there's only one trainer in this manager. Think about how will you extend it.
   */
  private Actor trainer;
  private final int MAX_AFFECTION_LVL = 100;

  /**
   * private singleton constructor
   */
  private AffectionManager() {
    this.affectionPoints = new HashMap<>();
  }

  /**
   * Access single instance publicly
   *
   * @return this instance
   */
  public static AffectionManager getInstance() {
    if (instance == null) {
      instance = new AffectionManager();
    }
    return instance;
  }

  /**
   * Add a trainer to this class's attribute. Assume there's only one trainer at a time.
   *
   * @param trainer the actor instance
   */
  public void registerTrainer(Actor trainer) {
    this.trainer = trainer;
  }

  /**
   * Add Pokemon to the collection. By default, it has 0 affection point. Ideally, you'll register
   * all instantiated game.pokemon.Pokemon
   *
   * @param pokemon
   */
  public void registerPokemon(Pokemon pokemon) {

    affectionPoints.put(pokemon, 0);
  }

  /**
   * Get the affection point by using the pokemon instance as the key.
   *
   * @param pokemon game.pokemon.Pokemon instance
   * @return integer of affection point.
   */
  public int getAffectionPoint(Pokemon pokemon) {
    return affectionPoints.get(pokemon);
  }

  public String printAp(Pokemon pokemon) {
    int points = this.getAffectionPoint(pokemon);
    return "(AP:" + points + ")";
  }

  /**
   * Useful method to search a pokemon by using Actor instance.
   *
   * @param actor general actor instance
   * @return the game.pokemon.Pokemon instance.
   */
  private Pokemon findPokemon(Actor actor) {
    for (Pokemon pokemon : affectionPoints.keySet()) {
      if (pokemon.equals(actor)) {
        return pokemon;
      }
    }
    return null;
  }

  /**
   * Increase the affection. Work on both cases when there's a Pokemon, or when it doesn't exist in
   * the collection.
   *
   * @param pokemon Pokemon instance
   * @param point   positive affection modifier
   * @return custom message to be printed by Display instance later.
   */
  public String increaseAffection(Pokemon pokemon, int point) {
    String print;
    Integer givenPoints = Integer.valueOf(point);
    Integer pokePts = getAffectionPoint(pokemon);

    if (pokePts < MAX_AFFECTION_LVL) {
      if (pokePts <= -50) {
        print = String.format(
            "The affection point for this " + pokemon + " is " + pokePts + " and cannot be fixed",
            this.printAp(pokemon));
      } else {
        affectionPoints.put(pokemon, affectionPoints.get(pokemon) + givenPoints);

        print = pokemon + " liked the pokefruit! (+20 affection point)";
      }

      if (pokePts >= 75) {
        pokemon.getBehaviours().put(5, new FollowBehaviour(trainer));
      }
    } else {
      print = "The affection point for " + pokemon + "is maxed";
    }

    return print;
  }

  /**
   * Decrease the affection level of the pokemon. Work on both cases when it is
   *
   * @param pokemon Pokemon instance
   * @param point   positive affection modifier (to be subtracted later)
   * @return custom message to be printed by Display instance later.
   */
  public String decreaseAffection(Pokemon pokemon, int point) {
    String print;
    Integer givenPoints = Integer.valueOf(point);

    affectionPoints.put(pokemon, affectionPoints.get(pokemon) - givenPoints);

    print = pokemon + "'s affection points have been decreased";

    return print;
  }
}
