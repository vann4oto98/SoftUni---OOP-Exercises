package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
       private static  Map<String, Method> methods;
    static {
        methods = initialize();
    }

    private static Map<String, Method> initialize() {
        Class clazz = BlackBoxInt.class;
        Map<String, Method> methods = new HashMap<>();
        Arrays.stream(clazz.getDeclaredMethods()).forEach(method -> {
            method.setAccessible(true);
            methods.putIfAbsent(method.getName(), method);
        });
        return methods;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        Scanner sc = new Scanner (System.in);
        Constructor<BlackBoxInt> blackBoxIntConstructor = BlackBoxInt.class.getDeclaredConstructor(int.class);
        blackBoxIntConstructor.setAccessible(true);
        BlackBoxInt blackBoxInt = blackBoxIntConstructor.newInstance(0);
        String input = sc.nextLine();
        while(!input.equals("END")){
            String[] nameAndValue = input.split("_");
            Method method = methods.get(nameAndValue[0]);
            int value = Integer.parseInt(nameAndValue[1]);
            method.invoke(blackBoxInt, value);

            Field field = blackBoxInt.getClass().getDeclaredField("innerValue");
            field.setAccessible(true);
            System.out.println(field.get(blackBoxInt));
            input = sc.nextLine();
        }
    }
}
