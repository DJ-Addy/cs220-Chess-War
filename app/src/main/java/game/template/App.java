/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package template;

import java.net.URL;

import gameHandlers.*;
import gameHandlers.ChessPiece.PieceType;
import playerController.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//extends Application
//need to rebuild the project with tiles, chesspiece, legal moves for each piece, and board
public class App extends Application
{
    private static final int SIZE = 8;
    // this is the size of each square in the chess or checkers board
    // this must be kept in sync with the size of the images, and the
    // size of the squares in the css file
    private static final int SQUARE_SIZE = 70;
    private VBox root;
    private Board ChessBoard = Board.createStandardBoard();
    private final String boardString = ChessBoard.toString();
    //beforeTile to see which piece is being moved
    private Tile beforeTile;
    //afterTile to see where the piece is being moved to
    private Tile afterTile;
    private ChessPiece movedPiece;
    // using StackPane so that they can hold a rectangle and an image
    // we use the rectangle to color the squares
    // and the image to place the pieces
    private StackPane[][] grid = new StackPane[SIZE][SIZE];

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new VBox();

        root.getChildren().add(createMenuBar());

        GridPane gridPane = new GridPane();
        // preferred size of the gridpane
        gridPane.setPrefSize(SQUARE_SIZE * 8, SQUARE_SIZE * 8);
        
        root.getChildren().add(gridPane);

