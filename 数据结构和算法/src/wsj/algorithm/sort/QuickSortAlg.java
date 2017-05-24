package wsj.algorithm.sort;

/**
 * �������� : O(nlogn)
 *
 */
public class QuickSortAlg {

	public static void main(String[] args) {
		int [] data = {19,2,3,90,67,33,-7,24,3,56,34,5};
		Utils.printArray(data);
		new QuickSortAlg().quick(data);
		Utils.printArray(data);
	}
	public void  quick(int[] data) {
		if (data == null || data.length == 0) {
			return;
		}
		quickSort(data, 0, data.length - 1);
		
	}
	/**
	 * ��������.
	 * 	˼�� : 	
	 * 		1. ��ȷ��һ������, Ȼ��ȷ�����������е�λ��.
	 * @param data
	 * @param low
	 * @param high
	 */
	public void quickSort(int[] data,int low , int high) {
		if (low < high) {
			int pos = findPos(data, low ,high);
			quickSort(data, low, pos - 1);
			quickSort(data, pos + 1, high);
		}
	}
	/**
	 * �ҵ�Ԫ�ص����λ��.
	 * @param data
	 * @param low
	 * @param high
	 * @return
	 */
	private int findPos(int[] data, int low, int high) {
		int base = data[low];
		while(low < high){
			// �ƶ���λ��.
			while(high > low && base <= data[high])
				high --;
			data[low] = data[high];
			while(low < high && base >= data[low])
				low ++;
			data[high] = data[low];
		}
		data[low] = base;
		return low;
	}

}
