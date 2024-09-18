package org.example;

import java.util.HashMap;
import java.util.Scanner;


public class Main {
    public static String findRightmostSubstring(String s, String chars, int k) {
        HashMap<Character, Integer> requiredCharCount = new HashMap<>();
        for (char c : chars.toCharArray()) {
            requiredCharCount.put(c, requiredCharCount.getOrDefault(c, 0) + 1);
        }

        int left = s.length() - k, right = s.length() - 1;
        HashMap<Character, Integer> windowCounts = new HashMap<>();
        int iter = right;

        while (right >= chars.length() - 1) {
            while (iter >= left && right >= chars.length() - 1 && iter >= 0) {
                if (requiredCharCount.containsKey(s.charAt(iter))) {
                    windowCounts.put(s.charAt(iter), windowCounts.getOrDefault(s.charAt(iter), 0) + 1);
                } else {
                    right = iter - 1;
                    left = right - k + 1;
                    --iter;
                    windowCounts = new HashMap<>();
                    continue;
                }
                if (windowCounts.size() == chars.length()) {
                    return s.substring(iter,right + 1);
                }
                --iter;
            }
            if (!windowCounts.isEmpty()) {
                if (windowCounts.get(s.charAt(right)) == 1) windowCounts.remove(s.charAt(right));
                else windowCounts.replace(s.charAt(right), windowCounts.get(s.charAt(right)) - 1);
            }
            --right;
            --left;
        }
        return "-1";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String chars = scanner.nextLine();
        int k = Integer.parseInt(scanner.nextLine());
        scanner.close();

        String result = findRightmostSubstring(str, chars, k);
        System.out.println(result);
    }
}