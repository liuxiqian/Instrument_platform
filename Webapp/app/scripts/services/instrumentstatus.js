'use strict';

/**
 * @ngdoc service
 * @name webappApp.instrumentStatus
 * @description
 * # instrumentStatus
 * Value in the webappApp.
 * 器具状态
 */
angular.module('webappApp')
    .value('instrumentStatus', {
        normal: 0,  // 正常
        new: -1,    // 新增
        backed:  -2, // 退回
        stoped: 1,  // 停用
        scrap: 2    // 报废
    });
