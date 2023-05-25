import java.util.*;

public class PlayLadderAndSnake {
    public static void main(String[] args) {
        LadderAndSnake game = new LadderAndSnake();
        ArrayList<Player> playingOrder;
        Scanner userInput = new Scanner(System.in);
        int numberOfPlayers = 0;
        int tries = 0;
        String exception;
        boolean proceed = false;

        System.out.println("\nWelcome to Snake & Ladders!\nGame made by Brandon Lessard-Rook\n");

        //ENTER NUMBER OF PLAYERS (TRY LIMIT OF FOUR)
        do {
            if (tries == 3) System.out.print("Last attempt - ");
            System.out.print("Enter number of players (2 to 4 players): ");

            try {
                numberOfPlayers = userInput.nextInt();
            } catch (InputMismatchException e) {
                exception = userInput.nextLine();
                System.out.println("Error: Must be a number");
            }

            if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                tries++;
                System.out.print("Bad attempt " + tries + " - ");
            }

            game.setNumberOfPlayers(numberOfPlayers);
        } while ((numberOfPlayers < 2 || numberOfPlayers > 4) && tries < 4);

        if (tries < 4) proceed = true;

        //INITIALIZE PLAYERS AND THEIR PLAYING ORDER
        playingOrder = LadderAndSnake.initializeGame(game, proceed);

        //PLAY THE GAME
        LadderAndSnake.play(playingOrder, proceed);
    }
}