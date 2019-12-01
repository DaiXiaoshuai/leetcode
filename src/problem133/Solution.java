package problem133;

import common.Node;

import java.util.*;

public class Solution {
    List<Node> source = new ArrayList<>();
    List<Node> copy =  new ArrayList<Node>();

    public Node cloneGraph(Node node) {
        bfs(node);
        return copy.get(0);
    }

    public void bfs(Node node){
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Node> visited = new ArrayList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node head = queue.poll();
            if(head != null){
                visited.add(head);
                Node cp;
                if(source.indexOf(head) == -1) {
                    cp = new Node();
                    copy.add(cp);
                    cp.val = head.val;
                    cp.neighbors = new LinkedList<>();
                    source.add(head);
                }else{
                    cp = source.get(source.indexOf(head));
                    if(cp.neighbors == null)  cp.neighbors = new LinkedList<>();
                }
                Iterator<Node> listIt = head.neighbors.iterator();
                while (listIt.hasNext()){
                    Node item = listIt.next();
                    int index = source.indexOf(item);
                    if(index == -1){
                        source.add(item);
                        copy.add(new Node(item.val, null));
                    }
                    index = source.indexOf(item);
                    cp.neighbors.add(copy.get(index));
                    if(visited.indexOf(item) == -1){
                        queue.add(item);
                    }
                }
            }
        }
    }



}
