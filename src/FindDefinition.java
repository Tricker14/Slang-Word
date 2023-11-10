import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.ArrayList;

public class FindDefinition {
    private JTextArea resultArea;
    private HashMap<String, String> dictionary;

    public FindDefinition(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;

        JLabel title = new JLabel("Find By Definition");

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

        JFrame frame = new JFrame("Find By Definition");
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
                findByDefinition(search.getText());
            }
        });
    }

    public void findByDefinition(String definition) {
        ArrayList<String> slangs = new ArrayList<String>();
        for(String i : dictionary.keySet()){
            if(dictionary.get(i).toLowerCase().contains(definition.toLowerCase())){
                slangs.add(i);
            }
        }
        if (slangs.size() > 0) {
            resultArea.setText("");
            for (String slang : slangs) {
                resultArea.append(slang + "\n"); // Display each slang word on a new line
            }
        } else {
            resultArea.setText("Slang words not found");
        }
    }
}
