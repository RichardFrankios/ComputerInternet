package wsj.algorithm.recursion;


/**
 * ��ŵ�� . �ݹ�
 *
 */
public class HanNoTa {

	
	/**
	 * ��ŵ���㷨.
	 * @param n         ���Ӹ���
	 * @param from      ��ʼλ��
	 * @param dependOn  ����λ��
	 * @param to        Ŀ��λ��
	 */
	public void hanNoTa(int n , char from , char dependOn, char to) {
		if (n == 1) {
			move(1, from , to );
		}else {
			// �� n-1 �����Ӵ�A ���� C Ų��B.
			hanNoTa(n - 1, from, to, dependOn);
			// �� n ��A--C
			move(n, from, to);
			// �� n-1 ����������AŲ��C
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
			// ��һ�� : �� (n-1)�� A ��Ų�� B, ����B��Ϊ��ת.
			hanNoTa1(n-1, from , to, dependOn);
			// �ڶ��� : �� �� n �� ֱ��Ų�� C
			move(n, from, to);
			// ������ : �� (n-1)�� B ��Ų�� C, ����A��Ϊ��ת.
			hanNoTa1(n-1, dependOn, from, to);
		}
	}
	
	
	
	
	
	

}
