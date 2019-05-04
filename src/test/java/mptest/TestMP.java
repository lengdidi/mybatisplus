package mptest;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ld.config.ApplicationConfig;
import com.ld.entity.Employee;
import com.ld.mapper.EmployeeMapper;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMP {

    ApplicationContext ioc = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    EmployeeMapper emp = (EmployeeMapper) ioc.getBean("employeeMapper");

    @Test
    public void testInsert() {
        Employee employee = new Employee();
        employee.setAge(22);
        employee.setEmail("legend@ld.com");
        employee.setGender(1);
        employee.setLastName("legend");
        emp.insert(employee);
    }

    @Test
    public void testInsertAllColumn() {
        Employee employee = new Employee();
        employee.setAge(22);
        employee.setEmail("legend@ld.com");
//		employee.setGender(1);
        employee.setLastName("legend");
        emp.insert(employee);
    }

    @Test
    public void testDeleteById() {
        emp.deleteById(12);
    }

    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("gender", 3);
        map.put("last_name", "legend4");
        emp.deleteByMap(map);
    }

    @Test
    public void testDelete() {
        emp.delete(new EntityWrapper<Employee>().eq("last_name", "legend3"));
    }

    @Test
    public void testDeleteBatchIds() {
        emp.deleteBatchIds(Arrays.asList(7, 8, 9));
    }

    @Test
    public void testUpdateById() {
        Employee employee = new Employee();
        employee.setId(14);
        employee.setGender(1);
        emp.updateById(employee);
    }

    @Test
    public void testUpdateAllColumnById() {
        Employee employee = new Employee();
        employee.setId(14);
        employee.setGender(2);
        employee.setAge(23);
        employee.setLastName("legend2");
        employee.setEmail("legend2@qq.com");
        emp.updateAllColumnById(employee);
    }

    @Test
    public void testUpdate() {
        Employee employee = new Employee();
        employee.setEmail("legend1@ld.com");
        emp.update(employee, new EntityWrapper<Employee>().eq("id", 13));
    }

    @Test
    public void testUpdateForSet() {
        String setStr = "email = 'legend1@ld.com'";
        emp.updateForSet(setStr, new EntityWrapper<Employee>().eq("last_name", "legend1"));
    }

    @Test
    public void testSelectById() {
        System.err.println(emp.selectById(1));
    }

    @Test
    public void testSelectBatchIds() {
        List<Employee> employees = emp.selectBatchIds(Arrays.asList(1, 2, 3, 4, 5, 6));
        employees.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("gender", 2);
        map.put("last_name", "legend1");
        List<Employee> employees = emp.selectByMap(map);
        employees.forEach(System.out::println);
    }

    @Test
    public void testSelectOne() {
        Employee employee = new Employee();
        employee.setId(16);
        Employee employee1 = emp.selectOne(employee);
        System.out.println(employee1);
    }

    @Test
    public void testSelectCount() {
        System.out.println(emp.selectCount(new EntityWrapper<Employee>().eq("last_name", "legend3")));
    }

    @Test
    public void testSelectList() {
        List<Employee> emps = emp.selectList(new EntityWrapper<Employee>().lt("id", 7));
        emps.forEach(System.out::println);
    }

    @Test
    public void testSelectMaps() {
        List<Map<String, Object>> emps = emp.selectMaps(new EntityWrapper<Employee>().lt("id", 7));
        emps.forEach(System.out::println);
    }

    @Test
    public void testSelectObjs() {
        List<Object> emps = emp.selectObjs(new EntityWrapper<Employee>().lt("id", 7));
        emps.forEach(System.out::println);
    }

    @Test
    public void testSelectPage() {
        RowBounds rowBounds = new RowBounds(2, 2);
        List<Employee> emps = emp.selectPage(rowBounds, new EntityWrapper<Employee>().like("email", ".com"));
        emps.forEach(System.out::println);
    }

    @Test
    public void testSelectMapsPage() {
        RowBounds rowBounds = new RowBounds(2, 2);
        List<Map<String, Object>> emps = emp.selectMapsPage(rowBounds, new EntityWrapper<Employee>().like("email", ".com"));
        emps.forEach(System.out::println);
    }

}
