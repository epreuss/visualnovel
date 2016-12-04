package gui;

import forms.Editor;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JList;
import model.Project;
import model.Scene;

/**
 *
 * @author Estevan
 */
public class SceneSelector 
{
    Editor editor;
    private JList scenesList;
    private JButton save;
    private JButton delete;
    private Vector scenes;
    
    private boolean addedScene;
    private boolean deletedScene;
    
    public SceneSelector(Editor e, JList l, JButton s, JButton d)
    {
        editor = e;
        save = s;
        delete = d;
        
        scenes = new Vector();
        scenes.add(" ");
        
        scenesList = l;        
        scenesList.setEnabled(false);
        scenesList.setListData(scenes);
        
        s.setEnabled(false);
        d.setEnabled(false);        
    }
    
    /**
     * Callback para o botao de nova cena.
    */
    public void onButtonNewScene()
    {
        addedScene = true;
        deletedScene = false;
        editor.projectManager.addScene();
        updateList(editor.projectManager.project);      
    }
    
    /**
     * Callback para o botao de deletar a cena.
    */
    public void onButtonDeleteScene()
    {
        addedScene = false;
        deletedScene = true;
        if (editor.projectManager.deleteCurrentScene())
        {
            updateList(editor.projectManager.project);   
            editor.projectManager.setCurrentScene(scenesList.getSelectedIndex());        
        }
    }
    
    public void onChangeScene()
    {
        /*
        Caso 1: Adicionar cena quando nao existem cenas.
        Caso 2: Adicionar cena quando existem cenas.
        Caso 3: Deletar cena e sobrar nenhuma.
        Caso 4: Deletar cena e sobrar mais de uma.
        Caso 5: Seleciona cena.
        */
        editor.projectManager.setCurrentScene(scenesList.getSelectedIndex());
        
        if (true) return;
        updateList(editor.projectManager.project);      
        
        // Caso 1.
        if (addedScene && scenesList.isSelectionEmpty())
        {
            editor.sceneEditor.setTextAreaEnabled(true);
            editor.projectManager.setCurrentScene(0);
            editor.sceneEditor.updateTextArea(editor.projectManager.getCurrentScene()); 
        }
        // Caso 2.
        else if (addedScene && !scenesList.isSelectionEmpty())
        {
            editor.projectManager.setCurrentScene(scenesList.getSelectedIndex());
        }
        // Caso 3.
        else if (deletedScene && scenesList.isSelectionEmpty())
        {
            
            editor.sceneEditor.setTextAreaEnabled(false);
            editor.projectManager.setCurrentScene(scenesList.getSelectedIndex());
            editor.sceneEditor.updateTextArea(editor.projectManager.getCurrentScene()); 
        }
        // Caso 4.
        else if (deletedScene && !scenesList.isSelectionEmpty())
        {
            editor.projectManager.saveCurrentScene(editor.sceneEditor.getSceneText());
            editor.projectManager.setCurrentScene(scenesList.getSelectedIndex());
            editor.sceneEditor.updateTextArea(editor.projectManager.getCurrentScene()); 
        }
        // Caso 5.
        else
        {
            editor.projectManager.setCurrentScene(scenesList.getSelectedIndex());
        }
        
        
        /*
        if (addedScene && !scenesList.isSelectionEmpty())
        {
            addedScene = false;
            return;
        }
        */
        
        
        /*
        Vale -1 quando ele adiciona na lista.
        Assim, nao eh preciso mudar a cena aberta atual do projeto.
        */
        if (scenesList.getSelectedIndex() != -1)
        {
            editor.projectManager.saveCurrentScene(editor.sceneEditor.getSceneText());
            editor.projectManager.setCurrentScene(scenesList.getSelectedIndex());
            editor.sceneEditor.updateTextArea(editor.projectManager.getCurrentScene()); 
        }    
    }        
    
    /**
     * Atualiza a lista de cenas com as cenas do projeto.
     * Seleciona uma cena nova quando existe remocao.
     * @param p 
     */
    public void updateList(Project p)
    {
        int savedSelection = scenesList.getSelectedIndex();
        if (savedSelection < 0)
            savedSelection = 0;
        if (savedSelection >= p.scenes.size())
            savedSelection = p.scenes.size()-1;
        
        scenes.clear();
        for (Scene s : p.scenes)        
            addSceneToList(s);
        
        scenesList.setListData(scenes);        

        if (scenes.size() > 0)
        {
            delete.setEnabled(true);
            scenesList.setEnabled(true);
            scenesList.setSelectedIndex(savedSelection);        
        }
        else
        {
            delete.setEnabled(false);
            scenesList.setEnabled(false);
        }
    }
    
    public void addSceneToList(Scene s)
    {
        scenes.add("Cena " + s.id);      
    }        
}
