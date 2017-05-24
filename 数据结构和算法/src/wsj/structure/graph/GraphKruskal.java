package wsj.structure.graph;

import java.util.Arrays;

/**
 * ��³˹�����㷨.
 */
public class GraphKruskal {
	private Edge[] edges;
	private int edgeSize;
	public GraphKruskal(int edgeSize) {
		this.edgeSize = edgeSize;
		edges = new Edge[edgeSize];
	
	}
	/**
	 * ��³˹�����㷨, 
	 */
	private void miniSpanTreeKruskal() {
		// n, ���, ����λ��.
		// m, �յ�����λ��,
		// n,m ������¼, �Ѿ���ͨ��λ��. 
		int n , m, sum = 0 ;
		// ���������, �����������С���������Ѿ���ͨ�Ķ���. 
		// �±� : ���.
		// ֵ : �յ�.
		int[] parent = new int[edgeSize];
		Arrays.fill(parent, 0);
		// �������еı�
		for(int i = 0; i < edgeSize ; i++){
			n = find(parent, edges[i].begin);
			m = find(parent, edges[i].end);
			if (m != n) {
				// û�л�·, ����·��.
				System.out.println("�� : " + edges[i].begin + "~" + edges[i].end + "  ֵ :" + edges[i].weight);
				parent[n] = m;
				sum += edges[i].weight;
			}
		}
		System.out.println("��С·�� : " + sum);
	}
	
	
	
	
	/**
	 * ������, parent��, begin��ͷ��·���ϵ��յ�.
	 * @param parent : ��С�� . �������е�[�±�, ֵ] ��һ����.
	 * @param begin  : ��ʼ��.
	 * @return
	 */
	private int find(int[] parent, int begin) {
		while(parent[begin] > 0){
			begin = parent[begin];
		}
		// System.out.println("=======begin = " + begin);
		return begin;
	}
	public void createEdgeArray(){
		Edge edge0 = new Edge(4,7,7);
		Edge edge1 = new Edge(2,8,8);
		Edge edge2 = new Edge(0,1,10);
		Edge edge3 = new Edge(0,5,11);
		Edge edge4 = new Edge(1,8,12);
		Edge edge5 = new Edge(3,7,16);
		Edge edge6 = new Edge(1,6,16);
		Edge edge7 = new Edge(5,6,17);
		Edge edge8 = new Edge(1,2,18);
		Edge edge9 = new Edge(6,7,19);
		Edge edge10 = new Edge(3,4,20);
		Edge edge11 = new Edge(3,8,21);
		Edge edge12 = new Edge(2,3,22);
		Edge edge13 = new Edge(3,6,24);
		Edge edge14 = new Edge(4,5,26);
		edges[0] = edge0;
		edges[1] = edge1;
		edges[2] = edge2;
		edges[3] = edge3;
		edges[4] = edge4;
		edges[5] = edge5;
		edges[6] = edge6;
		edges[7] = edge7;
		edges[8] = edge8;
		edges[9] = edge9;
		edges[10] = edge10;
		edges[11] = edge11;
		edges[12] = edge12;
		edges[13] = edge13;
		edges[14] = edge14;
	}
	class Edge{
		private int begin;
		private int end;
		private int weight;
		public Edge(int begin, int end, int weight) {
			super();
			this.begin = begin;
			this.end = end;
			this.weight = weight;
		}
		public int getBegin() {
			return begin;
		}
		public void setBegin(int begin) {
			this.begin = begin;
		}
		public int getEnd() {
			return end;
		}
		public void setEnd(int end) {
			this.end = end;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		
	}
	
	
	public static void main(String[]args){
		GraphKruskal graphKruskal = new GraphKruskal(15);
		graphKruskal.createEdgeArray();
		graphKruskal.miniSpanTreeKruskal();
	}




	
}
