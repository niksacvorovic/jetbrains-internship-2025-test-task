import javax.swing.*
import javax.swing.filechooser.FileNameExtensionFilter

import java.awt.Color
import java.awt.Rectangle

import java.io.File

class MarkdownEditor : JFrame(){
    
    private val textArea = JTextArea("(Markdown text will appear here)")
    private val scroll = JScrollPane(textArea)
    private val importButton = JButton("Import File")
    private val saveButton = JButton("Save Changes")
    private val layout = GroupLayout(this.contentPane)
    private var file: File? = null

    init{
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addComponent(scroll)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(importButton, 200, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(saveButton, 200, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
            .addGroup(layout.createSequentialGroup()
                .addComponent(importButton)
                .addComponent(saveButton)))

        importButton.addActionListener {
            val fc = JFileChooser()
            fc.fileFilter = FileNameExtensionFilter("Markdown Files (*.md)", "md")
            val result = fc.showOpenDialog(null)

            if(result == JFileChooser.APPROVE_OPTION){
                file = fc.selectedFile
                loadFile()
                importButton.isEnabled = false
                saveButton.isEnabled = true
                textArea.isEnabled = true
            }
        }

        saveButton.addActionListener {
            saveFile()
            importButton.isEnabled = true
            saveButton.isEnabled = false
            textArea.text = "(Markdown text will appear here)"
            textArea.isEnabled = false
        }

        scroll.border = BorderFactory.createLineBorder(Color.BLACK)
        textArea.isEnabled = false
        textArea.lineWrap = true
        saveButton.isEnabled = false
        layout.autoCreateGaps = true
        layout.autoCreateContainerGaps = true

        setLayout(layout)
        title = "MarkdownEditor"
        defaultCloseOperation = EXIT_ON_CLOSE
        bounds = Rectangle(100, 100, 800, 500)
        isVisible = true
    }
}
