import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Edit {
    private HashMap<String, String> dictionary;
    public Edit(HashMap<String, String> dictionary){
        this.dictionary = dictionary;

        JPanel pane1 = new JPanel();
        JLabel title = new JLabel("Edit Slang Word");
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
        JButton button = new JButton("Edit");
        pane2.add(button);

        JFrame frame = new JFrame("Edit Slang Words");
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
                editSlang(slang, meaning);
            }
        });
    }

    public void editSlang(String slang, String meaning){
        if(dictionary.get(slang) == null){
            JOptionPane.showMessageDialog(null, "This slang does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            String newMeaning = meaning;
            dictionary.replace(slang, newMeaning);
            JOptionPane.showMessageDialog(null, "Edit slang word successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
