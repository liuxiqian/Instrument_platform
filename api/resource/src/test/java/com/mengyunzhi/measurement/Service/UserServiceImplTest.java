package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.exception.AccessDeniedException;
import com.mengyunzhi.measurement.exception.ValidationException;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/5/18.
 * 用户
 */
@ContextConfiguration
@WebAppConfiguration
public class UserServiceImplTest extends ServiceTest {
    private static Logger logger = Logger.getLogger(UserServiceImplTest.class.getName());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private DepartmentTypeRepository departmentTypeRepository;    // 部门类型
    @Autowired
    private DepartmentRepository departmentRepository;   // 部门

    @Autowired
    private WebAppMenuRepository webAppMenuRepository;       // 前台菜单
    @Autowired
    private RoleRepository roleRepository;   // 角色

    @Test
    public void getAllUser() throws Exception {
        logger.info("新建一个实体User并保存");
        User user = new User();
        user.setName("zhangjiahao");
        user.setPassword("hahahahaha");
        userRepository.save(user);

        logger.info("新建另外一个user实体并保存");
        User user1 = new User();
        user1.setName("zhaoxingtao");
        user1.setPassword("heiheihei");
        userRepository.save(user1);

        logger.info("设置分页大小");
        final PageRequest pageRequest = new PageRequest(
                1, 1
        );

        Page<User> page = userRepository.findAll(pageRequest);
        logger.info("断言总页数与数据表中元素个数相同");   // 因为分页大小为1，所以有多少条数据就应该分多少页
        assertThat(page.getTotalPages()).isEqualTo((int) page.getTotalElements());
        assertThat(page.getContent().size()).isEqualTo(1);
        userRepository.delete(user);
        userRepository.delete(user1);
        return;
    }

    @Test
    public void getAll() throws Exception {
        //存用户
        User user = new User();
        userRepository.save(user);

        //断言可取
        assertThat(userService.getAll().size()).isNotEqualTo(0);
    }

    @Test
    public void get() throws Exception {
        //存用户
        User user = new User();
        user.setName("zhangsan");
        userRepository.save(user);

        //断言可取
        assertThat(userService.get(user.getId()).getName()).isEqualTo("zhangsan");
    }

    @Test
    public void save() throws Exception {
        //存用户
        User user = new User();
        userService.save(user);

        //断言存成功
        user = userRepository.findOne(user.getId());
        assertThat(user).isNotNull();
    }

    @Test
    public void update() throws Exception {
        //存用户
        User user = new User();
        userService.save(user);

        //编辑用户
        user.setName("123");

        //更新用户
        userService.update(user.getId(), user);

        //断言用户已更新
        User user1 = userRepository.findOne(user.getId());
        assertThat(user1.getName()).isEqualTo("123");
    }

    @Test
    public void delete() throws Exception {
        logger.info("---- 删除一个实体 -----");
        logger.info("增加一个实体");
        User user = new User();
        userService.save(user);

        logger.info("删除实体");
        userService.delete(user.getId());

        logger.info("查询实体，并断言其删除成功");
        User newUser = userRepository.findOne(user.getId());
        assertThat(newUser).isNull();
    }

    @Test
    public void getCurrentUserWebAppMenus() {
        logger.info("设置当前用户，并断言获取后即为刚刚传入值");
        WebAppMenu webAppMenu = new WebAppMenu();
        webAppMenuRepository.save(webAppMenu);

        Role role = new Role();
        role.addWebAppMenu(webAppMenu);
        roleRepository.save(role);

        User user = new User();
        user.addRole(role);
        userRepository.save(user);

        userService.setCurrentLoginUser(user);
        logger.info("断言返回值为数组");
        assertThat(userService.getCurrentUserWebAppMenus().size()).isEqualTo(1);

        userRepository.delete(user.getId());
        roleRepository.delete(role.getId());
        webAppMenuRepository.delete(webAppMenu.getId());
    }


    @Test
    public void setCurrentLoginUser() {
        User user = new User();
        userService.setCurrentLoginUser(user);
        User user1 = userService.getCurrentLoginUser();
        assertThat(user.getId()).isEqualTo(user1.getId());
    }

    @Test
    public void checkUsernameIsExist() {
        String username = "xdfsefsdfsdfewfsxvcxvewfdfwef";
        assertThat(userService.checkUsernameIsExist(username)).isEqualTo(false);
        User user = new User();
        user.setUsername(username);
        userRepository.save(user);
        assertThat(userService.checkUsernameIsExist(username)).isEqualTo(true);

    }

