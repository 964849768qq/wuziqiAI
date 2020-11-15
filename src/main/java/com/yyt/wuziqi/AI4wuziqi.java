package com.yyt.wuziqi;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AI4wuziqi {
    //搜索深度
    private static final int searchdepth=4;
    private static int searchresult=0;
    private static int[][] position = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
            { 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0 },
            { 0, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1, 0 },
            { 0, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 3, 2, 1, 0 },
            { 0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 4, 3, 2, 1, 0 },
            { 0, 1, 2, 3, 4, 5, 6, 6, 6, 5, 4, 3, 2, 1, 0 },
            { 0, 1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1, 0 },
            { 0, 1, 2, 3, 4, 5, 6, 6, 6, 5, 4, 3, 2, 1, 0 },
            { 0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 4, 3, 2, 1, 0 },
            { 0, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 3, 2, 1, 0 },
            { 0, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 1, 0 },
            { 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0 },
            { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };
    //用于判断k方（1为黑、-1为白）场上局势

    public Map<String,Integer> judgeqixing(int k, int[][] board){
        Map<String,Integer> map = new HashMap<>();
        int zi5=0;
        int huo4=0;
        int mian4=0;
        int huo3=0;
        int mian3=0;
        int huo2=0;
        int mian2=0;
        int si4=0;
        int si3=0;
        int si2=0;
        //判断横向方向的得分
        for (int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                //5子
                if((j+4<15)&&(board[i][j]+board[i][j+1]+board[i][j+2]+board[i][j+3]+board[i][j+4]==5*k)){
                    zi5++;
                }
                //活4
                if((j+5<15)&&(board[i][j]==0)&&(board[i][j+5]==0)&&(board[i][j+1]+board[i][j+2]+board[i][j+3]+board[i][j+4]==4*k)) {
                    huo4++;
                }
                //眠4
                if((j+5<15)&&(board[i][j+1]+board[i][j+2]+board[i][j+3]+board[i][j+4]==4*k)&&(board[i][j]+board[i][j+5]==1*k)){
                    mian4++;
                }
                //活3
                if(((j+4<15)&&(board[i][j]==0)&&(board[i][j+4]==0)&&board[i][j+1]==k&&board[i][j+2]==k&&board[i][j+3]==k)){
                    huo3++;
                }
                if((j+5<15)&&(board[i][j]==0&board[i][j+5]==0)&&board[i][j+1]==k&&board[i][j+2]==0&&board[i][j+3]==k&&board[i][j+4]==k){
                    huo3++;
                }
                if((j+5<15)&&(board[i][j]==0&board[i][j+5]==0)&&board[i][j+1]==k&&board[i][j+2]==k&&board[i][j+3]==0&&board[i][j+4]==k){
                    huo3++;
                }
                //眠3
                if((j+5<15)&&(board[i][j+1]+board[i][j+2]+board[i][j+3]+board[i][j+4]==3*k)&&(board[i][j]+board[i][j+5]==-k)&&((board[i][j]==-k&&board[i][j+1]==k)||(board[i][j+5]==-k&&board[i][j+4]==k))){
                    mian3++;
                }
                if((j+4<15)&&(board[i][j]+board[i][j+4]==2*k)&&board[i][j+1]!=-k&&board[i][j+2]!=-k&&board[i][j+3]!=-k&&(board[i][j+1]+board[i][j+2]+board[i][j+3]==k)){
                    mian3++;
                }
                if((j+6<15)&&(board[i][j]+board[i][j+6]==-2*k)&&board[i][j+1]==0&&board[i][j+5]==0&&(board[i][j+2]+board[i][j+3]+board[i][j+4]==3*k)){
                    mian3++;
                }
                //活2
                if((j+4<15)&&board[i][j]==0&&board[i][j+4]==0&&board[i][j+1]==k&&board[i][j+2]==0&&board[i][j+3]==k){
                    huo2++;
                }
                if((j+3<15)&&board[i][j]==0&&board[i][j+1]==k&&board[i][j+2]==k&&board[i][j+3]==0){
                    huo2++;
                }
                if((j+5<15)&&(board[i][j+1]+board[i][j+4]==2*k)&&board[i][j]==0&&board[i][j+2]==0&&board[i][j+3]==0&&board[i][j+5]==0){
                    huo2++;
                }
                //眠2
                if((j+5<15)&&((board[i][j]==-k&&board[i][j+1]==k&&(board[i][j+2]+board[i][j+3]+board[i][j+4]==k)&&board[i][j+2]!=-k&&board[i][j+3]!=-k&&board[i][j+4]!=-k&&board[i][j+5]==0)||
                        (board[i][j+5]==-k&&board[i][j+4]==k&&(board[i][j+3]+board[i][j+2]+board[i][j+1]==k)&&board[i][j+2]!=-k&&board[i][j+3]!=-k&&board[i][j+4]!=-k&&board[i][j]==0))){
                    mian2++;
                }
                if((j+4<15)&&(board[i][j]+board[i][j+4]==2*k)&&board[i][j+1]==0&&board[i][j+2]==0&&board[i][j+3]==0){
                    mian2++;
                }
                if((j+6<15)&&(board[i][j]+board[i][j+6]==-2*k)&&(board[i][j+2]+board[i][j+3]+board[i][j+4]==2*k)&&board[i][j+1]==0&&board[i][j+5]==0){
                    mian2++;
                }
                //死4
                if((j+5<15)&&(board[i][j]+board[i][j+5]==-2*k)&&(board[i][j+1]+board[i][j+2]+board[i][j+3]+board[i][j+4]==4*k)){
                    si4++;
                }
                //死3
                if((j+4<15)&&(board[i][j]+board[i][j+4]==-2*k)&&(board[i][j+1]+board[i][j+2]+board[i][j+3]==3*k)){
                    si3++;
                }
                if((j+3<15)&&(board[i][j]+board[i][j+3]==-2*k)&&(board[i][j+1]+board[i][j+2]==2*k)){
                    si2++;
                }
            }
        }
        //判断纵向方向的得分
        for (int j=0;j<15;j++){
            for(int i=0;i<15;i++){
                //5子
                if((i+4<15)&&board[i][j]+board[i+1][j]+board[i+2][j]+board[i+3][j]+board[i+4][j]==5*k){
                    zi5++;
                }
                //活4
                if((i+5<15)&&(board[i][j]==0)&&(board[i+5][j]==0)&&(board[i+1][j]+board[i+2][j]+board[i+3][j]+board[i+4][j]==4*k)) {
                    huo4++;
                }
                //眠4
                if((i+5<15)&&(board[i+1][j]+board[i+2][j]+board[i+3][j]+board[i+4][j]==4*k)&&(board[i][j]+board[i+5][j]==1*k)){
                    mian4++;
                }
                //活3
                if(((i+4<15)&&(board[i][j]==0)&&(board[i+4][j]==0)&&(board[i+1][j]+board[i+2][j]+board[i+3][j]==3*k))){
                    huo3++;
                }
                if((i+5<15)&&(board[i][j]==0&board[i+5][j]==0)&&board[i+1][j]==k&&board[i+2][j]==0&&board[i+3][j]==k&&board[i+4][j]==k){
                    huo3++;
                }
                if((i+5<15)&&(board[i][j]==0&board[i+5][j]==0)&&board[i+1][j]==k&&board[i+2][j]==k&&board[i+3][j]==0&&board[i+4][j]==k){
                    huo3++;
                }
                //眠3
                if((i+5<15)&&(board[i+1][j]+board[i+2][j]+board[i+3][j]+board[i+4][j]==3*k)&&(board[i][j]+board[i+5][j]==-k)&&((board[i][j]==-k&&board[i+1][j]==k)||(board[i+5][j]==-k&&board[i+4][j]==k))){
                    mian3++;
                }
                if((i+4<15)&&(board[i][j]+board[i+4][j]==2*k)&&board[i+1][j]!=-k&&board[i+2][j]!=-k&&board[i+3][j]!=-k&&(board[i+1][j]+board[i+2][j]+board[i+3][j]==k)){
                    mian3++;
                }
                if((i+6<15)&&(board[i][j]+board[i+6][j]==-2*k)&&board[i+1][j]==0&&board[i+5][j]==0&&(board[i+2][j]+board[i+3][j]+board[i+4][j]==3*k)){
                    mian3++;
                }
                //活2
                if((i+4<15)&&board[i+1][j]==k&&board[i][j]==0&&board[i+4][j]==0&&board[i+2][j]==0&&board[i+3][j]==k){
                    huo2++;
                }
                if((i+3<15)&&board[i][j]==0&&board[i+1][j]==k&&board[i+2][j]==k&&board[i+3][j]==0){
                    huo2++;
                }
                if((i+5<15)&&(board[i+1][j]+board[i+4][j]==2*k)&&board[i][j]==0&&board[i+2][j]==0&&board[i+3][j]==0&&board[i+5][j]==0){
                    huo2++;
                }
                //眠2
                if((i+5<15)&&((board[i][j]==-k&&board[i+1][j]==k&&(board[i+2][j]+board[i+3][j]+board[i+4][j]==k)&&board[i+2][j]!=-k&&board[i+3][j]!=-k&&board[i+4][j]!=-k&&board[i+5][j]==0)||
                        (board[i+5][j]==-k&&board[i+4][j]==k&&(board[i+3][j]+board[i+2][j]+board[i+1][j]==k)&&board[i+2][j]!=-k&&board[i+3][j]!=-k&&board[i+4][j]!=-k&&board[i][j]==0))){
                    mian2++;
                }
//                if(i<10){
//                    if((board[i][j]==-k&&board[i+1][j]==k&&(board[i+2][j]+board[i+3][j]+board[i+4][j]==k)&&board[i+2][j]!=-k&&board[i+3][j]!=-k&&board[i+4][j]!=-k&&board[i+5][j]==0)||
//                            (board[i+5][j]==-k&&board[i+4][j]==k&&(board[i+3][j]+board[i+2][j]+board[i+1][j]==k)&&board[i+2][j]!=-k&&board[i+3][j]!=-k&&board[i+4][j]!=-k&&board[i][j]==0)){
//                        mian2++;
//                    }
//                }
                if((i+4<15)&&(board[i][j]+board[i+4][j]==2*k)&&board[i+1][j]==0&&board[i+2][j]==0&&board[i+3][j]==0){
                    mian2++;
                }
                if((i+6<15)&&(board[i][j]+board[i+6][j]==-2*k)&&(board[i+2][j]+board[i+3][j]+board[i+4][j]==2*k)&&board[i+1][j]==0&&board[i+5][j]==0){
                    mian2++;
                }
                //死4
                if((i+5<15)&&(board[i][j]+board[i+5][j]==-2*k)&&(board[i+1][j]+board[i+2][j]+board[i+3][j]+board[i+4][j]==4*k)){
                    si4++;
                }
                //死3
                if((i+4<15)&&(board[i][j]+board[i+4][j]==-2*k)&&(board[i+1][j]+board[i+2][j]+board[i+3][j]==3*k)){
                    si3++;
                }
                if((i+3<15)&&(board[i][j]+board[i+3][j]==-2*k)&&(board[i+1][j]+board[i+2][j]==2*k)){
                    si2++;
                }
            }
        }
        //判断左轴向方向的得分
        for(int t=0;t<15;t++){
            for(int i=0;i<15;i++){
                //5子
                if((i+t+4<15)&&(board[i+t][i]+board[i+t+1][i+1]+board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==5*k)){
                    zi5++;
                }
                //活4
                if((i+t+5<15)&&(board[i+t][i]==0)&&(board[i+t+5][i+5]==0)&&(board[i+t+1][i+1]+board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==4*k)) {
                    huo4++;
                }
                //眠4
                if((i+t+5<15)&&(board[i+t+1][i+1]+board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==4*k)&&(board[i+t][i]+board[i+t+5][i+5]==1*k)){
                    mian4++;
                }
                //活3
                if(((i+t+4<15)&&(board[i+t][i]==0)&&(board[i+t+4][i+4]==0)&&(board[i+t+1][i+1]+board[i+t+2][i+2]+board[i+t+3][i+3]==3*k))){
                    huo3++;
                }
                if((i+t+5<15)&&(board[i+t][i]==0&board[i+t+5][i+5]==0)&&board[i+t+1][i+1]==k&&board[i+t+2][i+2]==0&&board[i+t+3][i+3]==k&&board[i+t+4][i+4]==k){
                    huo3++;
                }
                if((i+t+5<15)&&(board[i+t][i]==0&board[i+t+5][i+5]==0)&&board[i+t+1][i+1]==k&&board[i+t+2][i+2]==k&&board[i+t+3][i+3]==0&&board[i+t+4][i+4]==k){
                    huo3++;
                }
                //眠3
                if((i+t+5<15)&&(board[i+t+1][i+1]+board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==3*k)&&(board[i+t][i]+board[i+t+5][i+5]==-k)&&((board[i+t][i]==-k&&board[i+t+1][i+1]==k)||(board[i+t+5][i+5]==-k&&board[i+t+4][i+4]==k))){
                    mian3++;
                }
                if((i+t+4<15)&&(board[i+t][i]+board[i+t+4][i+4]==2*k)&&board[i+t+1][i+1]!=-k&&board[i+t+2][i+2]!=-k&&board[i+t+3][i+3]!=-k&&(board[i+t+1][i+1]+board[i+t+2][i+2]+board[i+t+3][i+3]==k)){
                    mian3++;
                }
                if((i+t+6<15)&&(board[i+t][i]+board[i+t+6][i+6]==-2*k)&&board[i+t+1][i+1]==0&&board[i+t+5][i+5]==0&&(board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==3*k)){
                    mian3++;
                }
                //活2
                if((i+t+4<15)&&board[i+t+1][i+1]==k&&board[i+t][i]==0&&board[i+t+4][i+4]==0&&board[i+t+2][i+2]==0&&board[i+t+3][i+3]==k){
                    huo2++;
                }
                if((i+t+3<15)&&board[i+t][i]==0&&board[i+t+1][i+1]==k&&board[i+t+2][i+2]==k&&board[i+t+3][i+3]==0){
                    huo2++;
                }
                if((i+t+5<15)&&(board[i+t+1][i+1]+board[i+t+4][i+4]==2*k)&&board[i+t][i]==0&&board[i+t+2][i+2]==0&&board[i+t+3][i+3]==0&&board[i+t+5][i+5]==0){
                    huo2++;
                }
                //眠2
                if((i+t+5<15)&&((board[i+t][i]==-k&&board[i+t+1][i+1]==k&&(board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==k)&&board[i+t+2][i+2]!=-k&&board[i+t+3][i+3]!=-k&&board[i+t+4][i+4]!=-k&&board[i+t+5][i+5]==0)||
                        (board[i+t+5][i+5]==-k&&board[i+t+4][i+4]==k&&(board[i+t+3][i+3]+board[i+t+2][i+2]+board[i+t+1][i+1]==k)&&board[i+t+2][i+2]!=-k&&board[i+t+3][i+3]!=-k&&board[i+t+4][i+4]!=-k&&board[i+t][i]==0))){
                    mian2++;
                }
                if((i+t+4<15)&&(board[i+t][i]+board[i+t+4][i+4]==2*k)&&board[i+t+1][i+1]==0&&board[i+t+2][i+2]==0&&board[i+t+3][i+3]==0){
                    mian2++;
                }
                if((i+t+6<15)&&(board[i+t][i]+board[i+t+6][i+6]==-2*k)&&(board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==2*k)&&board[i+t+1][i+1]==0&&board[i+t+5][i+5]==0){
                    mian2++;
                }
                //死4
                if((i+t+5<15)&&(board[i+t][i]+board[i+t+5][i+5]==-2*k)&&(board[i+t+1][i+1]+board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==4*k)){
                    si4++;
                }
                //死3
                if((i+t+4<15)&&(board[i+t][i]+board[i+t+4][i+4]==-2*k)&&(board[i+t+1][i+1]+board[i+t+2][i+2]+board[i+t+3][i+3]==3*k)){
                    si3++;
                }
                if((i+t+3<15)&&(board[i+t][i]+board[i+t+3][i+3]==-2*k)&&(board[i+t+1][i+1]+board[i+t+2][i+2]==2*k)){
                    si2++;
                }

            }
        }
        for(int t=1;t<15;t++){
            for(int i=0;i+t<15;i++){
                //5子
                if((i+t+4<15)&&(board[i][i+t]+board[i+1][i+t+1]+board[i+2][i+t+2]+board[i+3][i+t+3]+board[i+4][i+t+4]==5*k)){
                    zi5++;
                }
                //活4
                if((i+t+5<15)&&(board[i][i+t]==0)&&(board[i+5][i+t+5]==0)&&(board[i+1][i+t+1]+board[i+2][i+t+2]+board[i+3][i+t+3]+board[i+4][i+t+4]==4*k)) {
                    huo4++;
                }
                //眠4
                if((i+t+5<15)&&(board[i+1][i+t+1]+board[i+2][i+t+2]+board[i+3][i+t+3]+board[i+4][i+t+4]==4*k)&&(board[i][i+t]+board[i+5][i+t+5]==1*k)){
                    mian4++;
                }
                //活3
                if(((i+t+4<15)&&(board[i][i+t]==0)&&(board[i+4][i+t+4]==0)&&(board[i+1][i+t+1]+board[i+2][i+t+2]+board[i+3][i+t+3]==3*k))){
                    huo3++;
                }
                if((i+t+5<15)&&(board[i][i+t]==0&board[i+5][i+t+5]==0)&&(board[i+1][i+t+1]==k&&board[i+2][i+t+2]==0&&board[i+3][i+t+3]==k&&board[i+4][i+t+4]==k)){
                    huo3++;
                }
                if((i+t+5<15)&&(board[i][i+t]==0&board[i+5][i+t+5]==0)&&(board[i+1][i+t+1]==k&&board[i+2][i+t+2]==k&&board[i+3][i+t+3]==0&&board[i+4][i+t+4]==k)){
                    huo3++;
                }
                //眠3
                if((i+t+5<15)&&(board[i+1][i+t+1]+board[i+2][i+t+2]+board[i+3][i+t+3]+board[i+4][i+t+4]==3*k)&&(board[i][i+t]+board[i+5][i+t+5]==-k)&&((board[i][i+t]==-k&&board[i+1][i+t+1]==k)||(board[i+5][i+t+5]==-k&&board[i+4][i+t+4]==k))){
                    mian3++;
                }
                if((i+t+4<15)&&(board[i][i+t]+board[i+4][i+t+4]==2*k)&&board[i+1][i+t+1]!=-k&&board[i+2][i+t+2]!=-k&&board[i+3][i+t+3]!=-k&&(board[i+1][i+t+1]+board[i+2][i+t+2]+board[i+3][i+t+3]==k)){
                    mian3++;
                }
                if((i+t+6<15)&&(board[i][i+t]+board[i+6][i+t+6]==-2*k)&&board[i+1][i+t+1]==0&&board[i+5][i+t+5]==0&&(board[i+2][i+t+2]+board[i+3][i+t+3]+board[i+4][i+t+4]==3*k)){
                    mian3++;
                }
                //活2
                if((i+t+4<15)&&board[i][i+t]==0&&board[i+4][i+t+4]==0&&board[i+1][i+t+1]==k&&board[i+2][i+t+2]==0&&board[i+3][i+t+3]==k){
                    huo2++;
                }
                if((i+t+3<15)&&board[i][i+t]==0&&board[i+1][i+t+1]==k&&board[i+2][i+t+2]==k&&board[i+3][i+t+3]==0){
                    huo2++;
                }
                if((i+t+5<15)&&(board[i+1][i+t+1]+board[i+4][i+t+4]==2*k)&&board[i][i+t]==0&&board[i+2][i+t+2]==0&&board[i+3][i+t+3]==0&&board[i+5][i+t+5]==0){
                    huo2++;
                }
                //眠2
                if((i+t+5<15)&&((board[i+t][i]==-k&&board[i+t+1][i+1]==k&&(board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==k)&&board[i+t+2][i+2]!=-k&&board[i+t+3][i+3]!=-k&&board[i+t+4][i+4]!=-k&&board[i+t+5][i+5]==0)||
                        (board[i+t+5][i+5]==-k&&board[i+t+4][i+4]==k&&(board[i+t+3][i+3]+board[i+t+2][i+2]+board[i+t+1][i+1]==k)&&board[i+t+2][i+2]!=-k&&board[i+t+3][i+3]!=-k&&board[i+t+4][i+4]!=-k&&board[i+t][i]==0))){
                    mian2++;
                }
                if((i+t+4<15)&&(board[i+t][i]+board[i+t+4][i+4]==2*k)&&board[i+t+1][i+1]==0&&board[i+t+2][i+2]==0&&board[i+t+3][i+3]==0){
                    mian2++;
                }
                if((i+t+6<15)&&(board[i+t][i]+board[i+t+6][i+6]==-2*k)&&(board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==2*k)&&board[i+t+1][i+1]==0&&board[i+t+5][i+5]==0){
                    mian2++;
                }
                //死4
                if((i+t+5<15)&&(board[i+t][i]+board[i+t+5][i+5]==-2*k)&&(board[i+t+1][i+1]+board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==4*k)){
                    si4++;
                }
                //死3
                if((i+t+4<15)&&(board[i+t][i]+board[i+t+4][i+4]==-2*k)&&(board[i+t+1][i+1]+board[i+t+2][i+2]+board[i+t+3][i+3]==3*k)){
                    si3++;
                }
                if((i+t+3<15)&&(board[i+t][i]+board[i+t+3][i+3]==-2*k)&&(board[i+t+1][i+1]+board[i+t+2][i+2]==2*k)){
                    si2++;
                }
            }
        }
        //判断右轴向方向的得分
        for(int t=14;t>=0;t--){
            for(int i=0;i<15;i++){
                //5子
                if((t-i-4>=0)&&(board[t-i][i]+board[t-i-1][i+1]+board[t-i-2][i+2]+board[t-i-3][i+3]+board[t-i-4][i+4]==5*k)){
                    zi5++;
                }
                //活4
                if((t-i-5>=0)&&(board[t-i][i]==0)&&(board[t-i-5][i+5]==0)&&(board[t-i-1][i+1]+board[t-i-2][i+2]+board[t-i-3][i+3]+board[t-i-4][i+4]==4*k)) {
                    huo4++;
                }
                //眠4
                if((t-i-5>=0)&&(board[t-i-1][i+1]+board[t-i-2][i+2]+board[t-i-3][i+3]+board[t-i-4][i+4]==4*k)&&(board[t-i][i]+board[t-i-5][i+5]==1*k)){
                    mian4++;
                }
                //活3
                if(((t-i-4>=0)&&(board[t-i][i]==0)&&(board[t-i-4][i+4]==0)&&(board[t-i-1][i+1]+board[t-i-2][i+2]+board[t-i-3][i+3]==3*k))){
                    huo3++;
                }
                if((t-i-5>=0)&&(board[t-i][i]==0&board[t-i-5][i+5]==0)&&(board[t-i-1][i+1]==k&&board[t-i-2][i+2]==0&&board[t-i-3][i+3]==k&&board[t-i-4][i+4]==k)){
                    huo3++;
                }
                if((t-i-5>=0)&&(board[t-i][i]==0&board[t-i-5][i+5]==0)&&(board[t-i-1][i+1]==k&&board[t-i-2][i+2]==k&&board[t-i-3][i+3]==0&&board[t-i-4][i+4]==k)){
                    huo3++;
                }
                //眠3
                if((t-i-5>=0)&&(board[t-i-1][i+1]+board[t-i-2][i+2]+board[t-i-3][i+3]+board[t-i-4][i+4]==3*k)&&(board[t-i][i]+board[t-i-5][i+5]==-k)&&((board[t-i][i]==-k&&board[t-i-1][i+1]==k)||(board[t-i-5][i+5]==-k&&board[t-i-4][i+4]==k))){
                    mian3++;
                }
                if((t-i-4>=0)&&(board[t-i][i]+board[t-i-4][i+4]==2*k)&&board[t-i-1][i+1]!=-k&&board[t-i-2][i+2]!=-k&&board[t-i-3][i+3]!=-k&&(board[t-i-1][i+1]+board[t-i-2][i+2]+board[t-i-3][i+3]==k)){
                    mian3++;
                }
                if((t-i-6>=0)&&(board[t-i][i]+board[t-i-6][i+6]==-2*k)&&board[t-i-1][i+1]==0&&board[t-i-5][i+5]==0&&(board[t-i-2][i+2]+board[t-i-3][i+3]+board[t-i-4][i+4]==3*k)){
                    mian3++;
                }
                //活2
                if((t-i-4>=0)&&board[t-i-1][i+1]==k&&board[t-i-2][i+2]==0&&board[t-i-3][i+3]==k&&board[t-i][i]==0&&board[t-i-4][i+4]==0){
                    huo2++;
                }
                if((t-i-3>=0)&&board[t-i][i]==0&&board[t-i-1][i+1]==k&&board[t-i-2][i+2]==k&&board[t-i-3][i+3]==0){
                    huo2++;
                }
                if((t-i-5>=0)&&(board[t-i-1][i+1]+board[t-i-4][i+4]==2*k)&&board[t-i][i]==0&&board[t-i-2][i+2]==0&&board[t-i-3][i+3]==0&&board[t-i-5][i+5]==0){
                    huo2++;
                }
                //眠2
                if((t-i-5>=0)&&((board[t-i][i]==-k&&board[t-i-1][i+1]==k&&(board[t-i-2][i+2]+board[t-i-3][i+3]+board[t-i-4][i+4]==k)&&board[t-i-2][i+2]!=-k&&board[t-i-3][i+3]!=-k&&board[t-i-4][i+4]!=-k&&board[t-i-5][i+5]==0)||
                        (board[t-i-5][i+5]==-k&&board[t-i-4][i+4]==k&&(board[t-i-3][i+3]+board[t-i-2][i+2]+board[t-i-1][i+1]==k)&&board[t-i-2][i+2]!=-k&&board[t-i-3][i+3]!=-k&&board[t-i-4][i+4]!=-k&&board[t-i][i]==0))){
                    mian2++;
                }
                if((t-i-4>=0)&&(board[t-i][i]+board[t-i-4][i+4]==2*k)&&board[t-i-1][i+1]==0&&board[t-i-2][i+2]==0&&board[t-i-3][i+3]==0){
                    mian2++;
                }
                if((t-i-6>=0)&&(board[t-i][i]+board[t-i-6][i+6]==-2*k)&&(board[t-i-2][i+2]+board[t-i-3][i+3]+board[t-i-4][i+4]==2*k)&&board[t-i-1][i+1]==0&&board[t-i-5][i+5]==0){
                    mian2++;
                }
                //死4
                if((t-i-5>=0)&&(board[t-i][i]+board[t-i-5][i+5]==-2*k)&&(board[t-i-1][i+1]+board[t-i-2][i+2]+board[t-i-3][i+3]+board[t-i-4][i+4]==4*k)){
                    si4++;
                }
                //死3
                if((t-i-4>=0)&&(board[t-i][i]+board[t-i-4][i+4]==-2*k)&&(board[t-i-1][i+1]+board[t-i-2][i+2]+board[t-i-3][i+3]==3*k)){
                    si3++;
                }
                if((t-i-3>=0)&&(board[t-i][i]+board[t-i-3][i+3]==-2*k)&&(board[t-i-1][i+1]+board[t-i-2][i+2]==2*k)){
                    si2++;
                }
            }
        }
        for(int t=14;t>=0;t--){
            for(int i=1;t-i>=0;i++){
                //5子
                if((t-i-4>=0)&&(board[t][14-t+i]+board[t-1][14-t+i+1]+board[t-2][14-t+i+2]+board[t-3][14-t+i+3]+board[t-4][14-t+i+4]==5*k)){
                    zi5++;
                }
                //活4
                if((t>i+4)&&(board[t][14-t+i]==0)&&(board[t-5][14-t+i+5]==0)&&(board[t-1][14-t+i+1]+board[t-2][14-t+i+2]+board[t-3][14-t+i+3]+board[t-4][14-t+i+4]==4*k)) {
                    huo4++;
                }
                //眠4
                if((t>i+4)&&(board[t-1][14-t+i+1]+board[t-2][14-t+i+2]+board[t-3][14-t+i+3]+board[t-4][14-t+i+4]==4*k)&&(board[t][14-t+i]+board[t-5][14-t+i+5]==1*k)){
                    mian4++;
                }
                //活3
                if(((t>i+3)&&(board[t][14-t+i]==0)&&(board[t-4][14-t+i+4]==0)&&(board[t-1][14-t+i+1]+board[t-2][14-t+i+2]+board[t-3][14-t+i+3]==3*k))){
                    huo3++;
                }
                if((t>i+4)&&(board[t][14-t+i]==0&board[t-5][14-t+i+5]==0)&&(board[t-1][14-t+i+1]==k&&board[t-2][14-t+i+2]==0&&board[t-3][14-t+i+3]==k&&board[t-4][14-t+i+4]==k)){
                    huo3++;
                }
                if((t>i+4)&&(board[t][14-t+i]==0&board[t-5][14-t+i+5]==0)&&(board[t-1][14-t+i+1]==k&&board[t-2][14-t+i+2]==k&&board[t-3][14-t+i+3]==0&&board[t-4][14-t+i+4]==k)){
                    huo3++;
                }
                //眠3
                if((t>i+4)&&(board[t-1][14-t+i+1]+board[t-2][14-t+i+2]+board[t-3][14-t+i+3]+board[t-4][14-t+i+4]==3*k)&&(board[t][14-t+i]+board[t-5][14-t+i+5]==-k)&&((board[t][14-t+i]==-k&&board[t-1][14-t+i+1]==k)||(board[t-5][14-t+i+5]==-k&&board[t-4][14-t+i+4]==k))){
                    mian3++;
                }
                if((t>i+3)&&(board[t][14-t+i]+board[t-4][14-t+i+4]==2*k)&&board[t-1][14-t+i+1]!=-k&&board[t-2][14-t+i+2]!=-k&&board[t-3][14-t+i+3]!=-k&&(board[t-1][14-t+i+1]+board[t-2][14-t+i+2]+board[t-3][14-t+i+3]==k)){
                    mian3++;
                }
                if((t>i+5)&&(board[t][14-t+i]+board[t-6][14-t+i+6]==-2*k)&&board[t-1][14-t+i+1]==0&&board[t-5][14-t+i+5]==0&&(board[t-2][14-t+i+2]+board[t-3][14-t+i+3]+board[t-4][14-t+i+4]==3*k)){
                    mian3++;
                }
                //活2
                if((t>i+3)&&board[t-1][14-t+i+1]==k&&board[t-2][14-t+i+2]==0&&board[t-3][14-t+i+3]==k&&board[t][14-t+i]==0&&board[t-4][14-t+i+4]==0){
                    huo2++;
                }
                if((t>i+2)&&board[t][14-t+i]==0&&board[t-3][14-t+i+3]==0&&board[t-1][14-t+i+1]==k&&board[t-2][14-t+i+2]==k){
                    huo2++;
                }
                if((t>i+4)&&(board[t-1][14-t+i+1]+board[t-4][14-t+i+4]==2*k)&&board[t][14-t+i]==0&&board[t-2][14-t+i+2]==0&&board[t-3][14-t+i+3]==0&&board[t-5][14-t+i+5]==0){
                    huo2++;
                }
                //眠2
                if((t>i+4)&&((board[t][14-t+i]==-k&&board[t-1][14-t+i+1]==k&&(board[t-2][14-t+i+2]+board[t-3][14-t+i+3]+board[t-4][14-t+i+4]==k)&&board[t-2][14-t+i+2]!=-k&&board[t-3][14-t+i+3]!=-k&&board[t-4][14-t+i+4]!=-k&&board[t-5][14-t+i+5]==0)||
                        (board[t-5][14-t+i+5]==-k&&board[t-4][14-t+i+4]==k&&(board[t-3][14-t+i+3]+board[t-2][14-t+i+2]+board[t-1][14-t+i+1]==k)&&board[t-2][14-t+i+2]!=-k&&board[t-3][14-t+i+3]!=-k&&board[t-4][14-t+i+4]!=-k&&board[t][14-t+i]==0))){
                    mian2++;
                }
                if((t>i+3)&&(board[t][14-t+i]+board[t-4][14-t+i+4]==2*k)&&board[t-1][14-t+i+1]==0&&board[t-2][14-t+i+2]==0&&board[t-3][14-t+i+3]==0){
                    mian2++;
                }
                if((t>i+5)&&(board[t][14-t+i]+board[t-6][14-t+i+6]==-2*k)&&(board[t-2][14-t+i+2]+board[t-3][14-t+i+3]+board[t-4][14-t+i+4]==2*k)&&board[t-1][14-t+i+1]==0&&board[t-5][14-t+i+5]==0){
                    mian2++;
                }
                //死4
                if((t>i+4)&&(board[t][14-t+i]+board[t-5][14-t+i+5]==-2*k)&&(board[t-1][14-t+i+1]+board[t-2][14-t+i+2]+board[t-3][14-t+i+3]+board[t-4][14-t+i+4]==4*k)){
                    si4++;
                }
                //死3
                if((t>i+3)&&(board[t][14-t+i]+board[t-4][14-t+i+4]==-2*k)&&(board[t-1][14-t+i+1]+board[t-2][14-t+i+2]+board[t-3][14-t+i+3]==3*k)){
                    si3++;
                }
                if((t>i+2)&&(board[t][14-t+i]+board[t-3][14-t+i+3]==-2*k)&&(board[t-1][14-t+i+1]+board[t-2][14-t+i+2]==2*k)){
                    si2++;
                }

            }
        }
        map.put("zi5",zi5);
        map.put("huo4",huo4);
        map.put("mian4",mian4);
        map.put("huo3",huo3);
        map.put("mian3",mian3);
        map.put("huo2",huo2);
        map.put("mian2",mian2);
        map.put("si4",si4);
        map.put("si3",si3);
        map.put("si2",si2);
        return map;
    }
    //用于判断是否存在胜利，1为黑胜、0为还未定胜负、-1为白胜（判断在qipan类里）
    public int isvictory(int[][] board){
        //判断横向方向是否有5连珠
        for (int i=0;i<15;i++){
            for(int j=0;j<11;j++){
                if(board[i][j]+board[i][j+1]+board[i][j+2]+board[i][j+3]+board[i][j+4]==5) {
                    return 1;
                }else if(board[i][j]+board[i][j+1]+board[i][j+2]+board[i][j+3]+board[i][j+4]==-5){
                    return -1;
                }
            }
        }
        //判断纵向方向是否有5连珠
        for (int j=0;j<15;j++){
            for(int i=0;i<11;i++){
                if(board[i][j]+board[i+1][j]+board[i+2][j]+board[i+3][j]+board[i+4][j]==5){
                    return 1;
                }else if(board[i][j]+board[i+1][j]+board[i+2][j]+board[i+3][j]+board[i+4][j]==-5){
                    return -1;
                }
            }
        }
        //判断左轴向方向是否有5连珠
        for(int t=0;t<15;t++){
            for(int i=0;i+t<11;i++){
                if(board[i+t][i]+board[i+t+1][i+1]+board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==5){
                    return 1;
                }else if(board[i+t][i]+board[i+t+1][i+1]+board[i+t+2][i+2]+board[i+t+3][i+3]+board[i+t+4][i+4]==-5){
                    return -1;
                }
            }
        }
        for(int t=0;t<15;t++){
            for(int i=0;i+t<11;i++){
                if(board[i][i+t]+board[i+1][i+t+1]+board[i+2][i+t+2]+board[i+3][i+t+3]+board[i+4][i+t+4]==5){
                    return 1;
                }else if(board[i][i+t]+board[i+1][i+t+1]+board[i+2][i+t+2]+board[i+3][i+t+3]+board[i+4][i+t+4]==-5){
                    return -1;
                }
            }
        }
        //判断右轴向方向是否有5连珠
        for(int t=14;t>=0;t--){
            for(int i=0;t-i>3;i++){
                if(board[t-i][i]+board[t-i-1][i+1]+board[t-i-2][i+2]+board[t-i-3][i+3]+board[t-i-4][i+4]==5){
                    return 1;
                }else if(board[t-i][i]+board[t-i-1][i+1]+board[t-i-2][i+2]+board[t-i-3][i+3]+board[t-i-4][i+4]==-5){
                    return -1;
                }
            }
        }
        for(int t=14;t>=4;t--){
            for(int i=0;i<=t-4;i++){
                if(board[t][14-t+i]+board[t-1][14-t+i+1]+board[t-2][14-t+i+2]+board[t-3][14-t+i+3]+board[t-4][14-t+i+4]==5){
                    return 1;
                }else if(board[t][14-t+i]+board[t-1][14-t+i+1]+board[t-2][14-t+i+2]+board[t-3][14-t+i+3]+board[t-4][14-t+i+4]==-5){
                    return -1;
                }
            }
        }
        //否则还未定胜负
        return 0;
    }
    //根据所得棋型计算得分
    public int calculatescore(Map<String,Integer> map1){
        int score=0;

        int zi5   =map1.get("zi5");
        int huo4  =map1.get("huo4");
        int mian4 =map1.get("mian4");
        int huo3  =map1.get("huo3");
        int mian3 =map1.get("mian3");
        int huo2  =map1.get("huo2");
        int mian2 =map1.get("mian2");
        int si4   =map1.get("si4");
        int si3   =map1.get("si3");
        int si2   =map1.get("si2");

        if(zi5>=1){
            score+=zi5*100000;
        }
        if(huo3>=3){
            score+=20000;
        }
        if(huo4==1||mian4>=2||(mian4==1&&huo3==1)){
            score+=10000;
        }
        if(huo3==2){
            score+=5000;
        }
        if(huo3==1&&mian3==1){
            score+=1000;
        }
        if(mian4==1){
            score+=500;
        }

        if(huo3==1){
            score+=200;
        }
        if(huo2>=3){
            score+=150;
        }
        if(huo2==2){
            score+=100;
        }
        if(mian3==1){
            score+=50;
        }
        if(huo2==1&&mian2==1){
            score+=10;
        }
        if(huo2==1){
            score+=5;
        }
        if(mian2==1){
            score+=3;
        }
        if(si4==1||si3==1||si2==1){
            score+=-5;
        }
        return score;
    }

    //AI主程序
    public int AIXiaQi(int turns,int[][] board){
        alphabetaCut(turns,0,-1,board,-99999,99999);
        return searchresult;
    }
    //alpha-beta剪枝算法
    public int alphabetaCut(int turns,int currentdepth,int k,int[][] board,int max,int min){
        //到达搜索深度或者不是AI手，停止搜索
        if(currentdepth==searchdepth||turns==1){
            return 0;
        }
        int[] scores=new int[225];
        int max1=-999999;
        int max2=-999999;
        int max3=-999999;
        int max4=-999999;
        int max5=-999999;
        int min1=99999;
        int min2=99999;
        int min3=99999;
        int min4=99999;
        int min5=99999;
        int[] location =new int[5];
        //初始化得分
        for(int i=0;i<225;i++){
            scores[i]=0;
        }
        //排序取4个分值最高的10个落子点
        for(int i=0;i<15;i++){
            for (int j=0;j<15;j++){
                if(board[i][j]==0){
                    board[i][j]=-k;
                    Map<String,Integer> map1 = judgeqixing(-k, board);
                    board[i][j]=k;
                    Map<String,Integer> map2 = judgeqixing(k, board);
                    //防守分高选防守，进攻分高选进攻
                    if(calculatescore(map1)<=calculatescore(map2)){
                        scores[i*15+j]=calculatescore(map2)+position[i][j];
                    }else{
                        scores[i*15+j]=calculatescore(map1)+position[i][j];
                    }
                    scores[i*15+j]=calculatescore(map1)+calculatescore(map2)+position[i][j];
                    board[i][j]=0;
                    Map<String,Integer> map3 = judgeqixing(-k, board);
                    board[i][j]=k;
                    Map<String,Integer> map4 = judgeqixing(k, board);

                    if(scores[i*15+j]>max1){
                        location[0]=i*15+j;
                        max1=scores[location[0]];
                    }else if(scores[i*15+j]>max2){
                        location[1]=i*15+j;
                        max2=scores[location[1]];
                    }else if(scores[i*15+j]>max3){
                        location[2]=i*15+j;
                        max3=scores[location[2]];
                    }else if(scores[i*15+j]>max4){
                        location[3]=i*15+j;
                        max4=scores[location[3]];
                    }else if(scores[i*15+j]>max5){
                        location[4]=i*15+j;
                        max5=scores[location[4]];
                    }

                    board[i][j]=0;
                }
            }
        }
        int score;
        System.out.println("max1234:"+max1+","+max2+","+max3+","+max4);
        //递归进行深度搜索
        for(int x : location){
            System.out.println("currentdepth:"+currentdepth+"    location"+x/15+","+x%15);
            System.out.println(x/15+","+x%15+"的得分为："+scores[x]);
            board[x/15][x%15]=k;
            if(currentdepth==searchdepth-1){
                score=scores[x];
            }else {
                score=-alphabetaCut(turns,currentdepth+1, -k, board,-max,-min);
            }
            System.out.println("max:"+max+"   min:"+min);
            //αβ剪枝
            if(currentdepth%2==0){
                if(max<score){
                    max=score;
                    System.out.println("currentdepth:"+currentdepth+"    (i,j):"+x/15+","+x%15+"   max"+max);
                    if(currentdepth==0){//第一层必为max层
                        searchresult=x;
                    }
                }
                if(max>=min){//β剪枝
                    System.out.println("β剪枝"+x/15+","+x%15);
                    score=max;
                    board[x/15][x%15]=0;
                    return score;
                }
            }else if(currentdepth%2==1){
                min=Math.min(score,min);
                if(max>=min){//α剪枝
                    System.out.println("α剪枝"+x/15+","+x%15);
                    System.out.println("(i,j):"+x/15+","+x%15+"   min"+min);
                    score=min;
                    board[x/15][x%15]=0;
                    return score;
                }
            }
            board[x/15][x%15]=0;
        }
        return currentdepth%2==0?max:min;
    }

    //判断周围4格是否有子 gg了
    public boolean judgeyouzi(int i,int j,int[][] board){
        //横向
        if(i-4>=0&&i+4<=14&&board[i-1][j]==0&&board[i-2][j]==0&&board[i-3][j]==0&&board[i-4][j]==0&&board[i+1][j]==0&&board[i+2][j]==0&&board[i+3][j]==0&&board[i+4][j]==0){
            return false;
        }else if(i<4&&board[0][j]==0&&board[1][j]==0&&board[2][j]==0&&board[3][j]==0&&board[i+1][j]==0&&board[i+2][j]==0&&board[i+3][j]==0&&board[i+4][j]==0){
            return false;
        }else if(i>10&&board[i-1][j]==0&&board[i-2][j]==0&&board[i-3][j]==0&&board[i-4][j]==0&&board[11][j]==0&&board[12][j]==0&&board[13][j]==0&&board[14][j]==0){
            return false;
        }
        //纵向
        if(j-4>=0&&j+4<=14&&board[i][j-1]==0&&board[i][j-2]==0&&board[i][j-3]==0&&board[i][j-4]==0&&board[i][j+1]==0&&board[i][j+2]==0&&board[i][j+3]==0&&board[i][j+4]==0){
            return false;
        }else if(j<4&&board[i][0]==0&&board[i][1]==0&&board[i][2]==0&&board[i][3]==0&&board[i][j+1]==0&&board[i][j+2]==0&&board[i][j+3]==0&&board[i][j+4]==0){
            return false;
        }else if(j>10&&board[i][j-1]==0&&board[i][j-2]==0&&board[i][j-3]==0&&board[i][j-4]==0&&board[i][11]==0&&board[i][12]==0&&board[i][13]==0&&board[i][14]==0){
            return false;
        }
//        左轴向 0,12 ~ 2,14
        if(j-4>=0&&j+4<=14&&i-4>=0&&i+4<=14&&board[i-1][j-1]==0&&board[i-2][j-2]==0&&board[i-3][j-3]==0&&board[i-4][j-4]==0&&board[i+1][j+1]==0&&board[i+2][j+2]==0&&board[i+3][j+3]==0&&board[i+4][j+4]==0){
            return false;
        }else if(j<4&&i-4>=0&&i+4<=14&&board[i-j][0]==0&&board[i-j+1][1]==0&&board[i-j+2][2]==0&&board[i-j+3][3]==0&&board[i+1][j+1]==0&&board[i+2][j+2]==0&&board[i+3][j+3]==0&&board[i+4][j+4]==0){
            return false;
        }else if(j>10&&i-4>=0&&i+4<=14&&board[i-1][j-1]==0&&board[i-2][j-2]==0&&board[i-3][j-3]==0&&board[i-4][j-4]==0&&board[i-j+11][11]==0&&board[i-j+12][12]==0&&board[i-j+13][13]==0&&board[i-j+14][14]==0){
            return false;
        }else if(i<4){
            if((i>=j)&&board[i-j][0]==0&&board[i-j+1][1]==0&&board[i-j+2][2]==0&&board[i-j+3][3]==0&&board[i+1][j+1]==0&&board[i+2][j+2]==0&&board[i+3][j+3]==0){
                return false;
            }else if(i<j&&j<11&&board[0][j-i]==0&&board[1][j-i+1]==0&&board[2][j-i+2]==0&&board[i+1][j+1]==0&&board[i+2][j+2]==0&&board[i+3][j+3]==0){
                return false;
            }else if(i<j&&j>10){
                if(14-j+i<4){
                    return false;
                }else if(14-j+i>=4&&board[0][j-i]==0&&board[1][j-i+1]==0&&board[2][j-i+2]==0&&board[3][j-i+3]==0&&board[4][j-i+4]==0&&board[5][j-i-5]==0){
                    return false;
                }
            }
        }else if(i>10){
            if(j<4){
                int x=14-i+j;
                if(x<4){
                    return false;
                }else if(x>=4&&board[11][j-i+11]==0&&board[12][j-i+12]==0&&board[13][j-i+13]==0&&board[14][j-i+14]==0&&board[10][j-i+10]==0){
                    return false;
                }
            }else if(j>3&&j<=11&&board[i-1][j-1]==0&&board[i-2][j-2]==0&&board[i-3][j-3]==0&&board[i-4][j-4]==0&&board[11][j-i+11]==0&&board[12][j-i+12]==0&&board[13][j-i+13]==0&&board[14][j-i+14]==0){
                return false;
            }else if(j>11&&i>=j&&board[i-1][j-1]==0&&board[i-2][j-2]==0&&board[i-3][j-3]==0&&board[i-4][j-4]==0&&board[i-j+1][1]==0&&board[i-j+2][2]==0&&board[i-j+3][3]==0){
                return false;
            }else if(j>11&&i<j&&board[i-j+14][11]==0&&board[i-j+12][12]==0&&board[i-j+13][13]==0&&board[i-j+14][14]==0&&board[i-1][j-1]==0&&board[i-2][j-2]==0&&board[i-3][j-3]==0){
                return false;
            }
        }

        //右轴向
        if(j-4>=0&&j+4<=14&&i-4>=0&&i+4<=14&&board[i+1][j-1]==0&&board[i+2][j-2]==0&&board[i+3][j-3]==0&&board[i+4][j-4]==0&&board[i-1][j+1]==0&&board[i-2][j+2]==0&&board[i-3][j+3]==0&&board[i-4][j+4]==0){
            return false;
        }else if(j<4&&i+4<15&&i-4>=0&&board[i+j][0]==0&&board[i+j-1][1]==0&&board[i+j-2][2]==0&&board[i+j-3][3]==0&&board[i-1][j+1]==0&&board[i-2][j+2]==0&&board[i-3][j+3]==0&&board[i-4][j+4]==0){
            return false;
        }else if(j+4>14&&i-4>=0&&i+4<=14&&board[i+1][j-1]==0&&board[i+2][j-2]==0&&board[i+3][j-3]==0&&board[i+4][j-4]==0&&board[i+j-11][11]==0&&board[i+j-12][12]==0&&board[i+j-13][13]==0&&board[i+j-14][14]==0){
            return false;
        }else if(i<4){
            if(i>14-j&&board[i+j-11][11]==0&&board[i+j-12][12]==0&&board[i+j-13][13]==0&&board[i+j-14][14]==0&&board[i+1][j-1]==0&&board[i+2][j-2]==0&&board[i+3][j-3]==0&&board[i+4][j-4]==0){
                return false;
            }else if(i<=14-j&&j>=4&&board[i+1][j-1]==0&&board[i+2][j-2]==0&&board[i+3][j-3]==0&&board[i+4][j-4]==0&&board[0][j+i]==0&&board[1][j+i-1]==0&&board[2][j+i-2]==0&&board[3][j+i-3]==0){
                return false;
            }else if(j<4){
                int x=i+j;
                if(x<4){
                    return false;
                }else if(x>=4&&board[0][j+i]==0&&board[1][j+i-1]==0&&board[2][j+i-2]==0&&board[3][j+i-3]==0&&board[4][j+i-4]==0){
                    return false;
                }
            }
        }else if(i>10){
            if(14-i>=j&&board[i+j][0]==0&&board[i+j-1][1]==0&&board[i+j-2][2]==0&&board[i+j-3][3]==0&&board[i-1][j+1]==0&&board[i-2][j+2]==0&&board[i-3][j+3]==0&&board[i-4][j+4]==0){
                return false;
            }else if(j+3<15&&i+j<25&&board[14][j+i-14]==0&&board[13][j+i-13]==0&&board[12][j+i-12]==0&&board[11][j+i-11]==0&&board[i-1][j+1]==0&&board[i-2][j+2]==0&&board[i-3][j+3]==0){
                return false;
            }else if(j>10){
                if(i+j>=25){
                return false;
                }else if(board[14][j+i-14]==0&&board[13][j+i-13]==0&&board[12][j+i-12]==0&&board[11][j+i-11]==0&&board[10][j+i-10]==0){
                    return false;
                }
            }
        }
        if(i<=2&&j<=2){
            return false;
        }
        if(i>=12&&j<=2){
            return false;
        }
        if(i<=2&&j>=12){
            return false;
        }
        if(i>=12&&j>=12){
            return false;
        }
        System.out.println();
        return true;
    }
    //测试输出当前棋盘情况
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