package gameHandlers;
import java.util.*;

public class BoardUtil {
    public static final int NumTiles = 64;
    public static final int NumTilesPerRow = 8;

    public static final boolean[] FirstColumn = initColumn(0);
    public static final boolean[] SecondColumn = initColumn(1);
    public static final boolean[] SeventhColumn = initColumn(6);
    public static final boolean[] EigthColumn = initColumn(7);
    public static final boolean[] SecondRow = initRow(8);
    public static final boolean[] SeventhRow = initRow(48);

    private BoardUtil() {
        throw new RuntimeException("You cannot instantiate me!");
    }


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

    private static boolean[] initRow(int rowNumber) {
        final boolean[] row = new boolean[NumTiles];
       while(rowNumber % NumTilesPerRow != 0) {
            row[rowNumber] = true;
            rowNumber++;
        }
        //print statement here
        System.out.println("Row initialized: " + Arrays.toString(row));
        return row;
    }


    public static boolean isValidTile(final int candidateDestinationCoordinate) {
        return candidateDestinationCoordinate >= 0 && candidateDestinationCoordinate < 64;
    }

}
