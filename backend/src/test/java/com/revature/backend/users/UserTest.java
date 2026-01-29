//package com.revature.backend.users;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
//import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.client.RestTestClient;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.BDDAssumptions.given;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest
//@AutoConfigureRestTestClient
//public class UserTest {
//
//	@Autowired
//	private MockMvc mvc;
//
//	@Autowired
//	private RestTestClient restTestClient;
//
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	@BeforeEach
//	public void setup() throws Exception {
//		mvc = MockMvcBuilders
//				.webAppContextSetup(webApplicationContext)
//				.defaultRequest(get("/")
//						.with(user("a").password("a")))
//				.apply(springSecurity())
//				.build();
////		mvc.perform(get("/login")
////				.with(user("a").password("a")));
//	}
//
//	@Test
//	public void getsUsers() throws Exception {
//		User u = new User();
////		List<User> allUsers = List.of(u);
//
//		mvc.perform(get("/api/users")
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
//
////		given(userRepository.findAll.willReturn(allUsers));
////
////		restTestClient.get()
////				.uri("/api/users")
////				.exchange()
////				.expectStatus().is2xxSuccessful();
////				.expectBody(String.class)
////				.isEqualTo("Hello, World");
//
//	}
//
//}
