package wsj.algorithm.dp;

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

/**
 * ���ݷ� ---> 8�ʺ�����.
 *
 */
public class EightQueen {

	// ���ʺ����.
	public static final int MAX_QUEEN_COUTN = 8;
	// ÿ���ϻʺ�ķ���λ��.
	public static int[] cols = new int[MAX_QUEEN_COUTN];
	// ������
	public int num = 0;
	
	/**
	 * ��ȡ��n���ʺ��λ��.
	 * @param n
	 */
	public void getCount(int n){
		// ��¼��n����λ��״̬.
		boolean[] rows = new boolean[MAX_QUEEN_COUTN];
		int delta = 0;
		Arrays.fill(rows, true);
		
		for(int i =0 ; i < n ;i ++){
			// ͬ�в�����.
			rows[cols[i]] = false;
			// ���Ͻ�
			delta = n - i;
			if (cols[i] + delta < MAX_QUEEN_COUTN) {
				rows[cols[i] + delta] = false;
			}
			// ���½�
			if (cols[i] - delta >= 0) {
				rows[cols[i] - delta] = false;
			}
		}
		// ��ʱ�Ϳ��Կ���ʹ�õľ��Ѿ�������. �������õ�.
		for(int i= 0 ;i < MAX_QUEEN_COUTN ; i ++){
			if (!rows[i]) {
				continue;
			}
			cols[n] = i;
			
			if (n < MAX_QUEEN_COUTN-1) {
				// �������һ��, ��ȡ��һ��.
				getCount(n + 1);
			}else {
				// ���һ��. ���.һ��
				num ++;
				printQueen();	
			}
		}
	}
	
	/**
	 * ��ӡ�ʺ�.
	 */
	private void printQueen() {
		
		System.out.println("���� : " + num);
		char[][] queen = new char[MAX_QUEEN_COUTN][MAX_QUEEN_COUTN];
		for(int i= 0 ;i < MAX_QUEEN_COUTN ;i++)
			Arrays.fill(queen[i], '+');
		for(int i= 0 ;i < MAX_QUEEN_COUTN ;i++){
			queen[cols[i]][i] = 'Q';
		}
		for(int i = 0;i < MAX_QUEEN_COUTN ; i++){
			System.out.println(Arrays.toString(queen[i]));
		}
	}




	public static void main(String[] args) {
		new EightQueen().getCount(0);
		
	}

}
