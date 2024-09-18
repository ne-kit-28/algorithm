package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        String[] snowStr = scanner.nextLine().split(" ");
        scanner.close();
        int[] snow = new int[count + 2];
        for (int i = 1; i < snowStr.length + 1; i++) {
            snow[i] = Integer.parseInt(snowStr[i - 1]);
        }
        snow[0] = 0;
        snow[count + 1] = -1;
        snow[count + 1] = count + 2 + Arrays.stream(snow).max().getAsInt();

        int[] inDay = new int[count];

        for (int i = 1; i < count + 1; ++i) {
            if (snow[i] == -1) {
                inDay[i - 1] = 1;
                snow[i] = snow[i - 1] + 1;
            } else
                inDay[i - 1] = snow[i] - snow[i - 1];
        }
        if (Arrays.stream(inDay).min().getAsInt() > 0) {
            System.out.println("YES");
            for (int i = 0; i < count - 1; ++i) {
                System.out.print(inDay[i] + " ");
            }
            System.out.print(inDay[count - 1]);
        } else System.out.println("NO");
    }
}