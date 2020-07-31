import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;
@SuppressWarnings("Duplicates")


public class Grid {

    int BLOCKRANGE = 300;
    int[][] DEMGrid = new int[800][800];


    public void initMap() throws IOException{

        String fileName = "DEM/DEM27.asc"; //this path is on my local


        int positionCounter = 0;

        ArrayList<Object> DEMData = new ArrayList<>();
        ArrayList<Integer> DEMIntArrayList = new ArrayList<>();


        String[] DEMString;

        // lines(Path path, Charset cs)
        try (Stream inputStream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            inputStream.forEach(x-> DEMData.add(x));
            //Printing Details
            System.out.println(DEMData.size());
            System.out.println(DEMData.get(0) + "\n" + DEMData.get(1) + "\n" + DEMData.get(2) + "\n" + DEMData.get(3) + "\n" + DEMData.get(4) + "\n" + DEMData.get(5));
        }

        DEMString = DEMData.get(6).toString().split(" ");
        for(int i = 0 ; i < DEMString.length ; i++){
            DEMIntArrayList.add(Integer.parseInt(DEMString[i]));
        }

        for(int row = 0 ; row < 800 ; row++){
            for(int column = 0; column< 800 ; column++){
                DEMGrid[row][column] = DEMIntArrayList.get(positionCounter);
                positionCounter++;
            }
        }
        System.out.println(DEMGrid[0][4]);
    }

