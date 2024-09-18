package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static boolean[] resh() {
        int max = 10000000;
        boolean[] simples = new boolean[max + 1];
        for (int i = 2; i <= max; ++i)
            simples[i] = true;
        for (int i = 2; i * i <= max; i++)
            if (simples[i])
                for (int j = i * i; j <= max; j += i)
                    simples[j] = false;
        return simples;
    }

    static int countNums(long l, long r) {
        boolean[] simple = resh();
        int count = 0;

        ArrayList<Integer> simpleList = new ArrayList<>();
        for (int i = 2; i <= 10000000; i++)
            if (simple[i])
                simpleList.add(i);
        
        for (int p : simpleList) {
            long p_ = (long) p * (long) p;

            for (int a = 2; p_ <= r; a++) {
                if (p_ >= l && simple[a + 1])
                    ++count;
                if (p_ > r / (long) p)
                    break;
                p_ *= p;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] enter = scanner.nextLine().split(" ");
        scanner.close();

        System.out.println(countNums(Long.parseLong(enter[0]), Long.parseLong(enter[1])));
    }
}