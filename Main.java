import java.io.File;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		File maze = new File("maze.dat");
		int x, y, linenum = 0, startX, startY;
		String temp;
		char[][] mazeChars;
		int[] start = new int[2];

		try (Scanner doabarrelroll = new Scanner(maze))
		{
			x = doabarrelroll.nextInt();
			y = doabarrelroll.nextInt();
			mazeChars = new char[x][y];
			doabarrelroll.nextLine();

			while (doabarrelroll.hasNext())
			{
				temp = doabarrelroll.nextLine();

				for (int bogus = 0; bogus < temp.length(); bogus++)
				{
					mazeChars[linenum][bogus] = temp.charAt(bogus);
				}

				linenum++;
			}

			start = findStartX(mazeChars, 0, 0);

			if(move(mazeChars, start[0], start[1]))
                        {
                                System.out.println("Solution found!");
                        }
                        else
                        {
                                System.out.println("No solution.");
                        }

			for (int bogus = 0; bogus < mazeChars.length; bogus++)
			{
				for (int bogus2 = 0; bogus2 < mazeChars[bogus].length; bogus2++)
				{
					System.out.print(mazeChars[bogus][bogus2]);
				}
				System.out.println();
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static int[] findStartX(char[][] maze, int x, int y)
	{
		int[] start = {-1, -1};

		if (x < maze.length)
		{
			if ((start[1] = findStartY(maze[x], y)) > -1)
			{
				start[0] = x;
				return (start);
			}

			return (findStartX(maze, x + 1, 0));
		}

		return (start);
	}

	private static int findStartY(char[] maze, int y)
	{
		if (y < maze.length)
		{
			if (maze[y] == '+')
			{
				return (y);
			}

			return (findStartY(maze, y + 1));
		}

		return (-1);
	}

	private static boolean move(char[][] maze, int x, int y)
	{
		//Base Cases:
		//Exit is found: true
		//Dead end is found: false
		//
		//Recursive Case:
		//blank is found

		boolean end = false;

		if (maze[x][y] == '-')
		{
			end = true;
		}
		else if (maze[x][y] == 'X' || maze[x][y] == '.')
		{
			end = false;
		}
		else
		{
			maze[x][y] = '+';

			if (x > 0 && (maze[x - 1][y] == ' ' || maze[x - 1][y] == '-') && !end)
			{
				end = move(maze, x - 1, y);
			}
			if (x < maze.length && (maze[x + 1][y] == ' ' || maze[x + 1][y] == '-') && !end)
			{
				end = move(maze, x + 1, y);
			}
			if (y > 0 && (maze[x][y - 1] == ' ' || maze[x][y - 1] == '-') && !end)
			{
				end = move(maze, x, y - 1);
			}
			if (y < maze[x].length && (maze[x][y + 1] == ' '|| maze[x][y + 1] == '-') && !end)
			{
				end = move(maze, x, y + 1);
			}
		}

		if (!end)
		{
			maze[x][y] = '.';
		}

		return (end);
	}
}
