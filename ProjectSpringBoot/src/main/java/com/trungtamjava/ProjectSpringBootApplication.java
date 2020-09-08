package com.trungtamjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.trungtamjava.service.impl.UserLoginServiceImpl;

@SpringBootApplication(scanBasePackages = { "com.trungtamjava" })
@EntityScan(basePackages = { "com.trungtamjava.entity" })
public class ProjectSpringBootApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	UserLoginServiceImpl userLoginServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringBootApplication.class, args);
		
//		String pass = "123";
//		String endcode = new BCryptPasswordEncoder().encode(pass);
//		System.out.println(endcode);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests().antMatchers("/home","/homeclt").permitAll().antMatchers("/admin/**")
		.hasAnyRole("ADMIN").antMatchers("/member/**").hasAnyRole("MEMBER").anyRequest().authenticated()
		// antMatchers(antPatterns) có 3 cách 1 là phương thức 2 là đường dẫn 3 là cả 2
		// ở đây dùng cách 2 giống filter servelet
		// hasAnyRole bất kỳ đường đãn nào để trong đây sẽ được thông qua còn k sẽ
		// authenticated
				.and().formLogin().loginPage("/login")
				.usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login")
				.defaultSuccessUrl("/home",true).failureUrl("/login?e=error").permitAll().and().logout().permitAll().and()
				.exceptionHandling().accessDeniedPage("/login?e=deny");

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**","/vendor/**");
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
		// auth.inMemoryAuthentication().passwordEncoder(new
		// BCryptPasswordEncoder()).withUser("admin").password(new
		// BCryptPasswordEncoder().encode("123456")).roles("ADMIN");

		// co the dung md 5 ở đay xài BCrypasswor
		auth.userDetailsService(userLoginServiceImpl).passwordEncoder(new BCryptPasswordEncoder());

	}
}
