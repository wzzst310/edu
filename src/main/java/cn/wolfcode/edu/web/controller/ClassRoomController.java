package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.domain.ClassRoom;
import cn.wolfcode.edu.query.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.IClassRoomService;
import cn.wolfcode.edu.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("classRoom")
public class ClassRoomController {

    @Autowired
    private IClassRoomService classRoomService;

    @RequestMapping("")
    public String index() {
        return "classRoom/list";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject qo){
        return classRoomService.query(qo);
    }

    @RequestMapping("save")
    @ResponseBody
    public JsonResult save(ClassRoom classRoom) {
        JsonResult result = new JsonResult();
        try {
            classRoomService.save(classRoom);
        } catch (Exception e) {
            e.printStackTrace();
            result.markMsg("保存失败");
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public JsonResult update(ClassRoom classRoom) {
        JsonResult result = new JsonResult();
        try {
            classRoomService.update(classRoom);
        } catch (Exception e) {
            e.printStackTrace();
            result.markMsg("更新失败");
        }
        return result;
    }

    @RequestMapping("changeState")
    @ResponseBody
    public JsonResult changeState(Long id) {
        System.out.println("111");
        JsonResult result = new JsonResult();
        try {
            classRoomService.changeState(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.markMsg("更新失败");
        }
        return result;
    }
}
