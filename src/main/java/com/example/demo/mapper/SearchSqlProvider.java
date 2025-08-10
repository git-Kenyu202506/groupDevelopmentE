package com.example.demo.mapper;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class SearchSqlProvider {
    public String buildSearchSql(Map<String,Object> p) {
        return new SQL(){{
            SELECT("id, name, age, start_date, end_date");
            FROM("employee");

            if (p.get("id") != null) {
                WHERE("id = #{id}");
            }
            if (p.get("name") != null && !((String)p.get("name")).isEmpty()) {
                WHERE("name LIKE CONCAT('%', #{name}, '%')");
            }
            if (p.get("minAge") != null) {
                WHERE("age >= #{minAge}");
            }
            if (p.get("maxAge") != null) {
                WHERE("age <= #{maxAge}");
            }
            if (p.get("startL") != null && !((String)p.get("startL")).isEmpty()) {
                WHERE("start_date >= #{startL}");
            }
            if (p.get("startR") != null && !((String)p.get("startR")).isEmpty()) {
                WHERE("start_date <= #{startR}");
            }
            if (p.get("endL") != null && !((String)p.get("endL")).isEmpty()) {
                WHERE("(end_date IS NOT NULL AND end_date >= #{endL})");
            }
            if (p.get("endR") != null && !((String)p.get("endR")).isEmpty()) {
                WHERE(" (end_date IS NULL OR end_date <= #{endR})");
            }
        }}.toString();
    }
}
