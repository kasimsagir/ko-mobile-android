package com.kinght.commerce.data.network;


public class NetworkError extends Throwable {
    public static String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    public static String NETWORK_ERROR_MESSAGE = "Can not connect to server";

    private final Throwable error;

    public NetworkError(Throwable e) {
        super(e);
        this.error = e;
    }

    public String getMessage() {
        return error.getMessage();
    }


    public void response(ServiceCallback serviceCallback) {
    serviceCallback.onError(500,"Err");

    }

    public Throwable getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkError that = (NetworkError) o;

        return error != null ? error.equals(that.error) : that.error == null;

    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }
}
