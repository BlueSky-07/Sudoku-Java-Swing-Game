/**
 * 3x3 区域块
 */

package View;

import Controller.Game;

import javax.swing.*;
import java.awt.*;

class AreaBlocks extends JPanel {
    private GridLayout grid = new GridLayout(3, 3);
    private int Row;
    private int Col;

    AreaBlocks(int Row, int Col) {
        this.Row = Row;
        this.Col = Col;
        init();
    }

    // 初始化
    private void init() {
        setLayout(grid);
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                // 求得格子的坐标
                int row = Row * 3 + r + 1;
                int col = Col * 3 + c + 1;
                if (Game.sudoku.canEdit(row, col)) {
                    add(new BlockEditor(row, col));
                } else {
                    add(new BlockViewer(row, col));
                }
            }
        }
    }
}
