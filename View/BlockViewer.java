/**
 * 不可编辑格
 */

package View;

import Controller.Game;

import javax.swing.*;
import java.awt.*;

class BlockViewer extends JLabel {
    BlockViewer(int row, int col) {
        super(String.valueOf(Game.sudoku.getNum(row, col)), JLabel.CENTER);
        setFont(Window.GameFont);
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
        setForeground(Color.black);
    }
}
