package edu.hw10.task1;

import edu.hw10.task1.annotation.Max;
import edu.hw10.task1.annotation.Min;
import edu.hw10.task1.annotation.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class RandomObjectGenerator {
    ArgumentGenerator intGenerator = new ArgumentGenerator((r, a) -> {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        for (Annotation annotation : a) {
            if (annotation instanceof Min) {
                min = ((Min) annotation).value();
            } else if (annotation instanceof Max) {
                max = ((Max) annotation).value() + 1;
            }
        }
        return r.nextInt(min, max);
    });
    ArgumentGenerator boolGenerator = new ArgumentGenerator((r, a) -> r.nextBoolean());
    @SuppressWarnings("MagicNumber")
    ArgumentGenerator stringGenerator = new ArgumentGenerator((r, a) -> {
        int min = 0;
        int max = 20;
        boolean mayBeNull = true;
        for (Annotation annotation : a) {
            if (annotation instanceof NotNull) {
                mayBeNull = false;
            } else if (annotation instanceof Min) {
                min = ((Min) annotation).value();
            } else if (annotation instanceof Max) {
                max = ((Max) annotation).value();
            }
        }
        if (mayBeNull && r.nextBoolean()) {
            return null;
        } else {
            char[] string = new char[r.nextInt(min, max + 1)];
            for (int c = 0; c < string.length; c++) {
                string[c] = (char) r.nextInt('А', 'я' + 1);
            }
            return new String(string);
        }
    });
    private final Map<Class<?>, ArgumentGenerator> paramGenerators = Map.of(
        int.class, intGenerator,
        boolean.class, boolGenerator,
        String.class, stringGenerator
    );

    public Object nextObject(Class<?> cl)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = Arrays.stream(cl.getDeclaredConstructors())
            .max(Comparator.comparingInt(Constructor::getParameterCount))
            .orElseThrow(() -> new NoSuchMethodException("Класс %s не имеет конструкторов".formatted(cl)));
        constructor.setAccessible(true);
        return constructor.newInstance(generateParams(constructor.getParameters()));
    }

    public Object nextObject(Class<?> cl, String factoryMethodName)
        throws InvocationTargetException, IllegalAccessException {
        Method method = Arrays.stream(cl.getDeclaredMethods())
            .filter(m -> m.getName().equals(factoryMethodName))
            .max(Comparator.comparingInt(Method::getParameterCount))
            .orElseThrow(() -> new IllegalArgumentException("В классе %s нет метода %s()"
                .formatted(cl, factoryMethodName)));
        if (!method.getReturnType().equals(cl)
            || !Modifier.isPublic(method.getModifiers())
            || !Modifier.isStatic(method.getModifiers())) {
            throw new IllegalArgumentException("Метод %s() в классе %s не является фабричным"
                .formatted(factoryMethodName, cl));
        }
        return method.invoke(null, generateParams(method.getParameters()));
    }

    private Object[] generateParams(Parameter[] params) {
        return Arrays.stream(params)
            .map(p -> paramGenerators.get(p.getType()).next(p.getAnnotations()))
            .toArray();
    }
}
