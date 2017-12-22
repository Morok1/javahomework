import java.util.Map;

public interface CountMap<T> {
    /*Добавляет элемент в контейнер*/
    void add(T o);


    /*Возвращает количество добавлений данного элемента*/
    int getCount(T o);

    /* удаляет элемент*/
    int remove(T o);

    /*количество разых элементов*/
    int size();
    /*Добавить все элементы из source в текущий контейнер*/
    void addAll(CountMap<T> source);

    /*Вернуть в Map*/
    Map<T, Integer> toMap();

    /*Тот же самый контракт как и toMap(), только всю информацию записать в destination*/

    void toMap(Map<T, Integer> destination);

}