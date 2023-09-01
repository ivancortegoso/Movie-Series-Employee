package com.example.demo.domain.mapper;

import com.example.demo.domain.dto.request.MovieRequest;
import com.example.demo.domain.model.Movie;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateMovie(MovieRequest request, @MappingTarget Movie entity);
}
