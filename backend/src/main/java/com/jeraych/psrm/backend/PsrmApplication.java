package com.jeraych.psrm.backend;

import com.jeraych.psrm.client.App;
import javafx.application.Platform;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.application.Application;

@SpringBootApplication
public class PsrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsrmApplication.class, args);
//		Application.launch(App.class);
//		Platform.exit();
//		System.exit(0);
	}

}
