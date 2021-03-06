package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.Client;
import cn.wolfcode.edu.mapper.ClientMapper;
import cn.wolfcode.edu.query.ClientQueryObject;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.query.StudentQueryObject;
import cn.wolfcode.edu.query.PageResult;
import cn.wolfcode.edu.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {
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
        if (total == 0) {
            return new PageResult();
        }
        //查询分页数据
        List<Client> rows = clientMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    /**
     * 根据传入的id和状态值 修改状态
     * @param id
     * @param stateId
     */
    public void changeState(Long id,int stateId) {
             Client client = clientMapper.selectByPrimaryKey(id);
         if (stateId==1){
             //转正同时 设置下转正时间
             client.setTransferDate(new Date());

         }
        Date transferDate = client.getTransferDate();
        clientMapper.changeState(id,stateId,transferDate);


    }

    public List<Client> queryClients(StudentQueryObject qo) {
        return clientMapper.queryClients(qo);
    }

    /**
     * 资源池客户的列表显示
     * @param qo
     * @return
     */
    public PageResult queryPoolClient(ClientQueryObject qo) {
        //查询总条数
        int total = clientMapper.queryForPoolClientCount(qo);
        if(total==0){
            return new PageResult();
        }
        //查询分页数据
        List<Client> rows = clientMapper.queryPoolClientList(qo);
        return new PageResult(total, rows);
    }


    @Override
    public List<Client> listStudents() {
        return clientMapper.listStudents();
    }

    @Override
    public void updateByStudent(Client client) {
        clientMapper.updateByStudent(client);
    }

    @Override
    public void changeTrend(Long id) {
        Client client = clientMapper.selectByPrimaryKey(id);
        if (client.getStateId() == 1){
            clientMapper.changeTrend(id,3L);
            return;
        } else if(client.getStateId() == 3){
            clientMapper.changeTrend(id,1L);
            return;
        }
    }
}
