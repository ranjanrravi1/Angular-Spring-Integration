package com.tcs.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcs.demo.ProductService;
import com.tcs.demo.dto.ProductDTO;

@Controller
public class ProductController {

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public @ResponseBody String addProduct(@RequestBody ProductDTO productDTO) throws JsonParseException, JsonMappingException, IOException{
		System.out.println("addProduct "+productDTO);
		/*ObjectMapper objectMapper = new ObjectMapper();
		ProductDTO productDTO = objectMapper.readValue(product, ProductDTO.class);
		*/
		ProductService.productDTOMap.put(productDTO.getpId(), productDTO);
		
		String message ="{\"message\":\"New Product Added successfully.\"}";
		
		return message;
	}
	
	@RequestMapping(value = "/product/{pId}", method = RequestMethod.GET)
	public @ResponseBody ProductDTO getProduct(@PathVariable("pId") String pId) throws JsonGenerationException, JsonMappingException, IOException{
		
		System.out.println("getProduct "+pId);
		
//		ObjectMapper objectMapper = new ObjectMapper();
		ProductDTO productDto = ProductService.productDTOMap.get(pId);
		
//		String product = objectMapper.writeValueAsString(productDto);
		
		return productDto;
	}
	
	@RequestMapping(value = "/product/list", method = RequestMethod.GET)
	public @ResponseBody String getAllProducts() throws JsonGenerationException, JsonMappingException, IOException{
		
		Set<Entry<String, ProductDTO>> entrySet = ProductService.productDTOMap.entrySet();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		
		for (Entry<String, ProductDTO> entry : entrySet) {
			productDTOs.add(entry.getValue());
		}
		 
		String mapAsJson = new ObjectMapper().writeValueAsString(productDTOs);
        System.out.println(mapAsJson);
		
		return mapAsJson;		
	}
}
