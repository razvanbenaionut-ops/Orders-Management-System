package org.example;
import presentation.MainView;
import javax.swing.SwingUtilities;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainFrame = new MainView();
            mainFrame.setVisible(true);
        });
    }
}