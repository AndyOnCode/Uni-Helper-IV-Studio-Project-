package ec.edu.espe.ivstudio.unihelper.model;
/**
 *
 * @author Tomas Tapia, IV Studio, @ESPE
 */
import java.util.regex.Pattern;

public record RoomIdentifier(String block, String roomNumber) {
    private static final Pattern BLOCK_PATTERN = Pattern.compile("^[A-Ha-h]$");
    private static final Pattern ROOM_PATTERN = Pattern.compile("^[0-9]{1,3}[A-Za-z]?$");

    public RoomIdentifier {
        if (!BLOCK_PATTERN.matcher(block).matches()) {
            throw new IllegalArgumentException("Invalid Block code. Must be between A and H.");
        }
        if (!ROOM_PATTERN.matcher(roomNumber).matches()) {
            throw new IllegalArgumentException("Invalid room number format.");
        }
    }

    public String formatted() {
        return block.toUpperCase() + "-" + roomNumber;
    }
}

