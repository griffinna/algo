package tree;

public class Heap {

    public static void main(String[] args) {
        int arr[] = { 5, 2, 1, 7, 112, 42, 52, 62 };
        heapSort(arr);
        printArray(arr);
    }

    private static void heapSort(int[] arr) {
        int length = arr.length -1;
        maxBuildHeap(arr);
        for (int i = length; i >= 1; i--) {
            swap(arr, 0, 1);
            length -= 1;
            maxHeapify(arr, 0);
        }
    }

    private static void maxBuildHeap(int[] arr){
        int length = arr.length;
        // 마지막 노드의 부모부터 시작
        for (int i = length / 2; i >= 0; i--) {
            maxHeapify(arr, i);
        }
    }

    private static void maxHeapify(int[] arr, int i) {
        int length = arr.length;
        int leftChild = i * 2;
        int rightChild = i * 2 + 1;
        int k = 0;

        // 자식노드 없으면 끝
        if (leftChild > length || rightChild > length) {
            return;
        }

        // 더 큰 child 를 k 에 할당
        if (arr[leftChild] > arr[rightChild]) {
            k = leftChild;
        }else{
            k = rightChild;
        }

        // 부모가 자식보다 크면 종료
        if (arr[i] >= arr[k]) {
            return;
        }

        swap(arr, i, k);
        maxHeapify(arr, k);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
