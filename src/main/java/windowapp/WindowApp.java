package windowapp;

import javax.swing.*;

/**
 * Created by M.Khabenko on 3/14/2017.
 */
public class WindowApp extends JFrame {

    public WindowApp(String string){
        super("My First Window"); //Заголовок окна
        setBounds(100, 100, 200, 200); //Если не выставить
        //размер и положение
        //то окно будет мелкое и незаметное
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //это нужно для того чтобы при
        //закрытии окна закрывалась и программа,
        //иначе она останется висеть в процессах

    }



}
