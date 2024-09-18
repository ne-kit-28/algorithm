package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String str = scanner.nextLine();
            boolean interval = false;
            StringBuilder buf_start = new StringBuilder();
            StringBuilder buf_end = new StringBuilder();
            for (char ch : str.toCharArray()) {
                if (ch == '-') {
                    interval = true;
                    continue;
                }
                if (ch == ',') {
                    if (interval) {
                        for (int i = Integer.parseInt(buf_start.toString());
                             i <= Integer.parseInt(buf_end.toString()); ++i) {
                            System.out.print(i + " ");
                        }
                        interval = false;
                        buf_start = new StringBuilder();
                        buf_end = new StringBuilder();
                        continue;
                    }
                    System.out.print(buf_start + " ");
                    buf_start = new StringBuilder();
                    continue;
                }
                if (interval)
                    buf_end.append(ch);
                else
                    buf_start.append(ch);
            }
            if (interval) {
                for (int i = Integer.parseInt(buf_start.toString());
                     i < Integer.parseInt(buf_end.toString()); ++i) {
                    System.out.print(i + " ");
                }
                System.out.print(buf_end);
            } else
                System.out.print(buf_start);
        }
        scanner.close();
    }
}