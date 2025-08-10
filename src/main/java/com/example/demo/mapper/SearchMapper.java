package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.core.annotation.MergedAnnotations.Search;

@Mapper
public interface SearchMapper {
	//社員情報検索
    @SelectProvider(type=SearchSqlProvider.class, method="buildSearchSql")
    @Results({
        @Result(property = "startDate", column = "start_date"),
        @Result(property = "endDate", column = "end_date")
    })
    List<Search> search(@Param("id") int id,
                          @Param("name") String name,
                          @Param("minAge") Integer minAge,
                          @Param("maxAge") Integer maxAge,
                          @Param("startL") String startL,
                          @Param("startR") String startR,
                          @Param("endL") String endL,
                          @Param("endR") String endR);

    @Delete({"<script>",
             "DELETE FROM employee WHERE id IN",
             "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
             "#{id}",
             "</foreach>",
             "</script>"})
    void deleteByIds(@Param("ids") List<Long> ids);
}
