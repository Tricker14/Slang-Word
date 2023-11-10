import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class ShowHistory {
    private JTable historyTable;

    public ShowHistory() {
        JLabel title = new JLabel("Show History");

        JPanel pane1 = new JPanel();
        pane1.add(title);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Number");
        model.addColumn("Slang Words");

        historyTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(historyTable);

        JFrame frame = new JFrame("Show History");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        frame.add(pane1, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        readHistory(model);
    }
    public static void readHistory(DefaultTableModel model){
        try{
            BufferedReader br = new BufferedReader(new FileReader("history.txt"));
            String line;
            int lineNumber = 1;
            while((line = br.readLine()) != null){
                model.addRow(new Object[]{lineNumber, line});
                lineNumber++;
            }
            br.close();
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Error reading data from file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
