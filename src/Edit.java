import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
            editWord(slang, meaning, "slang.txt");
            JOptionPane.showMessageDialog(null, "Edit slang word successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void editWord(String slang, String meaning, String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(slang)) {
                    System.out.println(line);
                    line = slang + "`" + meaning;
                    System.out.println(line);
                }
                fileContent.append(line).append(System.lineSeparator());
            }
            br.close();

            // Write the modified content back to the file
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(fileContent.toString());
            bw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error edit slang", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
