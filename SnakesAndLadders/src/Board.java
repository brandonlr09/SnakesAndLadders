public class Board {

    public Board() {
    }

    public static boolean isOnUnevenRow(Player player) {
        if (player.getRow() % 2 != 0) return true;
        return false;
    }

    public static boolean isLadder(Player player) {
        //LADDER STARTS: 1-4-9-21-28-36-51-71-80
        Player positionAfterFirstMove = new Player(player);
        switch (player.getRow()) {
            case 3:
                switch (player.getColumn()) {
                    case 1:
                        player.setRowAndColumn(1, 1);
                        player.setTileNumber(100);
                        break;
                    case 10:
                        player.setRowAndColumn(1, 10);
                        player.setTileNumber(91);
                        break;
                }
                break;
            case 5:
                if (player.getColumn() == 10) {
                    player.setRowAndColumn(4, 7);
                    player.setTileNumber(67);
                }
                break;
            case 7:
                if (player.getColumn() == 5) {
                    player.setRowAndColumn(6, 4);
                    player.setTileNumber(44);
                }
                break;
            case 8:
                switch (player.getColumn()) {
                    case 1:
                        player.setRowAndColumn(6, 2);
                        player.setTileNumber(42);
                        break;
                    case 8:
                        player.setRowAndColumn(2, 4);
                        player.setTileNumber(84);
                        break;
                }
                break;
            case 10:
                switch (player.getColumn()) {
                    case 1:
                        player.setRowAndColumn(7, 3);
                        player.setTileNumber(38);
                        break;
                    case 4:
                        player.setRowAndColumn(9, 7);
                        player.setTileNumber(14);
                        break;
                    case 9:
                        player.setRowAndColumn(7, 10);
                        player.setTileNumber(31);
                        break;
                }
                break;
        }
        if (!player.equals(positionAfterFirstMove)) {
            System.out.println("went to square " + positionAfterFirstMove.getTileNumber()
                    + " then up to square " + player.getTileNumber());
            return true;
        }

        return false;
    }

    public static boolean isSnake(Player player) {
        //SNAKE STARTS: 16-48-64-79-93-95-97-98
        Player positionAfterFirstMove = new Player(player);
        switch (player.getRow()) {
            case 1:
                switch (player.getColumn()) {
                    case 3:
                        player.setRowAndColumn(3, 3);
                        player.setTileNumber(78);
                        break;
                    case 4:
                        player.setRowAndColumn(3, 5);
                        player.setTileNumber(76);
                        break;
                    case 6:
                        player.setRowAndColumn(8, 4);
                        player.setTileNumber(24);
                        break;
                    case 8:
                        player.setRowAndColumn(4, 8);
                        player.setTileNumber(68);
                        break;
                }
                break;
            case 3:
                if (player.getColumn() == 2) {
                    player.setRowAndColumn(9, 2);
                    player.setTileNumber(19);
                }
                break;
            case 4:
                if (player.getColumn() == 4) {
                    player.setRowAndColumn(5, 1);
                    player.setTileNumber(60);
                }
                break;
            case 6:
                if (player.getColumn() == 8) {
                    player.setRowAndColumn(8, 10);
                    player.setTileNumber(30);
                }
                break;
            case 9:
                if (player.getColumn() == 5) {
                    player.setRowAndColumn(10, 6);
                    player.setTileNumber(6);
                }
                break;
        }
        if (!player.equals(positionAfterFirstMove)) {
            System.out.println("went to square " + positionAfterFirstMove.getTileNumber()
                    + " then down to square " + player.getTileNumber());
            return true;
        }

        return false;
    }
}