package wsj.algorithm.recursion;

/**
 * ŷ������㷨 : �������ݵ����Լ��,�������н�С���Ǹ���������������������Ĺ�Լ�����.
 *            (1) m > n
 *            (2) gcd(m, n)  == gcd(n, m%n); 
 */
public class GcdAlg {

	/**
	 * ŷ������㷨
	 * @param m 
	 * @param n
	 */
	public int gcd(int m, int n) {
		// ȷ�� m > n
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
		System.out.println("���Լ�� : " + new GcdAlg().gcd(99, 44));
	}

}
