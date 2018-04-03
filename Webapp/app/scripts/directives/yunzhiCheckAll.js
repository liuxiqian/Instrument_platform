'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiCheckAll
 * @全选与返全反选
 * # yunzhiCheckAll
 */
angular.module('webappApp')
    .directive('yunzhiCheckAll', function() {

        return {
            scope: {
                ngModel: '=?',
                lists: '=',
                key: '@?' // 键值. 
            },
            templateUrl: 'views/directive/yunzhiCheckAll.html',
            restrict: 'EA',
            link: function postLink(scope) {
                var self = this;
                self.init = function() {
                    if (typeof(scope.key === 'undefined')) {
                        scope.key = '_checked';
                    }

                    if (typeof(scope.ngModel === 'undefined') || (scope.ngModel === 'false') || (scope.ngModel === false)) {
                        scope.ngModel = false;
                    } else {
                        scope.ngModel = true;
                    }
                };


                self.reset = function(ngModel, lists) {
                    angular.forEach(lists, function(value) {
                        value[scope.key] = ngModel;
                    });
                };

                self.watchNgModel = function(newValue) {
                    self.reset(newValue, scope.lists);
                };

                self.watchLists = function(newValue) {
                    self.reset(scope.ngModel, newValue);
                };


                self.init();
                scope.$watch('ngModel', self.watchNgModel);
                scope.$watch('lists', self.watchLists);
            }
        };
    });
