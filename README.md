私有项目，注意保护项目源码：
# 开发规范
1. 前台V层不进行复用。
2. 前台C层不做继承。
3. 当需要复用C层时，使用路由中的params选项对C层进行初始化。
4. $scope中需要的数据，必须进行初始化。
5. 路由中传入的参数，全部绑定于$scope.params。
6. 后台的每个action中，除void方法，必须添加JsonView注解。
7. 实体中，凡是有@ManyToOne @OneToOne @OneToMany @ManyToMany关系时，必须添加 @JsonView(NoneJsonView.class) 注解。


# 开发环境下项目启动方法
https://pan.baidu.com/s/1qZ31xYc

# JAVA版本
1.8.0_151

## window系统下idea如何打开项目后台
File -> New -> Project form Existing Sources然后选择项目目录api下的pox.xml文件打开
