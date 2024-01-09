package ensam.gcdste.student;


import ensam.gcdste.student.entity.Event;
import ensam.gcdste.student.entity.Field;
import ensam.gcdste.student.repository.FieldRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final FieldRepository fieldRepository;

    public Initializer(FieldRepository repository) {
        this.fieldRepository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("GS", "GI", "GE",
                "GIL").forEach(name ->
                fieldRepository.save(new Field(name))
        );

        Field GS = fieldRepository.findByName("GS");
        Event e = Event.builder().title("Exam-M-LATIF")
                .description("WSN and IOT")
                .date(Instant.parse("2023-12-30T11:00:00.498+01:00"))
                .build();
        GS.setEvents(Collections.singleton(e));
        fieldRepository.save(GS);

        fieldRepository.findAll().forEach(System.out::println);
    }
}
