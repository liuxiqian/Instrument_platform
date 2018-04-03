'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MymessageInboxEditorCtrl
 * @description
 * # MymessageInboxEditorCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('MymessageInboxEditorCtrl', function ($scope) {
      // 文本内容初始化
      $scope.message.content = [''].join('');

      $scope.summernoteOpt = {
          toolbar: [
              ['headline', ['style']],
              ['style', ['bold', 'italic', 'underline', 'superscript', 'subscript', 'strikethrough', 'clear']],
              ['textsize', ['fontsize']],
              ['alignment', ['ul', 'ol', 'paragraph', 'lineheight']],
          ]
      };
  });
