package ec.edu.espe.ivstudio.unihelper.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class RoomIdentifierTest {

    @Test
    @DisplayName("[Low] Accepts a valid block and room number")
    void acceptsValidBlockAndRoomNumber() {
        RoomIdentifier room = new RoomIdentifier("A", "101");

        assertEquals("A", room.block());
        assertEquals("101", room.roomNumber());
    }

    @Test
    @DisplayName("[Low] Formats block in uppercase and keeps room suffix")
    void formatsBlockInUppercaseAndKeepsRoomSuffix() {
        RoomIdentifier room = new RoomIdentifier("c", "12B");

        assertEquals("C-12B", room.formatted());
    }

    @Test
    @DisplayName("[Low] Accepts blocks from A through H regardless of case")
    void acceptsBlocksFromAThroughHRegardlessOfCase() {
        assertDoesNotThrow(() -> new RoomIdentifier("a", "1"));
        assertDoesNotThrow(() -> new RoomIdentifier("H", "999"));
    }

    @Test
    @DisplayName("[Medium] Rejects blocks outside the allowed range")
    void rejectsBlockOutsideAllowedRange() {
        assertThrows(IllegalArgumentException.class, () -> new RoomIdentifier("I", "101"));
        assertThrows(IllegalArgumentException.class, () -> new RoomIdentifier("AA", "101"));
    }

    @Test
    @DisplayName("[Medium] Accepts one to three digits with an optional letter suffix")
    void acceptsOneToThreeDigitsWithOptionalLetterSuffix() {
        assertDoesNotThrow(() -> new RoomIdentifier("A", "1"));
        assertDoesNotThrow(() -> new RoomIdentifier("A", "99"));
        assertDoesNotThrow(() -> new RoomIdentifier("A", "123"));
        assertDoesNotThrow(() -> new RoomIdentifier("A", "123a"));
    }

    @Test
    @DisplayName("[Medium] Rejects invalid room number formats")
    void rejectsInvalidRoomNumberFormats() {
        assertThrows(IllegalArgumentException.class, () -> new RoomIdentifier("A", ""));
        assertThrows(IllegalArgumentException.class, () -> new RoomIdentifier("A", "1234"));
        assertThrows(IllegalArgumentException.class, () -> new RoomIdentifier("A", "12AB"));
        assertThrows(IllegalArgumentException.class, () -> new RoomIdentifier("A", "A12"));
    }

    @Test
    @DisplayName("[Low] Formats a room without a suffix")
    void formatsRoomWithoutSuffix() {
        assertEquals("H-999", new RoomIdentifier("H", "999").formatted());
    }

    @Test
    @DisplayName("[Low] Preserves uppercase room suffix")
    void preservesUppercaseRoomSuffix() {
        assertEquals("B-12A", new RoomIdentifier("B", "12A").formatted());
    }

    @Test
    @DisplayName("[Medium] Accepts room numbers with leading zeroes")
    void acceptsRoomNumberWithLeadingZeroes() {
        assertEquals("A-007", new RoomIdentifier("A", "007").formatted());
    }

    @Test
    @DisplayName("[Medium] Record equality compares block and room number")
    void recordEqualityComparesComponents() {
        assertEquals(new RoomIdentifier("C", "12B"), new RoomIdentifier("C", "12B"));
        assertNotEquals(new RoomIdentifier("C", "12B"), new RoomIdentifier("C", "12A"));
    }

    @Test
    @DisplayName("[Hard] Exposes crash when block is null")
    void throwsWhenBlockIsNull() {
        assertThrows(NullPointerException.class, () -> new RoomIdentifier(null, "101"));
    }

    @Test
    @DisplayName("[Hard] Exposes crash when room number is null")
    void throwsWhenRoomNumberIsNull() {
        assertThrows(NullPointerException.class, () -> new RoomIdentifier("A", null));
    }
}

