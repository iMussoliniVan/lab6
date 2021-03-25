package com.company;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JMenuItem pauseMenuBigItem;
    private JMenuItem resumeMenuBigItem;
    private Field field = new Field();
    public MainFrame() {
        super("Программирование и синхронизация потоков");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        setExtendedState(MAXIMIZED_BOTH);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            public void actionPerformed(ActionEvent event) {
                field.addBall();
                if (!pauseMenuItem.isEnabled() && !resumeMenuItem.isEnabled() && !pauseMenuBigItem.isEnabled() && !resumeMenuBigItem.isEnabled()) {
                    pauseMenuItem.setEnabled(true);
                    pauseMenuBigItem.setEnabled(true);
                }
            }
        };
        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);
        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);
        Action pauseAction = new AbstractAction("Приостановить движение"){
            public void actionPerformed(ActionEvent event) {
                field.pause();
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
                pauseMenuBigItem.setEnabled(false);
            }
        };
        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);
        Action resumeAction = new AbstractAction("Возобновить движение") {
            public void actionPerformed(ActionEvent event) {
                field.resume();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
                pauseMenuBigItem.setEnabled(true);
            }
        };
        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);
        Action pauseBigAction = new AbstractAction("Приостановить движение больших"){
            public void actionPerformed(ActionEvent event) {
                field.pauseBig();
                pauseMenuBigItem.setEnabled(false);
                pauseMenuItem.setEnabled(false);
                resumeMenuBigItem.setEnabled(true);
            }
        };
        pauseMenuBigItem = controlMenu.add(pauseBigAction);
        pauseMenuBigItem.setEnabled(false);
        Action resumeBigAction = new AbstractAction("Возобновить движение больших") {
            public void actionPerformed(ActionEvent event) {
                field.resumeBig();
                pauseMenuBigItem.setEnabled(true);
                resumeMenuBigItem.setEnabled(false);
                pauseMenuItem.setEnabled(true);
            }
        };
        resumeMenuBigItem = controlMenu.add(resumeBigAction);
        resumeMenuBigItem.setEnabled(false);
        getContentPane().add(field, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}