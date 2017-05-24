package wsj.structure.graph;


/**
 * Dijkstra graph : 求最短路径.
 *
 */
public class GraphDijkstra {


	/**
	 * 最短路径, Dijkstra 算法.
	 */
	/**
	 * @param graph      图
	 * @param maxVertexs 最大顶点数.
	 */
	public static void shortestPathDijstra(Graph graph){
		
		final int maxWeight = graph.MAX_WEIGHT;
		final int verCount = graph.getVertexSize();
		
		int index = 0 , min = maxWeight ;
		// 保存各个顶点到V0 的最小路径.
		int shortestPathTable[] = new int[verCount];
		// 保存顶点是否已经确定最短路径.
		boolean isGetShortestPath[] = new boolean[verCount];
		
		// 1. 假设. 各个顶点到V0 的最短距离就是邻接矩阵中的第一行
		
		for(int i = 0; i < verCount ; i ++ ){
			shortestPathTable[i] = graph.getMatrix()[0][i];
		}
		
		// 2. 初始化, Vo--> Vo 最小距离是 0
		shortestPathTable[0] = 0;
		isGetShortestPath[0] = true;
		
		
		// 3. 循环确定各个顶点到Vo 的最短路径.
		for(int i = 1; i < verCount ; i ++){
			
			min = maxWeight;
			// 4. 获取去现在, V0 到各个顶点中距离最小的顶点
			for(int j = 1; j < verCount; j ++ ){
				if (!isGetShortestPath[j] && shortestPathTable[j] <min) {
					min = shortestPathTable[j];
					index = j;
				}
			}
			
			// 5. 上面循环获取到的顶点是最短距离最短的额, 因此此距离肯定小于经过其他的顶点再到达该点的距离.
			//    也就是说, 现在可以确定此时的距离就是该点的最短路径 . 
			isGetShortestPath[index] = true;
			// 6. 更新没有求出最短路径的点到达V0的暂时最短距离.其实也就是那些经过上确定的的点的到达V0的路径是否比保存的路径更短.
			for(int j = 1; j < verCount ; j ++){
				// 计算 : (V0-->V上) + (V上--> V其他);
				// V0到新点的距离 + 新点到其他个点距离.
				int distance = min + graph.getMatrix()[index][j];
				if (!isGetShortestPath[j] && (distance < shortestPathTable[j])) {
					shortestPathTable[j] = distance;
				}
			}
		}
		// 7. 输出最短路径.
		for(int i = 0 ; i < verCount ; i ++){
			System.out.println("V0-->V" + i + "最短距离 : " + shortestPathTable[i]);
		}
	}
	
	
	
	public static void main(String[] args) {
		Graph graph = new Graph(9);
		graph.createGraph();
		shortestPathDijstra(graph);
	}

}

















