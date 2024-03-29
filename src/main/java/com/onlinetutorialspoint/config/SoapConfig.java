package com.onlinetutorialspoint.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import sun.java2d.pipe.SpanShapeRenderer;

@Configuration
@EnableWs
public class SoapConfig {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServletServlet(ApplicationContext applicationContext){
        MessageDispatcherServlet dispatcherServlet = new MessageDispatcherServlet();
        dispatcherServlet.setApplicationContext(applicationContext);
        dispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<MessageDispatcherServlet>(dispatcherServlet,"/ws/*");
    }
    @Bean(name="item")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("Item");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://onlinetutorialspoint.com/generated");
        wsdl11Definition.setSchema(xsdSchema);
        return wsdl11Definition;
    }
    @Bean
    public XsdSchema xsdSchema(){
        return new SimpleXsdSchema(new ClassPathResource("item-details.xsd"));
    }
}
