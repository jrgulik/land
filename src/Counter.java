import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Counter {

    static final int width = 400;
    static final int height = 600;


    static PlotOfLand[][] allTheLand = new PlotOfLand[width][height];
    static List<Integer> viableLandSizes;

    public static void main(String[] args){

        initMap();
        viableLandSizes = new ArrayList<>();


        boolean anyMore;

        do {
            System.out.print("Please enter the barren land coordinates: ");
            String[] barren = System.console().readLine().split(" ");


            for (int i = Integer.parseInt(barren[0]); i <= Integer.parseInt(barren[2]); i++) {
                for (int j = Integer.parseInt(barren[1]); j <= Integer.parseInt(barren[3]); j++) {
                    allTheLand[i][j].barren = true;
                }
            }

            System.out.println("Any more? (y/n)");
            anyMore = System.console().readLine().toLowerCase().equals("y");
        }while (anyMore);


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(!allTheLand[i][j].counted && !allTheLand[i][j].barren)
                {
                    viableLandSizes.add(traverseMap(i,j,0));
                }
            }
        }

        viableLandSizes.removeIf(integer -> integer == 0);
        viableLandSizes.sort(Collections.reverseOrder());
        System.out.println(viableLandSizes);
    }

    private static int traverseMap(int i, int j, int currentCount) {

        if (allTheLand[i][j].barren) {
            return currentCount;
        }

        if (!allTheLand[i][j].counted) {
            allTheLand[i][j].counted = true;
            currentCount++;
        }

        if (i > 0 && !allTheLand[i - 1][j].counted && !allTheLand[i - 1][j].barren) {
            currentCount = traverseMap(i - 1, j, currentCount);
        }

        if (j < height - 1 && !allTheLand[i][j + 1].counted && !allTheLand[i][j + 1].barren) {
            currentCount = traverseMap(i, j + 1, currentCount);
        }

        if (i < width - 1 && !allTheLand[i + 1][j].counted && !allTheLand[i + 1][j].barren) {
            currentCount = traverseMap(i + 1, j, currentCount);
        }

        if (j > 0 && !allTheLand[i][j - 1].counted && !allTheLand[i][j - 1].barren) {
            currentCount = traverseMap(i, j - 1, currentCount);
        }

        return currentCount;
    }

    private static void initMap() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                allTheLand[i][j] = new PlotOfLand();
            }
        }
    }
}
