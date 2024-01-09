package ensam.gcdste.student.controller;

import ensam.gcdste.student.entity.Field;
import ensam.gcdste.student.repository.FieldRepository;
import ensam.gcdste.student.repository.StudentRepository;
import ensam.gcdste.student.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
class FieldController {

    private final Logger log = LoggerFactory.getLogger(FieldController.class);
    @Autowired
    private  FieldRepository fieldRepository;
    private final StudentRepository studentRepository;

    public FieldController(FieldRepository fieldRepository, StudentRepository studentRepository) {
        this.fieldRepository = fieldRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/fields")
    Collection<Field> fields() {
        return fieldRepository.findAll();
    }

    @GetMapping("/field/{id}")
    ResponseEntity<?> getField(@PathVariable Long id) {
        Optional<Field> field = fieldRepository.findById(id);
        return field.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /*@PostMapping("/field")
    ResponseEntity<Field> createField(@Validated @RequestBody Field filed) throws URISyntaxException {
        log.info("Request to create field: {}", filed);
        Field result = fieldRepository.save(filed);
        return ResponseEntity.created(new URI("/api/field/" + result.getId()))
                .body(result);

    }*/
    /*


    @PostMapping("/field")
    ResponseEntity<Field> createField(@Valid @RequestBody Field field) throws URISyntaxException {
        log.info("Request to create field: {}", field);
        try {
            Field result = fieldRepository.save(field);
            log.info("Field created successfully: {}", result);
            return ResponseEntity.created(new URI("/api/field/" + result.getId())).body(result);
        } catch (Exception e) {
            log.error("Error creating field", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
*/
    /*@PostMapping("/field")
    ResponseEntity<Field> createField(@Valid @RequestBody Field field,
                                      @AuthenticationPrincipal OAuth2User principal) throws URISyntaxException {
        log.info("Request to create field: {}", field);
        Map<String, Object> details = principal.getAttributes();
        String userId = details.get("sub").toString();

        // check to see if user already exists
        Optional<Student> student = studentRepository.findById(userId);
        field.setStudent((Set<Student>) student.orElse(new Student(userId, details.get("name").toString(), details.get("email").toString())));

        Field result = fieldRepository.save(field);
        return ResponseEntity.created(new URI("/api/field/" + result.getId()))
                .body(result);
    }*/

    @PostMapping("/field")
    ResponseEntity<Field> createField(@Valid @RequestBody Field field) throws URISyntaxException {
        log.info("Request to create field: {}", field);
        Field result = fieldRepository.save(field);
        return ResponseEntity.created(new URI("/api/field/" + result.getId()))
                .body(result);
    }

    @PutMapping("/field/{id}")
    ResponseEntity<Field> updateField(@Valid @RequestBody Field field) {
        log.info("Request to update group: {}", field);
        Field result = fieldRepository.save(field);
        return ResponseEntity.ok().body(result);
    }
/*
    @PutMapping("/field/{id}")
    ResponseEntity<Field> updateField(@Valid @RequestBody Field field) {
        log.info("Request to update field: {}", field);
        Field result = fieldRepository.save(field);
        return ResponseEntity.ok().body(result);
    }*/

    @DeleteMapping("/field/{id}")
    public ResponseEntity<?> deleteField(@PathVariable Long id) {
        log.info("Request to delete field: {}", id);
        fieldRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
//gg
