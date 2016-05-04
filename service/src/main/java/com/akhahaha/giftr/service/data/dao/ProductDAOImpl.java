package com.akhahaha.giftr.service.data.dao;

import com.akhahaha.giftr.service.data.models.Product;
import com.akhahaha.giftr.service.data.models.ProductSource;
import com.akhahaha.shopzilla.catalog.client.CatalogClient;
import com.akhahaha.shopzilla.catalog.models.Image;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ProductDAO implementation
 * Created by Alan on 5/2/2016.
 */
public class ProductDAOImpl implements ProductDAO {
    private CatalogClient catalogClient;

    @Autowired
    public void setCatalogClient(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @Override
    public List<Product> searchProductsByKeyword(String keyword) {
        // Get Shopzilla products
        return catalogClient.searchProductsByKeyword(keyword).stream().map(catalogProductToProduct)
                .collect(Collectors.toList());
    }

    /**
     * Transforms a Shopzilla Catalog product to a Product
     */
    private Function<com.akhahaha.shopzilla.catalog.models.Product, Product> catalogProductToProduct =
            catalogProduct -> new Product(
                    ProductSource.CONNEXITY,
                    catalogProduct.getId(),
                    catalogProduct.getTitle(),
                    catalogProduct.getManufacturer(),
                    catalogProduct.getDescription(),
                    catalogProduct.getUrl().getValue(),
                    catalogProduct.getImages().getImages().stream().map(Image::getValue).collect(Collectors.toList()),
                    BigDecimal.valueOf(catalogProduct.getPrice().getIntegral() * 0.01));
}
