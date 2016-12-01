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
        lines.add("Empty Scene");        
    }
    
    public void save(List<String> lines)
    {
        this.lines = lines;
    }
}
