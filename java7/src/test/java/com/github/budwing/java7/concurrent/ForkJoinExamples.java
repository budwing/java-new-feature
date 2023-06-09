package com.github.budwing.java7.concurrent;

import com.github.budwing.java7.permutation.sudoku.SudokuPermutationProcessor;
import com.github.budwing.java7.permutation.Permutation;
import com.github.budwing.java7.permutation.PrintProcessor;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.ForkJoinPool;

@Slf4j
public class ForkJoinExamples {
    @Test
    public void countTask() {
        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(1, 100);
        long start = System.nanoTime();
        pool.submit(task);
        log.info("result is {}, time: {}", task.join(), System.nanoTime()-start);
    }

    @Test
    public void count() {
        int sum = 0;
        long start = System.nanoTime();
        for (int i = 1; i <= 100; i++) {
            sum+=i;
        }
        log.info("result is {}, time: {}", sum, System.nanoTime()-start);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //// try yourselves
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * TODO: try to list all the files under a directory by using Fork/Join framework
     */
    @Test
    public void tryTransverseFiles() {
        Assert.fail("try to list all the files under a directory by using Fork/Join framework");
    }

    //@Test
    public void permuteWithSudoku() {
        Integer a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Permutation<Integer> p = new Permutation<Integer>(new SudokuPermutationProcessor());
        p.permute(a);
    }

    //@Test
    public void permuteWithPrint() {
        /*Character[] words = {'0','1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
				'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};*/
        Character[] words = {'0','1','2','3','4','5','6','7','8','9'};
        Permutation<Character> p = new Permutation<Character>(new PrintProcessor(true));
        long start = System.currentTimeMillis();
        p.simpleCombine(words, 5);
        log.info("time: {}", System.currentTimeMillis()-start);
    }
}
