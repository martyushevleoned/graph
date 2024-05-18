import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс для описания вершины графа
 */
public class Point {

    /**
     * мапа <Названия соединённой вершины, вес пути до неё>
     */
    private final Map<String, Integer> pathsMap = new LinkedHashMap<>();

    /**
     * добавить вершину
     * @param pointName название вершины
     * @param weight стоимость прохождения до вершины
     */
    public void put(String pointName, int weight) {
        pathsMap.put(pointName, weight);
    }

    public Map<String, Integer> getPathsMap() {
        return pathsMap;
    }
}
