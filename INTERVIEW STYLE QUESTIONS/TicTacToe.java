import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Deque;
import java.util.Scanner;



enum PieceType{
    X,
    O
}

class PlayingPiece{
    private PieceType pieceType;

    public PlayingPiece(PieceType pieceType){
        this.pieceType = pieceType;
    }

    public PieceType getPieceType(){
        return pieceType;
    }
}

class PlayingPieceX extends PlayingPiece{
    public PlayingPieceX(){
        super(PieceType.X);
    }
}

class PlayingPieceO extends PlayingPiece{
    public PlayingPieceO(){
        super(PieceType.O);
    }
}

class Player{
    private String name;
    private PlayingPiece playingPiece;

    public Player(String name, PlayingPiece playingPiece){
        this.name=name;
        this.playingPiece=playingPiece;
    }

    public String getName(){
        return this.name;
    }

    public PlayingPiece getPlayingPiece(){
        return this.playingPiece;
    }
}

class Cell{
    private int row;
    private int col;

    public Cell(int row, int col){
        this.row=row;
        this.col=col;
    }

    public int getRow(){ return this.row; }
    public int getCol(){ return this.col; }
}


class Board{
    private int size;
    private PlayingPiece[][] board;

    public Board(int size){
        this.size = size;
        this.board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int col, PlayingPiece playingPiece){
        if(row<0 || row>=size || col<0 || col>= size) return false;
        if(board[row][col] != null) return false;
        board[row][col] = playingPiece;
        return true;
    }

    public List<Cell> getAvailableCells(){
        List<Cell> availableCells = new ArrayList<>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j] == null) availableCells.add(new Cell(i,j));
            }
        }
    return availableCells;
    }

    public void printBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j] != null) System.out.print(board[i][j].getPieceType().name() + " ");
                else System.out.print("  ");
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public int getSize(){ return this.size; }
    public PlayingPiece[][] getBoard(){ return this.board; }
    public PlayingPiece getPiece(int row, int column) {
        return board[row][column];
    }
}

class TicTacToe{
    private Deque<Player> players;
    private Board gameBoard;
    private Scanner scanner; 

    public TicTacToe(List<Player> totalPlayers, int size){
        this.players = new LinkedList<>();
        this.players.addAll(totalPlayers);
        this.gameBoard = new Board(size);
        this.scanner = new Scanner(System.in);
    }

    public String startGame(){
        boolean noWinner = true;

        while(noWinner){
            Player currentPlayer = players.removeFirst();
            gameBoard.printBoard();
            if(gameBoard.getAvailableCells().size() == 0) break;
            System.out.print("Hey, " + currentPlayer.getName() + " (" + currentPlayer.getPlayingPiece().getPieceType() + ") , " + "Please enter the row,col: ");
            String input = scanner.nextLine();
            String[] values = input.split(",");
            int row;
            int col;
            try{
                row = Integer.parseInt(values[0].trim());
                col = Integer.parseInt(values[1].trim());
            }
            catch(Exception e){
                System.out.println("Invalid entry of row,col. Please enter the format correctly ! ");
                players.addFirst(currentPlayer);
                continue;
            }

            boolean addedSuccessfully = gameBoard.addPiece(row, col, currentPlayer.getPlayingPiece());
            if(!addedSuccessfully){
                System.out.println("Incorrect position chosen, try again.");
                players.addFirst(currentPlayer);
                continue;
            }
            players.addLast(currentPlayer);
            boolean winner = isThereWinner(row,col,currentPlayer.getPlayingPiece().getPieceType());
            if(winner){
                gameBoard.printBoard();
                return "Hey " + currentPlayer.getName() + ". You did it. Congratulations !! 🥳✌️";
            }
        }
         System.out.println("Game is done !!");
         gameBoard.printBoard();
        return "It's a tie. Well Played";
    }

    public boolean isThereWinner(int row, int col, PieceType pieceType){
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagMatch = true;
        boolean revDiagMatch = true;

        for(int i=0;i<gameBoard.getSize();i++){
                if(gameBoard.getPiece(i,col) == null ||  gameBoard.getPiece(i,col).getPieceType() != pieceType) colMatch = false;
                if(gameBoard.getPiece(row,i) == null ||  gameBoard.getPiece(row,i).getPieceType() != pieceType) rowMatch = false;
                if(gameBoard.getPiece(i,i) == null || gameBoard.getPiece(i,i).getPieceType() != pieceType) diagMatch = false;
                if(gameBoard.getPiece(i,gameBoard.getSize()-1-i) == null || gameBoard.getPiece(i,gameBoard.getSize()-1-i).getPieceType() != pieceType) revDiagMatch = false;
        }
        return rowMatch || colMatch || diagMatch || revDiagMatch;
    }
}

public class TicTacToeGame{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Player1 name: ");
        String str1 = sc.nextLine();
        System.out.print("Enter Player2 name: ");
        String str2 = sc.nextLine();
        PlayingPiece xPiece = new PlayingPieceX();
        PlayingPiece oPiece = new PlayingPieceO();
        Player player1 = new Player(str1, xPiece);
        Player player2 = new Player(str2, oPiece);
        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        TicTacToe ticTacToe = new TicTacToe(playerList, 3);
        String result = ticTacToe.startGame();
        System.out.println(result);

    }
}
