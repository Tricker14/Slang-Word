import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Delete {
    private HashMap<String, String> dictionary;
    public Delete(HashMap<String, String> dictionary){
        this.dictionary = dictionary;

        JPanel pane1 = new JPanel();
        JLabel title = new JLabel("Delete Slang Word");
        pane1.add(title);

        JPanel slangWord = new JPanel();
        slangWord.setLayout(new FlowLayout());
        JLabel titleSlangWord = new JLabel("Enter Slang Word");
        JTextField fieldSlangWord = new JTextField(30);
        slangWord.add(titleSlangWord);
        slangWord.add(fieldSlangWord);

        JPanel pane2 = new JPanel();
        JButton button = new JButton("Delete");
        pane2.add(button);

        JFrame frame = new JFrame("Delete Slang Words");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(3, 1)); // 3 rows, 1 column

        frame.add(pane1);
        frame.add(slangWord);
        frame.add(pane2);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String slang = fieldSlangWord.getText();
                int choice = showConfirmationDialog();
                if (choice == JOptionPane.YES_OPTION) {
                    deleteSlang(slang);
                }
            }
        });
    }

    public void deleteSlang(String slang){
        if(dictionary.get(slang) == null){
            JOptionPane.showMessageDialog(null, "This slang does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            dictionary.remove(slang);
            JOptionPane.showMessageDialog(null, "Delete slang word successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            deleteInFile();
        }
    }
    public int showConfirmationDialog() {
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this slang word?", "Confirmation", JOptionPane.YES_NO_OPTION);
        return choice;
    }
    public void deleteInFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"))) {
            for (String key : dictionary.keySet()) {
                String value = dictionary.get(key);
                String outputLine = key + "`" + value;
                bw.write(outputLine);
                bw.newLine(); // Add a newline to separate entries
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error writing data to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
