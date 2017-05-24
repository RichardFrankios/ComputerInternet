package wsj.algorithm.recursion;


/**
 * 數呾 n 腔論傚
 */
public class CalNFactorial {

	public static void main(String[] args) {
		System.out.println("5 腔論傚 : " + new CalNFactorial().calNFact(5));
	}
	
	/**
	 * 數呾論傚
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
