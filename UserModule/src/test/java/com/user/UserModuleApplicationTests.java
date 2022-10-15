package com.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.helper.UtilityClass;
import com.user.model.Location;
import com.user.model.User;
import com.user.repository.LocationRepository;
import com.user.repository.UserRepository;
import com.user.request.UserDetailsRequest;
import com.user.request.UserLocationBasedOnTimeRangeRequest;
import com.user.request.UserLocationDataRequest;
import com.user.service.IUserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,classes = {UserModuleApplication.class,H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
class UserModuleApplicationTests {

	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper mapper;
	@Autowired
	UserRepository userRepository;
	@Autowired
	IUserService userService;
	@Autowired
	LocationRepository locationRepository;

	@Test
	public void createUser() throws Exception {

		UserDetailsRequest userDetailsRequest = new UserDetailsRequest("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2","2022-03-08T11:44:00.524","medha.mehta@gmail.com","Medha","Mehta");
		User expectedUser = new User("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2",UtilityClass.parseDate("2022-03-08T11:44:00.524"),"medha.mehta@gmail.com","Medha","Mehta");
				mapper
						.readValue(
								mockMvc
										.perform(
												post("/user/saveUser")
														.contentType(MediaType.APPLICATION_JSON)
														.content(mapper.writeValueAsString(userDetailsRequest)))
										.andReturn()
										.getResponse()
										.getContentAsString(),
								UserDetailsRequest.class);
		assertThat(
				userRepository
						.findById("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2").get().toString(),
				equalTo(expectedUser.toString()));
	}

	@Test
	public void addLocationsForTheUser() throws Exception {
		createUser();
		Location location = new Location();
		location.setId(Long.parseLong("1"));
		location.setUser(userRepository.findById("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2").get());
		location.setLatitude(52.3681563);
		location.setLongitude(4.9010029);
		UserLocationDataRequest userDetailsRequest = new UserLocationDataRequest("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2","2022-03-08T11:45:00.524",location);
		Location location2 = new Location();
		location2.setId(Long.parseLong("2"));
		location2.setUser(userRepository.findById("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2").get());
		location2.setLatitude(51.3681563);
		location2.setLongitude(5.9010029);
		UserLocationDataRequest userDetailsRequest2 = new UserLocationDataRequest("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2","2022-03-08T11:47:00.524",location2);
		mapper
				.readValue(
						mockMvc
								.perform(
										post("/user/locationData")
												.contentType(MediaType.APPLICATION_JSON)
												.content(mapper.writeValueAsString(userDetailsRequest)))
								.andReturn()
								.getResponse()
								.getContentAsString(),
						UserLocationDataRequest.class);
		mapper
				.readValue(
						mockMvc
								.perform(
										post("/user/locationData")
												.contentType(MediaType.APPLICATION_JSON)
												.content(mapper.writeValueAsString(userDetailsRequest2)))
								.andReturn()
								.getResponse()
								.getContentAsString(),
						UserLocationDataRequest.class);
		assertThat(
				locationRepository.findAllByUser(userRepository.findById("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2").get()).size(),
				equalTo(Integer.parseInt("2")));
	}

	@Test
	@Disabled
	void allUserLocations() throws Exception {
		addLocationsForTheUser();
		UserLocationBasedOnTimeRangeRequest request = new UserLocationBasedOnTimeRangeRequest("2e3b11b0-07a4-4873-8de5-d2ae2eab26b2","2022-03-08T11:45:00.524","2022-03-08T11:46:00.524");
		mockMvc
				.perform(get("/user/userLocationsByTimeRange")
						.content(mapper.writeValueAsString(request)))
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	@Disabled
	void getLatestUserLocation() throws Exception {
		addLocationsForTheUser();
		mockMvc
				.perform(get("/user/latestLocation/2e3b11b0-07a4-4873-8de5-d2ae2eab26b2"))
				.andExpect(jsonPath("$",hasSize(1)))
				.andExpect(jsonPath("$[0].createdOn", is("2022-03-08T06:17:00.524+00:00")));
	}

}
