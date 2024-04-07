package com.practice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class RetriveFlipCartLink {
	public static List<WebElement> linksRetrieving(WebDriver driver, String url) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        List<WebElement> links = driver.findElements(By.tagName("a"));
        return links;
    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String url = "https://www.flipkart.com/";
        List<WebElement> links = linksRetrieving(driver, url);

        // Print links using for-each loop
        System.out.println("Using for-each loop:");
        for (WebElement link : links) {
            System.out.println(link.getAttribute("href"));
        }

        // Print links using Stream
        System.out.println("\nUsing Stream:");
        links.stream().map(link -> link.getAttribute("href")).forEach(System.out::println);

        // Print links using Parallel Stream
        System.out.println("\nUsing Parallel Stream:");
        links.parallelStream().map(link -> link.getAttribute("href")).forEach(System.out::println);

}
}