package com.yyt.wuziqi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

@Component
//默认AI为白方，玩家为黑方且为先手
public class Qipan {

    private static int[][] board=new int[15][15];//场上棋子情况，0代表无子，1代表黑子，-1代表白子
    private static int turns = 1;    //用于标记下棋顺序 如果为1则为黑方下棋（即用户），若为0则为白方下棋（即AI）

    public void initialchess(JFrame jFrame){
        Graphics graphics = jFrame.getGraphics();
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setStroke(new BasicStroke(2.0f));
        for(int i=45;i<=675;i+=45){
            graphics2D.drawLine(45,i+20,675,i+20);
            graphics2D.drawLine(i,65,i,695);
        }
        turns=1;
        for(int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                board[i][j]=0;
            }
        }
    }

    public void create(){
        JFrame jFrame = new JFrame("五子棋");
        jFrame.setResizable(false);
        jFrame.setSize(1000,800);
        jFrame.setLayout(null);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel(null);
        jPanel.setLocation(0,0);

        //设置程序图标
        ImageIcon icon = new ImageIcon("./src/main/resources/static/images/tubiao.jpg");
        jFrame.setIconImage(icon.getImage());
//        添加古韵背景
        JLabel backgroundimg = new JLabel();
        ImageIcon imageIcon = new ImageIcon("./src/main/resources/static/images/guyun5.jpg");
        backgroundimg.setIcon( imageIcon);
        backgroundimg.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());

        JLabel hengqixian= new JLabel("0            1             2             3            4             5             6             7            8             9           10           11          12          13           14");
        hengqixian.setBounds(35,15,680,20);

        JLabel shuqixian0= new JLabel("0");
        shuqixian0.setBounds(22,30,20,20);
        JLabel shuqixian1= new JLabel("1");
        shuqixian1.setBounds(22,75,20,20);
        JLabel shuqixian2= new JLabel("2");
        shuqixian2.setBounds(22,120,20,20);
        JLabel shuqixian3= new JLabel("3");
        shuqixian3.setBounds(22,165,20,20);
        JLabel shuqixian4= new JLabel("4");
        shuqixian4.setBounds(22,210,20,20);
        JLabel shuqixian5= new JLabel("5");
        shuqixian5.setBounds(22,255,20,20);
        JLabel shuqixian6= new JLabel("6");
        shuqixian6.setBounds(22,300,20,20);
        JLabel shuqixian7= new JLabel("7");
        shuqixian7.setBounds(22,345,20,20);
        JLabel shuqixian8= new JLabel("8");
        shuqixian8.setBounds(22,390,20,20);
        JLabel shuqixian9= new JLabel("9");
        shuqixian9.setBounds(22,435,20,20);
        JLabel shuqixian10= new JLabel("10");
        shuqixian10.setBounds(22,480,20,20);
        JLabel shuqixian11= new JLabel("11");
        shuqixian11.setBounds(22,525,20,20);
        JLabel shuqixian12= new JLabel("12");
        shuqixian12.setBounds(22,570,20,20);
        JLabel shuqixian13= new JLabel("13");
        shuqixian13.setBounds(22,615,20,20);
        JLabel shuqixian14= new JLabel("14");
        shuqixian14.setBounds(22,660,20,20);

        //添加棋盘图
//        JLabel backgroundimg = new JLabel();
//        ImageIcon imageIcon = new ImageIcon("./src/main/resources/static/images/wuziqi.jpg");
//        backgroundimg.setIcon( imageIcon);
//        backgroundimg.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
//        jPanel.add(backgroundimg);

        JButton startbutton = new JButton("开始");
        startbutton.setFocusPainted(false);
        startbutton.setBackground(new Color(248,248,255));
        startbutton.setBorderPainted(false);
//        ImageIcon starticon = new ImageIcon("./src/main/resources/static/images/start.jpg");
//        startbutton.setIcon(starticon);
        startbutton.setForeground(Color.DARK_GRAY);
        startbutton.setBounds(800,100,80,50);

        startbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialchess(jFrame);
                int[] x = new int[]{45,90,135,180,225,270,315,360,405,450,495,540,585,630,675};
                int[] y = new int[]{65,110,155,200,245,290,335,380,425,470,515,560,605,650,695};
                jFrame.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == e.BUTTON1&&turns==1) {
                            for (int i = 0; i < 15; i++) {
                                if (e.getX() > x[i] - 15 && e.getX() < x[i] + 15) {
                                    for (int j = 0; j < 15; j++) {
                                        if (e.getY() > y[j] - 15 && e.getY() < y[j] + 15 &&board[i][j]==0) {
                                            Graphics g = jFrame.getGraphics();
                                            g.setColor(Color.BLACK);
                                            g.fillArc(x[i]-10, y[j]-10, 20, 20, 0, 360);
                                            board[i][j] = 1;

                                            System.out.println("黑方落子位置："+i+","+j);
                                            if(isvictory(board)==0){
                                                turns=0;
                                                int chesslocation =new AI4wuziqi().AIXiaQi(turns,board);
                                                board[chesslocation/15][chesslocation%15]=-1;

                                                g.setColor(Color.white);
                                                g.fillArc(x[chesslocation/15]-10, y[chesslocation%15]-10, 20, 20, 0, 360);

                                                //用来测试棋型（测出了n个bug）
//                                                System.out.println(isvictory(board));
//                                                AI4wuziqi ai4wuziqi = new AI4wuziqi();
//                                                System.out.println(ai4wuziqi.judgeqixing(1,board));
//                                                System.out.println(ai4wuziqi.calculatescore(ai4wuziqi.judgeqixing(1,board)));

                                                turns=1;
                                            }else if(isvictory(board)==1){
                                                new WinFrame().create();
                                            }else if(isvictory(board)==-1){
                                                new LoseFrame().create();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
        });
        jPanel.add(hengqixian);
        jPanel.add(shuqixian0);
        jPanel.add(shuqixian1);
        jPanel.add(shuqixian2);
        jPanel.add(shuqixian3);
        jPanel.add(shuqixian4);
        jPanel.add(shuqixian5);
        jPanel.add(shuqixian6);
        jPanel.add(shuqixian7);
        jPanel.add(shuqixian8);
        jPanel.add(shuqixian9);
        jPanel.add(shuqixian10);
        jPanel.add(shuqixian11);
        jPanel.add(shuqixian12);
        jPanel.add(shuqixian13);
        jPanel.add(shuqixian14);


        jPanel.add(startbutton);
        jPanel.add(backgroundimg);
        jFrame.setContentPane(jPanel);
        jFrame.setVisible(true);
    }
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



}
