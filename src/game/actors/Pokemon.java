package game.actors;

import engine.actions.Action;
import engine.actions.ActionList;
import engine.actions.DoNothingAction;
import engine.actors.Actor;
import engine.displays.Display;
import engine.items.Item;
import engine.positions.GameMap;

import game.actions.CatchAction;
import game.actions.FeedAction;
import game.affection.AffectionManager;
import game.behaviours.*;
import game.items.Pokefruit;
import game.utility.Element;
import game.utility.Status;


import java.util.HashMap;
import java.util.Map;

/**
 * Abstraction of features and behaviours common across all Pokemon.
 * <p>
 * Created by:
 *
 * @author Lai Carson, Anson Wong Sie Yuan
 * @version 3.0
 * @since 15/10/2022
 */
public abstract class   Pokemon extends Actor {
  /**
   * hashmap to store the behaviour of the Pokemon
   */
  protected final Map<Integer, Behaviour> behaviours = new HashMap<>();
  /**
   * The amount of turn for Pokemon to evolve
   */
  private final int EVOLVE_AT = 3;

  /**
   * TO store the element of the Pokemon
   */
  private Element element;

  /**
   * The amount of turns of the Pokemon
   */
  private int age; // how many turns a pokemon has survived

  public Pokemon(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    this.addCapability(Status.HOSTILE);
    this.addCapability(Status.CATCHABLE);
    this.behaviours.put(10, new WanderBehaviour());
    this.behaviours.put(1, new AttackBehaviour());
    this.age = 0; // initial age is 0
  }

  /**
   * Process turn for a Pokemon
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return available actions for this Pokemon this turn.
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

    if (!this.isConscious()){
      map.removeActor(this);
    }
    else{
      System.out.println(this.name + " " + this.printHp() + AffectionManager.getInstance().printAp(this)); // current health
      this.age += 1;
      if (this.age >= EVOLVE_AT || AffectionManager.getInstance().getAffectionPoint(this) == 100){ // check age requirement for evolution
        // System.out.println("Age requirement met.");
        this.behaviours.put(2, new EvolveBehaviour(this.evolve())); // can potentially evolve
      }

      // implement follow behaviour
      for(Behaviour behaviour: behaviours.values()){
        Action action = behaviour.getAction(this, map);
        if (action != null)
          return action;
      }


    }
    return new DoNothingAction();
  }


  /**
   * To get the behaviour hashmap of the pokemon
   * @return Hashmap of behaviour
   */
  public Map<Integer, Behaviour> getBehaviours() {
    return behaviours;
  }


  /**
   * To get the element of the Pokemon
   * @return element
   */
  public Element getElement() {
    return element;
  }

  /**
   * To add a behaviour for the pokemon
   * @param priority the priority value of the behaviour
   * @param behaviour Behaviour instance
   */
  public void setBehaviours(int priority, Behaviour behaviour) {
    behaviours.put(priority, behaviour);
  }

  /**
   * Returns a list of actions that can be performed by the actor
   * @param otherActor the Actor that might perform an action.
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   * @return list of actions
   */
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    ActionList actions = new ActionList();
    boolean gotFruit = false;

    int i = 0;
    while(!gotFruit && i < otherActor.getInventory().size()){
      Item item = otherActor.getInventory().get(i);
      if(item.hasCapability(Status.EDIBLE) & otherActor.hasCapability(Status.TRAINER)){
        actions.add(new FeedAction(otherActor,this, (Pokefruit) item));
        gotFruit = true;
      }
      i = i + 1;
    }

      if(otherActor.hasCapability(Status.TRAINER)){
        actions.add(new CatchAction(this));
      }

    return actions;
  }

  /**
   * An abstract method to randomly picks out a weapon from the BackUpWeapon of the Pokemon is the conditions are met
   * @param isEquipping boolean representing if the condition to equip weapon is met
   */
  public abstract void toggleWeapon(boolean isEquipping);

  /**
   *
   * @return Evolved form of a Pokemon, else null if no evolved form.
   */
  public abstract Pokemon evolve();

  /**
   * An abstract method to check the condition to use the special weapon item for the Pokemon
   * @param actor current pokemon
   * @param actor2 other pokemon
   * @param map the game map
   * @return boolean to indicate whether the condition are met or not
   */
  public abstract boolean addSpecialItem(Actor actor, Actor actor2 ,GameMap map);
}
