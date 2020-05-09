import java.util.Random;

public class Board {

    // BOARD IS 2-DIMENSIONAL

    // Dimensions and pipe storage
    int      height;
    int      width;
    Pipe[][] pipes;
    Random   random;

    // Allocation constructor
    public Board(int height, int width) {

        this.height = height;
        this.width  = width;
        this.pipes  = new Pipe[height][width];
        random      = new Random(System.currentTimeMillis());
    }

    // Spawn the pipes to fill up a board
    public void fillPipes() {

        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++)
                this.pipes[i][j] = new Pipe(2, random);
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

        // Create a new board
        int height = 2;
        int width  = 2;
        Board board = new Board(height, width);

        // Demonstrate the code
        boolean success = false;
        while (!success) {

            // Arrange the pipes
            board.fillPipes();
            success = board.isLegal();

            // Print whether board is legal
            System.out.println("Is board legal: " +success);
            System.out.println(board);
        }
    }
}
