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
public class SceneManager 
{
    Editor editor;
    private JList scenesList;
    private JButton save;
    private JButton delete;
    private Vector scenes;
    
    public SceneManager(Editor e, JList l, JButton s, JButton d)
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
     * Callback para o botão de nova cena.
    */
    public void onButtonNewScene()
    {
        
    }
    
    /**
     * Callback para o botão de salvar cena.
    */
    public void onButtonSaveScene()
    {
        
    }
    
    public void onProjectSceneUpdate(Project p)
    {
        updateList(p);
    }
    
    public void updateList(Project p)
    {
        int savedSelection = scenesList.getSelectedIndex();
        if (savedSelection < 0)
            savedSelection = 0;
        if (savedSelection >= p.scenes.size())
            savedSelection = p.scenes.size()-1;
        
        scenes.clear();
        for (Scene s : p.scenes)             
            scenes.add("Cena " + s.id);        
        
        //if (scenes.isEmpty())
//            scenes.add(" ");
        
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
            scenesList.setSelectedIndex(0);        
        }
    }
    
    public void addSceneToList()
    {
        
    }
    
    public void removeSceneFromList()
    {
        
    }
    
    public void changeCurrentScene()
    {
        
    }
}
