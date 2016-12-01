package model;

import forms.Editor;
import java.io.File;

public class ProjectManager 
{
    Editor editor;
    Project currentProject;
    
    public ProjectManager(Editor editor)
    {
        this.editor = editor;
    }
    
    /**
     * Callback para quando a interface pede para criar um projeto.
     */
    public void onProjectCreate(String name)
    {
        currentProject = new Project(name);    
        editor.onProjectStart(currentProject);
    }
    
    /**
     * Callback para quando a interface carrega um projeto.
     */
    public void onProjectLoad(File target)
    {
        String name = validateFile(target);
        if (name != null)
            onProjectCreate(name);
    }
    
    /**
     * Valida um arquivo, para ver se é um projeto de visual novel.
     */
    private String validateFile(File target)
    {
        if (target.isDirectory())
        {
            return target.getName();
            /*
            File[] childrenFiles = target.listFiles();
            for (File f : childrenFiles)
                if (f.getName() == "data.project")
                    return f;
            */
        }
        return null;
    }
    
    /**
     * Callback para quando a interface exporta um projeto.
     */
    public void onProjectExport()
    {
        
    }
    

    
    public void createProject()
    {
        
    }
    
    public void loadProject(String directory)
    {
        
    }
    
    public void exportCurrentProject()
    {
        
    }
        
        
}
