import javax.sql.rowset.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Streams<T> {
    private List<T> collection;

    private int size(){
        return collection.size();
    }
    private Streams(List<T> collection) {
        this.collection = new ArrayList<T>(collection);
    }

    public static <C> Streams<C> of(List<C> list){
        return new Streams<>(list);
    }

    public Streams<T> filter(Predicate<? super T> predicate){
        for (int i = 0; i < this.collection.size(); i++) {
            if(! predicate.test(this.collection.get(i)))
                this.collection.remove(i);
        }
        return this;
    }
    public <R> Streams<R> transform(Function<? super T, ? extends R> mapper){
        List<R> newList = new ArrayList<>();
        for(T t: collection)
            newList.add(mapper.apply(t));
        return new Streams<>(newList);
    }

    public<K, V> Map<K, V> toMap(Function<? super T, ? extends K> mapperKey, Function<? super T, ? extends V> mapperValue){
        Map<K,V> map = new HasMap<>();
        for (T t: collection)
            map.put(mapperKey.apply(t), mapperValue.apply(t));
        return map;
    }
}
