package com.uway.sports.web.serviceImpl;

import com.uway.sports.web.entity.paraentity.ProductInfoTable;
import com.uway.sports.web.entity.paraentity.TmpProduct;
import com.uway.sports.web.service.IPandAService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhao on 2018/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-mvc.xml"})
public class PandAServiceImplTest {
    private final Logger logger = LoggerFactory.getLogger(PandAServiceImplTest.class);
    @Autowired
    private IPandAService pandAService;
    @Test
    public void checkProductUrl() throws Exception {
        pandAService.checkProductUrl();
    }
    @Test
    public void getProductList(){
        TmpProduct condition = new TmpProduct();
        condition.setRowStart(0);
        condition.setPageSize(1280);
        condition.setStatus(1);
        List<ProductInfoTable> infoList = pandAService.getProductList(condition);
        Assert.assertNotNull(infoList);

    }


    @Test
    public void httpCilentGet(){
    }
}