import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Add {
    private HashMap<String, String> dictionary;
    public Add(HashMap<String, String> dictionary){
        this.dictionary = dictionary;

        JPanel pane1 = new JPanel();
        JLabel title = new JLabel("Add Slang Word");
        pane1.add(title);

        JPanel slangWord = new JPanel();
        slangWord.setLayout(new FlowLayout());
        JLabel titleSlangWord = new JLabel("Enter Slang Word");
        JTextField fieldSlangWord = new JTextField(30);
        slangWord.add(titleSlangWord);
        slangWord.add(fieldSlangWord);

        JPanel meaning = new JPanel();
        meaning.setLayout(new FlowLayout());
        JLabel titleMeaning = new JLabel("Enter Meaning");
        JTextField fieldMeaning= new JTextField(30);
        meaning.add(titleMeaning);
        meaning.add(fieldMeaning);

        JPanel pane2 = new JPanel();
        JButton button = new JButton("Add");
        pane2.add(button);

        JFrame frame = new JFrame("Find Slang Words");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(4, 1)); // 3 rows, 1 column

        frame.add(pane1);
        frame.add(slangWord);
        frame.add(meaning);
        frame.add(pane2);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = fieldSlangWord.getText();
                String meaning = fieldMeaning.getText();
                addSlang(slang, meaning);
            }
        });
    }

    public void addSlang(String slang, String meaning){
        if(dictionary.get(slang) == null){
            dictionary.put(slang, meaning);
            JOptionPane.showMessageDialog(null, "Slang word added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            // Slang already exists, show a dialog with three choices
            String[] options = {"Overwrite", "Duplicate", "Cancel"};
            int choice = JOptionPane.showOptionDialog(null, "Slang word already exists. What would you like to do?", "Slang Exists",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                // Handle overwrite if the user chooses "Overwrite"
                String newMeaning = meaning;
                dictionary.replace(slang, newMeaning);
                JOptionPane.showMessageDialog(null, "Slang word overwrite successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (choice == 1) {
                // Handle duplicate if the user chooses "Duplicate"
                String newMeaning = meaning;
                dictionary.replace(slang, newMeaning);
                JOptionPane.showMessageDialog(null, "Slang word duplicated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
