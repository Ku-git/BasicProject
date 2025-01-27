package com.home;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomGraphImpl {

	private HashMap<String, LinkedList<String>> adjacentList;
	private int numberOfNodes;
	
	public CustomGraphImpl() {
		this.adjacentList = new HashMap<>();
		this.numberOfNodes = 0;
	}
	
	public void addVertex(String node) {
		if(!adjacentList.containsKey(node)) {
			adjacentList.put(node, new LinkedList<String>());
			numberOfNodes++;
		}
	}
	
	public void addEdge(String node1, String node2) {
		if(adjacentList.containsKey(node1)) {
			List<String> connectData = adjacentList.get(node1);
			connectData.add(node2);
		}
		if(adjacentList.containsKey(node2)) {
			List<String> connectData = adjacentList.get(node2);
			connectData.add(node1);
		}
	}
	
	public String showConnections() {
		StringBuilder builder = new StringBuilder();
		adjacentList.entrySet().stream()
			.forEach(entry -> {
				builder.append(entry.getKey() + "-->" + 
						entry.getValue().stream()
						.collect(Collectors.joining(",")));
				builder.append("\n");
			});
		return builder.toString();
	}
	
	// BFS算法
    public void bfs(String startVertex) {
        Set<String> visited = new HashSet<String>(); // 用於標記頂點是否已被訪問
        Queue<String> queue = new LinkedList<>(); // 用於存儲待處理的頂點

        visited.add(startVertex); // 將起始頂點標記為已訪問
        queue.add(startVertex); // 將起始頂點加入隊列

        while (!queue.isEmpty()) {
            String currentVertex = queue.poll(); // 從隊列中取出一個頂點
            System.out.print(currentVertex + " "); // 處理當前頂點（這裡只是簡單地打印出來）

            LinkedList<String> neighbors = adjacentList.get(String.valueOf(currentVertex)); // 獲取當前頂點的鄰接表
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) { // 如果鄰接頂點尚未訪問過
                    visited.add(neighbor); // 將鄰接頂點標記為已訪問
                    queue.add(neighbor); // 將鄰接頂點加入隊列
                }
            }
        }
    }
	
	public static void main(String[] args) {
		CustomGraphImpl graphImpl = new CustomGraphImpl();
		graphImpl.addVertex("0");
		graphImpl.addVertex("1");
		graphImpl.addVertex("2");
		graphImpl.addVertex("3");
		graphImpl.addVertex("4");
		graphImpl.addVertex("5");
		graphImpl.addVertex("6");
		graphImpl.addEdge("3", "1"); 
		graphImpl.addEdge("3", "4"); 
		graphImpl.addEdge("4", "2"); 
		graphImpl.addEdge("4", "5"); 
		graphImpl.addEdge("1", "2"); 
		graphImpl.addEdge("1", "0"); 
		graphImpl.addEdge("0", "2"); 
		graphImpl.addEdge("6", "5");

		String result = graphImpl.showConnections(); 
		System.out.println(result);
		System.out.println("node numbers: " + graphImpl.numberOfNodes);
		
		graphImpl.bfs("0");
	}
}
