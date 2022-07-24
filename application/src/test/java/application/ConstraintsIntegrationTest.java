package application;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jaguniek.SpringAppApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { SpringAppApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConstraintsIntegrationTest {

	//@MockBean
	//private ExternalProductService/Database service/database;

	 //@Test
	 //void checkIfAllowedTest() {
	
	//TODO: implement integration test

	//}
}
