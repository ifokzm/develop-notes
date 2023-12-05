package com.github.sailboat.notes.demos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.sailboat.notes.demos.vo.ProductInfoQueryVO;

@RestController
@RequestMapping("/product/product-info")
public class ProductInfoController {

    public ProductInfoQueryVO findById(Integer id) {
        return null;
    }

    public IPage findPage(Page page, ProductInfoQueryVO vo) {
        return page;
    }

}
