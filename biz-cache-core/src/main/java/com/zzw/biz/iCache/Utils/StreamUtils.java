package com.zzw.biz.iCache.Utils;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流 工具类
 *
 * @author qiaolin
 */

public class StreamUtils {

    /**
     * 根据条件分组
     */
    public static <K, V> Map<K, List<V>> group(List<V> list, Function<V, K> keyMapper) {
        notNull(list);
        return list.stream().collect(Collectors.groupingBy(keyMapper));
    }


    /**
     * 以 predicate 对象中的方法进行 filter,然后转成List
     *
     * @return
     */
    public static <T> List<T> filterAndToList(Collection<T> list, Predicate<T> predicate) {
        notNull(list);
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 以 predicate 对象中的方法进行 filter,然后转成Set
     *
     * @return
     */
    public static <T> Set<T> filterAndToSet(Collection<T> list, Predicate<T> predicate) {
        notNull(list);
        return list.stream().filter(predicate).collect(Collectors.toSet());
    }

    /**
     * 以 predicate 对象中的方法进行 filter,然后转成List
     *
     * @return
     */
    public static <T> T filterAndFindFirst(List<T> list, Predicate<T> predicate) {
        notNull(list);
        return list.stream().filter(predicate).findFirst().get();
    }


    /**
     * 以 mapMapper 对象中的方法进行 map,然后转成List
     */
    public static <T, R> List<R> mapAndToList(Collection<T> list, Function<T, R> mapMapper) {
        notNull(list);
        return list.stream().map(mapMapper).collect(Collectors.toList());
    }



    /**
     * 以 mapMapper 对象中的方法进行 map,然后转成List
     */
    public static <T, R> List<R> mapAndToList(T[] array, Function<T, R> mapMapper) {
        notNull(array);
        return Arrays.asList(array).stream().map(mapMapper).collect(Collectors.toList());
    }


    /**
     * 以 mapMapper 对象中的方法进行 map,然后转成List
     */
    public static <T, R> Set<R> mapAndToSet(Collection<T> list, Function<T, R> mapMapper) {
        notNull(list);
        return list.stream().map(mapMapper).collect(Collectors.toSet());
    }


    public static <T, R> Object[] mapAndToArray(Collection<T> list, Function<T, R> mapMapper) {
        notNull(list);
        return list.stream().map(mapMapper).toArray();
    }

    /**
     * 以 mapMapper 对象中的方法进行 map,然后转成Array,使用这个你能获得一个你想要的类型。
     *
     * @return
     */
    public static <T, R> R[] mapAndToArray(Collection<T> list, Function<T, R> mapMapper, IntFunction<R[]> generator) {
        notNull(list);
        return list.stream().map(mapMapper).toArray(generator);
    }


    public static <T, R> Object[] mapAndToArray(T[] array, Function<T, R> mapMapper) {
        notNull(array);
        return Arrays.stream(array).map(mapMapper).toArray();
    }

    /**
     * 以 mapMapper 对象中的方法进行 map,然后转成Array,使用这个你能获得一个你想要的类型。
     *
     * @return
     */
    public static <T, R> R[] mapAndToArray(T[] array, Function<T, R> mapMapper, IntFunction<R[]> generator) {
        notNull(array);
        return Arrays.stream(array).map(mapMapper).toArray(generator);
    }

    /**
     * 转换成 list对象， T为 keyMapper参数中方法的入参，R为为返回值
     */
    public static <R, T> List<R> toList(T[] list, Function<T, R> keyMapper) {
        notNull(list);
        return Stream.of(list).map(keyMapper).collect(Collectors.toList());
    }

    /**
     * 转换成 set对象， T为 keyMapper参数中方法的入参，R为为返回值
     */
    public static <R, T> Set<R> toSet(T[] list, Function<T, R> keyMapper) {
        notNull(list);
        return Stream.of(list).map(keyMapper).collect(Collectors.toSet());
    }

    /**
     * 转换成 list对象， T为 keyMapper参数中方法的入参，R为为返回值
     */
    public static <R, T> List<R> toList(List<T> list, Function<T, R> keyMapper) {
        notNull(list);
        return list.stream().map(keyMapper).collect(Collectors.toList());
    }

    /**
     * 转换成 Map对象， key为 keyMapper参数中方法的返回值，值为本身
     */
    public static <K, V> Map<K, V> toMap(List<V> list, Function<V, K> keyMapper) {
        notNull(list);
        return list.stream().collect(Collectors.toMap(keyMapper, Function.identity()));
    }

    /**
     * 转换成 Map对象， key为 keyMapper参数中方法的返回值，值为本身
     */
    public static <K, V> Map<K, V> toMap(V[] list, Function<V, K> keyMapper) {
        notNull(list);
        return Stream.of(list).collect(Collectors.toMap(keyMapper, Function.identity()));
    }

    /**
     * 转换成 Map对象， key为 keyMapper参数中方法的返回值，值为本身
     */
    public static <K, V> Map<K, V> toMap(List<V> list, Function<V, K> keyMapper, BinaryOperator<V> merger) {
        notNull(list);
        return list.stream().collect(Collectors.toMap(keyMapper, Function.identity(), merger));
    }

    /**
     * 转换成 Map对象， key为 keyMapper参数中方法的返回值，valueMapper参数中方法的返回值
     */
    public static <K, V, VM> Map<K, VM> toMap(List<V> list, Function<V, K> keyMapper, Function<V, VM> valueMapper) {
        notNull(list);
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    /**
     * 转换成 Map对象， key为 keyMapper参数中方法的返回值，valueMapper参数中方法的返回值
     *
     * @param merger 可以使用 {{@link #firstCoverageLast()}}
     */
    public static <K, V, VM> Map<K, VM> toMap(List<V> list, Function<V, K> keyMapper, Function<V, VM> valueMapper, BinaryOperator<VM> merger) {
        notNull(list);
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper, merger));
    }


    /**
     * 第一个覆盖第二个
     *
     * @return
     */
    private static <T> BinaryOperator<T> firstCoverageLast() {
        return (u, v) -> u;
    }

    private static <T> void notNull(Object object) {
        if (object == null) {
            throw new NullPointerException("list 不可为空");
        }
    }

}

