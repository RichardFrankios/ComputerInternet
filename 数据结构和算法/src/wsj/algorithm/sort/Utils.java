package wsj.algorithm.sort;

public class Utils {
	/**
	 * ��ӡ����.
	 * @param data
	 */
	public static void printArray(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
	/**
	 * ��������Ԫ��
	 * @param data   ����
	 * @param index1 index1
	 * @param index2    index2
	 */
	public static void exchangeElements(int[] data, int index1, int index2) {
		int temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}
}
