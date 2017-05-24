package wsj.algorithm.dp;

/**
 * ‘º…™∑“…±»À”Œœ∑
 *
 */
public class JohnGame {

	public int TOTAL_COUNT = 20;
	public int KILL_NUMBER = 5;
	
	
	class Node{
		int value;
		Node next;
		public Node(int v) {
			value = v;
		}
	}
	
	public void killNode() {
		Node header = new Node(0);
		Node x = header;
		
		for(int i = 1; i< TOTAL_COUNT ;i ++){
			x = (x.next = new Node(i));
		}
		x.next = header;
		
		System.out.println("À¿ÕˆÀ≥–Ú : ");
		while(x != x.next){
			for(int i = 0 ;i < KILL_NUMBER; i ++){
				x = x.next;
			}
			System.out.println(x.next.value + " KILLED");
			x.next = x.next.next;
		}
		System.out.println("LUCKY : " + x.value);
		
	}
	public static void main(String[] args) {
		new JohnGame().killNode();
	}

}
