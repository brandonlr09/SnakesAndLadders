import java.util.*;
import java.util.Collections;

public class Player implements Comparable<Player> {
    private String name;
    private int row, column;
    int tileNumber;
    int diceRoll;
    int playerNumber;
    static int playerCount = 0;
    public static ArrayList<Player> listOfPlayers = new ArrayList<>();

    public Player() {
    }

    public Player(String name) {
        if (!isDuplicateName(name)) {
            this.name = name;
            row = 10;
            column = 0;
            tileNumber = 0;
            diceRoll = 0;
            playerNumber = ++playerCount;
            listOfPlayers.add(this);
        }
    }

    public Player(Player p) {
        this.name = p.name;
        this.row = p.row;
        this.column = p.column;
        tileNumber = p.tileNumber;
    }

    public boolean isDuplicateName(String name) {
        for (Player player : listOfPlayers) {
            if (player.getName().equals(name)) {
                System.out.println("Duplicate name");
                return true;
            }
        }
        return false;
    }

    public ArrayList<Player> determineOrderOfPlay(int diceRoll) {
        ArrayList<Player> playerList = new ArrayList<>();
        ArrayList<Player> ties = new ArrayList<>();
        ties.add(this);

        //COPY ARRAYLIST FROM PLAYER CLASS
        for (Player player : listOfPlayers)
            playerList.add(player);

        //FIND TIES OF this CURRENT PLAYERS DICEROLL IN THE ARRAY
        for (Player player : listOfPlayers)
            if (player.getDiceRoll() == diceRoll && !player.equals(this))
                ties.add(player);

        //AT LEAST ONE TIE IS FOUND
        if (ties.size() >= 2) {

            //REMOVE THE TIES FROM THE COPIED ARRAYLIST OF PLAYERS
            System.out.print("A tie was acheived between " + this);
            playerList.remove(this);
            for (int i = 1; i < ties.size(); i++) {
                System.out.print(" and " + ties.get(i));
                playerList.remove(ties.get(i));
            }

            //REROLL DICE FOR PLAYERS IN TIES
            System.out.println(". Attempting to break the tie.");
            for (Player player : ties)
                System.out.println(LadderAndSnake.playerDiceRoll(player));

            //SORT BOTH ARRAYS
            sortPlayersByDiceRoll(playerList);
            sortPlayersByDiceRoll(ties);

            //ADD THE TIES BACK TO THE ARRAYLIST OF PLAYERS
            for (Player player : ties)
                playerList.add(player);

            //IF THERE IS NO TIES JUST SORT THE ARRAY
        } else if (ties.size() < 2) sortPlayersByDiceRoll(playerList);

        return playerList;
    }

    public void setDiceRoll(int diceRoll) {
        this.diceRoll = diceRoll;
    }

    public static void moveToNewPosition(Player player, int diceRoll) {

        //CHECK IF ON LAST ROW AND if DiceRoll GOES OVER 100 (from 96 to 99)
        if (player.getTileNumber() + diceRoll > 100) {
            int overCap = ((player.getTileNumber() + diceRoll) - 100);
            System.out.println("dice value exceeds the board, moving back " + overCap + " squares");
            player.setTileNumber(100 - overCap);
            player.setRow(1);//row doesnt change (1)
            player.setColumn(11 - getLastDigit(player.getTileNumber()));

            //FIRST ROLL
        } else if (player.getColumn() == 0) {
            player.setColumn(diceRoll);

            //CHECK IF ON ROW END
        } else if (getLastDigit(player.getTileNumber()) == 0) {

            if (Board.isOnUnevenRow(player))
                player.setColumn(diceRoll);
            else
                player.setColumn(11 - diceRoll);
            player.setRow(player.getRow() - 1);

            //CHECK IF ROLL INCREMENTS ROW
        } else if (getLastDigit(player.getTileNumber()) + diceRoll > 10) {

            if (Board.isOnUnevenRow(player)) {
                player.setColumn(diceRoll - (10 - getLastDigit(player.getTileNumber())));
            } else {
                player.setColumn(11 - (diceRoll - (10 - getLastDigit(player.getTileNumber()))));
            }
            player.setRow(player.getRow() - 1);

            //INCREMENT ON THE SAME ROW
        } else {

            if (Board.isOnUnevenRow(player))
                player.setColumn(player.getColumn() - diceRoll);
            else
                player.setColumn(player.getColumn() + diceRoll);
        }
        player.setTileNumber(player.getTileNumber() + diceRoll);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRowAndColumn(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getTileNumber() {
        return tileNumber;
    }

    public void setTileNumber(int tileNumber) {
        this.tileNumber = tileNumber;
    }

    public int getDiceRoll() {
        return diceRoll;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public static int getPlayerCount() {
        return playerCount;
    }

    public static int getLastDigit(int number) {
        return (number % 10);
    }

    public static ArrayList<Player> sortPlayersByDiceRoll(ArrayList<Player> playersList) {
        Collections.sort(playersList);
        Collections.reverse(playersList);
        return playersList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return row == player.row && column == player.column && tileNumber == player.tileNumber && Objects.equals(name, player.name);
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(this.diceRoll, o.getDiceRoll());
    }

    @Override
    public String toString() {
        return "Player " + getPlayerNumber() + " (" + getName() + ")";
    }
}