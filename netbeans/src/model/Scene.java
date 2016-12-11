package model;

import java.util.ArrayList;
import java.util.List;
   
/**
 * Guarda uma string de linhas para serem interpretadas
 * pelo parser, que vai dar vida para as linhas.
 * @author Estevan
 */
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
    
    /**
     * Salva a cena com novas linhas.
     * @param target Linhas de string.
     */
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
    
    /**
     * Retorna texto de id da cena.
     * @return String do id.
     */
    public String getIdText()
    {
        return String.format("<" + id + ">");
    }
    
    /**
     * Retorna se esta salva.
     * @return Salva ou nao.
     */
    public boolean isSaved()
    {
        return saved;
    }
    
    /**
     * Modifica o estado da cena.
     * @param state Salvo ou nao.
     */
    public void setSaveState(boolean state)
    {
        saved = state;
    }
    
    /**
     * Retorna as linhas da cena.
     * @return Lista de strings.
     */
    public List<String> getLines()
    {
        return lines;
    }
}
