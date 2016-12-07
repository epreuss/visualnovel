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
    String[] staticCodes;
    
    public SceneEditor(Editor editor, JTextArea textArea, JComboBox comboBox)
    {
        this.editor = editor;
        this.textArea = textArea;
        this.comboBox = comboBox;
        currentSceneID = -1;
        createCodes();
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
                addCode(staticCodes[0]);
                break;
            case "Change BG":
                addCode(staticCodes[1]);
                break;
            case "Change Music":
                addCode(staticCodes[2]);
                break;
            case "Add Sprite":
                addCode(getAddSpriteCode());
                break;
            case "Remove Sprite":
                addCode(getRemoveSpriteCode());
                break;
            case "Choose":
                addCode(staticCodes[3]);
                break;
            case "Jump":           
                addCode(staticCodes[4]);
                break;
        }            
        editor.projectManager.setCurrentSceneSavedState(false);
        editor.updateSceneSavedState();
        textArea.requestFocus();
    }
    
    private void createCodes()
    {
        staticCodes = new String[5];
        staticCodes[0] = "dial \"person\" \"text\"";
        staticCodes[1] = "changebg \"name.jpeg\"";
        staticCodes[2] = "playsong \"name.mp3\"\nstopsong";
        staticCodes[3] = "choice \"pergunta?\", aceita.scene(\"sim\"), nega.scene(\"nao\")";
        staticCodes[4] = "branch \"name.scene\"";
    }
    
    private String getAddSpriteCode()
    {
        String spritePlace = comboBox.getSelectedItem().toString();
        spritePlace = spritePlace.toLowerCase();
        return String.format("addsprite(" + spritePlace + ") \"name.jpeg\"");
    }
    
    private String getRemoveSpriteCode()
    {
        String spritePlace = comboBox.getSelectedItem().toString();
        spritePlace = spritePlace.toLowerCase();
        return String.format("removesprite(" + spritePlace + ")");
    }
    
    /**
     * Transfere as linhas de uma cena para a area de texto.
     * @param target 
     */
    public void updateTextArea(Scene target)
    {
        if (target == null)
            return;
        currentSceneID = target.id;
        String text = "";
        for (String line : target.getText())
            text += line;
        textArea.setText(text);
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
    private void addCode(String code)
    {
        int caret = textArea.getCaretPosition()-1;     
        if (caret == -1)
        {
            textArea.setText(code + '\n');            
        }
        else
        {
            String text = textArea.getText();
            String output = "";
            System.out.println("Text size: " + text.length() + ", " + caret);
            for (int i = 0; i < text.length(); i++)
            {
                output += text.charAt(i);
                if (i == caret)
                    output += code + '\n';
            }
            textArea.setText(output);
        }
        textArea.setCaretPosition(caret + code.length() + 2);
    }      
}
