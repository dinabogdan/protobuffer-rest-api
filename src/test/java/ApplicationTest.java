import com.freesoft.protobuf.Application;
import com.freesoft.protobuf.Training;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTest {

    private static final String COURSE_URL = "http://localhost:8080/courses/1";

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void whenUsingRestTemplateThenSucceed() {
        ResponseEntity<Training.Course> course = restTemplate.getForEntity(COURSE_URL, Training.Course.class);
        Assert.assertNotNull(course);
        System.out.println(course);
    }
}
