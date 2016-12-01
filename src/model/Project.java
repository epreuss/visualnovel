package model;

public class Project 
{
    String gameName;
    private String directory;
    private Scene[] scenes;
    private Scene currentScene;
    private int sceneIndex;
    
    public Project(String gameName)
    {
        this.gameName = gameName;
        sceneIndex = 0;
        createEmptyScene();
        updateCurrentScene();
    }
    
    public Scene getCurrentScene()
    {
        return currentScene;
    }
    
    public void createEmptyScene()
    {
        scenes[sceneIndex] = new Scene(sceneIndex);        
        sceneIndex++;        
    }
    
    public void updateCurrentScene()
    {
        if (scenes.length > 0)
            currentScene = scenes[sceneIndex-1];
        else
            currentScene = null;
    }
    
    void removeScene(int sceneID)
    {
        
    }
}
