package org.example;

import java.time.LocalTime;
import java.util.*;


public class Main {
    static class Request {
        String company;
        String server;

        public Request(String company, String server) {
            this.company = company;
            this.server = server;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Request that = (Request) other;
            return Objects.equals(company, that.company) && Objects.equals(server, that.server);
        }

        @Override
        public int hashCode() {
            return Objects.hash(company, server);
        }
    }

    static class Data {
        Boolean status;
        int count;
        int time;

        public Data() {
            count = 0;
            time = 0;
            status = false;
        }

        public Data add(String status, int time) {
            ++count;
            if (Objects.equals(status, "ACCESSED")) {
                this.time = time;
                this.status = true;
            } else {
                this.time = 0;
                this.status = false;
            }
            return this;
        }

        public int fine() {
            return time + --count * 20;
        }

    }

    static int timeInMinutes(String startTime, String time) {
        LocalTime timeS = LocalTime.parse(startTime);
        LocalTime timeNow = LocalTime.parse(time);
        if (timeNow.isAfter(timeS) || timeNow.equals(timeS))
            return (timeNow.toSecondOfDay() - timeS.toSecondOfDay()) / 60;
        else
            return (timeNow.toSecondOfDay() - timeS.toSecondOfDay() + 86400) / 60;
    }

    static class Res implements Comparable<Res>{
        String company;
        int ball;
        int fine;

        public Res(String company) {
            this.company = company;
            ball = 0;
            fine = 0;
        }

        public Res add(int fine) {
            ++ball;
            this.fine += fine;
            return this;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Res that = (Res) other;
            return Objects.equals(company, that.company);
        }

        @Override
        public int compareTo(Res o) {
            int result = Integer.compare(this.ball, o.ball);
            if (result != 0) {
                return -result;
            }

            result = Integer.compare(this.fine, o.fine);
            if (result != 0) {
                return result;
            }
            if (this.company.length() != o.company.length()) {
                return Integer.compare(this.company.length(), o.company.length());
            }
            return this.company.compareTo(o.company);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String startTime = scanner.nextLine();
        int count = Integer.parseInt(scanner.nextLine());

        Request request;
        HashMap<Request, Data> ctf = new HashMap<>();
        String[] description;
        for (int i = 0; i < count; ++i) {
            description = scanner.nextLine().split(" ");
            if (!Objects.equals(description[3], "PONG")) {
                request = new Request(description[0], description[2]);
                ctf.put(request, ctf.getOrDefault(request
                        , new Data()).add(description[3], timeInMinutes(startTime, description[1])));
            } else
                ctf.putIfAbsent(new Request(description[0], description[2]), new Data());
        }

        HashMap<String, Res> resHashMap = new HashMap<>();
        for (Request r : ctf.keySet()) {
            if (ctf.get(r).status) {
                resHashMap.put(r.company, resHashMap.getOrDefault(r.company
                        , new Res(r.company)).add(ctf.get(r).fine()));
            } else {
                resHashMap.putIfAbsent(r.company, new Res(r.company));
            }
        }

        ArrayList<Res> result = new ArrayList<>(resHashMap.values());
        Collections.sort(result);

        int num = 1;
        for (int i = 0; i < result.size(); ++i) {
            if (i >= num) {
                if (!(num == 1 && result.get(i).ball == result.get(i - 1).ball &&
                        result.get(i).fine == result.get(i - 1).fine)) {
                    num = i + 1;
                }
            }
            System.out.println(num + " " + result.get(i).company + " " + result.get(i).ball + " " + result.get(i).fine);
        }
    }
}