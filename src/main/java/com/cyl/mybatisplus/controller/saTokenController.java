package com.cyl.mybatisplus.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class saTokenController {

    // 测试登录，浏览器访问： /doLogin?username=zhang&password=123456
    @RequestMapping("/doLogin")
    public SaResult doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            //System.out.println(StpUtil.getLoginId());       //就是登录时调用login的ID
            //System.out.println(StpUtil.getTokenValue());    //返回给的token

            return SaResult.ok("登录成功");
        }
        return SaResult.error("登陆失败");
    }

    // 查询登录状态  ---- /isLogin
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        System.out.println("查询登录状态——login的唯一ID: "+StpUtil.getLoginId());       //就是登录时调用login的唯一ID
        System.out.println("查询登录状态——会话的token: "+StpUtil.getTokenValue());
        //token指向唯一tokeninfo对象，里面包装了各种账号细节
        //token 都封装到cookie里面，对应key - value

        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 校验当前登录状态  ---- http://localhost:8081/acc/checkLogin
    @RequestMapping("checkLogin")
    public SaResult checkLogin() {
        // 检验当前会话是否已经登录, 如果未登录，则抛出异常：`NotLoginException`
        StpUtil.checkLogin();

        // 抛出异常后，代码将走入全局异常处理（GlobalException.java），如果没有抛出异常，则代表通过了登录校验，返回下面信息
        return SaResult.ok("校验登录成功，这行字符串是只有登录后才会返回的信息");
    }



    // 查询 Token 信息  ---- /tokenInfo
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        // TokenName 是 Token 名称的意思，此值也决定了前端提交 Token 时应该使用的参数名称
        // token 存储到 cookie 里面的名字
        String tokenName = StpUtil.getTokenName();
        System.out.println("前端提交 Token 时应该使用的参数名称：" + tokenName);   //satoken
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- /logout
    @RequestMapping("logout")
    public SaResult logout() {
        // 退出登录会清除三个地方的数据：
        // 		1、Redis中保存的 Token 信息
        // 		2、当前请求上下文中保存的 Token 信息
        // 		3、Cookie 中保存的 Token 信息（如果未使用Cookie模式则不会清除）
        StpUtil.logout();

        // StpUtil.logout() 在未登录时也是可以调用成功的，
        // 也就是说，无论客户端有没有登录，执行完 StpUtil.logout() 后，都会处于未登录状态
        System.out.println("当前是否处于登录状态：" + StpUtil.isLogin());

        // 返回给前端
        return SaResult.ok("退出登录成功");
    }
}
