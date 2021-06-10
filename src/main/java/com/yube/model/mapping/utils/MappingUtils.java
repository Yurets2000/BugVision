package com.yube.model.mapping.utils;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MappingUtils {

    public static <T, S> List<T> mapAllList(List<S> source, Function<? super S, ? extends T> mapper) {
        return source.stream().map(mapper).collect(Collectors.toList());
    }

    public static <T, S> Set<T> mapAllSet(Set<S> source, Function<? super S, ? extends T> mapper) {
        return source.stream().map(mapper).collect(Collectors.toSet());
    }
}
