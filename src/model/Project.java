package model;

import gui.MediaImporter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;
import java.util.ArrayList;
import java.util.List;

public class Project 
{
    String gameName;
    private String directory;
    public List<Scene> scenes;
    private Scene currentScene;
    
    /**
     * Contador de ID para a criação de cenas.
     */
    private int sceneId;
    
    public Project(String gameName)
    {
        sceneId = 0;
        this.gameName = gameName;
        directory = System.getProperty("user.dir") + "\\projetos\\" + gameName;
        scenes = new ArrayList();
        createEmptyScene();
        setCurrentScene(0);
    }
    
    public Scene getCurrentScene()
    {
        return currentScene;
    }
    
    public int getTotalScenes()
    {
        return scenes.size();
    }
    
    public Scene getLastCreatedScene()
    {
        return scenes.get(scenes.size()-1);
    }
    
    public void createEmptyScene()
    {
        scenes.add(new Scene(sceneId));
        sceneId++;
    }
    
    public void saveCurrentScene(List<String> lines)
    {
        if (currentScene != null)
            currentScene.save(lines);
    }
    
    public void setCurrentSceneSavedState(boolean state)
    {
        currentScene.saved = state;
    }
    
    public void setCurrentScene(int index)
    {
        if (index >= 0)
        {
            currentScene = scenes.get(index);
        }
        else
        {
            currentScene = null;
        }
    }
    
    public void deleteCurrentScene()
    {
        for (int i = 0; i < scenes.size(); i++)
            if (scenes.get(i).id == currentScene.id)
                scenes.remove(i);
    }
    
    public boolean importFile(File target, MediaImporter.MediaType type)
    {
        String targetDir = "";
        switch (type)
        {
            case BG:
                targetDir = "backgrounds";
                break;
            case CG:
                targetDir = "cgs";
                break;
            case Music:
                targetDir = "musics";
                break;
            case Sprite:
                targetDir = "sprites";
                break;
        }
        
        Path dirPath = Paths.get(directory + "\\" + targetDir + "\\" + target.getName());
        Path targetPath = Paths.get(target.getAbsolutePath());
        try 
        {
            Files.copy(targetPath, dirPath, COPY_ATTRIBUTES);
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Project import exception: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Cria as pastas do projeto.
     * Sobrescreve a pasta de nome 'gameName' se ela já existe.
     */
    public boolean export()
    {
        String path = directory;
        File mainDir = new File(path);
        
        /*
        if (mainDir.exists())
        {
            System.out.println("Project already exists.");
            return false;
        }
        */
                
        mainDir.mkdir();
        path += "\\";
        new File(path + "backgrounds").mkdir();
        new File(path + "cgs").mkdir();
        new File(path + "musics").mkdir();
        new File(path + "sprites").mkdir();
        try 
        {
            new File(path + "save.save").createNewFile();
            new File(path + "data.project").createNewFile();
            for (int i = 0; i < scenes.size(); i++)
            {
                File scene = new File(path + "scene" + scenes.get(i).id + ".scene");                
                scene.createNewFile();
                try
                {
                    FileWriter writer = new FileWriter(path + scene.getName());
                    for (String line : scenes.get(i).getText())
                        writer.append(line);
                    writer.close();
                } 
                catch (Exception e) 
                {
                    System.out.println("Project export exception: " + e.getMessage());
                }                
            }
        }        
        catch(Exception e)
        {
            System.out.println("Project export exception: " + e.getMessage());
        }        
        return true;
    }
}
