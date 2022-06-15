import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Board extends JFrame {
    private final JButton[][] squares = new JButton[7][];
    private int gamesCounter;
    private boolean isWon;

    public Board() {
        this.gamesCounter = 1;

        for(int i = 0; i < Constant.BOARD_WIDTH; ++i) {
            this.squares[i] = new JButton[Constant.BOARD_WIDTH];

            for(int j = 0; j < Constant.BOARD_WIDTH; ++j) {
                JButton square = new JButton();
                if (i == 0) {
                    square.setText(String.valueOf(j + 1));
                    square.setBackground(Color.WHITE);
                    square.setFont(new Font(Constant.FONT_NAME, 1, Constant.FONT_SIZE));


                        square.addActionListener((e) -> {
                            int column = (Integer.parseInt(square.getText())) ;
                            int emptyY = returnEmpty(column);
                            if (emptyY < 7){
                                if (gamesCounter % 2!= 0){
                                    System.out.println("current player- " + 1);
                                    placeSquare(column,emptyY,1);

                                }else {
                                    System.out.println("current player- " + 2);
                                    placeSquare(column,emptyY,2);

                                }
                            }

                            gamesCounter++;

                        });


                } else {
                    square.setEnabled(false);
                }

                this.squares[i][j] = square;
                this.add(square);

            }

        }

        this.setLocationRelativeTo((Component)null);
        GridLayout gridLayout = new GridLayout(Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT);
        this.setLayout(gridLayout);
        this.setSize(Constant.BOARD_HEIGHT * Constant.SQUARE_SIZE, Constant.BOARD_HEIGHT * Constant.SQUARE_SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public int returnEmpty (int column){
        int emptySquareY = 0;
        for (int i = 1; i < squares.length; i++) {
            int currentSquare = getPlayerInSquare(column,i);
            if ( currentSquare == 0){
                return i;
            }

        }
        return emptySquareY;
    }



    public void placeSquare(int x, int y, int player) {
        this.squares[Constant.BOARD_WIDTH - y][x - 1].setBackground(player == 1 ? Color.RED : Color.YELLOW);
    }


    public int getPlayerInSquare(int x, int y) {
        byte player = 0;

        try {
            Color backgroundColor = this.squares[Constant.BOARD_WIDTH - y][x - 1].getBackground();
            if (backgroundColor.equals(Color.RED)) {
                player = 1;
            } else if (backgroundColor.equals(Color.YELLOW)) {
                player = 2;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return player;



    }
}
