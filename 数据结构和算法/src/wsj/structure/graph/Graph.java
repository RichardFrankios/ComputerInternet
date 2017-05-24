package wsj.structure.graph;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.naming.InitialContext;

/**
 * ����ͼ��ʹ��
 * @author WSJ
 *
 */
public class Graph {
	
	private int vertexSize ; // �������
	private int vertexs[] ; // ��������.
	private int matrix[][] ; // �ڽӾ���
	public final static int MAX_WEIGHT = 1000; // Ȩֵ����
	
	private boolean isVisited[];// ���ʱ��
	
	public int[] getVertexs() {
		return vertexs;
	}

	public void setVertexs(int[] vertexs) {
		this.vertexs = vertexs;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}


	public Graph(int vertexSize){
		this.vertexSize = vertexSize;
		matrix = new int[vertexSize][vertexSize];
		vertexs = new int[vertexSize];

		// ��ʼ������.
		for (int i = 0; i < vertexSize; i++) {
			vertexs[i] = i;
		}
		
	}
	
	/**
	 * ��ȡָ������ĳ���.  ����Ӧ�ò�����.
	 */
	public int getOutDegree(int vertex) {
		int degree = 0;
		for (int i = 0; i < vertexSize; i++) {
			if (matrix[vertex][i] > 0 && matrix[vertex][i] < MAX_WEIGHT) {
				degree ++;
			}
		}
		return degree;
	}
	/**
	 * ��ȡ���. ��ȿ���
	 */
	public int getInDegree(int vertex) {
		int degree = 0;
		for(int i = 0 ;i < vertexSize ; i ++){
			if (matrix[i][vertex] > 0 && matrix[i][vertex] < MAX_WEIGHT) {
				degree ++ ;
			}
		}
		return degree;
	}
	/**
	 * ��ȡ���������Ȩֵ.
	 * @param v1 ����1
	 * @param v2 ����2
	 * @return Ȩֵ, 
	 */
	public int  getWeight(int v1, int v2) {
		int w = matrix[v1][v2];
		// ����� 0 Ҳֱ�ӷ������ﲻ�����ж���.
		return w >= MAX_WEIGHT ? -1 : w;
	}
	/**
	 * ��ȡĳ������ĵ�һ���ڽӵ�.
	 */
	public int getFirstNeighbor(int vertex) {
		for(int i = 0; i < vertexSize ;i ++){
			if (matrix[vertex][i] > 0 && matrix[vertex][i] < MAX_WEIGHT) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * ���ݵ�ǰ���ڽӵ�, ����ָ���������һ���ڽӵ�.
	 * @param v1 Ҫ�����ڽӵ�Ķ���.
	 * @param v2 ��ǰ�ڽӵ�.
	 * @return ��һ���ڽӵ�.
	 */
	public int getNextNeighbor(int v1 , int v2) {
		for(int i = v2 + 1; i < vertexSize ;i ++){
			if (matrix[v1][i] > 0 && matrix[v1][i] < MAX_WEIGHT) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * ���ڵ�����ͨͼ��������ȱ���
	 */
	private void depthFirstSearch(int i) {
		// 1. ���ʵ�ǰ�ڵ�.
		System.out.println("�ڵ� : " + i);
		isVisited[i] = true; 
		
		// 2. ��ȡ��һ���ڽӵ�
		int w = getFirstNeighbor(i);
		
		while(w != -1){
			if (!isVisited[w]) {
				// ������ȱ���
				depthFirstSearch(w);
			}
			
			// i �ڵ������ w �ڵ����һ���ڽӵ�
			w = getNextNeighbor(i, w);
		}
	}
	/**
	 * ���⿪�ŵ�������ȱ���
	 */
	public void depthFirstSearch() {
		isVisited = new boolean[vertexSize];
		
		for(int i = 0; i < vertexSize ; i ++){
			// ���ڷ���ͨͼ, ��Ҫ��ν��б���.
			if (!isVisited[i]) {
				depthFirstSearch(i);
			}
		}
	}
	
	/**
	 * ������ͨͼ������ȱ���
	 * ˼�� : 
	 * 		1. ����һ���ڵ������. 
	 * 		2. ������, ���ʸýڵ�, Ȼ��Ŷ���ýڵ��û�б����ʹ����ڽӵ������,
	 * 		3. ���ѭ��.
	 */
	public void broadFirstSearch(int i) {
		// LinkedList ʵ����Queue�ӿ�.
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offer(i);
		while(!queue.isEmpty()){
			int current = (int)queue.poll();
			if (!isVisited[current]) {
				System.out.println("�ڵ� : " + current);
				isVisited[current] = true;
			}
			int neighbor = getFirstNeighbor(current);
			while(neighbor != -1){
				if (!isVisited[neighbor]) {
					queue.offer(neighbor);					
				}
				neighbor = getNextNeighbor(current, neighbor);
			}
		}
	}
	/**
	 * ���⿪���Ĺ�����ȱ���
	 */
	public void broadFirstSearch() {
		isVisited = new boolean[vertexSize];
		for(int i = 0; i < vertexSize ; i++){
			// Ӧ�Է���ͨͼ�����.
			if (!isVisited[i]) {
				broadFirstSearch(i);
			}
		}
	}
	
	/**
	 * ��С������, prim �㷨.
	 */
	public void miniTreePrim() {
		// ����Ȩֵ. 0 ��ʾ�Ѿ����뵽��С������.
		int lowcast[] = new int[vertexSize];
		// ��С�����������±�. 
		int adjvex [] = new int[vertexSize];
		
		int min, minId, sum;
		
		// 1. ��ʼ����С������.
		lowcast[0] = 0;// Ȩֵ�� 0.
		adjvex[0] = 0; // ��һ�������� 0
		minId = 0;
		sum = 0;
		
		// 2. �����������ж��㵽��С��������Ȩֵ.
		for(int i = 1; i < vertexSize ; i++){
			lowcast[i] = matrix[0][i];
		}
		
		// 3. ѭ������������㵽��С������.
		for(int i = 1; i< vertexSize ; i ++){
			min = MAX_WEIGHT;
			
			// ������С����ЧȨֵ
			for(int j = 1; j < vertexSize ; j ++){
				if (lowcast[j] > 0 && lowcast[j] < min) {
					min = lowcast[j];
					minId = j;
				}
			}
			// �������ӽ���С�������Ķ���
			System.out.println("���� : " + minId + "---ֵ : " + min);
			adjvex[i] = minId;
			sum += min;
			
			// ���øö���Ϊ 0 ��ʾ����������.
			lowcast[minId] = 0;
			
			// �������ж��㵽��С���ɵ���СȨֵ.
			// ��ʵ��Ҫ���Ǹ���, �������㵽�¼����������Ķ������СȨֵ.
			for(int j = 1; j < vertexSize ; j ++){
				if (lowcast[j] != 0 && lowcast[j] > matrix[minId][j]) {
					lowcast[j] = matrix[minId][j];
				}
			}
		}
		System.out.println("���·�� : " + sum);
		
	}
	
	
	
	
	
	
	
	

	public int getVertexSize() {
		return vertexSize;
	}



	public void setVertexSize(int vertexSize) {
		this.vertexSize = vertexSize;
	}
	


	public static void main(String[] args) {
		// �������.
		Graph graph = initGraph();
		
		graph.miniTreePrim();
		
		
//		System.out.println("���� 4 �ĳ��� : " + graph.getOutDegree(4));
//		System.out.println("���� 0 ����� : " + graph.getInDegree(0));
		
//		System.out.println("������ȱ��� : ");
//		graph.depthFirstSearch();
		
//		System.out.println("������ȱ��� : ");
//		graph.broadFirstSearch();
		
//		System.out.println(graph.getFirstNeighbor(0));
//		System.out.println(graph.getNextNeighbor(0, 1));
		
		
		
		
	}


	/**
	 * �������.
	 */
	private static Graph initGraph() {
		Graph graph = new Graph(9);
		
		int [] a1 = new int[]{0,10,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,11,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
		int [] a2 = new int[]{10,0,18,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,16,MAX_WEIGHT,12};
		int [] a3 = new int[]{MAX_WEIGHT,MAX_WEIGHT,0,22,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,8};
		int [] a4 = new int[]{MAX_WEIGHT,MAX_WEIGHT,22,0,20,MAX_WEIGHT,MAX_WEIGHT,16,21};
		int [] a5 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,20,0,26,MAX_WEIGHT,7,MAX_WEIGHT};
		int [] a6 = new int[]{11,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,26,0,17,MAX_WEIGHT,MAX_WEIGHT};
		int [] a7 = new int[]{MAX_WEIGHT,16,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,17,0,19,MAX_WEIGHT};
		int [] a8 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,16,7,MAX_WEIGHT,19,0,MAX_WEIGHT};
		int [] a9 = new int[]{MAX_WEIGHT,12,8,21,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,0};
		
		graph.matrix[0] = a1;
		graph.matrix[1] = a2;
		graph.matrix[2] = a3;
		graph.matrix[3] = a4;
		graph.matrix[4] = a5;
		graph.matrix[5] = a6;
		graph.matrix[6] = a7;
		graph.matrix[7] = a8;
		graph.matrix[8] = a9;
		return graph;
	}
	/**
	 * ����ͼ�Ĺ���, Dijkstra
	 */
	public void createGraph(){
		int [] a1 = new int[]{0,1,5,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
		int [] a2 = new int[]{1,0,3,7,5,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
		int [] a3 = new int[]{5,3,0,MAX_WEIGHT,1,7,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
		int [] a4 = new int[]{MAX_WEIGHT,7,MAX_WEIGHT,0,2,MAX_WEIGHT,3,MAX_WEIGHT,MAX_WEIGHT};
		int [] a5 = new int[]{MAX_WEIGHT,5,1,2,0,3,6,9,MAX_WEIGHT};
		int [] a6 = new int[]{MAX_WEIGHT,MAX_WEIGHT,7,MAX_WEIGHT,3,0,MAX_WEIGHT,5,MAX_WEIGHT};
		int [] a7 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,3,6,MAX_WEIGHT,0,2,7};
		int [] a8 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,9,5,2,0,4};
		int [] a9 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,7,4,0};
		
		matrix[0] = a1;
		matrix[1] = a2;
		matrix[2] = a3;
		matrix[3] = a4;
		matrix[4] = a5;
		matrix[5] = a6;
		matrix[6] = a7;
		matrix[7] = a8;
		matrix[8] = a9;
	}

}
