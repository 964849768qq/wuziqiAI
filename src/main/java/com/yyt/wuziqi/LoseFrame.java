package com.yyt.wuziqi;

import javax.swing.*;

public class LoseFrame {
    public void create(){
        JFrame jFrame = new JFrame("你输啦！");
        jFrame.setResizable(false);
        jFrame.setSize(300,200);
        jFrame.setLayout(null);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        ImageIcon icon = new ImageIcon("./src/main/resources/static/images/tubiao.jpg");
        jFrame.setIconImage(icon.getImage());

        jFrame.setVisible(true);
    }
}
