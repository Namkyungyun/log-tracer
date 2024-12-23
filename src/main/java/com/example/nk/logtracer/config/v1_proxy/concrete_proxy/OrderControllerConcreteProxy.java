package com.example.nk.logtracer.config.v1_proxy.concrete_proxy;

import com.example.nk.logtracer.app.proxy.v2.ProxyOrderControllerV2;
import com.example.nk.logtracer.threadlocal.TraceStatus;
import com.example.nk.logtracer.threadlocal.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends ProxyOrderControllerV2 {

    private final ProxyOrderControllerV2 target;
    private final LogTrace logTrace;

    public OrderControllerConcreteProxy(ProxyOrderControllerV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderControllerConcreteProxy.request");
            final String result = target.request(itemId);
            logTrace.end(status);

            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
