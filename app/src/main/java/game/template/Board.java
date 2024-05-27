package main.java.game.template;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableList;
public class Board {
    
    private final List<Tile> gameBoard;

    private Board(final Builder builder) {
        this.gameBoard = createGameBoard(builder);
        this.whitePlayer = builder.whitePlayer;
        this.blackPlayer = builder.blackPlayer;
        final List<ChessPiece> whitePieces = calculateActivePieces(this.gameBoard, Player.WHITE);
        final List<ChessPiece> blackPieces = calculateActivePieces(this.gameBoard, Player.BLACK);
    }

    public Tile getTile(final int tileCoordinate) {
        return null;
    }

    public static Board createStandardBoard(){

        final Builder builder = new Builder();
        //Black Layout
        builder.setPiece(new RookHandler(0, Player.BLACK));
        builder.setPiece(new KnightHandler(1, Player.BLACK));
        builder.setPiece(new BishopHandler(2, Player.BLACK));
        builder.setPiece(new QueenHandler(3, Player.BLACK));
        builder.setPiece(new KingHandler(4, Player.BLACK));
        builder.setPiece(new BishopHandler(5, Player.BLACK));
        builder.setPiece(new KnightHandler(6, Player.BLACK));
        builder.setPiece(new RookHandler(7, Player.BLACK));
        builder.setPiece(new PawnHandler(8, Player.BLACK));
        builder.setPiece(new PawnHandler(9, Player.BLACK));
        builder.setPiece(new PawnHandler(10, Player.BLACK));
        builder.setPiece(new PawnHandler(11, Player.BLACK));
        builder.setPiece(new PawnHandler(12, Player.BLACK));
        builder.setPiece(new PawnHandler(13, Player.BLACK));
        builder.setPiece(new PawnHandler(14, Player.BLACK));
        builder.setPiece(new PawnHandler(15, Player.BLACK));
        //White Layout
        builder.setPiece(new RookHandler(63, Player.WHITE));
        builder.setPiece(new KnightHandler(62, Player.WHITE));
        builder.setPiece(new BishopHandler(61, Player.WHITE));
        builder.setPiece(new QueenHandler(60, Player.WHITE));
        builder.setPiece(new KingHandler(59, Player.WHITE));
        builder.setPiece(new BishopHandler(58, Player.WHITE));
        builder.setPiece(new KnightHandler(57, Player.WHITE));
        builder.setPiece(new RookHandler(56, Player.WHITE));
        builder.setPiece(new PawnHandler(55, Player.WHITE));
        builder.setPiece(new PawnHandler(54, Player.WHITE));
        builder.setPiece(new PawnHandler(53, Player.WHITE));
        builder.setPiece(new PawnHandler(52, Player.WHITE));
        builder.setPiece(new PawnHandler(51, Player.WHITE));
        builder.setPiece(new PawnHandler(50, Player.WHITE));
        builder.setPiece(new PawnHandler(49, Player.WHITE));
        builder.setPiece(new PawnHandler(48, Player.WHITE));
        //white to move
        builder.setMoveMaker(Player.WHITE);
        //build the board
        return builder.build();
    }

    private static List<Tiles> createGameBoard(final Builder builder) {
        final Tile[] tiles = new Tile[BoardUtil.NumTiles];
        for (int i = 0; i < BoardUtil.NumTiles; i++) {

            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public static class Builder {
        public Builder setPiece(final ChessPiece piece) {
            return this;
        }
        Map<Integer, ChessPiece> boardConfig;
        
        Player nextMoveMaker;
        Public Builder(){

        }

        public Builder setPiece(final Player piece) {
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }
        //Setting the next move maker
        public Builder setMoveMaker(final Player nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }
        
        public Board build() {
            return new Board(this);
        }

    }


}
