'use strict';

function configState($stateProvider, $urlRouterProvider, $compileProvider, config) {
    // Optimize load start with remove binding information inside the DOM element
    $compileProvider.debugInfoEnabled(true);

    // Set default state
    $urlRouterProvider.otherwise(config.defaultRoute);
    $stateProvider
    //用户注册
        .state('registration', {
            url: '/registration',
            templateUrl: 'views/registration.html',
            data: {},
            controller: 'RegistrationCtrl'
        })

        //用户审核
        .state('check', {
            url: '/check',
            templateUrl: 'views/check.html',
            data: {}
        })

        // 用户登录
        .state('login', {
            url: '/login',
            templateUrl: 'views/login.html',
            data: {},
            controller: 'LoginCtrl'
        })

        .state('login.resetPassword', {
            // 路由值, 表示该值继承于system
            url: '/resetPassword',
            templateUrl: 'views/resetPassword.html',
            data: {
                pageTitle: '重置密码',
                pageDesc: '根据用户名、手机号重置密码'
            },
            controller: 'ResetpasswordCtrl'
        })

        .state('main', {
            abstract: true, // 表示此路由不对应具体的页面
            url: "/main",
            templateUrl: "views/main.html", // 模板文件
        })

        // 关于--版权信息、公司信息
        .state('main.about', {
            url: '/about',
            templateUrl: 'views/about.html',
            data: {
                pageTitle: '关于'
            }
        })

        // 仪表台 -- 首页
        .state('main.dashboard', {
            url: '/dashboard',
            templateUrl: 'views/dashboard.html',
            data: {
                pageTitle: '首页'
            },
            controller: 'DashboardCtrl'
        })

        // 大数据平台
        .state('main.analytics', {
            url: "/analytics",
            templateUrl: "views/analytics.html",
            data: {
                pageTitle: '大数据平台'
            }
        })

        .state('InstrumentForced', { // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/InstrumentForced", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '强检器具备案管理'
            }
        })

        // 强制检定备案
        .state('InstrumentForced.InstrumentForcedApply', {
            url: '/InstrumentForcedApply',
            templateUrl: 'views/instrument/forcedApply/index.html',
            data: {
                pageTitle: '备案申请'
            },
            params: {
                page: 0,
                size: config.size,
                startApplyDate: undefined,
                endApplyDate: undefined
            },
            controller: 'InstrumentForcedapplyIndexCtrl'
        })



        // 强检器具新增(新增)
        .state('InstrumentForced.InstrumentForcedApply.add', {
            url: '/add',
            templateUrl: 'views/instrument/forced/add.html',
            data: {
                pageTitle: '新增器具',
                pageDesc: ''
            },
            params: {
                mandatoryInstrumentList: {  // 强检器具列表
                    value: []
                }
            },
            controller: 'InstrumentForcedAddCtrl'
        })

        // 强检器具新增(编辑)
        .state('InstrumentForced.InstrumentForcedApply.edit', {
            url: '/edit',
            templateUrl: 'views/instrument/forced/add.html',
            data: {
                pageTitle: '编辑器具',
                pageDesc: ''
            },
            params: {
                mandatoryInstrumentSet: {  // 强检器具列表
                    value: []
                },
                key: {}
            },
            controller: 'InstrumentForcedEditCtrl'
        })

        // 强检器具新增(备案)
        .state('InstrumentForced.InstrumentForcedApply.addApply', {
            url: '/addApply',
            templateUrl: 'views/instrument/forced/addApply.html',
            data: {
                pageTitle: '新增申请',
                pageDesc: ''
            },
            params: {
                mandatoryInstrumentList: {  // 强检器具列表
                    value: []
                }
            },
            controller: 'InstrumentForcedAddapplyCtrl'
        })

        // 强制检定备案
        .state('InstrumentForced.InstrumentForcedApply.audit', {
            url: '/audit/:workId',
            templateUrl: 'views/instrument/forcedApply/audit.html',
            data: {
                pageTitle: '办理'
            },

            controller: 'InstrumentForcedapplyAuditCtrl'
        })


        // 强制检定备案
        .state('InstrumentForced.InstrumentForcedApply.view', {
            url: '/view/:workId',
            templateUrl: 'views/instrument/forcedApply/view.html',
            data: {
                pageTitle: '查看'
            },
            controller: 'InstrumentForcedapplyViewCtrl'
        })

        // 强检器具申请 编辑
        .state('InstrumentForced.InstrumentForcedApplyEdit', {
            url: "/InstrumentForcedApplyEdit/:workId",
            templateUrl: "views/mandatory/integrated/add.html",
            data: {
                pageTitle: '备案 -- 编辑',
                pageDesc: ''
            },

            controller: 'InstrumentForcedApplyEditCtrl'
        })

        // 强检器具申请技术机构综合查询
        .state('InstrumentForced.InstrumentForcedTechnologyDepartmentIntegratedQuery', {
            url: "/InstrumentForcedTechnologyDepartmentIntegratedQuery/page/:page/size/:size",
            templateUrl: "views/instrument/forced/technologyDepartmentIntegratedQueryIndex.html",
            data: {
                pageTitle: '综合查询'
            },
            params: {
                page: {
                    value: '0'
                },
                size: {
                    value: config.size.toString()
                },
                districtId: {
                    value: '' // 区域ID
                },
                departmentId: {
                    value: '' // 器具用户
                },
                id: {
                    value: ''
                },
                disciplineId: {
                    value: '' // 学科类别ID
                },
                instrumentTypeFirstLevelId: {
                    value: '' // 一级类别ID
                },
                instrumentTypeId: {
                    value: '' // 二级类别ID
                },
                audit: {
                    value: '-1' // 审核状态
                },
                name: {
                    value: '' // 器具名称
                },
                isConfirmedByTechnologyDepartment: {
                    value: '0' // 是否已被技术机构确认
                }
            },
            controller: 'InstrumentForcedTechnologyDepartmentIntegratedQueryIndexCtrl'
        })

        // 强检器具超期检定备案管理 panjie
        .state('InstrumentForcedTimeOutCheckApplyParent', { // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/InstrumentForcedTimeOutCheckApplyParent", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '强检器具超期检定管理'
            }
        })

        // 强检器具超期检定备案管理 - 检定申请 panjie
        .state('InstrumentForcedTimeOutCheckApplyParent.InstrumentForcedTimeOutCheckApplyIndex', {
            url: '/InstrumentForcedTimeOutCheckApplyIndex',
            templateUrl: 'views/instrument/overdueCheckApply/index.html',
            data: {
                pageTitle: '检定申请'
            },
            params: {
                page: 0,
                size: config.size,
                startApplyDate: undefined,      // 申请起始日期
                endApplyDate: undefined,     // 申请结束日期
                instrumentUserName: ''      // 器具用户名称
            },
            controller: 'InstrumentForcedTimeoutCheckApplyIndexCtrl'
        })


        .state('InstrumentForcedTimeOutCheckApplyParent.InstrumentForcedTimeOutCheckApplyIndex.Add', {
            url: '/Add',
            templateUrl: 'views/instrument/overdueCheckApply/add.html',
            data: {
                pageTitle: '新增',
                pageDesc: '强检器具超期检定申请 新增（适用于器具用户）'
            },
            controller: 'MandatoryOverduecheckapplyforinstrumentuserAddCtrl',
            params: {
                mandatoryInstrumentSet: {
                    value: []
                }
            }
        })

        .state('InstrumentForcedTimeOutCheckApplyParent.InstrumentForcedTimeOutCheckApplyIndex.Add.choose', {
            url: '/choose',
            templateUrl: 'views/mandatory/checkapplyforinstrumentuser/choose.html',
            data: {
                pageTitle: '选择器具',
                pageDesc: '强检器具超期检定管理检定申请 1.选择要送检的器具（适用于器具用户）'
            },
            controller: 'MandatoryOverduecheckapplyforinstrumentuserChooseCtrl',
            params: {
                mandatoryInstrumentSet: [],// 已经存在强检器具
                id: undefined,
                discipline: {id: undefined},
                instrumentTypeFirstLevel: {id: undefined},
                instrumentType: {id: undefined},
                audit: undefined,
                name: '',
                overdue: true,   // 是否超期 本项由路由指定
                status: 0     // 器具状态 本项由路由指定
            }
        })


        .state('InstrumentForcedTimeOutCheckApplyParent.InstrumentForcedTimeOutCheckApplyIndex.view', {
            url: '/view/:workId',
            templateUrl: 'views/instrument/overdueCheckApply/view.html',
            data: {
                pageTitle: '查看',
                pageDesc: '强检器具超期检定申请 查看（适用于器具用户）'
            },
            controller: 'MandatoryOverduecheckapplyforinstrumentuserViewCtrl',
            params: {
                id: {value: ''}
            }
        })

        .state('InstrumentForcedTimeOutCheckApplyParent.InstrumentForcedTimeOutCheckApplyIndex.audit', {
            url: '/audit/:workId',
            templateUrl: 'views/instrument/overdueCheckApply/audit.html',
            data: {
                pageTitle: '审核',
                pageDesc: '强检器具超期检定申请 审核（适用于管理部门）'
            },
            controller: 'MandatoryOverduecheckapplyforinstrumentuserAuditCtrl',
            params: {
                id: {value: ''}
            }
        })

        // 强检器具超期检定备案管理 - 综合查询 panjie
        .state('InstrumentForcedTimeOutCheckApplyParent.InstrumentForcedTimeoutCheckApplyIntegratedQueryIndex', {
            url: '/InstrumentForcedTimeoutCheckApplyIntegratedQueryIndex',
            templateUrl: 'views/instrument/forced/integratedQueryTimeoutCheckapplyIndex.html',
            data: {
                pageTitle: '综合查询'
            },
            params: {
                id: undefined,
                discipline: {id: undefined},
                instrumentTypeFirstLevel: {id: undefined},
                instrumentType: {id: undefined},
                audit: undefined,
                name: '',
                overdue: true,   // 是否超期 本项由路由指定
                status: 0     // 器具状态 本项由路由指定
            },
            controller: 'InstrumentForcedTimeoutCheckApplyIntegratedQueryIndexCtrl'
        })

        // 强检器具超期检定备案管理 - 综合查询（管理部门）poshichao
        .state('InstrumentForcedTimeOutCheckApplyParent.InstrumentForcedTimeoutCheckApplyDepartmentQueryIndex', {
            url: "/InstrumentForcedTimeoutCheckApplyDepartmentQueryIndex/page/:page/size/:size",
            templateUrl: "views/instrument/forced/integratedQueryTimeoutCheckapplyIndex.html",
            data: {
                pageTitle: '综合查询'
            },
            params: {
                page: {
                    value: '0'
                },
                size: {
                    value: config.size.toString()
                }
            },
            controller: 'InstrumentForcedTimeoutCheckApplyDepartmentQueryIndexCtrl'
        })


        //个人中心
        .state('Personal', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/Personal",
            templateUrl: "views/common/content.html",
            data: {
                pageTitle: '个人中心',
            }
        })

        //个人中心
        .state('Personal.PersonalInfoManage', {
            // 路由值
            url: "/PersonalInfoManage",
            templateUrl: "views/personalcenter/PersonalInfoManage/index.html",
            data: {
                pageTitle: '登录用户信息',
                pageDesc: '个人中心--登录用户信息'
            }
        })

        .state('Personal.PersonalDepartmentManage', {
            // 路由值
            url: '/PersonalDepartmentManage',
            templateUrl: 'views/personalcenter/PersonalDepartmentManage/index.html',
            data: {
                pageTitle: '部门信息',
                pageDesc: '个人中心--部门信息'
            }
        })


        // 强制检定管理
        .state('mandatory', { // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/mandatory", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '强检器具检定管理'
            }
        })

        .state('mandatory.NumberCategories', {
            // 路由值, . 表示该值继承于mandatory
            url: "/NumberCategories",
            templateUrl: "views/mandatory/numbercategories/index.html",
            data: {
                pageTitle: '强制检定计量器具用户档案',
                pageDesc: '强制检定管理计量器具强制检定计量器具用户档案'
            }
        })

        .state('InstrumentForced.Integrated', {
            // 路由值, . 表示该值继承于mandatory
            url: '/Integrated',
            templateUrl: 'views/mandatory/integrated/index.html',
            data: {
                pageTitle: '综合查询',
                pageDesc: '强检备案--综合查询'
            },
            params: {
                page: 0,
                size: config.size,
                overdue: undefined,
                status: undefined,
                id: undefined,
                discipline: {id: undefined}, // 学科类别
                instrumentTypeFirstLevel: {id: undefined}, // 一级类别
                instrumentType: {id: undefined}, // 二级类别
                audit: undefined, // 是否已审核
                name: '' // 器具名称
            },
            controller: 'InstrumentForcedIndexCtrl'
        })

        // 编辑
        .state('mandatory.IntegratedEdit', {
            // 路由值, . 表示该值继承于mandatory
            url: "/IntegratedEdit/:id",
            templateUrl: "views/mandatory/integrated/edit.html",
            data: {
                pageTitle: '计量器具用户强制检定计量器具档案--编辑',
                pageDesc: '强检器具管理--计量器具用户强制检定计量器具档案--编辑'
            },
            controller: 'MandatoryIntegratedEditCtrl'
        })

        .state('mandatory.integratedAudit', {
            // 路由值, . 表示该值继承于mandatory
            url: '/integratedAudit',
            templateUrl: 'views/mandatory/integratedAudit/index.html',
            data: {
                pageTitle: '检定档案管理',
                pageDesc: '强检器具管理 -- 检定档案管理'
            },
            params: {
                page: 0,
                size: config.size,
                discipline: {id: undefined},  // 学科类别
                instrumentFirstLevelType: {id: undefined},    // 一级类别
                instrumentType: {id: undefined},  // 二级类别
                checkResult: {id: undefined},           // 检定结果
                name: '',           // 强检器具名称
                year: undefined,            // 年度
                mandatoryInstrumentId: undefined
            },
            controller: 'MandatoryIntegratedAuditIndexCtrl'
        })

        .state('mandatory.Userapplication', {
            // 路由值, . 表示该值继承于mandatory
            url: "/Userapplication",
            data: {
                pageTitle: '用户检定申请',
                pageDesc: '强制检定管理计量器具用户检定申请'
            },
            templateUrl: "views/mandatory/integrated/add.html",
            controller: 'MandatoryIntegratedAddCtrl'
        })

        .state('mandatory.Reply', {
            // 路由值, . 表示该值继承于mandatory
            url: "/Reply",
            templateUrl: "views/mandatory/reply/index.html",
            data: {
                pageTitle: '机构申请回复',
                pageDesc: '强制检定管理计量器具机构申请回复'
            },
            controller: 'PersonnelPersonnelfileIndexCtrl'
        })

        .state('personnel.PersonnelFileEdit', {
            url: "/PersonnelFile/Edit",
            templateUrl: "views/personnel/personnelfile/add.html",
            data: {
                pageTitle: '档案管理--编辑',
                pageDesc: '人员管理--档案管理编辑'
            },
            params: {
                type: {
                    value: 'edit'
                },
                data: {
                    value: {}
                }

            },
            controller: 'StandardPersonnelfileAddCtrl',
        })

        // .state('personnel.PersonnelFileDetail', {
        //  url: "/PersonnelFile/Detail",
        //  templateUrl: "views/personnel/personnelfile/detail.html",
        //  data: {
        //      pageTitle: '档案管理--人员资质',
        //      pageDesc: '人员资质管理--人员资质'
        //  },
        //  controller: 'StandardPersonnelfileDetailCtrl',
        // })


        // .state('personnel.PersonnelFileDetailAdd', {
        //  url: "/PersonnelFile/DetailAdd",
        //  templateUrl: "views/personnel/personnelfile/detailadd.html",
        //  data: {
        //      pageTitle: '人员资质--新增资质',
        //      pageDesc: '人员资质管理--人员资质档案管理--新增资质'
        //  },
        //  params: {
        //      type: {
        //          value: 'add'
        //      },
        //      data: {
        //          value: {}
        //      }

        //  },
        //  controller: 'StandardPersonnelfileDetailaddCtrl',
        // })

        // .state('personnel.PersonnelFileDetailEdit', {
        //  url: "/PersonnelFile/DetailEdit",
        //  templateUrl: "views/personnel/personnelfile/detailadd.html",
        //  data: {
        //      pageTitle: '人员资质--编辑资质',
        //      pageDesc: '人员资质管理--人员资质档案管理--编辑资质'
        //  },
        //  params: {
        //      type: {
        //          value: 'edit'
        //      },
        //      data: {
        //          value: {}
        //      }

        //  },
        //  controller: 'StandardPersonnelfileDetailaddCtrl',
        // })

        //注册信息
        // .state('register', {
        //     // 路由值
        //     abstract: true, // 表示此路由不对应具体的页面
        //     url: "/register", // 对应的URL信息
        //     templateUrl: "views/common/content.html", // 模板文件
        //     data: { // 数据
        //         pageTitle: '注册信息'
        //     }
        // })

        .state('register.MeasureDevice', {
            url: "/MeasureDevice",
            templateUrl: "views/register/measuredevice/index.html",
            data: {
                pageTitle: '强制检定计量器具用户注册信息',
                pageDesc: '注册信息强制检定计量器具用户注册信息'
            }
        })

        .state('register.MeasureDeviceAdd', {
            url: "/MeasureDevice/Add",
            templateUrl: "views/register/measuredevice/add.html",
            data: {
                pageTitle: '强制检定计量器具用户注册信息--新增',
                pageDesc: '注册信息强制检定计量器具用户注册信息--新增'
            }
        })

        //我的工作
        .state('mywork', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/mywork", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '我的工作'
            }
        })

        .state('mywork.Unhandle', {
            url: '/Unhandle',
            templateUrl: 'views/mywork/unhandle/index.html',
            data: {
                pageTitle: '待办',
                pageDesc: '我的工作-待办'
            },
            params: {
                page: 0,
                size: config.size
            },
            controller: 'MyworkUnhandleIndexCtrl'
        })

        .state('mywork.Handling', {
            url: "/Handling",
            templateUrl: "views/mywork/handling/index.html",
            data: {
                pageTitle: '在办',
                pageDesc: '我的工作-在办'
            },
            params: {
                page: 0,
                size: config.size
            },
            controller: 'MyworkHandlingIndexCtrl'
        })

        .state('mandatory.CheckDetail', {
            // 路由值, . 表示该值继承于mandatory
            url: "/CheckDetail",
            templateUrl: "views/mandatory/checkdetail/index.html",
            data: {
                pageTitle: '检定信息',
                pageDesc: '强制检定管理计量器具检定信息'
            }
        })

        .state('mywork.Handled', {
            url: '/Handled',
            templateUrl: 'views/mywork/handled/index.html',
            data: {
                pageTitle: '已办',
                pageDesc: '我的工作-已办'
            },
            params: {
                page: 0,
                size: config.size
            },
            controller: 'MyworkHandledIndexCtrl'
        })

        .state('mywork.Done', {
            url: "/Done",
            templateUrl: "views/mywork/done/index.html",
            data: {
                pageTitle: '办结',
                pageDesc: '我的工作-办结'
            },
            params: {
                page: 0,
                size: config.size
            },
            controller: 'MyworkDoneIndexCtrl'
        })
        // 审核工作
        .state('mywork.audit', {
            url: "/audit/:id",
            templateUrl: "views/mywork/auditAndView.html",
            data: {
                pageTitle: '审核',
                pageDesc: '我的工作-审核'
            },
            controller: 'MyworkAuditCtrl'
        })

        .state('mandatory.instrumentCheckInfoManage', {
            // 路由值, . 表示该值继承于mandatory
            url: '/instrumentCheckInfoManage',
            templateUrl: 'views/mandatory/instrumentcheckinfomanage/index.html',
            data: {
                pageTitle: '器具检定综合查询',
                pageDesc: '强检器具管理--器具检定综合查询'
            },
            params: {
                mandatoryInstrumentId: {
                    value: undefined
                }
                // 强检定器具使用信息
            },
            controller: 'MandatoryQualifiedIndexCtrl'
        })

        .state('mandatory.instrumentCheckInfo', {
            // 路由值, . 表示该值继承于mandatory
            url: '/instrumentCheckInfo',
            templateUrl: 'views/mandatory/instrumentCheckInfo/index.html',
            data: {
                pageTitle: ' 器具检定档案管理',
                pageDesc: '强检器具管理-- 器具检定档案管理'
            },
            controller: 'MandatoryInstrumentcheckinfoIndexCtrl'
        })

        .state('mandatory.instrumentCheckInfoAdd', {
            // 路由值, . 表示该值继承于mandatory
            url: "/instrumentCheckInfo/Add",
            templateUrl: "views/mandatory/qualified/add.html",
            data: {
                pageTitle: ' 器具检定档案管理',
                pageDesc: '强检器具管理-- 器具检定档案管理'
            },
            params: {
                mandatoryInstrument: {
                    value: {}
                },
                type: {
                    value: 'add'
                }
            },
            controller: 'MandatoryQualifiedAddCtrl'
        })

        .state('mandatory.instrumentCheckInfoEdit', {
            // 路由值, . 表示该值继承于mandatory
            url: "/instrumentCheckInfo/Edit",
            templateUrl: "views/mandatory/qualified/edit.html",
            data: {
                pageTitle: ' 器具检定档案管理',
                pageDesc: '强检器具管理-- 器具检定档案管理'
            },
            params: {
                mandatoryInstrument: {
                    value: {}
                },
                type: {
                    value: 'edit'
                },
                instrumentCheckInfo: {
                    value: {}
                }
            },
            controller: 'MandatoryQualifiedAddCtrl'
        })

        .state('mandatory.instrumentCheckInfoOne', {
            // 路由值, . 表示该值继承于mandatory
            url: "/instrumentCheckInfoOne/:id",
            templateUrl: "views/mandatory/instrumentcheckinfo/read.html",
            data: {
                pageTitle: ' 器具检定档案管理',
                pageDesc: '强检器具管理-- 器具检定器具信息'
            },
            controller: 'MandatoryInstrumentcheckinfoReadCtrl'
        })

        .state('InstrumentForced.appointCheckInstrument', {
            // 路由值, . 表示该值继承于mandatory（弃用）
            url: '/appointCheckInstrument',
            templateUrl: 'views/mandatory/appointcheckinstrument/index.html',
            data: {
                pageTitle: '综合查询',
                pageDesc: '强检备案-- 综合查询'
            },
            params: {
                page: 0,
                size: config.size,
                district: {id: undefined}, // 区域
                department: {id: undefined},    // 部门
                id: undefined,
                discipline: {id: undefined},    // 学科类别
                instrumentTypeFirstLevel: {id: undefined}, // 一级类别ID
                instrumentType: {id: undefined},// 二级类别ID
                audit: undefined,// 审核状态
                name: '', // 器具名称
                isConfirmedByTechnologyDepartment: undefined// 是否已被技术机构确认
            },
            controller: 'MandatoryAppointcheckinstrumentIndexCtrl'
        })

        .state('mandatory.appointCheckInstrumentEdit', {
            // 路由值, . 表示该值继承于mandatory
            url: "/appointCheckInstrument/edit",
            templateUrl: "views/mandatory/appointcheckinstrument/edit.html",
            data: {
                pageTitle: '指定器具管理',
                pageDesc: '强检器具管理-- 指定器具管理'
            },
            params: {
                mandatoryInstrument: {
                    value: {}
                }
            },
            controller: 'MandatoryAppointcheckinstrumentEditCtrl'
        })

        .state('mandatory.PassRate', {
            // 路由值, . 表示该值继承于mandatory
            url: "/PassRate",
            templateUrl: "views/mandatory/passrate/index.html",
            data: {
                pageTitle: '强制检定计量器具合格率统计',
                pageDesc: '强制检定管理计量器具强制检定计量器具合格率统计'
            }
        })

        .state('mandatory.checkApplyForInstrumentUser', {
            url: '/checkApplyForInstrumentUser',
            templateUrl: 'views/mandatory/checkapplyforinstrumentuser/index.html',
            data: {
                pageTitle: '检定申请',
                pageDesc: '强检器具检定申请（适用于器具用户）'
            },
            params: {
                page: 0,
                size: config.size
            },
            controller: 'MandatoryCheckApplyForTechnicalInstitutionIndexCtrl'
        })

        .state('mandatory.checkApplyForInstrumentUser.view', {
            url: '/view/:workId',
            templateUrl: 'views/mandatory/checkapplyforinstrumentuser/view.html',
            data: {
                pageTitle: '查看',
                pageDesc: '查看检定申请详情'
            },
            controller: 'MandatoryCheckApplyForTechnicalInstitutionViewCtrl'
        })

        .state('mandatory.checkApplyForInstrumentUser.Add', {
            url: '/Add',
            templateUrl: 'views/mandatory/checkapplyforinstrumentuser/add.html',
            data: {
                pageTitle: '新增',
                pageDesc: '强检器具检定申请 新增（适用于器具用户）'
            },
            controller: 'MandatoryCheckapplyforinstrumentuserAddCtrl',
            params: {
                mandatoryInstrumentSet: {
                    value: []
                }
            }
        })

        .state('mandatory.checkApplyForInstrumentUser.Add.choose', {
            url: '/choose',
            templateUrl: 'views/mandatory/checkapplyforinstrumentuser/choose.html',
            data: {
                pageTitle: '选择器具',
                pageDesc: '强检器具检定管理检定申请 1.选择要送检的器具（适用于器具用户）'
            },
            controller: 'MandatorycheckapplyforinstrumentuserchooseCtrl',
            params: {
                mandatoryInstrumentSet: [], // 已经存在强检器具
                page: 0,
                size: config.size,
                id: undefined,
                discipline: {id: undefined},// 学科类别ID
                instrumentTypeFirstLevel: {id: undefined},
                instrumentType: {id: undefined},
                status: 0,// 器具状态
                isFirstCheckApply: {}, // 是否首次检定
                name: '', // 器具名称
                overdue: false,// 是否超期未检
                audit: true
            }
        })

        .state('mandatory.checkApplyForInstrumentUser.addInstrumentCheckInfo', {
            url: '/addInstrumentCheckInfo',
            templateUrl: 'views/mandatory/checkapplyforinstrumentuser/addInstrumentCheckInfo.html',
            data: {
                pageTitle: '新增强制检定器具检定信息',
                pageDesc: '强检器具检定申请 新增强制检定器具检定信息'
            },
            controller: 'MandatoryCheckapplyforinstrumentuserAddinstrumentcheckinfoCtrl',
            params: {
                mandatoryInstrument: {},  // 强检器具
                type: {
                    value: 'add'
                },
                mandatoryInstrumentCheckApplyId: {}, // 检定申请ID
                instrumentCheckInfo: {}
            }
        })


        .state('mandatory.checkApplyForTechnicalInstitution', {
            url: '/checkApplyForTechnicalInstitution',
            templateUrl: 'views/mandatory/checkapplyfortechnicalinstitution/index.html',
            data: {
                pageTitle: '检定申请',
                pageDesc: '强检器具检定管理检定申请'
            },
            params: {
                page: 0,
                size: config.size,
                instrumentUserName: '',      // 器具用户名称
                startApplyDate: undefined,          // 申请开始日期
                endApplyDate: undefined           // 申请结束日期
            },
            controller: 'MandatoryCheckApplyForTechnicalInstitutionIndexCtrl'
        })

        .state('mandatory.checkApplyForTechnicalInstitution.audit', {
            url: "/audit/:workId",
            templateUrl: 'views/mandatory/checkapplyfortechnicalinstitution/audit.html',
            data: {
                pageTitle: '审核',
                pageDesc: '强检器具检定管理检定申请（适用于技术机构）'
            },
            controller: 'MandatoryCheckApplyForTechnicalInstitutionReplyCtrl'
        })

        .state('mandatory.checkApplyForTechnicalInstitutionAdd', {
            url: "/checkApplyForTechnicalInstitution/add",
            templateUrl: "views/mandatory/checkapplyfortechnicalinstitution/add.html",
            data: {
                pageTitle: '检定申请',
                pageDesc: '强检器具检定管理检定申请（适用于技术机构）'
            },
            controller: 'MandatoryCheckApplyForTechnicalInstitutionAddCtrl'
        })

        .state('mandatory.checkApplyForTechnicalInstitution.view', {
            url: "/view/:workId",
            templateUrl: 'views/mandatory/checkapplyfortechnicalinstitution/view.html',
            data: {
                pageTitle: '检定查看',
                pageDesc: '强检器具检定管理检定查看（适用于技术机构）'
            },
            controller: 'MandatoryCheckApplyForTechnicalInstitutionViewCtrl'
        })

        .state('InstrumentForced.Instrument', {
            // 路由值, . 表示该值继承于mandatory
            url: '/Instrument',
            templateUrl: 'views/mandatory/instrument/index.html',
            data: {
                pageTitle: '综合查询',
                pageDesc: '强检备案--综合查询'
            },
            params: {
                page: 0,
                size: config.size,
                district: {id: undefined},          // 区域
                department: {id: undefined},      // 器具用户
                checkDepartment: {id: undefined}, // 检定机构
                id: undefined,                              // id
                discipline: {id: undefined},  // 学科类别
                instrumentTypeFirstLevel: {id: undefined},    // 一级类别
                instrumentType: {id: undefined},  // 二级类别
                audit: undefined,    // 是否已审核
                name: undefined,    // 器具名称
                overdue: undefined // 是否超期未检
            },
            controller: 'MandatoryInstrumentIndexCtrl'
        })

        // 非强制检定管理
        .state('optional', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/optional", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '非强制检定管理'
            }
        })

        .state('optional.NumberSubjects', {
            url: "/NumberSubjects",
            templateUrl: "views/optional/numbersubjects/index.html",
            data: {
                pageTitle: '非强制检定工作计量器具档案',
                pageDesc: '非强制检定管理计量器具非强制检定工作计量器具档案'
            }
        })

        .state('optional.Integrated', {
            url: "/Integrated",
            templateUrl: "views/optional/integrated/index.html",
            data: {
                pageTitle: '非强制检定工作计量器具查询',
                pageDesc: '非强制检定管理非强制检定工作计量器具查询'
            }
        })

        .state('system.applyType', {
            url: "/applyType",
            templateUrl: "views/system/applyType/index.html",
            data: {
                pageTitle: '系统设置-申请类别管理',
                pageDesc: '计量系统申请类别管理'
            },
            controller: "systemApplyTypeIndexCtrl"
        })

        .state('system.applyTypeAdd', {
            url: "/applyTypeAdd",
            templateUrl: "views/system/applyType/add.html",
            data: {
                pageTitle: '系统设置-申请类别管理增加',
                pageDesc: '计量系统申请类别管理增加'
            },
            controller: "systemApplyTypeAddCtrl"
        })

        .state('system.applyTypeEdit', {
            url: "/applyTypeEdit/:applyTypeId",
            templateUrl: "views/system/applyType/add.html",
            data: {
                pageTitle: '系统设置-申请类别管理增加',
                pageDesc: '计量系统申请类别管理增加'
            },
            controller: "systemApplyTypeEditCtrl"
        })

        .state('system.applyField', {
            url: "/applyField/:applyTypeId", //此处传入的是申请类型的ID，根据申请类型的id查找出来该申请类型对应的所有的申请字段
            templateUrl: "views/system/applyField/index.html",
            data: {
                pageTitle: '系统设置-申请类别管理-申请字段',
                pageDesc: '系统设置申请类别管理中申请字段管理'
            },
            params: {
                applyTypeName: {
                    value: ''
                }
            },
            controller: "SystemApplyFieldIndexCtrl"
        })

        .state('system.applyFieldAdd', {
            url: "/applyFieldAdd/:applyTypeId", //此处传入的是申请类型的ID，根据申请类型的id来对应添加该申请类型下对应的有哪些申请字段
            templateUrl: "views/system/applyField/add.html",
            data: {
                pageTitle: '申请字段添加',
                pageDesc: '提供申请类型字段下有关数据的添加功能'
            },
            controller: "SystemApplyFieldAddCtrl"
        })

        .state('system.applyFieldEdit', {
            url: "/applyFieldEdit/:id", //此处传入的是申请字段的id，根据该申请字段的找到该id对应的申请字段实体，传入编辑界面的v层
            templateUrl: "views/system/applyField/add.html",
            data: {
                pageTitle: '申请字段添加',
                pageDesc: '提供申请类型字段下有关数据的添加功能'
            },
            controller: "SystemApplyFieldEditCtrl"
        })


        .state('optional.IntegratedAdd', {
            url: '/Integrated/add',
            templateUrl: 'views/optional/integrated/add.html',
            data: {
                pageTitle: '非强制检定工作计量器具查询--添加',
                pageDesc: '非强制检定管理非强制检定工作计量器具查询--添加'
            }
        })

        .state('optional.CheckDetail', {
            url: "/CheckDetail",
            templateUrl: "views/optional/checkdetail/index.html",
            data: {
                pageTitle: '检定信息',
                pageDesc: '非强制检定管理计量器具检定信息'
            }
        })

        .state('optional.Userapplication', {
            url: "/Userapplication",
            templateUrl: "views/optional/userapplication/index.html",
            data: {
                pageTitle: '用户检定申请',
                pageDesc: '非强制检定管理计量器具用户检定申请'
            }
        })

        .state('optional.Reply', {
            url: "/Reply",
            templateUrl: "views/optional/reply/index.html",
            data: {
                pageTitle: '机构申请回复',
                pageDesc: '非强制检定管理计量器具机构申请回复'
            }
        })

        // 企业行业最高计量标准管理
        .state('higheststandard', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/higheststandard", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '企业行业最高计量标准管理'
            }
        })

        .state('higheststandard.Reply', {
            url: "/Reply",
            templateUrl: "views/higheststandard/file/index.html",
            data: {
                pageTitle: '企业行业最高计量标准档案',
                pageDesc: '企业行业最高计量标准管理企业行业最高计量标准档案'
            }
        })

        // 技术机构管理
        .state('standard', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/standard", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '标准装置管理'
            }
        })

        .state('standard.deviceSetManage', {
            url: '/deviceSetManage',
            templateUrl: 'views/standard/devicesetmanage/index.html',
            data: {
                pageTitle: '档案管理',
                pageDesc: '标准装置管理--档案管理'
            },
            params: {
                page: 0,
                size: config.size
            },
            controller: 'StandardDeviceSetManageIndexCtrl'
        })

        .state('standard.deviceSetManage.authorization', {
            url: '/authorization/:deviceSetId',
            templateUrl: 'views/standard/deviceSetManage/authorization/index.html',
            data: {
                pageTitle: '检定项目',
                pageDesc: '查看检定项目'
            },
            params: {
                district: {},
                department: {},
                discipline: {},
                instrumentTypeFirstLevel: {},
                instrumentType: {},
                deviceSet: {name:undefined}
            },
            controller: 'StandardAuthorizationIndexCtrl'
        })

        .state('standard.deviceSetManage.detail', { //此路由表示计量标准装置的标准器页面
            url: '/detail/:deviceSetId',
            templateUrl: 'views/standard/devicesetmanage/detail.html',
            data: {
                pageTitle: '标准器信息',
                pageDesc: '标准装置管理--标准器信息'
            },
            params: {
                type: {
                    value: 'detail'
                },
                id: {
                    value: '0'
                }
            },
            controller: 'StandardFileDetailCtrl'

        })

        .state('standard.deviceSetManage.detail.add', { //此路由标识标准器的增加页面
            url: '/add',
            templateUrl: 'views/standard/file/detailadd.html',
            data: {
                pageTitle: '新增',
                pageDesc: '标准装置管理--标准器信息--新增'
            },
            controller: 'StandardFiledetailaddCtrl'
        })

        .state('standard.deviceSetManage.detail.edit', { //此路由标识标准器的增加页面
            url: "/edit/:id",
            templateUrl: 'views/standard/file/detailadd.html',
            data: {
                pageTitle: '编辑',
                pageDesc: '编辑标准器信息'
            },
            params: {
                standardDevice: undefined // 标准器
            },
            controller: 'StandardFileEditCtrl'
        })

        .state('standard.deviceSetManage.detail.checkInfo', { //此路由表示计量标准装置的标准器页面
            url: '/checkInfo/:standardDeviceId',
            templateUrl: 'views/standard/file/checkdetail.html',
            data: {
                pageTitle: '检定档案',
                pageDesc: '标准器的检定信息'
            },
            controller: 'StandardFileCheckdetailCtrl'
        })
        .state('standard.deviceSetManage.detail.checkInfo.add', { //此路由表示计量标准装置的标准器页面
            url: '/add',
            templateUrl: 'views/standard/file/checkdetailadd.html',
            data: {
                pageTitle: '新增',
                pageDesc: '新增标准器的检定记录'
            },
            controller: 'StandardFileCheckdetailAddCtrl'
        })
        .state('standard.deviceSetManage.detail.checkInfo.edit', { //此路由表示计量标准装置的标准器页面
            url: "/edit/:id",
            templateUrl: "views/standard/file/checkdetailedit.html",
            data: {
                pageTitle: '技术机构计量标准装置一览表--标准器检定信息详情编辑',
                pageDesc: '技术机构管理技术机构计量标准装置一览表--标准器检定信息详情编辑'
            },
            params: {
                type: {
                    value: 'edit'
                },
                standardDeviceCheckDetail: {
                    value: {}
                }
            },
            controller: 'StandardFileCheckdetailEditCtrl'
        })

        .state('standard.File', {
            url: '/File',
            templateUrl: 'views/standard/file/index.html',
            data: {
                pageTitle: '综合查询',
                pageDesc: '标准装置管理--综合查询'
            },
            params: {
                page: 0,
                size: config.size,
                name: undefined,           //标准装置名称
                code: undefined,           // 器具代码
                district: {id: undefined},      // 区域
                department: {id: undefined}     // 技术机构
            },
            controller: 'StandardFileIndexCtrl'
        })

        .state('standard.File.detail', {
            url: '/Detail/:deviceSetId',
            templateUrl: 'views/standard/file/detail.html',
            data: {
                pageTitle: '标准器信息',
                pageDesc: '档案管理--标准器信息'
            },
            params: {
                deviceSetId: {
                    value: '0'
                }
            },
            controller: 'StandardFileDetailCtrl'
        })

        .state('standard.File.authorization', {
            url: '/authorization/:deviceSetId',
            templateUrl: 'views/standard/file/authorization/index.html',
            data: {
                pageTitle: '检定项目',
                pageDesc: '标准装置管理--授权检定项目综合查询'
            },
            params: {
                district: {},
                department: {},
                discipline: {},
                instrumentTypeFirstLevel: {},
                instrumentType: {},
                deviceSet: {name:undefined}
            },
            controller: 'StandardAuthorizationIndexCtrl'
        })

        .state('standard.FileAdd', {
            url: '/File/Add',
            templateUrl: 'views/standard/file/add.html',
            data: {
                pageTitle: '新增',
                pageDesc: '标准装置管理--新增'
            },
            params: {
                type: {
                    value: 'add'
                }
            },
            controller: 'StandardFileAddCtrl'
        })
        .state('standard.FileEdit', {
            url: "/File/Edit",
            templateUrl: "views/standard/file/add.html",
            data: {
                pageTitle: '编辑',
                pageDesc: '标准装置管理--编辑'
            },
            params: {
                deviceSet: {
                    value: {}
                },
                type: {
                    value: 'edit'
                }
            },
            controller: 'StandardFileAddCtrl'
        })

        .state('standard.FileDeviceInstrument', { //此路由授权检定项目
            url: '/FileDeviceInstrument',
            templateUrl: 'views/standard/filedeviceinstrument/index.html',
            data: {
                pageTitle: '授权检定项目管理',
                pageDesc: '标准装置管理--授权检定项目管理'
            },
            params: {
                deviceSetId: undefined     // 标准装置
            },
            controller: 'StandardFiledeviceinstrumentIndexCtrl'
        })

        .state('standard.Authorization', {
            url: '/Authorization',
            templateUrl: 'views/standard/authorization/index.html',
            data: {
                pageTitle: '授权检定项目综合查询',
                pageDesc: '标准装置管理--授权检定项目综合查询'
            },
            params: {
                page: 0,
                size: config.size,
                district: {id: undefined},
                department: {id: undefined},
                discipline: {id: undefined},  // 学科类别
                instrumentTypeFirstLevel: {id: undefined},    // 一级类别
                instrumentType: {id: undefined},  // 二级类别
                route: 'Authorization',
                deviceSet: {id: undefined, name :undefined}       // 标准装置
            },
            controller: 'StandardAuthorizationIndexCtrl'
        })

        .state('standard.AuthorizationAdd', {
            url: "/Authorization/Add/:id",
            templateUrl: "views/standard/authorization/add.html",
            data: {
                pageTitle: '技术机构授权检定项目一览表--新增',
                pageDesc: '技术机构管理技术机构授权检定项目一览表--新增'
            },
            params: {
                id: {
                    value: '0'
                }
            },
            controller: 'StandardAuthorizationAddCtrl'
        })

        .state('standard.AuthorizationEdit', {
            url: "/Authorization/Edit/:instrumentTypeId/:deviceSetId",
            templateUrl: "views/standard/authorization/add.html",
            data: {
                pageTitle: '编辑',
                pageDesc: '授权检定项目 -- 编辑'
            },
            params: {
                instrumentTypeId: {
                    value: '0'
                },
                deviceSetId: {
                    value: '0'
                }
            },
            controller: 'StandardAuthorizationEditCtrl'
        })



        .state('standard.Fixedassets', {
            url: "/Fixedassets",
            templateUrl: "views/standard/fixedassets/index.html",
            data: {
                pageTitle: '技术机构固定资产一览表',
                pageDesc: '技术机构管理技术机构固定资产一览表'
            }
        })

        .state('standard.Ability', {
            url: "/Ability",
            templateUrl: "views/standard/ability/index.html",
            data: {
                pageTitle: '技术机构能力建设申请、进度表',
                pageDesc: '技术机构管理技术机构能力建设申请进度表'
            }
        })
        .state('standard.Schedule', {
            url: "/Schedule",
            templateUrl: "views/standard/schedule/index.html",
            data: {
                pageTitle: '检定进度查询',
                pageDesc: '技术机构管理检定进度查询'
            }
        })

        .state('standard.Technology', {
            url: "/Technology",
            templateUrl: "views/standard/technology/index.html",
            data: {
                pageTitle: '授权检定机构查询',
                pageDesc: '标准装置管理--授权检定机构查询'
            }
        })

        // 计量器具产品管理
        .state('measuringdevice', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/measuringdevice", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '计量器具产品管理'
            }
        })

        .state('measuringdevice.Integrated', {
            url: "/Integrated",
            templateUrl: "views/measuringdevice/integrated/index.html",
            data: {
                pageTitle: '计量器具产品档案',
                pageDesc: '计量器具产品管理计量器具产品档案'
            }
        })

        .state('measuringdevice.ApplianceArchives', {
            url: "/ApplianceArchives",
            templateUrl: "views/measuringdevice/appliancearchives/index.html",
            data: {
                pageTitle: '计量器具生产企业档案',
                pageDesc: '计量器具产品管理计量器具生产企业档案'
            }
        })

        .state('measuringdevice.ApplianceArchivesAdd', {
            url: "/ApplianceArchives/add",
            templateUrl: "views/measuringdevice/appliancearchives/add.html",
            data: {
                pageTitle: '计量器具生产企业档案--新增',
                pageDesc: '计量器具产品管理计量器具生产企业档案--新增'
            }
        })

        .state('measuringdevice.EnterpriseFile', {
            url: "/EnterpriseFile",
            templateUrl: "views/measuringdevice/enterprisefile/index.html",
            data: {
                pageTitle: '获证产品目录',
                pageDesc: '获证产品目录'
            }
        })

        .state('measuringdevice.EnterpriseFileAdd', {
            url: "/EnterpriseFile/add",
            templateUrl: "views/measuringdevice/enterprisefile/add.html",
            data: {
                pageTitle: '获证产品目录',
                pageDesc: '获证产品目录'
            }
        })

        //重点用能企业计量管理
        .state('energyenterprise', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/energyenterprise", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '重点用能企业计量管理'
            }
        })

        .state('energyenterprise.File', {
            url: "/File",
            templateUrl: "views/energyenterprise/file/index.html",
            data: {
                pageTitle: '能源计量器具档案',
                pageDesc: '重点用能企业计量管理能源计量器具档案'
            }
        })

        .state('energyenterprise.Integrated', {
            url: "/Integrated",
            templateUrl: "views/energyenterprise/integrated/index.html",
            data: {
                pageTitle: '能源计量器具一览表',
                pageDesc: '重点用能企业计量管理能源计量器具一览表'
            }
        })

        .state('energyenterprise.Measuringdevice', {
            url: "/Measuringdevice",
            templateUrl: "views/energyenterprise/measuringdevice/index.html",
            data: {
                pageTitle: '进出用能单位能源计量器具一览表',
                pageDesc: '重点用能企业计量管理进出用能单位能源计量器具一览表'
            }
        })

        .state('energyenterprise.Secondarydevice', {
            url: "/Secondarydevice",
            templateUrl: "views/energyenterprise/secondarydevice/index.html",
            data: {
                pageTitle: '进出主要次级用能单位能源计量器具一览表',
                pageDesc: '重点用能企业计量管理进出主要次级用能单位能源计量器具一览表'
            }
        })

        .state('energyenterprise.Maindevice', {
            url: "/Maindevice",
            templateUrl: "views/energyenterprise/maindevice/index.html",
            data: {
                pageTitle: '主要用能设备能源计量器具一览表',
                pageDesc: '重点用能企业计量管理主要用能设备能源计量器具一览表'
            }
        })

        .state('energyenterprise.Otherdevice', {
            url: "/Otherdevice",
            templateUrl: "views/energyenterprise/otherdevice/index.html",
            data: {
                pageTitle: '其他能源计量器具一览表',
                pageDesc: '重点用能企业计量管理其他能源计量器具一览表'
            }
        })

        .state('energyenterprise.Summary', {
            url: "/Summary",
            templateUrl: "views/energyenterprise/summary/index.html",
            data: {
                pageTitle: '能源计量器具配备情况汇总表',
                pageDesc: '重点用能企业计量管理能源计量器具配备情况汇总表'
            }
        })

        .state('energyenterprise.Statisticalsummary', {
            url: "/Statisticalsummary",
            templateUrl: "views/energyenterprise/statisticalsummary/index.html",
            data: {
                pageTitle: '能源计量器具配备情况统计汇总表',
                pageDesc: '重点用能企业计量管理能源计量器具配备情况统计汇总表'
            }
        })

        .state('energyenterprise.Accuracy', {
            url: "/Accuracy",
            templateUrl: "views/energyenterprise/accuracy/index.html",
            data: {
                pageTitle: '能源计量器具准确度等级统计汇总表',
                pageDesc: '重点用能企业计量管理能源计量器具准确度等级统计汇总表'
            }
        })

        .state('energyenterprise.Flowgraph', {
            url: "/Flowgraph",
            templateUrl: "views/energyenterprise/flowgraph/index.html",
            data: {
                pageTitle: '重点用能单位能源流向图',
                pageDesc: '重点用能企业计量管理重点用能单位能源流向图'
            }
        })

        .state('energyenterprise.Map', {
            url: "/Map",
            templateUrl: "views/energyenterprise/map/index.html",
            data: {
                pageTitle: '能源采集点网络图',
                pageDesc: '重点用能企业计量管理能源采集点网络图'
            }
        })

        .state('energyenterprise.Equipment', {
            url: "/Equipment",
            templateUrl: "views/energyenterprise/equipment/index.html",
            data: {
                pageTitle: '主要用能设备一览表',
                pageDesc: '重点用能企业计量管理主要用能设备一览表'
            }
        })

        .state('energyenterprise.personnel', {
            url: "/personnel",
            templateUrl: "views/energyenterprise/personnel/index.html",
            data: {
                pageTitle: '能源计量人员一览表',
                pageDesc: '重点用能企业计量管理能源计量人员一览表'
            }
        })

        .state('energyenterprise.Training', {
            url: "/Training",
            templateUrl: "views/energyenterprise/training/index.html",
            data: {
                pageTitle: '能源计量人员培训管理',
                pageDesc: '重点用能企业计量管理能源计量人员培训管理'
            }
        })

        .state('energyenterprise.Statistics', {
            url: "/Statistics",
            templateUrl: "views/energyenterprise/statistics/index.html",
            data: {
                pageTitle: '重点用能单位能源购进、消费、库存统计表',
                pageDesc: '重点用能企业计量管理重点用能单位能源购进、消费、库存统计表'
            }
        })

        .state('energyenterprise.Energystatistics', {
            url: "/Energystatistics",
            templateUrl: "views/energyenterprise/energystatistics/index.html",
            data: {
                pageTitle: '能源消费动态统计',
                pageDesc: '重点用能企业计量管理能源消费动态统计'
            }
        })

        //监督抽查
        .state('supervise', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/supervise", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '监督抽查'
            }
        })

        .state('supervise.Organization', {
            url: "/Organization",
            templateUrl: "views/supervise/organization/index.html",
            data: {
                pageTitle: '授权检定机构监督抽查',
                pageDesc: '监督抽查授权检定机构监督抽查'
            }
        })

        .state('supervise.Standard', {
            url: "/Standard",
            templateUrl: "views/supervise/standard/index.html",
            data: {
                pageTitle: '计量标准监督抽查',
                pageDesc: '监督抽查计量标准监督抽查'
            }
        })

        .state('supervise.Manufacturing', {
            url: "/Manufacturing",
            templateUrl: "views/supervise/manufacturing/index.html",
            data: {
                pageTitle: '计量器具制造企业监督抽查',
                pageDesc: '监督抽查计量器具制造企业监督抽查'
            }
        })

        .state('supervise.Suttle', {
            url: "/Suttle",
            templateUrl: "views/supervise/suttle/index.html",
            data: {
                pageTitle: '定量包装商品净含量监督抽查',
                pageDesc: '监督抽查定量包装商品净含量监督抽查'
            }
        })

        .state('supervise.Instrument', {
            url: "/Instrument",
            templateUrl: "views/supervise/instrument/index.html",
            data: {
                pageTitle: '重点计量器具监督抽查',
                pageDesc: '监督抽查重点计量器具监督抽查'
            }
        })

        .state('supervise.Enterpriseinstrument', {
            url: "/Enterpriseinstrument",
            templateUrl: "views/supervise/enterpriseinstrument/index.html",
            data: {
                pageTitle: '重点耗能企业计量器具监督抽查',
                pageDesc: '监督抽查重点耗能企业计量器具监督抽查'
            }
        })

        .state('supervise.Department', {
            url: "/Department",
            templateUrl: "views/supervise/department/index.html",
            data: {
                pageTitle: '能源标识使用单位监督抽查',
                pageDesc: '监督抽查能源标识使用单位监督抽查'
            }
        })

        .state('supervise.Measure', {
            url: "/Measure",
            templateUrl: "views/supervise/measure/index.html",
            data: {
                pageTitle: '法定计量单位监督抽查',
                pageDesc: '监督抽查法定计量单位监督抽查'
            }
        })

        //人员资质
        .state('personnel', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/personnel", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '人员管理'
            }
        })

        .state('personnel.personnelQualificationManage', {
            url: '/personnelQualificationManage',
            templateUrl: 'views/standard/personnelfile/index.html',
            data: {
                pageTitle: '综合查询',
                pageDesc: '人员管理--综合查询'
            },
            params: {
                page: 0,
                size: config.size,
                district: {id: undefined},  // 区域
                name: undefined,    // 人员资质名称
                departmentName: undefined // 技术机构名称
            },
            controller: 'StandardPersonnelfileIndexCtrl'
        })

        // .state('personnel.personnelQualificationManageEdit', {
        //  url: "/personnelQualificationManageEdit",
        //  templateUrl: "views/standard/personnelfile/add.html",
        //  data: {
        //      pageTitle: '综合查询--编辑人员',
        //      pageDesc: '人员管理综合查询--编辑人员'
        //  },
        //  params: {
        //      type: {
        //          value: 'edit'
        //      },
        //      data: {
        //          value: {}
        //      }

        //  },
        //  controller: 'StandardPersonnelfileAddCtrl'
        // })

        // .state('personnel.personnelQualificationManageDetail', {
        //  url: "/personnelQualificationManage/Detail",
        //  templateUrl: "views/standard/personnelfile/detail.html",
        //  data: {
        //      pageTitle: '人员资质',
        //      pageDesc: '人员资质管理--人员资质综合查询'
        //  },
        //  controller: 'StandardPersonnelfileDetailCtrl'
        // })

        // .state('personnel.personnelQualificationManageDetailAdd', {
        //  url: "/personnelQualificationManage/DetailAdd",
        //  templateUrl: "views/standard/personnelfile/detailadd.html",
        //  data: {
        //      pageTitle: '人员资质--新增资质',
        //      pageDesc: '人员资质管理--人员资质综合查询--新增资质'
        //  },
        //  params: {
        //      type: {
        //          value: 'add'
        //      },
        //      data: {
        //          value: {}
        //      }

        //  },
        //  controller: 'StandardPersonnelfileDetailaddCtrl',
        // })

        // .state('personnel.personnelQualificationManageDetailEdit', {
        //  url: "/personnelQualificationManage/DetailEdit",
        //  templateUrl: "views/standard/personnelfile/detailadd.html",
        //  data: {
        //      pageTitle: '人员资质--编辑资质',
        //      pageDesc: '人员资质管理--人员资质综合查询--编辑资质'
        //  },
        //  params: {
        //      type: {
        //          value: 'edit'
        //      },
        //      data: {
        //          value: {}
        //      }

        //  },
        //  controller: 'StandardPersonnelfileDetailaddCtrl',
        // })

        .state('personnel.PersonnelFile', {
            url: '/PersonnelFile',
            templateUrl: 'views/personnel/personnelfile/index.html',
            data: {
                pageTitle: '档案管理',
                pageDesc: '人员管理--档案管理'
            },
            params: {
                page: 0,
                size: config.size
            },
            controller: 'PersonnelPersonnelfileIndexCtrl'
        })

        .state('personnel.PersonnelFileAdd', {
            url: "/PersonnelFile/Add",
            templateUrl: "views/personnel/personnelfile/add.html",
            data: {
                pageTitle: '档案管理--新增',
                pageDesc: '人员管理--档案管理新增'
            },
            params: {
                type: {
                    value: 'add'
                },
                data: {
                    value: {}
                }
            },
            controller: 'StandardPersonnelfileAddCtrl',
        })

        // .state('personnel.PersonnelFileDetail', {
        //  url: "/PersonnelFile/Detail",
        //  templateUrl: "views/personnel/personnelfile/detail.html",
        //  data: {
        //      pageTitle: '档案管理--人员资质',
        //      pageDesc: '人员资质管理--人员资质'
        //  },
        //  controller: 'StandardPersonnelfileDetailCtrl',
        // })


        // .state('personnel.PersonnelFileDetailAdd', {
        //  url: "/PersonnelFile/DetailAdd",
        //  templateUrl: "views/personnel/personnelfile/detailadd.html",
        //  data: {
        //      pageTitle: '人员资质--新增资质',
        //      pageDesc: '人员资质管理--人员资质档案管理--新增资质'
        //  },
        //  params: {
        //      type: {
        //          value: 'add'
        //      },
        //      data: {
        //          value: {}
        //      }

        //  },
        //  controller: 'StandardPersonnelfileDetailaddCtrl',
        // })

        // .state('personnel.PersonnelFileDetailEdit', {
        //  url: "/PersonnelFile/DetailEdit",
        //  templateUrl: "views/personnel/personnelfile/detailadd.html",
        //  data: {
        //      pageTitle: '人员资质--编辑资质',
        //      pageDesc: '人员资质管理--人员资质档案管理--编辑资质'
        //  },
        //  params: {
        //      type: {
        //          value: 'edit'
        //      },
        //      data: {
        //          value: {}
        //      }

        //  },
        //  controller: 'StandardPersonnelfileDetailaddCtrl',
        // })

        //注册信息
        .state('register', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/register", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '注册信息'
            }
        })

        .state('register.Technology', {
            url: "/Technology",
            templateUrl: "views/register/technology/index.html",
            data: {
                pageTitle: '技术机构注册信息',
                pageDesc: '注册信息技术机构注册信息'
            }
        })

        .state('register.TechnologyAdd', {
            url: "/Technology/Add",
            templateUrl: "views/register/technology/add.html",
            data: {
                pageTitle: '技术机构注册信息--新增',
                pageDesc: '注册信息技术机构注册信息--新增'
            }
        })

        .state('register.Enterprise', {
            url: "/Enterprise",
            templateUrl: "views/register/enterprise/index.html",
            data: {
                pageTitle: '定量包装商品生产、销售企业注册信息',
                pageDesc: '注册信息定量包装商品生产、销售企业注册信息'
            }
        })

        .state('register.EnterpriseAdd', {
            url: "/Enterprise/Add",
            templateUrl: "views/register/enterprise/add.html",
            data: {
                pageTitle: '定量包装商品生产、销售企业注册信息--新增',
                pageDesc: '注册信息定量包装商品生产、销售企业注册信息--新增'
            }
        })

        .state('register.Society', {
            url: "/Society",
            templateUrl: "views/register/society/index.html",
            data: {
                pageTitle: '社会公众注册信息',
                pageDesc: '注册信息社会公众注册信息'
            }
        })

        .state('register.Energycompanies', {
            url: "/Energycompanies",
            templateUrl: "views/register/energycompanies/index.html",
            data: {
                pageTitle: '重点用能企业注册信息',
                pageDesc: '注册信息重点用能企业注册信息'
            }
        })

        //行政许可
        .state('business', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/business", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '行政许可'
            }
        })

        .state('business.Measurement', {
            url: "/Measurement",
            templateUrl: "views/business/measurement/index.html",
            data: {
                pageTitle: '计量标准建标考核（复查）申请',
                pageDesc: '计量标准建标考核（复查）申请'
            }
        })

        .state('business.MeasurementAdd', {
            url: "/Measurement/Add",
            templateUrl: "views/business/measurement/add.html",
            data: {
                pageTitle: '计量标准建标考核（复查）申请--新增',
                pageDesc: '计量标准建标考核（复查）申请--新增'
            }
        })

        .state('business.Technology', {
            url: "/Technology",
            templateUrl: "views/business/technology/index.html",
            data: {
                pageTitle: '技术机构考核（复查）申请',
                pageDesc: '技术机构考核（复查）申请'
            }
        })

        .state('business.TechnologyAdd', {
            url: "/Technology/Add",
            templateUrl: "views/business/technology/add.html",
            data: {
                pageTitle: '技术机构考核（复查）申请--新增',
                pageDesc: '技术机构考核（复查）申请--新增'
            }
        })

        .state('business.MeasureTool', {
            url: "/MeasureTool",
            templateUrl: "views/business/measuretool/index.html",
            data: {
                pageTitle: '计量器具生产许可证(年检)申请',
                pageDesc: '计量器具生产许可证(年检)申请'
            }
        })

        .state('business.MeasureToolAdd', {
            url: "/MeasureTool/Add",
            templateUrl: "views/business/measuretool/add.html",
            data: {
                pageTitle: '计量器具生产许可证(年检)申请--新增',
                pageDesc: '计量器具生产许可证(年检)申请--新增'
            }
        })

        // 查看工作
        .state('mywork.view', {
            url: "/view/:id",
            templateUrl: "views/mywork/view.html",
            data: {
                pageTitle: '查看',
                pageDesc: '我的工作-查看'
            },
            controller: 'MyworkViewCtrl'
        })

        // 添加工作
        .state('mywork.add', {
            url: "/add/applyTypeId/:applyTypeId",
            templateUrl: "views/mywork/add.html",
            data: {
                pageTitle: '添加',
                pageDesc: '我的工作-添加'
            },
            controller: 'MyworkAddCtrl'
        })

        // 编辑工作
        .state('mywork.edit', {
            url: "/edit/:id",
            templateUrl: "views/mywork/edit.html",
            data: {
                pageTitle: '编辑',
                pageDesc: '我的工作-编辑'
            },
            controller: 'MyworkEditCtrl'
        })

        //岗位管理
        .state('post', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/post", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '岗位管理'
            }
        })

        .state('post.Postfile', {
            url: "/Postfile",
            templateUrl: "views/post/postfile/index.html",
            data: {
                pageTitle: '岗位管理',
                pageDesc: '计量系统岗位管理'
            }
        })

        .state('post.PostfileAdd', {
            // 路由值, 表示该值继承于post
            url: "/PostfileAdd",
            templateUrl: "views/post/postfile/add.html",
            data: {
                pageTitle: '岗位管理——新增',
                pageDesc: '计量系统岗位管理'
            }
        })

        .state('post.PostfileEdit', {
            // 路由值, 表示该值继承于post
            url: "/PostfileEdit",
            templateUrl: "views/post/postfile/edit.html",
            data: {
                pageTitle: '岗位管理——编辑',
                pageDesc: '计量系统岗位管理'
            }
        })

        .state('post.PostfileDetail', {
            // 路由值, 表示该值继承于post
            url: "/PostfileDetail",
            templateUrl: "views/post/postfile/detail.html",
            data: {
                pageTitle: '岗位管理——详情页面',
                pageDesc: '计量系统岗位管理'
            }
        })

        //其他功能
        .state('others', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/others", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '其他功能'
            }
        })

        .state('others.Integritylist', {
            url: "/Integritylist",
            templateUrl: "views/others/integritylist/index.html",
            data: {
                pageTitle: '诚信体系红黑榜',
                pageDesc: '其他功能诚信体系红黑榜'
            }
        })

        .state('others.Platform', {
            url: "/Platform",
            templateUrl: "views/others/platform/index.html",
            data: {
                pageTitle: '技术监督质量信息平台链接',
                pageDesc: '其他功能技术监督质量信息平台链接'
            }
        })

        //用户管理
        .state('department', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/department",
            templateUrl: "views/common/content.html", // 模板文件
            data: {
                pageTitle: '用户管理'
            }
        })

        .state('department.user', {
            // 路由值, 表示该值继承于department
            url: "/user/departmentId/:departmentId",
            templateUrl: "views/department/user/index.html",
            data: {
                pageTitle: '登录用户管理'
            },
            controller: 'DepartmentUserIndexCtrl'
        })


        // 用户管理 -> 登录用户管理 -> 新增
        .state('department.userAdd', {
            // 路由值, 表示该值继承于department
            url: "/add/departmentId/:departmentId",
            templateUrl: "views/department/user/add.html",
            data: {
                pageTitle: '新增'
            },
            controller: 'DepartmentUserAddCtrl'
        })


        .state('department.enterpriseAdd', {
            // 路由值, 表示该值继承于department
            url: "/enterpriseAdd",
            templateUrl: "views/department/enterprise/add.html",
            data: {
                pageTitle: '生产企业——新增',
                pageDesc: '用户管理--生产企业'
            },
            controller: 'DepartmentEnterpriseAddCtrl'
        })


        .state('department.instrumentUser', {
            // 路由值, 表示该值继承于department
            url: '/instrumentUser',
            templateUrl: 'views/department/instrumentuser/index.html',
            data: {
                pageTitle: '器具用户',
                pageDesc: '用户管理--器具用户'
            },
            params: {
                type: {
                    value: 'qijuyonghu'
                },
                page: 0,
                size: config.size
            },
            controller: 'DepartmentInstrumentuserIndexCtrl'
        })

        .state('department.instrumentUserAdd', {
            // 路由值, 表示该值继承于department
            url: "/instrumentUserAdd",
            templateUrl: "views/department/instrumentuser/add.html",
            data: {
                pageTitle: '器具用户——新增',
                pageDesc: '用户管理--器具用户'
            },
            controller: 'DepartmentInstrumentUserAddCtrl'
        })


        .state('department.instrumentUserEdit', {
            // 路由值, 表示该值继承于department
            url: "/instrumentUserEdit/:id",
            templateUrl: "views/department/instrumentuser/edit.html",
            data: {
                pageTitle: '器具用户——编辑',
                pageDesc: '用户管理--器具用户'
            },
            controller: 'DepartmentInstrumentuserEditCtrl'
        })

        .state('department.technicalInstitution', {
            // 路由值, 表示该值继承于department
            url: "/technicalInstitution/page/:page/size/:size",
            templateUrl: "views/department/technicalinstitution/index.html",
            data: {
                pageTitle: '技术机构',
                pageDesc: '用户管理--技术机构'
            },
            params: {
                type: {
                    value: 'jishujigou'
                }
            },
            controller: 'DepartmentTechnicalinstitutionIndexCtrl'
        })

        //用户管理
        .state('department.technicalInstitutionAdd', {
            // 路由值
            url: "/technicalInstitutionAdd",
            templateUrl: "views/department/technicalinstitution/add.html", // 模板文件
            data: {
                pageTitle: '技术机构——新增',
                pageDesc: '用户管理--技术机构'
            },
            controller: 'DepartmentTechnicalinstitutionAddCtrl'
        })

        .state('department.technicalInstitutionEdit', {
            // 路由值, 表示该值继承于department
            url: "/technicalInstitutionEdit/:id",
            templateUrl: "views/department/technicalinstitution/edit.html",
            data: {
                pageTitle: '技术机构——编辑',
                pageDesc: '用户管理--技术机构'
            },
            params: {
                department: {
                    value: ''
                }
            },
            controller: 'DepartmentTechnicalinstitutionEditCtrl'
        })

        .state('department.enterprise', {
            // 路由值, 表示该值继承于department
            url: "/enterprise/page/:page/size/:size",
            templateUrl: "views/department/enterprise/index.html",
            data: {
                pageTitle: '生产企业',
                pageDesc: '用户管理--生产企业'
            },
            params: {
                type: {
                    value: 'shengchanqiye'
                }
            },
            controller: 'DepartmentEnterpriseIndexCtrl'
        })

        .state('department.enterpriseEdit', {
            // 路由值, 表示该值继承于department
            url: "/enterpriseEdit/:id",
            templateUrl: "views/department/enterprise/edit.html",
            data: {
                pageTitle: '生产企业——编辑',
                pageDesc: '用户管理--生产企业'
            },
            controller: 'DepartmentEnterpriseEditCtrl'
        })

        .state('department.management', {
            // 路由值, 表示该值继承于department
            url: '/management',
            templateUrl: 'views/department/management/index.html',
            data: {
                pageTitle: '管理部门',
                pageDesc: '用户管理--管理部门'
            },
            params: {
                type: {
                    value: 'guanlibumen'
                }
            },
            controller: 'DepartmentManagementIndexCtrl'
        })

        .state('department.managementAdd', {
            // 路由值, 表示该值继承于department
            url: "/managementAdd",
            templateUrl: "views/department/management/add.html",
            data: {
                pageTitle: '管理部门——新增',
                pageDesc: '用户管理--管理部门'
            },
            controller: 'DepartmentManagementAddCtrl'
        })

        .state('department.managementEdit', {
            // 路由值, 表示该值继承于department
            url: "/managementEdit/:id",
            templateUrl: "views/department/management/edit.html",
            data: {
                pageTitle: '管理部门——编辑',
                pageDesc: '用户管理--管理部门'
            },
            params: {
                department: {
                    value: ''
                }
            },
            controller: 'DepartmentEditCtrl'
        })

        //系统设置
        .state('system', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/system",
            templateUrl: "views/common/content.html", // 模板文件
            data: {
                pageTitle: '系统设置'
            }
        })

        .state('system.viewSpecialChar', {
            // 路由值, 表示该值继承于system
            url: "/viewSpecialChar",
            templateUrl: "views/system/viewSpecialChar/index.html",
            data: {
                pageTitle: '查看特殊字符',
                pageDesc: '计量系统查看特殊字符'
            }
        })

        .state('system.Menu', {
            // 路由值, 表示该值继承于system
            url: "/Menu",
            templateUrl: "views/system/menu/index.html",
            data: {
                pageTitle: '菜单管理',
                pageDesc: '计量系统菜单管理'
            }
        })

        .state('system.MenuAdd', {
            // 路由值, 表示该值继承于system
            url: "/MenuAdd",
            templateUrl: "views/system/menu/add.html",
            data: {
                pageTitle: '菜单管理-新增菜单',
                pageDesc: '计量系统菜单管理'
            }
        })

        .state('system.WorkFlowTypeManage', {
            // 路由值, 表示该值继承于workflowtype
            url: "/WorkFlowTypeManage",
            templateUrl: "views/workflowtype/workflowtypemanage/index.html",
            data: {
                pageTitle: '工作流管理',
                pageDesc: '计量系统工作流管理'
            }
        })

        .state('system.WorkFlowTypeManageAdd', {
            // 路由值, 表示该值继承于workflowtype
            url: "/WorkFlowTypeManageAdd",
            templateUrl: "views/workflowtype/workflowtypemanage/add.html",
            data: {
                pageTitle: '工作流管理-新增页面',
                pageDesc: '计量系统工作流管理'
            }
        })

        //角色管理
        .state('system.role', {
            url: "/role",
            templateUrl: "views/role/rolefile/index.html",
            data: {
                pageTitle: '角色管理',
                pageDesc: '计量系统角色管理'
            }
        })

        .state('system.RoleAdd', {
            url: "/roleAdd",
            templateUrl: "views/role/add.html",
            data: {
                pageTitle: '角色管理--新增',
                pageDesc: '计量系统角色管理新增'
            },
            controller: 'RoleAddCtrl'
        })

        .state('system.RolefileAdd', {
            // 路由值, 表示该值继承于role
            url: "/RolefileAdd",
            templateUrl: "views/role/rolefile/add.html",
            data: {
                pageTitle: '角色管理——新增',
                pageDesc: '计量系统角色管理'
            },
            controller: 'RoleRolefileAddCtrl'
        })

        .state('system.RolefileEdit', {
            // 路由值, 表示该值继承于role
            url: "/RolefileEdit",
            templateUrl: "views/role/rolefile/add.html",
            data: {
                pageTitle: '角色管理——编辑',
                pageDesc: '计量系统角色管理'
            },
            controller: 'RoleRolefileEditCtrl',
            params: {
                data: { // 角色对象
                    value: {}
                }
            }
        })

        .state('system.RolefileDetail', {
            // 路由值, 表示该值继承于role
            url: "/RolefileDetail",
            templateUrl: "views/role/rolefile/detail.html",
            data: {
                pageTitle: '角色管理——详情页面',
                pageDesc: '计量系统角色管理'
            }
        })


        .state('system.Userfile', {
            url: "/Userfile/departmentId/:departmentId",
            templateUrl: "views/user/userfile/index.html",
            data: {
                pageTitle: '用户管理',
                pageDesc: '计量系统用户管理'
            },
            controller: 'UserUserfileIndexCtrl',
            params: {
                page: {
                    value: '0'
                },
                size: {
                    value: config.size.toString()
                },
                districtId: {
                    value: '0'
                },
                departmentTypeId: {
                    value: '0'
                },
                status: {
                    value: '-2'
                },
                departmentName: {
                    value: ''
                },
                username: {
                    value: ''
                }
            }
        })

        .state('system.UserfileAdd', {
            // 路由值, 表示该值继承于user
            url: "/UserfileAdd/departmentId/:departmentId",
            templateUrl: "views/user/userfile/add.html",
            data: {
                pageTitle: '用户管理——新增',
                pageDesc: '计量系统用户管理'
            },
            controller: 'UserUserfileAddCtrl'
        })

        .state('system.UserfileEdit', {
            // 路由值, 表示该值继承于user
            url: "/UserfileEdit",
            templateUrl: "views/user/userfile/edit.html",
            data: {
                pageTitle: '用户管理——编辑',
                pageDesc: '计量系统用户管理'
            },
            controller: 'UserUserfileEditCtrl',
            params: {
                data: {
                    value: {}
                },
                department: {}
            }
        })

        .state('system.UserfileDetail', {
            // 路由值, 表示该值继承于user
            url: "/UserfileDetail",
            templateUrl: "views/user/userfile/detail.html",
            data: {
                pageTitle: '用户管理——详情页面',
                pageDesc: '计量系统用户管理'
            }
        })

        .state('system.Authority', {
            // 路由值, 表示该值继承于system
            url: "/Authority",
            templateUrl: "views/system/authority/index.html",
            data: {
                pageTitle: '系统设置-权限设置',
                pageDesc: '计量系统权限设置'
            }
        })

        .state('system.Login', {
            // 路由值, 表示该值继承于system
            url: "/Login",
            templateUrl: "views/system/login/index.html",
            data: {
                pageTitle: '系统设置-登录管理',
                pageDesc: '计量系统登录管理'
            }
        })

        .state('system.instrumentType', {
            url: '/instrumentType',
            templateUrl: 'views/system/instrumentType/index.html',
            data: {
                pageTitle: '强检器具类别管理',
                pageDesc: '对器具的种进行管理，每种器具对应的种类是唯一的'
            },
            params: {
                config: {
                    value: {type: 'MandatoryInstrument'}
                },
                page: 0,
                size: config.size,
                discipline: {id: 1}
            },
            controller: 'InstrumentTypeCtrl'
        })

        // ui-route官方文档：https://github.com/angular-ui/ui-router/wiki/URL-Routing#url-parameters
        // https://stackoverflow.com/questions/25647454/how-to-pass-parameters-using-ui-sref-in-ui-router-to-controller
        .state('system.instrumentTypeFirstLevel', {
            url: "/instrumentTypeFirstLevel/:disciplineId",
            templateUrl: "views/system/instrumentTypeFirstLevel/index.html",
            data: {
                pageTitle: '系统设置-强检器具类别管理-管理一级分类',
                pageDesc: '管理一级分类名称'
            },
            controller: 'SystemInstrumenttypeManageFirstLevelIndexCtrl'
        })

        .state('system.instrumentTypeFirstLevelEdit', {
            url: "/instrumentTypeFirstLevelEdit/:id",
            templateUrl: "views/system/instrumentTypeFirstLevel/edit.html",
            data: {
                pageTitle: '一级分类名称编辑',
                pageDesc: '提供一级分类名称以及对应拼音字段的编辑功能'
            },
            params: {
                type: {
                    value: 'edit'
                }
            },
            controller: 'SystemInstrumenttypeManageFirstLevelEditCtrl'
        })

        .state('system.instrumentTypeFirstLevelAdd', {
            url: "/instrumentTypeFirstLevelAdd/:disciplineId",
            templateUrl: "views/system/instrumentTypeFirstLevel/edit.html",
            data: {
                pageTitle: '一级分类名称添加',
                pageDesc: '提供一级分类名称以及对应拼音字段的添加功能'
            },
            params: {
                type: {
                    value: 'add'
                }
            },
            controller: 'SystemInstrumenttypeManageFirstLevelAddCtrl'
        })

        .state('system.instrumentTypeAdd', {
            url: "/instrumentTypeAdd/:disciplineId",
            templateUrl: "views/system/instrumentType/add.html",
            data: {
                pageTitle: '器具种类添加',
                pageDesc: '添加器具种类 名称唯一'
            },
            params: {
                type: {
                    value: 'add'
                },
                config: {
                    value: {
                        type: 'MandatoryInstrument'
                    }
                }
            },
            controller: 'InstrumentTypeAddCtrl'
        })

        .state('system.instrumentTypeEdit', {
            url: "/instrumentTypeEdit/:id",
            templateUrl: "views/system/instrumentType/add.html",
            data: {
                pageTitle: '器具类别编辑',
                pageDesc: '提供器具（二级）类别的编辑功能'
            },
            params: {
                type: {
                    value: 'edit'
                },
                data: {
                    value: {}
                },
                config: {
                    value: {
                        type: 'MandatoryInstrument'
                    }
                }

            },
            controller: 'InstrumentTypeAddCtrl',

        })

        .state('system.standardInstrumentType', {
            url: '/standardInstrumentType',
            templateUrl: 'views/system/instrumentType/index.html',
            data: {
                pageTitle: '标准器类别管理',
                pageDesc: '对器具的种进行管理，每种器具对应的种类是唯一的'
            },
            params: {
                config: {
                    value: {
                        type: 'standardDeviceInstrument'
                    }
                }
            },
            controller: 'InstrumentTypeCtrl'
        })
        // ui-route官方文档：https://github.com/angular-ui/ui-router/wiki/URL-Routing#url-parameters
        // https://stackoverflow.com/questions/25647454/how-to-pass-parameters-using-ui-sref-in-ui-router-to-controller
        .state('system.standardInstrumentTypeAdd', {
            url: "/instrumentTypeAdd/:disciplineId",
            templateUrl: "views/system/instrumentType/add.html",
            data: {
                pageTitle: '系统设置 - 器具种类添加',
                pageDesc: '添加器具种类 名称唯一'
            },
            params: {
                type: {
                    value: 'add'
                },
                config: {
                    value: {
                        type: 'standardInstrument'
                    }
                }
            },
            controller: 'InstrumentTypeAddCtrl'
        })

        .state('system.standardInstrumentTypeEdit', {
            url: "/instrumentTypeEdit",
            templateUrl: "views/system/instrumentType/add.html",
            data: {
                pageTitle: '系统设置 - 器具种类添加',
                pageDesc: '添加器具种类 名称唯一'
            },
            params: {
                type: {
                    value: 'edit'
                }
            },
            config: {
                value: {
                    type: 'standardInstrument'
                }
            },
            controller: 'InstrumentTypeAddCtrl'

        })

        .state('system.qualifierCertificateType', {
            // 路由值, 表示该值继承于system
            url: "/qualifierCertificateType",
            templateUrl: "views/system/qualifiercertificatetype/index.html",
            data: {
                pageTitle: '系统设置-资格证类别管理',
                pageDesc: '计量系统菜单管理'
            }
        })
        //计量科技园地
        .state('technology', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/technology", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '计量科技园地'
            }
        })
        .state('technology.Dynamic', {
            // 路由值, 表示该值继承于technology
            url: "/Dynamic",
            templateUrl: "views/technology/dynamic/index.html",
            data: {
                pageTitle: '计量科技园地',
                pageDesc: '新闻动态'
            }
        })

        .state('technology.Newregulations', {
            // 路由值, 表示该值继承于technology
            url: "/Newregulations",
            templateUrl: "views/technology/newregulations/index.html",
            data: {
                pageTitle: '计量科技园地',
                pageDesc: '新法规宣讲'
            }
        })

        .state('technology.Law', {
            // 路由值, 表示该值继承于technology
            url: "/Law",
            templateUrl: "views/technology/law/index.html",
            data: {
                pageTitle: '计量科技园地',
                pageDesc: '法律法规'
            }
        })

        .state('technology.Information', {
            // 路由值, 表示该值继承于technology
            url: "/Information",
            templateUrl: "views/technology/information/index.html",
            data: {
                pageTitle: '计量科技园地',
                pageDesc: '新闻资讯'
            }
        })

        // 我的消息
        .state('myMessage', {
            // 路由值
            abstract: true, // 表示此路由不对应具体的页面
            url: "/myMessage", // 对应的URL信息
            templateUrl: "views/common/content.html", // 模板文件
            data: { // 数据
                pageTitle: '我的消息'
            }
        })

        .state('myMessage.inbox', {
            url: "/inbox/page/:page/size/:size",
            templateUrl: "views/myMessage/inbox/index.html",
            data: {
                pageTitle: '收件箱',
                pageDesc: '我的消息-收件箱'
            },
            params: {
                page: {
                    value: '0'
                },
                size: {
                    value: config.size.toString()
                }
            },
            controller: 'MyMessageInboxIndexCtrl'
        })

        .state('myMessage.inboxDetail', {
            url: "/inboxDetail/:id",
            templateUrl: "views/myMessage/inbox/detai1.html",
            data: {
                pageTitle: '收件箱详情',
                pageDesc: '我的消息-收件箱-详情'
            },
            params: {
                id: {
                    value: '0'
                }
            },
            controller: 'MyMessageInboxDetailCtrl'
        })

        .state('myMessage.inboxReply', {
            url: "/inboxReply/:id",
            templateUrl: "views/myMessage/inbox/reply.html",
            data: {
                pageTitle: '收件箱',
                pageDesc: '我的消息-收件箱-回复'
            },
            params: {
                id: {
                    value: '0'
                }
            },
            controller: 'MyMessageInboxReplyCtrl'
        })

        .state('myMessage.sent', {
            url: "/sent/page/:page/size/:size",
            templateUrl: "views/myMessage/sent/index.html",
            data: {
                pageTitle: '已发送',
                pageDesc: '我的消息-已发送'
            },
            params: {
                page: {
                    value: '0'
                },
                size: {
                    value: config.size.toString()
                }
            },
            controller: 'MyMessageSentIndexCtrl'
        })

        .state('myMessage.sentDetail', {
            url: "/sentDetail/:id",
            templateUrl: "views/myMessage/sent/detai1.html",
            data: {
                pageTitle: '已发送详情',
                pageDesc: '我的消息-已发送-详情'
            },
            params: {
                id: {
                    value: '0'
                }
            },
            controller: 'MyMessageSentDetailCtrl'
        })

        .state('myMessage.unReadMessage', {
            url: "/unReadMessage/page/:page/size/:size",
            templateUrl: "views/myMessage/inbox/index.html",
            data: {
                pageTitle: '未读消息',
                pageDesc: '我的消息-未读消息'
            },
            params: {
                page: {
                    value: '0'
                },
                size: {
                    value: config.size.toString()
                }
            },
            controller: 'MyMessageUnReadMessageIndexCtrl'
        })

        .state('myMessage.unReadMessageDetail', {
            url: "/unReadMessageDetail",
            templateUrl: "views/myMessage/unReadMessage/detai1.html",
            data: {
                pageTitle: '未读消息详情',
                pageDesc: '我的消息-未读消息-详情'
            }
        })

        .state('myMessage.unReadMessageReply', {
            url: "/unReadMessageReply",
            templateUrl: "views/myMessage/unReadMessage/reply.html",
            data: {
                pageTitle: '未读消息回复',
                pageDesc: '我的消息-未读消息-回复'
            }
        })

        // 强检器具统计管理
        .state('forceInstrumentStatistics', {
            abstract: true,                               // 表示此路由不对应具体的页面
            url: "/forceInstrumentStatistics",            // 对应的URL信息
            templateUrl: "views/common/content.html",     // 模板文件
            data: {
                pageTitle: '强检器具统计管理'
            }
        })
        .state('forceInstrumentStatistics.checkRate', {
            url: "/checkRate",
            templateUrl: 'views/forceInstrumentStatistics/checkRate/index.html',
            data: {
                pageTitle: '强检器具受检率统计',
                pageDesc: '强检器具统计管理-强检器具受检率统计'
            },
            controller: 'ForceinstrumentstatisticsCheckrateIndexCtrl'
        })
        .state('forceInstrumentStatistics.qualifiedRate', {
            url: "/qualifiedRate",
            templateUrl: 'views/forceInstrumentStatistics/qualifiedRate/index.html',
            data: {
                pageTitle: '一次性检定合格率统计',
                pageDesc: '强检器具统计管理-一次性检定合格率统计'
            },
            controller: 'ForceinstrumentstatisticsQualifiedrateIndexCtrl'
        })
        .state('forceInstrumentStatistics.checkAbility', {
            url: "/checkAbility",
            templateUrl: 'views/forceInstrumentStatistics/checkAbility/index.html',
            data: {
                pageTitle: '检定能力统计',
                pageDesc: '强检器具统计管理-检定能力统计'
            },
            controller: 'ForceInstrumentStatisticsCheckAbilityIndexCtrl'
        })
        .state('forceInstrumentStatistics.coverageRate', {
            url: "/coverageRate",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '检定能力覆盖率统计',
                pageDesc: '强检器具统计管理-检定能力覆盖率统计'
            }
        })
        .state('forceInstrumentStatistics.violationRate', {
            url: "/violationRate",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '违规出证率统计',
                pageDesc: '强检器具统计管理-违规出证率统计'
            }
        })
        .state('forceInstrumentStatistics.timelyRate', {
            url: "/timelyRate",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '出证及时率统计',
                pageDesc: '强检器具统计管理-出证及时率统计'
            }
        })

        // 报警管理
        .state('alarmManagement', {
            abstract: true,                               // 表示此路由不对应具体的页面
            url: "/alarmManagement",                      // 对应的URL信息
            templateUrl: "views/common/content.html",     // 模板文件
            data: {
                pageTitle: '报警管理'
            }
        })
        .state('alarmManagement.certificateOverdue', {
            url: "/certificateOverdue",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '证书超期查询',
                pageDesc: '报警管理-证书超期查询'
            }
        })
        .state('alarmManagement.evidenceOverdue', {
            url: "/evidenceOverdue",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '超期出证查询',
                pageDesc: '报警管理-超期出证查询'
            }
        })
        .state('alarmManagement.evidenceOverRange', {
            url: "/evidenceOverRange",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '超范围出证查询',
                pageDesc: '报警管理-超范围出证查询'
            }
        })

        // 非强检器具检定申请管理
        .state('nonForceInstrumentCheckApplyManagement', {
            abstract: true,                                  // 表示此路由不对应具体的页面
            url: "/nonForceInstrumentCheckApplyManagement",  // 对应的URL信息
            templateUrl: "views/common/content.html",        // 模板文件
            data: {
                pageTitle: '非强检器具检定申请管理'
            }
        })
        .state('nonForceInstrumentCheckApplyManagement.checkApply', {
            url: "/checkApply",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '检定申请',
                pageDesc: '非强检器具检定申请管理-检定申请'
            }
        })
        .state('nonForceInstrumentCheckApplyManagement.integratedQuery', {
            url: "/integratedQuery",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '综合查询',
                pageDesc: '非强检器具检定申请管理-综合查询'
            }
        })

        // 非强检器具检定信息综合查询
        .state('nonForceInstrumentCheckInfoQuery', {
            abstract: true,                                  // 表示此路由不对应具体的页面
            url: "/nonForceInstrumentCheckInfoQuery",        // 对应的URL信息
            templateUrl: "views/common/content.html",        // 模板文件
            data: {
                pageTitle: '非强检器具检定信息综合查询'
            }
        })
        .state('nonForceInstrumentCheckInfoQuery.infoIntegratedQuery', {
            url: "/infoIntegratedQuery",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '综合查询',
                pageDesc: '非强检器具检定信息综合查询-综合查询'
            }
        })

        // 非强检器具校准申请管理
        .state('nonForceInstrumentCorrectApplyManagement', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/nonForceInstrumentCorrectApplyManagement",   // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '非强检器具校准申请管理'
            }
        })
        .state('nonForceInstrumentCorrectApplyManagement.correctApply', {
            url: "/correctApply",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '校准申请',
                pageDesc: '非强检器具校准申请管理-校准申请'
            }
        })

        // 非强检器具校准信息综合查询
        .state('nonForceInstrumentCorrectInfoQuery', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/nonForceInstrumentCorrectInfoQuery",         // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '非强检器具校准信息综合查询'
            }
        })
        .state('nonForceInstrumentCorrectInfoQuery.correctInfoQuery', {
            url: "/correctInfoQuery",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '校准信息综合查询',
                pageDesc: '非强检器具校准信息综合查询-校准信息综合查询'
            }
        })

        // 定量包装商品净含量监督管理
        .state('wareNetContentSupervision', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/wareNetContentSupervision",                  // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '定量包装商品净含量监督管理'
            }
        })

        // 定量包装商品经营单位档案
        .state('wareBusinessFile', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/wareBusinessFile",                           // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '定量包装商品经营单位档案'
            }
        })

        // 定量包装商品生产单位档案
        .state('wareProductionFile', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/wareProductionFile",                         // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '定量包装商品生产单位档案'
            }
        })

        // 定量包装商品监督抽查计划
        .state('wareSupervisoryCheckPlan', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/wareSupervisoryCheckPlan",                   // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '定量包装商品监督抽查计划'
            }
        })

        // 定量包装商品监督抽查结果
        .state('wareSupervisoryCheckResult', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/wareSupervisoryCheckResult",                 // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '定量包装商品监督抽查结果'
            }
        })

        // 定量包装检验原始记录
        .state('checkPrimaryRecord', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/checkPrimaryRecord",                         // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '定量包装检验原始记录'
            }
        })

        // 定量包装商品监督抽查通报
        .state('wareSupervisoryCheckBulletin', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/wareSupervisoryCheckBulletin",               // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '定量包装商品监督抽查通报'
            }
        })

        // 注册信息
        .state('quantitativePackageRegisterInfo', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/quantitativePackageRegisterInfo",            // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '注册信息'
            }
        })

        // 技术机构监督考核模块
        .state('technicalInstitutionAssessment', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/technicalInstitutionAssessment",             // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '技术机构监督考核模块'
            }
        })
        .state('technicalInstitutionAssessment.assessmentApply', {
            url: "/assessmentApply",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '考核申请',
                pageDesc: '技术机构监督考核模块-考核申请'
            }
        })
        .state('technicalInstitutionAssessment.examinerFile', {
            url: "/examinerFile",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '考评员档案',
                pageDesc: '技术机构监督考核模块-考评员档案'
            }
        })
        .state('technicalInstitutionAssessment.examinerFirstTrial', {
            url: "/examinerFirstTrial",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '考评员初审',
                pageDesc: '技术机构监督考核模块-考评员初审'
            }
        })
        .state('technicalInstitutionAssessment.fieldAssessmentPlan', {
            url: "/fieldAssessmentPlan",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '现场考核计划',
                pageDesc: '技术机构监督考核模块-现场考核计划'
            }
        })
        .state('technicalInstitutionAssessment.firstMeeting', {
            url: "/firstMeeting",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '首次会议',
                pageDesc: '技术机构监督考核模块-首次会议'
            }
        })
        .state('technicalInstitutionAssessment.fieldAssessmentRecord', {
            url: "/fieldAssessmentRecord",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '现场考核记录',
                pageDesc: '技术机构监督考核模块-现场考核记录'
            }
        })
        .state('technicalInstitutionAssessment.assessmentReport', {
            url: "/assessmentReport",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '考评报告',
                pageDesc: '技术机构监督考核模块-考评报告'
            }
        })
        .state('technicalInstitutionAssessment.abilityComparison', {
            url: "/abilityComparison",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '能力对比',
                pageDesc: '技术机构监督考核模块-能力对比'
            }
        })
        .state('technicalInstitutionAssessment.correctiveMeasures', {
            url: "/correctiveMeasures",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '整改措施',
                pageDesc: '技术机构监督考核模块-整改措施'
            }
        })
        .state('technicalInstitutionAssessment.correctiveReport', {
            url: "/correctiveReport",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '整改报告',
                pageDesc: '技术机构监督考核模块-整改报告'
            }
        })
        .state('technicalInstitutionAssessment.trackingReport', {
            url: "/trackingReport",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '跟踪报告',
                pageDesc: '技术机构监督考核模块-跟踪报告'
            }
        })

        // 计量标准考核（复查）申请
        .state('measurementStandardAssessmentApply', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/measurementStandardAssessmentApply",         // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '计量标准考核（复查）申请'
            }
        })
        .state('measurementStandardAssessmentApply.queryFunction', {
            url: "/queryFunction",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '查询功能',
                pageDesc: '计量标准考核（复查）申请-查询功能'
            }
        })
        .state('measurementStandardAssessmentApply.newBuiltFunction', {
            url: "/newBuiltFunction",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '新建功能',
                pageDesc: '计量标准考核（复查）申请-新建功能'
            }
        })
        .state('measurementStandardAssessmentApply.uploadFunction', {
            url: "/uploadFunction",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '上传功能',
                pageDesc: '计量标准考核（复查）申请-上传功能'
            }
        })
        .state('measurementStandardAssessmentApply.firstTrial', {
            url: "/firstTrial",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '初审',
                pageDesc: '计量标准考核（复查）申请-初审'
            }
        })
        .state('measurementStandardAssessmentApply.organizationEvaluationUnit', {
            url: "/organizationEvaluationUnit",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '组织考评单位/考评组',
                pageDesc: '计量标准考核（复查）申请-组织考评单位/考评组'
            }
        })
        .state('measurementStandardAssessmentApply.settingAssessmentReport', {
            url: "/settingAssessmentReport",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '设定考核报告',
                pageDesc: '计量标准考核（复查）申请-设定考核报告'
            }
        })
        .state('measurementStandardAssessmentApply.auditResultsEvaluation', {
            url: "/auditResultsEvaluation",
            templateUrl: config.developingPage,
            data: {
                pageTitle: '审核结果评定',
                pageDesc: '计量标准考核（复查）申请-审核结果评定'
            }
        })

        // 能源计量器具管理
        .state('energyMeasurementInstrumentsManagement', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/energyMeasurementInstrumentsManagement",     // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '能源计量器具管理'
            }
        })

        // 能源计量器具档案
        .state('energyMeasurementInstrumentsFile', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/energyMeasurementInstrumentsFile",           // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '能源计量器具档案'
            }
        })

        // 用能系统管理
        .state('energyUseSystemManagement', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/energyUseSystemManagement",                  // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '用能系统管理'
            }
        })

        // 能源计量人员管理
        .state('energyMeteringPersonnelManagement', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/energyMeteringPersonnelManagement",          // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '能源计量人员管理'
            }
        })

        // 用能管理
        .state('energyUseManagement', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/energyUseManagement",                        // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '用能管理'
            }
        })

        // 重点用能企业信息查询
        .state('keyEnergyEnterpriseInfoQuery', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/keyEnergyEnterpriseInfoQuery",               // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '重点用能企业信息查询'
            }
        })

        // 重点用能企业数据分析
        .state('keyEnergyEnterpriseDataAnalysis', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/keyEnergyEnterpriseDataAnalysis",            // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '重点用能企业数据分析'
            }
        })

        // 区域能耗数据分析
        .state('regionalEnergyDataAnalysis', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/regionalEnergyDataAnalysis",                 // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '区域能耗数据分析'
            }
        })

        // 授权检定机构监督抽查
        .state('authorizedInspectionAgencySupervise', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/authorizedInspectionAgencySupervise",        // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '授权检定机构监督抽查'
            }
        })

        // 计量标准监督抽查
        .state('measurementStandardInspection', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/measurementStandardInspection",              // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '计量标准监督抽查'
            }
        })

        // 计量器具制造企业监督抽查
        .state('measurementInstrumentsManufacturingInspection', {
            abstract: true,                                           // 表示此路由不对应具体的页面
            url: "/measurementInstrumentsManufacturingInspection",    // 对应的URL信息
            templateUrl: "views/common/content.html",                 // 模板文件
            data: {
                pageTitle: '计量器具制造企业监督抽查'
            }
        })

        // 定量包装商品净含量监督抽查
        .state('wareNetContentInspection', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/wareNetContentInspection",                   // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '定量包装商品净含量监督抽查'
            }
        })

        // 重点计量器具监督抽查
        .state('keyMeasurementInstrumentsInspection', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/keyMeasurementInstrumentsInspection",        // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '重点计量器具监督抽查'
            }
        })

        // 重点耗能企业计量器具监督抽查
        .state('keyEnergyEnterpriseInstrumentsInspection', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/keyEnergyEnterpriseInstrumentsInspection",   // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '重点耗能企业计量器具监督抽查'
            }
        })

        // 能源标识使用单位监督抽查
        .state('energyLabelUseEnterpriseInspection', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/energyLabelUseEnterpriseInspection",         // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '能源标识使用单位监督抽查'
            }
        })

        // 法定计量单位监督抽查
        .state('legalMeasurementEnterpriseInspection', {
            abstract: true,                                     // 表示此路由不对应具体的页面
            url: "/legalMeasurementEnterpriseInspection",       // 对应的URL信息
            templateUrl: "views/common/content.html",           // 模板文件
            data: {
                pageTitle: '法定计量单位监督抽查'
            }
        });
}

