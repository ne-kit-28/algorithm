package org.example;

import java.util.*;

public class Main {

    static class Vertex{
        String number;

        Vertex(String number) {
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex vertex = (Vertex) o;
            return Objects.equals(number, vertex.number);
        }

        @Override
        public int hashCode() {
            return number != null ? number.hashCode() : 0;
        }
    }

    static long BFT(ArrayList<String> starts, HashMap<Vertex, ArrayList<Vertex>> map, long[] edgeLength) {
        long maxTime = 0;
        for(String start : starts) {
            long[] lengths = edgeLength.clone();
            Queue<String> queue = new LinkedList<>();
            queue.add(start);
            while (!queue.isEmpty()) {
                String vertex = queue.poll();
                ArrayList<Vertex> list = map.get(new Vertex(vertex));
                if (list != null) {
                    for (Vertex v : list) {
                        if (lengths[Integer.parseInt(v.number)]
                                <= lengths[Integer.parseInt(vertex)] + edgeLength[Integer.parseInt(v.number)]) {
                            lengths[Integer.parseInt(v.number)] =
                                    lengths[Integer.parseInt(vertex)] + edgeLength[Integer.parseInt(v.number)];
                            queue.add(v.number);
                        }
                    }
                }
            }
            long max = Arrays.stream(lengths).max().getAsLong();
            if (max > maxTime) maxTime = max;
        }
        return maxTime;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());

        HashMap<Vertex, ArrayList<Vertex>> map = new HashMap<>();
        long[] edgeLength = new long[count + 1];
        ArrayList<String> start = new ArrayList<>();

        for (int i = 1; i <= count; ++i) {
            String[] parts = scanner.nextLine().split(" ");

            edgeLength[i] = Long.parseLong(parts[0]);
            Vertex child1 = new Vertex(String.valueOf(i));
            for (int j = 1; j < parts.length; ++j) {
                Vertex parent = new Vertex(parts[j]);
                map.putIfAbsent(parent, new ArrayList<>());
                map.get(parent).add(child1);
            }
            if (parts.length == 1) start.add(String.valueOf(i));
        }
        scanner.close();
        System.out.print(BFT(start, map, edgeLength));
    }
}