package wsj.algorithm.exhaustive;

/**
 * ��ٷ�--���ɷھ�
 */
public class ShareWine {

	// ������ƿ.
	int bottle1  = 12;
	int bottle2  = 8;
	int bottle3  = 5;
	// Ŀ�ľ���
	int expect = 0;
	
	public ShareWine(int expect) {
		this.expect = expect;
	}
	
	/**
	 * ���Ʒ���. 
	 * 		���Ʋ��� :
	 * 			(1) b1 --> b2
	 * 			(2) b2 --> b3
	 * 			(3) b3 --> b1
	 * @param bw1 ��һ��ƿ���еľ���
	 * @param bw2 �ڶ���ƿ���еľ���
	 * @param bw3 ������ƿ���еľ���
	 */
	public void pouringWine(int bw1, int bw2, int bw3) {
		
		System.out.println("B1 == " + bw1 + " -- B2 == " + bw2 + " -- B3 == " + bw3);
		
		if (bw1 == expect || bw2 == expect || bw3 == expect) {
			System.out.println("job done !!!");
			return;
		}
		
		if (bw2 != 0 && bw3 < bottle3) {
			// b2 �о�, �� b3 δ��.
			if (bw2 + bw3 > bottle3) { // b2 + b3 ����������. bottle3 ������.
				pouringWine(bw1, bw2 - (bottle3 - bw3), bottle3);
			}else {
				pouringWine(bw1, 0, bw2 + bw3);
			}
		}else if (bw3 == bottle3) {
			// bottle3 �Ѿ�����. B3 ---> B1
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
		// ���ó�ʼ������Ŀ�����.
		new ShareWine(6).pouringWine(12, 0, 0);
	}

}
