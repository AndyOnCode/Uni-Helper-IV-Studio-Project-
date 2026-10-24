package ec.edu.espe.ivstudio.unihelper.model;

/**
 *
 * @author Andy Tiban, IV Studio, @ESPE
 */
public class Subject {
    private String id;
    private String name;
    private int creditHours;

    
    public Subject(String id, String name, int creditHours) {
        this.id = id;
        this.name = name;
        this.creditHours = creditHours;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the creditHours
     */
    public int getCreditHours() {
        return creditHours;
    }

    /**
     * @param creditHours the creditHours to set
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", name=" + name + ", creditHours=" + creditHours + '}';
    }
  
    
}
