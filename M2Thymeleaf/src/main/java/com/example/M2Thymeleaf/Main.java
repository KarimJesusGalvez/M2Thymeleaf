package com.example.M2Thymeleaf;

import com.example.M2Thymeleaf.Bibliographic_classes.Author;
import com.example.M2Thymeleaf.Bibliographic_classes.Bibliographic_entry;
import com.example.M2Thymeleaf.Database.DB;
import com.example.M2Thymeleaf.Repos.Author_Repo;
import com.example.M2Thymeleaf.Repos.B_E_Repo;
import com.example.M2Thymeleaf.Static_classes.Print_logo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {


	@Autowired
	B_E_Repo B_ERepository;
	@Autowired
	Author_Repo author_repo;

	public static void main(String[] args){
		SpringApplication.run(Main.class, args);

		System.out.println("Closing main".toUpperCase());
		System.out.println("\n");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n");
		System.out.println("Starting run".toUpperCase());

		Print_logo.print_logo();
		System.out.println("\n");
		DB database1 = new DB();
		database1.query1.menuhtml();
		System.out.print(database1.getCreation_date().substring(0,10));
		System.out.println(" Welcome to the DataBase");
		System.out.println("...");
		System.out.println("......");

		//dbRepository.save(database1);


		Author eug = new Author("Eugenia Perez");
		author_repo.save(eug);

		List<Author> sec_autors = new ArrayList<>();
		Bibliographic_entry tempsave = new Bibliographic_entry(eug, sec_autors,null,"book","paperback",
				"Spring 5","Spanish",",LAN,Java,Spring ","407pg","2018");
		B_ERepository.save(tempsave);
/*
		Bibliographic_entry temp2 = new Bibliographic_entry("book");
		author_repo.save(temp2.getMain_author());
		for (Author count : temp2.getSecondary_authorsList())
			author_repo.save(count);
		B_ERepository.save(temp2);
 */
		//Test test1 = new Test();
		//test1.nonstatictest();

		boolean close_validation = false;

		while ( !close_validation ) {

			boolean[] check_call = database1.query1.startup();
			database1.update_reg();
			if (check_call[5]){
				database1 = new DB();
			}
			if (check_call[6]){
				close_validation = database1.close();
			}

		}

		System.out.print("Closing the Main application");
		Print_logo.print_logo();
		System.out.print("Copyright ");
		System.out.println(database1.getCreation_date().substring(0,4));
		System.out.println("...");
		Scanner scanner = new Scanner(System.in);
		scanner.close();
		System.out.println("......");


	}
}
