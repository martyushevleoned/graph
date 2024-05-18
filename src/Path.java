import java.util.ArrayList;
import java.util.List;

/**
 * Класс для описания пути в графе
 */
public class Path {

    /**
     * Суммарный вес пути
     */
    private final int weight;
    /**
     * Список пройденных фершин
     */
    private final List<String> points;

    /**
     * @param startPoint - начальная точка маршрута
     */
    public Path(String startPoint) {
        this.points = new ArrayList<>();
        points.add(startPoint);
        weight = 0;
    }

    /**
     * приватный конструктор для метода {@link Path#addPoint}
     */
    private Path(List<String> points, int weight) {
        this.points = points;
        this.weight = weight;
    }

    /**
     * текущая точка маршрута
     */
    public String getLastPointName() {
        return points.getLast();
    }

    /**
     * Создаёт копию пути с уже добавленной вершиной
     * @param pointName название вершины
     * @param pointWeight вес прохождения до вершины
     */
    public Path addPoint(String pointName, int pointWeight) {
        List<String> p = new ArrayList<>(List.copyOf(points));
        p.add(pointName);
        return new Path(p, weight + pointWeight);
    }

    /**
     * Этот метод стоит переписать в зависимости от ТЗ<br>
     * Метод определяет можно ли пройти по указанной вершине<br>
     * Сейчас переход к вершине разрешён,
     * если путь ещё не проходил через указанную вершину
     * @param pointName название вершины
     */
    public boolean isPossibleToAdd(String pointName) {
        return !points.contains(pointName);
    }

    /**
     * Метод для красивого вывода
     */
    public void printPath() {
        System.out.print("weight: " + weight);
        System.out.print("\t" + points.getFirst());
        points.stream().skip(1).forEach(p -> System.out.print(" -> " + p));
        System.out.println();
    }

    public int getWeight() {
        return weight;
    }
}
