'use strict';

/**
 * @ngdoc service
 * @name webappApp.UserServer
 * @description
 * # UserServer 用户
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('UserServer', function ($cookies, $http, $q, $location, config, CommonService, WebAppMenuService, $route, $timeout) {
        var self = this;
        var cacheKey = 'currentLoginUser';
        self.init = function () {
            // 当前登录用户
            self.currentLoginUser = $cookies.getObject(cacheKey);
        };

        // 设置当登录用户
        self.setCurrentLoginUser = function (user) {
            self.currentLoginUser = user;
            $cookies.putObject(cacheKey, user);
        };

        // 获取当前的登录用户
        self.getCurrentLoginUser = function (callback) {
            callback(self.currentLoginUser);
        };

        // 判断当前用户是否登录
        self.checkUserIsLogin = function (callback) {
            if (self.currentLoginUser) {
                callback(true);
            } else {
                callback(false);
            }
        };

        // 登录
        self.login = function (user, callback) {
            var headers = {authorization: "Basic " + btoa(user.username + ':' + user.password)};
            $http.get('/User/login', {headers: headers})
                .then(function success(response) {
                        // 获取header中传回的x-auth-token并进行cookie
                        var xAuthToken = response.headers(config.xAuthTokenName);
                        if (xAuthToken) {
                            $cookies.put(config.xAuthTokenName, xAuthToken, {expires: CommonService.getCookiesExpireDate()});
                            self.setCurrentLoginUser(response.data);
                            WebAppMenuService.reload(function () {// 重新加载当前登录用户的菜单
                                callback(response.status);
                            });

                        } else {
                            console.log('获取' + config.xAuthTokenName + '发生错误，获取到的值为：' + xAuthToken);
                            callback(400);
                        }
                    },
                    function error(response) {
                        if (callback) {
                            callback(response.status);
                        }
                    });
        };

        // 注销
        self.logout = function (callback) {
            var keys = $cookies.getAll();
            angular.forEach(keys, function (key) {
                $cookies.remove(key);
            });

            $route.reload();
            callback();
        };

        // 注册新用户
        self.register = function (user, callback) {
            //处理数据
            $http.post('/User/register', user)
                .then(function success(response) {
                    callback(response.status);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取用户名是否存在
         * @param username, callback
         * @author liuxi
         */
        self.checkUsernameIsExist = function (username, callback) {
            var url = '/User/checkUsernameIsExist/' + username;
            $http.get(url).then(function success(response) {
                callback(response.data);
            }, function error(response) {
                CommonService.httpError(response);
            });
        };

        self.all = function (callback) {
            // 调用$http获取数据
            $http.get('/User/getAll').then(function successCallback(response) {
                callback(response.data);
            });
        };

        // 设置或获取当前被操作的对象  --- panjie
        self.currentOperateObject = {};
        self.setCurrentOperateObject = function (object) {
            self.currentOperateObject = object;
        };
        self.getCurrentOperateObject = function () {
            return self.currentOperateObject;
        };

        /**
         * 获取被选中的角色
         * @param Roles 点选的角色
         * @param result list结果集
         * @author panjie
         */
        self.getCheckedRoles = function (Roles, result) {
            angular.forEach(Roles, function (value) {
                if (value.checked) {
                    result.push({"id": value.id});
                }
            });
        };

        self.save = function ($scope, callback) {
            // 遍历被点击的角色，添加到role中
            $http.post('/User/save', $scope.data).then(function successCallback() {
                callback();
            });
        };

        // 显示“保存成功”的弹窗
        self.showInfo = function ($scope, info) {
            $scope.info = info;
            $timeout(function () {
                $scope.info = '';
            }, 1000);
        };

        // 更新
        self.update = function (id, user, callback) {
            //处理传过来的数据
            var sendData = user;
            sendData.department = {id: user.department.id};
            sendData.status = user.status.key;

            $http.put("/User/update/" + id, sendData)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 状态值
        self.statuses = [{key: -1, value: "未审核"},
            {key: 0, value: "正常"},
            {key: 1, value: "冻结"},
            {key: 2, value: "其它"}
        ];


        /**
         * 获取状态值的列表
         * @param callback
         * @author panjie
         */
        self.getStatuses = function (callback) {
            callback(self.statuses);
        };

        /**
         * 重置用户密码
         * @param callback
         */
        self.resetPassword = function (id, callback) {
            $http.put("/User/resetPasswordById/" + id)
                .then(function success(response) {
                    if (callback) {
                        callback(response.status);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 是否显示区县
        self.showQuxian = function () {
            if (self.currentLoginUser && self.currentLoginUser.department && self.currentLoginUser.department.district.districtType.pinyin === 'quxian') {
                return false;
            } else {
                return true;
            }
        };

        // 是否显示市
        self.showShi = function () {
            if (self.currentLoginUser && self.currentLoginUser.department && self.currentLoginUser.department.district.districtType.pinyin === 'sheng') {
                return true;
            } else {
                return false;
            }
        };

        /**
         * 是否显示 器具用户
         * @return   {}
         * @author 梦云智 http://www.mengyunzhi.com
         * @DateTime 2017-12-11T20:35:35+0800
         */
        self.showQijuyonghu = function () {
            if (self.currentLoginUser && self.currentLoginUser.department && self.currentLoginUser.department.departmentType.pinyin === 'qijuyonghu') {
                return false;
            } else {
                return true;
            }
        };

        /*
         * 更新用户密码和姓名
         * @param   用户id
         * @param   用户原密码与新密码，及用户名称
         * @param   callback
         * */
        self.updatePasswordAndNameOfCurrentUser = function (userPasswordAndName, callback) {
            $http.put("/User/updatePasswordAndNameOfCurrentUser/", userPasswordAndName)
                .then(function success(response) {
                    callback(response.status);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.getAllPage = function (params, callback) {
            var queryString = CommonService.querySerialize(params);
            var url = '/User/getAllPage' + '?' + queryString;
            // 调用$http获取数据
            $http.get(url)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 多条件查询
        self.pageAllBySpecification = function (params, callback) {
            var url = "/User/pageAllBySpecification";
            $http.get(url, {params: params})
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.delete = function (user, callback) {
            $http.delete("/User/delete/" + user.id)
                .then(function success(response) {
                    if (callback) {
                        callback(response.status);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        // 验证密码是否正确, 由于参数是密码，所以从安全方面考虑，使用post方法较为合适
        self.checkPasswordIsRight = function (password, callback) {
            $http.post("/User/checkPasswordIsRight", {password: password})
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取指定部门下的所有登录用户
         * @param  {long}   departmentId 部门iD
         * @param  {Function} callback     回调
         * @return {array}
         * @author  panjie
         */
        self.getAllByDepartmentId = function (departmentId, callback) {
            var url = "/User/getByDepartmentId/" + departmentId;
            $http.get(url)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取当前用户对应的临时TOKEN
         * @param    {Function}               callback
         * @return   {string}                          token
         * @author 梦云智 http://www.mengyunzhi.com
         * @DateTime 2017-11-12T15:54:14+0800
         * panjie
         */
        self.getTempTokenOfCurrentUser = function (callback) {
            var url = "/User/getHashMapWithTempTokenOfCurrentUser";
            $http.get(url)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data.value);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 重置密码
         * 发送至注册手机
         *
         * @param username 用户名
         * @param mobile 密码
         * @returns
         * @Author panjie
         */
        self.resetPasswordByUsernameAndMobile = function (username, mobile) {
            var url = '/User/resetPasswordByUsernameAndMobile';
            var data = {
                username: username,
                mobile: mobile
            };
            return $http.patch(url, data);
        };
        self.init();

        return {
            all: self.all,
            save: self.save,
            init: self.init,
            login: self.login,
            logout: self.logout,
            update: self.update,
            delete: self.delete,
            showShi: self.showShi,
            showInfo: self.showInfo,
            register: self.register,
            getAllPage: self.getAllPage,
            showQuxian: self.showQuxian,
            getStatuses: self.getStatuses,
            resetPassword: self.resetPassword,
            resetPasswordByUsernameAndMobile: self.resetPasswordByUsernameAndMobile,
            showQijuyonghu: self.showQijuyonghu,
            getCheckedRoles: self.getCheckedRoles,
            checkUserIsLogin: self.checkUserIsLogin,
            setCurrentLoginUser: self.setCurrentLoginUser,
            getCurrentLoginUser: self.getCurrentLoginUser,
            getAllByDepartmentId: self.getAllByDepartmentId,
            checkPasswordIsRight: self.checkPasswordIsRight,
            checkUsernameIsExist: self.checkUsernameIsExist,
            getUsersByDevelopmentId: self.getCurrentLoginUser,
            pageAllBySpecification: self.pageAllBySpecification,
            setCurrentOperateObject: self.setCurrentOperateObject,
            getCurrentOperateObject: self.getCurrentOperateObject,
            getTempTokenOfCurrentUser: self.getTempTokenOfCurrentUser,
            updatePasswordAndNameOfCurrentUser: self.updatePasswordAndNameOfCurrentUser
        };
    });
