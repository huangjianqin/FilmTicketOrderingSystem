package org.filmticketorderingsystem.controller.user;

import org.filmticketorderingsystem.bean.OrderBean;
import org.filmticketorderingsystem.bean.OrderDetailBean;
import org.filmticketorderingsystem.service.UserOrderingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 健勤 on 2016/7/5.
 */
@Controller
@RequestMapping(value = "/user/ordering")
public class UserOrderingController {

    @Resource(name = "userOrderingService")
    private UserOrderingService userOrderingService;

    public void setUserOrderingService(UserOrderingService userOrderingService) {
        this.userOrderingService = userOrderingService;
    }

    public UserOrderingService getUserOrderingService() {
        return userOrderingService;
    }

    /**
     *
     * @param filmSessionId
     * @param selectedSeat
     * @return
     */
    @RequestMapping(value = "/handInOrder.do")
    @ResponseBody
    public OrderDetailBean handInOrder(@RequestParam Integer filmSessionId,
                                 @RequestParam String selectedSeat,
                                 HttpServletRequest request){
        //获取当前登录用户id
        String id = (String)request.getSession().getAttribute("user_id");

        String[] selectedSeats = selectedSeat.split(",");

        String[] result = userOrderingService.handInOrder(id, filmSessionId, selectedSeats);

        if(result != null){
            OrderDetailBean orderDetailBean = new OrderDetailBean();

            if(result[0] == "1"){
                orderDetailBean.setOrderNum(result[1]);
            }
            //设置调用服务结果状态
            orderDetailBean.setState(Integer.valueOf(result[0]));

            return orderDetailBean;
        }

        return null;
    }

    @RequestMapping(value = "/pay.do")
    @ResponseBody
    public OrderBean pay(@RequestParam String orderNum, HttpServletRequest request){
        //获取当前登录用户id
        String id = (String)request.getSession().getAttribute("user_id");

        int flag = userOrderingService.pay(id, orderNum);

        OrderBean orderBean = new OrderBean();

        orderBean.setState(flag);

        return orderBean;
    }

}
