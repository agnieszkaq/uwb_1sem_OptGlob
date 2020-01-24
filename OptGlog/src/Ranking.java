import java.util.Arrays;

public class Ranking {

	int n;
	double[] reverse_tab, min_tab, index_tab, ranking_final_min_values,ranking_final_max_values;;

	public Ranking() {
	}

	public Ranking(int n) {
		this.n = n;
	}
	
	public double[] sort_by_max_tab(double[] tab) {
		double[] reverse_tab = new double[this.n];
		double[] max_tab = new double[this.n];
		for (int i = 0; i < this.n; i++) {
			max_tab[i] = tab[i];
		}
		Arrays.sort(max_tab);
		for (int i = 0; i < this.n; i++) {
			reverse_tab[i] = max_tab[this.n - i - 1];
		}

		this.reverse_tab = reverse_tab;
		return reverse_tab;
	}

	public double[] sort_by_min_tab(double[] tab) {
		double[] min_tab = new double[this.n];
		for (int i = 0; i < this.n; i++) {
			min_tab[i] = tab[i];
		}
		Arrays.sort(min_tab);
		this.min_tab = min_tab;
		return min_tab;
	}

	int get_double_random() {
		return (int) (Math.random() * (int) (Math.random() * n));
	}

	public double[] getIndexTab() {
		index_tab = new double[this.n];
		for (int i = 0; i < this.n; i++) {
			index_tab[i] = get_double_random();
		}
		return index_tab;
	}

	double[] getRankingMaxValues(double[] tab) {
		ranking_final_max_values = new double[this.n];
		for (int i = 0; i < this.n; i++) {
			ranking_final_max_values[i] = reverse_tab[(int) index_tab[i]];
		}
		return ranking_final_max_values;
	}

	double[] getRankingMinValues(double[] tab) {
		ranking_final_min_values = new double[this.n];
		for (int i = 0; i < this.n; i++) {
			ranking_final_min_values[i] = min_tab[(int) index_tab[i]];
		}
		return ranking_final_min_values;
	}
}