    /**
     * 设置用户的状态为正常
     *
     * @author panjie
     */
    @Test
    public void setUserStatusToNormalById() {
        logger.info("新建一个用户,并断言状态为0已审核");
        User user = new User();
        userRepository.save(user);
        assertThat(user.getStatus()).isEqualTo(-1);

        try {
            logger.info("设置这个用户的状态为正常");
            userService.setUserStatusToNormalById(user.getId());
            logger.info("查询这个用户，并断言状态为0正常");
            assertThat(userRepository.findOne(user.getId()).getStatus()).isEqualTo(0);
        } catch (org.hibernate.ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void register() {
        Department department = departmentService.getOneSavedDepartment();
        DepartmentType departmentType = departmentTypeRepository.findOneByName(DepartmentType.NAME_GUAN_LI_BU_MEN);
        department.setDepartmentType(departmentType);
        departmentRepository.save(department);

        User user = new User();
        user.setDepartment(department);
        userService.register(user);

        user = userRepository.findOne(user.getId());

        assertThat(user.getRoles()).isNotEmpty();
        List<Role> roles = new ArrayList<>(user.getRoles());
        assertThat(roles.get(0).getName()).isEqualTo(Role.NAME_GUAN_LI_BU_MEN);
        return;
    }


    @Test
    public void pageAllBySpecification() {
        logger.info("新建三个区域");
        District district = new District();
        districtRepository.save(district);
        District district1 = new District();
        district1.setParentDistrict(district);
        districtRepository.save(district1);
        District district2 = new District();
        district2.setParentDistrict(district1);
        districtRepository.save(district2);

        logger.info("新建部门类型");
        DepartmentType departmentType = new DepartmentType();
        departmentTypeRepository.save(departmentType);

        logger.info("新建部门");
        Department department = new Department();
        department.setDepartmentType(departmentType);
        String name = CommonService.getRandomStringByLength(10);
        department.setName(name);
        department.setDistrict(district1);
        departmentRepository.save(department);

        logger.info("新建用户");
        User user = new User();
        user.setStatus(0);
        String username = CommonService.getRandomStringByLength(10);
        user.setUsername(username);
        user.setDepartment(department);
        userRepository.save(user);
        Pageable pageable = new PageRequest(0, 2);
        Page<User> users = null;

        // 分别按组合条件进行查询并断言
        users = userService.pageAllBySpecification(district.getId(), departmentType.getId(), 0, name, username, null, pageable);
        assertThat(users.getTotalElements()).isEqualTo(1);

        users = userService.pageAllBySpecification(district1.getId(), departmentType.getId(), 0, name, username, null, pageable);
        assertThat(users.getTotalElements()).isEqualTo(1);

        users = userService.pageAllBySpecification(district2.getId(), departmentType.getId(), 0, name, username, null, pageable);
        assertThat(users.getTotalElements()).isEqualTo(0);

        users = userService.pageAllBySpecification(district1.getId(), departmentType.getId() + 1L, 0, name, username, null, pageable);
        assertThat(users.getTotalElements()).isEqualTo(0);

        users = userService.pageAllBySpecification(district1.getId(), departmentType.getId(), 1, name, username, null, pageable);
        assertThat(users.getTotalElements()).isEqualTo(0);

        users = userService.pageAllBySpecification(district1.getId(), departmentType.getId(), 0, name + "hello", username, null, pageable);
        assertThat(users.getTotalElements()).isEqualTo(0);

        users = userService.pageAllBySpecification(district1.getId(), departmentType.getId(), 0, name, username + "hello", null, pageable);
        assertThat(users.getTotalElements()).isEqualTo(0);
    }

    @Autowired
    @Qualifier("DepartmentService")
    DepartmentService departmentService;

    @Test
    public void getByDepartmentIdTest() {
        logger.info("持久化10个用户，并设置部门");
        Department department = departmentService.getOneSavedDepartment();
        Set<User> userSet = new HashSet<>();
        for (Integer i = 0; i < 10; i++) {
            User user = UserService.getOneUser();
            user.setDepartment(department);
            userSet.add(user);

        }

        userRepository.save(userSet);

        logger.info("查询并断言查到的数量为10");
        List<User> userList = userService.getByDepartmentId(department.getId());
        assertThat(userList.size()).isEqualTo(10);
    }

    /**
     * 获取一小段时间内生效的token
     */
    @Test
    public void getEffectSmallTimeTokenOfCurrentUser() throws InterruptedException {
        logger.info("登录用户，并获取token");
        User user = userService.loginWithOneUser();
        String token = userService.getTempTokenOfCurrentUser();
        User user1 = userService.getUserByToken(token);
        assertThat(user1.getId()).isEqualTo(user.getId());

        logger.info("在指定的时间后，再次获取，断言获取到的值为null");
        Thread.sleep(UserService.TOKEN_EFFECT_SMALL_TOKEN_CATCH_TIME * 1000 + 1);
        User user2 = userService.getUserByToken(token);
        assertThat(user2).isNull();

    }

    @Test
    public void resetPasswordByUsernameAndMobile() {
        User user = new User();
        user.setUsername(CommonService.getRandomStringByLength(8));
        user.setMobile("18888888888");
        userRepository.save(user);

        boolean isException = false;
        try {
            userService.resetPasswordByUsernameAndMobile(user.getUsername(), "12342");
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage()).isEqualTo("手机号码格式不正确");
            isException = true;
        }
        assertThat(isException).isTrue();

        isException = false;
        try {
            userService.resetPasswordByUsernameAndMobile(user.getUsername(), "13423423123");
        } catch (AccessDeniedException accessDeniedException) {
            assertThat(accessDeniedException.getMessage()).isEqualTo("用户名或手机号不正确");
            isException = true;
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        assertThat(isException).isTrue();

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            logger.error("sleep 发生错误");
        }

        isException = false;
        try {
            userService.resetPasswordByUsernameAndMobile(user.getUsername(), "13423432421");
        } catch (AccessDeniedException accessDeniedException) {
            assertThat(accessDeniedException.getMessage()).isEqualTo("您请求的频率过高，每60秒内只能请求一次");
            isException = true;
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        assertThat(isException).isTrue();
    }

    @Test
    public void resetPasswordSuccess() throws ValidationException {
        User user = new User();
        user.setUsername(CommonService.getRandomStringByLength(8));
        user.setMobile("18888888888");
        userRepository.save(user);
        userService.resetPasswordByUsernameAndMobile(user.getUsername(), user.getMobile());
    }
}