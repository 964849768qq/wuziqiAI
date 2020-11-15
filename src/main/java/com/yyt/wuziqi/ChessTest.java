package com.yyt.wuziqi;

public class ChessTest {
    public void test(int[][] board){
        for (int i=0;i<15;i++){
            for (int j=0;j<15;j++){
                if(board[i][j]!=0){
                    System.out.println("(i,j):"+i+","+j+"    "+board[i][j]);
                }
            }
        }
    }
}
