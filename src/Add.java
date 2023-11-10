import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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
            addSlang(slang, meaning, "data.txt");
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
                alterSlang(slang, newMeaning, "data.txt");
            } else if (choice == 1) {
                // Handle duplicate if the user chooses "Duplicate"
                String oldMeaning = dictionary.get(slang);
                if(oldMeaning.contains(meaning)){
                    JOptionPane.showMessageDialog(null, "This meaning already exist", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String newMeaning = oldMeaning + "| " + meaning; // Append the new meaning.
                    System.out.println(newMeaning);
                    dictionary.replace(slang, newMeaning);
                    JOptionPane.showMessageDialog(null, "Slang word duplicated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    alterSlang(slang, newMeaning, "data.txt");
                }
            }
        }
    }
    public void addSlang(String slang, String meaning, String fileName){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(slang + "`" + meaning);
            writer.newLine(); // Add a newline for the next entry

            // Close the writer
            writer.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error adding new word", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void alterSlang(String slang, String meaning, String fileName) {
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
            JOptionPane.showMessageDialog(null, "Error adding new word", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
