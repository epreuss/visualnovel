package gui;

import forms.Editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import model.Scene;

/**
 * Adiciona codigo para a area de texto da cena, atualiza a area de texto com
 * as linhas da cena aberto e retorna um texto usado para salvar a cena.
 * @author Estevan
 */
public class SceneEditor implements ActionListener
{
    Editor editor;
    JTextArea textArea;
    JComboBox comboBox;
    int currentSceneID;
    
    public SceneEditor(Editor editor, JTextArea textArea, JComboBox comboBox)
    {
        this.editor = editor;
        this.textArea = textArea;
        this.comboBox = comboBox;
        currentSceneID = -1;
    }

    /**
     * Listener para os botoes que editam a cena. 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand())
        {
            case "Dialogue":
                addCode("dialogo");
                break;
            case "Change BG":
                addCode("dialogo");
                break;
            case "Change Music":
                addCode("dialogo");
                break;
            case "Add Sprite":
                addCode("dialogo");
                break;
            case "Remove Sprite":
                addCode("dialogo");
                break;
            case "Choose":
                addCode("dialogo");
                break;
            case "Jump":           
                addCode("dialogo");
                break;
        }            
    }
    
    /**
     * Transfere as linhas de uma cena para a area de texto.
     * @param target 
     */
    public void updateTextArea(Scene target)
    {
        if (target == null)
            return;
        if (currentSceneID != target.id)
        {            
            currentSceneID = target.id;
            String text = "";
            for (String line : target.getText())
                text += line;
            textArea.setText(text);
        }
    }
    
    public void setTextAreaEnabled(boolean active)
    {
        textArea.setEnabled(active);
        editor.setSceneEditorButtonsEnabled(active);
        if (active == false)
            textArea.setText("");
    }
    
    /**
     * Pega a String no componente de area de texto e
     * transforma em uma lista de Strings, que eh
     * formato que a cena usa para salvar seu texto.
     * @return 
     */
    public List<String> getSceneText()
    {
        List<String> text = new ArrayList<>();
        String allText = textArea.getText();
        String currentLine = "";
        for (int i = 0; i < allText.length(); i++)
        {
            currentLine += allText.charAt(i);
            if (allText.charAt(i) == '\n' || i == allText.length() - 1)
            {
                text.add(currentLine);
                currentLine = "";
            }
        }
        return text;
    }
    
    /**
     * Adiciona uma linha de codigo para a area de texto.
     * @param code 
     */
    public void addCode(String code)
    {
        textArea.setText(textArea.getText() + '\n' + code);
    }         
}
