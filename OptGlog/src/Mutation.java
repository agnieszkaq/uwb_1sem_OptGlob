
public class Mutation {

	int n;
	double pr = 0.3;
	int[][] inwersja_tab;

	public Mutation() {
	}

	public Mutation(int n) {
		this.n = n;
	}

	double get_random() {
		return Math.random() * 1;
	}

	int[][] create_classic(int[][] tab, int m) {
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < m; j++) {
				double r = get_random();

				if (pr > r) {
					if (tab[i][j] == 0) {
						tab[i][j] = 1;
					} else
						tab[i][j] = 0;
				}
			}
		}
		return tab;
	}

	int[][] create_inversion(int[][] tab, int m) {
		inwersja_tab = new int[this.n][m];
		int[] reverse_tab = new int[m];

		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < m; j++) {
				inwersja_tab[i][j] = tab[i][j];
			}
		}

		for (int i = 0; i < n; i++) {
			int random1 = (int) (Math.random() * (m - 1));
			int random2 = (int) (Math.random() * (m - 1));
			double r = get_random();

			if (pr > r) {
				int start;
				int end;
				if (random1 < random2) {
					start = random1;
					end = random2;

				} else {
					start = random2;
					end = random1;
				}
				for (int j = start; j < end; j++) {

					reverse_tab[j] = inwersja_tab[i][end-j-1];
				}
				for (int j = start; j < end; j++) {

					inwersja_tab[i][j] = reverse_tab[j];
				}
			}
		}
		return inwersja_tab;
	}
}
