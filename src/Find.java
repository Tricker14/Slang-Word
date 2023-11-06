import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Find {
    private JTextArea resultArea;
    private HashMap<String, String> dictionary;

    public Find(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;

        JLabel title = new JLabel("Find Slang Words");

        JTextField search = new JTextField(30);

        JPanel pane1 = new JPanel();
        pane1.add(title);
        pane1.add(search);

        JPanel pane2 = new JPanel();
        JButton button = new JButton("Find");
        pane2.add(button);

        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        JFrame frame = new JFrame("Find Slang Words");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        frame.add(pane1, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(pane2, BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findSlangMeaning(search.getText());
                saveHistory(search.getText(), "history.txt");
            }
        });
    }

    public void findSlangMeaning(String slang) {
        String meaning = dictionary.get(slang);
        if (meaning != null) {
            meaning = meaning.replace("| ", "\n"); // Replace | with a newline
            resultArea.setText(meaning);
        } else {
            resultArea.setText("Slang word not found");
        }
    }

    public void saveHistory(String slangWord, String fileName){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
            bw.write(slangWord);
            bw.newLine();
            bw.close();
        }
        catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Error writing data to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
