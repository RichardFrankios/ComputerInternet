package wsj.algorithm.dp;

import java.sql.Blob;
import java.util.Arrays;

import javax.naming.InitialContext;

/**
 * 动态规划法.
 *
 */
public class DP {

	public static void main(String[] args) {
//		int max = new DP().findLCS("android", "random");
//		System.out.println("最长子序列长度："+max);
		//new DP().findLcs2("android", "random");
		new DP().findLcs3("android", "random");
	}
	
	/**
	 * 求最大公共子序列.
	 * 	(1) i==0 || j == 0  --> 0;
	 * 	(2) i == j --> a[i][j] = a[i-1][j-1] + 1;
	 * 	(3) i!= j  --> a[i][j] = Math.max(a[i-1][j], a[i][j-1]); 
	 * @param A
	 * @param B
	 */
	public void findLcs2(String A, String B) {
		int al = A.length();
		int bl = B.length();
		
		char[] as = A.toCharArray();
		char[] bs = B.toCharArray();
		
		// 多出一行一列的 0 
		int[][] dp = new int[al+ 1][bl + 1];
		
		for(int i = 1; i < al + 1; i ++){
			for(int j = 1; j < bl +1 ; j ++){
				if (as[i -1] == bs[j - 1]) {
					dp[i][j] = dp[i -1][j - 1] + 1;
				}else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		
		// 输出串
		System.out.println("     " + Arrays.toString(bs));
		for(int i =0 ;i < al + 1; i++){
			if (i!= 0) {
				System.out.print(as[i - 1] + " ");
			}else {
				System.out.print("  ");
			}
			
			System.out.println(Arrays.toString(dp[i]));
		}
		System.out.println("最大子序列长度 : " + dp[al][bl]);
	}
	
	/**
	 * 求最大公共子串. 
	 * 	(1) i==0 || j == 0  --> 0;
	 * 	(2) i == j --> a[i][j] = a[i-1][j-1] + 1;
	 * 	(3) i!= j  --> 0; // 只要不相等则连续的串也就断了.
	 * @param A
	 * @param B
	 */
	public void findLcs3(String A, String B) {
		int al = A.length();
		int bl = B.length();
		
		char[] as = A.toCharArray();
		char[] bs = B.toCharArray();
		
		// 多出一行一列的 0 
		int[][] dp = new int[al+ 1][bl + 1];

		int maxColIndex = 0;
		int maxRowIndex = 0;
		
		for(int i = 1; i < al + 1; i ++){
			for(int j = 1; j < bl +1 ; j ++){
				if (as[i -1] == bs[j - 1]) {
					dp[i][j] = dp[i -1][j - 1] + 1;
					
					if (dp[maxRowIndex][maxColIndex] < dp[i][j]) {
						maxRowIndex = i;
						maxColIndex = j;
					}
					
					
				}else {
					dp[i][j] = 0;
				}
			}
		}
		
		// 输出串
		System.out.println("     " + Arrays.toString(bs));
		for(int i =0 ;i < al + 1; i++){
			if (i!= 0) {
				System.out.print(as[i - 1] + " ");
			}else {
				System.out.print("  ");
			}
			
			System.out.println(Arrays.toString(dp[i]));
		}
		int maxLen = dp[maxRowIndex][maxColIndex];
		System.out.println("最大子串长度 : " + maxLen);
		System.out.print("最大串 : ");
		for(int i = maxLen - 1; i >= 0 ; i --){
			System.out.print(as[maxRowIndex - i -1]);
		}
		
	}
	
	
	
	
	

}
