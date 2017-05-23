/**
 * 工具栏
 */

package View;

import Controller.Game;

import javax.swing.*;

class ToolBar extends JPanel {
    private ToolTimer toolTimer;

    ToolBar() {
        init();
    }

    // 初始化
    private void init() {
        // 左下角版权
        JLabel copyright = new JLabel("@ BlueSky");
        copyright.setFont(Window.ToolFont);
        add(copyright);
        // 新游戏按钮
        JButton btn_next = new JButton("New");
        btn_next.setFont(Window.ToolFont);
        btn_next.addActionListener(e -> {
            Game.next();
        });
        add(btn_next);
        // 显示答案按钮
        JButton btn_solve = new JButton("Answer");
        btn_solve.setFont(Window.ToolFont);
        btn_solve.addActionListener(e -> {
            Game.solve();
        });
        add(btn_solve);
        // 游戏时间
        toolTimer = new ToolTimer();
        toolTimer.init();
        add(toolTimer);
    }

    // 新游戏
    void next() {
        stop();
        toolTimer.init();
    }

    // 停止计时
    void stop() {
        toolTimer.stop();
    }
}

// 工具栏游戏时间
class ToolTimer extends JLabel {
    private Timer timer;

    ToolTimer() {
        timer = new Timer(1000, e -> {
            setText(Game.getTime());
        });
        setFont(Window.ToolFont);
    }

    // 初始化
    void init() {
        timer.start();
        setText(Game.getTime());
    }

    // 停止计时
    void stop() {
        timer.stop();
    }
}