import java.util.Random;

public class Board {

    // BOARD IS 2-DIMENSIONAL

    // Dimensions and pipe storage
    int      height;
    int      width;
    Pipe[][] pipes;
    Random   random;

    // Allocation constructor
    public Board(int height, int width, Random random) {

        this.height = height;
        this.width  = width;
        this.pipes  = new Pipe[height][width];
        this.random = random;
    }

    // Spawn random pipes to fill up a board
    public void randomPipes() {

        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++)
                this.pipes[i][j] = new Pipe(2, this.random);
    }

    // Check if the pipe arrangement is legal
    public boolean isLegal() {

        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++) {

                // Periodic boundary conditions
                int iNext, jNext;

                if (i == this.height - 1)
                    iNext = 0;
                else
                    iNext = i + 1;

                if (j == this.width - 1)
                    jNext = 0;
                else
                    jNext = j + 1;

                // Check for compatibility in all dimensions
                if (    !Pipe.isCompatible(this.pipes[i][j], this.pipes[iNext][j], 0) ||
                        !Pipe.isCompatible(this.pipes[i][j], this.pipes[i][jNext], 1))
                    return false;
            }
        return true;
    }

    // Compute the probability that a randomly generated board is legal
    public double probabilityLegal(int repetitions) {

        // Run the simulations for `repetitions` successes
        int successes = 0;
        int generations;

        for (generations = 0; successes < repetitions; generations++) {

            // Generate random board
            this.randomPipes();

            if (this.isLegal())
                successes++;
        }
        return (double) repetitions / generations;
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();

        // Run through the entire board
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                output.append(this.pipes[i][j]);
                output.append(" ");
            }
            output.append("\n");
        }
        output.append("\n");

        return output.toString();
    }

    // The main attraction
    public static void main(String[] args) {

        // Create new board
        int height = 2;
        int width  = 2;
        Random random = new Random(System.currentTimeMillis());
        Board board = new Board(height, width, random);

        // Compute probability
        int repetitions = 100;
        System.out.println("Probability of legal board: " +
                            board.probabilityLegal(repetitions) * 100 + " %.");
    }
}
