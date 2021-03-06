import java.util.Map;

public class CountMapReal<T> implements CountMap<T> {
    Map<T, Integer> countMap;


    @Override
    public void add(T o) {
        Integer count = 1;
            count += countMap.get(o);
        countMap.put(o, count);
    }

    @Override
    public int getCount(T o) {
        Integer count = countMap.get(o);
        return count == null ? 0 : count;
    }

    @Override
    public int remove(T o) {
        Integer count = countMap.remove(o);
        return  count == null ? 0 : count;
    }

    @Override
    public int size() {
        return countMap.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        Map<T, Integer> src = source.toMap();
        for (T key : src.keySet()) {
            Integer count = src.get(key);
            count += getCount(key);
            countMap.put(key, count);
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return countMap;
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        for(T key: countMap.keySet()){
            Integer count = countMap.get(key);
            if(destination.containsKey(key)) count += destination.get(key);
                destination.put(key, count);
        }
    }

}

