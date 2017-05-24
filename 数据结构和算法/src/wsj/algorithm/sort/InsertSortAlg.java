package wsj.algorithm.sort;

/**
 * 插入排序:
 * 	1. 直接插入排序. O(n^2)
 * 	2. 二分插入排序. O(n^2)
 * 	3. 希尔排序.    O(n^2)
 *
 */
public class InsertSortAlg {

	public static void main(String[] args) {
		
		System.out.println("直接插入排序 >>>> :");
		int[] data = new int[]{9,4,2,6,7,3,10,33,88,1,17};
		Utils.printArray(data);
		new InsertSortAlg().simpleInsertSort(data);
		Utils.printArray(data);
		
		
		System.out.println("二分插入排序 >>>> :");
		int[] data1 = new int[]{9,4,2,6,7,3,10,33,88,9,1,17};
		Utils.printArray(data1);
		new InsertSortAlg().binaryInsertSort(data1);
		Utils.printArray(data1);
	}
	
	/**
	 * 
	 * 直接插入排序, 是始终认为左侧的是一个有序数据列, 将右侧的中的数据一次插入左侧
	 * @param data
	 */
	public void simpleInsertSort(int[] data) {
		if (data == null || data.length == 0) {
			return;
		}
		// 首先认为第一个元素自己组成的集合是一个有序集合,
		int size = data.length;
		// 一次将 第 2 ~ size 个数据插入有序集合.
		for(int i = 1;i < size; i++ ){
			// 为寻找合适位置.
			for(int j = 0; j < i; j ++){
				if (data[i] < data[j]) {
					Utils.exchangeElements(data, i, j);
				}
			}
			
		}
	}
	
	/**
	 * 二分插入排序.
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
			// 移动数组.
			for(int j = i; j > pos ; j--){
				data[j] = data[j - 1];
			}
			data[pos] = cur;
			Utils.printArray(data);
		}
	}


	/**
	 * 二分查找位置.
	 * @param data 数组,
	 * @param item 待插入数据
	 * @param low  开始index
	 * @param high 结束index
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
