package info.smartkit.eip.springbootrestfulprotobuf.controllers;

import com.baeldung.protobuf.CourseRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.protobuf.BaeldungTraining.Course;

@RestController
@RequestMapping("/courses")
@Api(value="courses", description="Operations pertaining to course in protobuf")
public class CourseController {

    @Autowired
    CourseRepository courseRepo;

    @RequestMapping("/{id}")
    Course customer(@PathVariable Integer id) {
        return courseRepo.getCourse(id);
    }
}
