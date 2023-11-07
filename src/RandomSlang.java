import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class RandomSlang {
    private HashMap<String, String> dictionary;

    public RandomSlang(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;

        JFrame frame = new JFrame("Random Slang Word");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout()); // 2 rows, 1 column

        JPanel titleContainer = new JPanel();
        JLabel title = new JLabel("Random Slang Word");
        titleContainer.add(title);

        JTextField resultField = new JTextField(30);
        resultField.setEditable(false);
        resultField.setHorizontalAlignment(SwingConstants.CENTER);

        frame.add(titleContainer, BorderLayout.NORTH);
        frame.add(resultField, BorderLayout.CENTER);

        generateRandomSlang(resultField);
    }

    private void generateRandomSlang(JTextField resultField) {
        if (dictionary.isEmpty()) {
            resultField.setText("Dictionary is empty. Cannot generate a random slang word.");
        } else {
            Random random = new Random();
            Object[] keys = dictionary.keySet().toArray();
            String randomSlang = (String) keys[random.nextInt(keys.length)];
            resultField.setText(randomSlang);
        }
    }
}
