package one;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Pair {

	Set<Integer> set = new HashSet<>(2);
	int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
		set.add(x);
		set.add(y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(set);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != getClass())
			return false;

		Pair p = (Pair) obj;

		return (p.x == x && p.y == y) || (p.x == y && p.y == x);
	}
}

public class GridTraveler {

	static HashMap<Pair, Long> mp = new HashMap<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mp.put(new Pair(1, 1), 1L);

		long t3 = System.currentTimeMillis();
		long ans2 = grid_traveler(new Pair(12, 12));
		long t4 = System.currentTimeMillis();
		System.out.println("Cacheing approach: " + ans2 + ", Time taken = " + (t4 - t3));

		long t1 = System.currentTimeMillis();
		long ans = grid_traveler(12, 12);
		long t2 = System.currentTimeMillis();
		System.out.println("My approach: " + ans + ", Time taken = " + (t2 - t1));

	}

	public static long grid_traveler(int n, int m) {
		long[][] grid = new long[n][m];

		grid[0][0] = 1;

		boolean hasTop = false, hasLef = false;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				hasTop = i - 1 >= 0;
				hasLef = j - 1 >= 0;

				if (hasTop && hasLef) {
					grid[i][j] = grid[i][j - 1] + grid[i - 1][j];
				} else if (hasLef) {
					grid[i][j] = grid[i][j - 1];
				} else if (hasTop) {
					grid[i][j] = grid[i - 1][j];
				}
			}
		}

		return grid[n - 1][m - 1];
	}

	public static long grid_traveler(Pair p) {
		if (p.x == 0 || p.y == 0)
			return 0;
		if (mp.containsKey(p)) {
			return mp.get(p);
		}

		long ans = grid_traveler(new Pair(p.x - 1, p.y)) + grid_traveler(new Pair(p.x, p.y - 1));
		mp.put(p, ans);
		return ans;
	}

	public static long grid_traveler2(int n, int m) {
		if (n == 0 || m == 0)
			return 0;
		if (n == 1 && m == 1)
			return 1;

		return grid_traveler2(n - 1, m) + grid_traveler2(n, m - 1);
	}

}
