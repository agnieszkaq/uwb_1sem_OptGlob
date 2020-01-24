
public class Restring {

	double a, b, d;
	int n;
	int[][] binary_table;
	double[] sum_tab;
	double[] xrestring_tab;
	int m;

	public Restring() {
	}

	public Restring(double a, double b, double d, int n) {
		this.a = a;
		this.b = b;
		this.d = d;
		this.n = n;
	}

	double middle_number() {
		return (this.b - this.a) * Math.pow(10, this.d);
	}

	int get_m() {
		int counter_m = 0;
		while (middle_number() > Math.pow(2, counter_m)) {
			counter_m = counter_m + 1;
		}
		this.m = counter_m;
		return counter_m;
	}

	int[][] create_binary_tab() {
		int m = get_m();
		binary_table = new int[this.n][m];

		for (int i = 0; i < this.n; i++) {
			for (int j = m - 1; j >= 0; j--) {
				int liczba = (int) Math.floor(Math.random() * 2);
				binary_table[i][j] = liczba;
			}
		}
		return binary_table;
	}

	double[] get_sum(int[][] binary_table2) {
		double[] sum_tab = new double[binary_table2.length];
		for (int i = 0; i < binary_table2.length; i++) {
			int sum = 0;
			for (int j = get_m() - 1; j >= 0; j--) {
				int liczba = (int) Math.floor(Math.random() * 2);
				sum = (int) (sum + (liczba * Math.pow(2, j)));
			}
			sum_tab[i] = sum;
		}
		this.sum_tab = sum_tab;
		return sum_tab;
	}

	public void set_n(int n) {
		this.n = n;
	}

	double[] get_x() {
		double l = this.b - this.a;
		double[] xrestring_tab = new double[this.n];
		for (int i = 0; i < this.n; i++) {
			xrestring_tab[i] = (double) (a + ((l) * this.sum_tab[i] / (Math.pow(2, get_m()) - 1)));
		}
		this.xrestring_tab = xrestring_tab;
		return xrestring_tab;
	}

	double[] get_function_val() {
		double[] function_val = new double[this.n];
		for (int i = 0; i < this.n; i++) {
			function_val[i] = 10 + Math.pow(xrestring_tab[i], 2) - 10 * Math.cos(20 * Math.PI * xrestring_tab[i]);
		}
		return function_val;
	}

	void show_tab(double tab[]) {
		for (int i = 0; i < tab.length; i++) {
			System.out.print("-- " + i + " --   ");
			System.out.println(tab[i]);
		}
		System.out.println();
	}

	void show_tab(int tab[]) {
		for (int i = 0; i < tab.length; i++) {
			System.out.print("-- " + i + " --   ");
			System.out.println(tab[i]);
		}
		System.out.println();
	}

	void show_tab(int[][] tab, int m) {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < m; j++) {

				System.out.print(tab[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	int get_index(double number, double[] tab) {
		int index = 0;
		for (int i = 0; i < n; i++) {
			if (tab[i] == number)
				index = i;
		}
		return index;
	}

}