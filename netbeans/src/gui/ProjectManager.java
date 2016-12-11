package gui;

import forms.Editor;
import gui.MediaImporter.MediaType;
import java.io.File;
import java.util.List;
import javax.swing.JMenuItem;
import model.Project;
import model.Scene;


/**
 * Administra a criacao, carregamento e exportcao do projeto.
 * Tambem eh uma interface de metodos para acesso ao projeto.
 * @author Estevan
 */
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
     * @param name Nome do projeto.
     */
    public void onProjectCreate(String name)
    {
        if (canCreateProject(name))
        {
            project = new Project(name);    
            editor.onProjectStart(project);
            export.setEnabled(true);
            save.setEnabled(true);
        }
        else
            editor.showWindowMessage("Já existe um projeto com o mesmo nome.");
    }
    
    /**
     * Callback para quando a interface carrega um projeto.
     * @param target Arquivo selecionado.
     */
    public void onProjectLoad(File target)
    {
        if (validateFileAsDirectory(target))
        {
            Project toLoad = new Project(target);
            if (!toLoad.load(target))
                editor.showWindowMessage("Cenas do projeto corrompidas. Carregamento falhou.");
            else
            {
                project = toLoad;
                editor.onProjectStart(project);
                export.setEnabled(true);
                save.setEnabled(true);
            }
        }
    }
    
    /**
     * Valida um arquivo para ver se é um diretorio.
     */
    private boolean validateFileAsDirectory(File target)
    {
        return target.isDirectory();
    }
    
    /**
     * Callback para quando a interface pede para exportar um projeto.
     */
    public void onProjectExport()
    {
        project.export();
    }
    
    /**
     * Percorre a lista de projetos e verifica se possui um do mesmo nome.
     * @param name Nome do projeto.
     * @return 
     */
    private boolean canCreateProject(String name)
    {
        String projectsPath = System.getProperty("user.dir") + "\\projetos";
        File projectsDir = new File(projectsPath);
        
        for (File file : projectsDir.listFiles())
            if (file.getName().equals(name))
                return false;
        return true;
    }
    
    /**
     * Importa uma midia.
     * @param target Arquivo selecionado.
     * @param type Tipo de midia.
     */
    public void importFile(File target, MediaType type)
    {
        if (!project.importFile(target, type))
            editor.showWindowMessage("Já existe um arquivo com o mesmo nome.");
    }

    /**
     * Deleta a cena atual do projeto.
     */
    public void deleteCurrentScene()
    {
        project.deleteCurrentScene();
        if (project.getSceneCount() == 0)
            save.setEnabled(false);
    }    
    
    /**
     * Muda a cena atual.
     * @param index Indice da cena
     */
    public void setCurrentScene(int index)
    {
        project.setCurrentScene(index);
    }

    /**
     * Retorna a cena atual.
     * @return Cena do projeto.
     */
    public Scene getCurrentScene()
    {      
        return project.getCurrentScene();
    }
    
    /**
     * Salva a cena atual.
     * @param lines Novas linhas para a cena.
     */
    public void saveCurrentScene(List<String> lines)
    {
        project.saveCurrentScene(lines);
    }
    
    /**
     * Retorna a ultima cena criada.
     * @return Cena do projeto.
     */
    public Scene getLastCreatedScene()
    {
        return project.getLastCreatedScene();
    }
    
    /**
     * Retorna total de cenas.
     * @return Quantidade.
     */
    public int getTotalScenes()
    {
        return project.getTotalScenes();
    }
           
    /**
     * Atualiza estado da cena.
     * @param state Salva ou nao.
     */
    public void setCurrentSceneSavedState(boolean state)
    {
        project.setCurrentSceneSavedState(state);
    }
    
    /**
     * Adiciona uma cena.
     */
    public void addScene()
    {
        project.createEmptyScene();
        save.setEnabled(true);
    }
}
