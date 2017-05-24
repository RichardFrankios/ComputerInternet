package wsj.structure.graph;


/**
 * Dijkstra graph : �����·��.
 *
 */
public class GraphDijkstra {


	/**
	 * ���·��, Dijkstra �㷨.
	 */
	/**
	 * @param graph      ͼ
	 * @param maxVertexs ��󶥵���.
	 */
	public static void shortestPathDijstra(Graph graph){
		
		final int maxWeight = graph.MAX_WEIGHT;
		final int verCount = graph.getVertexSize();
		
		int index = 0 , min = maxWeight ;
		// ����������㵽V0 ����С·��.
		int shortestPathTable[] = new int[verCount];
		// ���涥���Ƿ��Ѿ�ȷ�����·��.
		boolean isGetShortestPath[] = new boolean[verCount];
		
		// 1. ����. �������㵽V0 ����̾�������ڽӾ����еĵ�һ��
		
		for(int i = 0; i < verCount ; i ++ ){
			shortestPathTable[i] = graph.getMatrix()[0][i];
		}
		
		// 2. ��ʼ��, Vo--> Vo ��С������ 0
		shortestPathTable[0] = 0;
		isGetShortestPath[0] = true;
		
		
		// 3. ѭ��ȷ���������㵽Vo �����·��.
		for(int i = 1; i < verCount ; i ++){
			
			min = maxWeight;
			// 4. ��ȡȥ����, V0 �����������о�����С�Ķ���
			for(int j = 1; j < verCount; j ++ ){
				if (!isGetShortestPath[j] && shortestPathTable[j] <min) {
					min = shortestPathTable[j];
					index = j;
				}
			}
			
			// 5. ����ѭ����ȡ���Ķ�������̾�����̵Ķ�, ��˴˾���϶�С�ھ��������Ķ����ٵ���õ�ľ���.
			//    Ҳ����˵, ���ڿ���ȷ����ʱ�ľ�����Ǹõ�����·�� . 
			isGetShortestPath[index] = true;
			// 6. ����û��������·���ĵ㵽��V0����ʱ��̾���.��ʵҲ������Щ������ȷ���ĵĵ�ĵ���V0��·���Ƿ�ȱ����·������.
			for(int j = 1; j < verCount ; j ++){
				// ���� : (V0-->V��) + (V��--> V����);
				// V0���µ�ľ��� + �µ㵽�����������.
				int distance = min + graph.getMatrix()[index][j];
				if (!isGetShortestPath[j] && (distance < shortestPathTable[j])) {
					shortestPathTable[j] = distance;
				}
			}
		}
		// 7. ������·��.
		for(int i = 0 ; i < verCount ; i ++){
			System.out.println("V0-->V" + i + "��̾��� : " + shortestPathTable[i]);
		}
	}
	
	
	
	public static void main(String[] args) {
		Graph graph = new Graph(9);
		graph.createGraph();
		shortestPathDijstra(graph);
	}

}

















