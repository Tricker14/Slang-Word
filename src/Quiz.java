import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Quiz {
    private HashMap<String, String> dictionary;

    public Quiz(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;

        JFrame frame = new JFrame("Quiz");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                openQuizQuestionSlang();
            }
        });

        buttonMeaning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open a new window for the quiz question
                openQuizQuestionMeaning();
            }
        });
    }

    public void openQuizQuestionSlang() {
        JFrame quizFrame = new JFrame("Random Slang Words Game");
        quizFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        quizFrame.setSize(500, 300);
        quizFrame.setLayout(new BorderLayout());
        quizFrame.setVisible(true);

        String randomSlang = generateRandomSlang();
        String correctMeaning = dictionary.get(randomSlang);

        JLabel questionLabel = new JLabel("What is the meaning of: " + randomSlang + " ?");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2, 2, 40, 40)); // Four options vertically

        // Generate three random incorrect meanings
        ArrayList<String> incorrectMeanings = generateRandomIncorrectMeanings(correctMeaning);

        // Shuffle the meanings to place the correct one randomly
        incorrectMeanings.add(correctMeaning);
        Collections.shuffle(incorrectMeanings);

        // Add your answer options (JButtons) with shuffled meanings
        JButton option1 = new JButton(incorrectMeanings.get(0));
        JButton option2 = new JButton(incorrectMeanings.get(1));
        JButton option3 = new JButton(incorrectMeanings.get(2));
        JButton option4 = new JButton(incorrectMeanings.get(3));

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        quizFrame.add(questionLabel, BorderLayout.NORTH);
        quizFrame.add(optionsPanel, BorderLayout.CENTER);

        // Add action listeners to the buttons
        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswerSlang(option1.getText(), correctMeaning);
            }
        });

        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswerSlang(option2.getText(), correctMeaning);
            }
        });

        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswerSlang(option3.getText(), correctMeaning);
            }
        });

        option4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswerSlang(option4.getText(), correctMeaning);
            }
        });
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
    private ArrayList<String> generateRandomIncorrectMeanings(String correctMeaning) {
        // Create a list of all values (meanings) from the dictionary
        ArrayList<String> allMeanings = new ArrayList<>(dictionary.values());

        // Remove the correct meaning from the list
        allMeanings.remove(correctMeaning);

        // Shuffle the list and select three random incorrect meanings
        Collections.shuffle(allMeanings);

        ArrayList<String> incorrectMeanings = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            incorrectMeanings.add(allMeanings.get(i));
        }

        return incorrectMeanings;
    }
    private void handleAnswerSlang(String selectedAnswer, String correctAnswer) {
        if (selectedAnswer.equals(correctAnswer)) {
            // Correct answer, show a success message dialog
            JOptionPane.showMessageDialog(null, "Correct! That's the right meaning.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Incorrect answer, show an error message dialog
            JOptionPane.showMessageDialog(null, "Sorry, that's incorrect", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void openQuizQuestionMeaning() {
        JFrame quizFrame = new JFrame("Random Definition Game");
        quizFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        quizFrame.setSize(500, 300);
        quizFrame.setLayout(new BorderLayout());
        quizFrame.setVisible(true);

        String randomMeaning = generateRandomMeaning();
        String tempCorrectSlang = "";
        for(String i : dictionary.keySet()){
            if(dictionary.get(i) == randomMeaning){
                tempCorrectSlang = i;
            }
        }
        String correctSlang = tempCorrectSlang;

        JLabel questionLabel = new JLabel("What is the slang of: " + randomMeaning + " ?");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2, 2, 40, 40)); // Four options vertically

        // Generate three random incorrect meanings
        ArrayList<String> incorrectSlangs = generateRandomIncorrectSlangs(correctSlang);

        // Shuffle the meanings to place the correct one randomly
        incorrectSlangs.add(correctSlang);
        Collections.shuffle(incorrectSlangs);

        // Add your answer options (JButtons) with shuffled meanings
        JButton option1 = new JButton(incorrectSlangs.get(0));
        JButton option2 = new JButton(incorrectSlangs.get(1));
        JButton option3 = new JButton(incorrectSlangs.get(2));
        JButton option4 = new JButton(incorrectSlangs.get(3));

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        quizFrame.add(questionLabel, BorderLayout.NORTH);
        quizFrame.add(optionsPanel, BorderLayout.CENTER);

        // Add action listeners to the buttons
        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswerMeaning(option1.getText(), correctSlang);
            }
        });

        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswerMeaning(option2.getText(), correctSlang);
            }
        });

        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswerMeaning(option3.getText(), correctSlang);
            }
        });

        option4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswerMeaning(option4.getText(), correctSlang);
            }
        });
    }
    private String generateRandomMeaning() {
        if (dictionary.isEmpty()) {
            return "Dictionary is empty. Cannot generate a random meaning.";
        } else {
            Random random = new Random();
            Object[] values = dictionary.values().toArray();
            String randomMeaning = (String) values[random.nextInt(values.length)];
            return randomMeaning;
        }
    }

    private ArrayList<String> generateRandomIncorrectSlangs(String correctSlang) {
        // Create a list of all slang words (keys) from the dictionary
        ArrayList<String> allSlangs = new ArrayList<>(dictionary.keySet());

        // Remove the correct slang from the list
        allSlangs.remove(correctSlang);

        // Shuffle the list and select three random incorrect slangs
        Collections.shuffle(allSlangs);

        ArrayList<String> incorrectSlangs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            incorrectSlangs.add(allSlangs.get(i));
        }
        return incorrectSlangs;
    }
    private void handleAnswerMeaning(String selectedAnswer, String correctAnswer) {
        if (selectedAnswer.equals(correctAnswer)) {
            // Correct answer, show a success message dialog
            JOptionPane.showMessageDialog(null, "Correct! That's the right slang.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Incorrect answer, show an error message dialog
            JOptionPane.showMessageDialog(null, "Sorry, that's incorrect", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
