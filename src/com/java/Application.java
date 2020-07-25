package com.java;

import com.java.controller.SQLController;
import com.java.goods.ChangeGoodsQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.*;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import javax.servlet.descriptor.JspConfigDescriptor;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;


@EnableAutoConfiguration
@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan({"com.java.controller"})
@ComponentScan({"com.java.Customer"})
@ComponentScan({"com.java.goods"})
//@PropertySource("C:\\Users\\Ilya\\Desktop\\Yoink\\src\\com\\resources\\application.properties")
//@EnableJpaRepositories(basePackages = "com.java.Repository")
   // @ServletComponentScan({"com.java.WEB-INF"})
class Application {
/*
    public void onStartUp(ServletContext servletContext) throws ServletException{
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(SpringConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);

        ServletRegistration.Dynamic myCustomDispatcherServlet = servletContext.addServlet("myDispatcherServlet", dispatcherServlet);

        myCustomDispatcherServlet.setLoadOnStartup(1);
        myCustomDispatcherServlet.addMapping("/localhost:8080/");
    }
*/
    public static void main(String[] args){
        ServletContext servletContext = new ServletContext() {
            @Override
            public String getContextPath() {
                return null;
            }

            @Override
            public ServletContext getContext(String s) {
                return null;
            }

            @Override
            public int getMajorVersion() {
                return 0;
            }

            @Override
            public int getMinorVersion() {
                return 0;
            }

            @Override
            public int getEffectiveMajorVersion() {
                return 0;
            }

            @Override
            public int getEffectiveMinorVersion() {
                return 0;
            }

            @Override
            public String getMimeType(String s) {
                return null;
            }

            @Override
            public Set<String> getResourcePaths(String s) {
                return null;
            }

            @Override
            public URL getResource(String s) throws MalformedURLException {
                return null;
            }

            @Override
            public InputStream getResourceAsStream(String s) {
                return null;
            }

            @Override
            public RequestDispatcher getRequestDispatcher(String s) {
                return null;
            }

            @Override
            public RequestDispatcher getNamedDispatcher(String s) {
                return null;
            }

            @Override
            public Servlet getServlet(String s) throws ServletException {
                return null;
            }

            @Override
            public Enumeration<Servlet> getServlets() {
                return null;
            }

            @Override
            public Enumeration<String> getServletNames() {
                return null;
            }

            @Override
            public void log(String s) {

            }

            @Override
            public void log(Exception e, String s) {

            }

            @Override
            public void log(String s, Throwable throwable) {

            }

            @Override
            public String getRealPath(String s) {
                return null;
            }

            @Override
            public String getServerInfo() {
                return null;
            }

            @Override
            public String getInitParameter(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getInitParameterNames() {
                return null;
            }

            @Override
            public boolean setInitParameter(String s, String s1) {
                return false;
            }

            @Override
            public Object getAttribute(String s) {
                return null;
            }

            @Override
            public Enumeration<String> getAttributeNames() {
                return null;
            }

            @Override
            public void setAttribute(String s, Object o) {

            }

            @Override
            public void removeAttribute(String s) {

            }

            @Override
            public String getServletContextName() {
                return null;
            }

            @Override
            public ServletRegistration.Dynamic addServlet(String s, String s1) {
                return null;
            }

            @Override
            public ServletRegistration.Dynamic addServlet(String s, Servlet servlet) {
                return null;
            }

            @Override
            public ServletRegistration.Dynamic addServlet(String s, Class<? extends Servlet> aClass) {
                return null;
            }

            @Override
            public ServletRegistration.Dynamic addJspFile(String s, String s1) {
                return null;
            }

            @Override
            public <T extends Servlet> T createServlet(Class<T> aClass) throws ServletException {
                return null;
            }

            @Override
            public ServletRegistration getServletRegistration(String s) {
                return null;
            }

            @Override
            public Map<String, ? extends ServletRegistration> getServletRegistrations() {
                return null;
            }

            @Override
            public FilterRegistration.Dynamic addFilter(String s, String s1) {
                return null;
            }

            @Override
            public FilterRegistration.Dynamic addFilter(String s, Filter filter) {
                return null;
            }

            @Override
            public FilterRegistration.Dynamic addFilter(String s, Class<? extends Filter> aClass) {
                return null;
            }

            @Override
            public <T extends Filter> T createFilter(Class<T> aClass) throws ServletException {
                return null;
            }

            @Override
            public FilterRegistration getFilterRegistration(String s) {
                return null;
            }

            @Override
            public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
                return null;
            }

            @Override
            public SessionCookieConfig getSessionCookieConfig() {
                return null;
            }

            @Override
            public void setSessionTrackingModes(Set<SessionTrackingMode> set) {

            }

            @Override
            public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
                return null;
            }

            @Override
            public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
                return null;
            }

            @Override
            public void addListener(String s) {

            }

            @Override
            public <T extends EventListener> void addListener(T t) {

            }

            @Override
            public void addListener(Class<? extends EventListener> aClass) {

            }

            @Override
            public <T extends EventListener> T createListener(Class<T> aClass) throws ServletException {
                return null;
            }

            @Override
            public JspConfigDescriptor getJspConfigDescriptor() {
                return null;
            }

            @Override
            public ClassLoader getClassLoader() {
                return null;
            }

            @Override
            public void declareRoles(String... strings) {

            }

            @Override
            public String getVirtualServerName() {
                return null;
            }

            @Override
            public int getSessionTimeout() {
                return 0;
            }

            @Override
            public void setSessionTimeout(int i) {

            }

            @Override
            public String getRequestCharacterEncoding() {
                return null;
            }

            @Override
            public void setRequestCharacterEncoding(String s) {

            }

            @Override
            public String getResponseCharacterEncoding() {
                return null;
            }

            @Override
            public void setResponseCharacterEncoding(String s) {

            }
        };
        SpringApplication.run(Application.class, args);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    }



}


