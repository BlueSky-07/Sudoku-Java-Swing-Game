/**
 * 数独核心算法，DFS
 */

package Model;

import java.util.Random;

public class Sudoku {
    private Num nums[][]; // 9x9 数独数组
    private Num emptys[]; // 空值集合
    private int empty_cnt; // 空值个数
    private boolean hasAns; // 是否有解
    private double percent; // 难度系数（数独完成度）

    private class Num {
        int row; // 行
        int col; // 列
        int num; // 值
        boolean canEdit; // 可否修改

        Num(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
            this.canEdit = false;
        }

        Num(int row, int col) {
            this.row = row;
            this.col = col;
            this.num = 0;
            this.canEdit = true;
        }
    }

    // 建立数独模型
    public Sudoku(double percent) {
        this.percent = percent;
        next();
    }

    // 用作创建数独备份
    private Sudoku(Num toRead[][]) {
        update(toRead);
    }

    // 初始化
    private void init() {
        nums = new Num[11][11];
        emptys = new Num[81];
        empty_cnt = 0;
        hasAns = false;
    }

    // 随机生成题目，需要传入难度系数，即数独完成度
    public void next() {
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        if (percent > 1 || percent < 0) {
            percent = 0.36;
        }
        int cnt = (int) Math.ceil(percent * 81);
        do {
            init();
            for (int now = 0; now < cnt; now++) {
                int row = rnd.nextInt(9) + 1;
                int col = rnd.nextInt(9) + 1;
                int times = 0;
                if (nums[row][col] == null || nums[row][col].num == 0) {
                    do {
                        nums[row][col] = new Num(row, col, rnd.nextInt(9) + 1);
                        update(nums);
                        times++;
                        if (times == 9) {
                            nums[row][col] = null;
                            update(nums);
                            now--;
                            break;
                        }
                    } while (check(row, col) == false);
                } else {
                    now--;
                }
            }
            setHasAns();
        } while (hasAns == false);
        // consoleOut();
    }

    // 填写答案，需传入0，表示从第一个空的格开始填数
    public boolean solve(int cnt) {
        if (cnt == empty_cnt) {
            return true;
        }
        for (int i = 1; i <= 9; i++) {
            setNum(emptys[cnt].row, emptys[cnt].col, i);
            if (check(emptys[cnt].row, emptys[cnt].col) && solve(cnt + 1)) {
                return true;
            }
        }
        setNum(emptys[cnt].row, emptys[cnt].col, 0);
        return false;
    }

    // 检查指定坐标是否正确
    public boolean check(int row, int col) {
        for (int i = 1; i <= 9; i++) {
            if (i != col && nums[row][col].num == nums[row][i].num) {
                return false;
            }
            if (i != row && nums[row][col].num == nums[i][col].num) {
                return false;
            }
        }
        for (int r = (row - 1) / 3 * 3 + 1; r <= (row + 2) / 3 * 3; r++) {
            for (int c = (col - 1) / 3 * 3 + 1; c <= (col + 2) / 3 * 3; c++) {
                if (r != row && c != col && nums[row][col].num == nums[r][c].num) {
                    return false;
                }
            }
        }
        return true;
    }

    // 读入数据
    private void update(Num toRead[][]) {
        init();
        for (int row = 1; row <= 9; row++) {
            for (int col = 1; col <= 9; col++) {
                nums[row][col] = toRead[row][col];
                if (nums[row][col] == null || nums[row][col].num == 0) {
                    nums[row][col] = new Num(row, col);
                    emptys[empty_cnt] = nums[row][col];
                    empty_cnt++;
                }
            }
        }
    }

    // 获取指定坐标的当前值
    public int getNum(int row, int col) {
        return nums[row][col].num;
    }

    // 获取指定坐标的可修改属性
    public boolean canEdit(int row, int col) {
        return nums[row][col].canEdit;
    }

    // 修改指定坐标的值
    public void setNum(int row, int col, int num) {
        nums[row][col].num = num;
    }

    // 检查数独是否有解，即判断当前数独和它的解是否不同
    private void setHasAns() {
        Sudoku bak = new Sudoku(nums);
        bak.solve(0);
        for (int row = 1; row <= 9; row++) {
            for (int col = 1; col <= 9; col++) {
                if (bak.nums[row][col].num != nums[row][col].num) {
                    hasAns = true;
                    return;
                }
            }
        }
        hasAns = false;
    }

    // 检查是否全部填写并且答案正确
    public boolean isWin() {
        for (int r = 1; r <= 9; r++) {
            for (int c = 1; c <= 9; c++) {
                if (nums[r][c].num == 0 || check(r, c) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    // 控制台输出，调试用
    private void consoleOut() {
        System.out.println("====== NEW ======");
        System.out.println("hasAns : " + hasAns);
        for (int row = 1; row <= 9; row++) {
            System.out.print(getNum(row, 1));
            for (int col = 2; col <= 9; col++) {
                System.out.print(" " + getNum(row, col));
            }
            System.out.println();
        }
        Sudoku bak = new Sudoku(nums);
        bak.solve(0);
        System.out.println("====== ANS ======");
        for (int row = 1; row <= 9; row++) {
            System.out.print(bak.getNum(row, 1));
            for (int col = 2; col <= 9; col++) {
                System.out.print(" " + bak.getNum(row, col));
            }
            System.out.println();
        }
    }
}