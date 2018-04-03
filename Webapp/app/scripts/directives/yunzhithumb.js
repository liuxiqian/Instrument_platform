'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiThumb
 * @description
 * # yunzhiThumb
 */
angular.module('webappApp')
    .directive('yunzhiThumb', ['$window', function($window) {
        var helper = {
            support: !!($window.FileReader && $window.CanvasRenderingContext2D),
            isFile: function(item) {
                return angular.isObject(item) && item instanceof $window.File;
            },
            isImage: function(file) {
                var type =  '|' + file.type.slice(file.type.lastIndexOf('/') + 1) + '|';
                return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
            }
        };

        return {
            restrict: 'A',
            template: '<canvas/>',
            link: function(scope, element, attributes) {

                if (!helper.support) {
                    return;
                }

                var params = scope.$eval(attributes.yunzhiThumb);

                if (!helper.isFile(params.file)) {
                    return;
                }
                if (!helper.isImage(params.file)) {
                    return;
                }

                var canvas = element.find('canvas');
                var reader = new FileReader();

                reader.onload = function loadFile(event) {
                    var img = new Image();
                    img.onload = OnLoadImage;
                    img.src = event.target.result;
                };

                reader.readAsDataURL(params.file);

                function OnLoadImage() {
                    var self = this;
                    var width = params.width || self.width / self.height * params.height;
                    var height = params.height || self.height / self.width * params.width;
                    canvas.attr({ width: width, height: height });
                    canvas[0].getContext('2d').drawImage(self, 0, 0, width, height);
                }
            }
        };
    }]);
