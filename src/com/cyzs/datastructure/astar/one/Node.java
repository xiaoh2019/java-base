package com.cyzs.datastructure.astar.one;

/**
 * @author xh
 * @description
 * @create 2020-08-17 10:10
 */
public class Node implements Comparable<Node>{
    //行
    public int row;
    //列
    public int col;
    //
    public String type;
    public String name;
    //总的代价
    public double countValue = 0;
    //从起始点到当前点的代价
    public double startValue = 0;
    //从当前点到结束点的代价
    public double endValue = 0;
    //是否可走
    public boolean reachable;
    public Node pNode;

    @Override
    public String toString() {
        return "Node{" +
                "row=" + row +
                ", col=" + col +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", fValue=" + countValue +
                ", gValue=" + startValue +
                ", hValue=" + endValue +
                ", reachable=" + reachable +
                ", pNode=" + pNode +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.countValue, o.countValue);
    }
}
