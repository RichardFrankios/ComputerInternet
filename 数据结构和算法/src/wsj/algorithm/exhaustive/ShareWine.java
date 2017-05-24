package wsj.algorithm.exhaustive;

/**
 * 穷举法--泊松汾酒
 */
public class ShareWine {

	// 三个酒瓶.
	int bottle1  = 12;
	int bottle2  = 8;
	int bottle3  = 5;
	// 目的酒量
	int expect = 0;
	
	public ShareWine(int expect) {
		this.expect = expect;
	}
	
	/**
	 * 倒酒方法. 
	 * 		倒酒策略 :
	 * 			(1) b1 --> b2
	 * 			(2) b2 --> b3
	 * 			(3) b3 --> b1
	 * @param bw1 第一个瓶子中的酒量
	 * @param bw2 第二个瓶子中的酒量
	 * @param bw3 第三个瓶子中的酒量
	 */
	public void pouringWine(int bw1, int bw2, int bw3) {
		
		System.out.println("B1 == " + bw1 + " -- B2 == " + bw2 + " -- B3 == " + bw3);
		
		if (bw1 == expect || bw2 == expect || bw3 == expect) {
			System.out.println("job done !!!");
			return;
		}
		
		if (bw2 != 0 && bw3 < bottle3) {
			// b2 有酒, 且 b3 未满.
			if (bw2 + bw3 > bottle3) { // b2 + b3 酒量超过了. bottle3 的容量.
				pouringWine(bw1, bw2 - (bottle3 - bw3), bottle3);
			}else {
				pouringWine(bw1, 0, bw2 + bw3);
			}
		}else if (bw3 == bottle3) {
			// bottle3 已经满了. B3 ---> B1
			if (bw3 + bw1 > bottle1) {
				pouringWine(bottle1, bw2, bw3 - (bottle1 - bw1));
			}else {
				pouringWine(bw1 + bw3, bw2, 0);
			}
		}else if(bw2 == 0) {
			if (bw1 > bottle2) {
				pouringWine(bw1 - bottle2, bottle2, bw3);
			}else {
				pouringWine(0, bw1, bw3);
			}
		}
	}
	
	public static void main(String[] args) {
		// 设置初始酒量和目标酒量.
		new ShareWine(6).pouringWine(12, 0, 0);
	}

}
