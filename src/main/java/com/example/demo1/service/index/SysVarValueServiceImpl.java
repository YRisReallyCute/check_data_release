package com.example.demo1.service.index;

import com.example.demo1.repository.index.SysVarValueDao;
import org.springframework.stereotype.Service;

@Service
public class SysVarValueServiceImpl implements SysVarValueService {

    private final SysVarValueDao sysVarValueDao;

    public SysVarValueServiceImpl(SysVarValueDao sysVarValueDao) {
        this.sysVarValueDao = sysVarValueDao;
    }

    @Override
    public String getValue(String key) {
        String value = null;
        if(sysVarValueDao.findById(key).isPresent()){
            value = sysVarValueDao.findById(key).get().getValue();
        }
        return value;
    }
}
