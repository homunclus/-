package sw.boot.begin.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import sw.boot.begin.entity.Dept;
import sw.boot.begin.service.DeptService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequiredArgsConstructor
public class DeptRestController {

	@Autowired
	private DeptService deptService;
	
	@GetMapping("/selectAll")
	public List<Dept> getAllDepts() {
		return deptService.findAllDepts();
	}
	
	@GetMapping("pathect/{deptno}")
	public ResponseEntity<Dept> getDeptById(@PathVariable("deptno") Integer deptno) {
		Dept dept = deptService.findDeptById(deptno);

		if (dept != null) {
			return new ResponseEntity<>(dept, HttpStatus.OK);
		} else {
			
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
  }
	
	@PostMapping("/insert")
	public ResponseEntity<String> createDept(@RequestBody Dept dept) {
		
		if (deptService.existsByDeptno(dept.getDeptno())) {
			return new ResponseEntity<>("부서 번호가 존재합니다.", HttpStatus.BAD_REQUEST);
		}
		Dept dept2 = deptService.saveDept(dept);
		return new ResponseEntity<>("부서가 성공적으로 저장되었습니다.", HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{deptno}")
	public ResponseEntity<Dept> updateDept(@PathVariable("deptno") Integer deptno, @RequestBody Dept deptDetails) {
		Dept dept = deptService.findDeptById(deptno);
		if (dept != null) {
			dept.setDname(deptDetails.getDname());
			dept.setLoc(deptDetails.getLoc());
			
			Dept dept2 = deptService.saveDept(dept);
			return new ResponseEntity<>(dept2, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{deptno}")
	public ResponseEntity<HttpStatus> deleteDept(@PathVariable("deptno") Integer deptno){
		deptService.deleteDeptById(deptno);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
}
