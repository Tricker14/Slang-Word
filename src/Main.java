import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

public class Main {
    private static HashMap<String, String> dictionary;

    public static void mainScreen() {
        JLabel title = new JLabel("Slang Words");

        JPanel titleContainer = new JPanel();
        titleContainer.add(title);

        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(3, 3, 5, 5));

        JButton find = new JButton("Find Words");
        JButton definition = new JButton("Find Definition");
        JButton history = new JButton("Show History");
        JButton add = new JButton("Add Word");
        JButton edit = new JButton("Edit Word");
        JButton delete = new JButton("Delete Word");
        JButton reset = new JButton("Reset List");
        JButton random = new JButton("Random Word");
        JButton quiz = new JButton("Quiz");

        menu.add(find);
        menu.add(definition);
        menu.add(history);
        menu.add(add);
        menu.add(edit);
        menu.add(delete);
        menu.add(reset);
        menu.add(random);
        menu.add(quiz);

        JFrame frame = new JFrame("Main Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setVisible(true);

        frame.setLayout(new BorderLayout());

        frame.add(titleContainer, BorderLayout.NORTH);
        frame.add(menu);

        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the SearchScreen when the button is clicked
                new Find(dictionary);
            }
        });
        definition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the SearchScreen when the button is clicked
                new FindDefinition(dictionary);
            }
        });
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the SearchScreen when the button is clicked
                new ShowHistory();
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the SearchScreen when the button is clicked
                new Add(dictionary);
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Edit(dictionary);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Delete(dictionary);
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDictionary();
                JOptionPane.showMessageDialog(null, "Reset list successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RandomSlang(dictionary);
            }
        });
        quiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Quiz(dictionary);
            }
        });
    }

    public static void loadDictionary() {
        dictionary = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("`");
                if (parts.length == 2) {
                    dictionary.put(parts[0], parts[1]);
                }
            }
            br.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading data from file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        loadDictionary();
        mainScreen();
    }
}
