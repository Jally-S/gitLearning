package adapter;

public class SearchAdapter extends BinarySearch implements DataOperation {
	public int search(int[]a,int x) {
		System.out.println("���ýӿ��е�search����...");
		System.out.println("ʵ����BinarySearch�еĶ��ֲ��ҷ���...");
		int b = super.binarySearch(a, x);
		return b+1;
		
	}
	public void sort(int[]a,int low,int high){}
}
	
	

