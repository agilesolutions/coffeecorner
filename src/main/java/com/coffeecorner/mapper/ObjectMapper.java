package com.coffeecorner.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface ObjectMapper <O,D>{
    O toObject(D dto);

    D toDto(O object);

    default List<O> toObjects(List<D> dtos) {
        return dtos.stream().map(this::toObject).collect(Collectors.toList());
    }

    default List<D> toDtos(List<O> objects) {
        return objects.stream().map(this::toDto).collect(Collectors.toList());
    }

}
