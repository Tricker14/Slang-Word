import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Find {
    private JTextArea resultArea;
    private HashMap<String, String> dictionary;

    public Find(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;

        JLabel title = new JLabel("Find Slang Words");

        JTextField search = new JTextField(30);

        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
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
            }
        });
    }

    public void findSlangMeaning(String slang) {
        String meaning = dictionary.get(slang);
        if (meaning != null) {
            resultArea.setText(meaning);
        } else {
            resultArea.setText("Slang word not found");
        }
    }
}
