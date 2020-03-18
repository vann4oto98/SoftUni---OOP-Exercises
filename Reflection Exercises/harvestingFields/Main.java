package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);

		Field[] fields = RichSoilLand.class.getDeclaredFields();
		String input = sc.nextLine();
		while (!input.equals("HARVEST")){
			if (input.equals("all")){
				Arrays.stream(fields)
						.forEach(f-> System.out.printf("%s %s %s%n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
			} else {
				String modifier = input;
				Arrays.stream(fields)
						.filter(f-> Modifier.toString(f.getModifiers()).equals(modifier))
						.forEach(f-> System.out.printf("%s %s %s%n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName()));
			}

			input = sc.nextLine();
		}
	}
}
