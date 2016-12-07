package model;

import java.util.ArrayList;
import java.util.List;
   
public class Scene
{
    public int id;
    private boolean saved;
    private List<String> lines;
    
    public Scene(int id)
    {
        saved = true;
        this.id = id;
        lines = new ArrayList<>();
        lines.add(getIdText());
    }
    
    public void save(List<String> target)
    {
        if (target.size() == 0)
            return;
        
        // Copia as linhas, ao inves de simplesmente pegar a referencia.        
        lines.clear();
        for (String line : target)
            lines.add(line);
        saved = true;
    }
    
    public String getIdText()
    {
        return String.format("<" + id + ">");
    }
    
    public boolean isSaved()
    {
        return saved;
    }
    
    public void setSaveState(boolean state)
    {
        saved = state;
    }
    
    public List<String> getLines()
    {
        return lines;
    }
}
