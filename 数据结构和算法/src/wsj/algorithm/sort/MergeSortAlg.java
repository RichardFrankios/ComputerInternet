package wsj.algorithm.sort;

import java.util.Arrays;

/**
 * �鲢���� : O(nlogn)
 *	��������ֳ����ɸ�С����. ��С�����ڽ�������. Ȼ��ϲ�.
 */
public class MergeSortAlg {

	public static void main(String[] args) {
		int [] data = new int[]{90,3,2,67,44,-9,87,65,11,9,2,8};
		Utils.printArray(data);
		new MergeSortAlg().mergeSort(data, 0, data.length -1);;
		Utils.printArray(data);
	}
	
	/**
	 * �鲢����.
	 * @param data
	 * @param start
	 * @param end
	 */
	public void mergeSort(int[] data, int start, int end) {
		if (data == null || data.length == 0) {
			return;
		}
		if (start < end) {
			int mid = (start + end)/2;
			// �ݹ�����.
			mergeSort(data, start, mid);
			mergeSort(data, mid + 1, end);
			// �ϲ�����.
			merge(data, start, mid, end);
		}
	}

	/**
	 * �ϲ�������������.
	 * @param data
	 * @param start
	 * @param mid
	 * @param end
	 */
	private void merge(int[] data, int start, final int mid, int end) {
		int[] tempArray = Arrays.copyOf(data, data.length);
		// ��ʼλ���ǵ�ǰС����ĵĿ�ʼ.
		int index = start;
		int rightStart = mid + 1;
		
		while(start <= mid && rightStart <= end){
			if (tempArray[start] <= tempArray[rightStart]) {
				data[index ++] = tempArray[start++];
			}else {
				data[index ++] = tempArray[rightStart++];
			}
		}
		while(start <= mid){
			data[index ++] = tempArray[start ++];
		}
		
		while(rightStart <= end){
			data[index ++] = tempArray[rightStart ++];
		}
	}

}















