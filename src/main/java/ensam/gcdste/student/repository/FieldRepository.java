package ensam.gcdste.student.repository;

import ensam.gcdste.student.entity.Field;
import ensam.gcdste.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository  extends JpaRepository<Field, Long> {
    Field findByName(String name);
    List<Field> findAllByStudentId(String id);

}
