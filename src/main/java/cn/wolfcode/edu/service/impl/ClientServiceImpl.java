package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Client;
import cn.wolfcode.edu.mapper.ClientMapper;
import cn.wolfcode.edu.query.ClientQueryObject;
import cn.wolfcode.edu.query.PageResult;
import cn.wolfcode.edu.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService{
    @Autowired
    private ClientMapper clientMapper;

    public void delete(Long id) {
        clientMapper.deleteByPrimaryKey(id);
    }

    public void save(Client record) {
        clientMapper.insert(record);
    }

    public Client get(Long id) {
        return clientMapper.selectByPrimaryKey(id);
    }

    public List<Client> list() {
        return clientMapper.selectAll();
    }

    public void update(Client record) {
        clientMapper.updateByPrimaryKey(record);
    }

    public PageResult query(ClientQueryObject qo) {
        //查询总条数
        int total = clientMapper.queryForCount(qo);
        if(total==0){
            return new PageResult();
        }
        //查询分页数据
        List<Client> rows = clientMapper.queryForList(qo);
        return new PageResult(total, rows);
    }
    @Override
    public List<Client> queryClients() {
        return clientMapper.queryClients();
    }
}