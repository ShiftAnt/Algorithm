import java.io.*;
import java.util.*;

class Node {
	int y, x;
	int rb = 0;
	int num = 0;
	ArrayList<Node> group;
	public Node(int y, int x) {
		this.y = y; this.x = x;
	}
}
public class Main {
	int n, m;
	int[][] P;

	int[] dr = {-1, 0, 0, 1};
	int[] dc = {0, -1, 1, 0};
	Node group(int y, int x, boolean[][] vstd) {
		vstd[y][x] = true;
		var que = new ArrayList<Node>();
		que.add(new Node(y, x));
		var ret = new Node(100, 100);
		for (int tc = 0; tc < que.size(); tc++) {
			var cur = que.get(tc);
			if (P[cur.y][cur.x] == 0) ++ret.rb;
			++ret.num;
			if (P[cur.y][cur.x] > 0 && (ret.y > cur.y || ret.y == cur.y && ret.x > cur.x)) {
				ret.y = cur.y;
				ret.x = cur.x;
			}
			for (int i = 0; i < dr.length; i++) {
				var a = cur.y + dr[i];
				var b = cur.x + dc[i];
				if (a >= 0 && a < n && b >= 0 && b < n && !vstd[a][b] && (P[a][b] == 0 || P[a][b] == P[y][x])) {
					vstd[a][b] = true;
					que.add(new Node(a, b));
				}
			}
		}
		ret.group = que;
		return ret;
	}
	int ret = 0;
	boolean findGroup() {
		var vstd = new boolean[n][n];
		int i, j;
		var rbs = new ArrayList<Node>();

		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (P[i][j] == 0) rbs.add(new Node(i, j));
			}
		}
		var min = new Node(100, 100);
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (!vstd[i][j] && P[i][j] > 0) {
					for (Node rb : rbs) {
						vstd[rb.y][rb.x] = false;
					}
					var sub = group(i, j, vstd);
					if (min.num < sub.num) {
						min = sub;
					} else if (min.num == sub.num) {
						if (min.rb < sub.rb) {
							min = sub;
						} else if (min.rb == sub.rb) {
							if (min.y < sub.y) {
								min = sub;
							} else if (min.y == sub.y) {
								if (min.x < sub.x) min = sub;
							}
						}
					}
				}
			}
		}
		if (min.num < 2) return false;
		ret += min.num * min.num;
		for (Node del : min.group) {
			P[del.y][del.x] = -2;
		}
		return true;
	}
	void gravity() {
		int i, j;

		for (j = 0; j < n; j++) {
			for (i = n - 1; i >= 0; i--) {
				if (P[i][j] < 0) continue;
				int tmp = P[i][j];
				P[i][j] = -2;
				int a = i + 1;
				while (a < n && P[a][j] == -2) {
					++a;
				}
				P[a - 1][j] = tmp;
			}
		}
	}
	void rotate() {
		int[][] tmp = new int[n][n];
		int i, j;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				tmp[i][j] = P[i][j];
			}
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				P[n - 1 - j][i] = tmp[i][j];
			}
		}
	}
	void solution() throws Exception {
		var br = new BufferedReader(new InputStreamReader(System.in));
		var st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		P = new int[n][n];
		int i, j;

		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				P[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (findGroup()) {
			gravity();
			rotate();
			gravity();
		}
		System.out.println(ret);
	}
	public static void main(String[] args) throws Exception {
		new Main().solution();
	}
}