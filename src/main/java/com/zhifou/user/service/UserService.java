package com.zhifou.user.service;
/**
 * @program: zhifou
 * @description: userService
 * @author: 景光赞
 * @create: 2020-04-07 20:53
 **/
import com.zhifou.annotation.Encode;
import com.zhifou.entity.User;
import com.zhifou.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
/**
 * @description:
 * @author :景光赞
 * @date :2020/4/15 17:31
 */
@Service
@Transactional(readOnly = true)
public class UserService {
    @Resource
    private UserRepository userRepository;

    @Encode(description = "登录")
    public User findUserByAccountAndPassword(String account, String password){
        return userRepository.findUserByAccountAndPassword(account,password);
    }

    public User findUserByAccount(String account){
        return userRepository.findUserByAccount(account);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findUserById(int id){
        return userRepository.findUserById(id);
    }

    @Encode(description = "注册")
    @Transactional
    public int regist(User user){
        return userRepository.save(user).getId();
    }

    /**
     * @Author li
     * @param email
     * @param password
     * @return boolean
     * @Description 修改密码
     * @Date 21:14 2020/4/9
     **/
    @Encode(description = "修改密码")
    @Transactional
    public boolean changePassword(String email, String password) {
        try {
            User user = userRepository.findUserByAccount(email);
            user.setPassword(password);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Transactional(readOnly = false)
    public int updateUser(User user){
        return userRepository.saveAndFlush(user).getId();
    }

    public List<User> findRelativeUsers(int typeId){
        return userRepository.findRelativeUsers(typeId);
    }
}
