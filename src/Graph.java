import java.util.*;

/**
 * Класс описывающий граф
 */
public class Graph {

    /**
     * мапа <название вершины, вершина>
     */
    private final Map<String, Point> points;

    /**
     * Конструктор сделан приватным,
     * чтобы объекты можно было создавать только через методы {@link Graph#oneWayGraph} и {@link Graph#twoWayGraph}
     * @param points мапа с описанием вершин графа
     */
    private Graph(Map<String, Point> points) {
        this.points = points;
    }

    /**
     * Публичный статический конструктор для создания графа с односторонними связями
     * @param edges лист связей
     * @return объект тип {@link Graph}
     */
    public static Graph oneWayGraph(List<Edge> edges) {
        Map<String, Point> map = new HashMap<>();
        edges.forEach(edge -> {
            if (!map.containsKey(edge.firstPointName()))
                map.put(edge.firstPointName(), new Point());
            if (!map.containsKey(edge.secondPointName()))
                map.put(edge.secondPointName(), new Point());

            map.get(edge.firstPointName()).put(edge.secondPointName(), edge.weight());
        });
        return new Graph(map);
    }

    /**
     * Публичный статический конструктор для создания графа с двусторонними связями
     * @param edges лист связей
     * @return объект тип {@link Graph}
     */
    public static Graph twoWayGraph(List<Edge> edges) {
        Map<String, Point> map = new HashMap<>();
        edges.forEach(edge -> {
            if (!map.containsKey(edge.firstPointName()))
                map.put(edge.firstPointName(), new Point());
            if (!map.containsKey(edge.secondPointName()))
                map.put(edge.secondPointName(), new Point());

            map.get(edge.firstPointName()).put(edge.secondPointName(), edge.weight());
            map.get(edge.secondPointName()).put(edge.firstPointName(), edge.weight());
        });
        return new Graph(map);
    }

    /**
     * Метод для поиска всех возможных путей между вершинами графа
     * @param startPointName начальная вершина
     * @param endPointName конечная вершина
     */
    public List<Path> findAllPaths(String startPointName, String endPointName) {

        if (!points.containsKey(startPointName) || !points.containsKey(endPointName))
            throw new RuntimeException("Указаная начальная или конечная точка не существует");

        return find(List.of(new Path(startPointName)), endPointName);
    }

    /**
     * рекурсивная функция для поиска всех возможных путей
     * @param paths список с одним значением - путь с начальной точкой
     * @param endPointName конечная точка
     */
    private List<Path> find(List<Path> paths, String endPointName) {

        List<Path> result = new ArrayList<>();
        paths.forEach(path -> {

            Point point = points.get(path.getLastPointName());
            point.getPathsMap().forEach((pointName, weight) -> {

                if (path.isPossibleToAdd(pointName)) {
                    Path tempPath = path.addPoint(pointName, weight);

                    if (Objects.equals(pointName, endPointName))
                        result.add(tempPath);
                    else
                        result.addAll(find(List.of(tempPath), endPointName));
                }
            });
        });

        return result;
    }


}
