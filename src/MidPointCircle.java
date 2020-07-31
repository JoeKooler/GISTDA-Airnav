import java.awt.Color;

public class MidPointCircle {
    private BasicBitmapStorage image;

    public MidPointCircle(final int imageWidth, final int imageHeight) {
        this.image = new BasicBitmapStorage(imageWidth, imageHeight);
    }

    private void drawCircle(final int placingX, final int placingY, final int radius) {
        int d = (5 - radius * 4)/4;
        int positionCounter = 0;
        int radiusCounter = radius;
        Color circleColor = Color.blue;

        do {
            image.setPixel(placingX + positionCounter, placingY + radiusCounter, circleColor);
            image.setPixel(placingX + positionCounter, placingY - radiusCounter, circleColor);
            image.setPixel(placingX - positionCounter, placingY + radiusCounter, circleColor);
            image.setPixel(placingX - positionCounter, placingY - radiusCounter, circleColor);
            image.setPixel(placingX + radiusCounter, placingY + positionCounter, circleColor);
            image.setPixel(placingX + radiusCounter, placingY - positionCounter, circleColor);
            image.setPixel(placingX - radiusCounter, placingY + positionCounter, circleColor);
            image.setPixel(placingX - radiusCounter, placingY - positionCounter, circleColor);
            if (d < 0) {
                d += 2 * positionCounter + 1;
            } else {
                d += 2 * (positionCounter - radiusCounter) + 1;
                radiusCounter--;
            }
            positionCounter++;
        } while (positionCounter <= radiusCounter);

    }

    public static void main(String[] args){
        MidPointCircle midPointCircle = new MidPointCircle(100,100);
        midPointCircle.drawCircle(10,10,10);
    }
}
 