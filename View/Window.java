/**
 * 窗体
 */

package View;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private GameBody gameBody;
    private ToolBar toolBar;
    static Font GameFont = new Font("Microsoft YaHei", Font.BOLD, 32);
    static Font ToolFont = new Font("Microsoft YaHei", Font.PLAIN, 20);

    public Window() {
        gameBody = new GameBody();
        toolBar = new ToolBar();
        init();
    }

    // 初始化
    private void init() {
        add(gameBody, BorderLayout.CENTER);
        add(toolBar, BorderLayout.PAGE_END);
        setTitle("Sudoku");
        setSize(450, 550);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // 新游戏
    public void next() {
        gameBody.next();
        toolBar.next();
    }

    // 停止计时
    public void stop() {
        gameBody.next();
        toolBar.stop();
    }
}
