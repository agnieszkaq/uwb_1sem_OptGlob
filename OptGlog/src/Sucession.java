import java.util.ArrayList;
import java.util.List;

public class Sucession {
	int n, m;
	int[][] copy_tab, new_hp_tab, random_sucession_tab, binary_table;

	public Sucession(int n) {
		this.n = n;
		// TODO Auto-generated constructor stub
	}

	int[][] start_algorithm(int loop_epoka) {

		// _____________________S_E_L_E_K_C_J_A____________________________

		// ______________________RESTRING___________________________________
		Restring r = new Restring(-1, 1, 6, this.n);
		// utworzenie randomowej binarnej tablicy
		binary_table = r.create_binary_tab();

		// _______________________SUKCECJA LOSOWA____________________________

		for (int in = 0; in < loop_epoka; in++) {
			System.out.println("Rastring: tablica binarna");

			this.m = r.get_m();
			r.show_tab(this.binary_table, r.get_m());

			// obliczenie sumy dziesiêtnej
			r.get_sum(this.binary_table);
			r.set_n(this.n);
			r.get_x();

			// utorzenie tablicy dla wartoœæi Rastringa
			double[] restring_val = r.get_function_val();
			System.out.println("RESTRING: Wartoœci funkcji Restringa: ");
			r.show_tab(restring_val);

			// _______________________RANKING_____________________________________

			Ranking ranking = new Ranking(this.n);
			double[] max_tab = ranking.sort_by_max_tab(restring_val);
			double[] min_tab = ranking.sort_by_min_tab(restring_val);

			System.out.println("RANKING: Posortowana tablica funkcji restringa po MAX");
			r.show_tab(ranking.sort_by_max_tab(restring_val));

			System.out.println("RANKING: Posortowana tablica funkcji restringa po MIN");
			r.show_tab(ranking.sort_by_min_tab(restring_val));

			System.out.println("RANKING: Indexy podwójnego losowania");
			r.show_tab(ranking.getIndexTab());

			System.out.println("RANKING: MAX:");
			r.show_tab(ranking.getRankingMaxValues(max_tab));

			System.out.println("RANKING: MIN:");
			r.show_tab(ranking.getRankingMinValues(min_tab));

			int[] min_ranking_tab_by_rastring_index = new int[this.binary_table.length];
			int[] max_ranking_tab_by_rastring_index = new int[this.binary_table.length];
			for (int i = 0; i < this.n; i++) {
				min_ranking_tab_by_rastring_index[i] = r.get_index(ranking.getRankingMinValues(min_tab)[i],
						restring_val);
				max_ranking_tab_by_rastring_index[i] = r.get_index(ranking.getRankingMaxValues(max_tab)[i],
						restring_val);
			}

			// Utowrzenie tablic z indeksami tablicy binarnej, wg. maksymalnej/minimalnej
			// wartoœæi z metody rankingowej
			System.out.println("RANKING: MIN wg. indexów funkcji Rastringa: ");
			r.show_tab(min_ranking_tab_by_rastring_index);

			System.out.println("RANKING: MAX wg. indexów funkcji Rastringa: ");
			r.show_tab(max_ranking_tab_by_rastring_index);

			// ________________UTWORZENIE____TABLIC_____BINARNYCH______________

			int[][] population_tab = new int[n][r.get_m()];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < r.get_m(); j++) {
					// population_tab[i][j] =
					// this.binary_table[min_ranking_tab_by_rastring_index[i]][j];
					population_tab[i][j] = this.binary_table[max_ranking_tab_by_rastring_index[i]][j];
				}
			}
			// System.out.println("RANKING: tablica binarna po MAX");
			// r.show_tab(population_tab, r.get_m());

			System.out.println("RANKING: tablica binarna po MIN");
			r.show_tab(population_tab, r.get_m());

			// ____________O_P_E_R_A_T_R_Y______G_E_N_E_T_Y_C_Z_N_E_____________

			// ____________________________MUTACJA_______________________________

			int[][] binary_mutation_tab = new int[n][r.get_m()];

