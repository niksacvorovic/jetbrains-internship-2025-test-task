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
    private val layoutManager = GroupLayout(this.contentPane)
    private lateinit var file: File

    init{
        layoutManager.setHorizontalGroup(layoutManager.createSequentialGroup()
            .addComponent(scroll)
            .addGroup(layoutManager.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(importButton, 200, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(saveButton, 200, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))

        layoutManager.setVerticalGroup(layoutManager.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
            .addGroup(layoutManager.createSequentialGroup()
                .addComponent(importButton)
                .addComponent(saveButton)))

        importButton.addActionListener {
            val fc = JFileChooser()
            fc.fileFilter = FileNameExtensionFilter("Markdown Files (*.md)", "md")
            fc.isAcceptAllFileFilterUsed = false
            val result = fc.showOpenDialog(null)

            if(result == JFileChooser.APPROVE_OPTION){
                file = fc.selectedFile
                title = file.name + " [MarkdownEditor]"
                textArea.text = file.readText()
                importButton.isEnabled = false
                saveButton.isEnabled = true
                textArea.isEnabled = true
            }
        }

        saveButton.addActionListener {
            file.writeText(textArea.text)
            JOptionPane.showMessageDialog(null, "File successfully saved!")
            title = "MarkdownEditor"
            textArea.text = "(Markdown text will appear here)"
            importButton.isEnabled = true
            saveButton.isEnabled = false
            textArea.isEnabled = false
        }

        scroll.border = BorderFactory.createLineBorder(Color.BLACK)
        textArea.isEnabled = false
        textArea.lineWrap = true
        saveButton.isEnabled = false
        layoutManager.autoCreateGaps = true
        layoutManager.autoCreateContainerGaps = true

        layout = layoutManager
        title = "MarkdownEditor"
        defaultCloseOperation = EXIT_ON_CLOSE
        bounds = Rectangle(100, 100, 800, 500)
        isVisible = true
    }
}
