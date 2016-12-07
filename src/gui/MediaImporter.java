package gui;

import forms.Editor;
import forms.auxiliars.FileSelector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;



/**
 * Carrega arquivos para o projeto.
 * @author Estevan
 */
public class MediaImporter implements ActionListener
{    

    public enum MediaType
    {
        BG,
        CG,
        Music,
        Sprite
    }   

    Editor editor;
    MediaType lastSelectedType;
    
    public MediaImporter(Editor editor)
    {
        this.editor = editor;
    }
    
    /**
     * Chamado quando um botao de importacao eh ativado.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        editor.setEnabled(false);
        FileSelector fs = new FileSelector(this);
        fs.setVisible(true);
        fs.setOnlyFiles();
        switch (e.getActionCommand())
        {
            case "BG":
                lastSelectedType = MediaType.BG;
                break;
            case "CG":
                lastSelectedType = MediaType.CG;
                break;
            case "Music":
                lastSelectedType = MediaType.Music;
                break;
            case "Sprite":
                lastSelectedType = MediaType.Sprite;
                break;            
        }  
    }
    
    /**
     * Callback da janela de selecao de arquivo.    
     * Envia o arquivo selecionado para o projeto.
     * @param target
     */
    public void onFileSelected(File target)
    {
        editor.projectManager.importFile(target, lastSelectedType);
        editor.setEnabled(true);
    }
    
    public void onFileNotSelected()
    {
        editor.setEnabled(true);
    }            
}
