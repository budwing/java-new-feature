package com.github.budwing.java9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SimpleModule {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java 9 Platform Module System(JPMS)");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLUE);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}
