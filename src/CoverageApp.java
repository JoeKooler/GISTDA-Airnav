import java.io.IOException;

public class CoverageApp {
    public static void main(String[] args) throws IOException {
        Grid grid = new Grid();
        grid.initMap();
        //grid.testGreen(400,400,12000);
        //grid.testGreenRedundantless(400,400,12000);
        //grid.placingTower(400,400,12000);
        grid.placingTowerwithRim(400,400,10000);
    }
}
