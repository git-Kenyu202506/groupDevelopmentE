package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.SearchMapper;

@Service
public class SearchService {
    @Autowired
    private SearchMapper mapper;

    public List<Search> search(int id, String name,
                                 Integer minAge, Integer maxAge,
                                 LocalDate startL, LocalDate startR,
                                 LocalDate endL, LocalDate endR) {
        String sL = startL != null ? startL.toString() : null;
        String sR = startR != null ? startR.toString() : null;
        String eL = endL != null ? endL.toString() : null;
        String eR = endR != null ? endR.toString() : null;
      //値がNullじゃなければ文字列にして、NullならそのままNullにして検索条件に含めない
        return mapper.search(id, name, minAge, maxAge, sL, sR, eL, eR);
    }

    public void deleteByIds(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) mapper.deleteByIds(ids);
    }
}

