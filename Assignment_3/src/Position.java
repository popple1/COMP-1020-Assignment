public class Position {
    public int row;
    public int column;
    public SquareType squareType;
    private boolean visited;
    public String symbol;

    public Position(int row, int column, SquareType squareType)
    {
        this.row = row;
        this.column = column;
        this.squareType = squareType;
        visited = false;
    }

    public String symbol()
    {
        if(squareType == SquareType.PATH)
            symbol = ".";
        else if(squareType == SquareType.WALL)
            symbol = "#";
        else if(squareType == SquareType.START)
            symbol = "S";
        else
            symbol = "F";
        return symbol;
    }

    public String coordinates()
    {
        return "(" + row + ",  " + column + ") ";
    }

    public boolean visited()
    {
        visited = true;
        return visited;
    }

    public boolean checkVisited()
    {
        return visited;
    }
}
