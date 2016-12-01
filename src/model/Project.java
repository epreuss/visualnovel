package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Project 
{
    String gameName;
    private String directory;
    public List<Scene> scenes;
    private Scene currentScene;
    private int sceneId;
    
    public Project(String gameName)
    {
        sceneId = 0;
        this.gameName = gameName;
        scenes = new ArrayList();
        createEmptyScene();
        updateCurrentScene();
    }
    
    public Scene getCurrentScene()
    {
        return currentScene;
    }
    
    public void createEmptyScene()
    {
        scenes.add(new Scene(sceneId));
        sceneId++;
    }
    
    public void updateCurrentScene()
    {
        if (scenes.size() > 0)
            currentScene = scenes.get(scenes.size()-1);
        else
            currentScene = null;
    }
    
    public boolean deleteScene(int sceneID)
    {
        if (scenes.size() > 0)
        {   
            scenes.remove(sceneID);
            updateCurrentScene();
            return true;
        }
        else
            return false;
    }
    
    /**
     * Cria as pastas do projeto.
     * Sobrescreve a pasta de nome 'gameName' se ela já existe.
     */
    public void export()
    {
        String path = System.getProperty("user.dir") + "\\projetos\\";
        new File(path + gameName).mkdir();
        path += gameName + "\\";
        new File(path + "backgrounds").mkdir();
        new File(path + "cgs").mkdir();
        new File(path + "musics").mkdir();
        new File(path + "sprites").mkdir();
        try 
        {
            new File(path + "save.save").createNewFile();
            new File(path + "data.project").createNewFile();
            for (int i = 0; i < scenes.size(); i++)
                new File(path + "scene" + scenes.get(i).id + ".scene").createNewFile();                
        }        
        catch(Exception e)
        {
            System.out.println("Project exception: " + e.getMessage());
        }        
    }
}