    public void testGreen(int placedRow, int placedColumn, int signalRange) throws IOException {
        int maxBlock = signalRange / BLOCKRANGE;
        ArrayList<String> greenBlock = new ArrayList<>();

        for (int currentCircleArea = 1; currentCircleArea <= maxBlock; currentCircleArea++) {
            int d = (5 - currentCircleArea * 4) / 4;
            int positionCounter = 0;
            int radius = currentCircleArea;


            do {
                double toLongLat = (double) 300/111111;

                double placedRowPlusCounter = (placedRow + positionCounter)*toLongLat;
                double placedRowMinusCounter = (placedRow - positionCounter)*toLongLat;
                double placedRowPlusRadius = (placedRow + radius)*toLongLat;
                double placedRowMinusRadius = (placedRow - radius)*toLongLat;

                double placedColumnPlusCounter = (placedColumn + positionCounter)*toLongLat;
                double placedColumnMinusCounter = (placedColumn - positionCounter)*toLongLat;
                double placedColumnPlusRadius = (placedColumn + radius)*toLongLat;
                double placedColumnMinusRadius = (placedColumn - radius)*toLongLat;

                greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius));
                greenBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius));
                greenBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius));
                greenBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter));
                greenBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter));
                greenBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter));
                greenBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter));

                if (d < 0) {
                    d += 2 * positionCounter + 1;
                } else {
                    d += 2 * (positionCounter - radius) + 1;
                    radius--;
                }
                positionCounter++;
            } while (positionCounter <= radius);
        }
        System.out.println("Initiating Green Coverage Test.");
        System.out.println("Green box size:" + greenBlock.size());
        System.out.println("Greed box members:" + greenBlock);
        System.out.println("First Height: " + 300 * Math.tan(Math.toRadians(30)));
    }

    public void testGreenRedundantless(int placedRow, int placedColumn, int signalRange) throws IOException {
        int maxBlock = signalRange / BLOCKRANGE;
        ArrayList<String> greenBlock = new ArrayList<>();

        for (int currentCircleArea = 1; currentCircleArea <= maxBlock; currentCircleArea++) {
            int d = (5 - currentCircleArea * 4) / 4;
            int positionCounter = 0;
            int radius = currentCircleArea;


            do {
                double toLongLat = (double) 300/111111;

                double placedRowPlusCounter = (placedRow + positionCounter)*toLongLat;
                double placedRowMinusCounter = (placedRow - positionCounter)*toLongLat;
                double placedRowPlusRadius = (placedRow + radius)*toLongLat;
                double placedRowMinusRadius = (placedRow - radius)*toLongLat;

                double placedColumnPlusCounter = (placedColumn + positionCounter)*toLongLat;
                double placedColumnMinusCounter = (placedColumn - positionCounter)*toLongLat;
                double placedColumnPlusRadius = (placedColumn + radius)*toLongLat;
                double placedColumnMinusRadius = (placedColumn - radius)*toLongLat;

                if (!greenBlock.contains("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius)))
                    greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));

                if (!greenBlock.contains("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius)))
                    greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius));

                if (!greenBlock.contains("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius)))
                    greenBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius));

                if (!greenBlock.contains("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius)))
                    greenBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius));

                if (!greenBlock.contains("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter)))
                    greenBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter));

                if (!greenBlock.contains("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter)))
                    greenBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter));

                if (!greenBlock.contains("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter)))
                    greenBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter));

                if (!greenBlock.contains("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter)))
                    greenBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter));

                if (d < 0) {
                    d += 2 * positionCounter + 1;
                } else {
                    d += 2 * (positionCounter - radius) + 1;
                    radius--;
                }
                positionCounter++;
            } while (positionCounter <= radius);
        }
        System.out.println("Initiating Green Coverage Redundantless Test.");
        System.out.println("Green box size:" + greenBlock.size());
        System.out.println("Greed box members:" + greenBlock);
        System.out.println("First Height: " + 300 * Math.tan(Math.toRadians(30)));
    }

    public void placingTower(int placedRow, int placedColumn, int signalRange) throws IOException {
        int maxBlock = signalRange / BLOCKRANGE;
        ArrayList<String> greenBlock = new ArrayList<>();
        ArrayList<String> redBlock = new ArrayList<>();

        for (int currentCircleArea = 1; currentCircleArea <= maxBlock; currentCircleArea++) {
            int d = (5 - currentCircleArea * 4) / 4;
            int positionCounter = 0;
            int radius = currentCircleArea;
            double xToRadian = Math.toRadians(30);
            double signalHeight = currentCircleArea * 300 * Math.tan(xToRadian);

            //Check if it is any duplicate coord or red block

            do {
                int placedRowPlusCounter = placedRow + positionCounter;
                int placedRowMinusCounter = placedRow - positionCounter;
                int placedRowPlusRadius = placedRow + radius;
                int placedRowMinusRadius = placedRow - radius;

                int placedColumnPlusCounter = placedColumn + positionCounter;
                int placedColumnMinusCounter = placedColumn - positionCounter;
                int placedColumnPlusRadius = placedColumn + radius;
                int placedColumnMinusRadius = placedColumn - radius;

                if (!greenBlock.contains("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius))
                        && !redBlock.contains("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius)))
                    if (DEMGrid[placedRowPlusCounter][placedColumnPlusRadius] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowPlusCounter,placedColumnPlusRadius),redBlock))
                        greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    else {
                        redBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    }

                if (!greenBlock.contains("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius))
                        && !redBlock.contains("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius)))
                    if (DEMGrid[placedRowPlusCounter][placedColumnMinusRadius] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowPlusCounter,placedColumnMinusRadius),redBlock))
                        greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius));
                    else {
                        redBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius));
                    }

                if (!greenBlock.contains("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius))
                        && !redBlock.contains("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius)))
                    if (DEMGrid[placedRowMinusCounter][placedColumnPlusRadius] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowMinusCounter,placedColumnPlusRadius),redBlock))
                        greenBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius));
                    else {
                        redBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius));
                    }

                if (!greenBlock.contains("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius))
                        && !redBlock.contains("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius)))
                    if (DEMGrid[placedRowMinusCounter][placedColumnMinusRadius] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowMinusCounter,placedColumnMinusRadius),redBlock))
                        greenBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius));
                    else {
                        redBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius));
                    }

                if (!greenBlock.contains("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter))
                        && !redBlock.contains("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter)))
                    if (DEMGrid[placedRowPlusRadius][placedColumnPlusCounter] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowPlusRadius,placedColumnPlusCounter),redBlock))
                        greenBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter));
                    else {
                        redBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter));
                    }

                if (!greenBlock.contains("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter))
                        && !redBlock.contains("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter)))
                    if (DEMGrid[placedRowPlusRadius][placedColumnMinusCounter] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowPlusRadius,placedColumnPlusCounter),redBlock))
                        greenBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter));
                    else {
                        redBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter));
                    }

                if (!greenBlock.contains("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter))
                        && !redBlock.contains("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter)))
                    if (DEMGrid[placedRowMinusRadius][placedColumnPlusCounter] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowMinusRadius,placedColumnPlusCounter),redBlock))
                        greenBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter));
                    else {
                        redBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter));
                    }

                if (!greenBlock.contains("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter))
                        && !redBlock.contains("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter)))
                    if (DEMGrid[placedRowMinusRadius][placedColumnMinusCounter] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowMinusRadius,placedColumnMinusCounter),redBlock))
                        greenBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter));
                    else {
                        redBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter));
                    }

                if (d < 0) {
                    d += 2 * positionCounter + 1;
                } else {
                    d += 2 * (positionCounter - radius) + 1;
                    radius--;
                }
                positionCounter++;
            } while (positionCounter <= radius);
        }
        System.out.println("Green box size:" + greenBlock.size());
        System.out.println("Greed box members:" + greenBlock);
        System.out.println("Red box size :" + redBlock.size());
        System.out.println("Red box member :" + redBlock);
        System.out.println("Check 400,401 Height :" + DEMGrid[400][401] );
        System.out.println("First Height: " + 300 * Math.tan(Math.toRadians(30)));
    }


    public void placingTowerwithRim(int placedRow, int placedColumn, int signalRange) throws IOException {
        int maxBlock = signalRange / BLOCKRANGE;
        int towerHeight = 100;

        ArrayList<String> greenBlock = new ArrayList<>();
        ArrayList<String> rimBlock = new ArrayList<>();
        ArrayList<String> redBlock = new ArrayList<>();

        double toLongLat = (double) 300 / 111111;
        double xToRadian = Math.toRadians(30);

        for (int currentCircleArea = 1; currentCircleArea <= maxBlock; currentCircleArea++) {
            int d = (5 - currentCircleArea * 4) / 4;
            int positionCounter = 0;
            int radius = currentCircleArea;
            double signalHeight = (currentCircleArea * 300 * Math.tan(xToRadian)) + towerHeight;

            do {
                //Declared in here so we don't need to redundantly doing operations in the loop
                //This is the mid-point circle algorithm from bresenham algorithm :)
                int placedRowPlusCounter = placedRow + positionCounter;
                int placedRowMinusCounter = placedRow - positionCounter;
                int placedRowPlusRadius = placedRow + radius;
                int placedRowMinusRadius = placedRow - radius;

                int placedColumnPlusCounter = placedColumn + positionCounter;
                int placedColumnMinusCounter = placedColumn - positionCounter;
                int placedColumnPlusRadius = placedColumn + radius;
                int placedColumnMinusRadius = placedColumn - radius;

                if (!greenBlock.contains("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius))
                        && !redBlock.contains("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius))
                        && !rimBlock.contains("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius))){           //Check for duplicated value
                    if(DEMGrid[placedRowPlusCounter][placedColumnPlusRadius] < signalHeight                             //Check if the height map is lower than signal height
                            && currentCircleArea == maxBlock                                                            //and the circle area loop is at the circle edge
                            && Collections.disjoint(lineOfSight(placedRow, placedColumn, placedRowPlusCounter, placedColumnPlusRadius), rimBlock)){     //and the list of lineofsight from the center to target and rimblock have no same value
                        rimBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                        greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    }else if (DEMGrid[placedRowPlusCounter][placedColumnPlusRadius] < signalHeight                      //Check if the heightmap is lower than signal height
                            && Collections.disjoint(lineOfSight(placedRow, placedColumn, placedRowPlusCounter, placedColumnPlusRadius), redBlock)) {  //and the list of lineofsight and redblock have no same value
                        greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    } else if (DEMGrid[placedRowPlusCounter][placedColumnPlusRadius] >= signalHeight                    //Check if the heightmap is greater or equal to signal height
                            && Collections.disjoint(lineOfSight(placedRow, placedColumn, placedRowPlusCounter, placedColumnPlusRadius), rimBlock)) {    //Check no same value in lineofsight and rimblock
                        rimBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                        redBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    } else {
                        redBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    }
                }

                if (!greenBlock.contains("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius))
                        && !redBlock.contains("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius))
                        && !rimBlock.contains("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius))){
                    if(DEMGrid[placedRowPlusCounter][placedColumnMinusRadius] < signalHeight
                            && currentCircleArea == maxBlock
                            && Collections.disjoint(lineOfSight(placedRow, placedColumn, placedRowPlusCounter, placedColumnMinusRadius), rimBlock)){
                        rimBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius));
                        greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    }else if (DEMGrid[placedRowPlusCounter][placedColumnMinusRadius] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowPlusCounter,placedColumnMinusRadius),redBlock)) {
                        greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius));
                    }else if(DEMGrid[placedRowPlusCounter][placedColumnMinusRadius] >= signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowPlusCounter,placedColumnMinusRadius),rimBlock)) {
                        rimBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius));
                        redBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius));
                    }else {
                        redBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnMinusRadius));
                    }
                }

                if (!greenBlock.contains("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius))
                        && !redBlock.contains("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius))
                        && !rimBlock.contains("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius))) {
                    if(DEMGrid[placedRowMinusCounter][placedColumnPlusRadius] < signalHeight
                            && currentCircleArea == maxBlock
                            && Collections.disjoint(lineOfSight(placedRow, placedColumn, placedRowMinusCounter, placedColumnPlusRadius), rimBlock)){
                        rimBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius));
                        greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    }else if (DEMGrid[placedRowMinusCounter][placedColumnPlusRadius] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow, placedColumn, placedRowMinusCounter, placedColumnPlusRadius), redBlock)){
                        greenBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius));
                    }else if(DEMGrid[placedRowMinusCounter][placedColumnPlusRadius] >= signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn, placedRowMinusCounter,placedColumnPlusRadius), rimBlock)){
                        rimBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius));
                        redBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius));
                    }else {
                        redBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnPlusRadius));
                    }
                }

                if (!greenBlock.contains("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius))
                        && !redBlock.contains("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius))
                        && !rimBlock.contains("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius))){
                    if(DEMGrid[placedRowMinusCounter][placedColumnMinusRadius] < signalHeight
                            && currentCircleArea == maxBlock
                            && Collections.disjoint(lineOfSight(placedRow, placedColumn, placedRowMinusCounter, placedColumnMinusRadius), rimBlock)){
                        rimBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius));
                        greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    }else if (DEMGrid[placedRowMinusCounter][placedColumnMinusRadius] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowMinusCounter,placedColumnMinusRadius),redBlock)) {
                        greenBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius));
                    }else if(DEMGrid[placedRowMinusCounter][placedColumnMinusRadius] >= signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowMinusCounter,placedColumnMinusRadius), rimBlock)) {
                        rimBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius));
                        redBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius));
                    }else {
                        redBlock.add("" + (placedRowMinusCounter) + "," + (placedColumnMinusRadius));
                    }
                }

                if (!greenBlock.contains("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter))
                        && !redBlock.contains("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter))
                        && !rimBlock.contains("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter))){
                    if(DEMGrid[placedRowPlusRadius][placedColumnPlusCounter] < signalHeight
                            && currentCircleArea == maxBlock
                            && Collections.disjoint(lineOfSight(placedRow, placedColumn, placedRowPlusRadius, placedColumnPlusCounter), rimBlock)){
                        rimBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter));
                        greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    }else if (DEMGrid[placedRowPlusRadius][placedColumnPlusCounter] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowPlusRadius,placedColumnPlusCounter),redBlock)) {
                        greenBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter));
                    }else if(DEMGrid[placedRowPlusRadius][placedColumnPlusCounter] >= signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowPlusRadius,placedColumnPlusCounter),rimBlock)) {
                        rimBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter));
                        redBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter));
                    }else {
                        redBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter));
                    }
                }

                if (!greenBlock.contains("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter))
                        && !redBlock.contains("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter))
                        && !rimBlock.contains("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter))){
                    if(DEMGrid[placedRowPlusRadius][placedColumnMinusCounter] < signalHeight
                            && currentCircleArea == maxBlock
                            && Collections.disjoint(lineOfSight(placedRow, placedColumn, placedRowPlusRadius, placedColumnMinusCounter), rimBlock)){
                        rimBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter));
                        greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    }else if (DEMGrid[placedRowPlusRadius][placedColumnMinusCounter] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowPlusRadius,placedColumnMinusCounter),redBlock)) {
                        greenBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter));
                    }else if(DEMGrid[placedRowPlusRadius][placedColumnMinusCounter] >= signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowPlusRadius,placedColumnMinusCounter),rimBlock)) {
                        rimBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter));
                        redBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter));
                    }else {
                        redBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnMinusCounter));
                    }
                }

                if (!greenBlock.contains("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter))
                        && !redBlock.contains("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter))
                        && !rimBlock.contains("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter))){
                    if(DEMGrid[placedRowMinusRadius][placedColumnPlusCounter] < signalHeight
                            && currentCircleArea == maxBlock
                            && Collections.disjoint(lineOfSight(placedRow, placedColumn, placedRowMinusRadius, placedColumnPlusCounter), rimBlock)){
                        rimBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter));
                        greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    }else if (DEMGrid[placedRowMinusRadius][placedColumnPlusCounter] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowMinusRadius,placedColumnPlusCounter),redBlock)) {
                        greenBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter));
                    }else if(DEMGrid[placedRowMinusRadius][placedColumnPlusCounter] >= signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowMinusRadius,placedColumnPlusCounter),rimBlock)) {
                        rimBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter));
                        redBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter));
                    }else {
                        redBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnPlusCounter));
                    }
                }

                if (!greenBlock.contains("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter))
                        && !redBlock.contains("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter))
                        && !rimBlock.contains("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter))){
                    if(DEMGrid[placedRowMinusRadius][placedColumnMinusCounter] < signalHeight
                            && currentCircleArea == maxBlock
                            && Collections.disjoint(lineOfSight(placedRow, placedColumn, placedRowMinusRadius, placedColumnMinusCounter), rimBlock)){
                        rimBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter));
                        greenBlock.add("" + (placedRowPlusCounter) + "," + (placedColumnPlusRadius));
                    }else if (DEMGrid[placedRowMinusRadius][placedColumnMinusCounter] < signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowMinusRadius,placedColumnMinusCounter),redBlock)) {
                        greenBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter));
                    }else if(DEMGrid[placedRowMinusRadius][placedColumnMinusCounter] >= signalHeight
                            && Collections.disjoint(lineOfSight(placedRow,placedColumn,placedRowMinusRadius,placedColumnMinusCounter),rimBlock)) {
                        rimBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter));
                        redBlock.add("" + (placedRowPlusRadius) + "," + (placedColumnPlusCounter));
                    }else {
                        redBlock.add("" + (placedRowMinusRadius) + "," + (placedColumnMinusCounter));
                    }
                }

                if (d < 0) {
                    d += 2 * positionCounter + 1;
                } else {
                    d += 2 * (positionCounter - radius) + 1;
                    radius--;
                }
                positionCounter++;
            } while (positionCounter <= radius);
        }

        //co-ord check
        System.out.println("Rim box before size :" + rimBlock.size());
        System.out.println("Rim box before member :" + rimBlock);

        //co-ord to longitude , latitude
        for(int i = 0 ; i < rimBlock.size() ; i++){
            String numStr = rimBlock.get(i);
            String[] numStrArry = numStr.split(",");
            double num1 = Integer.parseInt(numStrArry[0]) * toLongLat;
            double num2 = Integer.parseInt(numStrArry[1]) * toLongLat;
            rimBlock.set(i , "" + num1 + "," + num2);
            Collections.sort(rimBlock);
        }

        //Print Check
        System.out.println("Green box size:" + greenBlock.size());
        System.out.println("Green box members:" + greenBlock);
        System.out.println("Rim box size :" + rimBlock.size());
        System.out.println("Rim box member :" + rimBlock);
        System.out.println("Red box size :" + redBlock.size());
        System.out.println("Red box member :" + redBlock);
        System.out.println("Check 400,401 Height :" + DEMGrid[400][401] );
        System.out.println("First Height: " + 300 * Math.tan(Math.toRadians(30)));
    }

    private ArrayList<String> lineOfSight(int placedRow, int placedColumn, int targetRow, int targetColumn) {
        // delta of exact value and rounded value of the dependent variable
        // use to detect and return the position between the path of the center co-ord to the target co-ord in arraylist
        // this is line of sight linealgorithm from bresenham :)

        int d = 0;

        int xFromMiddle = Math.abs(targetRow - placedRow);
        int yFromMiddle = Math.abs(targetColumn - placedColumn);

        int dx2 = 2 * xFromMiddle; // slope scaling factors to
        int dy2 = 2 * yFromMiddle; // avoid floating point

        int ix = placedRow < targetRow ? 1 : -1; // increment direction
        int iy = placedColumn < targetColumn ? 1 : -1;

        int x = placedRow;
        int y = placedColumn;

        ArrayList<String> coordKeeper = new ArrayList<>();

        if (xFromMiddle >= yFromMiddle) {
            while (true) {
                //System.out.println(x + "," + y);
                coordKeeper.add(x+","+y);
                if (x == targetRow)
                    break;
                x += ix;
                d += dy2;
                if (d > xFromMiddle) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                //System.out.println(x + "," + y);
                coordKeeper.add(x+","+y);
                if (y == targetColumn)
                    break;
                y += iy;
                d += dx2;
                if (d > yFromMiddle) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
    return coordKeeper;
    }
}
