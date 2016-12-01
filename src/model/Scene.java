package model;

import java.util.ArrayList;
import java.util.List;
   
public class Scene 
{
    int id;
    private List<String> lines;
    
    public Scene(int id)
    {
        this.id = id;
        lines = new ArrayList<>();
        lines.set(0, "oi");
    }
    
    public void save(List<String> lines)
    {
        this.lines = lines;
    }
}
