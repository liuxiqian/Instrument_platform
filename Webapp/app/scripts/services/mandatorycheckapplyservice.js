'use strict';

/**
 * @ngdoc service
 * @name webappApp.MandatoryCheckApplyService
 * @description
 * # MandatoryCheckApplyService
 * 强检器具检定申请
 * panjie
 */
angular.module('webappApp')
    .service('MandatoryCheckApplyService', function () {
        var self = this;
        /**
         * 是否显示查看
         * @param    {[type]}                 object 对象
         * @return   {}
         * @author 梦云智 http://www.mengyunzhi.com
         * @DateTime 2017-11-22T14:13:41+0800
         */
        self.showView = function (object) {
            if (object.reply === true) {
                return true;
            } else {
                return false;
            }
        };

        /**
         * 是否显示回复
         * @param    {[type]}                 object [description]
         * @return   {[type]}                        [description]
         * @author 梦云智 http://www.mengyunzhi.com
         * @DateTime 2017-11-22T14:13:59+0800
         */
        self.showReply = function (object) {
            return !self.showView(object);
        };

        return self;
    });
