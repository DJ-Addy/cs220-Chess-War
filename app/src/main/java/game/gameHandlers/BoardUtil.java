package gameHandlers;
import java.util.*;

public class BoardUtil {
    public static final int NumTiles = 64;
    public static final int NumTilesPerRow = 8;

    public static final boolean[] FirstColumn = initColumn(0);
    public static final boolean[] SecondColumn = initColumn(1);
    public static final boolean[] SeventhColumn = initColumn(6);
    public static final boolean[] EigthColumn = initColumn(7);
    public static final boolean[] EightRank = initRow(0);
    public static final boolean[] SeventhRank = initRow(8);
    public static final boolean[] SixthRank = initRow(16);
    public static final boolean[] FifthRank = initRow(24);
    public static final boolean[] FourthRank = initRow(32);
    public static final boolean[] ThirdRank = initRow(40);
    public static final boolean[] SecondRank = initRow(48);
    public static final boolean[] FirstRank = initRow(56);

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

    public static int getCoordinateAtPosition(final int x, final int y) {
        return x + NumTilesPerRow * y;
    }

    public static int getXPositionAtCoordinate(final int coordinate) {
        return coordinate % NumTilesPerRow;
    }

    public static int getYPositionAtCoordinate(final int coordinate) {
        return coordinate / NumTilesPerRow;
    }


    public static boolean isValidTile(final int candidateDestinationCoordinate) {
        return candidateDestinationCoordinate >= 0 && candidateDestinationCoordinate < 64;
    }

}
