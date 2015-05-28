import java.awt.Point;
import java.util.Scanner;
import java.util.LinkedList;

public class BFSPathFinding {

    static final int MAXR = 1000, MAXC = 1000;
    static int R,C;
    static int sX,sY,gX,gY;
    static boolean [][] grid = new boolean[MAXR+2][MAXC+2];
    static int [][] dist = new int[MAXR+2][MAXC+2];
    static boolean [][] vis = new boolean[MAXR+2][MAXC+2];
    static final int [][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static Scanner scan = new Scanner(System.in);

    public static String main(int startX, int startY, int endX, int endY){
        //System.out.println("Enter grid size (X by Y, MAX 1000x1000): ");
        C = 58;
        R = 56;
        //System.out.println("Enter start coordinates (top left is (1,1)): ");
        sX = startX;
        sY = startY;
        //System.out.println("Enter goal coordinates (top left is (1,1)): ");
        gX = endX;
        gY = endY;
        //System.out.println("Enter grid (0 for wall, 1 for empty space): ");
        int input;
        for (int i = 1; i <= R; i++){
            for (int j = 1; j <= C; j++){
                //input = scan.nextInt();
                //if (input == 0) grid[i][j] = false;
                //else 
                grid[i][j] = true;
            }
        }
        bfs();
        Point cur = new Point(gX,gY);
        LinkedList<Point> path = new LinkedList<Point>();
        for (int i = dist[gY][gX]; i >= 0; i--){
            for (int j = 0; j < 4; j++){
                int nextX = cur.x + dir[j][0];
                int nextY = cur.y + dir[j][1];
                if (dist[nextY][nextX] == i-1){
                    path.addFirst(cur);
                    cur = new Point(nextX,nextY);
                    break;
                }
            }
        }
        while(!path.isEmpty()){
            return (path.getFirst().x + "x" + path.getFirst().y);
            //path.removeFirst();
        }
        return "";
    }

    private static void bfs(){
        LinkedList<Point> q = new LinkedList<Point>();
        q.addLast(new Point(sX,sY));
        dist[sY][sX] = 1;
        vis[sY][sX] = true;
        while(!q.isEmpty()){
            Point cur = q.removeFirst();
            if (cur.x == gX && cur.y == gY) return;
            for (int i = 0; i < 4; i++){
                int nextY = cur.y + dir[i][1];
                int nextX = cur.x + dir[i][0];
                if (nextX < 1 || nextX > C || nextY < 1 || nextY > R) continue;
                if (!vis[nextY][nextX] && grid[nextY][nextX]){
                    dist[nextY][nextX] = dist[cur.y][cur.x] + 1;
                    vis[nextY][nextX] = true;
                    q.addLast(new Point(nextX,nextY));
                }
            }
        }
    }

}
