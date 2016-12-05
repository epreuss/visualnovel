package gui;

import forms.Editor;
import java.io.File;
import java.util.List;
import javax.swing.JMenuItem;
import model.Project;
import model.Scene;

public class ProjectManager 
{
    Editor editor;
    public Project project;
    private JMenuItem export;
    private JMenuItem save;
    
    public ProjectManager(Editor editor, JMenuItem export, JMenuItem save)
    {
        this.editor = editor;        
        this.export = export;
        this.save = save;
    }
    
    /**
     * Callback para quando a interface pede para criar um projeto.
     */
    public void onProjectCreate(String name)
    {
        project = new Project(name);    
        editor.onProjectStart(project);
        export.setEnabled(true);
        save.setEnabled(true);
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
     * Callback para quando a interface pede para exportar um projeto.
     */
    public void onProjectExport()
    {
        project.export();
    }
    
    /**
     * Deleta uma cena do projeto.
     * @param index 
     * @return  
     */
    public void deleteCurrentScene()
    {
        project.deleteCurrentScene();
    }    
    
    public void setCurrentScene(int index)
    {
        project.setCurrentScene(index);
    }

    public Scene getCurrentScene()
    {
        return project.getCurrentScene();
    }
    
    public void saveCurrentScene(List<String> lines)
    {
        project.saveCurrentScene(lines);
    }
    
    public Scene getLastCreatedScene()
    {
        return project.getLastCreatedScene();
    }
    
    public int getTotalScenes()
    {
        return project.getTotalScenes();
    }
           
    public void setCurrentSceneSavedState(boolean state)
    {
        project.setCurrentSceneSavedState(state);
    }
    
    public void addScene()
    {
        project.createEmptyScene();
    }
}
