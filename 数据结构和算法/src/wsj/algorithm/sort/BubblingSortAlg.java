package wsj.algorithm.sort;

/**
 * ð������.
 * 	�㷨���Ӷ� : O(n^2)
 *
 */
public class BubblingSortAlg {

	public static void main(String[] args) {
		int[] data = new int[]{9,4,2,6,7,3,10,33,88,1,17};
		Utils.printArray(data);
		new BubblingSortAlg().bubblingSort(data);
		Utils.printArray(data);
	}
	
	/**
	 * ð������
	 * @param data
	 */
	public void bubblingSort(int[] data) {
		if (data == null || data.length == 0) {
			return;
		}
		int temp;
		int size = data.length;
		// ���һ�ζ�û�н��н���˵���Ѿ������������
		boolean isSwaped = false;
		for(int i = 0;i < size; i ++) {
			for(int j = size - 1; j > i ;j --){
				if (data[j] < data[j-1]) {
					temp = data[j];
					data[j] = data[j - 1];
					data[j - 1] = temp;
					isSwaped = true;
				}
			}
			if (!isSwaped) {
				return;
			}
		}
	}
	

}














