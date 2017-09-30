package com.mud.controller;

import com.mud.context.UserContext;
import com.mud.dao.HeroExtendDao;
import com.mud.dao.ProductDao;
import com.mud.dao.UserBagDao;
import com.mud.mapper.HeroExtend;
import com.mud.mapper.Product;
import com.mud.mapper.UserAuth;
import com.mud.mapper.UserBag;
import com.mud.model.ProductModel;
import com.mud.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leeesven on 2017/9/25.
 */

@RequestMapping("/api/bag")
@RestController
public class BagController {

    @Autowired
    UserBagDao userBagDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    HeroExtendDao heroExtendDao;

    @GetMapping(value = "/product/all")
    public ResponseModel getAllProduct(){

        UserAuth userAuth = UserContext.getCurrentUserAuth();
        System.out.println("getAllProduct userId = " + userAuth.getUserId());

        ResponseModel responseModel = new ResponseModel();
        List<ProductModel> productModelList = new ArrayList<>();
        List<UserBag> userBags = userBagDao.getAllBagsOf(userAuth.getUserId());
        if (userBags != null){
            for (UserBag userBag : userBags) {
                Product product = productDao.getProductById(userBag.getProductId());
                ProductModel productModel = new ProductModel(product, userBag);
                productModelList.add(productModel);
            }
        }
        responseModel.setData(productModelList);
        return responseModel;
    }

    @GetMapping(value = "/product/all/hero_piece")
    public ResponseModel getAllProductOfHeroPiece(){
        UserAuth userAuth = UserContext.getCurrentUserAuth();
        System.out.println("getAllProduct userId = " + userAuth.getUserId());

        ResponseModel responseModel = new ResponseModel();
        List<ProductModel> productModelList = new ArrayList<>();
        List<UserBag> userBags = userBagDao.getAllBagsOfHeroPiece(userAuth.getUserId());
        if (userBags != null){
            for (UserBag userBag : userBags) {
                Product product = productDao.getProductById(userBag.getProductId());
                ProductModel productModel = new ProductModel(product, userBag);

                HeroExtend heroExtend = heroExtendDao.getHeroExtendHeroPiece(product.getProductId());
                HeroExtend intoExtend = heroExtendDao.getHeroExtendIntoNum(heroExtend.getHeroId());
                if (intoExtend != null){
                    productModel.setIntoNum(Integer.parseInt(intoExtend.getVal()));
                }
                productModelList.add(productModel);
            }
        }
        responseModel.setData(productModelList);
        return responseModel;
    }
}
