/**
 * Класс для описания соединения вершин
 * @param firstPointName имя первой вершины
 * @param secondPointName имя второй вершины
 * @param weight вес перехода между ними
 */
public record Edge (String firstPointName, String secondPointName, int weight) {
}
