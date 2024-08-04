package sw.boot.begin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Dept {

	@Id
	private Integer deptno;
	private String dname;
	private String loc;
}
