import java.awt.Point;
import java.util.Scanner;
import java.util.LinkedList;

public class BFSPathFinding {
    private final int MAXR = 1000, MAXC = 1000;
    private int R,C;
    private int sX,sY,gX,gY;
    boolean [][] grid = new boolean[MAXR+2][MAXC+2];
    private int [][] dist = new int[MAXR+2][MAXC+2];
    private boolean [][] vis = new boolean[MAXR+2][MAXC+2];
    private final int [][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    LinkedList<Point> path;

    public LinkedList<Point> BFSPathFinding(int startX, int startY, int endX, int endY, boolean[][] grid2){
        C = 56;
        R = 58;
        sX = startX;
        sY = startY;
        gX = endX;
        gY = endY;
        for (int i = 0; i < R; i++){
            for (int j = 0; j < C; j++){
                if (!grid2[i][j]) grid[i][j] = false;
                else grid[i][j] = true;
            }
        }
        bfs();
        Point cur = new Point(gX,gY);
        path = new LinkedList<Point>();
        for (int i = dist[gY][gX]; i >= 1; i--){
            if (i == 1){
                path.addFirst(cur);
                break;
            }
            for (int j = 0; j < 4; j++){
                int nextX = cur.x + dir[j][0];
                int nextY = cur.y + dir[j][1];
                if (nextX < 0 || nextX >= C || nextY <= 0 || nextY >= R) continue;
                if (dist[nextY][nextX] == i-1){
                    path.addFirst(cur);
                    cur = new Point(nextX,nextY);
                    break;
                }
            }
        }
        return path;
    }

    public void reset(){
        grid = new boolean[MAXR+2][MAXC+2];
        dist = new int[MAXR+2][MAXC+2];
        vis = new boolean[MAXR+2][MAXC+2];
    }

    private void bfs(){
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
                if (nextX < 0 || nextX >= C || nextY < 0 || nextY >= R) continue;
                if (!vis[nextY][nextX] && grid[nextY][nextX]){
                    dist[nextY][nextX] = dist[cur.y][cur.x] + 1;
                    vis[nextY][nextX] = true;
                    q.addLast(new Point(nextX,nextY));
                }
            }
        }
    }
}
