package com.parking.service;

import com.parking.entity.NumberPrefixInfo;
import com.parking.mapper.NumberPrefixMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberPrefixService {
    @Autowired
    private NumberPrefixMapper numberPrefixMapper;

    public List<NumberPrefixInfo> getPrefix() {
        return numberPrefixMapper.getPrefix();
    }
}
