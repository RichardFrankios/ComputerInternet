package wsj.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

import javax.sound.sampled.DataLine;

/**
 * ѡ�������㷨 : 
 * 	1. ��ѡ������. 
 * 		�㷨���Ӷ� :   O(n^2)
 * 	2. ������
 * 		�㷨���Ӷ� : nlog2(n)
 *
 */
public class SelectSortAlg {

	public static void main(String[] args) {
		System.out.println(">>>>>> ѡ������");
		
//		int[] data = new int[]{9,4,2,6,7,3,10,33,88,1,17};
		int size = 100000;
		int[] data = new int[size];
		for (int i = 0; i < data.length; i++) {
			data[i] = new Random().nextInt(data.length);
		}
		long start = System.currentTimeMillis();
		new SelectSortAlg().simpleSelectSort(data);
		System.out.println("��ʱ : " + (System.currentTimeMillis() - start));
		
		//printArray(data);	
		
		System.out.println(">>>>>> ������");
//		 data = new int[]{9,4,2,6,7,3,10,33,88,1,17};
		
		int[] data1 = new int[size];
		for (int i = 0; i < data1.length; i++) {
			data1[i] = new Random().nextInt(data1.length);
		}
		
		start = System.currentTimeMillis();
		new SelectSortAlg().HeapSort(data1);
		
		System.out.println("��ʱ : " + (System.currentTimeMillis() - start));
	}

	private static void printArray(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * ��ѡ������
	 * 	˼�� : 
	 * 		1. ÿ��ѭ����¼��Сֵ�±�.
	 * 		2. ÿ��ѭ�����ж���Сֵ�Ƿ��ǵ�ǰ�ȽϷ�Χ�еĵ�һ��.������򽻻�����.
	 */
	public void simpleSelectSort(int[] data) {
		if (data == null || data.length == 0) {
			return;
		}
		int minIndex;
		int temp;
		// �Ƚ� n-1 ��, ���һ������û�бȽϵı�Ҫ.
		for (int i = 0; i < data.length-1 ; i++) {
			minIndex = i;
			for (int j = i; j < data.length; j++) {
				if (data[minIndex] > data[j]) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				exchangeElements(data, minIndex, i);
			}
		}
	}
	
	
	/**
	 * ������
	 * 	�ͼ�ѡ��������Ƚ�, �ȽϵĴ�������. �������ݽ����Ĵ�������.
	 */
	public void HeapSort(int[] data) {
		if (data == null || data.length == 0) {
			return;
		}
		// ��ʼ����
		buildMaxHeap(data, data.length);
		// ����.
		for (int cur = data.length - 1; cur > 0; --cur) {
			exchangeElements(data, 0, cur);
			adjustHeap(data, cur, 0);
		}
		
	}

	/**
	 * ��ʼ������
	 * @param data
	 * @param size
	 */
	private void buildMaxHeap(int[] data, int size) {
		// ������������Ҷ�ӽڵ���  n/2
		// ��������Ԫ��
		for(int parent = size /2 - 1; parent >= 0 ; parent --){
			adjustHeap(data, size, parent);	
		}
	}

	/**
	 * ������
	 * @param data
	 * @param size
	 * @param parent
	 */
	private void adjustHeap(int[] data, int size, int parent) {
		int left = parent*2 +1;
		int right = parent*2 +2;
		int max = parent;
		if (left < size && data[left] > data[max]) {
			max = left;
		}
		if (right < size && data[right] > data[max]) {
			max = right;
		}
		// ��������.
		if (max != parent) {
			exchangeElements(data, parent, max);
			// �������������.
			adjustHeap(data, size, max);
		}
		
	}

	/**
	 * ��������Ԫ��
	 * @param data   ����
	 * @param index1 index1
	 * @param index2    index2
	 */
	private void exchangeElements(int[] data, int index1, int index2) {
		int temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}
	

}















