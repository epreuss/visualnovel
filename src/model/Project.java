package model;

import gui.MediaImporter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Project 
{
    public String gameName;
    private String directory;
    public List<Scene> scenes;
    private Scene currentScene;
    
    /**
     * Contador de ID para a criação de cenas.
     */
    private int sceneId;
    
    /**
     * Construtor para criacao de projeto.
     * @param gameName 
     */
    public Project(String gameName)
    {
        sceneId = 0;
        this.gameName = gameName;
        directory = System.getProperty("user.dir") + "\\projetos\\" + gameName;
        scenes = new ArrayList();
        createEmptyScene();
        setCurrentScene(0);
        export();
    }
    
    /**
     * Construtor para carregamento de projeto.
     * @param target 
     */
    public Project(File target)
    {
        sceneId = 0;
        gameName = target.getName();
        directory = System.getProperty("user.dir") + "\\projetos\\" + gameName;
        scenes = new ArrayList();
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
    
    public int getSceneCount()
    {
        return scenes.size();
    }
    
    public void saveCurrentScene(List<String> lines)
    {
        if (currentScene != null)
            currentScene.save(lines);
    }
    
    public void setCurrentSceneSavedState(boolean state)
    {
        currentScene.setSaveState(state);        
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
            {
                scenes.remove(i);
                break;
            }
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
     * Cria todas as pastas do projeto.
     * Apaga todas as cenas, se a pasta do projeto já existe.
     */
    public boolean export()
    {
        String path = directory;
        File mainDir = new File(path);
        if (mainDir.exists())
            deleteAllScenes();
                
        mainDir.mkdir();
        path += "\\";
        new File(path + "backgrounds").mkdir();
        new File(path + "cgs").mkdir();
        new File(path + "musics").mkdir();
        new File(path + "sprites").mkdir();
        try 
        {
            new File(path + "save.save").createNewFile();            
            // Criacao das cenas.
            for (int i = 0; i < scenes.size(); i++)
            {
                File scene = new File(path + "scene" + scenes.get(i).id + ".scene");                
                scene.createNewFile();
                try
                {
                    // Preenche as cenas com seus dados.
                    FileWriter writer = new FileWriter(path + scene.getName());
                    //writer.append(scenes.get(i).getIdText());
                    for (String line : scenes.get(i).getLines())                                      
                        // Por algum motivo, o writer nao colocava '\n'. Funciona com o codigo do System.
                        // Revisado: O '\n' nao aparece no notepad, mas ele existe!!
                        writer.append(line);// + System.getProperty("line.separator"));
                    writer.close();
                } 
                catch (IOException e) 
                {
                    System.out.println("#2 Project export exception: " + e.getMessage());
                }                
            }
        }        
        catch(IOException e)
        {
            System.out.println("#3 Project export exception: " + e.getMessage());
        }        
        return true;
    }
    
    /**
     * Carrega o arquivos de cenas do projeto-alvo.
     * @param target 
     */
    public boolean load(File target)
    {
        File projectDir = new File(directory);
        List<String> sceneLines = new ArrayList();
        for (File file : projectDir.listFiles())
        {
            if (file.getName().contains(".scene"))
            {
                String sceneId = "";
                try
                {
                    FileReader reader = new FileReader(file);
                    String line = "";
                    int ch = reader.read();
                    int steps = 0;
                    boolean gotSceneId = false;
                    System.out.println("Lendo Arquivo: " + file.getName());
                    while (ch != -1)
                    {
                        System.out.print((char)ch);
                        if (ch == '<' && steps == 0)
                        {
                            // Recupera o ID da cena.
                            line += (char)ch;
                            ch = reader.read();
                            while (ch != '>' && ch != -1)
                            {
                                line += (char)ch;
                                sceneId += (char)ch;
                                ch = reader.read();
                            }
                            line += (char)ch;
                            ch = reader.read();
                            gotSceneId = true;
                            // Falha o carregamento se o id encontrando na cena eh diferente do que esta escrito no arquivo.
                            if (!file.getName().contains(sceneId) || sceneId == "")
                            {
                                System.out.println("Project load error: Cena nao contem seu id: " + sceneId);
                                return false;
                            }
                            if (ch == -1)
                                break;
                        }
                        else if (!gotSceneId)
                        {                            
                            // Se nao comecar com o caractere '<', eh pq a cena foi alterada.
                            System.out.println("Project load error: Cena nao comeca com caractere '<'");
                            return false;
                        }                        
                        line += (char)ch;
                        ch = reader.read();
                        if (ch == '\n')
                        {
                            line += (char)ch;
                            ch = reader.read();
                            sceneLines.add(line);
                            line = "";
                        }
                        steps++;
                    }                    
                    sceneLines.add(line); // Ultima linha da cena.
                    createEmptyScene();
                    scenes.get(scenes.size()-1).save(sceneLines);
                    scenes.get(scenes.size()-1).id = Integer.parseInt(sceneId);
                    sceneLines.clear();
                    System.out.println("Fim da leitura: " + file.getName());
                }
                catch (IOException e)
                {
                    System.out.println("Project load exception: " + e.getMessage());
                }
            }
        }     
        return true;
    }
    
    private void deleteAllScenes()
    {
        File projectDir = new File(directory);
        for (File file : projectDir.listFiles())
        {
            if (file.getName().contains(".scene"))
            {
                try 
                {
                    Files.delete(Paths.get(file.getAbsolutePath()));
                } catch (IOException ex) 
                {
                    Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
