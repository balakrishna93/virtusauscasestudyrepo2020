package com.virtusa.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.ecommerce.models.Location;
import com.virtusa.ecommerce.models.Product;
import com.virtusa.ecommerce.models.Stock;
import com.virtusa.ecommerce.repository.ProductRepository;
import com.virtusa.ecommerce.repository.StockRepository;

@Service
public class StockService {
    @Autowired
	private StockRepository stockRepository;
    @Autowired
	private LocationService locationService;
    @Autowired
	private ProductService productService;
 
    
	
	public Stock addStock(Stock stock,long productId,long locationId)
	{

		Product product=productService.getProductById(productId);
		Location location=locationService.getLocationById(locationId);
		
		if((product!=null) &&(location!=null))
		{
			stock.setProduct(product);
			stock.setLocation(location);
			stockRepository.save(stock);
		}
		
		return stock;
	}
	public List<Stock> getAllStocks()
    {
    	return stockRepository.findAll();
    	
    	
    }
    
    
	
	
	public List<Stock> getAllStocksAboveBufferLevel()
    {
    	
    	
    	return stockRepository.findAll().stream().filter(stock->stock.getQty()> 
    	productService.getProductById(stock.getProduct().getProductId())
    	.getBufferLevel()).collect(Collectors.toList());
    }
    
    
    
}
