package game;

import game.actors.*;
import game.environment.*;

import java.util.Arrays;
import java.util.List;

import engine.actors.Actor;
import engine.displays.Display;
import engine.positions.FancyGroundFactory;
import engine.positions.GameMap;
import engine.positions.World;
import game.teleport.Door;
import game.trading.NurseJoy;


/**
 * The main class to start the game.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * @author Mior Shazryl Afiq
 * @version 2.0
 * @since 8/10/2022
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(),
                new Floor(), new Tree(),
                new Lava(), new Puddle(), new Crater(), new Waterfall(), new Hay(), new Door());

        // Game Map
        List<String> map = Arrays.asList(

                "..........,..................................^^^^^^^^^^^^^^",
                "...+...+.,,.......................W~..W....,...+...^^^^^^^^",
                "......+,,,........^O^^.............~WW~...,,.........^^^^^^",
                ".....,,,,........O^^^...............~~~~....,...........^^^",
                "........++................###.........~...........,......^^",
                ".......,..................#_#.............................^",
                ".....................+......,..,...........................",
                "...+.......~..................,,,..........,...............",
                "...~~~~~~~~.................^^^............................",
                "....~~~~~...................^^^..............O.............",
                "~~~~~~~...................,,^^^.~,.........................",
                "~~~~~~..+.............................+....................",
                "~~~~~~~~~..................................................");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        //Add player - Ash
        Player ash = new Player("Ash", '@', 100);
        world.addPlayer(ash, gameMap.at(15, 10));

        Actor charmander = new Charmander();
        gameMap.at(20, 11).addActor(charmander);

        Actor pikachu = new Pikachu(ash);
        gameMap.at(14,10).addActor(pikachu);


        // Pokemon Center
        List<String> pokemonCenterMap= Arrays.asList(

                "##################",
                "#________________#",
                "#______.._.._____#",
                "#________________#",
                "#________________#",
                "########___#######");

        GameMap pokemonCenter = new GameMap(groundFactory, pokemonCenterMap);
        world.addGameMap(pokemonCenter);

        // Add Nurse Joy in the Pokemon Center
        Actor nurseJoy = new NurseJoy();
        pokemonCenter.at(9, 2).addActor(nurseJoy);

        Door door1 = new Door(gameMap.at(27, 5), "to Pallet Town");

        Door door2 = new Door(pokemonCenter.at(9, 5), "to Pokemon Center");

        door2.setDestinationDoor(door1);

        door1.setDestinationDoor(door2);

        gameMap.at(27, 5).setGround(door1);

        pokemonCenter.at(9, 5).setGround(door2);

        world.run();
    }
}
