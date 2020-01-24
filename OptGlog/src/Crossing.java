import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Crossing {
	int n, m, chromosomes_tab_rows;
	double pr = 0.5;
	int[][] selected_chromosomes_tab;

	public Crossing() {
		// TODO Auto-generated constructor stub
	}

	public Crossing(int n) {
		this.n = n;
	}

	double get_random(int random) {
		return Math.random() * random;
	}

	int[][] select_chromosoms(int[][] tab, int m) {
		List<Integer> index_number = new ArrayList<Integer>();
		for (int i = 0; i < this.n; i++) {
			double r = get_random(1);
			if (r < pr) {
				index_number.add(i);
			}
		}
		// DODAWANIE LOSOWO OSOBNIKA z wybranych, je¿eli jest niepa¿ysta liczba, to
		// losujemy z ju¿ wczeœniejszych wybranych chromosomów
		/*
		 * if (index_number.size() % 2 != 0) { index_number.add(index_number.get((int)
		 * (Math.random() * index_number.size()))); }
		 */

		// USUWANIE LOSOWO OSBNIKA z wybranych
		if (index_number.size() % 2 != 0) {
			index_number.remove(index_number.get((int) (Math.random() * index_number.size())));
		}
		// System.out.println(index_number.size() + "- randomowa ilosæ wybranych
		// chromosomów");

		// System.out.println("Indexy chromosomów do krzyzowania:");
		for (int i = 0; i < index_number.size(); i++) {
			// System.out.println(index_number.get(i));
		}
		// tworzenie tablicy dla wylosowanych chromosomów
		int[][] selected_chromosomes_tab = new int[index_number.size()][m];
		int rows_of_selected_chromosomes = index_number.size();

		// losowanie chromosomów, aby pary by³y nieposrtowane rosn¹co, tylko wylosowane
		// System.out.println("Mieszanie losowe chromosomów w pary");

		// tablica do przechowywania indeksów, a randomowej kolejnosci
		int[] los_tab_index = new int[rows_of_selected_chromosomes];

		// losowanie indexów(aby by³y randomowe),a nie posrtowane, wstawianie do
		// tablicy, usuawnie z listy indesów
		for (int i = 0; i < rows_of_selected_chromosomes; i++) {
			int random = (int) get_random(index_number.size());
			los_tab_index[i] = index_number.get(random);
			index_number.remove(random);
		}

		// wstawianie do tablicy par chromosomów
		for (int i = 0; i < rows_of_selected_chromosomes; i++) {
			for (int j = 0; j < m; j++) {
				selected_chromosomes_tab[i][j] = tab[los_tab_index[i]][j];
			}
		}
		this.m = m;
		this.chromosomes_tab_rows = rows_of_selected_chromosomes;
		this.selected_chromosomes_tab = selected_chromosomes_tab;
		return selected_chromosomes_tab;
	}

	int[][] one_point_cross() {
		// kopiowanie wartosci tablicy chromosomów do nowej tablicy
		int[][] one_point_cross_tab = new int[chromosomes_tab_rows][m];
		for (int i = 0; i < chromosomes_tab_rows; i++) {
			for (int j = 0; j < m; j++) {
				one_point_cross_tab[i][j] = selected_chromosomes_tab[i][j];
			}
		}

		for (int i = 0; i < chromosomes_tab_rows; i = i + 2) {
			int cross_point = (int) get_random(m);
			System.out.println("Onepoint CROSS: " + cross_point);
			one_point_cross_tab[i][(int) cross_point] = selected_chromosomes_tab[i + 1][(int) cross_point];
			one_point_cross_tab[i + 1][(int) cross_point] = selected_chromosomes_tab[i][(int) cross_point];
		}
		return one_point_cross_tab;
	}

	int[][] two_point_cross() {
		int[][] two_point_cross_tab = new int[chromosomes_tab_rows][m];

		for (int i = 0; i < chromosomes_tab_rows; i++) {
			for (int j = 0; j < m; j++) {
				two_point_cross_tab[i][j] = selected_chromosomes_tab[i][j];
			}
		}
		for (int i = 0; i < chromosomes_tab_rows; i = i + 2) {
			int start_cross_point, end_cross_point;
			int random1 = (int) get_random(m);
			int random2 = (int) get_random(m);

			if (random1 < random2) {
				start_cross_point = random1;
				end_cross_point = random2;

			} else {
				start_cross_point = random2;
				end_cross_point = random1;
			}
			System.out.println("Twopoints CROSS start: " + start_cross_point + " end:" + end_cross_point);

			for (int j = start_cross_point; j < end_cross_point; j++) {
				two_point_cross_tab[i][j] = selected_chromosomes_tab[i + 1][j];
				two_point_cross_tab[i + 1][j] = selected_chromosomes_tab[i][j];
			}
		}
		return two_point_cross_tab;
	}

	int[][] few_point_cross() {
		int[][] few_point_cross_tab = new int[chromosomes_tab_rows][m];

		for (int i = 0; i < chromosomes_tab_rows; i++) {
			for (int j = 0; j < m; j++) {
				few_point_cross_tab[i][j] = selected_chromosomes_tab[i][j];
			}
		}
		for (int i = 0; i < chromosomes_tab_rows; i = i + 2) {
			System.out.println("Few point: ");
			// Jako, ¿e nasz chromosom ma 21 genów to maksymalnie ustawiamy 10 punktów
			// losowania
			int number_of_points = (int) get_random(m / 2);
			if (number_of_points % 2 != 0) {
				number_of_points = number_of_points + 1;
			}
			int[] points_tab = new int[number_of_points];
			System.out.println("Iloœæ punktów: " + number_of_points);
			List<Integer> points = new ArrayList<Integer>();

			for (int index = 0; index < m; index++) {
				points.add(index);
			}
			// tworzenie tablicy, bez powtarzajacych siê punktów krzy¿owania
			for (int index = 0; index < number_of_points; index++) {
				int random = (int) get_random(points.size());
				points_tab[index] = points.get(random);
				points.remove(random);
			}
			Arrays.sort(points_tab);

			for (int index = 0; index < points_tab.length; index++) {
				System.out.print(points_tab[index] + ", ");
			}
			System.out.println();

			for (int j = 0; j < number_of_points; j = j + 2) {
				int start_cross_point = points_tab[j];
				int end_cross_point = points_tab[j + 1];
				for (int k = start_cross_point; k < end_cross_point; k++) {
					few_point_cross_tab[i][k] = selected_chromosomes_tab[i + 1][k];
					few_point_cross_tab[i + 1][k] = selected_chromosomes_tab[i][k];
				}
			}
		}
		return few_point_cross_tab;
	}

	int[][] evenly_cross() {
		int[][] evenly_cross_tab = new int[chromosomes_tab_rows][m];

		System.out.println("Krzy¿owanie równomierne:");
		int[] pattern = new int[m];

		for (int i = 0; i < this.m; i++) {
			pattern[i] = (int) get_random(2);
		}
		System.out.print("Wzorzec: ");
		for (int i = 0; i < this.m; i++) {
			System.out.print(pattern[i]);
		}
		System.out.println(" ");
		System.out.println(" ");

		for (int i = 0; i < chromosomes_tab_rows - 1; i = i + 1) {
			for (int j = 0; j < m; j++) {
				if (pattern[j] == 0) {
					evenly_cross_tab[i][j] = selected_chromosomes_tab[i][j];
					evenly_cross_tab[i + 1][j] = selected_chromosomes_tab[i + 1][j];

				} else {
					evenly_cross_tab[i][j] = selected_chromosomes_tab[i + 1][j];
					evenly_cross_tab[i + 1][j] = selected_chromosomes_tab[i][j];
				}
			}
		}
		return evenly_cross_tab;
	}
}
