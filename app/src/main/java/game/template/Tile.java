package main.java.game.template;
import java.util.Map;
import game.template.ChessPiece;
import com.google.common.collect.ImmutableMap;

public abstract class Tile {
   final int tileCoordinate;

   private static final Map<Integer, EmptyTile> EmptyTileCache = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
       final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

       for (int i = 0; i < BoardUtil.NumTiles; i++) {
           emptyTileMap.put(i, new EmptyTile(i));
       }
       return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordinate, final ChessPiece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EmptyTileCache.get(tileCoordinate);
    }
    private Tile(final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract ChessPiece getPiece();

    public static final class EmptyTile extends Tile {
        private EmptyTile(int coordinate) {
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public ChessPiece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile {
        private final ChessPiece pieceOnTile;

        private OccupiedTile(int tileCoordinate,final ChessPiece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public ChessPiece getPiece() {
            return this.pieceOnTile;
        }
    }
}
