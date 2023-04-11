package algorithm;

import attraction.AttractionInfo;

import java.util.*;

public class ShortestPath {

    private static final int INF = (int) 1e9;
    private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> visited = new ArrayList<>();
    private static double[] d = new double[1001];

    public List<AttractionInfo> getShortestPath(List<AttractionInfo> attractionInfos) {
        int n = attractionInfos.size();
        Map<Integer, AttractionInfo> store = new HashMap<>();
        for (int i = 0; i < n; i++) {
            store.put(i + 1, attractionInfos.get(i));
        }
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            visited.add(new ArrayList<>());
        }
        Arrays.fill(d, INF);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                AttractionInfo start = attractionInfos.get(i);
                AttractionInfo end = attractionInfos.get(j);
                double cost = getDistance(start, end);
                graph.get(i).add(new Node(j, cost));
                graph.get(j).add(new Node(i, cost));
            }
        }
        int start = 1;
        int end = n;

        dijkstra(start);

        List<AttractionInfo> result = new ArrayList<>();
        for (int index : visited.get(end)) {
            result.add(store.get(index));
        }
        return result;
    }

    private void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;
        visited.get(start).add(start);
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            double distance = node.getCost();
            int now = node.getIndex();
            if (d[now] < distance) {
                continue;
            }
            for (int i = 0; i < graph.get(now).size(); i++) {
                double cost = d[now] + graph.get(now).get(i).getCost();
                if (d[graph.get(now).get(i).getIndex()] >= cost) {
                    visited.set(graph.get(now).get(i).getIndex(), new ArrayList<>());
                    for (int index : visited.get(now)) {
                        visited.get(graph.get(now).get(i).getIndex()).add(index);
                    }
                    visited.get(graph.get(now).get(i).getIndex()).add(graph.get(now).get(i).getIndex());
                    d[graph.get(now).get(i).getIndex()] = cost;
                    pq.offer(new Node(graph.get(now).get(i).getIndex(), cost));
                }
            }
        }
    }

    private double getDistance(AttractionInfo start, AttractionInfo end) {
        double x = Math.abs(start.getLatitude() - end.getLatitude());
        double y = Math.abs(start.getLongitude() - end.getLongitude());
        return Math.sqrt(x * x + y * y);
    }
}
class Node implements Comparable<Node> {
    private int index;
    private double cost;

    public Node(int index, double cost) {
        this.index = index;
        this.cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public int compareTo(Node o) {
        if (this.cost < o.cost) {
            return -1;
        }
        return 1;
    }
}