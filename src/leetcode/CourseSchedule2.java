package leetcode;

import java.util.*;

public class CourseSchedule2 {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] pre = new int[numCourses];
        List<List<Integer>> edges = new ArrayList<>();

        for(int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        for(int[] edge : prerequisites) {
            int req = edge[1];
            int curr = edge[0];

            pre[curr]++;
            edges.get(req).add(curr);
        }

        int[] result = new int[numCourses];

        Queue<Integer> canFinished = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if (pre[i] == 0) canFinished.add(i);
        }

        int finished = 0;
        while(!canFinished.isEmpty()) {
            int currFinishedClass = canFinished.poll();
            result[finished++] = currFinishedClass;
            for(int target : edges.get(currFinishedClass)) {
                pre[target]--;
                if(pre[target] == 0) canFinished.add(target);
            }
        }

        return finished == numCourses ? result : new int[0];
    }

    public static void main(String[] args) {
        int[] result = findOrder(2, new int[][]{{1, 0}});
        Arrays.stream(result).forEach(System.out::println);
    }
}
