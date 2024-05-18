import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Graph graph = Graph.oneWayGraph(List.of(
                new Edge("A", "B", 1),
                new Edge("A", "C", 2),
                new Edge("A", "D", 4),
                new Edge("B", "C", 3),
                new Edge("B", "D", 4),
                new Edge("C", "D", 5)
        ));

        graph.findAllPaths("A", "D").stream()
                .sorted(Comparator.comparing(Path::getWeight))
                .forEach(Path::printPath);
    }
}