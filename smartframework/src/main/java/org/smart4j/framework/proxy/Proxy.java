package org.smart4j.framework.proxy;

/**
 * 代理接口
 * Created by nanca on 12/26/2017.
 */
public interface Proxy {
    /**
     * 执行链式代理
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
