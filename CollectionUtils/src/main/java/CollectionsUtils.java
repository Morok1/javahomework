import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<T> limit(List<? extends T> source, int size) {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            list.add(source.get(i));
        }
        return list;
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        for (T e : c2)
            if (c1.contains(e))
                return true;
        return false;
    }

    public static <T extends Comparable<T>> List<T> range(List<? extends T> list, T min, T max) {
        List<T> returnList = new ArrayList<>();
        for (T e : list) {
            if ((e.compareTo(min) >= 0) && (e.compateTo(max)) <= 0) {
                returnList.add(e);
            }
        }
        Collections.sort(returnList, Comparator.naturalOrder());
        return returnList;
    }

    public static <T> List range(List<? extends T> list, T min, T max, Comparator<T> comparator) {
        List<T> returnList = new ArrayList<T>();
        for (T e : list) {
            if ((comparator.compare(e, min) >= 0) && (comparator.compare(e, max) <= 0)) {
                returnList.add(e);
            }

        }
        Collections.sort(returnList, comparator);
        return returnList;
    }
}
