package com.quercus.PineappleSupermarket.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quercus.PineappleSupermarket.dto.CategoryDTO;
import com.quercus.PineappleSupermarket.dto.ProductDTO;
import com.quercus.PineappleSupermarket.model.ProductModel;
import com.quercus.PineappleSupermarket.models.Category;
import com.quercus.PineappleSupermarket.models.Product;
import com.quercus.PineappleSupermarket.services.ICategoryService;
import com.quercus.PineappleSupermarket.services.IProductService;
import com.quercus.PineappleSupermarket.services.mapper.MapperUtil;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	private final static String UPLOADS_FOLDER = "uploads";
	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> getPicture(@PathVariable String filename) {
		Path pathimg = Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
		Resource resource = null;
		
		try {
			resource = new UrlResource(pathimg.toUri());
			if(!resource.exists() && !resource.isReadable()) {
				throw new RuntimeException("Error: no se puede cargar la imagen: " + pathimg.toString());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<ProductDTO> saveProduct(@ModelAttribute ProductModel product) throws Exception {
		
		Optional<Category> cat;
		Optional<Product> prod = productService.findByName(product.getName());
		CategoryDTO catDto;
		ProductDTO prodDto = new ProductDTO();
		
		if(prod.isPresent()) {
			throw new Exception("El producto ya existe"); 
		}
		
		if(!product.getPicture().isEmpty()) {
			String uniqueFileName = UUID.randomUUID().toString() + "_" + product.getPicture().getOriginalFilename(); 
			Path rootPath = Paths.get(UPLOADS_FOLDER).resolve(uniqueFileName);
			Path rootAbsolutePath = rootPath.toAbsolutePath();
			
			try {
				
				Files.copy(product.getPicture().getInputStream(), rootAbsolutePath);
				prodDto.setPicture(uniqueFileName);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		cat = categoryService.findByName(product.getCategory());
		
		if(cat.isEmpty()) {
			throw new Exception("Category not found");
		}
		
		catDto = MapperUtil.convertToDto(cat.get());
  		
		prodDto.setName(product.getName());
		prodDto.setDescription(product.getDescription());
		prodDto.setQuantity(product.getQuantity());
		prodDto.setPrice(product.getPrice());
		prodDto.setCategory(catDto.getName());
		
		return new ResponseEntity<ProductDTO>(MapperUtil.convertToDto(productService.saveProduct(MapperUtil.convertToEntity(prodDto, catDto))),
											HttpStatus.OK);
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<ProductDTO> updateProduct(@ModelAttribute ProductModel product) throws Exception {
		
		Optional<Category> cat;
		Optional<Product> prod = productService.findById(product.getId());
		CategoryDTO catDto;
		ProductDTO prodDto = new ProductDTO();
		
		if(prod.isEmpty()) {
			throw new Exception("El producto no existe"); 
		}
		
		if(!product.getPicture().isEmpty()) {
			
			if(product.getPicture() != null
					&& product.getPicture().getOriginalFilename().length() > 0) {
				Path rootPath = Paths.get(UPLOADS_FOLDER).resolve(prod.get().getPicture()).toAbsolutePath();
				File file = rootPath.toFile();
				
				if(file.exists() && file.canRead()) {
					file.delete();
				}
			}
			
			String uniqueFileName = UUID.randomUUID().toString() + "_" + product.getPicture().getOriginalFilename(); 
			Path rootPath = Paths.get("uploads").resolve(uniqueFileName);
			Path rootAbsolutePath = rootPath.toAbsolutePath();
			
			try {
				
				Files.copy(product.getPicture().getInputStream(), rootAbsolutePath);
				prodDto.setPicture(uniqueFileName);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		cat = categoryService.findByName(product.getCategory());
		
		if(cat.isEmpty()) {
			throw new Exception("Category not found");
		}
		
		catDto = MapperUtil.convertToDto(cat.get());
  		
		prodDto.setId(product.getId());
		prodDto.setName(product.getName());
		prodDto.setDescription(product.getDescription());
		prodDto.setQuantity(product.getQuantity());
		prodDto.setPrice(product.getPrice());
		prodDto.setCategory(catDto.getName());
		
		return new ResponseEntity<ProductDTO>(MapperUtil.convertToDto(productService.saveProduct(MapperUtil.convertToEntity(prodDto, catDto))),
											HttpStatus.OK);
	}
	
	@GetMapping(value = "/list")
	public ResponseEntity<List<ProductDTO>> getAll() {
		List<ProductDTO> list = productService.findAll().stream().map(elem -> MapperUtil.convertToDto(elem)).collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filter")
	public ResponseEntity<List<ProductDTO>> filterByName(@RequestParam("str") String str) {
		List<ProductDTO> list = productService.findByNameContaining(str).stream().map(elem -> MapperUtil.convertToDto(elem)).collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws Exception {
		
		Optional<Product> prod = productService.findById(id);
		
		if(id < 0) {
			throw new Exception("Invalid id");
		}
		
		productService.deleteProduct(id);
		
		if(prod.isPresent()) {
			Path rootPath = Paths.get(UPLOADS_FOLDER).resolve(prod.get().getPicture()).toAbsolutePath();
			File file = rootPath.toFile();
			
			if(file.exists() && file.canRead()) {
				if(file.delete()) {
					return new ResponseEntity<String>("Se eliminó el producto correctamente", HttpStatus.OK);
				}
			}
		}
		
		return new ResponseEntity<String>("Se eliminó el producto. Error al borrar la imagen", HttpStatus.OK);
	}
	
	@GetMapping(value = "/order/name/asc")
	public ResponseEntity<List<ProductDTO>> orderByNameAsc() {
		List<ProductDTO> list = productService.findAllByOrderByNameAsc().stream().map(elem -> MapperUtil.convertToDto(elem)).collect(Collectors.toList());
		return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/order/name/desc")
	public ResponseEntity<List<ProductDTO>> orderByNameDesc() {
		List<ProductDTO> list = productService.findAllByOrderByNameDesc().stream().map(elem -> MapperUtil.convertToDto(elem)).collect(Collectors.toList());
		return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/order/price/asc")
	public ResponseEntity<List<ProductDTO>> orderByPriceAsc() {
		List<ProductDTO> list = productService.findAllByOrderByPriceAsc().stream().map(elem -> MapperUtil.convertToDto(elem)).collect(Collectors.toList());
		return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/order/price/desc")
	public ResponseEntity<List<ProductDTO>> orderByPriceDesc() {
		List<ProductDTO> list = productService.findAllByOrderByPriceDesc().stream().map(elem -> MapperUtil.convertToDto(elem)).collect(Collectors.toList());
		return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/order/quantity/asc")
	public ResponseEntity<List<ProductDTO>> orderByQuantityAsc() {
		List<ProductDTO> list = productService.findAllByOrderByQuantityAsc().stream().map(elem -> MapperUtil.convertToDto(elem)).collect(Collectors.toList());
		return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/order/quantity/desc")
	public ResponseEntity<List<ProductDTO>> orderByQuantityDesc() {
		List<ProductDTO> list = productService.findAllByOrderByQuantityDesc().stream().map(elem -> MapperUtil.convertToDto(elem)).collect(Collectors.toList());
		return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
	}
}
