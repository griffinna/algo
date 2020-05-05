package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * 달리기
 *
 * https://www.acmicpc.net/problem/2517
 *
 * 문제
 * KOI 장거리 달리기 대회가 진행되어 모든 선수가 반환점을 넘었다.
 * 각 선수의 입장에서 자기보다 앞에 달리고 있는 선수들 중 평소 실력이 자기보다 좋은 선수를 남은 거리 동안 앞지르는 것은 불가능하다.
 * 반대로, 평소 실력이 자기보다 좋지 않은 선수가 앞에 달리고 있으면 남은 거리 동안 앞지르는 것이 가능하다.
 * 이러한 가정 하에서 각 선수는 자신이 앞으로 얻을 수 있는 최선의 등수를 알 수 있다.
 *
 * 각 선수의 평소 실력은 정수로 주어지는데 더 큰 값이 더 좋은 실력을 의미한다.
 * 현재 달리고 있는 선수를 앞에서 부터 표시했을 때 평소 실력이 각각 2, 8, 10, 7, 1, 9, 4, 15라고 하면
 * 각 선수가 얻을 수 있는 최선의 등수는 (같은 순서로) 각각 1, 1, 1, 3, 5, 2, 5, 1이 된다.
 * 예를 들어, 4번째로 달리고 있는 평소 실력이 7인 선수는 그 앞에서 달리고 있는 선수들 중 평소 실력이 2인 선수만 앞지르는 것이 가능하고
 * 평소실력이 8과 10인 선수들은 앞지르는 것이 불가능하므로, 최선의 등수는 3등이 된다.
 *
 * 선수들의 평소 실력을 현재 달리고 있는 순서대로 입력 받아서 각 선수의 최선의 등수를 계산하는 프로그램을 작성하시오.
 *
 * 입력
 * 첫째 줄에는 선수의 수를 의미하는 정수 N이 주어진다. N은 3 이상 500,000 이하이다.
 * 이후 N개의 줄에는 정수가 한 줄에 하나씩 주어진다.
 * 이 값들은 각 선수들의 평소 실력을 앞에서 달리고 있는 선수부터 제시한 것이다.
 * 각 정수는 1 이상 1,000,000,000 이하이다. 단, 참가한 선수들의 평소 실력은 모두 다르다.
 *
 * 출력
 * 각 선수의 최선의 등수를 나타내는 정수 N개를 입력에 주어진 선수 순서와 동일한 순서로 한 줄에 하나씩 출력한다.
 *
 *
 * 8
 * 2
 * 8
 * 10
 * 7
 * 1
 * 9
 * 4
 * 15
 *
 *
 * 1
 * 1
 * 1
 * 3
 * 5
 * 2
 * 5
 * 1
 *
 *
 */
public class Solution_2517 {

    static class Player{
        int index;
        long speed;
    }

    static int N, size;
    static Player[] arr;
    static int[] tree;
    static int[] answer;

    static class ComparePlayer implements Comparator<Player> {

        @Override
        public int compare(Player o1, Player o2) {
            return (int) (o2.speed - o1.speed);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Player[N];
        answer = new int[N];
        for (int i = 0; i < N; i++) {
            Player player = new Player();
            player.index = i + 1;
            player.speed = Long.parseLong(br.readLine());
            arr[i] = player;
        }

        Arrays.sort(arr, new ComparePlayer());

        size = 2;
        while (size < N) {
            size *= 2;
        }
        tree = new int[size * 2];

        for (int i = 0; i < arr.length; i++) {
//            int count = getCount(size, size + arr[i].index - 1);
//            updateCount(size + arr[i].index - 1, 1);

            int count = getCountToTop(1, size, size + N - 1, size, size + arr[i].index - 1);
            updateCount(size + arr[i].index - 1, 1);
            answer[arr[i].index - 1] = count + 1;
        }

//        System.out.println(Arrays.toString(answer));

        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    private static int getCountToTop(int node, int left, int right, int start, int end) {   // segment

        if (right < start || end < left) {
            return 0;
        }

        if (start <= left && right <= end) {
            return tree[node];
        }

        int mid = (left + right) / 2;

        return tree[node] = getCountToTop(node * 2, left, mid, start, end)
                + getCountToTop(node * 2 + 1, mid + 1, right, start, end);

    }

    private static void updateCount(int idx, int value) {   // index

        while (idx > 0) {
            tree[idx] += value;
            idx /= 2;
        }

    }

    private static int getCount(int a, int b) {         // index

        int count = 0;
        while (a <= b) {
            if(a % 2 == 1) count += tree[a];
            if(b % 2 == 0) count += tree[b];

            a = (a + 1) / 2;
            b = (b - 1) / 2;
        }
        return count;
    }

}
