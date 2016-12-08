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
        //scenes.add(" ");
        
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
        //addSceneToList(editor.projectManager.getLastCreatedScene());
        updateList(editor.projectManager.project);      
    }
    
    /**
     * Callback para o botao de deletar a cena.
    */
    public void onButtonDeleteScene()
    {
        addedScene = false;
        deletedScene = true;
        if (editor.projectManager.getCurrentScene() != null)
        {
            editor.projectManager.deleteCurrentScene();
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
        
        int totalListScenes = scenesList.getModel().getSize();
        if (totalListScenes != editor.projectManager.getTotalScenes())
            return;
        
        System.out.println("----------------- Cenas: " + scenesList.getModel().getSize());
        
        
        // Caso 1.
        if (addedScene && totalListScenes == 1)
        {
            System.out.println("Caso 1: Adicionar cena quando nao existem cenas.");
            editor.sceneEditor.setTextAreaEnabled(true);
            editor.projectManager.setCurrentScene(0);
            editor.sceneEditor.updateTextArea(editor.projectManager.getCurrentScene()); 
            addedScene = false;
        }
        // Caso 2.
        else if (addedScene && totalListScenes > 1)
        {
            System.out.println("Caso 2: Adicionar cena quando existem cenas.");
            addedScene = false;
        }
        // Caso 3.
        else if (deletedScene && totalListScenes == 0)
        {
            System.out.println("Caso 3: Deletar cena e sobrar nenhuma.");
            editor.sceneEditor.setTextAreaEnabled(false);
            editor.projectManager.setCurrentScene(scenesList.getSelectedIndex());
            editor.sceneEditor.updateTextArea(editor.projectManager.getCurrentScene()); 
            deletedScene = false;
        }
        // Caso 4.
        else if (deletedScene && totalListScenes > 0)
        {
            System.out.println("Caso 4: Deletar cena e sobrar mais de uma.");
            editor.projectManager.saveCurrentScene(editor.sceneEditor.getSceneText());
            editor.projectManager.setCurrentScene(scenesList.getSelectedIndex());
            editor.sceneEditor.updateTextArea(editor.projectManager.getCurrentScene()); 
            deletedScene = false;
        }
        // Caso 5.
        else
        {
            System.out.println("Caso 5: Seleciona cena.");
            editor.projectManager.saveCurrentScene(editor.sceneEditor.getSceneText());            
            editor.projectManager.setCurrentScene(scenesList.getSelectedIndex());
            editor.sceneEditor.updateTextArea(editor.projectManager.getCurrentScene()); 
            addedScene = false;
            deletedScene = false;
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
    
    public void addSceneToList(Scene target)
    {
        scenes.add("Cena " + target.id); 
        scenesList.setListData(scenes);   
    }    

    public void deleteSceneFromList(Scene target)
    {
        for (int i = 0; i < scenes.size(); i++)      
        {
            if (scenes.get(i).equals(target))
            {
                scenes.remove(i);
                break;
            }
        }
        for (int i = 0; i < scenes.size(); i++)  
            System.out.println(scenes.get(i));
        scenesList.setListData(scenes);   
        
        int savedSelection = scenesList.getSelectedIndex();
        if (savedSelection < 0)
            savedSelection = 0;
        if (savedSelection >= scenes.size())
            savedSelection = scenes.size() - 1;
                
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
}
