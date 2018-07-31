import com.freesoft.protobuf.Application;
import com.freesoft.protobuf.Training;
import com.googlecode.protobuf.format.JsonFormat;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

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

    @Test
    public void whenUsingHttpClientThenSucceed() throws IOException {
        InputStream responseStream = executeHttpRequest(COURSE_URL);
        String jsonOutput = convertProtobufMessageStreamToJsonString(responseStream);
        System.out.println(jsonOutput);
        Assert.assertNotNull(jsonOutput);
    }

    private InputStream executeHttpRequest(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        return response.getEntity().getContent();
    }

    private String convertProtobufMessageStreamToJsonString(InputStream inputStream) throws IOException {
        JsonFormat jsonFormat = new JsonFormat();
        Training.Course course = Training.Course.parseFrom(inputStream);
        return jsonFormat.printToString(course);
    }
}
