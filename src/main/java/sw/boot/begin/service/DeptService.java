package sw.boot.begin.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sw.boot.begin.entity.Dept;
import sw.boot.begin.repository.DeptRepository;

@Service
public class DeptService {

	@Autowired
	private DeptRepository deptRepository;
	
	@Transactional
	public Dept saveDept(Dept dept) {
		return deptRepository.save(dept);
	}
	
	@Transactional(readOnly = true)
	public List<Dept> findAllDepts(){
		return deptRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Dept findDeptById(Integer deptno) {
		return deptRepository.findById(deptno).orElse(null);
	}
	
	@Transactional
	public void deleteDeptById(Integer deptno) {
		deptRepository.deleteById(deptno);
	}
	
	public boolean existsByDeptno(Integer deptno) {
		return deptRepository.existsById(deptno);
	}
}
