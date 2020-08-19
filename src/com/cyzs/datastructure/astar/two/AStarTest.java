package com.cyzs.datastructure.astar.two;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

/**
 * @author xh
 * @description
 * @create 2020-08-17 9:30
 */
public class AStarTest {

    AStar aStar = new AStar();

    static AStar aStar2 = new AStar();

    public static void main(String[] args)throws Exception {
        while (true){
            aStar2.initNodeAll();
            Node[][] nodeAll1 = aStar2.getNodeAll();

            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    System.out.print(nodeAll1[i][j].type + " ");

                }
                System.out.println("");
            }

            System.out.println("请输入起点坐标和终点坐标：");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            String[] split = s.split(" ");
            if (split.length != 4){
                continue;
            }

            List<String> strings = testRoudMain(Integer.parseInt(split[0]), Integer.parseInt(split[1])
                    , Integer.parseInt(split[2]), Integer.parseInt(split[3]));
            Node[][] nodeAll = aStar2.getNodeAll();
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    if (strings.contains(i+ "_"+ j)){
                        //前景色 31-36 黄  背景色 41-46 红 树叶绿 黄 蓝 粉红  浅绿
                        System.out.print("\33[34;41;4m"+"A" + " ");
                    }else {
                        System.out.print("\33[33;44;4m"+nodeAll[i][j].type + " ");
                    }
                }
                System.out.println("");
            }
            System.out.println("=====================");

        }
    }

    @Before
    public void before(){
        aStar.initNodeAll();
    }

    @Test
    public void test3(){
        testRoud(2, 2, 2, 6);
        //testRoud(5, 0, 3, 5);
    }

    public void testRoud(int startRow, int startCol, int endRow, int endCol){
        Node node = new Node();
        node.row = startRow;
        node.col = startCol;
        node.name = startRow + "_" + startCol;
        Node node2 = new Node();
        node2.row = endRow;
        node2.col = endCol;
        node2.name = endRow+"_"+endCol;
        List<String> strings = aStar.searchRoad(node, node2);
        System.out.println(node.name + "到"+ node2.name +"  " +strings);

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

    public static List<String> testRoudMain(int startRow, int startCol, int endRow, int endCol){
        Node node = new Node();
        node.row = startRow;
        node.col = startCol;
        node.name = startRow + "_" + startCol;
        Node node2 = new Node();
        node2.row = endRow;
        node2.col = endCol;
        node2.name = endRow+"_"+endCol;
        List<String> strings = aStar2.searchRoad(node, node2);
        return strings;

    }

}
