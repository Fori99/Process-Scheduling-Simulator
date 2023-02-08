import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        int min, max;
        int quantum = 5;

        System.out.print("Enter the number of processes: ");
        n = sc.nextInt();

        int[][] processes = new int[n][3];

        /*System.out.println("Enter the burst time of each process: ");
        for (int i = 0; i < n; i++) {
            processes[i][0] = i + 1;
            processes[i][1] = sc.nextInt();
        }*/

        System.out.print("Enter the minimum burst time: ");
        min = sc.nextInt();
        System.out.print("Enter the maximum burst time: ");
        max = sc.nextInt();

        Random random = new Random();
        for (int i = 0; i < n; i++) {
            processes[i][0] = i + 1;
            processes[i][1] = random.nextInt(max - min + 1) + min;
            processes[i][2] = i;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] remainingTime = new int[n];
        for (int i = 0; i < n; i++) {
            remainingTime[i] = processes[i][1];
            queue.add(i);
        }

        int time = 0;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            int burst = Math.min(quantum, remainingTime[i]);
            remainingTime[i] -= burst;
            time += burst;

            if (remainingTime[i] > 0) {
                queue.add(i);
            } else {
                turnaroundTime[i] = time;
                waitingTime[i] = turnaroundTime[i] - processes[i][1];
            }
        }

        double avgWaitingTime = 0;
        double avgTurnaroundTime = 0;
        for (int i = 0; i < n; i++) {
            avgWaitingTime += waitingTime[i];
            avgTurnaroundTime += turnaroundTime[i];
        }
        avgWaitingTime /= n;
        avgTurnaroundTime /= n;

        System.out.println("\nProcess ID\t Burst Time\t Waiting Time\t Turnaround Time");
        System.out.println("-------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.println("   " + processes[i][0] + "\t\t     " + processes[i][1] + "\t\t     " + waitingTime[i] + "\t\t\t    " + turnaroundTime[i]);
        }

        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);

        sc.close();
    }
}
