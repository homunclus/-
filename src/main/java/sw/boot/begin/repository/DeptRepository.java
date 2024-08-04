package sw.boot.begin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.boot.begin.entity.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer>{

}
