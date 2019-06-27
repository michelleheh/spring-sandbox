package hello;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        cleanDatabase(jdbcTemplate);
    }

    public void cleanDatabase(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.update("DELETE FROM USER");
    }

    @Test
    public void demoAdd() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/demo/add?name=First&email=someemail@someemailprovider.com'").accept(
                MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Saved")));
    }

    @Test
    public void getAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/demo/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

}