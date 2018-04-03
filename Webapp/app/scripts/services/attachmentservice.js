'use strict';

/**
 * @ngdoc service
 * @name webappApp.attachmentService
 * @description
 * # attachmentService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('AttachmentService', ['$http', function ($http, CommonService) {
      var self = this;

      // 删除附件
      self.delete = function (id, callback) {
          // 利用$http获取代码
          $http.delete('/Attachment/' + id).then(function successCallback(response) {
              if (callback) {
                  callback(response.status);
              }
          },function errorCallback(response) {
              CommonService.httpError(response);
          });
      };

      // 上传图片类型的附件
      self.uploadImage = function (image, callback) {
          var url = "/Attachment/uploadImage";

          // post文件类型的参数，使Content-Type:multipart/form-data;
          var formData = new FormData();
          formData.append('attachment', image);

          $http.post(url, formData, {
              transformRequest: angular.identity,
              headers: {'Content-Type': undefined}
          })
          .then(function success(response){
              if (callback) {
                  callback(response.status, response.data);
              }
          }, function error(response) {
              CommonService.httpError(response);
          });
      };

      return{
          delete: self.delete,
          uploadImage: self.uploadImage
      };
  }]);
