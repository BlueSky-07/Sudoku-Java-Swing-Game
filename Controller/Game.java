/**
 * 设置游戏难度及开始新游戏
 */

package Controller;

import Model.Sudoku;
import View.*;

public class Game {
    private static Window window;
    public static Sudoku sudoku;

    // 游戏难度，设定一个 0 ~ 1 之间的小数，表示初始数独的完成度
    // 程序保证所有数独一定有至少一个正确答案
    // 完成度越高游戏越简单，对计算机性能要求越高，建议设置最高 0.45
    private static double percent = 0.36;

    // 创建游戏
    private Game() {
        GameTimer.init();
        sudoku = new Sudoku(percent);
        window = new Window();
    }

    // 新游戏
    public static void next() {
        GameTimer.init();
        sudoku.next();
        window.next();
    }

    // 游戏答案
    public static void solve() {
        sudoku.solve(0);
        window.stop();
    }

    // 获取游戏时间
    public static String getTime() {
        String gameTime = GameTimer.getTime();
        GameTimer.updateTime();
        return gameTime;
    }

    public static void main(String[] args) {
        new Game();
    }
}
