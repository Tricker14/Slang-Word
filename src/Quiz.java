import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class Quiz {
    private HashMap<String, String> dictionary;

    public Quiz(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;

        JFrame frame = new JFrame("Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        JLabel title = new JLabel("Quiz");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel choice = new JPanel();
        choice.setLayout(new FlowLayout(FlowLayout.CENTER)); // Buttons centered horizontally
        JButton buttonSlang = new JButton("Slang Words");
        JButton buttonMeaning = new JButton("Meaning");
        choice.add(buttonSlang);
        choice.add(buttonMeaning);

        int topSpacing = 80;
        choice.setBorder(BorderFactory.createEmptyBorder(topSpacing, 0, 0, 0));

        frame.add(title, BorderLayout.NORTH);
        frame.add(choice, BorderLayout.CENTER);

        buttonSlang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a new window for the quiz question
                openQuizQuestion();
            }
        });

        buttonMeaning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a new window for the quiz question
                openQuizQuestion();
            }
        });
    }

    public void openQuizQuestion() {
        JFrame quizFrame = new JFrame("Quiz Question");
        quizFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        quizFrame.setSize(500, 300);
        quizFrame.setLayout(new BorderLayout());
        quizFrame.setVisible(true);

        String randomSlang = generateRandomSlang();
        JLabel questionLabel = new JLabel("What is the definition for: " + randomSlang + " ?");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2, 2, 40, 40)); // Four options vertically

        // Add your answer options (JButtons) here
        JButton option1 = new JButton("Option 1");
        JButton option2 = new JButton("Option 2");
        JButton option3 = new JButton("Option 3");
        JButton option4 = new JButton("Option 4");

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        quizFrame.add(questionLabel, BorderLayout.NORTH);
        quizFrame.add(optionsPanel, BorderLayout.CENTER);
    }

    private String generateRandomSlang() {
        if (dictionary.isEmpty()) {
            return "Dictionary is empty. Cannot generate a random slang word.";
        } else {
            Random random = new Random();
            Object[] keys = dictionary.keySet().toArray();
            String randomSlang = (String) keys[random.nextInt(keys.length)];
            return randomSlang;
        }
    }
}
