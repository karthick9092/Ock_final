package com.ockart.test.ockart.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ockart.entity.amazon.com.ecs.client.jax.ItemSearchResponse;
import com.ockart.entity.common.OckCompany;
import com.ockart.entity.common.OckElectronicsCategory;
import com.ockart.entity.common.OckProductDtlElectronics;
import com.ockart.entity.common.OckProductElectronics;
import com.ockart.util.amazon.AmazonUtils;
import com.ockart.util.amazon.bean.ProductInfo;
import com.ockart.util.amazon.helper.CommonConstants;
import com.ockart.util.amazon.helper.FlipkartCategoryConstant;
import com.ockart.util.amazon.repository.CompanyRepository;
import com.ockart.util.amazon.repository.OckElectronicsCtgryRepository;

@Service
public class ElectronicsEntityService {

	@Autowired
	OckElectronicsCtgryRepository ockCategoryRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	AmazonUtils amazonUtils;

	public OckElectronicsCategory addFkDtlsToProductEntity(
			List<ProductInfo> productInfoList, String category) {
		// TODO Auto-generated method stub
		OckElectronicsCategory ockProductCategory = new OckElectronicsCategory();
		List<OckProductElectronics> ockProductList = null;
		OckCompany ockCompany = null;
		try {
			if (productInfoList != null && productInfoList.size() != 0) {
				ockCompany = companyRepository
						.findByCompanyName(CommonConstants.FLIPKART);
				ockProductList = new ArrayList<OckProductElectronics>();
				if (category.equals(FlipkartCategoryConstant.MOBILES)) {
					ockProductCategory = ockCategoryRepository
							.findByCategoryName(FlipkartCategoryConstant.MOBILES);
				}
				ockProductCategory = mapFlipKartProductDtlsToEntity(
						productInfoList, ockProductList, ockProductCategory,
						ockCompany);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ockProductCategory;
	}
	
	/***
	 * Mapping the flipkart product info bean to ockart entity
	 * @param productInfoList
	 * @param ockProductList
	 * @param ockProductCategory
	 * @param ockCompany
	 * @return
	 */

	private OckElectronicsCategory mapFlipKartProductDtlsToEntity(
			List<ProductInfo> productInfoList, List<OckProductElectronics> ockProductList,
			OckElectronicsCategory ockProductCategory, OckCompany ockCompany) {
		// TODO Auto-generated method stub
		try {
			for (int i = 0; i < productInfoList.size(); i++) {
				OckProductElectronics ockProduct = new OckProductElectronics();
				List<OckProductDtlElectronics> ockProductDtlList = new ArrayList<OckProductDtlElectronics>();
				OckProductDtlElectronics ockProductDtl = new OckProductDtlElectronics();
				ProductInfo productInfo = productInfoList.get(i);
				ockProduct.setProductName(productInfo.getTitle());

				// Getting The Manufacturer From The Title
				String title = productInfo.getTitle();
				String arr[] = title.split(" ", 2);
				String manufacturer = arr[0];

				ockProduct.setManufacturer(manufacturer);
				ockProduct.setDescription(productInfo.getDescription());
				ockProduct.setOckProductCategory(ockProductCategory);

				// Mapping FlipKart Dtls To The Product
				ockProductDtl.setItemNumber(productInfo.getId());
				ockProductDtl.setItemPrice(new BigDecimal(productInfo
						.getSellingPrice()));
				ockProductDtl.setProduct_URL(productInfo.getProductUrl());
				ockProductDtl.setOckCompany(ockCompany);
				ockProductDtl.setOckProduct(ockProduct);

				ockProductDtlList.add(ockProductDtl);
				ockProduct.setOckProductDtls(ockProductDtlList);
				ockProductList.add(ockProduct);
				ockProductCategory.setOckProducts(ockProductList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ockProductCategory;
	}
	
	/***
	 * Getting product details from amazon using name of the product
	 * @param ockProductCategory
	 * @return
	 */

	public OckElectronicsCategory addAmazonDtlsToProductEntity(
			OckElectronicsCategory ockProductCategory) {
		List<OckProductElectronics> ockProductList = null;
		OckProductDtlElectronics ockProductDtl = null;
		OckCompany ockCompany = null;
		try {
			ockProductList = ockProductCategory.getOckProducts();
			if (ockProductList != null && ockProductList.size() != 0) {
				ockCompany = companyRepository
						.findByCompanyName(CommonConstants.FLIPKART);
				if (ockProductCategory.getCategoryName().equals(
						FlipkartCategoryConstant.MOBILES)) {

					for (int i = 0; i < ockProductList.size(); i++) {
						OckProductElectronics ockProduct = ockProductList.get(i);
						ItemSearchResponse itemSearchResponse = amazonUtils
								.itemSearch("Electronics",
										ockProduct.getProductName());
						if (itemSearchResponse != null) {
							ockProductDtl = new OckProductDtlElectronics();
						}
						getPriceFromAmazon(itemSearchResponse);
						ockProductDtl.setItemNumber(itemSearchResponse
								.getItems().get(0).getItem().get(0).getASIN());
						ockProductDtl.setItemPrice(new BigDecimal(
								itemSearchResponse.getItems().get(0).getItem()
										.get(0).getItemAttributes()
										.getListPrice().getAmount()));
						ockProductDtl.setProduct_URL(itemSearchResponse
								.getItems().get(0).getItem().get(0)
								.getDetailPageURL());
						ockProductDtl.setOckProduct(ockProduct);
						ockProductDtl.setOckCompany(ockCompany);
						ockProduct.getOckProductDtls().add(ockProductDtl);
						ockProductCategory.addOckProduct(ockProduct);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ockProductCategory;
	}
	
	/***
	 * Parsing the amazon page to get the price detail using jsoup
	 * @param itemSearchResponse
	 */

	private void getPriceFromAmazon(ItemSearchResponse itemSearchResponse) {
		// TODO Auto-generated method stub
		try {
			Document doc = Jsoup.connect(itemSearchResponse
					.getItems().get(0).getItem().get(0)
					.getDetailPageURL()).get();
			String price = doc.select("span#priceblock_ourprice").text();
			itemSearchResponse.getItems().get(0).getItem()
			.get(0).getItemAttributes()
			.getListPrice().setAmount(new BigInteger(price));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
