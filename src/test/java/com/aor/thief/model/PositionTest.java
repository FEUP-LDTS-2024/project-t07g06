import com.aor.thief.model.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    void testGetLeft() {
        for (int x = -10; x < 10; x++) {
            for (int y = -10; y < 10; y++) {
                Position position = new Position(x, y);
                Position result = position.getLeft();
                assertEquals(x - 1, result.getX());
                assertEquals(y, result.getY());
            }
        }
    }

    @Test
    void testGetRight() {
        for (int x = -10; x < 10; x++) {
            for (int y = -10; y < 10; y++) {
                Position position = new Position(x, y);
                Position result = position.getRight();
                assertEquals(x + 1, result.getX());
                assertEquals(y, result.getY());
            }
        }
    }

    @Test
    void testGetUp() {
        for (int x = -10; x < 10; x++) {
            for (int y = -10; y < 10; y++) {
                Position position = new Position(x, y);
                Position result = position.getUp();
                assertEquals(x, result.getX());
                assertEquals(y - 1, result.getY());
            }
        }
    }

    @Test
    void testGetDown() {
        for (int x = -10; x < 10; x++) {
            for (int y = -10; y < 10; y++) {
                Position position = new Position(x, y);
                Position result = position.getDown();
                assertEquals(x, result.getX());
                assertEquals(y + 1, result.getY());
            }
        }
    }

    @Test
    void testMovingPatternDown() {
        for (int x = -10; x < 10; x++) {
            for (int y = -10; y < 10; y++) {
                Position position = new Position(x, y);
                Position result = position.movingpattern(0);
                assertEquals(x, result.getX());
                assertEquals(y + 1, result.getY());
            }
        }
    }

    @Test
    void testMovingPatternLeft() {
        for (int x = -10; x < 10; x++) {
            for (int y = -10; y < 10; y++) {
                Position position = new Position(x, y);
                Position result = position.movingpattern(1);
                assertEquals(x - 1, result.getX());
                assertEquals(y, result.getY());
            }
        }
    }

    @Test
    void testMovingPatternRight() {
        for (int x = -10; x < 10; x++) {
            for (int y = -10; y < 10; y++) {
                Position position = new Position(x, y);
                Position result = position.movingpattern(3);
                assertEquals(x + 1, result.getX());
                assertEquals(y, result.getY());
            }
        }
    }

    @Test
    void testMovingPatternUp() {
        for (int x = -10; x < 10; x++) {
            for (int y = -10; y < 10; y++) {
                Position position = new Position(x, y);
                Position result = position.movingpattern(2);
                assertEquals(x, result.getX());
                assertEquals(y - 1, result.getY());
            }
        }
    }

    @Test
    void testUpdateHitbox() {
        for (int x = -10; x < 10; x++) {
            for (int y = -10; y < 10; y++) {
                Position position = new Position(x, y);
                List<Position> hitbox = position.updatehitbox_();


                assertEquals(18, hitbox.size());


                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        assertTrue(hitbox.contains(new Position(x + i, y + j)));
                        assertTrue(hitbox.contains(new Position(x - i, y - j)));
                    }
                }
            }
        }
    }
}