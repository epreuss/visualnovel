package model;

import java.util.ArrayList;
import java.util.List;
   
public class Scene
{
    public int id;
    public boolean saved;
    private List<String> lines;
    
    public Scene(int id)
    {
        saved = true;
        this.id = id;
        lines = new ArrayList<>();
        lines.add("Empty Scene ID: " + id);        
    }
    
    public void save(List<String> lines)
    {
        if (lines.size() > 0)
        {
            this.lines = lines;
            saved = true;
        }
    }
    
    public List<String> getText()
    {
        return lines;
    }
}
