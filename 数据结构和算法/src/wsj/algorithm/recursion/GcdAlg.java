package wsj.algorithm.recursion;

/**
 * 欧几里得算法 : 两个数据的最大公约数,等于其中较小的那个数和两个数相除的余数的公约数相等.
 *            (1) m > n
 *            (2) gcd(m, n)  == gcd(n, m%n); 
 */
public class GcdAlg {

	/**
	 * 欧几里得算法
	 * @param m 
	 * @param n
	 */
	public int gcd(int m, int n) {
		// 确保 m > n
		if (m < n) {
			m = m + n;
			n = m - n;
			m = m - n;
		}
		if (n == 0) {
			return m;
		}else {
			return gcd(n, m%n);
		}
	}
	public static void main(String[] args) {
		System.out.println("最大公约数 : " + new GcdAlg().gcd(99, 44));
	}

}
