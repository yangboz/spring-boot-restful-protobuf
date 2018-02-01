package info.smartkit.eip.springbootrestfulprotobuf;

import com.baeldung.protobuf.BaeldungTraining;
import com.baeldung.protobuf.CourseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@SpringBootApplication
public class SpringBootRestfulProtobufApplication {

	@Bean
	RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
		return new RestTemplate(Arrays.asList(hmc));
	}

	@Bean
	ProtobufHttpMessageConverter protobufHttpMessageConverter() {
		return new ProtobufHttpMessageConverter();
	}

	@Bean
	public CourseRepository createTestCourses() {
		Map<Integer, BaeldungTraining.Course> courses = new HashMap<>();

		BaeldungTraining.Course course1 = BaeldungTraining.Course.newBuilder().setId(1).setCourseName("REST with Spring").addAllStudent(createTestStudents()).build();

		BaeldungTraining.Course course2 = BaeldungTraining.Course.newBuilder().setId(2).setCourseName("Learn Spring Security").addAllStudent(new ArrayList<>()).build();

		courses.put(course1.getId(), course1);
		courses.put(course2.getId(), course2);

		return new CourseRepository(courses);
	}

	private List<BaeldungTraining.Student> createTestStudents() {
		BaeldungTraining.Student.PhoneNumber phone1 = createPhone("123456", BaeldungTraining.Student.PhoneType.MOBILE);
		BaeldungTraining.Student student1 = createStudent(1, "John", "Doe", "john.doe@baeldung.com", Arrays.asList(phone1));

		BaeldungTraining.Student.PhoneNumber phone2 = createPhone("234567", BaeldungTraining.Student.PhoneType.LANDLINE);
		BaeldungTraining.Student student2 = createStudent(2, "Richard", "Roe", "richard.roe@baeldung.com", Arrays.asList(phone2));

		BaeldungTraining.Student.PhoneNumber phone3_1 = createPhone("345678", BaeldungTraining.Student.PhoneType.MOBILE);
		BaeldungTraining.Student.PhoneNumber phone3_2 = createPhone("456789", BaeldungTraining.Student.PhoneType.LANDLINE);
		BaeldungTraining.Student student3 = createStudent(3, "Jane", "Doe", "jane.doe@baeldung.com", Arrays.asList(phone3_1, phone3_2));

		return Arrays.asList(student1, student2, student3);
	}

	private BaeldungTraining.Student createStudent(int id, String firstName, String lastName, String email, List<BaeldungTraining.Student.PhoneNumber> phones) {
		return BaeldungTraining.Student.newBuilder().setId(id).setFirstName(firstName).setLastName(lastName).setEmail(email).addAllPhone(phones).build();
	}

	private BaeldungTraining.Student.PhoneNumber createPhone(String number, BaeldungTraining.Student.PhoneType type) {
		return BaeldungTraining.Student.PhoneNumber.newBuilder().setNumber(number).setType(type).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestfulProtobufApplication.class, args);
	}
}
