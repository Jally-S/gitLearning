package adapter;

public class SortAdapter extends QuickSort implements DataOperation {
	public void sort(int[] a, int low, int high) {
		System.out.println("���ýӿ��е�sort����...");
		System.out.println("ʵ����QuickSort�еĿ������򷽷�...");
		super.quickSort(a, low, high);
	}

	public int search(int[] b, int x) {return -1;};
}

