import acm.graphics.GImage;
import acm.program.GraphicsProgram;

public class FlipHorizontal extends GraphicsProgram {
    /* main method */
    public static void main(String[] args) {
        new FlipHorizontal().start(args);
    }

    public void run() {
        GImage img = new GImage("CS106A.jpg");
        add(img, 0, 0);

        GImage img2 = flipHorizontal(img);
        add(img2, img.getWidth(), 0);

    }

    private GImage flipHorizontal(GImage image) {
        int[][] array = image.getPixelArray();
        int height = array.length;
        int width = array[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width / 2; j++) {
                int temp = array[i][j];
                array[i][j] = array[i][width - 1 - j];
                array[i][width - 1 - j] = temp;
            }
        }

        return new GImage(array);
    }
}
