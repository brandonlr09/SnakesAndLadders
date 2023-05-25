import java.util.*;

public class LadderAndSnake {
    int numberOfPlayers;

    public LadderAndSnake() {
    }

    public LadderAndSnake(int numberOfPlayers) {
        if (isValidNumberOfPlayers(this.numberOfPlayers)) {
            int[][] board = new int[10][10];
            this.numberOfPlayers = numberOfPlayers;
        } else System.out.println("Not a valid number of players");
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        if (isValidNumberOfPlayers(numberOfPlayers)) {
            this.numberOfPlayers = numberOfPlayers;
        } else System.out.println("Not a valid number of players");
    }

    public static void initializePlayers(int numberOfPlayers) {
        Scanner keyboard = new Scanner(System.in);
        int count = 1;
        do {
            System.out.print("Enter player " + count + "'s name: ");
            String name = keyboard.nextLine();
            switch (count) {
                case 1:
                    Player player1 = new Player(name);
                    break;
                case 2:
                    Player player2 = new Player(name);
                    break;
                case 3:
                    Player player3 = new Player(name);
                    break;
                case 4:
                    Player player4 = new Player(name);
                    break;
            }
            count++;
        } while (count <= numberOfPlayers);
    }

    public static int flipDice() {
        return (int) (Math.random() * 6 + 1);
    }

    public static String playerDiceRoll(Player player) {
        player.setDiceRoll(flipDice());
        return "* " + player + " rolled a " + player.getDiceRoll() + "; ";
    }

    public static ArrayList<Player> initializePlayerOrder() {
        ArrayList<Player> playingOrder = new ArrayList<>();

        System.out.println("\nNow deciding which player will start playing;");
        for (Player player : Player.listOfPlayers)
            System.out.println(LadderAndSnake.playerDiceRoll(player).replace(";", ""));

        //SORT BY DICE ROLL WHILE CHECKING FOR ROLL TIES
        for (Player player : Player.listOfPlayers) {
            playingOrder = player.determineOrderOfPlay(player.getDiceRoll());
            //DO NOT CHECK AGAIN AFTER RE-ROLLING TIE IF IT IS TIED WITH OTHER ROLLS
            if (!playingOrder.equals(Player.listOfPlayers)) break;
        }

        System.out.print("Reached final decision on the order of playing: ");
        System.out.println(playingOrder.toString().replace("[", "").replace("]", ""));

        return playingOrder;
    }

    public static ArrayList<Player> initializeGame(LadderAndSnake game, Boolean proceed) {
        ArrayList<Player> playingOrder = new ArrayList<>();
        if (proceed) {
            System.out.println("\nNumber of players = " + game.getNumberOfPlayers() + "\n");
            LadderAndSnake.initializePlayers(game.getNumberOfPlayers());
            playingOrder = LadderAndSnake.initializePlayerOrder();
        }
        return playingOrder;
    }

    public static void play(ArrayList<Player> playingOrder, Boolean proceed) {
        System.out.println("\nGame start!");

        while (proceed) {
            //GAME STARTS
            for (Player player : playingOrder) {
                System.out.print(LadderAndSnake.playerDiceRoll(player));
                Player.moveToNewPosition(player, player.getDiceRoll());

                //CHECK FOR SNAKE OR LADDER
                if (!Board.isSnake(player) && !Board.isLadder(player))
                    System.out.print("now in square " + player.getTileNumber() + "\n");

                //CHECK FOR WINNER
                if (player.getTileNumber() == 100) {
                    System.out.println("" + player + " has won the game!\nThanks for playing!");
                    proceed = false;
                    break;
                }
            }
            if (proceed == true)
                System.out.println("Game is not over; flipping again\n");
        }
    }

    public boolean isValidNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers >= 2 && numberOfPlayers <= 4) return true;
        return false;
    }
}