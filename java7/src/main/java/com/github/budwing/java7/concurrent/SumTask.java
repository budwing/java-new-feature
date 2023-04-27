package com.github.budwing.java7.concurrent;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {
    private int start;
    private int end;
    private int length = 10;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end-start<=length) {
            for (int i=start; i<=end; i++) {
                sum+=i;
            }
        } else {
            int mid = (start+end)/2;
            SumTask left = new SumTask(start, mid);
            SumTask right = new SumTask(mid+1, end);
            left.fork();
            right.fork();
            sum = left.join() + right.join();
        }
        return sum;
    }
}
