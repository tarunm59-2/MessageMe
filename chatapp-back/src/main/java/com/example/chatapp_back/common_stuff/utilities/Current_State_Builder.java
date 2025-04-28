package com.example.chatapp_back.common_stuff.utilities;

public class Current_State_Builder<D, E> {
    private Notification status;
    private D value;
    private E error;

    public Current_State<D, E> ErrorCase(E error) {
        this.error = error;
        this.status = Notification.BLEEP;
        return new Current_State<>(this.status, this.value, this.error);
    }

    public Current_State<D, E> SuccessCase() {
        this.status = Notification.OK;
        return new Current_State<>(this.status, this.value, this.error);
    }

    public Current_State<D, E> SuccessCase(D value) {
        this.value = value;
        this.status = Notification.OK;
        return new Current_State<>(this.status, this.value, this.error);
    }
    public Current_State<D,E> UnauthorizedCase(E error) {
        this.error = error;
        this.status = Notification.UNAUTHORIZED;
        return new Current_State<>(this.status, this.value, this.error);
    }

}