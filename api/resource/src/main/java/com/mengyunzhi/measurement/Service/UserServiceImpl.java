package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.exception.AccessDeniedException;
import com.mengyunzhi.measurement.exception.ValidationException;
import com.mengyunzhi.measurement.repository.DepartmentRepository;
import com.mengyunzhi.measurement.repository.RoleRepository;
import com.mengyunzhi.measurement.repository.UserRepository;
import com.mengyunzhi.measurement.repository.WebAppMenuRepository;
import com.mengyunzhi.measurement.specs.UserSpecs;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

/**
 * Created by panjie on 17/5/18.
 * 用户
 */
@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SMSService smsService;  // 短信
    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentRepository departmentRepository; // 部门

    @Autowired
    private WebAppMenuRepository webAppMenuRepository;    // 前台菜单
    @Autowired
    private DistrictService districtService;     // 区域
    @Autowired
    RoleRepository roleRepository;

    @ApiModelProperty("当前登录认证用户")
    private Principal currentLoginPrincipalUser;

    @ApiModelProperty("当前登录用户")
    static private User currentTestLoginUser;

    @Override
    @ApiOperation("获取当前登录的认证用户")
    public Principal getCurrentLoginPrincipalUser() {
        currentLoginPrincipalUser = SecurityContextHolder.getContext().getAuthentication();
        return currentLoginPrincipalUser;
    }

    @Override
    @ApiOperation("获取当前登录的用户")
    public User getCurrentLoginUser() {
        if (null != this.currentTestLoginUser) {
            return this.currentTestLoginUser;
        } else {
            String username = getCurrentLoginPrincipalUser().getName();
            User currentLoginUser = userRepository.findOneByUsername(username);
            return currentLoginUser;
        }
    }

    @Override
    public List<User> getByDepartmentId(Long departmentId) {
        return userRepository.getByDepartmentId(departmentId);
    }


    @Override
    @ApiOperation("获取所有用户")
    public List<User> getAll() {
        List<User> list = new ArrayList<User>();
        list = (List<User>) userRepository.findAll();
        return list;
    }

    @Override
    @ApiOperation("获取所有用户")
    public User get(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    @ApiOperation("保存用户")
    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    @ApiOperation("更新用户")
    public void update(Long id, User user) {
        logger.info("查找要更新的实体是否存在");
        User oldUser = userRepository.findOne(id);
        if (oldUser == null) {
            throw new ObjectNotFoundException(404, "相关记录已删除或未找到");
        }
        logger.info("更新角色、状态、岗位信息");
        oldUser.setRoles(user.getRoles());
        oldUser.setStatus(user.getStatus());
        oldUser.setPosts(user.getPosts());

        userRepository.save(oldUser);
        return;

    }

    @Override
    @ApiOperation("删除用户")
    public void delete(Long id) {
        logger.info("查找要更新的实体是否存在");
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new ObjectNotFoundException(404, "要删除的实体不存在");
        } else {
            userRepository.delete(id);
        }
    }

    @Transactional
    @Override
    public List<WebAppMenu> getCurrentUserWebAppMenus() {
        Set<Long> roleIds = new HashSet<>();
        for (Role role : this.getCurrentLoginUser().getRoles()) {
            roleIds.add(role.getId());
        }

        List<WebAppMenu> webAppMenus = new ArrayList<>();
        if (roleIds.size() > 0) {
            webAppMenus = webAppMenuRepository.findAllByRoleIds(roleIds);
        }

        return webAppMenus;
    }

    @Override
    public void setCurrentLoginUser(User user) {
        this.currentTestLoginUser = user;
    }

    /**
     * 检测用户是否已存在
     *
     * @param username 用户名
     * @return
     * @author: panjie
     */
    @Override
    public boolean checkUsernameIsExist(String username) {
        logger.info("接收到的用户名：" + username);
        User user = userRepository.findOneByUsername(username);
        if (null == user) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 设置某个用户ID的状态为正常
     *
     * @param id 用户id
     * @author: panjie
     */
    @Override
    public void setUserStatusToNormalById(Long id) throws ObjectNotFoundException {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new ObjectNotFoundException(404, "不存在id为" + id.toString() + "的用户信息");
        }
        this.setUserStatusToNormal(user);
        return;
    }

    /**
     * 设置用户状态为正常
     *
     * @param user 用户
     * @author panjie
     */
    @Override
    public void setUserStatusToNormal(User user) {
        this.setUserStatus(user, 0);
        return;
    }

    /**
     * 设置用户的状态
     *
     * @param user   用户
     * @param status 状态: -1 未审核； 0 正常； 1 冻结; 2 其它
     * @author panjie
     */
    public void setUserStatus(User user, int status) {
        user.setStatus(status);
        userRepository.save(user);
        return;
    }




    /**
     * 用户注册
     *
     * @param user 用户
     * @author panjie
     */
    @Override
    public void register(User user) {
        try {
            userService.getCurrentLoginUser();
            logger.info("如果当前用户登录，则设置状态为0，否则设置不设置状态.TODO: 1.分解为两个方法 2. 进行权限判断");
            user.setStatus(0);

        } catch (NullPointerException e) {
        }

        if (user.getDepartment().getId() == null) {
            logger.info("如果未传入部门信息,则认为用户在注册用户的同时，还在注册部门，则生成一个新的部门信息");
            departmentRepository.save(user.getDepartment());
        }

        logger.info("自动添加系统角色");
        String roleName;
        switch (user.getDepartment().getDepartmentType().getName()) {
            case DepartmentType.NAME_GUAN_LI_BU_MEN:
                roleName = Role.NAME_GUAN_LI_BU_MEN;
                break;
            case DepartmentType.NAME_JI_SHU_JI_GOU:
                roleName = Role.NAME_JI_SHU_JI_GOU;
                break;
            case DepartmentType.NAME_QI_JU_YONG_HU:
                roleName = Role.NAME_QI_JU_YONG_HU;
                break;
            case DepartmentType.NAME_SHENG_CHANG_QI_YE:
                roleName = Role.NAME_SHENG_CHAN_QI_YE;
                break;
            default:
                roleName = "";
        }

        Role role = roleRepository.findOneByName(roleName);
        if (role != null) {
            user.addRole(role);
        }

        userRepository.save(user);
    }

    @Override
    public Page<User> getAllUser(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page;
    }

    @Autowired
    @Qualifier("DepartmentService")
    DepartmentService departmentService;

    @Override
    public User getOneSavedUser() {
        User user = new User();
        Department department = departmentService.getOneSavedDepartment();
        user.setDepartment(department);
        user.setUsername(CommonService.getRandomStringByLength(20) + "@" + CommonService.getRandomStringByLength(3) + ".con");
        user.setPassword(CommonService.getRandomStringByLength(40));
        user.setName("测试姓名");
        user.setStatus(0);
        userRepository.save(user);
        return user;
    }

    @Override
    public User loginWithOneUser() {
        User user = this.getOneSavedUser();
        this.setCurrentLoginUser(user);
        return user;
    }

    @Override
    public void clearCurrentTestLoginUser() {
        this.currentTestLoginUser = null;
    }

    @Override
    public void updatePasswordAndNameOfCurrentUser(User user) {
        logger.info("获取当前用户");
        User currentUser = this.getCurrentLoginUser();

        if (!currentUser.getPassword().equals(user.getPassword())) {
            logger.info("原密码错误");
            throw new SecurityException("原密码错误");
        }

        logger.info("更新用户密码和名称");
        currentUser.setName(user.getName());
        currentUser.setPassword(user.getRePassword());
        userRepository.save(currentUser);

        return;
    }

    @Override
    public void resetPasswordById(Long id) {
        logger.info("获取当前用户");
        User user = userRepository.findOne(id);
        if (null == user) {
            throw new ObjectNotFoundException(404, "不存在此用户");
        }

        logger.info("重置密码");
        user.setPassword("111111");
        userRepository.save(user);

        return;
    }

    @Override
    public void freezeById(Long id) {
        logger.info("查找用户");
        User user = userRepository.findOne(id);
        if (null == user) {
            logger.info("不存在此用户");
            throw new ObjectNotFoundException(404, "不存在此用户");
        }

        logger.info("设置用户的状态");
        this.setUserStatus(user, 1);
        return;
    }

    @Override
    public Page<User> pageAllByMapAndPageable(Map<String, Object> map, Pageable pageable) {
        Specification specification = UserSpecs.base(map);
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public Page<User> pageAllBySpecification(Long districtId, Long departmentTypeId, Integer status, String departmentName, String username, Long departmentId, Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        logger.info("传入了区域ID");
        if (null != districtId) {
            List<District> districts = districtService.getSonsListByDistrictId(districtId);
            map.put("districts", districts);
        }

        map.put("departmentTypeId", departmentTypeId);
        map.put("status", status);
        map.put("departmentName", departmentName);
        map.put("username", username);
        map.put("departmentId", departmentId);
        return this.pageAllByMapAndPageable(map, pageable);
    }

    @Override
    public Boolean checkPasswordIsRight(User user) {
        logger.info("获取当前用户");
        User currentUser = this.getCurrentLoginUser();

        logger.info("验证密码是否正确");
        if (currentUser.getPassword().equals(user.getPassword())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 获取在很短的时间内生效的token, 用于用户认证的token
     * 用于下载附件、生成excel、word报表并下载时的权限检查
     * @return
     * panjie
     */
    @Override
    public String getTempTokenOfCurrentUser() {
        logger.info("获取当前登录用户，并加入缓存，设置过期时间");
        User user = this.getCurrentLoginUser();
        String token = CommonService.getRandomStringByLength(30);
        MemoryCacheService.put(token, user, UserService.TOKEN_EFFECT_SMALL_TOKEN_CATCH_TIME);
        return token;
    }

    /**
     * 获取一个带有tempToken的hashMap, 其中token存在value 键中
     * @return
     * panjie
     */
    @Override
    public HashMap<String, String> getHashMapWithTempTokenOfCurrentUser() {
        String token = this.getTempTokenOfCurrentUser();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("value", token);
        return hashMap;
    }

    /**
     * 通过token获取对应的user
     * @param token
     * @return
     */
    @Override
    public User getUserByToken(String token) {
        logger.debug("通过token获取对应的用户");
        User user = (User) MemoryCacheService.get(token);
        return user;
    }

    @Override
    public void resetPasswordByUsernameAndMobile(String username, String mobile) throws AccessDeniedException, ValidationException {
        logger.debug("较验手机号");
        if (!SMSService.isMobile(mobile)) {
            throw new ValidationException("手机号码格式不正确");
        }

        logger.debug("查看该用户名，是否最近进行重置请求");
        String cacheKey = "User_resetPasswordByUsernameAndMobile_" + username;
        Boolean requested = (Boolean) MemoryCacheService.get(cacheKey);
        if (requested != null) {
            throw new AccessDeniedException("您请求的频率过高，每60秒内只能请求一次");
        }

        MemoryCacheService.put(cacheKey, true, 60);
        logger.debug("查找用户名,手机号对应的用户");
        User user = userRepository.findByUsernameAndMobile(username, mobile);
        if (null == user) {
            throw new AccessDeniedException("用户名或手机号不正确");
        }

        logger.debug("用户名密码正确，设置随机密码，并发送短信");
        String password = CommonService.getRandomStringByLength(6);
        user.setPassword(password);
        userRepository.save(user);

        try {
            smsService.sendSMSByPhoneNumberAndContent(user.getMobile(), "您的新密码为：" + password);
        } catch (IOException iOException) {
            logger.error("发送短信发生错误");
        }
    }


}