package randomgamewereldenp1b;

import java.util.Random;

/**
 * Class to create random game worlds.
 * 
 * @author MOTIVECODEX
 */
public class RandomGameWorlds {

    public static void main(String[] args) {

        long startTime = System.nanoTime();
        int worlds = 10; // Set world digit on whatever you like.
        int counter = 0;
        int tiles = 0;
        int total = 0;

        for (int t = 0; t < worlds; t++) {
            GameTile[] gameTiles = randomTiles(20, 50);
            for (int i = 0; i < gameTiles.length; i++) {
                int x = gameTiles[i].getX();
                int y = gameTiles[i].getY();

                for (int j = i; j < gameTiles.length; j++) {
                    if (i != j && x == gameTiles[j].getX() && y == gameTiles[j].getY()) {
                        counter++;
                        tiles++;
                    }
                }
                System.out.println("X:" + gameTiles[i].getX() + "\n" + "Y:" + gameTiles[i].getY() + "\n");
            }

            if (counter != 0) {
                total++;
            }
            counter = 0;
        }

        int avg_counter = 0;
        for (int i = 0; i < 1000000; i++) {
            avg_counter += halfWorld();
        }

        int one_counter = 0;
        for (int i = 0; i < 1000000; i++) {
            one_counter += oneRow();
        }

        int e_Tiles = 0;
        for (int i = 0; i < 1; i++) {
            e_Tiles += emptyTiles();
        }

        int match = 0;
        for (int i = 0; i < 1000000; i++) {
            match += firstTile();
        }

        long endTime = System.nanoTime();
        System.out.println("Tiles: " + (tiles / (double) worlds));
        System.out.println("The chance that you will get a world with duplicate tiles will be: " + (total / (double) worlds * 100));
        System.out.println("Bottom half of the world: " + avg_counter / 1000000d);
        System.out.println("Objects on each row: " + one_counter / 1000000d);
        System.out.println("Empty tiles in the world: " + e_Tiles);
        System.out.println("First: " + match / 1000000d);
        System.out.println("Time it took to finish creating worlds: " + (endTime - startTime) / 1000000 + " ms");
    }

    public static int halfWorld() {
        GameTile[] gameTiles = randomTiles(20, 50);
        int t_half = 0;
        for (int i = 0; i < gameTiles.length; i++) {
            int x = gameTiles[i].getX();
            if (x < 10) {
                t_half++;
            }
        }
        return t_half;
    }

    public static int oneRow() {
        GameTile[] gameTiles = randomTiles(20, 50);
        int o_Row = 0;
        for (int i = 0; i < gameTiles.length; i++) {
            int x = gameTiles[i].getX();
            if (x < 1) {
                o_Row++;
            }
        }
        return o_Row;
    }

    public static int emptyTiles() {
        GameTile[] gameTiles = randomTiles(20, 50);
        int e_Tiles = 0;
        int e_Tiles_x = 0;
        int e_Tiles_y = 0;
        for (int i = 0; i < gameTiles.length; i++) {
            if (gameTiles[i].getX() == 0) {
                e_Tiles_x++;
                if (gameTiles[i].getY() == 0) {
                    e_Tiles_y++;
                }
            }
            e_Tiles = e_Tiles_x + e_Tiles_y;
        }
        return e_Tiles;
    }

    public static int firstTile() {
        int match = 0;
        int[] firstObject = new int[20];
        for (int i = 0; i < firstObject.length; i++) {
            firstObject[i] = 20;
        }

        GameTile[] gameTiles = randomTiles(20, 50);
        for (int i = 0; i < gameTiles.length; i++) {
            if (firstObject[gameTiles[i].getX()] > gameTiles[i].getY());
            {
                firstObject[gameTiles[i].getX()] = gameTiles[i].getY();
            }
        }
        int avg = 0;
        for (int i = 0; i < firstObject.length; i++) {
            avg += firstObject[i];
        }
        match += avg / (double) firstObject.length;

        return match;
    }

    public static GameTile[] randomTiles(int maxValue, int maxObjects) {
        GameTile[] gt = new GameTile[maxObjects];

        Random generator = new Random();

        for (int i = 0; i < maxObjects; i++) {
            int x = generator.nextInt(maxValue);
            int y = generator.nextInt(maxValue);

            gt[i] = new GameTile(x, y);
        }
        return gt;
    }
}
