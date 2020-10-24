//package com.luobo.config;
//
//
//import io.undertow.Undertow;
//import io.undertow.UndertowOptions;
//import io.undertow.servlet.api.SecurityConstraint;
//import io.undertow.servlet.api.SecurityInfo;
//import io.undertow.servlet.api.TransportGuaranteeType;
//import io.undertow.servlet.api.WebResourceCollection;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//
//
//
//
///**
// * @ClassName UndertowHttpsConfig
// * @Description 配置开启http和https端口监听 采用Undertow作为服务器,支持Https服务配置
// * @Author davidmorgan
// * @Date 2020/10/16 14:59
// * @Version 1.0
// **/
//
//@Configuration
//public class UndertowHttpsConfig {
//    /**
//     * http服务端口
//     */
//    @Value("${custom.server.http.port}")
//    private Integer httpPort;
//
//    /**
//     * https服务端口
//     */
//    @Value("${server.port}")
//    private Integer httpsPort;
//
//
//    @Bean
//    public ServletWebServerFactory undertowFactory() {
//        UndertowServletWebServerFactory undertowFactory = new UndertowServletWebServerFactory();
//        undertowFactory.addBuilderCustomizers((Undertow.Builder builder) -> {
//            //同时监听http端口
//            builder.addHttpListener(httpPort, "0.0.0.0");
//            // 开启HTTP2
//            builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true);
//        });
//
//        // 开启HTTP自动跳转至HTTPS
//        // 此配置注释可同时支持http及https调用
//        undertowFactory.addDeploymentInfoCustomizers(deploymentInfo -> {
//            deploymentInfo.addSecurityConstraint(new SecurityConstraint()
//                .addWebResourceCollection(new WebResourceCollection().addUrlPattern("/*"))
//                .setTransportGuaranteeType(TransportGuaranteeType.CONFIDENTIAL)
//                .setEmptyRoleSemantic(SecurityInfo.EmptyRoleSemantic.PERMIT))
//                .setConfidentialPortManager(exchange -> httpsPort);
//        });
//        return undertowFactory;
//    }
//}
