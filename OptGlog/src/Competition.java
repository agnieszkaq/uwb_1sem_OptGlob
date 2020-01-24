import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Competition {

	int n;
	double[] k_tab, full_n_competition_tab;

	public Competition() {
		// TODO Auto-generated constructor stub
	}

	public Competition(int n) {
		this.n = n;
	}

	int get_random() {
		return (int) (Math.random() * this.n);
	}

	public double[] getKtab(double[] tab, int k) {
		if (k > tab.length) {
			return tab;
		} else
			k_tab = new double[k];

		for (int i = 0; i < k; i++) {
			int random = get_random();
			boolean isExist = false;
			for (int j = 0; j < k; j++) {
				if (k_tab[i] == tab[random]) {
					isExist = true;
				}
			}
			if (isExist == false) {
				k_tab[i] = tab[random];
			}
		}
		return k_tab;
	}

	public double[] get_full_MAX_competition_val(double[] tab, int k) {
		full_n_competition_tab = new double[n];
		for (int i = 0; i < n; i++) {
			full_n_competition_tab[i] = max_val(getKtab(tab, k));
		}
		return full_n_competition_tab;
	}

	public double[] get_full_MIN_competition_val(double[] tab, int k) {
		full_n_competition_tab = new double[n];
		for (int i = 0; i < n; i++) {
			full_n_competition_tab[i] = min_val(getKtab(tab, k));
		}
		return full_n_competition_tab;
	}

	public double max_val(double[] tab) {
		Arrays.sort(tab);
		return tab[tab.length - 1];
	}

	public double min_val(double[] tab) {
		Arrays.sort(tab);
		return tab[1];
	}

}
