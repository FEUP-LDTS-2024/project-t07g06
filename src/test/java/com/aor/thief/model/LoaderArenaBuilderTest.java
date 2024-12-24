import com.aor.thief.model.Position;
import com.aor.thief.model.game.arena.LoaderArenaBuilder;
import com.aor.thief.model.game.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoaderArenaBuilderTest {

    private LoaderArenaBuilder loaderArenaBuilder;

    @BeforeEach
    void setUp() throws IOException {
        loaderArenaBuilder = new LoaderArenaBuilder(1);
    }

    @Test
    void testGetWidth() {
        int width = loaderArenaBuilder.getWidth();
        assertEquals(155, width);
    }

    @Test
    void testGetHeight() {
        int height = loaderArenaBuilder.getHeight();
        assertEquals(94, height);
    }

    @Test
    void testCreateWalls() {
        List<Wall> walls = loaderArenaBuilder.createWalls();
        assertEquals(174, walls.size());
        assertEquals(new Position(0, 0), walls.get(0).getPosition());
    }

    @Test
    void testCreateGuards() {
        List<Guard> guards = loaderArenaBuilder.createGuard();
        assertEquals(2, guards.size());
    }

    @Test
    void testCreateThief() {
        Thief thief = loaderArenaBuilder.createThief();
        assertNotNull(thief);
    }

    @Test
    void testCreatePrize() {
        List<Prize> prizes = loaderArenaBuilder.createPrize();
        assertEquals(1, prizes.size());
    }

    @Test
    void testCreateCamera() {
        List<Camera> cameras = loaderArenaBuilder.createCamera();
        assertEquals(2, cameras.size());
    }

    @Test
    void testCreateStillCamera() {
        List<CameraStill> stillCameras = loaderArenaBuilder.createStillCameras();
        assertEquals(1, stillCameras.size());
    }

    @Test
    void testCreateCop() {

        List<Cop> cops = loaderArenaBuilder.createCop();
        assertEquals(2, cops.size());
    }

    @Test
    void testCreateDoor() {
        Door door = loaderArenaBuilder.createDoor();
        assertNotNull(door);
    }
}
