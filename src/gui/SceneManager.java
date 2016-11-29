package gui;

import java.util.Vector;
import javax.swing.JList;

/**
 *
 * @author Estevan
 */
public class SceneManager 
{
    JList scenesList;
    
    public SceneManager()
    {
        
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
    
    public void setList(JList list)
    {
        scenesList = list;
        Vector scenes = new Vector();
        scenes.add(" ");
        scenesList.setListData(scenes);
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
