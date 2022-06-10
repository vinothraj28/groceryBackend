package com.grocery.groceries.grocery.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import com.grocery.groceries.grocery.Grocery;


/**
 * @author Vinoth
 *
 */
@Service
public class GroceryService {

	    @Autowired
	    GroceryRespository groceryRepository;
	    
	    @Autowired
	    JdbcTemplate jdbctemplate;
	    
	    Logger logger = LoggerFactory.getLogger(GroceryService.class);

	    public List<Grocery> getAllGrocery(){
	    	String sql = "select NAME, MAX(price), MAX(date) from GROCERY group by NAME order by MAX(PRICE) desc";
	    	List<Grocery> grocery;
	    	grocery = jdbctemplate.query(sql, 
			    (rs, rowNum) ->
         new Grocery(
			    rs.getString("name"),
			    rs.getDouble("MAX(price)"),
			    rs.getDate("MAX(date)")
         ));
	              return grocery;
	    };
	    
	    public void saveGrocery()  {
	    	logger.trace(" We can save here ");
	    	try {
	    		ClassPathResource resource = new ClassPathResource("Vegetable_2.xlsx");
	    		File file = resource.getFile();
		    	logger.trace("Inside save or update column");
		    	Workbook workbook = new XSSFWorkbook(new FileInputStream(file));
		    	Sheet sheet =  workbook.getSheetAt(0);
		        Iterator<Row> rows = sheet.iterator();
		    	DataFormatter dataFormatter = new DataFormatter();
		    	SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yy");
		        int rowNumber = 0;
		        while (rows.hasNext()) {
		            Row currentRow = rows.next();
		            if (rowNumber == 0) {
		              rowNumber++;
		              continue;
		            }
		            Iterator<Cell> cellsInRow = currentRow.iterator();
		            int cellIdx =0;
		            boolean saveTheGrocery = true;
		            Grocery grocery1 = new Grocery();
		            while (cellsInRow.hasNext()) {
		                Cell currentCell = cellsInRow.next();
		                String value = dataFormatter.formatCellValue(currentCell);
		                logger.trace(" formatted value "+value);
		                
		                if(!valid(value))
		                {
		                	saveTheGrocery = false;
		                	continue;
		                }
		                switch (cellIdx) {
		                case 0:
		                	 
		                  break;
		                case 1: 
		                	grocery1.setName(value);
		                    break;
		                  case 2:
	                	  	Date priceDt = dateFormat.parse(value);
	                	  	grocery1.setDate(priceDt);
		                    break;
		                  case 3:
		                	  grocery1.setPrice(Double.valueOf(value));
	   	                      break;
	   	                      
		                  default:
		                    break;
		                }
		                cellIdx++;  
		            } 
	
		            if(saveTheGrocery) {
		            	logger.trace("Grocery save is true");
		            	logger.trace("Saving grocery "+grocery1);
		            	groceryRepository.save(grocery1);
		            }
		        }
		        
		    	}catch(IOException e) {
		    		logger.trace("File not found "+e);
		    	}catch(ParseException e) {
		    		logger.trace("Unable to parse the date "+e);
		    	}
	    		catch(NullPointerException e) {
	    			logger.trace("Value is null "+e);
		    	}
	    }

		public List<Grocery> getGroceryByName(String name) {
			String sql = "select ID, NAME, price, date from GROCERY where NAME = '"+name+"' order by price";
			List<Grocery> grocery = jdbctemplate.query(sql, 
	                (rs, rowNum) ->
            new Grocery(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getDate("date")
            ));
			
			logger.trace("Inside getGroceryByName method :");
			logger.trace("grocery details retruned by name : "+grocery);
	        return grocery;	
		}
		
		private boolean valid(String value)
		{
			if(null != value && !value.isEmpty() && !"NULL".equalsIgnoreCase(value))
			{
				return true;
			}
			return false;
		}
}
