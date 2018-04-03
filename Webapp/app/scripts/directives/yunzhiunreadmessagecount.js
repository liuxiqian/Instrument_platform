'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiUnReadMessageCount
 * @description 获取消息的未读数量 （显示在屏幕的右上方）
 * # yunzhiUnReadMessageCount
 */
angular.module('webappApp')
  .directive('yunzhiUnReadMessageCount', ['ToMessageService', 'superCache', function (ToMessageService, superCache) {
    return {
      template: '',
      restrict: 'E',    // 元素
      link: function postLink(scope, element) {
          // 判断缓存中是否存在未读消息数量
          if (typeof superCache.get('unReadMessageCount') === 'undefined') {
              // 获取当前用户的所有未读收件消息
              ToMessageService.pageReceiveUnReadMessageOfCurrentUser(undefined, function(data) {
                  // 存入缓存
                  superCache.put('unReadMessageCount', data.totalElements);
                  // 显示文本信息
                  element.text(superCache.get('unReadMessageCount'));
              });
          } else {
              // 显示文本信息
              element.text(superCache.get('unReadMessageCount'));
          }
      }
    };
  }]);
