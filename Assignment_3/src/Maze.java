import java.io.*;
import java.util.Scanner;

public class Maze {
    Position[][] maze;
    File myObj;
    boolean foundPath;
    Position position;
    int row;
    int column;
    int endRow;
    int endColumn;
    SquareType symbol;
    public Maze(String fileName) throws FileNotFoundException {
        foundPath = false;
        Scanner inFs = new Scanner(new File(fileName));
        row = inFs.nextInt();
        column = inFs.nextInt();
        maze = new Position[row][column];
        for (int i = 0; i < row; i++) {
            String newRecord = inFs.next();
            for (int j = 0; j < column; j++) {
                char data = newRecord.charAt(j);
                if (data == '#')
                    symbol = SquareType.WALL;
                if (data == '.')
                    symbol = SquareType.PATH;
                if (data == 'S')
                    symbol = SquareType.START;
                if (data == 'F')
                    symbol = SquareType.FINISH;
                position = new Position(i, j, symbol);
                maze[i][j] = position;
            }
        }
        endRow = finishRow();
        endColumn = finishColumn();

        inFs.close();

    }

    private int finishRow()
    {
        int temp = -1;
        for(int i = 0; i < row; i++)
            for(int j = 0; j < column; j++)
                if(maze[i][j].symbol() == "F")
                    temp = i;
        return temp;
    }

    private int finishColumn()
    {
        int temp = -1;
        for(int i = 0; i < row; i++)
            for(int j = 0; j < column; j++)
                if(maze[i][j].symbol() == "F")
                    temp = j;
        return temp;
    }

    public void tryPosition(int row, int col)
    {
        maze[row][col].visited();
    }

    public boolean solved(int row, int col)
    {
        return(row == endRow && col == endColumn);
    }

    public int getRow()
    {
        return maze.length;
    }

    public int getColumn()
    {
        return maze[0].length;
    }

    public void markPath(int row, int col)
    {
        maze[row][col].visited();
    }

    public boolean validPosition(int row, int column)
    {
        boolean result = false;
        if(row >= 0 && row < maze.length && column >= 0 && column < maze[row].length)
            if((maze[row][column].symbol().equals(".") || maze[row][column].symbol().equals("F")) && !(maze[row][column].checkVisited()))
            {
                result = true;
            }
        return  result;
    }

    public void search()
    {
        findStart();
        search(row, column, SquareType.START);
        System.out.println(row);
    }


    public void search(int x, int y, SquareType symbol)
    {
        boolean done = false;
        Stack newStack = new Stack(maze.length* maze[0].length);
        newStack.push(maze[x][y]);
        maze[x][y].visited();
        while(!newStack.isEmpty() && !(done))
        {
            try {
                if(solved(x,y)) {
                    done = true;
                    System.out.println("WON");
                }
                else if(validPosition(x-1,y)) {
                    x = x-1;
                    maze[x][y].visited();
                    newStack.push(maze[x][y]);
                    System.out.println("UP");
                }
                else if(validPosition(x,y-1)) {
                    y = y-1;
                    maze[x][y].visited();
                    newStack.push(maze[x][y]);
                    System.out.println("LEFT");
                }
                else if(validPosition(x+1,y)) {
                    x = x+1;
                    maze[x][y].visited();
                    newStack.push(maze[x][y]);
                    System.out.println("DOWN");
                }
                else if(validPosition(x,y +1)) {
                    y = y+1;
                    maze[x][y].visited();
                    newStack.push(maze[x][y]);
                    System.out.println("RIGHT");
                }
                else {
                    Position pop = newStack.pop();
                    x = pop.row;
                    y = pop.column;
                    System.out.println("POP");
                    if (newStack.isEmpty()) {
                        System.out.println("NO PATH");
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("something went wrong");
            }
        }
        if(done)
        {
            System.out.println(toString(newStack));
        }
    }

    public String toString(Stack posStack)
    {
        String coOrdinate = "";
        while(!posStack.isEmpty())
        {
            Position popped = posStack.pop();
            coOrdinate += popped.coordinates();
            if(popped.symbol() != "F" && popped.symbol() != "S")
            {
                maze[popped.row][popped.column].symbol = "*";

            }

        }
        System.out.println(toString());
        return "Path from start to finish: " + coOrdinate;
    }
    public String toString()
    {

        String result ="";
        for(int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                result += maze[i][j].symbol;

            }
            result += "\n";
        }
        return result;
    }

    public void findStart()
    {
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++)
                if (maze[i][j].symbol() == "S") {
                    row = i;
                    column = j;
                }
        }

    }
}
