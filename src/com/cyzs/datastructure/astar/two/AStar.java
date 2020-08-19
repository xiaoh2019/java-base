package com.cyzs.datastructure.astar.two;

import java.util.*;


/** A星算法
 * @author xh
 * @description
 * @create 2020-08-17 10:09
 */
public class AStar {

    /*6y行  8x列*/
    /*  第一个[row][col]*/
    private int[][] a = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    /** */
    private int rowMax = 20;
    /** */
    private int colMax = 20;
    /** */
    private int min = 0;


    private ArrayList<Node> open = new ArrayList<>();
    private ArrayList<Node> close = new ArrayList<>();
    private  Node[][] nodeAll = new  Node[rowMax][colMax];


    public List<String> searchRoad(Node start,  Node end){
        Node node = searchNode(start, end);
        if (node != null){
            return endToStart(node);
        }
        return null;
    }

    /**
     * 找到终点node
     * @param start start
     * @param end end
     * @return Node
     */
    public Node searchNode(Node start, Node end){
        initNodeAll();
        close.clear();
        open.clear();
        open.add(start);
        //
        while (!open.isEmpty()){
            //get countValue总代价最小的
            Node minNode = getMinNode(open);
            if (minNode == null){
                System.out.println("error");
                return null;
            }
            //判断从起点到minNode是不是最佳路径

            //如果不是需要修改min的PNode
            Node node1 = search2(start, minNode);
            if (node1 != null && node1.startValue < minNode.startValue){
                //从open获取 node1的父节点
                ArrayList<Node> tmpNodos = new ArrayList<Node>();
                tmpNodos.addAll(open);
                tmpNodos.addAll(close);
                Node pnode = getPnode(tmpNodos, node1.pNode);
                if (pnode != null){
                    minNode.pNode = pnode;
                    open.add(pnode);
                }
            }

            close.add(minNode);

            //获取总代价最小的周围的节点，并且不在open和close里面的
            List<Node> roundNode = getRoundNode(minNode);
            //初始化父节点
            for (Node node : roundNode) {
                node.pNode = minNode;
                node.countValue = getCountValue(node, end);
                node.startValue = getStartValue(node);
                node.endValue = getEndValue(node, end);
            }
            //如果roundNode包含end说明成功找到
            if (isContainEnd(roundNode, end)){
                Node node = find(roundNode, end);
                //从end回溯到起始点
                return node;
            }
            //把minNode从open删除
            open.removeIf(next -> next.col == minNode.col && next.row == minNode.row);
            //把新的周围节点添加到open
            open.addAll(roundNode);
        }
        return null;
    }

    public Node searchNode2(Node start, Node end){
        close.clear();
        open.clear();
        open.add(start);
        //
        while (!open.isEmpty()){
            //get countValue总代价最小的
            Node minNode = getMinNode(open);
            if (minNode == null){
                System.out.println("error");
                return null;
            }
            close.add(minNode);

            //获取总代价最小的周围的节点，并且不在open和close里面的
            List<Node> roundNode = getRoundNode(minNode);
            //初始化父节点
            for (Node node : roundNode) {
                node.pNode = minNode;
                node.countValue = getCountValue(node, end);
                node.startValue = getStartValue(node);
                node.endValue = getEndValue(node, end);
            }
            //如果roundNode包含end说明成功找到
            if (isContainEnd(roundNode, end)){
                Node node = find(roundNode, end);
                //从end回溯到起始点
                return node;
            }
            //把minNode从open删除
            open.removeIf(next -> next.col == minNode.col && next.row == minNode.row);
            //把新的周围节点添加到open
            open.addAll(roundNode);
        }
        return null;
    }


    private Node search2(Node start, Node end){
        AStar aStar = new AStar();
        return aStar.searchNode2(start, end);
    }



    private Node getPnode(List<Node> nodes, Node node){
        for (Node node1 : nodes) {
            if (node.row == node1.row && node.col == node1.col){
                return node1;
            }
        }
        return null;
    }



    private void updateNodeParent(Node upNode, Node byNode){


    }


    public ArrayList<String> endToStart(Node node){
        ArrayList<String> list = new ArrayList<>();
        Node tmp = node;
        while (tmp != null &&node.pNode != null){
            list.add(tmp.name);
            tmp = tmp.pNode;
        }
        Collections.reverse(list);
        return list;
    }

    public Node find(List<Node> nodes, Node node){
        for (Node node1 : nodes) {
            if (node.col == node1.col && node.row == node1.row){
                return node1;
            }
        }
        return null;
    }


    /**
     * 是否包含结束点
     * @param nodes 集合
     * @param end 结束点
     * @return boolean
     */
    public boolean isContainEnd(List< Node> nodes,  Node end){
        for (Node node : nodes) {
            if (node.row == end.row && node.col == end.col){
                return true;
            }
        }
        return false;
    }

