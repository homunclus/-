package sw.boot.begin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import sw.boot.begin.dto.DeptDTO;
import sw.boot.begin.entity.Dept;
import sw.boot.begin.service.DeptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class DeptController {

	@Autowired
	private DeptService deptService;
	
	@GetMapping("/DeptSelect")
	public String list(Model model) {
		model.addAttribute("list", deptService.findAllDepts());
		return "./dept/dept_select_view";
	}
	
	@GetMapping("/DeptSelectDetail")
	public String detail(Model model, @RequestParam("deptno") Integer deptno) {
		model.addAttribute("deptDTO", deptService.findDeptById(deptno));
		return "./dept/dept_detail_view";
	}
	
	@GetMapping("/DeptInsert")
	public String insert() {
		return "./dept/dept_insert";
	}
	
	@PostMapping("/DeptInsert")
	public String insert(Model model, DeptDTO deptDTO) {
		
		Dept dept = new Dept();
		dept.setDeptno(deptDTO.getDeptno());
		dept.setDname(deptDTO.getDname());
		dept.setLoc(deptDTO.getLoc());
		model.addAttribute("list", deptService.findAllDepts());
		deptService.saveDept(dept);
		
		return "./dept/dept_insert_view";
	}
	
	@GetMapping("/DeptUpdate")
	public String update(Model model, @RequestParam("deptno") Integer deptno) {
		Dept dept = deptService.findDeptById(deptno);
		
		if (dept != null) {
			DeptDTO deptDTO = new DeptDTO();
			deptDTO.setDeptno(dept.getDeptno());
			deptDTO.setDname(dept.getDname());
			deptDTO.setLoc(dept.getLoc());
			model.addAttribute("deptDTO", deptDTO);
		}
		return "./dept/dept_update";
	}
	
	@PostMapping("/DeptUpdate")
	public String update(DeptDTO deptDTO) {
		Dept dept = new Dept();
		dept.setDeptno(deptDTO.getDeptno());
		dept.setDname(deptDTO.getDname());
		dept.setLoc(deptDTO.getLoc());
		deptService.saveDept(dept);
		
		return "./dept/dept_update_view";
	}
	
	@GetMapping("/DeptDelete")
	public String delete() {
		
		return "./dept/dept_delete";
	}
	
	@PostMapping("/DeptDelete")
	public String delete(@RequestParam("deptno") Integer deptno) {
		deptService.deleteDeptById(deptno);
		
		return "./dept/dept_delete_view";
	}
	
	@GetMapping("/DeptSelectView")
	public String selectAllView() {
		return "./dept/dept_ajax";
	}
	
}
