package Z_LLD6_DesignTicTacToe.Model;

import Z_LLD6_DesignTicTacToe.Exceptions.DuplicateSymbolException;
import Z_LLD6_DesignTicTacToe.Exceptions.MoreThanOneBotException;
import Z_LLD6_DesignTicTacToe.Exceptions.PlayerCountDimensionMismatchException;
import Z_LLD6_DesignTicTacToe.Strategies.Winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        this.nextMovePlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimension);
    }


    public static Builder getBuilder(){
        return new Builder();
    }
    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        private Builder(){
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.dimension = 0;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        public Builder addPlayer(Player player){
            this.players.add(player);
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder addWinningStrategy(WinningStrategy winningStrategy){
            winningStrategies.add(winningStrategy);
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        private void validateBotCounts() throws MoreThanOneBotException {
            int botCount = 0;
            for(Player player: players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount > 1){
                throw new MoreThanOneBotException();
            }
        }
        private void validateDimensionPlayersCount() throws PlayerCountDimensionMismatchException {
            if(players.size() != dimension -1){
                throw new PlayerCountDimensionMismatchException();
            }
        }
        private void validateUniqueSymbolsForPlayers() throws DuplicateSymbolException {
            Map<Character, Integer> symbolCount = new HashMap<>();
            for(Player player: players){
                if(!symbolCount.containsKey(player.getSymbol().getaChar())){
                    symbolCount.put(player.getSymbol().getaChar(), 1);
                } else{
                    throw new DuplicateSymbolException();
                }
            }
        }

        private void validate() throws MoreThanOneBotException, PlayerCountDimensionMismatchException, DuplicateSymbolException {
            validateBotCounts();
            validateDimensionPlayersCount();
            validateUniqueSymbolsForPlayers();
        }

        public Game build() throws DuplicateSymbolException, PlayerCountDimensionMismatchException, MoreThanOneBotException {
            validate();
            return new Game(dimension, players, winningStrategies);
        }
    }

    public void undo(){
        if(moves.size() == 0){
            System.out.println("No move to undo");
            return;
        }
        Move lastMove = moves.remove(moves.size()-1);

        Cell cell = lastMove.getCell();

        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);

        for(WinningStrategy winningStrategy: winningStrategies){
            winningStrategy.handleUndo(board, lastMove);
        }

        nextMovePlayerIndex -= 1;
        nextMovePlayerIndex = (nextMovePlayerIndex + players.size()) % players.size();

    }

    public void printBoard(){
        board.printBoard();
    }

    private boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row >= board.getBoard().size() || row < 0){
            return false;
        }
        if(col >= board.getBoard().get(0).size() || col < 0){
            return false;
        }

        if(!board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return false;
        }
        return true;
    }

    private boolean checkWinner(Move move){
        for(WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(board, move)){
                return true;
            }
        }
        return false;
    }


    public void makeMove(){
        Player currMovePlayer = players.get(nextMovePlayerIndex);
        System.out.println("This is " + currMovePlayer.getName() + "'s turn to make a move");
        Move move = currMovePlayer.makeMove(board);

        System.out.println(
                currMovePlayer.getName() + " has made a move at row: " + move.getCell().getRow() +
                        " and column: " + move.getCell().getCol()
        );

        if(!validateMove(move)){
            System.out.println("Invalid move, please try again.");
            return;
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setPlayer(currMovePlayer);
        cellToChange.setCellState(CellState.FILLED);

        Move finalMoveObject = new Move(cellToChange, currMovePlayer);
        moves.add(finalMoveObject);

        nextMovePlayerIndex += 1;
        nextMovePlayerIndex %= players.size();

        if(checkWinner(finalMoveObject)){
            gameState = GameState.WIN;
            winner = currMovePlayer;
            return;
        }

        if(moves.size() == (this.board.getSize() * this.board.getSize())){
            gameState = GameState.DRAW;
            return;
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }
}
