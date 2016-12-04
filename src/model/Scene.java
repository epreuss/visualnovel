package model;

import java.util.ArrayList;
import java.util.List;
   
public class Scene
{
    public int id;
    private List<String> lines;
    
    public Scene(int id)
    {
        this.id = id;
        lines = new ArrayList<>();
        lines.add("Empty Scene ID: " + id);        
    }
    
    public void save(List<String> lines)
    {
        if (lines.size() > 0)
            this.lines = lines;
    }
    
    public List<String> getText()
    {
        return lines;
    }
}
