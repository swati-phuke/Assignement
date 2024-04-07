package com.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.time.Duration;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReadExcelSeat {
	public static Map<String, Double> getStockPriceData() throws IOException
    {
        String excelFilePath = 
       " C:\\Users\\DELL\\eclipse-workspace\\Test\\Practice\\src\\test\\resources\\Stock Prices (1).xlsx";

        FileInputStream fis = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(fis);

        Map<String, Double> stockPricesMap = new HashMap<>();
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet)
        {
            if (row.getRowNum() > 0)
            {
                String excelCompanyName = row.getCell(0).getStringCellValue();
                double currentPrice = row.getCell(3).getNumericCellValue();
                stockPricesMap.put(excelCompanyName, currentPrice);
            }
        }
        return stockPricesMap;
    }


    public static void main(String[] args) throws IOException
    {
        WebDriver driver = new ChromeDriver();
        driver.get("https://money.rediff.com/losers/bse/daily/groupall");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();

        Map<String, Double> expectedStockPrices = getStockPriceData();
        Map<String, Double> actualStockPrices = new HashMap<>();

        for (String company : expectedStockPrices.keySet())
        {
            try
            {
                String stockPrice = driver.findElement(By.xpath("//a[contains(text(),'" + company + "')]/parent::td/following-sibling::td[3]")).getText();
                actualStockPrices.put(company, Double.parseDouble(stockPrice));
            } catch (NoSuchElementException e)
            {
                System.out.println("Company not found in list :: " + company);
            }

        }

        for (String companyName : actualStockPrices.keySet())
        {
            Double expectedPrice = expectedStockPrices.get(companyName);
            Double actualPrice = actualStockPrices.get(companyName);
            double tolerance = 0.001;
            if (Math.abs(expectedPrice - actualPrice) < tolerance)
            {
                System.out.println("Test Passed: Price Data Matched For : " + companyName);
            }
            else
            {
                System.out.println("Test Failed: Price Data Not Matched For : " + companyName);
            }


        }
    }

}
