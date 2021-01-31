package function;

import java.lang.reflect.InvocationTargetException;

public class data_factory {
    public jdbc createDataManipulation() {
        String name;
        name = "function.jdbc";
        try {
            return (jdbc) Class.forName(name).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