angular
    .module('webappApp')
    // 系统配置文件
    .constant('config', {
        maxDisplayPageCount: 7, // 最大显示的分页数
        size: 10, // 默认每页大小
        xAuthTokenName: 'x-auth-token', // 认证字段
        apiUrl: 'resource', // api接口地址(使用了zuul代理后，采用前后台同源策略。在此仅配置为资源服务前缀)
        loginPath: '/login', // 入口地址
        mainPath: '/main/dashboard', // 首页地址
        cookiesExpiresTime: 1800000, // cookies过期时间
        rootScopeConfig: { // rootScope的配置信息
            title: '计量信息管理服务平台',
            owner: '赤峰市工商管理质量技术监督局',
            technicalSupport: '<a href="http://lfshitong.com" target="_blank">世通科技有限公司</a>',
            beginYear: 2015,
            currentYear: (new Date()).getFullYear()
        },
        defaultRoute: '/login', // 默认路由
        developingPage: 'views/developing.html',
        debug: true
    })
    // 配置选项（可更改）
    .value('configOptions', {
        themes: [{ // 主题信息
            name: 'homeAdmin',
            headerHeight: '62px'
        },
            {
                name: 'basicAdmin',
                headerHeight: '88px'
            }
        ]
    })
    // 注入$http完成对URL请求的统一设置
    .factory('apiUrlHttpInterceptor', function ($cookies, config, $q, $location) {
        // 定义API接口地址
        var apiUrl = config.apiUrl;

        // 判断是否应该添加前缀
        var shouldPrependApiUrl = function (reqConfig) {
            if (!apiUrl) {
                return false;
            }
            // todo:以http或https打头时，返回false;
            return !(/[\s\S]*.html/.test(reqConfig.url) || /[\s\S]*.json/.test(reqConfig.url) ||
                (reqConfig.url && reqConfig.url.indexOf(apiUrl) === 0));
        };
        // 获取cookies过期时间
        var getCookiesExpireDate = function () {
            var now = new Date();
            now.setTime(now.getTime() + config.cookiesExpiresTime);
            return now;
        };
        // 是否应该将xAuthToken添加到header信息中
        var shouldAddXAuthToken = function (reqConfig) {
            // 如果为用户认证，或是已经带有x-auth-token进行提交，则返回 false
            if (reqConfig.headers.authorization || reqConfig.headers[config.xAuthTokenName]) {
                return false;
            } else {
                return true;
            }
        };
        return {
            request: function (reqConfig) {
                // 如果请求不以 .json|html|js|css 结尾，则进行请求url的改写
                if (apiUrl && shouldPrependApiUrl(reqConfig)) {
                    reqConfig.url = apiUrl + reqConfig.url;
                    // 如果用户非认证操作，且并没有带有x-auth-token进行请求，则在headers中加入x-auth-token
                    if (shouldAddXAuthToken(reqConfig)) {
                        reqConfig.headers[config.xAuthTokenName] = $cookies.get(config.xAuthTokenName);
                        $cookies.put(config.xAuthTokenName, $cookies.get(config.xAuthTokenName), {
                            expires: getCookiesExpireDate()
                        });
                    }
                }
                return reqConfig;
            },
            // 响应失败
            responseError: function (rejection) {
                if (rejection.status === 401) {
                    // 错误码为401，则跳转到登录界面
                    $location.path(config.loginPath);
                    return $q.reject(rejection);
                } else {
                    return $q.reject(rejection);
                }
            }
        };
    })
    // $http 注入，当发生请求时，自动添加前缀
    .config(['$httpProvider', function ($httpProvider) {
        // 增加header信息，防止弹出401对话框。
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        // 加入apiUrlHttpInterceptor，拦截401错误以及加入loading.
        $httpProvider.interceptors.push('apiUrlHttpInterceptor');
    }])
    .config(['cfpLoadingBarProvider', function (cfpLoadingBarProvider) {
        cfpLoadingBarProvider.spinnerTemplate = '<div id="loading-yz-bar"><div class="loading"><div class="table-cell"><div class="loading-main"><img src="styles/svg/loading-bars.svg" width="256" height="64"><p>正在努力为您加载...</p></div></div></div></div>';
        // cfpLoadingBarProvider.spinnerTemplate = '';
    }])
    .config(configState)
    .run(function ($rootScope, $state, editableOptions, UserServer, $location, config, configOptions) {
        // 检测当前用户状态，
        UserServer.checkUserIsLogin(function (status) {
            if (status === true) {
                // 已登录, 注册路由
            } else {
                if ($location.path() === '/resetPassWord' || $location.path() === '/check') {
                    // 重置密码或者系统核验提示界面
                } else {
                    // 未登录，则跳转至登录界面。所以，登录界面与首页这两个路中需要手动注册。
                    $location.path(config.loginPath);
                }
            }
        });
        // 设置系统信息
        $rootScope.config = config.rootScopeConfig;
        $rootScope.$state = $state;
        editableOptions.theme = 'bs3';
        $rootScope.theme = configOptions.themes[1];
    });