        // loosely based on https://stackoverflow.com/questions/69339314/how-can-i-draw-over-a-gridpane-of-rectangles-with-an-image-javafx
        for (int row = 0; row < SIZE; row++)
        {
            for (int col = 0; col < SIZE; col++)
            {
                Rectangle rect = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);

                if ((row + col) % 2 == 0) { 
                    rect.getStyleClass().add("white-square");
                }
                else {
                    rect.getStyleClass().add("black-square");
                }
                
                StackPane cell = new StackPane(rect);
                
                grid[row][col] = cell;

                // name each cell with its row and column
                // unsure we'll need this
                cell.setId(row + "-" + col);

                // we need to create these extra local final variables
                // I think this has to do with thread safety?
                final int r = row;
                final int c = col;
                // make each cell clickable
                cell.setOnMouseClicked(event -> handleMouseClick(event, r, c));

                //Send out updated board to stackpane

                // finally, put the stackpane into the gridpane
                gridPane.add(cell, col, row);
            }
        }

        // set the scene
        updateBoard(ChessBoard);

        // don't give a width or height to the scene
        // it will figure it out because there's a menu bar
        // plus each square is a fixed size
        Scene scene = new Scene(root);

        // add style information
        URL styleURL = getClass().getResource("/style.css");
        String stylesheet = styleURL.toExternalForm();
        scene.getStylesheets().add(stylesheet);

        // set title and scene and show to the user
        primaryStage.setTitle("CHESS WAR");
        primaryStage.setScene(scene);
        primaryStage.show();

        // handler for when we click the close button
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("oncloserequest");
        });

    }

    private void clearBoard()
    {
        // removes all of the images (pieces) from the board
        for (int row = 0; row < SIZE; row++)
        {
            for (int col = 0; col < SIZE; col++)
            {
                grid[row][col].getChildren().removeIf(child -> child instanceof ImageView);
            }
        }
    }
    public void updateBoard(Board board){
        clearBoard();
        Tilepieces(board);
        System.out.println("Board updated.");
    }

    private void setKeyboardHandler()
    {
        // add this to the root which is a VBox
        root.setOnKeyPressed(event -> {
            System.out.println("Key pressed: " + event.getCode());
            switch (event.getCode())
            {
                // check for the key input
                case ESCAPE:
                    // remove focus from the textfields by giving it to the root VBox
                    root.requestFocus();
                    System.out.println("You pressed ESC key");
                    break;
                case ENTER:
                    System.out.println("You pressed ENTER key");
                    break;
                default:
                    System.out.println("you typed key: " + event.getCode());
                    break;
                
            }
        });
    }
    private void handleMouseClick(MouseEvent event, int row, int col) {
        System.out.println("Mouse clicked on " + row + ", " + col);

        int index = row * BoardUtil.NumTilesPerRow + col;

        if (beforeTile == null) {
            beforeTile = ChessBoard.getTile(index);
            movedPiece = beforeTile.getPiece();
            if (movedPiece == null) {
                beforeTile = null;
            }
        } else {
            afterTile = ChessBoard.getTile(index);
            Move move = Move.MoveFactory.createMove(ChessBoard, beforeTile.getTileCoordinate(), afterTile.getTileCoordinate());
            MoveDriver transition = ChessBoard.currentPlayer().makeMove(move);
            if (transition.getMoveStatus().isDone()) {
                ChessBoard = transition.getTransitionBoard();
                updateBoard(ChessBoard); // Update the board after a successful move
            }
            beforeTile = null;
            afterTile = null;
            movedPiece = null;
        }
    }

    //generate a PlacePieceImage method that will place the pieces on the board, using the Board class, and the PieceType enum, and the Player enum, and the loadImage method
    // this is the method that will be called when we want to place a piece on the board

    private void placePieceImage(Player player, PieceType piece, int piecePosition)
    {
        final int row = piecePosition / 8;
        final int col = piecePosition % 8;
        String imageName = "";
        if (!player.equals(Player.WHITE) && !player.equals(Player.BLACK)) {
            throw new IllegalArgumentException("Player must be either white or black");
        }
        switch(piece.toString()){
            case "P":
                imageName = "pawn.png";
                break;
            case "R":
                imageName = "rook.png";
                break;
            case "N":
                imageName = "knight.png";
                break;
            case "B":
                imageName = "bishop.png";
                break;
            case "Q":
                imageName = "queen.png";
                break;
            case "K":
                imageName = "king.png";
                break;
            default:
                throw new IllegalArgumentException("Piece must be a valid chess piece");
        }
        String pngfile = "";
        if (player == Player.WHITE) {
            pngfile = "w" + imageName;
        } else {
            pngfile = "b" + imageName;
        }
    
        ImageView image = loadImage(pngfile);
        // add the image to the cell
        // each cell is stack pane so that we can "stack" the piece
        // on top of the rectangle for the square
        grid[row][col].getChildren().add(image);
        // not sure if any of this was necessary; commenting it out didn't seem to matter
        image.setFitWidth(SQUARE_SIZE);
        image.setFitHeight(SQUARE_SIZE);
        //image.fitWidthProperty().bind(grid[row][col].widthProperty().subtract(2));
        //image.fitHeightProperty().bind(grid[row][col].heightProperty().subtract(2));
    }

    private void Tilepieces(final Board board){
        clearBoard();
        for(int i = 0; i < BoardUtil.NumTiles; i++){
            if(board.getTile(i).isTileOccupied()){
                PieceType pieceType = board.getTile(i).getPiece().getPieceType();
                placePieceImage(board.getTile(i).getPiece().getPiecePlayer(), pieceType, i);
                
            }
        }

    }

    private ImageView loadImage(String name)
    {
        URL imageUrl = getClass().getResource("/assets/" + name);
    if (imageUrl == null) {
        throw new IllegalArgumentException("Image resource not found: " + name);
    }
    return new ImageView(imageUrl.toExternalForm());
    }

    private MenuBar createMenuBar()
    {
        MenuBar menuBar = new MenuBar();
    	menuBar.getStyleClass().add("menubar");

        //
        // File Menu
        //
    	Menu fileMenu = new Menu("File");

        addMenuItem(fileMenu, "Load from file", () -> {
            System.out.println("Load from file");
        });

        addMenuItem(fileMenu, "Load Board PGN Format", () -> {
            //open that file
            System.out.println("opening from file");
        });

        addMenuItem(fileMenu, "board2", () -> {
            
        });

        menuBar.getMenus().add(fileMenu);

        return menuBar;
    }

    private void addMenuItem(Menu menu, String name, Runnable action)
    {
        MenuItem menuItem = new MenuItem(name);
        menuItem.setOnAction(event -> action.run());
        menu.getItems().add(menuItem);
    }


    public static void main(String[] args) 
    {
        launch(args);
    }

}

