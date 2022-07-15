

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the input file name (.txt only):");
        String name = sc.nextLine();
        System.out.println("Processing " + name + "...");
        System.out.println("The initial maze is:");
        Maze maze = new Maze(name);
        System.out.println(maze);
        System.out.println("The path found using a stack is:");
        maze.search();


    }
}
