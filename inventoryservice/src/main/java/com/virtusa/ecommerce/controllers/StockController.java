package com.virtusa.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.ecommerce.models.Location;
import com.virtusa.ecommerce.models.Stock;
import com.virtusa.ecommerce.services.LocationService;
import com.virtusa.ecommerce.services.StockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/stocks")
public class StockController {

	@Autowired
	private StockService stockService;
    
 @PostMapping({"/v1.0/{productId}/{locationId}", "/v1.1/{productId}/{locationId}"})

    @CrossOrigin("*")
    public ResponseEntity<?> addStock(@RequestBody Stock stock,
    		@PathVariable("productId") long productId,
    		@PathVariable("locationId") long locationId
    		)
    {
    	
	 Stock stockObj=stockService.addStock(stock,productId,locationId);
    	if(stockObj!=null)
    	
    	  return ResponseEntity.status(HttpStatus.ACCEPTED).body(stockObj);
    	else
    		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Added");
    	
    }
 
 
    
 @GetMapping({"/v1.0", "/v1.1"})

    @CrossOrigin("*")
    public List<Stock> findAllStocks()
    {
    	
    		return stockService.getAllStocks();
    	
    }
}
