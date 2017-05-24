package wsj.algorithm.recursion;


/**
 * 汉诺塔 . 递归
 *
 */
public class HanNoTa {

	
	/**
	 * 汉诺塔算法.
	 * @param n         盘子个数
	 * @param from      起始位置
	 * @param dependOn  辅助位置
	 * @param to        目标位置
	 */
	public void hanNoTa(int n , char from , char dependOn, char to) {
		if (n == 1) {
			move(1, from , to );
		}else {
			// 将 n-1 个盘子从A 利用 C 挪到B.
			hanNoTa(n - 1, from, to, dependOn);
			// 将 n 从A--C
			move(n, from, to);
			// 将 n-1 个盘子利用A挪到C
			hanNoTa(n-1, dependOn, from, to);
		}
	}
	
	private void move(int i, char from, char to) {
		System.out.println(from + "------->" + to);
	}

	public static void main(String[] args) {
		new HanNoTa().hanNoTa(3, 'A', 'B', 'C');
	}
	
	
	
	
	public void hanNoTa1(int n , char from, char dependOn, char to) {
		if (n == 1) {
			move(n, from, to);
		}else {
			// 第一步 : 将 (n-1)从 A 个挪到 B, 借助B作为中转.
			hanNoTa1(n-1, from , to, dependOn);
			// 第二步 : 将 第 n 个 直接挪到 C
			move(n, from, to);
			// 第三步 : 将 (n-1)从 B 个挪到 C, 借助A作为中转.
			hanNoTa1(n-1, dependOn, from, to);
		}
	}
	
	
	
	
	
	

}
