package control;

import java.io.IOException;
import java.util.Scanner;

import algorithm.AntStrategy;
import algorithm.CityPerCity;
import algorithm.EdgePerEdge;
import model.TspMap;
import input.ReadInput;

public class Main {

	public static void main(String[] args) {
		ReadInput ri = new ReadInput(args[0]);
		TspMap tm = null;
		try {
			tm = ri.getTspMap();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out
					.println("Something with the input file is Wrong! (Array Index Out of Bounds");
			return;
		} catch (IOException e) {
			System.out.println("Input Output Exception");
			return;
		}

		System.out.println(tm.toStringDistances());
		System.out.println(tm);

		showMenu(tm);

	}

	private static void showMenu(TspMap tm) {

		String input = "";
		AntStrategy asE = new EdgePerEdge(new TspMap(tm));
		AntStrategy asC = new CityPerCity(new TspMap(tm));

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("-----------------------");
			System.out.println("Please choose an option:");
			System.out.println("(q) Quit");
			System.out.println("(1) City per City strategy");
			System.out.println("(2) Edge per Edge strategy");
			System.out.println("(3) Delete current City per City strategy");
			System.out.println("(4) Delete current Edge per Edge strategy");
			System.out.println("-----------------------");

			input = sc.next();

			switch (input) {
			case "q":
				return;
			case "1":
				showMenu(asC);
				break;
			case "2":
				showMenu(asE);
				break;
			case "3":
				asC = new CityPerCity(new TspMap(tm));
				System.out.println("Current City per City strategy deleted");
				break;
			case "4":
				asE = new EdgePerEdge(new TspMap(tm));
				System.out.println("Current Edge per Edge strategy deleted");
				break;
			default:
				System.out.println("no valid input!");
				break;
			}
		}
	}
	
	private static void showMenu(AntStrategy as) {

		String input = "";

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("-----------------------");
			System.out.println("Please choose an option:");
			System.out.println("(q) Back to main menu");
			System.out.println("(1) Do some iterations");
			System.out.println("(2) Show current best solution");
			System.out.println("(3) Show current pheromone table");
			System.out.println("(4) Show number of iterations, computation time, iteration with best result");
			System.out.println("-----------------------");

			input = sc.next();

			switch (input) {
			case "q":
				return;
			case "1":
				System.out.println("Please enter the number of iterations you want to perform:");
				int n = 0;
				while(true){
					String s = sc.next();
					try{
						n = Integer.parseInt(s);
						break;
					}catch(NumberFormatException e){
						System.out.println("Please enter an integer.");
					}
				}
				System.out.println("do "+ n + " iterations:");
				as.doIterations(n);
				break;
			case "2":
				System.out.println(as.getCurrentBestSolution());
				break;
			case "3":
				System.out.println("Current pheronome table: ");
				System.out.println(as.getTspMap());
				break;
			case "4":
				System.out.println("Number of iterations: " + as.getIterations());
				long time = as.getTime();
				long milliseconds = time%1000;
				long seconds = (time/1000)%60;
				long minutes = ((time/1000)/60)%60;
				long hours = ((time/1000)/60)/60;
				System.out.println("Computation time: " + hours + " h " + minutes + " m " + seconds + " s " + milliseconds + " ms");
				System.out.println("First iteration with the best result (-1 means best result not found): " + as.getFirstIterationWithBestResult());
				break;
			default:
				System.out.println("no valid input!");
				break;
			}
		}
	}

}
