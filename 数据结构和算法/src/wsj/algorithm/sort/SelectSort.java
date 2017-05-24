package wsj.algorithm.sort;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Iterator;
import java.util.logging.Handler;

import javax.naming.InitialContext;
import javax.swing.text.DefaultEditorKit.DefaultKeyTypedAction;

import org.omg.PortableServer.ServantActivator;

// 选择排序算法。

public class SelectSort {
	
	/**
	 * http://www.cnblogs.com/coder2012/archive/2013/04/13/3012390.html
	 * 主方法，主要是测试选择排序。
	 * @param args
	 */
	public static void main(String[] args) {
		
		
//		byte[] data = {0x01,0x00,0x5e,0x7f,(byte) 0xff,(byte) 0xfa,0x00,0x50,0x56,(byte) 0xc0,0x00,0x08,0x08,0x00,0x45,0x00
//				,0x00,(byte) 0xc9,0x2b,(byte) 0x96,0x00,0x00,0x01,0x11,(byte) 0xb1,(byte) 0xea,(byte) 0xc0,(byte) 0xa8,0x2b,0x01,(byte) 0xef,(byte) 0xff
//				,(byte) 0xff,(byte) 0xfa,(byte) 0xee,0x24,0x07,0x6c,0x00,(byte) 0xb5,(byte) 0xee,0x03,0x4d,0x2d,0x53,0x45,0x41,0x52
//				,0x43,0x48,0x20,0x2a,0x20,0x48,0x54,0x54,0x50,0x2f,0x31,0x2e,0x31,0x0d,0x0a,0x48
//				,0x4f,0x53,0x54,0x3a,0x20,0x32,0x33,0x39,0x2e,0x32,0x35,0x35,0x2e,0x32,0x35,0x35
//				,0x2e,0x32,0x35,0x30,0x3a,0x31,0x39,0x30,0x30,0x0d,0x0a,0x4d,0x41,0x4e,0x3a,0x20
//				,0x22,0x73,0x73,0x64,0x70,0x3a,0x64,0x69,0x73,0x63,0x6f,0x76,0x65,0x72,0x22,0x0d
//				,0x0a,0x4d,0x58,0x3a,0x20,0x31,0x0d,0x0a,0x53,0x54,0x3a,0x20,0x75,0x72,0x6e,0x3a
//				,0x64,0x69,0x61,0x6c,0x2d,0x6d,0x75,0x6c,0x74,0x69,0x73,0x63,0x72,0x65,0x65,0x6e
//				,0x2d,0x6f,0x72,0x67,0x3a,0x73,0x65,0x72,0x76,0x69,0x63,0x65,0x3a,0x64,0x69,0x61
//				,0x6c,0x3a,0x31,0x0d,0x0a,0x55,0x53,0x45,0x52,0x2d,0x41,0x47,0x45,0x4e,0x54,0x3a
//				,0x20,0x47,0x6f,0x6f,0x67,0x6c,0x65,0x20,0x43,0x68,0x72,0x6f,0x6d,0x65,0x2f,0x35
//				,0x36,0x2e,0x30,0x2e,0x32,0x39,0x32,0x34,0x2e,0x38,0x37,0x20,0x57,0x69,0x6e,0x64
//				,0x6f,0x77,0x73,0x0d,0x0a,0x0d,0x0a};
//		
//		try {
//			System.out.println(new String(data, "utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		int[] datas = {1, 6, 9, 5, 7, 2, 8, 4, 1, 6, 9, 5, 7, 2, 8, 4, };
		showArray(datas);
		
//		selectSort1(datas);
		insertSort1(datas);
//		shellSort1(datas);
		
		
		showArray(datas);
		
	}
	
	public static void showArray(int[] data) {
		System.out.print("数组 : ");
		for (int i : data) {
			System.out.print(i + " ");
		}
		System.out.println("");
		
	}
	
	// 结论一 : 如果是随机无重复主键的情况下, 插入排序的速度更快.
	// 结论二 : 如果不是随机无重复的情况下, 插入排序不一定比选择排序快.
	
	/**
	 * 希尔排序. 是插入排序的改良版本. 在插入排序中使用的人增量是 1, 
	 * @param datas
	 * 注意 : 在希尔排序中使用的增量是 1, 4, 13 40 ... 最小是 1 , (3 * h + 1)
	 */
	public static void shellSort(final int[] datas) {
		int length = datas.length;
		// 设置增量.
		int delta = 1; // 最小是1,
		while( delta < length/3)
			delta = 3 * delta + 1;
		
		System.out.println(delta);
		System.out.println(length);
		// 进行排序
		while(delta >= 1){
			System.out.println("while : " + delta);
			for (int i = delta; i < length; i++) {
				for (int j = i; j > delta; j -= delta) {
					if (datas[j] < datas[j - delta]) {
						int temp = datas[j];
						datas[j] = datas[j - delta];
						datas[j - delta] = temp;
					}
				}
			}
			delta /= 3;
		}
		
		
	}
	
	/**
	 * 插入排序
	 * @param datas 数据
	 * 插入排序特点 : 
	 * 		1. 运行时间和输入数据有关, 如果已经是一个有序的数据则会非常快的完成.
	 */
	public static void insertSort(final int[] datas) {
		// 从第二个元素往前插入. 因此是长度 - 1 次.
		for (int i = 1; i < datas.length; i++) {
			// 当前元素(i)和之前的元素进行比较 
			for (int j = 0; j < i; j++) {
				if (datas[i] < datas[j]) {
					int temp = datas[i];
					datas[i] = datas[j];
					datas[j] = temp;
				}
			}
		}
	}
	
	/**
	 * 选择排序, 升序.
	 * @param datas 数据
	 * 选择排序的特点 : 
	 * 		1. 运行时间和输入无关. 无论输入数据是否有序,都是需要比较 N次.
	 * 		2. 数据移动是最少的.
	 */
	public static void selectSort(final int[] datas) {
		// 最后一个不用比较,因此是数组长度 - 1次比较.
		for (int i = 0; i < datas.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < datas.length; j++) {
				// 和当前元素的下一个元素进行比较.
				if (datas[j] < datas[index]) {
					index = j;
				}				
			}
			if (index != i) {
				int temp = datas[i];
				datas[i] = datas[index];
				datas[index] = temp;
			}
		}
	}
	
	
	
	/**
	 * 排序算法练习
	 */
	public static void selectSort1(final int[] data){
		for (int i = 0; i < data.length; i++) {
			int min = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < data[min]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = data[i];
				data[i] = data[min];
				data[min] = temp;
			}
		}
	}
	
	public static void insertSort1(final int[] data){
		for (int i = 1; i < data.length; i++) {
			
			for (int j = i; j > 0; j--) {
				if (data[j - 1] > data[j]) {
					int temp = data[j];
					data[j] = data[j - 1];
					data[j - 1] = temp;
				}
			}
		}
	}
	
	public static void shellSort1(final int[] data) {
		int length = data.length;
		int delta = 1;
		while(delta < length/ 3)
			delta = delta *3 + 1;
		
		while(delta >=1){
			
			for (int i = delta; i < data.length; i++) {
				for (int j = i; j > delta; j-=delta) {
					if (data[j] < data[j -delta]) {
						int temp = data[j];
						data[j] = data[j - delta];
						data[j - delta] = temp;
					}
				}
			}
			
			delta /= 3;
		}
		
		
	}
	
	

}
