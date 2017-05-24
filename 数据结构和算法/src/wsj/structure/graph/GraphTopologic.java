package wsj.structure.graph;

import java.util.Stack;

/**
 * ��������
 */
public class GraphTopologic {

	/**
	 * 
	 */
	private int vertexCount;
	private VertexNode adjList[];

	public GraphTopologic(int vertexCount) {
		super();
		this.vertexCount = vertexCount;
	}
	private void createGraph(){
		VertexNode node0 = new VertexNode(0,"v0");
		VertexNode node1 = new VertexNode(0,"v1");
		VertexNode node2 = new VertexNode(2,"v2");
		VertexNode node3 = new VertexNode(0,"v3");
		VertexNode node4 = new VertexNode(2,"v4");
		VertexNode node5 = new VertexNode(3,"v5");
		VertexNode node6 = new VertexNode(1,"v6");
		VertexNode node7 = new VertexNode(2,"v7");
		VertexNode node8 = new VertexNode(2,"v8");
		VertexNode node9 = new VertexNode(1,"v9");
		VertexNode node10 = new VertexNode(1,"v10");
		VertexNode node11 = new VertexNode(2,"v11");
		VertexNode node12 = new VertexNode(1,"v12");
		VertexNode node13 = new VertexNode(2,"v13");
		adjList = new VertexNode[vertexCount];
		adjList[0] =node0;
		adjList[1] =node1;
		adjList[2] =node2;
		adjList[3] =node3;
		adjList[4] =node4;
		adjList[5] =node5;
		adjList[6] =node6;
		adjList[7] =node7;
		adjList[8] =node8;
		adjList[9] =node9;
		adjList[10] =node10;
		adjList[11] =node11;
		adjList[12] =node12;
		adjList[13] =node13;
		node0.firstEdge = new EdgeNode(11);
		node0.firstEdge.next = new EdgeNode(5);
		node0.firstEdge.next.next = new EdgeNode(4);
		node1.firstEdge = new EdgeNode(8);node1.firstEdge.next = new EdgeNode(4);node1.firstEdge.next.next = new EdgeNode(2);
		node2.firstEdge = new EdgeNode(9);node2.firstEdge.next = new EdgeNode(6);node2.firstEdge.next.next = new EdgeNode(5);
		node3.firstEdge = new EdgeNode(13);node3.firstEdge.next = new EdgeNode(2);
		node4.firstEdge = new EdgeNode(7);
		node5.firstEdge = new EdgeNode(12);node5.firstEdge.next = new EdgeNode(8);
		node6.firstEdge = new EdgeNode(5);
		node8.firstEdge = new EdgeNode(7);
		node9.firstEdge = new EdgeNode(11);node9.firstEdge.next = new EdgeNode(10);
		node10.firstEdge = new EdgeNode(13);
		node12.firstEdge = new EdgeNode(9);
	}
	
	/**
	 * �ڽӶ���. ����ڵ�.
	 */
	class VertexNode{
		private String data;
		// ���.
		private int in;
		// ����.
		private EdgeNode firstEdge;
		
		public VertexNode(int in,String data){
			this.in = in;
			this.data = data;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public int getIn() {
			return in;
		}

		public void setIn(int in) {
			this.in = in;
		}

		public EdgeNode getFirst() {
			return firstEdge;
		}

		public void setFirst(EdgeNode first) {
			this.firstEdge = first;
		}
	}
	/**
	 * �߱���. Ҳ�����ڽӱ��е�����ڵ�.
	 */
	class EdgeNode{
		// ��һ��
		EdgeNode next;
		// �±�
		private int verIndex;
		public EdgeNode( int verIndex) {
			this.verIndex = verIndex;
		}
		public EdgeNode getNext() {
			return next;
		}
		public void setNext(EdgeNode next) {
			this.next = next;
		}
		public int getVerIndex() {
			return verIndex;
		}
		public void setVerIndex(int verIndex) {
			this.verIndex = verIndex;
		}
	}
	
	
	/**
	 * ��������.ʹ��ջ
	 */
	public void topologicalSort(){
		
		Stack<Integer> stack = new Stack<>();
		
		int count = 0, index = 0;
		
		// 1. �����е����Ϊ0 �ĵ���ջ
		for(int i = 0; i < vertexCount ; i ++){
			if (adjList[i].in == 0) {
				stack.push(i);
			}
		}
		// 2. ����
		while(!stack.isEmpty()){
			int pop = stack.pop();
			System.out.println("���� : " + adjList[pop].data);
			
			count ++;
			
			// 3. ����ö����к��, Ҳ�����ڽӱ����д�.�����е����е�Ԫ����� -1;
			for(EdgeNode node = adjList[pop].firstEdge; node != null ; node = node.next){
				index = node.verIndex;
				if (--adjList[index].in == 0) {
					stack.push(index);
				}
			}
		}
		if (count < vertexCount) {
			System.out.println("����ʧ��");
		}
		
	}
	public static void main(String[] args) {
		GraphTopologic graphTopologic = new GraphTopologic(14);
		graphTopologic.createGraph();
		graphTopologic.topologicalSort();
	}

}
