package ec.edu.espe.ivstudio.unihelper.model;

/**
 *
 * @author Danilo Simba, @ESPE
 */
public class GradeItem {
    private String name;
    private double score;
    private double weight;

    public GradeItem(String name, double score, double weight) {
        this.name = name;
        this.score = score;
        this.weight = weight;
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
     * @return the score
     */
    public double getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "GradeItem{" + "name=" + name + ", score=" + score + ", weight=" + weight + '}';
    }
    
    
    
}
