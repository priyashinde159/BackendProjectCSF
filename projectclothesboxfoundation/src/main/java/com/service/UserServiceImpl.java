package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dao.CartRepository;

import com.dao.ProductRepository;
import com.dao.UserRepository;
import com.model.Cart;
import com.model.Donar;
import com.model.Login;
import com.model.Product;
import com.model.User;

@Service
public class UserServiceImpl implements UserServiceInt {
	@Autowired
	UserRepository userRepo;

	@Autowired
	ProductRepository productRepo;

	@Autowired
	CartRepository cartRepo;

	public User addUser(User user) {

		return userRepo.save(user);
	}

	public User findByEmailIdAndPassword(String email, String password) {

		return userRepo.findByEmailIdAndPassword(email, password);
	}

	public User getUserByEmail(String email) {

		return userRepo.findUserByEmail(email);
	}

	public User getUserById(int userId) {

		return userRepo.findById(userId).get();
	}
	
	public String uploadImage(MultipartFile image) throws IOException {
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
		Path uploadPath = Paths.get("C:/Users/");
		try(InputStream inputStream = image.getInputStream())
		{
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		}
		catch(IOException ioe)
		{
			throw new IOException("Could not save image");
		}
		return fileName;
	}

	public Product getProductById(int productId) {

		return productRepo.findById(productId).get();
	}

	public Product addProduct(Product product) {

		return productRepo.save(product);
	}

	public List<Product> getAllProducts() {

		return productRepo.findAll();
	}

	public List<Product> getProductsByUserId(int id) {
		return productRepo.findProductByUserId(id);
	}

	public Product updateProduct(Product product) {
		Product productDB = productRepo.findById(product.getProductId()).get();

		productDB.setProductPrice(product.getProductPrice());
		productDB.setProductName(product.getProductName());
		System.out.println(productDB);
		return productRepo.saveAndFlush(productDB);

	}

	public void deleteProduct(int id) {

		productRepo.deleteById(id);

	}

	

	public Cart addToCart(Cart cart) {

		return cartRepo.save(cart);
	}

	public List<Cart> getFromCart(int userId) {

		return cartRepo.findCartByUserId(userId);
	}

	public void deleteCart(int id) {
		cartRepo.deleteById(id);

	}

	@Override
	public Donar addDonar(Donar donar) {
		// TODO Auto-generated method stub
		return null;
	}

}
