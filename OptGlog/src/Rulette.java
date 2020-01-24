import java.util.Arrays;

public class Rulette {

	int n;
	double sum;
	double[] index_tab, random_tab, distribution_tab, sort_tab, eval_tab;
	double maxValue;

	public Rulette() {

	}

	public Rulette(int n) {
		this.n = n;
	}

	double get_max_value(double[] tab) {
		sort_tab = tab;
		Arrays.sort(sort_tab);
		maxValue = sort_tab[n - 1];
		return maxValue;
	}

	double[] create_eval_tab(double[] tab) {
		get_max_value(tab);
		eval_tab = new double[n];
		for (int i = 0; i < n; i++) {
			eval_tab[i] = this.maxValue - tab[i] + 1;
		}
		return eval_tab;
	}

	double getSumOfRestringX(double[] sum_tab) {
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum = sum +  sum_tab[i];
		}
		this.sum =  sum;
		return sum;
	}

	double[] show_probability(double[] tab) {
		double[] probability_tab = new double[n];
		for (int i = 0; i < n; i++) {
			probability_tab[i] = tab[i] / sum;
		}
		return probability_tab;
	}

	double[] get_distribution(double[] tab) {
		double[] probablity_tab = show_probability(tab);
		double[] distribution_tab = new double[n];
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum = sum + probablity_tab[i];
			distribution_tab[i] = sum;
		}
		this.distribution_tab = distribution_tab;
		return distribution_tab;
	}

	double get_random() {
		return (Math.random() * 1);
	}

	double[] get_tab_index(double[] tab) {
		double[] distribution_tab = get_distribution(tab);
		double[] rulette_index_tab = new double[n];
		double[] random_tab = new double[n];

		for (int i = 0; i < n; i++) {
			double random = get_random();
			random_tab[i] = random;
			if (random < distribution_tab[0]) {
				rulette_index_tab[i] = 0;
			} else {
				for (int j = 1; j < n; j++) {
					if (random > distribution_tab[j - 1]) {
						rulette_index_tab[i] = j;
					}
				}
			}
		}
		this.random_tab = random_tab;
		this.index_tab = rulette_index_tab;
		return rulette_index_tab;
	}

	double[] show_information(double[] sum) {
		for (int i = 0; i < n; i++) {
			System.out.print("      LOSOWA WARTOŒÆ:     " + random_tab[i]);
			System.out.print("      DYSTRYBUANTA:    " + distribution_tab[(int) index_tab[i]]);
			System.out.println("    Wartoœæ dopasowania(Restring): " + sum[(int) index_tab[i]]);
		}
		return null;
	}
}