package com.home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {

	public static void dijkstra(Graph graph, int source) {
		int size = graph.size;
		int[] distance = new int[size];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator
				.comparingInt(node -> node.distance));
		distance[source] = 0;
		priorityQueue.offer(new DijkstraAlgorithm().new Node(source, 0));
		
		while(!priorityQueue.isEmpty()) {
			// 取出優先級佇列中最小距離的節點
			Node node = priorityQueue.poll();
			int vertex = node.vertex;
			
			// 檢查與目前節點相鄰的節點
			for(Edge edge: graph.adjacentList[vertex]) {
				int destination = edge.destination;
				int weight = edge.weight;
				
				// 更新距離
				if(distance[vertex] != Integer.MAX_VALUE && 
						distance[vertex] + weight < distance[destination]) {
					distance[destination] = distance[vertex] + weight;
					// 更新優先級佇列中的節點距離
					priorityQueue.offer(new DijkstraAlgorithm().new Node(destination, distance[destination]));
				}
			}
			
			 // 印出最短距離
	        for (int i = 0; i < size; i++) {
	            System.out.println("Vertex " + i + ": Distance = " + distance[i]);
	        }
			
		}
		
	}
	
	public static void main(String[] args) {
		// 建立有向圖
        int size = 5;
        Graph graph = new DijkstraAlgorithm().new Graph(size);
        
        graph.addEdge(0, 1, 7);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 6);
        graph.addEdge(2, 1, 1);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 4);

        
        int source = 0; // 起點
        
        dijkstra(graph, source);
	}
	
	private class Graph {
		int size;
		List<Edge>[] adjacentList;
		
		public Graph(int size) {
			this.size = size;
			adjacentList = new ArrayList[size];
			for(int i = 0; i < size; i++) {
				adjacentList[i] = new ArrayList<>();
			}
		}
		
		public void addEdge(int source, int destination, int weight) {
			Edge edge = new Edge(destination, weight);
			adjacentList[source].add(edge);	
		}
		
	}
	
	private class Edge {
		int destination;
		int weight;
		
		public Edge(int destination, int weight) {
			super();
			this.destination = destination;
			this.weight = weight;
		}

	}
	
	private class Node {
		int vertex;
		int distance;
		
		public Node(int vertex, int distance) {
			super();
			this.vertex = vertex;
			this.distance = distance;
		}
		
	}
	
}
