import java.util.Random;

public class Pipe {

    // Unit cube faces [dimension][orientation]
    private boolean[][] faces;

    // Randomized constructor
    public Pipe(int nDims, Random random) {

        // Init my arrays
        int[] dimensions   = new int[2];
        int[] orientations = new int[2];
        this.faces = new boolean[nDims][2];

        // Select random face
        dimensions  [0] = random.nextInt(nDims);
        orientations[0] = random.nextInt(2);

        // Select new face
        do {
            dimensions  [1] = random.nextInt(nDims);
            orientations[1] = random.nextInt(2);
        }
        while (dimensions[0] == dimensions[1] && orientations[0] == orientations[1]);

        // Set opening faces to true
        this.faces[dimensions[0]][orientations[0]] = true;
        this.faces[dimensions[1]][orientations[1]] = true;
    }

    // Check for pipe pairwise compatibility
    public static boolean isCompatible(Pipe lowerPipe, Pipe higherPipe, int dimension) {

        return lowerPipe.faces[dimension][1] == higherPipe.faces[dimension][0];
    }

    @Override
    public String toString() {

        // Only allow printing for nDim == 2
        if (this.faces.length != 2)
            throw new UnsupportedOperationException("Cannot print if spatial nDims not 2.");

        // Format output
        StringBuilder output = new StringBuilder();

        if (faces[0][0]) output.append('T');
        if (faces[0][1]) output.append('B');
        if (faces[1][0]) output.append('L');
        if (faces[1][1]) output.append('R');

        return output.toString();
    }
}
