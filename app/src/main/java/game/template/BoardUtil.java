package main.java.game.template;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class BoardUtil {

    private BoardUtil() {
        throw new RuntimeException("You cannot instantiate me!");
    }

    public static final int NumTiles = 64;
    public static final int NumTilesPerRow = 8;

    private static boolean[] initColumn(int columnIndex) {
        final boolean[] column = new boolean[NumTiles];
            
        int i = columnIndex;
        while (i < NumTiles) {
            column[i] = true;
            i += NumTilesPerRow;
            }            
            //print statement here
           System.out.println("Column initialized: " + Arrays.toString(column));
        return column;
        }

    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);
    public static final boolean[] SECOND_ROW = null;
    public static final boolean[] SEVENTH_ROW = null;


    public static boolean isValidTile(final int candidateDestinationCoordinate) {
        return candidateDestinationCoordinate >= 0 && candidateDestinationCoordinate < 64;
    }

}
