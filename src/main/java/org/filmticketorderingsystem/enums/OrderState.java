package org.filmticketorderingsystem.enums;

/**
 * Created by 健勤 on 2016/5/12.
 */
public enum OrderState {
    ORDER_UNAVAILABLE(-1, "订单失效"),ORDER_NOT_PAID(0, "订单未支付"),ORDER_FINISHED(1, "订单完成");

    private int state;
    private String stateStr;

    OrderState(int state, String stateStr) {
        this.state = state;
        this.stateStr = stateStr;
    }

    public int getState() {
        return state;
    }

    public String getStateStr() {
        return stateStr;
    }
}
