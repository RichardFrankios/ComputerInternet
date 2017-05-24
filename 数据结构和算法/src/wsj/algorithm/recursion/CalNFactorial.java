package wsj.algorithm.recursion;


/**
 * ���� n �Ľ׳�
 */
public class CalNFactorial {

	public static void main(String[] args) {
		System.out.println("5 �Ľ׳� : " + new CalNFactorial().calNFact(5));
	}
	
	/**
	 * ����׳�
	 * @param n
	 * @return
	 */
	public int calNFact(int n) {
		if (n == 1) {
			return 1;
		}else {
			return calNFact(n - 1) * n;
		}
	}

}
