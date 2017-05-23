/**
 * 可编辑格
 */

package View;

import Controller.Game;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

class BlockEditor extends JTextField {
    BlockEditor(int row, int col) {
        if (Game.sudoku.getNum(row, col) != 0) {
            setText(String.valueOf(Game.sudoku.getNum(row, col)));
        }
        setFont(Window.GameFont);
        setBorder(BorderFactory.createLineBorder(Color.white, 1));
        setRightColor();
        setHorizontalAlignment(JTextField.CENTER);
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
//                System.out.println("in : function_insertUpdate");
                if (getDocument().getLength() > 1) {
                    Game.sudoku.setNum(row, col, 0);
                    setWrongColor();
                } else {
                    changedUpdate(e);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
//                System.out.println("in : function_removeUpdate");
                if (getDocument().getLength() == 0) {
                    Game.sudoku.setNum(row, col, 0);
                    setRightColor();
                } else {
                    changedUpdate(e);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
//                System.out.println("in : function_changedUpdate");
                try {
                    int value = Integer.valueOf(getDocument().getText(0, 1));
                    Game.sudoku.setNum(row, col, value);
                    if (Game.sudoku.check(row, col)) {
                        setRightColor();
                    } else {
                        throw new Exception();
                    }
                } catch (Exception err) {
                    Game.sudoku.setNum(row, col, 0);
                    setWrongColor();
                    return;
                }
                if (Game.sudoku.isWin()) {
                    Game.solve();
                    int choice = JOptionPane.showConfirmDialog(null, "your time: " + Game.getTime() + ", new game?", "Sudoku | Completed", JOptionPane.DEFAULT_OPTION);
                    if (choice == 0) {
                        Game.next();
                    }
                }
            }
        });
    }

    // 正确配色
    private void setRightColor() {
        setForeground(Color.yellow);
        setBackground(Color.black);
    }

    // 错误配色
    private void setWrongColor() {
        setForeground(Color.white);
        setBackground(Color.red);
    }
}