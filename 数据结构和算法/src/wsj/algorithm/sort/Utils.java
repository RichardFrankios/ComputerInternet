package wsj.algorithm.sort;

public class Utils {
	/**
	 * 打印数组.
	 * @param data
	 */
	public static void printArray(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
	/**
	 * 交换数组元素
	 * @param data   数组
	 * @param index1 index1
	 * @param index2    index2
	 */
	public static void exchangeElements(int[] data, int index1, int index2) {
		int temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}
}
