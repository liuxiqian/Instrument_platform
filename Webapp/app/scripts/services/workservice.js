'use strict';

/**
 * @ngdoc service
 * @name webappApp.WorkService
 * @description
 * # WorkService
 * 工作
 */
angular.module('webappApp')
    .service('WorkService', function ($http, CommonService, config, $state, $stateParams) {
        var self = this;

        // 申请类型
        self.applyTypes = {
            MandatoryInstrument: {route: 'InstrumentForced.InstrumentForcedApply'},          // 强检器具备案申请
            OverdueCheck: {route: 'InstrumentForcedTimeOutCheckApplyParent.InstrumentForcedTimeOutCheckApplyIndex'},
            MandatoryInstrumentCheck: {route: 'mandatory.checkApplyForTechnicalInstitution'}
        };

        /**
         * 更新工作状态为 在办
         * @param work 工作
         * @param callback
         * @author panjie
         */
        self.updateToDoingByWork = function (work, callback) {
            if (work && work.id) {
                self.updateToDoingByWorkId(work.id, callback);
            }
        };

        /**
         * 更新工作状态为 在办
         * @param id 工作id
         * @param callback
         * @author panjie
         */
        self.updateToDoingByWorkId = function (id, callback) {
            var url = "/Work/updateToDoingById/" + id;
            $http.patch(url)
                .then(function success(response) {
                    if (callback) {
                        callback(response.status);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.getOriginWorkByWork = function (work) {
            if (work.aliasWork && work.aliasWork.id) {
                return self.getOriginWorkByWork(work.aliasWork);
            } else {
                return work;
            }
        };

        /**
         * 获取某个申请下的所有的工作
         * @param applyId
         * @param callback
         * @author panjie
         */
        self.getAllByApplyId = function (applyId, callback) {
            var url = '/Work/getAllByApplyId/' + applyId;
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
         * 审核工作
         * @param  {工作}
         * @param  {Function}
         * @return {[type]}
         * @author panjie
         */
        self.audit = function (work, callback) {
            var url = '/Work/audit/';
            $http.patch(url, work)
                .then(function success(response) {
                    if (work.auditType === 'done') {
                        console.log('工作办结时，调用办结时的触发URL');
                        self.handleWhenApplyDoneByApply(work.apply, function () {
                            callback(response.data);
                        });
                    } else {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 当工作完成时，自动触发该事件.
         * 后台，我们应该还有，当工作送下一流程，删除或是送上一节点时，触的事件。思想相同
         * @param  {申请}
         * @param  {Function}
         * @return {[type]}
         * @author  panjie
         */
        self.handleWhenApplyDoneByApply = function (apply, callback) {
            if (apply.applyType.handleUrlWhenApplyDone && (apply.applyType.handleUrlWhenApplyDone !== '')) {
                var url = apply.applyType.handleUrlWhenApplyDone + apply.id;
                $http.patch(url)
                    .then(function success(response) {
                        callback(response);
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            }
        };

        /**
         * 获取某个工作实体
         * @param    int                 id       [description]
         * @param    {Function}               callback 回调函数
         * @return   {object}                          返回的请求信息
         * @author 梦云智 http://www.mengyunzhi.com
         * @DateTime 2017-10-11T08:05:21+0800
         */
        self.getById = function (id, callback) {
            var url = '/Work/' + id;
            $http.get(url)
                .then(function success(response) {
                    callback(response.data);
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取指工作申请类型名称的当前登录用户的工作分页数据
         * @param  {string}   applyTypeFlag 申请类型标识
         * @param  {object}   params        {page: 页码，size:总页数}
         * @param  {Function} callback      回调函数
         * @return {array}                 数组
         * @author  panjie
         */
        self.getPageOfCurrentUserByApplyTypeFlag = function (applyTypeFlag, params, callback) {
            var url = '/Work/getPageOfCurrentUserByApplyTypeFlag/' + applyTypeFlag;
            $http.get(url)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.pageOfCurrentLoginUserByApplyTypeFlagAndSpecification = function (params, callback) {
            var url = '/Work/pageOfCurrentLoginUserByApplyTypeFlagAndSpecification';
            // 如果传入起始日期，则将日期转化为时间戳
            if (typeof params.startApplyDate !== 'undefined' && typeof params.startApplyDate !== 'number') {
                params.startApplyDate = CommonService.dateToTimestamp(params.startApplyDate);
            }

            if (typeof params.endApplyDate !== 'undefined' && typeof params.endApplyDate !== 'number' && params.endApplyDate !== '') {
                // 结束日期加上86399999，因为如果用户选择2017-06-02，则是从0点0分计算的，加上之后则从23：59分计算
                params.endApplyDate = CommonService.dateToTimestamp(params.endApplyDate) + 86399999;
            }
            $http.get(url, {params: params})
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取当前登录用户的素有待办工作
         * @param  {Function} callback      回调函数
         * @return {[array]}
         * gaoliming
         */
        self.pageUnHandleWorkOfCurrentUser = function (params, callback) {
            var url = '/Work/pageUnHandleWorkOfCurrentUser';
            $http.get(url, {params: params})
                .then(function success(response) {
                    callback(response.data);

                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取当前登录用户的素有在办工作
         * @param  {Function} callback      回调函数
         * @return {[array]}
         */
        self.pageHandlingWorkOfCurrentUser = function (params, callback) {
            var url = '/Work/pageHandlingWorkOfCurrentUser';

            $http.get(url, {params: params})
                .then(function success(response) {
                    callback(response.data);

                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取当前登录用户已办工作
         * @param     params
         * @param     callback
         */
        self.pageHandledWorkOfCurrentUser = function (params, callback) {
            var url = '/Work/pageHandledWorkOfCurrentUser';

            $http.get(url, {params: params})
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };


        /**
         * 获取当前登录用户办结工作
         * @param     params
         * @param     callback
         */
        self.pageDoneWorkOfCurrentUser = function (params, callback) {
            var url = '/Work/pageDoneWorkOfCurrentUser';

            $http.get(url, {params: params})
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };


        /**
         * 获取当前登录用户超期工作
         * @param     params
         * @param     callback
         */
        self.getAllCurrentUserWorkOfOverTime = function (params, callback) {
            var url = '/MandatoryInstrument/getAllCurrentUserWorkOfOverTime/';

            $http.get(url, {params: params})
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 获取当前登录用户超期预警工作
         * @param     params
         * @param     callback
         */
        self.getAllCurrentUserWorkOfOverTimeWarn = function (params, callback) {
            var url = '/MandatoryInstrument/getAllCurrentUserWorkOfOverTimeWarn/';

            $http.get(url, {params: params})
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        /**
         * 初始化控制器，便于多个控制器复用
         */
        self.initController = function (controller, $scope) {
            CommonService.initControllerPage(controller, $scope);

            // 获取ui-route传入的参考
            controller.initScopeParams = function () {
                return {
                    page: $stateParams.page ? $stateParams.page : 0,
                    size: $stateParams.size ? $stateParams.size : config.size,
                    sort: 'id,desc',
                    startApplyDate: $stateParams.startApplyDate,
                    endApplyDate: $stateParams.endApplyDate
                };
            };

            controller.showAudit = function (work) {
                return !controller.showView(work);
            };

            controller.showView = function (work) {
                // 办结，则显示查看，否则不显示
                if (work.status === self.status.DONE || work.status === self.status.HANDLED) {
                    return true;
                } else {
                    return false;
                }
            };

            controller.clickView = function (work) {
                var applyType = self.validateApplyType(work);
                $state.go(applyType.route + '.view', {workId: work.id}, {reload: true});
            };

            controller.clickAudit = function (work) {
                var applyType = self.validateApplyType(work);
                $state.go(applyType.route + '.audit', {workId: work.id}, {reload: true});
            };

            $scope.delete = controller.delete;
            $scope.showAudit = controller.showAudit;
            $scope.showView = controller.showView;
            $scope.params = controller.initScopeParams();
            $scope.clickView = controller.clickView;
            $scope.clickAudit = controller.clickAudit;
        };

        /**
         * 验较申请类型
         * @param work 工作
         * @returns {route: 路由名}
         */
        self.validateApplyType = function (work) {
            if (!work || !work.apply || !work.apply.className) {
                throw '未接收到申请对应的类型信息';
            }

            var applyType = self.applyTypes[work.apply.className];
            if (!applyType) {
                throw '暂时还不能处理此' + work.apply.className + '申请，请维护本类的申请类型列表';
            }

            return applyType;
        };

        /**
         * 获取下一工作
         * @param id
         * @param callback
         * panjie
         */
        self.getNextWorkById = function (id, callback) {
            var url = '/Work/getNextWorkById/' + id;
            $http.get(url)
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        self.status = {
            UN_HANDLE: 0,
            HANDLING: 1,
            HANDLED: 2,
            DONE: 3
        };

        return {
            audit: self.audit,
            getById: self.getById,
            initController: self.initController,
            getAllByApplyId: self.getAllByApplyId,
            getNextWorkById: self.getNextWorkById,
            getStatus: self.getStatus,
            updateToDoingByWork: self.updateToDoingByWork,
            getOriginWorkByWork: self.getOriginWorkByWork,
            updateToDoingByWorkId: self.updateToDoingByWorkId,
            getPageOfCurrentUserByApplyTypeFlag: self.getPageOfCurrentUserByApplyTypeFlag,
            pageUnHandleWorkOfCurrentUser: self.pageUnHandleWorkOfCurrentUser,
            pageHandlingWorkOfCurrentUser: self.pageHandlingWorkOfCurrentUser,
            pageHandledWorkOfCurrentUser: self.pageHandledWorkOfCurrentUser,
            pageDoneWorkOfCurrentUser: self.pageDoneWorkOfCurrentUser,
            status: self.status,
            getAllCurrentUserWorkOfOverTime: self.getAllCurrentUserWorkOfOverTime,
            getAllCurrentUserWorkOfOverTimeWarn: self.getAllCurrentUserWorkOfOverTimeWarn,
            pageOfCurrentLoginUserByApplyTypeFlagAndSpecification: self.pageOfCurrentLoginUserByApplyTypeFlagAndSpecification
        };
    });
