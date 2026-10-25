package ec.edu.espe.ivstudio.unihelper.model;

/**
 *
 * @author Danilo Simba, @ESPE
 */
public class StudySession {
    private String topic;
    private int durationMinutes;

    public StudySession(String topic, int durationMinutes) {
        this.topic = topic;
        this.durationMinutes = durationMinutes;
    }

    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * @return the durationMinutes
     */
    public int getDurationMinutes() {
        return durationMinutes;
    }

    /**
     * @param durationMinutes the durationMinutes to set
     */
    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String toString() {
        return "StudySession{" + "topic=" + topic + ", durationMinutes=" + durationMinutes + '}';
    }
    
}
