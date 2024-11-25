package vn.iotstar.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.Repository.ProductRepository;
import vn.iotstar.Service.ProductServices;
import vn.iotstar.entity.Product;
@Service
public class ProductServiceimpl implements ProductServices{
	@Autowired
	private ProductRepository repo;
	
	public ProductServiceimpl(ProductRepository repo)
	{
		this.repo = repo;
	}
	
	@Override
	public List<Product> listAll(){
		return repo.findAll();
	}
	
	@Override
	public Product save(Product product)
	{
		return repo.save(product);
	}
	
	@Override
	public Product get(Long id)
	{
		return repo.findById(id).get();
	}
	
	@Override
	public void delete(Long id)
	{
		repo.deleteById(id);
	}
}
