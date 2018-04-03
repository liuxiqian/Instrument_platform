'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:RegistrationCtrl
 * @description 用户注册C层
 * # RegistrationCtrl
 * Controller of the webappApp
 * @author panjie
 */
angular.module('webappApp')
    .controller('RegistrationCtrl', ['$scope', '$location', 'UserServer', 'CommonService', '$stateParams', '$state', function($scope, $location, UserServer, CommonService, $stateParams, $state) {
        var data = {};
        var user = { username: "", password: "" }; // 用户
        var departmentType = {}; // 部门类型
        var department = { name: "", code: "", address: "", postalcode: "" }; // 部门
        var district = {}; // 区域
        department.district = district;
        department.departmentType = departmentType;

        data.user = user;
        data.user.department = department;

        $scope.regex = "^0?1[0-9]{10}$"; //手机号码验证
        $scope.regexUserNameIsEnglish = '^[a-zA-Z0-9.@]+$';        // 用户名是否为英文

        // 监听data.user.department.departmentType 当data.user.department.departmentType每次改变时, 都会触发显示或隐藏不同的字段信息
        $scope.$watch('data.user.department.departmentType', function(newValue) {
                if (newValue) {
                    switch (newValue.pinyin) {
                        case "guanlibumen":
                            $scope.departmentTypes = false;
                            break;
                        case "qijuyonghu":
                            $scope.departmentTypes = true;
                            break;
                        case "shengchanqiye":
                            $scope.departmentTypes = true;
                            break;
                        case "jishujigou":
                            $scope.departmentTypes = true;
                            break;
                        default:
                            $scope.departmentTypes = false;
                    }
                }
            },
            true);

        //传用户名给后台验证
        $scope.checkUsernameIsExist = function() {
            //判断输入的用户名是否是邮箱类型
            if ($scope.data.user.username === undefined) {
                //将输入为undefined（不是邮箱类型）的用户名进行验证
                $scope.shift = true;
                //将用户名是否存在的验证初始化
                $scope.modify = null;
            } else if ($scope.data.user.username === "") {
                //输入的为空字符串
                //将 shift置为 true，来验证是否是必填项
                $scope.shift = true;
            } else {
                //输入的是邮箱类型
                //将shift 置为false
                $scope.shift = false;
                //从后台验证是否存在用户所输入的用户名
                UserServer.checkUsernameIsExist($scope.data.user.username, function(data) {
                    //根据返回的data值，判断是否显示
                    if (data) {
                        //已存在用户名，data返回的是true，则将modify置为true
                        $scope.modify = false;
                    } else {
                        //不存在，data返回的是false
                        $scope.modify = true;
                    }
                });
            }
        };
        // 初始化数据
        $scope.data = data;

        //　是否显示全部区域
        UserServer.getCurrentLoginUser(function(currentUser) {
            if (angular.equals(currentUser, {})) {
                $scope.all = true;
                $scope.local = false;


            } else {
                $scope.all = false;
                $scope.local = true;
            }
        });

        $scope.register = function() {
            // 将注册按钮设置为disable 防止重复点击
            $scope.resubmit = false;
            // 发起注册请求
            UserServer.register($scope.data.user, function(code) {
                if (code === 201) {

                    UserServer.getCurrentLoginUser(function(currentUser) {
                        if (angular.equals(currentUser, {})) {

                            CommonService.success("注册成功", '', function() {
                                $state.reload(true);
                            });

                        } else {
                            CommonService.success('操作成功');
                        }
                    });
                } else {
                    // todo: 提示出错信息 改写为友好提示
                    console.log(code + "：系统错误");
                }
            });
        };
    }]);
