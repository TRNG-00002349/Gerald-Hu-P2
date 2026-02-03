package com.revature.backend.config;

import com.revature.backend.utils.UserOwnershipFilter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@Data
public class UserOwnershipConfig {

	@Bean
	public FilterRegistrationBean<UserOwnershipFilter> userOwnershipFilter(){
		System.out.println("do we even make this bean");
		FilterRegistrationBean<UserOwnershipFilter> filterRegistrationBean = new FilterRegistrationBean<>();

		filterRegistrationBean.setFilter(new UserOwnershipFilter());
		filterRegistrationBean.addUrlPatterns("/api/users/*");
		filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return filterRegistrationBean;
	}
}
