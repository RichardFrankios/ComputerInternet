package wsj.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

import javax.sound.sampled.DataLine;

/**
 * 选择排序算法 : 
 * 	1. 简单选择排序. 
 * 		算法复杂度 :   O(n^2)
 * 	2. 堆排序
 * 		算法复杂度 : nlog2(n)
 *
 */
public class SelectSortAlg {

	public static void main(String[] args) {
		System.out.println(">>>>>> 选择排序");
		
//		int[] data = new int[]{9,4,2,6,7,3,10,33,88,1,17};
		int size = 100000;
		int[] data = new int[size];
		for (int i = 0; i < data.length; i++) {
			data[i] = new Random().nextInt(data.length);
		}
		long start = System.currentTimeMillis();
		new SelectSortAlg().simpleSelectSort(data);
		System.out.println("耗时 : " + (System.currentTimeMillis() - start));
		
		//printArray(data);	
		
		System.out.println(">>>>>> 堆排序");
//		 data = new int[]{9,4,2,6,7,3,10,33,88,1,17};
		
		int[] data1 = new int[size];
		for (int i = 0; i < data1.length; i++) {
			data1[i] = new Random().nextInt(data1.length);
		}
		
		start = System.currentTimeMillis();
		new SelectSortAlg().HeapSort(data1);
		
		System.out.println("耗时 : " + (System.currentTimeMillis() - start));
	}

	private static void printArray(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * 简单选择排序
	 * 	思想 : 
	 * 		1. 每次循环记录最小值下标.
	 * 		2. 每次循环后判断最小值是否是当前比较范围中的第一个.如果是则交换数据.
	 */
	public void simpleSelectSort(int[] data) {
		if (data == null || data.length == 0) {
			return;
		}
		int minIndex;
		int temp;
		// 比较 n-1 次, 最后一个数字没有比较的必要.
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
	 * 堆排序
	 * 	和简单选择排序相比较, 比较的次数少了. 但是数据交换的次数多了.
	 */
	public void HeapSort(int[] data) {
		if (data == null || data.length == 0) {
			return;
		}
		// 初始化堆
		buildMaxHeap(data, data.length);
		// 遍历.
		for (int cur = data.length - 1; cur > 0; --cur) {
			exchangeElements(data, 0, cur);
			adjustHeap(data, cur, 0);
		}
		
	}

	/**
	 * 初始化最大堆
	 * @param data
	 * @param size
	 */
	private void buildMaxHeap(int[] data, int size) {
		// 二叉树的最大非叶子节点是  n/2
		// 遍历所有元素
		for(int parent = size /2 - 1; parent >= 0 ; parent --){
			adjustHeap(data, size, parent);	
		}
	}

	/**
	 * 调整堆
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
		// 交换数组.
		if (max != parent) {
			exchangeElements(data, parent, max);
			// 调整下面的子树.
			adjustHeap(data, size, max);
		}
		
	}

	/**
	 * 交换数组元素
	 * @param data   数组
	 * @param index1 index1
	 * @param index2    index2
	 */
	private void exchangeElements(int[] data, int index1, int index2) {
		int temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}
	

}















