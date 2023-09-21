package com.imooc.service.impl;

import com.imooc.enums.SexEnum;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UsersBO;
import com.imooc.service.UsersService;
import lombok.SneakyThrows;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;
    @Override
    public List<Users> select(Users users) {
        List<Users> select = usersMapper.select(users);
        return select;
    }

    /*
        REQUIRED(0),使用当前事物，如果当前没有事物，则会自己新建一个事物，子方法是必须运行在一个事物当中的，如果有事物，则加入这个事物，成为一个整体
                    举例子：领导没饭吃，我有钱，我会自己买了自己吃，如果领导有的吃，就会跟着领导一起吃
        SUPPORTS(1),如果当前有事物，则使用事物，如果当前没有事物，则不使用事物
                    举例子，领导有饭吃 我也有饭吃，领导没饭吃 我也没饭吃
        MANDATORY(2), 该传播属性强制必须存在一个事物，如果没有事物会报错
                    举例子 领导必须管饭，没有饭吃就不开心
        REQUIRES_NEW(3),如果当前有事物则挂起这个事物，如果没有事物，则同Required
                    举例子：领导有饭吃，我偏不要，我自己买了吃
                    如果父方法带有事物，子方法REQUIRES_NEW，那么子方法报错 其实也会抛出异常导致父方法监听到异常回滚
        NOT_SUPPORTED(4),如果当前有事物，则把事物挂起，自己不使用事物去处理
        NEVER(5),如果有事物，会报错，类似于不支持事物的意思
        NESTED(6);有事物 就当作一个子事物，没有事物 就是REQUIRED，和REQUIRES_NEW的区别是，如果主方法异常 nested是会跟着回滚的，REQUIRES_NEW不会
                    这是一个嵌套事物，如果主事物提交，会携带子事物一起提交
                    如果主事物回滚，则子事物会一起回滚，相反子事物异常，则主事物可以回滚或者不回滚，不回滚可以把trycatch放到父事物调用到子事物的代码上
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert(Users users) {
        usersMapper.insert(users);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean queryUserNameIsExist(String name) {
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",name);
        return usersMapper.selectOneByExample(example) != null;
    }

    @Override
    @Transactional
    @SneakyThrows
    public Users createUsers(UsersBO usersBO)  {
        Users user = new Users();
        user.setUsername(usersBO.getUsername());
        user.setPassword(usersBO.getPassword());
        user.setFace("test");
        user.setNickname(usersBO.getUsername());
        user.setBirthday(new Date());
        user.setSex(SexEnum.SECRET.getCode());
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        user.setId(sid.nextShort());
        usersMapper.insert(user);
        user.setPassword(null);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username",username);
        criteria.andEqualTo("password",password);
        return usersMapper.selectOneByExample(userExample);
    }
}
