package org.forbes;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableZuulProxy
public class ForbesApplication {

	
	/***启动类
	 * main方法慨述:
	 * @param args void
	 * @创建人 huanghy
	 * @创建时间 2019年5月13日 上午10:49:22
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	 public static void main(String[] args) {
	    	SpringApplication.run(ForbesApplication.class, args);
	    }
	 
	 
	 /****
      * multipartConfigElement方法慨述:文件上传大小限制
      * @return MultipartConfigElement
      * @创建人 huanghy
      * @创建时间 2019年9月11日 下午2:53:36
      * @修改人 (修改了该文件，请填上修改人的名字)
      * @修改日期 (请填上修改该文件时的日期)
      */
	  @Bean
	  public MultipartConfigElement multipartConfigElement() {
	      MultipartConfigFactory factory = new MultipartConfigFactory();
	      //单个文件最大
	      factory.setMaxFileSize("30MB"); //KB,MB
	      /// 设置总上传数据总大小
	      factory.setMaxRequestSize("30MB");
	      return factory.createMultipartConfig();
	 }
}