    private  Node getMinNode(List< Node> roundNode) {
        Iterator< Node> iterator = roundNode.iterator();
        if (iterator.hasNext()){
             Node node = iterator.next();
            for (Node node1 : close) {
                if (node.col == node1.col && node.row == node1.row){
                    roundNode.remove(node);
                }
            }
        }
        roundNode.sort(new Comparator< Node>() {
            @Override
            public int compare(Node o1,  Node o2) {
                return Double.compare(o1.countValue, o2.countValue);
            }
        });
        if (roundNode.isEmpty()){
            return null;
        }
        return roundNode.get(0);
    }

    /**
     * 总代价
     */
    public double getCountValue(Node node,  Node endNode){
        return getStartValue(node) + getEndValue(node, endNode);
    }

    /**
     *当前节点到起点的代价
     */
    public double getStartValue(Node node){
        double d = 0;
         Node tmp = node;
        while (tmp.pNode != null){
            if (tmp.col != tmp.pNode.col && tmp.row != tmp.pNode.row){
                d += 14;
            }else { d += 10; }
            tmp = tmp.pNode;
        }
        return d;
    }

    /**
     * 当前节点到终点的代价
     */
    public double getEndValue(Node node,  Node endNode){
        return (Math.abs(node.col - endNode.col) + Math.abs(node.row - endNode.row))*10;
    }

    /**
     * centerNode可到达的点
     */
    public List< Node> getRoundNode(Node centerNode){
        ArrayList< Node> nodes = new ArrayList<>();
        /*行*/
        int row = centerNode.row;
        int col = centerNode.col;
        /*左上*/
        if (col - 1 >= min && row - 1 >= min && nodeAll[row-1][col-1].reachable){
            boolean in = isIn(open, nodeAll[row - 1][col - 1]);
            boolean in2 = isIn(close, nodeAll[row - 1][col - 1]);
            if (!in && !in2){
                nodes.add(nodeAll[row-1][col-1]);
            }
        }
        /*正上*/
        if (row - 1 >= min && nodeAll[row-1][col].reachable){
            boolean in = isIn(open, nodeAll[row-1][col]);
            boolean in2 = isIn(close, nodeAll[row-1][col]);
            if (!in && !in2){
                nodes.add(nodeAll[row-1][col]);
            }
        }
        /*右上*/
        if (col + 1 < colMax && row - 1 >= min && nodeAll[row-1][col +1].reachable){
            boolean in = isIn(open, nodeAll[row-1][col +1]);
            boolean in2 = isIn(close, nodeAll[row-1][col +1]);
            if (!in && !in2){
                nodes.add(nodeAll[row-1][col +1]);
            }
        }

        /*正左*/
        if (col - 1 >= min && nodeAll[row][col-1].reachable){
            boolean in = isIn(open, nodeAll[row][col-1]);
            boolean in2 = isIn(close, nodeAll[row][col-1]);
            if (!in && !in2){
                nodes.add(nodeAll[row][col-1]);
            }
        }
        /*正右*/
        if (col + 1 < rowMax && nodeAll[row][col +1].reachable){
            boolean in = isIn(open, nodeAll[row][col +1]);
            boolean in2 = isIn(close, nodeAll[row][col +1]);
            if (!in && !in2){
                nodes.add(nodeAll[row][col +1]);
            }
        }

        /*左下*/
        if (col - 1 >= min && row + 1 < rowMax && nodeAll[row+1][col-1].reachable){
            boolean in = isIn(open, nodeAll[row+1][col-1]);
            boolean in2 = isIn(close, nodeAll[row+1][col-1]);
            if (!in && !in2){
                nodes.add(nodeAll[row+1][col-1]);
            }
        }
        /*正下*/
        if (row + 1 < rowMax && nodeAll[row + 1][col].reachable ){
            boolean in = isIn(open, nodeAll[row + 1][col]);
            boolean in2 = isIn(close, nodeAll[row + 1][col]);
            if (!in && !in2){
                nodes.add(nodeAll[row + 1][col]);
            }
        }
        /*右下*/
        if (col + 1 < colMax && row + 1 < rowMax && nodeAll[row+1][col+1].reachable){
            boolean in = isIn(open, nodeAll[row+1][col+1]);
            boolean in2 = isIn(close, nodeAll[row+1][col+1]);
            if (!in && !in2){
                nodes.add(nodeAll[row+1][col+1]);
            }
        }
        return nodes;
    }


    public boolean isIn(List< Node> nodes,  Node node){
        for (Node node1 : nodes) {
            if (node1.row == node.row && node1.col == node.col){
                return true;
            }
        }
        return false;
    }

    public void initNodeAll(){
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < colMax; j++) {
                if (a[i][j] == 1){
                     Node node = new  Node();
                    node.row = i;
                    node.col = j;
                    node.name = i + "_" + j;
                    node.reachable = true;
                    node.type = "1";
                    nodeAll[i][j] = node;
                }else{
                     Node node = new  Node();
                    node.row = i;
                    node.col = j;
                    node.name = i + "_" + j;
                    node.reachable = false;
                    node.type = "0";
                    nodeAll[i][j] = node;
                }
            }
        }
    }

    public Node[][] getNodeAll() {
        return nodeAll;
    }

}
