package cn.wolfcode.edu.service.impl;

import cn.wolfcode.edu.domain.ClassRoom;
import cn.wolfcode.edu.mapper.ClassRoomMapper;
import cn.wolfcode.edu.query.ClassRoomQueryObject;
import cn.wolfcode.edu.query.PageResult;
import cn.wolfcode.edu.service.IClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomServiceImpl implements IClassRoomService{
    @Autowired
    private ClassRoomMapper classRoomMapper;

    public void save(ClassRoom classroom) {

        //设置初始状态为可用
        classroom.setState(ClassRoom.STATE_NORMAL);

        classRoomMapper.insert(classroom);
    }

    public void update(ClassRoom classroom) {
        classRoomMapper.updateByPrimaryKey(classroom);
    }

    public void delete(Long id) {
        classRoomMapper.deleteByPrimaryKey(id);
    }

    public ClassRoom get(Long id) {
        return classRoomMapper.selectByPrimaryKey(id);
    }

    public List<ClassRoom> list() {
        return classRoomMapper.selectAll();
    }


    public PageResult query(ClassRoomQueryObject qo) {
        int total = classRoomMapper.queryForCount(qo);
        if(total ==0){
            return new PageResult();
        }
        List<ClassRoom> rows = classRoomMapper.queryForList(qo);
        return new PageResult(total,rows);
    }

    public void changeState(Long id) {
        classRoomMapper.changeState(id);
    }

}