			// WZIÊCIE TYLKO WARTOŒCI MINIMALNYCH
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < r.get_m(); j++) {
					// ZMIANA NA MAX:
					// binary_mutation_tab[i][j] = binary_tab_after_max_ranking[i][j];
					binary_mutation_tab[i][j] = population_tab[i][j];
				}
			}

			Mutation mutation = new Mutation(n);
			int[][] mutacja_tab = mutation.create_classic(binary_mutation_tab, r.get_m());

			System.out.println("MUTACJIA:");
			r.show_tab(mutacja_tab, r.get_m());

			// ____________________________INWERSJA_______________________________

			int[][] binary_inversion_tab = new int[n][r.get_m()];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < r.get_m(); j++) {
					binary_inversion_tab[i][j] = population_tab[i][j];
				}
			}
			int[][] inwersja_tab = mutation.create_inversion(binary_inversion_tab, r.get_m());
			System.out.println("INWERSJA:");
			r.show_tab(inwersja_tab, r.get_m());

			// ____________________________KRZY¯OWANIE_______________________________

			Crossing cross = new Crossing(n);
			int[][] crossing_tab = cross.select_chromosoms(population_tab, r.get_m());
			System.out.println("KRZY¯OWANIE:");
			r.show_tab(cross.one_point_cross(), r.get_m());

			// _____________________TOWRZENIE_TABLICY_KOPII___________________________

			int copy_tab_lenght = mutacja_tab.length + inwersja_tab.length + crossing_tab.length;
			int[][] copy_tab = new int[copy_tab_lenght][r.get_m()];

			for (int i = 0; i < mutacja_tab.length; i++) {
				for (int j = 0; j < r.get_m(); j++) {
					copy_tab[i][j] = mutacja_tab[i][j];
				}
			}

			for (int i = 0; i < inwersja_tab.length; i++) {
				for (int j = 0; j < r.get_m(); j++) {
					copy_tab[i + mutacja_tab.length][j] = inwersja_tab[i][j];
				}
			}

			for (int i = 0; i < crossing_tab.length; i++) {
				for (int j = 0; j < r.get_m(); j++) {
					copy_tab[i + mutacja_tab.length + inwersja_tab.length][j] = crossing_tab[i][j];
				}
			}

			System.out.println("KOPIA:");
			for (int i = 0; i < copy_tab_lenght; i++) {
				for (int j = 0; j < r.get_m(); j++) {
					System.out.print(copy_tab[i][j]);
				}
				System.out.println();
			}
			this.copy_tab = copy_tab;

			// ___________________SUKCESJA Z zastepowaniem_____________________

			int[][] half_p_tab = new int[this.binary_table.length / 2][r.get_m()];
			int[][] half_k_tab = new int[copy_tab_lenght / 2][r.get_m()];

			List<Integer> half_p_list = new ArrayList<Integer>();
			for (int i = 0; i < this.binary_table.length; i++) {
				half_p_list.add(i);
			}

			List<Integer> half_k_list = new ArrayList<Integer>();
			for (int i = 0; i < this.copy_tab.length; i++) {
				half_k_list.add(i);
			}
			int k_tab_size = half_k_list.size();
			int p_tab_size = half_p_list.size();

			// Usuwanie losowo chromosomów
			for (int i = 0; i < k_tab_size / 2; i++) {
				half_k_list.remove(half_k_list.get((int) (Math.random() * half_k_list.size())));
			}
			System.out.println("P" + p_tab_size);
			// Usuwanie losowo chromosomów
			for (int i = 0; i < p_tab_size / 2; i++) {
				half_p_list.remove(half_p_list.get((int) (Math.random() * half_p_list.size())));
			}

			System.out.println();
			System.out.println("POPULACJA");
			r.show_tab(this.binary_table, r.get_m());

			int size_hp_tab = half_k_list.size() + half_p_list.size();
			int size_K_tab = half_k_list.size();
			int size_P_tab = half_p_list.size();
			int[][] new_hp_tab = new int[size_hp_tab][r.get_m()];

			for (int i = 0; i < size_K_tab; i++) {
				for (int j = 0; j < this.m; j++) {
					new_hp_tab[i][j] = copy_tab[half_k_list.get(i)][j];
				}
			}

			for (int i = 0; i < size_P_tab; i++) {
				for (int j = 0; j < this.m; j++) {
					new_hp_tab[i + size_K_tab][j] = binary_table[half_p_list.get(i)][j];
				}
			}

			System.out.println("POL 50 kopii 50 populacji");
			r.show_tab(new_hp_tab, r.get_m());

			this.new_hp_tab = new_hp_tab;
			this.binary_table = new_hp_tab;
			this.n = this.binary_table.length;
		}

		this.binary_table = this.new_hp_tab;
		return null;
	}
}
