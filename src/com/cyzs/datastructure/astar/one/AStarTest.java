package com.cyzs.datastructure.astar.one;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author xh
 * @description
 * @create 2020-08-17 9:30
 */
public class AStarTest {

    AStar aStar = new AStar();

    @Before
    public void before(){
        aStar.initNodeAll();
    }

    @Test
    public void test3(){
        List<String> strings = testRoud(8, 1, 1, 5);
        Node[][] nodeAll = aStar.getNodeAll();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(nodeAll[i][j].type + " ");
            }
            System.out.println("");
        }
        System.out.println("=====================");
        //testRoud(5, 0, 3, 5);
    }

    public List<String> testRoud(int startRow, int startCol, int endRow, int endCol){
        Node node = new Node();
        node.row = startRow;
        node.col = startCol;
        node.name = startRow + "_" + startCol;
        Node node2 = new Node();
        node2.row = endRow;
        node2.col = endCol;
        node2.name = endRow+"_"+endCol;
        List<String> strings = aStar.searchRoad(node, node2);
        return strings;

    }

    @Test
    public void test2(){
        Node node = new Node();
        node.row = 2;
        node.col = 3;
        Node node2 = new Node();
        node2.row = 2;
        node2.col = 6;
        List<Node> roundNode = aStar.getRoundNode(node);
        for (Node node1 : roundNode) {
            System.out.println(node1);
        }

    }


    @Test
    public void test1(){
        AStar aStar = new AStar();
        aStar.initNodeAll();
        Node[][] nodeAll = aStar.getNodeAll();

        for (Node[] nodes : nodeAll) {
            for (Node node : nodes) {
                System.out.println(node);
            }
            System.out.println("");
        }

    }


    @Test
    public void test(){
        int[][] a = {{1,2,1,1,1,1,1,1},
                {1,1,3,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,5,6,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1}};
        /*y,x*/
        System.out.println(a[0][1]);
        System.out.println(a[3][2]);
    }

    /*public static void main(String[] args) {
        *//*8行6列*//*
        int[][] a = {{1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1}};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("=====================");

        a[1][4] = 0;
        a[2][4] = 0;
        a[3][4] = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println("");
        }








    }*/

}
