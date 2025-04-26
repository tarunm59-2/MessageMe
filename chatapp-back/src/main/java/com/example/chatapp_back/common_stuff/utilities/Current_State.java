package com.example.chatapp_back.common_stuff.utilities;

public class Current_State<D, E> {
    private D val;
    private E error;
    private Notification curr_status;

    public Current_State(Notification curr_status, D val, E err) {
        this.val = val;
        this.curr_status = curr_status;
        this.error = err;
        
    }

    public D getValue() {
        return this.val;
    }

    public void setValue(D val) {
        this.val = val;
    }

    public E getError() {
        return this.error;
    }

    public void setError(E error) {
        this.error = error;
    }

    public Notification get_curr_status() {
        return this.curr_status;
    }

    public void set_curr_status(Notification status) {
        this.curr_status = status;
    }

    public static <D,E> Current_State_Builder<D,E> builder() {
        return new Current_State_Builder<>();
    }

}