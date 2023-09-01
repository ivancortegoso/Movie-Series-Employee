package com.example.demo.domain.mapper;

import com.example.demo.domain.dto.request.SeriesRequest;
import com.example.demo.domain.model.Series;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public abstract class SeriesMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateSeries(SeriesRequest request, @MappingTarget Series entity);
}
