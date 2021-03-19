package leetcode;

import java.util.*;
// https://leetcode.com/explore/featured/card/march-leetcoding-challenge-2021/590/week-3-march-15th-march-21st/3677/
public class Keys_and_Rooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int start = 0;
        int N = rooms.size();
        boolean[] visit = new boolean[N];
        visit[start] = true;
        int cnt = 1;    // 0 is already opened.
        Queue<Integer> q = new ArrayDeque<>();

        q.add(start);
        while (!q.isEmpty()) {
            int now = q.poll();
            if(rooms.get(now).size() > 0) {
                for (int key: rooms.get(now)) {
                    if(!visit[key]) {
                        visit[key] = true;
                        cnt += 1;
                        q.add(key);
                    }
                }
            }
        }
        if(N == cnt) {
            return true;
        } else {
            return false;
        }
    }
}
