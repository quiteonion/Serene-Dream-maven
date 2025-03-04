package day20240805;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;

public class MyEmailUtil {
    // 1. state (filed、variable)
    /*
    private static final String FROM = "你自己的邮箱地址@xx.com";
    private static final String SECRET_TOKEN = "开启邮箱发送服务之后获取到的授权码（注意：不是登录秘密哦）";
    */

    private static final String FROM = "3345973813@qq.com"; // 邮件发送者的身份
    private static final String SECRET_TOKEN = "fclanbjkpyhlchab"; // 不是你的QQ邮箱登录密码，是要找QQ申请的密钥

    // 2. behavior (method、function)
    public static void send(String email, String content)  throws Exception {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.qq.com");
        prop.put("mail.smtp.port", "587");

        // https://wx.mail.qq.com/list/readtemplate?name=app_intro.html#/agreement/authorizationCode
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, SECRET_TOKEN);
            }
        });

        Message message = new MimeMessage(session);
        // who you are
        message.setFrom(new InternetAddress(FROM));
        // send to ...
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

        message.setSubject("来自 Java 的提醒");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

        System.out.println("邮件发送成功");
    }

    // 3. constructor
    public static void main(String[] args) throws Exception {
        MyEmailUtil.send("3345973813@qq.com", "这是一段测试消息aaabbb");
    }
}
