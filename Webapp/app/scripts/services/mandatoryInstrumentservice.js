'use strict';

/**
 * @ngdoc service
 * @名称 webappApp.madatoryIntegratedService
 * @描述：获取数据，save和all方法的具体实现。
 * # madatoryIntegratedService—— 强检器具
 * Service in the webappApp.（模型层）
 */
angular.module('webappApp')
    .service('mandatoryInstrumentService', ['$http',
        'ApplyTypeService',
        'WorkflowNodeService',
        'MandatoryInstrumentApplyService',
        'CommonService',
        'UserServer',
        '$window',
        'config',
        '$stateParams',
        function ($http,
                  ApplyTypeService,
                  WorkflowNodeService,
                  MandatoryInstrumentApplyService,
                  CommonService,
                  UserServer,
                  $window,
                  config,
                  $stateParams) {
            var self = this;

            //获取后台数据
            self.all = function (callback) {
                $http.get('/MandatoryInstrument/getAll').then(function success(response) {
                    var data = response.data;
                    callback(data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
            };

            /**
             * 技术机构在指定的日期内，可以批量退回指定由其检定的器具
             * @param mandatoryInstruments
             * @param reason
             * @param callback
             * @author chuhang
             */
            self.batchBackAssignedInstrumentByMandatoryInstrumentSetAndReason = function (mandatoryInstruments, reason, callback) {
                console.log(mandatoryInstruments);
                var url = '/MandatoryInstrument/batchBackAssignedInstrumentByMandatoryInstrumentSetAndReason/' + reason;
                $http.patch(url, mandatoryInstruments)
                    .then(function success(response) {
                        if (callback) {
                            callback(response.status);
                        }
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };

            /**
             * 技术机构在指定的日期内，可以批量确认指定由其检定的器具
             * @param mandatoryInstruments
             * @param callback
             * @author chuhang
             */
            self.batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser = function (mandatoryInstruments, callback) {
                var url = '/MandatoryInstrument/batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser';
                $http.patch(url, mandatoryInstruments)
                    .then(function success(response) {
                        if (callback) {
                            callback(response.status);
                        }
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };

            // 删除
            self.delete = function (id, callback) {
                var deleteUrl = '/MandatoryInstrument/delete/';
                $http.delete(deleteUrl + id)
                    .then(function success(response) {
                        if (callback) {
                            callback(response);
                        }
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };


            /**
             * 为当前的管理部门导出excel
             * @param    {array}                 params 综合查询的参数
             * @return   {string}                        跳转到新的地址进行下载操作
             * @author 梦云智 http://www.mengyunzhi.com
             * @DateTime 2017-11-12T15:49:45+0800
             * panjie
             */
            self.exportExcelOfCurrentManagementDepartment = function (params, callback) {
                console.log("获取token");
                UserServer.getTempTokenOfCurrentUser(function (token) {
                    var url = CommonService.getBashUrl() + "/MandatoryInstrument/exportExcelOfCurrentManageDepartmentBySpecification/withToken?token=" + token;
                    var queryString = CommonService.querySerialize(params);
                    url += "&" + queryString;
                    $window.open(url, "_blank");
                    if (callback) {
                        callback();
                    }
                });
            };

            self.findById = function (id, callback) {
                var url = '/MandatoryInstrument/' + id;
                var call = function (response) {
                    if (callback) {
                        if (response.data) {
                            callback(response.data);
                        } else {
                            callback(response);
                        }
                    } else {
                        console.warn('未传入回调函数');
                    }
                };

                $http.get(url)
                    .then(function success(response) {
                        call(response);
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };

            //前台保存数据
            self.save = function (data, callback) {
                var url = '/MandatoryInstrument/';
                $http.post(url, data)
                    .then(function success(response) {
                        if (callback) {
                            callback(response.data);
                        }
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };

            // 更新基本信息
            self.update = function (id, data, callback) {
                var url = '/MandatoryInstrument/' + id;
                $http.patch(url, data)
                    .then(function success(response) {
                        callback(response.data);
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };

            self.currentOperateObject = {}; // 当前正在被操作的对象
            // 设置当前正在操作的对象
            self.setCurrentOperateObject = function (user) {
                self.currentOperateObject = user;
            };

            // 获取当前正在被操作的对象
            self.getCurrentOperateObject = function () {
                return self.currentOperateObject;
            };

            // 更新
            self.update = function (data, callback) {
                // 如果传入了DATA，且存在id，则进行数据更新。返回状态码.
                if (data && data.id) {
                    var url = '/MandatoryInstrument/' + data.id;
                    $http.patch(url, data)
                        .then(function success(response) {
                            callback(response.status);
                        }, function error(response) {
                            CommonService.httpError(response);
                        });
                } else {
                    // 如未传入data，则直接给500状态码。
                    callback(500);
                }

            };

            /**
             * 新建一个新工作以及新申请。并保存
             * @param callback
             */
            self.saveNewWorkAndMandatoryInstrumentApply = function (applyTypeId, callback) {
                ApplyTypeService.getApplyTypeAndStartWorkflowNodeByApplyTypeId(applyTypeId, function (applyType, workflowNode) {
                    var data = {work: {}, mandatoryInstrumentApply: {}};
                    data.work.workflowNode = workflowNode;
                    data.mandatoryInstrumentApply.applyType = applyType;
                    MandatoryInstrumentApplyService.save(data, function (response) {
                        if (callback) {
                            callback(response);
                        }
                    });
                });
            };

            // 更新强检器具的指定审核部门
            self.updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId =
                function (mandatoryInstruments, departmentId, callback) {
                    var url = '/MandatoryInstrument/updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId/' + departmentId;
                    $http.post(url, mandatoryInstruments)
                        .then(function success(response) {
                            if (callback) {
                                callback(response.data);
                            }
                        }, function (response) {
                            CommonService.httpError(response);
                        });
                };

            //更新强检器具
            self.updateCheckCycleAndFirstCheckDate = function (mandatoryInstrumentId, data, callback) {
                var updataUrl = '/MandatoryInstrument/updateCheckCycleAndFirstCheckDate/';

                //处理传过来的数据
                var sendData = data;
                sendData.checkDepartment = {id: data.checkDepartment.id};
                sendData.department = {id: data.department.id};
                sendData.generativeDepartment = {id: data.generativeDepartment.id};
                sendData.instrumentProduction = {id: data.instrumentProduction};
                sendData.instrumentType = {id: data.instrumentType};
                sendData.purpose = {id: data.purpose.id};

                $http.post(updataUrl + mandatoryInstrumentId, sendData)
                    .then(function success(resonse) {
                        callback(resonse.data);
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };

            // 获取当前登录用户的数据分页数据
            self.pageAllOfCurrentUser = function (params, query, callback) {
                var url = '/MandatoryInstrument/pageAllOfCurrentUser';
                if (typeof(query.disciplineId) !== 'undefined') {
                    url += '/disciplineId/' + query.disciplineId;
                }

                if (typeof(query.instrumentTypeFirstLevelId) !== 'undefined') {
                    url += '/instrumentTypeFirstLevelId/' + query.instrumentTypeFirstLevelId;
                }

                if (typeof(query.instrumentTypeId) !== 'undefined') {
                    url += '/instrumentTypeId/' + query.instrumentTypeId;
                }

                if (typeof(query.checkStatus) !== 'undefined' && query.checkStatus !== -1 && query.checkStatus !== '-1') {
                    url += '/checkStatus/' + query.checkStatus;
                }

                if (typeof(query.name) !== 'undefined') {
                    url += '/name/' + query.name;
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

            // 获取被指定检定部门为当前部门的强检器具
            self.pageByCheckDepartmentOfCurrentDepartment = function (params, callback) {
                var url = '/MandatoryInstrument/pageByCheckDepartmentOfCurrentDepartment';
                $http.get(url, {params: params})
                    .then(function success(response) {
                        if (callback) {
                            callback(response.data);
                        }
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };

            //更新强检器具
            self.updateFixSite = function (mandatoryInstrumentId, data, callback) {
                var updataUrl = '/MandatoryInstrument/updateFixSite/';

                //处理传过来的数据
                var sendData = data;
                if (data.checkDepartment && data.checkDepartment.id) {
                    sendData.checkDepartment = {id: data.checkDepartment.id};
                }
                sendData.department = {id: data.department.id};
                sendData.generativeDepartment = {id: data.generativeDepartment.id};
                // sendData.instrumentProduction = {id: data.instrumentProduction};
                sendData.instrumentType = {id: data.instrumentType.id};
                sendData.purpose = {id: data.purpose.id};

                $http.put(updataUrl + mandatoryInstrumentId, sendData)
                    .then(function success(resonse) {
                        callback(resonse.data);
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };


            self.updateCheckCycleAndFirstCheckDate = function (mandatoryInstrumentId, data, callback) {
                var updataUrl = '/MandatoryInstrument/updateCheckCycleAndFirstCheckDate/';

                //处理传过来的数据
                var sendData = data;
                sendData.checkDepartment = {id: data.checkDepartment.id};
                sendData.department = {id: data.department.id};
                sendData.generativeDepartment = {id: data.generativeDepartment.id};
                sendData.checkCycle = data.checkCycle.value;
                // sendData.instrumentProduction = {id: data.instrumentProduction};
                sendData.instrumentType = {id: data.instrumentType.id};
                sendData.purpose = {id: data.purpose.id};

                $http.post(updataUrl + mandatoryInstrumentId, sendData)
                    .then(function success(resonse) {
                        callback(resonse.data);
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };


            //检定器具新增的时候需要选择当前技术机构的所以器具使用信息
            self.getAllOfCurrentUser = function (callback) {
                var url = '/MandatoryInstrument/getAllOfCurrentUser';

                $http.get(url)
                    .then(function (response) {
                        if (callback) {
                            callback(response.data);
                        }
                    }, function (response) {
                        CommonService.httpError(response);
                    });
            };

            //指定检定周期
            self.checkCycles = [
                {name: "三个月", value: 92},
                {name: "四个月", value: 122},
                {name: "半年", value: 183},
                {name: "一年", value: 365},
                {name: "二年", value: 730},
                {name: "五年", value: 1826},
                {name: "无限期", value: 0}
            ];

            //获取所有的检定周期
            self.getAllCheckCycle = function (callback) {
                callback(self.checkCycles);
            };

            /**
             * 获取带有多条件查询的分页信息
             * params:{
             *      disciplineId: 学科类别ID
             *      instrumentTypeFirstLevelId： 一级类别ID
             *      instrumentTypeId: 器具ID
             *      audit: 是否审核
             *      name: 器具名称
             * }
             */
            self.pageAllOfCurrentUserBySpecification = function (params, callback) {
                var url = '/MandatoryInstrument/pageAllOfCurrentUserBySpecification';
                $http.get(url, {params: params})
                    .then(function success(response) {
                        if (callback) {
                            callback(response.data);
                        }
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };

            // 获取当前登录的管理部门下的所有强检器具
            self.pageAllOfCurrentManageDepartmentBySpecification = function (params, callback) {
                var url = '/MandatoryInstrument/pageAllOfCurrentManageDepartmentBySpecification';
                $http.get(url, {params: params})
                    .then(function success(response) {
                        if (callback) {
                            callback(response.data);
                        }
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };

            // 获取当前登录的技术机构下的被指定的所有强检器具
            self.pageAllOfCurrentTechnicalInstitutionBySpecification = function (params, callback) {
                var url = '/MandatoryInstrument/pageAllOfCurrentTechnicalInstitutionBySpecification';
                $http.get(url, {params: params})
                    .then(function success(response) {
                        if (callback) {
                            callback(response.data);
                        }
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };

            // 设置器具状态为：被退回
            self.setStatusToBackById = function (id, callback) {
                var url = '/MandatoryInstrument/setStatusToBackById/' + id;
                $http.put(url)
                    .then(function success(response) {
                        if (callback) {
                            callback(response.status);
                        }
                    }, function error(response) {
                        CommonService.httpError(response);
                    });
            };

            /**
             * 当前用户超期未检的分页数据
             * @param  {object}   params   查询参数
             * @param  {Function} callback
             * @return {array}
             * @author  panjie
             */
            self.pageTimeoutCheckDataOfCurrentUser = function (params, callback) {
                var url = '/MandatoryInstrument/pageOverdueDataOfCurrentUserBySpecification';
                $http.get(url, {params: params})
                    .then(function success(response) {
                        if (callback) {
                            callback(response.data);
                        }
                    }, function (response) {
                        CommonService.httpError(response);
                    });
            };

            // /**
            //  * 获取管理部门下的所有超期未检器具
            //  * @param   params      查询参数
            //  * @param   callback
            //  * @author  poshichao
            //  */
            // self.pageTimeoutCheckDataOfDepartment = function(params, callback) {
            //     var url = '/MandatoryInstrument/pageAllOfCurrentManageDepartmentBySpecification';
            //     $http.get(url, { params: params })
            //         .then(function success(response) {
            //             if (callback) { callback(response.data); }
            //         }, function(response) {
            //             CommonService.error('数据请求错误', url);
            //             console.error(response);
            //         });
            // };


            /**
             * 为当前的器具用户导出excel
             * @param    {array}                 params 综合查询的参数
             * @return   {string}                        跳转到新的地址进行下载操作
             * @author chuhang
             */
            self.exportExcelOfCurrentUser = function (params, callback) {
                console.log("获取token");
                UserServer.getTempTokenOfCurrentUser(function (token) {
                    var url = CommonService.getBashUrl() + "/MandatoryInstrument/exportExcelOfCurrentUserWithTokenBySpecification/withToken?token=" + token;
                    var queryString = CommonService.querySerialize(params);
                    url += "&" + queryString;
                    $window.open(url, "_blank");
                    if (callback) {
                        callback();
                    }
                });
            };

            /**
             * 初始化控制器.
             * 1. 初始化V层的查询参数
             * 2. 获取向API发起访问的参数
             * 3. 设置获取当前登录用户（器具用户、技术机构）的超期列表
             * @param controller
             * @param scope
             * @param stateParams
             * panjie
             */
            self.initController = function (controller, scope) {
                CommonService.initControllerPage(controller, scope);
                // 获取ui-route传入的参考
                controller.initScopeParams = function () {
                    var params = {
                        page: $stateParams.page ? $stateParams.page : 0,
                        size: $stateParams.size ? $stateParams.size : config.size,
                        id: $stateParams.id,
                        discipline: $stateParams.discipline,// 学科类别
                        instrumentTypeFirstLevel: $stateParams.instrumentTypeFirstLevel, // 一级类别
                        instrumentType: $stateParams.instrumentType, // 二级类别
                        audit: $stateParams.audit, // 是否已审核
                        name: $stateParams.name, // 器具名称
                        overdue: $stateParams.overdue,   // 是否超期
                        status: $stateParams.status         // 器具状态
                    };
                    return params;
                };

                // 获取查询参数
                controller.generateQueryParams = function () {
                    // 整理传入的参数信息。我们之所以在params的属性中只使用了基本类型，是为了以后将查询条件放到url中做准备
                    var params = {
                        page: scope.params.page,
                        size: scope.params.size,
                        id: scope.params.id,
                        disciplineId: scope.params.discipline.id,
                        instrumentTypeFirstLevelId: scope.params.instrumentTypeFirstLevel.id,
                        instrumentTypeId: scope.params.instrumentType.id,
                        audit: scope.params.audit,
                        name: scope.params.name,
                        overdue: scope.params.overdue,   // 是否超期 本项由路由指定
                        status: scope.params.status     // 器具状态 本项由路由指定
                    };
                    return params;
                };


                /**
                 * 是否选中当前记录
                 * 如果在前面的申请中，已经包含了当前实体了，则选中；否则，不选中
                 * @param  强检器具  object
                 * @return
                 * panjie
                 */
                controller.isChecked = function (object) {
                    var found = false;
                    angular.forEach($stateParams.mandatoryInstrumentSet, function (value) {
                        if (!found && (object.id === value.id)) {
                            object._checked = true;
                            found = true;
                        }
                    });

                    return found;
                };

                /**
                 * 将选中\反选的对象添加到数组中，或从数组中删除
                 * @param
                 * @author 梦云智 http://www.mengyunzhi.com
                 * @DateTime 2017-12-11T19:36:56+0800
                 */
                controller.add = function (object) {
                    // 如果有，就pop。没有，则push
                    if (CommonService.popByKeyName(object, 'id', controller.mandatoryInstrumentSet) === undefined) {
                        controller.mandatoryInstrumentSet.push(object);
                    }
                };


                // 加载数据
                controller.load = function () {
                    var params = controller.generateQueryParams();
                    self.pageAllOfCurrentUserBySpecification(params, function (data) {
                        scope.data = data;
                    });
                };
            };

            // 器具状态
            self.statues = {
                NEW: {value: -1, description: '未备案', style: 'info'},
                NORMAL: {value: 0, description: '予以备案', style: 'success'},
                STOP: {value: 1, description: '停用', style: 'warning'},
                SCRAP: {value: 2, description: '报废', style: 'warning2'},
                BACK: {value: -2, description: '被退回', style: 'danger'}
            };

            return {
                all: self.all,
                batchBackAssignedInstrumentByMandatoryInstrumentSetAndReason: self.batchBackAssignedInstrumentByMandatoryInstrumentSetAndReason,
                batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser: self.batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser,
                delete: self.delete,
                exportExcelOfCurrentManagementDepartment: self.exportExcelOfCurrentManagementDepartment,
                exportExcelOfCurrentTechnicalInstitution: self.exportExcelOfCurrentTechnicalInstitution,
                exportExcelOfCurrentUser: self.exportExcelOfCurrentUser,
                findById: self.findById,
                getAllCheckCycle: self.getAllCheckCycle,
                getAllOfCurrentUser: self.getAllOfCurrentUser,
                getApplyTypeAndStartWorkflowNodeOfCurrentUserAndCurrentWebAppMenu: self.getApplyTypeAndStartWorkflowNodeOfCurrentUserAndCurrentWebAppMenu,
                getCurrentOperateObject: self.getCurrentOperateObject,
                initController: self.initController,
                pageAllOfCurrentManageDepartmentBySpecification: self.pageAllOfCurrentManageDepartmentBySpecification,
                pageAllOfCurrentTechnicalInstitutionBySpecification: self.pageAllOfCurrentTechnicalInstitutionBySpecification,
                pageAllOfCurrentUser: self.pageAllOfCurrentUser,
                pageAllOfCurrentUserBySpecification: self.pageAllOfCurrentUserBySpecification,
                pageByCheckDepartmentOfCurrentDepartment: self.pageByCheckDepartmentOfCurrentDepartment,
                pageTimeoutCheckDataOfCurrentUser: self.pageTimeoutCheckDataOfCurrentUser,
                pageTimeoutCheckDataOfDepartment: self.pageTimeoutCheckDataOfDepartment,
                save: self.save,
                saveNewWorkAndMandatoryInstrumentApply: self.saveNewWorkAndMandatoryInstrumentApply,
                statues: self.statues,
                setCurrentOperateObject: self.setCurrentOperateObject,
                setStatusToBackById: self.setStatusToBackById,
                update: self.update,
                updateCheckCycleAndFirstCheckDate: self.updateCheckCycleAndFirstCheckDate,
                updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId: self.updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId,
                updateFixSite: self.updateFixSite
            };
        }
    ]);
