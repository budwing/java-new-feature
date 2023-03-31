package com.github.budwing.java.permutation.sudoku;

import com.github.budwing.java.permutation.PermutationProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SudokuPermutationProcessor implements PermutationProcessor {
    public static int count;

    public Object process(Object[] a) {
        Integer[] tem;
        if (a instanceof Integer[]) {
            tem = (Integer[]) a;
        } else {
            tem = new Integer[9];
            for (int i = 0; i < tem.length; i++) {
                tem[i] = new Integer(a[i].toString());
            }
        }

        int sum = tem[0] + tem[1] + tem[2];
        boolean result = tem[3] + tem[4] + tem[5] == sum &&
                tem[6] + tem[7] + tem[8] == sum &&
                tem[0] + tem[3] + tem[6] == sum &&
                tem[1] + tem[4] + tem[7] == sum &&
                tem[2] + tem[5] + tem[8] == sum &&
                tem[0] + tem[4] + tem[8] == sum &&
                tem[2] + tem[4] + tem[6] == sum;

        if (result) {
            count++;
            log.info("solution of sudoku no.{}:", count);
            for (int i = 0; i < tem.length; i++) {
                System.out.print(tem[i] + "  ");
                if ((i + 1) % 3 == 0) {
                    System.out.println();
                }
            }
        }

        return null;
    }

}
