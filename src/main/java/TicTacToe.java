import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static final int SIZE = 5;
    static final int PTS = 4;
    static final char DOT_EMPTY ='.';
    static final char DOT_X = 'X';
    static final char DOT_0 = '0';

    static char[][] map = new char[SIZE][SIZE];
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {

        initMap();
        printMap();
        while(true){
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_0)) {
                System.out.println("Победил искутвенный интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
        /*humanTurn();
        printMap();
        aiTurn();
        printMap();
        */
    }

    private static void aiTurn() {
        int x, y;
        do {
            y = rand.nextInt(SIZE);
            x = rand.nextInt(SIZE);
        }
        while (!isCellValid(x, y));
        map[x][y] = DOT_0;
        System.out.println("Компьютер походил в точку X Y :" + (y+1) + " " + (x+1));
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты Х У для хода:");
            y = scanner.nextInt() - 1;
            x = scanner.nextInt() - 1;
        } while(!isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[x][y] == DOT_EMPTY) return true;
        return false;
    }

    private static void printMap() {
        for (int i = 0; i <= SIZE; i++)
            System.out.print(i+ " ");

        System.out.println();
        for (int i = 0; i < SIZE; i++){
            System.out.print((i+1) + " ");
            for (int j = 0; j < SIZE; j++){
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
    }

    private static void initMap() {
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                map [i][j] = DOT_EMPTY;
            }
        }
    }

    public static boolean checkWin(char symb) {
        int leftDiagCheck = 0;
        int rightDiagCheck = 0;
        for (int i = 0; i < SIZE; i++) {
            int horCheck = 0;
            int vertCheck = 0;
            for (int j = 0; j < SIZE; j++) {

                // проверка по горизонтали
                if (map[i][j] == symb) {
                    horCheck++;
                    if (horCheck == PTS) return true;
                }

                // проверка по вертикали
                if (map[j][i] == symb) {
                    vertCheck++;
                    if (vertCheck == PTS) return true;
                }
            }
            // проверка левой диагонали
            if (map[i][i] == symb) {
                leftDiagCheck++;
                if (leftDiagCheck == PTS) return true;
            }
            //проверка правой диагонали
            if (map[SIZE-1-i][i] == symb){
                rightDiagCheck++;
                if(rightDiagCheck == PTS) return true;
            }
        }
        return false;
    }

    public static boolean isMapFull(){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                if ( map[i][j] == DOT_EMPTY ) return false;
            }
        }
        return true;
    }
}
