package org.filmticketorderingsystem.controller.user;

import org.filmticketorderingsystem.bean.OrderBean;
import org.filmticketorderingsystem.bean.OrderDetailBean;
import org.filmticketorderingsystem.bean.UserBean;
import org.filmticketorderingsystem.bean.json.OrderBeanList;
import org.filmticketorderingsystem.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 健勤 on 2016/6/30.
 */
@Controller
@RequestMapping(value = "/user/info")
public class UserInfoController {
    @Resource(name = "userInfoService")
    private UserInfoService userInfoService;

    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @RequestMapping(value = "/getNowUser.do")
    @ResponseBody
    public UserBean getNowUser(HttpServletRequest request){
        //获取当前登录用户id
        String id = (String)request.getSession().getAttribute("user_id");

        UserBean userBean = new UserBean();
        userBean.setState(-1);

        if(id != null && !id.equals("")){
            String userName = (String)request.getSession().getAttribute("userName");
            userBean.setUserName(userName);
            userBean.setId(id);
            userBean.setState(1);
        }

        return userBean;
    }


    @RequestMapping(value = "/login.do")
    public @ResponseBody UserBean login(@RequestParam String id,
                                      @RequestParam String password, HttpServletRequest request){
        String userName = userInfoService.login(id, password);
        int flag = (userName == null)? -1 : 1;

        //登录成功,session记录用户Id
        if(flag == 1){
            request.getSession().setAttribute("user_id", id);
            request.getSession().setAttribute("userName", userName);
        }

        UserBean userBean = new UserBean();
        //返回用户名用于显示
        userBean.setUserName(userName);
        //设置调用服务结果状态
        userBean.setState(flag);

        return userBean;
    }

    @RequestMapping(value = "/logout.do")
    public @ResponseBody UserBean logout(HttpServletRequest request){
        int flag = userInfoService.logout(request);
        UserBean userBean = new UserBean();
        //设置调用服务结果状态
        userBean.setState(flag);
        return userBean;
    }

    @RequestMapping(value = "/register.do")
    public @ResponseBody UserBean register(@RequestParam String id,
                                           @RequestParam String userName,
                                           @RequestParam String password,
                                           HttpServletRequest request){

        int flag = userInfoService.regiser(id, userName, password);

        UserBean userBean = new UserBean();

        //设置调用服务结果状态
        userBean.setState(flag);

        //注册成功
        if(flag == 1){
            //注册成功后自动登录
            request.getSession().setAttribute("user_id", id);
        }

        return userBean;
    }

    @RequestMapping(value = "/getUserInfo.do")
    public @ResponseBody UserBean getUserInfo(HttpServletRequest request){
        //获取当前登录用户id
        String id = (String)request.getSession().getAttribute("user_id");

        UserBean userBean = userInfoService.getUserInfo(id);
        int flag = -1;

        //查询成功
        if(userBean != null){
            flag = 1;
        }

        //设置调用服务结果状态
        userBean.setState(flag);

        return userBean;
    }

    @RequestMapping(value = "/modifyUserInfo.do")
    public @ResponseBody UserBean modifyUserInfo(@RequestParam String userName, HttpServletRequest request){
        //获取当前登录用户id
        String id = (String)request.getSession().getAttribute("user_id");

        int flag = userInfoService.modifyUserInfo(id, userName);

        UserBean userBean = new UserBean();
        //设置调用服务结果状态
        userBean.setState(flag);

        return  userBean;

    }

    @RequestMapping(value = "/modifyPassword.do")
    public @ResponseBody UserBean modifyPassword(@RequestParam String oldPassword,
                                                 @RequestParam String newPassword,
                                                 HttpServletRequest request){
        //获取当前登录用户id
        String id = (String)request.getSession().getAttribute("user_id");

        int flag = userInfoService.modifyPassword(id, oldPassword, newPassword);

        UserBean userBean = new UserBean();
        //设置调用服务结果状态
        userBean.setState(flag);

        return  userBean;

    }

    @RequestMapping(value = "/recharge.do")
    public @ResponseBody UserBean recharge(@RequestParam double money, HttpServletRequest request){
        //获取当前登录用户id
        String id = (String)request.getSession().getAttribute("user_id");

        double[] result = userInfoService.recharge(id, money);

        UserBean userBean = new UserBean();
        //设置调用服务结果状态
        userBean.setState((int)result[0]);

        //充值成功,要返回最新的钱包数额
        if(result[0] == 1){
            userBean.setWallet(result[1]);
        }

        return userBean;
    }

    @RequestMapping(value = "/getOrders.do")
    public OrderBeanList getOrders(HttpServletRequest request){
        //获取当前登录用户id
        String id = (String)request.getSession().getAttribute("user_id");

        List<OrderBean> orderBeans = userInfoService.getOrders(id);
        int flag = -1;

        OrderBeanList orderBeanList = new OrderBeanList();

        if(orderBeans != null){
            flag = 1;
            orderBeanList.setOrderBeans(orderBeans);
        }
        //设置调用服务结果状态
        orderBeanList.setState(flag);

        return orderBeanList;

    }

    @RequestMapping(value = "/getOrderDetail.do")
    public OrderDetailBean getOrderDetail(@RequestParam String orderNum){
        OrderDetailBean orderDetailBean = userInfoService.getOrderDetail(orderNum);
        int flag = -1;

        if(orderDetailBean != null){
            flag = 1;
        }

        orderDetailBean.setState(flag);

        return orderDetailBean;
    }

}