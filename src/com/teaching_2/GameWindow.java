package com.teaching_2;

import javax.imageio.ImageIO; //чтение фоток
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GameWindow extends JFrame { //наследование класса
    private static GameWindow game_Window;
    private static long last_frame_time;
    private static Image background;
    private static Image game_over;
    private static Image drop;
    private static float drop_left = 200;
    private static float drop_top = -100;
    private static float drop_v = 200;

    public static void main(String[] args) throws IOException {
        background = ImageIO.read(GameWindow.class.getResourceAsStream("background.JPG")); //  для загрузки картинок
        game_over = ImageIO.read(GameWindow.class.getResourceAsStream("game_over.JPG"));
        drop = ImageIO.read(GameWindow.class.getResourceAsStream("drop.JPG"));
        game_Window = new GameWindow();
    game_Window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //при закрытии программа завершится
    game_Window.setLocation(200,100);
    game_Window.setSize(1000,800);
    game_Window.setResizable(false);
    last_frame_time = System.nanoTime(); // возвращает время в секундах
    GameField game_field = new GameField();
    game_Window.add(game_field);
    game_Window.setVisible(true);

    }
    private static void Repaint(Graphics g){
        long current_time = System.nanoTime();
        float delta_time = (current_time - last_frame_time) * .000000001f;//текущее время - засеченое
        last_frame_time = current_time;
        drop_top = drop_top + drop_v * delta_time;

        g.drawImage(background, 0, 0,null);

    g.drawImage(drop,(int)drop_left,(int)drop_top,null);
    //g.drawImage(game_over,280,120,null);

    }
    private static class GameField extends  JPanel{ //Класс для управлении панелями,которые нужны для редактирования
    @Override
        protected void paintComponent(Graphics g){ //вместо paintcomp.. который находится в jpanel
                                                    // будет вызван наш метод
        super.paintComponent(g); //отрисовка панели
        Repaint(g);
        repaint();
    }
    }

}
