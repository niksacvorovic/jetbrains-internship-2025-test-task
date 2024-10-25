import javax.swing.*;

public class MarkdownEditor extends JFrame{

    private JTextArea textArea;
    private JButton importButton;
    private JButton saveButton;
    private GroupLayout layout;

    public MarkdownEditor(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }

        this.textArea = new JTextArea();
        this.importButton = new JButton("Import File");
        this.saveButton = new JButton("Save Changes");
        this.layout = new GroupLayout(this.getContentPane());

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(this.textArea)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(this.importButton, 200, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(this.saveButton, 200, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(this.textArea)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(this.importButton)
                        .addComponent(this.saveButton)));

        this.layout.setAutoCreateGaps(true);
        this.layout.setAutoCreateContainerGaps(true);
        setLayout(this.layout);
        setTitle("MarkdownEditor");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setVisible(true);
    }
}
