package wsj.algorithm.sort;

/**
 * ��������:
 * 	1. ֱ�Ӳ�������. O(n^2)
 * 	2. ���ֲ�������. O(n^2)
 * 	3. ϣ������.    O(n^2)
 *
 */
public class InsertSortAlg {

	public static void main(String[] args) {
		
		System.out.println("ֱ�Ӳ������� >>>> :");
		int[] data = new int[]{9,4,2,6,7,3,10,33,88,1,17};
		Utils.printArray(data);
		new InsertSortAlg().simpleInsertSort(data);
		Utils.printArray(data);
		
		
		System.out.println("���ֲ������� >>>> :");
		int[] data1 = new int[]{9,4,2,6,7,3,10,33,88,9,1,17};
		Utils.printArray(data1);
		new InsertSortAlg().binaryInsertSort(data1);
		Utils.printArray(data1);
	}
	
	/**
	 * 
	 * ֱ�Ӳ�������, ��ʼ����Ϊ������һ������������, ���Ҳ���е�����һ�β������
	 * @param data
	 */
	public void simpleInsertSort(int[] data) {
		if (data == null || data.length == 0) {
			return;
		}
		// ������Ϊ��һ��Ԫ���Լ���ɵļ�����һ�����򼯺�,
		int size = data.length;
		// һ�ν� �� 2 ~ size �����ݲ������򼯺�.
		for(int i = 1;i < size; i++ ){
			// ΪѰ�Һ���λ��.
			for(int j = 0; j < i; j ++){
				if (data[i] < data[j]) {
					Utils.exchangeElements(data, i, j);
				}
			}
			
		}
	}
	
	/**
	 * ���ֲ�������.
	 * @param data
	 */
	public void binaryInsertSort(int[] data) {
		if (data == null || data.length == 0) {
			return;
		}
		int size = data.length;
		int pos = 0;
		int cur = 0;
		for(int i = 1;i < size; i ++){
			cur = data[i];
			pos = findPosition(data, cur, 0, i -1);
			System.out.println("Pos == " + pos);
			// �ƶ�����.
			for(int j = i; j > pos ; j--){
				data[j] = data[j - 1];
			}
			data[pos] = cur;
			Utils.printArray(data);
		}
	}


	/**
	 * ���ֲ���λ��.
	 * @param data ����,
	 * @param item ����������
	 * @param low  ��ʼindex
	 * @param high ����index
	 */
	private int findPosition(int[] data, int item, int low, int high) {
		
		int mid = (low + high)/2;
		while(high > 0 && low < high){
			mid = (low + high) /2;
			if (item < data[mid]) {
				high = mid -1;
			}else if (item > data[mid]) {
				low = mid + 1;
			}else {
				return mid +1;
			}
		}
		return item > data[low] ? low + 1 : low;
	}
	
	
	
	

}
