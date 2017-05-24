package wsj.structure.graph;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.naming.InitialContext;

/**
 * 介绍图的使用
 * @author WSJ
 *
 */
public class Graph {
	
	private int vertexSize ; // 顶点个数
	private int vertexs[] ; // 顶点数组.
	private int matrix[][] ; // 邻接矩阵
	public final static int MAX_WEIGHT = 1000; // 权值上限
	
	private boolean isVisited[];// 访问标记
	
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

		// 初始化顶点.
		for (int i = 0; i < vertexSize; i++) {
			vertexs[i] = i;
		}
		
	}
	
	/**
	 * 获取指定顶点的出度.  出度应该查找行.
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
	 * 获取入度. 入度看列
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
	 * 获取两个顶点的权值.
	 * @param v1 顶点1
	 * @param v2 顶点2
	 * @return 权值, 
	 */
	public int  getWeight(int v1, int v2) {
		int w = matrix[v1][v2];
		// 如果是 0 也直接返回这里不进行判断了.
		return w >= MAX_WEIGHT ? -1 : w;
	}
	/**
	 * 获取某个顶点的第一个邻接点.
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
	 * 根据当前的邻接点, 查找指定顶点的下一个邻接点.
	 * @param v1 要查找邻接点的顶点.
	 * @param v2 当前邻接点.
	 * @return 下一个邻接点.
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
	 * 对于单个连通图的深度优先遍历
	 */
	private void depthFirstSearch(int i) {
		// 1. 访问当前节点.
		System.out.println("节点 : " + i);
		isVisited[i] = true; 
		
		// 2. 获取第一个邻接点
		int w = getFirstNeighbor(i);
		
		while(w != -1){
			if (!isVisited[w]) {
				// 深度优先遍历
				depthFirstSearch(w);
			}
			
			// i 节点相对于 w 节点的下一个邻接点
			w = getNextNeighbor(i, w);
		}
	}
	/**
	 * 对外开放的深度优先遍历
	 */
	public void depthFirstSearch() {
		isVisited = new boolean[vertexSize];
		
		for(int i = 0; i < vertexSize ; i ++){
			// 对于非连通图, 需要多次进行遍历.
			if (!isVisited[i]) {
				depthFirstSearch(i);
			}
		}
	}
	
	/**
	 * 单个连通图广度优先遍历
	 * 思想 : 
	 * 		1. 将第一个节点入队列. 
	 * 		2. 出队列, 访问该节点, 然后哦将该节点的没有被访问过的邻接点入队列,
	 * 		3. 如此循环.
	 */
	public void broadFirstSearch(int i) {
		// LinkedList 实现了Queue接口.
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offer(i);
		while(!queue.isEmpty()){
			int current = (int)queue.poll();
			if (!isVisited[current]) {
				System.out.println("节点 : " + current);
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
	 * 对外开方的广度优先遍历
	 */
	public void broadFirstSearch() {
		isVisited = new boolean[vertexSize];
		for(int i = 0; i < vertexSize ; i++){
			// 应对非连通图的情况.
			if (!isVisited[i]) {
				broadFirstSearch(i);
			}
		}
	}
	
	/**
	 * 最小生成树, prim 算法.
	 */
	public void miniTreePrim() {
		// 顶点权值. 0 表示已经加入到最小生成树.
		int lowcast[] = new int[vertexSize];
		// 最小生成树顶点下标. 
		int adjvex [] = new int[vertexSize];
		
		int min, minId, sum;
		
		// 1. 初始化最小生成树.
		lowcast[0] = 0;// 权值是 0.
		adjvex[0] = 0; // 第一个顶点是 0
		minId = 0;
		sum = 0;
		
		// 2. 其他更新所有顶点到最小生成树的权值.
		for(int i = 1; i < vertexSize ; i++){
			lowcast[i] = matrix[0][i];
		}
		
		// 3. 循环添加其他顶点到最小生成树.
		for(int i = 1; i< vertexSize ; i ++){
			min = MAX_WEIGHT;
			
			// 查找最小的有效权值
			for(int j = 1; j < vertexSize ; j ++){
				if (lowcast[j] > 0 && lowcast[j] < min) {
					min = lowcast[j];
					minId = j;
				}
			}
			// 输出新添加进最小生成树的顶点
			System.out.println("顶点 : " + minId + "---值 : " + min);
			adjvex[i] = minId;
			sum += min;
			
			// 设置该顶点为 0 表示加入生成树.
			lowcast[minId] = 0;
			
			// 更新所有顶点到最小生成的最小权值.
			// 其实主要就是更新, 其他顶点到新加入生成树的顶点的最小权值.
			for(int j = 1; j < vertexSize ; j ++){
				if (lowcast[j] != 0 && lowcast[j] > matrix[minId][j]) {
					lowcast[j] = matrix[minId][j];
				}
			}
		}
		System.out.println("最短路径 : " + sum);
		
	}
	
	
	
	
	
	
	
	

	public int getVertexSize() {
		return vertexSize;
	}



	public void setVertexSize(int vertexSize) {
		this.vertexSize = vertexSize;
	}
	


	public static void main(String[] args) {
		// 构造矩阵.
		Graph graph = initGraph();
		
		graph.miniTreePrim();
		
		
//		System.out.println("顶点 4 的出度 : " + graph.getOutDegree(4));
//		System.out.println("顶点 0 的入度 : " + graph.getInDegree(0));
		
//		System.out.println("深度优先遍历 : ");
//		graph.depthFirstSearch();
		
//		System.out.println("广度优先遍历 : ");
//		graph.broadFirstSearch();
		
//		System.out.println(graph.getFirstNeighbor(0));
//		System.out.println(graph.getNextNeighbor(0, 1));
		
		
		
		
	}


	/**
	 * 构造矩阵.
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
	 * 创建图的过程, Dijkstra
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
