//class LineOfSightTester {
//
//    public static void main(String[] args){
//        drawLine(0, 0, 1, 2); // NNE
//    }
//
//    private static void drawLine(int centerX, int centerY, int width, int height) {
//        // delta of exact value and rounded value of the dependent variable
//        int d = 0;
//
//        int xFromMiddle = Math.abs(width - centerX);
//        int yFromMiddle = Math.abs(height - centerY);
//
//        int dx2 = 2 * xFromMiddle; // slope scaling factors to
//        int dy2 = 2 * yFromMiddle; // avoid floating point
//
//        int ix = centerX < width ? 1 : -1; // increment direction
//        int iy = centerY < height ? 1 : -1;
//
//        int x = centerX;
//        int y = centerY;
//
//        if (xFromMiddle >= yFromMiddle) {
//            while (true) {
//                System.out.println(x + "," + y);
//                if (x == width)
//                    break;
//                x += ix;
//                d += dy2;
//                if (d > xFromMiddle) {
//                    y += iy;
//                    d -= dx2;
//                }
//            }
//        } else {
//            while (true) {
//                System.out.println(x + "," + y);
//                if (y == height)
//                    break;
//                y += iy;
//                d += dx2;
//                if (d > yFromMiddle) {
//                    x += ix;
//                    d -= dy2;
//                }
//            }
//        }
//    }
//}