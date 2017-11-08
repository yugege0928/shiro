package com.lanou.shiro;

import com.lanou.bean.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yugege on 17/11/7.
 */
public class MyRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "hi";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String) authenticationToken.getPrincipal();

        if (!("zyy".equals(username))){
            throw new UnknownAccountException("用户名不对");
        }

        String password = new String((char[]) authenticationToken.getCredentials());

        if (!("123").equals(password)){
            throw new IncorrectCredentialsException("密码不对");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return new SimpleAuthenticationInfo(username,password,getName());
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User) principalCollection.getPrimaryPrincipal();

        //可以获取user的用户id及各种信息;
        List<String> perList =
                Arrays.asList("user:query","user:update");

        SimpleAuthorizationInfo info =
                new SimpleAuthorizationInfo();

        for (String per : perList){
            info.addStringPermission(per);
        }

        return info;
    }
}
