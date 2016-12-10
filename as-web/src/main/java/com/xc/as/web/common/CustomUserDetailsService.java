package com.xc.as.web.common;

import com.xc.as.core.model.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yxc on 2016/12/11.
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    protected MongoTemplate mongo;

    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        UserDetails user = null;

        try {

            Query query = new Query(Criteria.where("user").is(account));
            AdminUser dbUser = mongo.findOne(query, AdminUser.class);

            if (dbUser != null) {
                user = new User(dbUser.getUser(), dbUser.getPassword().toLowerCase(), true, true, true, true, getAuthorities(dbUser.getRole()));
            }

        } catch (Exception e) {
            throw new UsernameNotFoundException("Error in retrieving user");
        }

        return user;
    }

    public Collection<GrantedAuthority> getAuthorities(int role) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

        authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (0 < role) {
            //添加其他角色
        }
        return authList;
    }
}
