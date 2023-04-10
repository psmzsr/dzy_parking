package com.parking.mapper;

import com.parking.entity.NumberPrefixInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NumberPrefixMapper {
    @Select("SELECT * FROM number_prefix")
    List<NumberPrefixInfo> getPrefix();
}
