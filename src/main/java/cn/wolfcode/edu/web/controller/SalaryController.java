package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.domain.Job;
import cn.wolfcode.edu.domain.Salary;
import cn.wolfcode.edu.query.PageResult;
import cn.wolfcode.edu.query.QueryObject;
import cn.wolfcode.edu.service.ISalaryService;
import cn.wolfcode.edu.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("salary")
public class SalaryController {
    @Autowired
    private ISalaryService salaryService;

    @RequestMapping("")
    public String index() {
        return "salary/list";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject qo) {
        PageResult result = salaryService.query(qo);
        List<Salary> rows = (List<Salary>) result.getRows();
        for (Salary row : rows) {
            Job job = row.getEmployee().getJob();
            BigDecimal baseSalary = job.getBaseSalary();//基本工资
            BigDecimal accumulationFund = job.getAccumulationFund();//公积金缴存基数
            BigDecimal proportion = job.getProportion();//比例
            BigDecimal bonus = job.getBonus();//奖金
            BigDecimal coefficient = job.getCoefficient();//奖金系数
            BigDecimal socialInsurance = job.getSocialInsurance();//社保
            BigDecimal overtimeAllowance = job.getOvertimeAllowance();//加班补贴
            BigDecimal salary = baseSalary.subtract(accumulationFund.multiply(proportion)).subtract(socialInsurance).add(bonus.multiply(coefficient)).add(overtimeAllowance);
            row.setRealWages(salary);
            salaryService.updateByPrimaryKey(row);
        }
        return result;
    }


    @RequestMapping("selectAll")
    @ResponseBody
    public List<Salary> selectAll() {
        List<Salary> salarys = salaryService.selectAll();
        return salarys;
    }



    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Salary record) {
        JsonResult result = new JsonResult();
        try {
            salaryService.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
            result.markMsg("保存失败");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(Salary salary) {
        JsonResult result = new JsonResult();
        try {
            salaryService.updateByPrimaryKey(salary);
        } catch (Exception e) {
            e.printStackTrace();
            result.markMsg("更新失败");
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        JsonResult result = new JsonResult();
        try {
            salaryService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            result.markMsg("删除失败");
        }
        return result;
    }
}
