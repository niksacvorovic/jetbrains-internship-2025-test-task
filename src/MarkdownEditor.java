import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import java.io.File;

public class MarkdownEditor extends JFrame{

    private final JTextArea textArea;
    private final JScrollPane scroll;
    private final JButton importButton;
    private final JButton saveButton;
    private final GroupLayout layout;
    private File file;

    public MarkdownEditor(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }

        this.textArea = new JTextArea("(Markdown text will appear here)");
        this.scroll = new JScrollPane(this.textArea);
        this.importButton = new JButton("Import File");
        this.saveButton = new JButton("Save Changes");
        this.layout = new GroupLayout(this.getContentPane());

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(scroll)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(importButton, 200, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveButton, 200, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(scroll)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(importButton)
                        .addComponent(saveButton)));

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(new FileNameExtensionFilter("Markdown Files (*.md)", "md"));
                fc.showOpenDialog(null);

                file = fc.getSelectedFile();
                FileOptionsKt.loadFile();

                importButton.setEnabled(false);
                saveButton.setEnabled(true);
                textArea.setEnabled(true);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOptionsKt.saveFile();
                importButton.setEnabled(true);
                saveButton.setEnabled(false);
                textArea.setText("(Markdown text will appear here)");
                textArea.setEnabled(false);
            }
        });

        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textArea.setEnabled(false);
        textArea.setLineWrap(true);
        saveButton.setEnabled(false);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        setLayout(layout);
        setTitle("MarkdownEditor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setVisible(true);
    }
}
