package cn.wolfcode.edu.mapper;

import cn.wolfcode.edu.domain.DataDictionary;
import cn.wolfcode.edu.query.QueryObject;

import java.util.List;

public interface DataDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataDictionary record);

    DataDictionary selectByPrimaryKey(Long id);

    List<DataDictionary> selectAll();

    int updateByPrimaryKey(DataDictionary record);

    int queryForCount(QueryObject qo);

    List<DataDictionary> queryForList(QueryObject qo);
}