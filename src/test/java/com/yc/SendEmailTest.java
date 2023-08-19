package com.yc;

/**
 * @Author zp
 * @Date 2023/8/15 19:44
 * @PackageName:com.yc
 * @ClassName: SendTemplateEmailTest
 * @Description:
 * @Version 1.0
 */
import com.yc.controller.SendEmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

//@SpringBootTest
class SendEmailTest {
    @Autowired
    private SendEmailService sendEmailService;

//    @Test
//    public void sendSimpleMailTest() {
//        String to="12345678@qq.com";//这里修改为你能接收到的邮箱
//        String subject="【纯文本邮件】标题";
//        String text="嘟嘟嘟.....";
//        // 发送简单邮件
//        sendEmailService.sendSimpleEmail(to,subject,text);
//    }
//    @Test
//    public void sendComplexEmailTest() {
//        //根据前面定义的复杂邮件发送业务定制各种参数
//        String to="12345678@qq.com";//修改为你自己的邮件方便接收查看
//        String subject="【复杂邮件】标题";
//        // 定义邮件内容
//        StringBuilder text = new StringBuilder();
//        //对邮件内容使用了HTML标签编辑邮件内容
//        text.append("<html><head></head>");
//        text.append("<body><h1>二月二龙抬头！</h1>");
//        // cid为嵌入静态资源文件关键字的固定写法，如果改变将无法识别；rscId则属于自定义的静态资源唯一标识，一个邮件内容中可能会包括多个静态资源，该属性是为了区别唯一性的。
//        String rscId = "img001";
//        text.append("<img src='cid:" +rscId+"'/></body>");
//        text.append("</html>");
//        // 指定静态资源文件和附件路径
//        String rscPath="D:\\1.jpg";//注意这里修改为你的硬盘中有的资源
//        String filePath="D:\\hahaha.txt";//注意这里修改为你的硬盘中有的资源
//        // 发送复杂邮件
//        sendEmailService.sendComplexEmail(to,subject,text.toString(),filePath,rscId,rscPath);
//    }
//
//    @Autowired
//    private TemplateEngine templateEngine;
//
//    @Test
//    public void sendTemplateEmailTest() {
//        String to="12345678@qq.com";
//        String subject="【模板邮件】标题";
//        // 使用模板邮件定制邮件正文内容
//        Context context = new Context();//Context注意正确导入“import org.thymeleaf.context.Context;”
//        context.setVariable("username", "石头");
//        context.setVariable("code", "456123");
//        // 使用TemplateEngine设置要处理的模板页面
//        String emailContent = templateEngine.process("emailTemplate_vercode", context);
//        // 发送模板邮件
//        sendEmailService.sendTemplateEmail(to,subject,emailContent);
//    }
}
