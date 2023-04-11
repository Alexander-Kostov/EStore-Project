package com.example.Estore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EStoreProjectApplicationTests {
	@Value("${app.admin.defaultpass}")
	@Test
	void contextLoads() {
	}

}
