package wsj.algorithm.search;

import org.omg.PortableInterceptor.IORInterceptor;

import wsj.algorithm.sort.MergeSortAlg;
import wsj.algorithm.sort.Utils;

/**
 * 二分查找.  需要数组有序.
 * 	1. 递归实现.
 * 	2. 非递归实现.
 *
 */
public class BinarySearchAlg {

	public static void main(String[] args) {
		int [] data = {10,23,4,3,2,5,1,2,623,92,23,23,234,2,34,234,234,2,10};
		new MergeSortAlg().mergeSort(data, 0, data.length - 1);
		Utils.printArray(data);
		int pos = new BinarySearchAlg().recursiveBinarySearch(34, data, 0, data.length-1);
		System.out.println("递归查找 ---位置 : " + pos);
		pos = new BinarySearchAlg().directBinarySearch(34, data);
		System.out.println("非递归查找 ---位置 : " + pos);
	}

	/**
	 * 递归方法实现二分查找.
	 * @param value
	 * @param data
	 * @param low
	 * @param high
	 */
	public int recursiveBinarySearch(int value, int[] data, int low , int high) {
		if (low > high) {
			return -1;
		}
		int mid = (low + high)/2;
		if (value == data[mid]) {
			return mid;
		}else if (value < data[mid]) {
			return recursiveBinarySearch(value, data, low, mid - 1);
		}else {
			return recursiveBinarySearch(value, data, mid + 1, high);
		}
	}
	/**
	 * 非递归方式, 二分查找.
	 * @param value
	 * @param data
	 * @return
	 */
	public int directBinarySearch(int value, int[] data) {
		int low = 0;
		int high = data.length - 1;
		int mid ;
		
		while(low <= high){
			mid = (low + high)/2;
			if (value == data[mid]) {
				return mid;
			}else if (value < data[mid]) {
				high = mid - 1;
			}else {
				low = mid + 1;
			}
		}
		return -1;
	}
	

}













