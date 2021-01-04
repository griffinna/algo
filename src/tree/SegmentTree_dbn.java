package tree;

public class SegmentTree_dbn {

	static int[] tree, a;
	
	public static void main(String[] args) {
		a = new int[]{1, 9, 3, 8, 4, 5, 5, 9, 10, 3, 4, 5};
		tree = new int[a.length * 4];	// 4를 곱하면 모든 범위를 커버할 수 있음.
		int size = a.length;
		
		init(0, size - 1, 1);
		// 0 ~ 12 까지의 구간합
		sum(0, size - 1, 1, 0, size);
		// 구간합 갱신 : idx 5의 원소를 -5만큼 수정
		update(0, size - 1, 1, 5, -5);
	}
	
	// start : 시작인덱스, end : 끝 인덱스
	static int init(int start, int end, int node) {	// 재귀를 이용한 초기화
		if(start == end) tree[node] = a[start];
		int mid = (start + end) / 2;
		// 재귀적으로 두 부분으로 나눈 뒤에 그 합을 자기 자신으로 합친다.
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}
	
	// start : 시작인덱스, end : 끝 인덱스
	// left, right : 구간 합을 구하고자 하는 범위
	static int sum(int start, int end, int node, int left, int right) {	// 구간합 : 범위안에 있는 경우에 한해서만 더해준다.
		
		// 범위 밖
		if(left > end || right < start) return 0;
		
		// 범위 안
		if(left <= start && end <= right) return tree[node];
		
		// 그렇지 않은 경우
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
	
	// start : 시작인덱스, end : 끝 인덱스
	// idx : 구간 합을 수정하고자 하는 노드
	// dif : 수정할 값
	static void update(int start, int end, int node, int idx, int dif) {	// 특정 원소의 값을 수정 : 범위 안에 있는 경우에 한해서 수정
		// 범위 밖
		if(idx < start || end < idx) return;
		// 범위 안에 있으면 내려가며 다른 원소도 갱신
		tree[node] += dif;
		if(start == end) return;
		int mid = (start + end) / 2;
		update(start, mid, node * 2, idx, dif);
		update(mid + 1, end, node * 2 + 1, idx, dif);
	}
	
}
