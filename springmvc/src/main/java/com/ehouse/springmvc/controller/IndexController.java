package com.ehouse.springmvc.controller;

import com.ehouse.springmvc.entity.OrderInfo;
import com.ehouse.springmvc.service.OrderInfoService;
import com.ehouse.springmvc.utils.UUIDutil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private OrderInfoService orderInfoService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView index(@RequestParam(value = "name") String name) {
        System.out.println(name); ModelAndView mav = new ModelAndView();
        mav.setViewName("mav");
        mav.addObject("out", name + "out");
        mav.addObject("outfield", name);
        Map<String, String> result = new HashMap<>(2);
        result.put("out", name + " mavout");
        mav.addObject("mav", result + "resultmap");

        String versionNo = "v1";
        //save
        OrderInfo oi = new OrderInfo();
        oi.setOrderId("No.---");
        oi.setDescription("This a orderInfo by auto generator!--" + UUIDutil.getuuid());
        oi.setVersionNo(versionNo);
        orderInfoService.saveOrderInfo(oi);

        try {
            //saveBatch
            List<OrderInfo> list = new ArrayList<>();
            for (int i = 0 ; i < 100; i++) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrderId("No."+i);
                orderInfo.setDescription("This a orderInfo by auto generator!--" + UUIDutil.getuuid());
                orderInfo.setVersionNo(versionNo);

                list.add(orderInfo);
            }
            orderInfoService.saveBatch(list);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("***************************** has transaction");
            List<OrderInfo> exists = orderInfoService.findAll();
            System.out.println(exists.size());
        }


        try {
            //saveBatch
            List<OrderInfo> list = new ArrayList<>();
            for (int i = 0 ; i < 100; i++) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrderId("No."+i);
                orderInfo.setDescription("no transaction!--" + UUIDutil.getuuid());
                orderInfo.setVersionNo(versionNo);

                list.add(orderInfo);
            }
            orderInfoService.testSaveBatch(list);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("######################## no transaction !");
            List<OrderInfo> exists = orderInfoService.findAll();
            System.out.println(exists.size());
        }


        //findById
        OrderInfo exist = orderInfoService.findMin();
        System.out.println(exist.toString());

        //findAll
        List<OrderInfo> exists = orderInfoService.findAll();
        System.out.println(exists.size());

        return mav;
    }


    @RequestMapping(value = "/index2", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> index2(HttpServletRequest request, @RequestParam(value = "name",required = false) String name) {
        Map<String, String> map = new HashMap<>();
        map.put("a",name+" aa");
        map.put("b",name+" bb");
        map.put("c",name+" cc");
        map.put("d",name+" dd");

        ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) ac;
        //Bean的实例工厂
        DefaultListableBeanFactory dbf = (DefaultListableBeanFactory) context.getBeanFactory();
        //Bean构建  BeanService.class 要创建的Bean的Class对象
        BasicDataSource test = (BasicDataSource)dbf.getBean("dataSource");
        System.out.println(test.toString());

        this.setBean(request);
        OrderInfo orderInfo = (OrderInfo)dbf.getBean("orderInfo1");
        System.out.println(orderInfo.getOrderId());
        return map;
    }

    @RequestMapping(value = "/index3")
    @ModelAttribute("name")
    public String index3(@RequestParam(required = false) String name,Model model) {
        return name;
    }

    private void setBean(HttpServletRequest request){
        //将applicationContext转换为ConfigurableApplicationContext
//        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) SpringContextUtil.getApplicationContext();
        ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) ac;
        // 获取bean工厂并转换为DefaultListableBeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();

        // 通过BeanDefinitionBuilder创建bean定义
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(OrderInfo.class);

        // 设置属性userService,此属性引用已经定义的bean:userService,这里userService已经被spring容器管理了.
//        beanDefinitionBuilder.addPropertyReference("indexController", "indexController");
        beanDefinitionBuilder.addPropertyValue("orderId","SHO2018");

        // 注册bean
        defaultListableBeanFactory.registerBeanDefinition("orderInfo1", beanDefinitionBuilder.getRawBeanDefinition());

    }
}
